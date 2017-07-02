package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.LogRecordDao;
import com.heitian.ssm.model.LogRecord;
import com.heitian.ssm.service.LogRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Zhang Jingzhuo on 2017/6/11.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LogRecordServiceImpl implements LogRecordService {

    @Resource
    private LogRecordDao logRecordDao;

    public int insert(LogRecord record) {
        return logRecordDao.insert(record);
    }

    public List<LogRecord> selectByUserId(String userId) {
        return logRecordDao.selectByUserId(userId);
    }

}
