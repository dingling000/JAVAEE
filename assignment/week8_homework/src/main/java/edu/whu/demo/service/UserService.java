package edu.whu.demo.service;

import edu.whu.demo.dao.RoleRepository;
import edu.whu.demo.dao.UserRepository;
import edu.whu.demo.entity.Role;
import edu.whu.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${app.admin.user}")
    String adminUserName = "admin";

    @Value("${app.admin.password}")
    String adminPassword = "admin";


    /**
     * 将查询结果添加到缓存user中，以name作为key
     * @param name
     * @return
     */
    @Cacheable(cacheNames = "user",key = "#name",condition = "#name!=null")
    public User getUser(String name){
        Optional<User> u = userRepository.findById(name);
        return u.isPresent()?u.get():null;
    }

    /**
     * 程序启动后，自动创建Admin角色和管理员账号
     */
    @Transactional
    @PostConstruct
    public void init(){
        Optional<Role> roleAdmin = roleRepository.findById("Admin");
        Role role=roleAdmin.isPresent()? roleAdmin.get():null;
        if(role==null){
            role=new Role();
            role.setName("Admin");
            role.getAuthorities().add("Administration");
            roleRepository.save(role);
        }

        Optional<User> adminUser = userRepository.findById(adminUserName);
        if(!adminUser.isPresent()){
            User admin =new User();
            admin.setName(adminUserName);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.getRoles().add(role);
            userRepository.save(admin);
        }else{
            User admin=adminUser.get();
            if(!passwordEncoder.matches(adminPassword,admin.getPassword())){
                admin.setPassword(passwordEncoder.encode(adminPassword));
                userRepository.save(admin);
            }
        }
    }

    /**
     * 移除缓存user中key为userName的对象
     * @param userName
     */
    @CacheEvict(cacheNames = "user",key = "#userName")
    public void deleteUser(String userName) {
        userRepository.deleteById(userName);
    }

    /**
     * 移除缓存user中key为name的对象
     * @param name
     * @param user
     * @return
     */
    @CacheEvict(cacheNames = "user",key = "#name")
    public User updateUser(String name, User user) {
        if(name==null ||!name.equals(user.getName())||!userRepository.findById(name).isPresent()){
            throw new RuntimeException("update error");
        }
        String pwd = user.getPassword();
        if(pwd!=null){
            user.setPassword(passwordEncoder.encode(pwd));
        }
        return userRepository.saveAndFlush(user);
    }


    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        String pwd = user.getPassword();
        if(pwd!=null){
            user.setPassword(passwordEncoder.encode(pwd));
        }
        return userRepository.save(user);
    }

    @Transactional
    public void changePassword(String userName, String password) {
        userRepository.changePassword(userName,passwordEncoder.encode(password));
    }
}
