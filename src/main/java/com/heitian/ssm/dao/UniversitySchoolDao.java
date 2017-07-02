package com.heitian.ssm.dao;

import com.heitian.ssm.model.UniversitySchool;
import com.heitian.ssm.model.UniversitySchoolKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/30.
 */
@Repository
public interface UniversitySchoolDao {

    int deleteByPrimaryKey(UniversitySchoolKey key);

    int insert(UniversitySchool record);

    int insertSelective(UniversitySchool record);

    UniversitySchool selectByPrimaryKey(UniversitySchoolKey key);

    int updateByPrimaryKeySelective(UniversitySchool record);

    int updateByPrimaryKey(UniversitySchool record);

    List<UniversitySchool> selectByUniversityAndSchool(@Param("uni_name") String uni_name, @Param("sch_name") String sch_name);
}