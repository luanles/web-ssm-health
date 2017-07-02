package com.heitian.ssm.service;

import com.heitian.ssm.model.UserTest;

import java.util.List;

public interface UserTestService {

    List<UserTest> getAllUser();

    List<UserTest> selectUserByIdAndPassword(String username, String password);

    UserTest getUserById(Long userId);

    UserTest getUserByUserName(String username);

    void addUser(String username, String password);

}
