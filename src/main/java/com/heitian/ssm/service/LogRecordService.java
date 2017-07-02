package com.heitian.ssm.service;
import com.heitian.ssm.model.LogRecord;

import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/11.
 */

public interface LogRecordService {

    int insert(LogRecord record);

    List<LogRecord> selectByUserId(String userId);

}
