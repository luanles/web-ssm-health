package com.heitian.ssm.model;
/**
 * Created by Zhang Jingzhuo on 2017/6/10.
 */

import java.util.Date;

public class User {

    private String userId;
    private String userName;
    private String userSex;
    private Integer userUniversity;
    private Integer userSchool;
    private String isWarn;
    private String userPhone;
    private String userPassword;

    public String getUserId() { return userId;}

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public Integer getUserUniversity() {
        return userUniversity;
    }

    public void setUserUniversity(Integer userUniversity) {
        this.userUniversity = userUniversity;
    }

    public Integer getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(Integer userSchool) {
        this.userSchool = userSchool;
    }

    public String getIsWarn() {
        return isWarn;
    }

    public void setIsWarn(String isWarn) {
        this.isWarn = isWarn == null ? null : isWarn.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

}
