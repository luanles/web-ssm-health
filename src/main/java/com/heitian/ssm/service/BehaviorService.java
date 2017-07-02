package com.heitian.ssm.service;

import com.heitian.ssm.model.Behavior;

import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/12.
 */
public interface BehaviorService {

    int insert(Behavior record);

    List<Behavior> selectByUserId(String userId);

    List<Behavior> selectAllBehavior();

}
