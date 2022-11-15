package edu.whu.demo.controller;

import edu.whu.demo.entity.User;
import edu.whu.demo.security.JwtTokenUtil;
import edu.whu.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('Administration')")
    public List<User> findAllUser(){
        return userService.findAll();
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('Administration')")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('Administration')")
    public void deleteUser(@PathVariable String userName){
        userService.deleteUser(userName);
    }


    @GetMapping("{userName}")
    @PreAuthorize("hasAuthority('user/query') or #userName == authentication.name")
    public User getUser(@PathVariable String userName){
        return userService.getUser(userName);
    }


    @PutMapping("")
    @PreAuthorize("hasAuthority('Administration')")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user.getName(),user);
    }

    @PatchMapping("/changePassword/{userName}")
    @PreAuthorize("hasAuthority('Administration') or #userName == authentication.name")
    public void changePassword(@PathVariable String userName, String password){
         userService.changePassword(userName,password);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
        final String token = jwtTokenUtil.generateToken(user.getName());
        return ResponseEntity.ok(token);
    }

}
