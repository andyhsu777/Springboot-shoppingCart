package com.example.springbootshoppingcart.service.impl;

import com.example.springbootshoppingcart.dao.UserDao;
import com.example.springbootshoppingcart.dto.UserRegisterRequest;
import com.example.springbootshoppingcart.model.User;
import com.example.springbootshoppingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

}
