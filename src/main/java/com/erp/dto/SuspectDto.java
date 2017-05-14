package com.erp.dto;

import com.alibaba.fastjson.JSON;
public class SuspectDto {

    // 唯一编号
    private Integer id;
    // 嫌疑人名称
    private String suspectName;
    // 身份证号码
    private String identity;
    // 电话号码
    private String mobileNo;
    // 备注信息
    private String remarks;
    // 案件信息
    private String caseNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSuspectName() {
        return suspectName;
    }

    public void setSuspectName(String suspectName) {
        this.suspectName = suspectName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
