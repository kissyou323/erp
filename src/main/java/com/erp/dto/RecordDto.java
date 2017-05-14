package com.erp.dto;

import java.util.Date;

public class RecordDto {
    
    private Integer recordId;
    private String recordNo;
    private String recordStyle;
    private String recordName;
    private String recordTime;
    private String useDepartment_login;
    private String useDepartment;
    private String agent;
    private String useExplain;
    private String status;
    private String style;
    private Date startTime;
    private Date endTime;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public String getRecordStyle() {
        return recordStyle;
    }

    public void setRecordStyle(String recordStyle) {
        this.recordStyle = recordStyle;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getUseDepartment() {
        return useDepartment;
    }

    public void setUseDepartment(String useDepartment) {
        this.useDepartment = useDepartment;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getUseExplain() {
        return useExplain;
    }

    public void setUseExplain(String useExplain) {
        this.useExplain = useExplain;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUseDepartment_login() {
        return useDepartment_login;
    }

    public void setUseDepartment_login(String useDepartment_login) {
        this.useDepartment_login = useDepartment_login;
    }

    @Override
    public String toString() {
        return "RecordDto [recordId=" + recordId + ", recordNo=" + recordNo
                + ", recordStyle=" + recordStyle + ", recordName=" + recordName
                + ", recordTime=" + recordTime + ", useDepartment_login="
                + useDepartment_login + ", useDepartment=" + useDepartment
                + ", agent=" + agent + ", useExplain=" + useExplain
                + ", status=" + status + ", style=" + style + ", startTime="
                + startTime + ", endTime=" + endTime + "]";
    }
    
}
