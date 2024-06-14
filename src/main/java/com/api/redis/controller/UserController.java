package com.api.redis.controller;

import com.api.redis.dao.UserDao;
import com.api.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@EnableCaching
public class UserController {

    @Autowired
    UserDao userDao;

    @PostMapping("/add")
    public User saveUser(@RequestBody User user){
        user.setUserId(UUID.randomUUID().toString());
        return userDao.save(user);
    }

    @GetMapping("/{userId}")
    public User get(@PathVariable("userId") String userId){
        return userDao.get(userId);
    }

    @GetMapping("/getAll")
    public Map<Object, Object> findAll(){
        return userDao.findAll();
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") String userId){
        userDao.delete(userId);
    }

}
