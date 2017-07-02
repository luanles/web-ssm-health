package com.heitian.ssm.service;
import com.heitian.ssm.model.Resource;

import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/8.
 */
public interface ResourceService {

    Resource selectByPrimaryKey(Integer rId);

    List<Resource> selectAllItem();

    List<Resource> selectByTopic(String rTopic);

    int insert(Resource record);

}
