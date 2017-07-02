package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.UserDao;
import com.heitian.ssm.model.User;
import com.heitian.ssm.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/10.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    public int deleteByPrimaryKey(String userId) {
        return userDao.deleteByPrimaryKey(userId);
    }

    public int insert(User record) {
        return userDao.insert(record);
    }

    public int insertSelective(User record) {
        return userDao.insertSelective(record);
    }

    public User selectByPrimaryKey(String userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    public List<User> selectAllUser() {
        return userDao.selectAllUser();
    }

    public List<User> selectUserByIdAndPassword(String userId, String userPassword) {
        return userDao.selectUserByIdAndPassword(userId,userPassword);
    }

    public List<User> selectUserByUniversityAndSchool(Integer userUniversity, Integer userSchool) {
        return userDao.selectUserByUniversityAndSchool(userUniversity,userSchool);
    }

    public int updateByPrimaryKeySelective(User record) {
        return userDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(User record) {
        return userDao.updateByPrimaryKey(record);
    }
}
