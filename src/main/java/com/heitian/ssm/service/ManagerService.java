package com.heitian.ssm.service;
import com.heitian.ssm.model.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/7.
 */
public interface ManagerService {

    int insert(Manager record);

    Manager selectByPrimaryKey(String mId);

    List<Manager> selectByIdAndPassword(String mId, String mPassword);

    List<Manager> selectByIsKey(Integer mKey);

}
