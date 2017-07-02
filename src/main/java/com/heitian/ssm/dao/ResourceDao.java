package com.heitian.ssm.dao;
import com.heitian.ssm.model.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/8.
 */
@Repository
public interface ResourceDao {
    int deleteByPrimaryKey(Integer rId);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer rId);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Resource> selectAllItem();

    List<Resource> selectByTopic(String rTopic);


}
