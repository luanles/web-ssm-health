package com.heitian.ssm.model;

/**
 * Created by Zhang Jingzhuo on 2017/6/8.
 */
public class Resource {

    private Integer rId;
    private Integer rTopic;
    private String rContent;
    private String rTitle;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getrTopic() {
        return rTopic;
    }

    public void setrTopic(Integer rTopic) {
        this.rTopic = rTopic;
    }

    public String getrContent() {
        return rContent;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent == null ? null : rContent.trim();
    }

    public String getrTitle() {
        return rTitle;
    }

    public void setrTitle(String rTitle) {
        this.rTitle = rTitle == null ? null : rTitle.trim();
    }
}
