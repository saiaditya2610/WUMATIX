package com.example.demo.repository;

import java.util.List;

import com.example.demo.beans.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    
    List<User> findByMail(String mail);

}
