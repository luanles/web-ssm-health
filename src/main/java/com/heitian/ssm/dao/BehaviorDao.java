package com.heitian.ssm.dao;

import com.heitian.ssm.model.Behavior;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/12.
 */
@Repository
public interface BehaviorDao {
    int deleteByPrimaryKey(Integer bId);

    int insert(Behavior record);

    int insertSelective(Behavior record);

    Behavior selectByPrimaryKey(Integer bId);

    int updateByPrimaryKeySelective(Behavior record);

    int updateByPrimaryKey(Behavior record);

    List<Behavior> selectByUserId(String userId);

    List<Behavior> selectAllBehavior();
}
