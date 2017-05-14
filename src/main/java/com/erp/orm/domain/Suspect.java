package com.erp.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSON;

@Entity
@Table(name = "t_suspect")
public class Suspect {

    // 唯一编号
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    // 嫌疑人名称
    @Column(name = "suspect_name")
    private String suspectName;
    // 身份证号码
    @Column(name = "identity")
    private String identity;
    // 电话号码
    @Column(name = "mobile_no")
    private String mobileNo;
    // 备注信息
    @Column(columnDefinition = "TEXT",name = "remarks")
    private String remarks;
    // 案件信息
    @Column(name = "case_no")
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
