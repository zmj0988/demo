package com.example.demo.domain;

public class Group {

    private Integer groupId;

    private String groupName;

    private Integer status;

    private Boolean isAdvance;

    public Group(Integer groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean isAdvance() {
        return isAdvance;
    }

    public void setAdvance(Boolean advance) {
        isAdvance = advance;
    }
}
