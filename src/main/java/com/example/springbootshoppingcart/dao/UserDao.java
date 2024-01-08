package com.example.springbootshoppingcart.dao;

import com.example.springbootshoppingcart.dto.UserRegisterRequest;
import com.example.springbootshoppingcart.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer UserId);

    User getUserByEmail(String email);
}
