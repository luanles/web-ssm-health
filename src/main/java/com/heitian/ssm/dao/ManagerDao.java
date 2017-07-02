package com.heitian.ssm.dao;
import com.heitian.ssm.model.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/7.
 */
@Repository
public interface ManagerDao {
    int deleteByPrimaryKey(String mId);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey( String mId);

    List<Manager> selectByIdAndPassword(@Param("mId") String mId, @Param("mPassword") String mPassword);

    List<Manager> selectByIsKey(Integer mKey);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
}
