package com.heitian.ssm.model;

import java.util.Date;

/**
 * Created by Zhang Jingzhuo on 2017/6/11.
 */
public class LogRecord {
    private Integer logId;

    private Date logTime;

    private String userId;

    private Integer logType;

    private Integer logResult;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public Integer getLogResult() {
        return logResult;
    }

    public void setLogResult(Integer logResult) {
        this.logResult = logResult;
    }
}
