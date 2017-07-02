package com.heitian.ssm.dao;

import com.heitian.ssm.model.UserTest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface UserTestDao {

    UserTest selectUserById(@Param("userId") Long userId);

    UserTest selectUserByUserName(@Param("user_name") String username);

    List<UserTest> selectUserByIdAndPassword(@Param("username") String username, @Param("password") String password);

    List<UserTest> selectAllUser();

    void addUser(@Param("username") String username, @Param("password")String password);

}