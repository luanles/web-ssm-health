package com.heitian.ssm.service;

import com.heitian.ssm.model.BehaviorAna;
import com.heitian.ssm.model.BehaviorAnaKey;

import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/22.
 */
public interface BehaviorAnaService {
    int insert(BehaviorAna record);

    BehaviorAna selectByPrimaryKey(BehaviorAnaKey key);

    List<BehaviorAna> selectByUser(int userId);

}
