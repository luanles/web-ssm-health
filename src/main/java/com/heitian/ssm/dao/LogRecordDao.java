package com.heitian.ssm.dao;

import com.heitian.ssm.model.LogRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/11.
 */
@Repository
public interface LogRecordDao {

    int deleteByPrimaryKey(Integer logId);

    int insert(LogRecord record);

    int insertSelective(LogRecord record);

    LogRecord selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(LogRecord record);

    int updateByPrimaryKey(LogRecord record);

    List<LogRecord> selectByUserId(String userId);

}
