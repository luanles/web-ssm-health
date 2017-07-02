package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.UserTestDao;
import com.heitian.ssm.model.UserTest;
import com.heitian.ssm.service.UserTestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserTestServiceImpl implements UserTestService {
    
    @Resource
    private UserTestDao userTestDao;

    public UserTest getUserById(Long userId) {return userTestDao.selectUserById(userId);}

    public UserTest getUserByUserName(String username) {return userTestDao.selectUserByUserName(username);}

    public List<UserTest> selectUserByIdAndPassword(String username, String password) {
        return userTestDao.selectUserByIdAndPassword(username,password);
    }

    public List<UserTest> getAllUser() {return userTestDao.selectAllUser();}

    public void addUser(String username, String password) {
        userTestDao.addUser(username,password);}
}
