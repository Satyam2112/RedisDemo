package com.api.redis.dao;

import com.api.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private static final String KEY="USER";


    @CacheEvict(value="USER", allEntries = true)
    public User save(User user){
        System.out.println("Saving user :");
        redisTemplate.opsForHash().put(KEY, user.getUserId(), user);
        return user;
    }

    @Cacheable(key="#userId", value = "User")
    public User get(String userId){
        System.out.println("Getting user :"+userId);
        return (User)redisTemplate.opsForHash().get(KEY,userId);
    }
    @Cacheable("USER")
    public Map<Object,Object> findAll(){
        System.out.println("Getting All users :");
        return redisTemplate.opsForHash().entries(KEY);
    }

    @CacheEvict(key = "#userId",value = "User")
    public void delete(String userId){
        System.out.println("Deleting user :"+userId);
        redisTemplate.opsForHash().delete(KEY,userId);
    }
}
