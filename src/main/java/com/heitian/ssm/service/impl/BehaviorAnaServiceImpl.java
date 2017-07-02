package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.BehaviorAnaDao;
import com.heitian.ssm.model.BehaviorAna;
import com.heitian.ssm.model.BehaviorAnaKey;
import com.heitian.ssm.service.BehaviorAnaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/22.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BehaviorAnaServiceImpl implements BehaviorAnaService{

    @Resource
    private BehaviorAnaDao behaviorAnaDao;

    public int insert(BehaviorAna record) {
        return behaviorAnaDao.insert(record);
    }

    public BehaviorAna selectByPrimaryKey(BehaviorAnaKey key) {
        return behaviorAnaDao.selectByPrimaryKey(key);
    }

    public List<BehaviorAna> selectByUser(int userId) {
        return behaviorAnaDao.selectByUser(userId);
    }

}
