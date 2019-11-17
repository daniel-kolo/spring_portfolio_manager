package com.controller;

import com.domain.User;
import com.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/users")
    public List<User> getUsers (){
        return userRepo.findAll();
    }

    @GetMapping("/user/{Id}")
     public Optional<User> getUser(@PathVariable("Id") int Id){
        return userRepo.findById(Id);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        userRepo.save(user);
        return user;
    }

    @PutMapping("/user")
    public User saveOrUpdateUser(@RequestBody  User user){
        userRepo.save(user);
        return user;
    }

    @DeleteMapping("/user/{Id}")
    public String deleteUser(@PathVariable int Id){
        User user = userRepo.getOne(Id);
        userRepo.delete(user);
        return "deleted";
    }


}
