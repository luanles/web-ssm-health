package com.heitian.ssm.model;

import java.util.Date;

public class BehaviorAnaKey {
    private Integer userId;

    private Date anaTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getAnaTime() {
        return anaTime;
    }

    public void setAnaTime(Date anaTime) {
        this.anaTime = anaTime;
    }
}