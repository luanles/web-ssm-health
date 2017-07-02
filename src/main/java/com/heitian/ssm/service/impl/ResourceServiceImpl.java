package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.ResourceDao;
import com.heitian.ssm.model.Resource;
import com.heitian.ssm.service.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/8.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl implements ResourceService {

    @javax.annotation.Resource
    private ResourceDao resourceDao;

    public Resource selectByPrimaryKey(Integer rId) {return resourceDao.selectByPrimaryKey(rId);}

    public List<Resource> selectAllItem() {return resourceDao.selectAllItem();}

    public List<Resource> selectByTopic(String rTopic){return resourceDao.selectByTopic(rTopic);}

    public int insert(Resource record) {return resourceDao.insert(record);}

}
