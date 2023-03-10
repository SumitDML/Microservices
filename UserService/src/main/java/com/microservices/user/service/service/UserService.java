package com.microservices.user.service.service;

import com.microservices.user.service.entity.User;

import java.util.List;

public interface UserService {

    //create
    User saveUser(User user);
    //get all users
    List<User> getAllUser();

    User getUser(String userId);

    String deleteUser(String userId);

    User updateUser(User user);



}
