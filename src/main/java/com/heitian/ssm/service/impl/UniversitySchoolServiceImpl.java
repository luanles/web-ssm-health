package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.UniversitySchoolDao;
import com.heitian.ssm.model.UniversitySchool;
import com.heitian.ssm.model.UniversitySchoolKey;
import com.heitian.ssm.service.UniversitySchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/30.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UniversitySchoolServiceImpl implements UniversitySchoolService{

    @Resource
    private UniversitySchoolDao universitySchoolDao;



    public UniversitySchool selectByPrimaryKey(UniversitySchoolKey key) {
        return universitySchoolDao.selectByPrimaryKey(key);
    }

    public List<UniversitySchool> selectByUniversityAndSchool(String uni_name, String sch_name) {
        return universitySchoolDao.selectByUniversityAndSchool(uni_name,sch_name);
    }
}
