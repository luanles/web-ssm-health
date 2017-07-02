package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.ManagerDao;
import com.heitian.ssm.model.Manager;
import com.heitian.ssm.service.ManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/7.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class ManagerServiceImpl implements ManagerService {

    @Resource
    private ManagerDao managerDao;

    public int insert(Manager record) {return managerDao.insert(record);}

    //不能区分是不是待注册的
    public Manager selectByPrimaryKey(String mId) { return managerDao.selectByPrimaryKey(mId);}

    public List<Manager> selectByIdAndPassword(String mId, String mPassword) {
        return managerDao.selectByIdAndPassword(mId,mPassword);
    }

    //区分是够通过了审核
    public List<Manager> selectByIsKey(Integer mKey) {
        return managerDao.selectByIsKey(mKey);
    }
}
