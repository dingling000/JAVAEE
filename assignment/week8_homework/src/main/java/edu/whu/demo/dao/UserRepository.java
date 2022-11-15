package edu.whu.demo.dao;

import edu.whu.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.name = :userName")
    public void changePassword(String userName,String password);
}
