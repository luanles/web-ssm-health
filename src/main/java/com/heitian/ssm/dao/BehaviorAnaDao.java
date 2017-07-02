package com.heitian.ssm.dao;

import com.heitian.ssm.model.BehaviorAna;
import com.heitian.ssm.model.BehaviorAnaKey;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/22.
 */
@Repository
public interface BehaviorAnaDao {
    int deleteByPrimaryKey(BehaviorAnaKey key);

    int insert(BehaviorAna record);

    int insertSelective(BehaviorAna record);

    BehaviorAna selectByPrimaryKey(BehaviorAnaKey key);

    int updateByPrimaryKeySelective(BehaviorAna record);

    int updateByPrimaryKey(BehaviorAna record);

    List<BehaviorAna> selectByUser(int userId);
}