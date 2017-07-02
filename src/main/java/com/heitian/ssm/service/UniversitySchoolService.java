package com.heitian.ssm.service;

import com.heitian.ssm.model.UniversitySchool;
import com.heitian.ssm.model.UniversitySchoolKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/30.
 */
public interface UniversitySchoolService {

    UniversitySchool selectByPrimaryKey(UniversitySchoolKey key);

    List<UniversitySchool> selectByUniversityAndSchool(String uni_name, String sch_name);

}
