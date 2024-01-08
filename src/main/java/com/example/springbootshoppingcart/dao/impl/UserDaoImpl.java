package com.example.springbootshoppingcart.dao.impl;

import com.example.springbootshoppingcart.dao.UserDao;
import com.example.springbootshoppingcart.dto.UserRegisterRequest;
import com.example.springbootshoppingcart.model.User;
import com.example.springbootshoppingcart.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO user(email, password, created_date, last_modified_date) VALUE (:email," +
                ":password, :createdDate, :lastModifiedDate)";
        Map<String,Object> map = new HashMap<>();
        map.put("email",userRegisterRequest.getEmail());
        map.put("password",userRegisterRequest.getPassword());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int userId = keyHolder.getKey().intValue();
        return userId;
    }

    @Override
    public User getUserById(Integer UserId) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date from user where user_id = :userId";
        Map<String,Object> map = new HashMap<>();
        map.put("userId",UserId);
        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());
        if(userList.size() > 0){
            return userList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date from user where email = :email";
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        List<User> list = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if(list.size() > 0){
            return list.get(0);
        }else {
            return null;
        }
    }


}
