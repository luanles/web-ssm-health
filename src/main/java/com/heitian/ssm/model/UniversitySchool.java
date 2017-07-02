package com.heitian.ssm.model;

/**
 * Created by Zhang Jingzhuo on 2017/6/30.
 */

public class UniversitySchool extends UniversitySchoolKey {
    private String uniName;

    private String schName;

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName == null ? null : uniName.trim();
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName == null ? null : schName.trim();
    }
}