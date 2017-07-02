package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.BehaviorDao;
import com.heitian.ssm.model.Behavior;
import com.heitian.ssm.service.BehaviorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/12.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BehaviorServiceImpl implements BehaviorService {

    @Resource
    private BehaviorDao behaviorDao;

    public int insert(Behavior record) {
        return behaviorDao.insert(record);
    }

    public List<Behavior> selectByUserId(String userId) {
        return behaviorDao.selectByUserId(userId);
    }

    public List<Behavior> selectAllBehavior() {
        return behaviorDao.selectAllBehavior();
    }

}
