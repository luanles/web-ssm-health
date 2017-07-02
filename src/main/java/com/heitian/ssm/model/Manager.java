package com.heitian.ssm.model;

/**
 * Created by Zhang Jingzhuo on 2017/6/7.
 */
public class Manager {

    private String mId;
    private String mName;
    private String mSex;
    private String mUniversity;
    private String mSchool;
    private Integer mKey;
    private String mPhone;
    private String mPassword;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId == null ? null : mId.trim();
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName == null ? null : mName.trim();
    }

    public String getmSex() {
        return mSex;
    }

    public void setmSex(String mSex) {
        this.mSex = mSex == null ? null : mSex.trim();
    }

    public String getmUniversity() {
        return mUniversity;
    }

    public void setmUniversity(String mUniversity) {
        this.mUniversity = mUniversity == null ? null : mUniversity.trim();
    }

    public String getmSchool() {
        return mSchool;
    }

    public void setmSchool(String mSchool) {
        this.mSchool = mSchool == null ? null : mSchool.trim();
    }

    public Integer getmKey() {
        return mKey;
    }

    public void setmKey(Integer mKey) {
        this.mKey = mKey;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone == null ? null : mPhone.trim();
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword == null ? null : mPassword.trim();
    }
}