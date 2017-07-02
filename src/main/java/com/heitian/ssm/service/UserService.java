package com.heitian.ssm.service;

/**
 * Created by Zhang Jingzhuo on 2017/6/10.
 */

import com.heitian.ssm.model.User;

import java.util.List;

public interface UserService {

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    List<User> selectAllUser();

    List<User> selectUserByIdAndPassword(String userId, String userPassword);

    List<User> selectUserByUniversityAndSchool(Integer userUniversity, Integer userSchool);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
