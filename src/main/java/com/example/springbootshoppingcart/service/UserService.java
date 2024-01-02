package com.example.springbootshoppingcart.service;

import com.example.springbootshoppingcart.dto.UserRegisterRequest;
import com.example.springbootshoppingcart.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
