package com.heitian.ssm.model;

import java.util.Date;

/**
 * Created by Zhang Jingzhuo on 2017/6/12.
 */
public class Behavior {
    private Integer bId;

    private Date bTime;

    private String bUserId;

    private Integer bRId;

    private Integer bType;

    private String bTestresult;

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public Date getbTime() {
        return bTime;
    }

    public void setbTime(Date bTime) {
        this.bTime = bTime;
    }

    public String getbUserId() {
        return bUserId;
    }

    public void setbUserId(String bUserId) {
        this.bUserId = bUserId == null ? null : bUserId.trim();
    }

    public Integer getbRId() {
        return bRId;
    }

    public void setbRId(Integer bRId) {
        this.bRId = bRId;
    }

    public Integer getbType() {
        return bType;
    }

    public void setbType(Integer bType) {
        this.bType = bType;
    }

    public String getbTestresult() {
        return bTestresult;
    }

    public void setbTestresult(String bTestresult) {
        this.bTestresult = bTestresult == null ? null : bTestresult.trim();
    }
}
