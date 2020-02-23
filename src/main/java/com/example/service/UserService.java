package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    public User findUserById(Integer userId) {
        return userMapper.findUserById(userId);
    }

    public Integer addUser(User user){
        return userMapper.addUser(user);
    }

    public Integer updatePwdById(User user){
        return userMapper.updatePwdById(user);
    }
}
