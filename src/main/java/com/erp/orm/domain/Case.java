package com.erp.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSON;

@Entity
@Table(name = "t_case")
public class Case implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 案卷id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "case_id", unique = true, nullable = false)
    private int caseId;

    /**
     * 案卷编号
     */
    @Column(name = "case_no")
    private String caseNo;

    /**
     * 案卷名字
     */
    @Column(name = "case_name")
    private String caseName;
    
    /**
     * 办案民警登录名
     */
    @Column(name = "police_login_name")
    private String policeLoginName;

    /**
     * 办案民警
     */
    @Column(name = "police_name")
    private String policeName;

    /*
     * 案发时间
     */
    @Column(name = "case_time")
    private Date caseTime;

    /**
     * 案发地点
     */
    @Column(name = "case_address")
    private String caseAddress;

    /**
     * 入库时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 结案时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 案件状态（1-未结案、2-已结案3案件撤销4案件合并）
     */
    @Column(name = "case_status")
    private String caseStatus;

    /**
     * 档案状态(1-还入2-建档5-归档3借出4其他6-移交)
     */
    @Column(name = "file_status")
    private String fileStatus;

    /**
     * 二维码图片url
     */
    @Column(name = "qrcode")
    private String qrcode;

    /**
     * 最后修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 最后修改人
     */
    @Column(name = "modify_name")
    private String modifyName;

    /**
     * 简要案情
     */
    @Column(columnDefinition = "TEXT",name = "case_explain")
    private String caseExplain;

    /**
     * 涉案财物编号
     */
    @Column(name = "possessions_no")
    private String possessionsNo;
    
    /**
     * 涉案财物名称
     */
    @Column(name = "possessions_name")
    private String possessionsName;

    /**
     * 案件类型（1-行政、2-刑事）
     */
    @Column(name = "style")
    private String style;

    /**
     * 结案时上传登记的图片
     */
    @Column(name = "case_add_url")
    private String caseAddUrl;

    /**
     * 结案时的描述
     */
    @Column(columnDefinition = "TEXT",name = "endcase_explain")
    private String endcaseExplain;
    
    /**
     * 结案状态1-未结案2-结案审核中2-已结案
     */
    @Column(name = "endcase_status")
    private String endcaseStatus;
    
    /**
     * 申请结案的驳回意见
     */
    @Column(columnDefinition = "TEXT",name = "rejectmsg")
    private String rejectmsg;
    
    /**
     * 父案件编号
     */
    @Column(name = "parent_caseno")
    private String parentCaseno;
    
    /**
     * 是否是子案件1是2否
     */
    @Column(name = "son_status")
    private String sonStatus;
    
    /**
     * 是否已督案0未督案1已督案
     */
    @Column(name = "is_caseList")
    private String isCaseList;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public Date getCaseTime() {
        return caseTime;
    }

    public void setCaseTime(Date caseTime) {
        this.caseTime = caseTime;
    }

    public String getCaseAddress() {
        return caseAddress;
    }

    public void setCaseAddress(String caseAddress) {
        this.caseAddress = caseAddress;
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

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public String getCaseExplain() {
        return caseExplain;
    }

    public void setCaseExplain(String caseExplain) {
        this.caseExplain = caseExplain;
    }

    public String getPoliceLoginName() {
        return policeLoginName;
    }

    public void setPoliceLoginName(String policeLoginName) {
        this.policeLoginName = policeLoginName;
    }

    public String getPossessionsNo() {
        return possessionsNo;
    }

    public void setPossessionsNo(String possessionsNo) {
        this.possessionsNo = possessionsNo;
    }

    public String getPossessionsName() {
        return possessionsName;
    }

    public void setPossessionsName(String possessionsName) {
        this.possessionsName = possessionsName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getCaseAddUrl() {
        return caseAddUrl;
    }

    public void setCaseAddUrl(String caseAddUrl) {
        this.caseAddUrl = caseAddUrl;
    }

    public String getEndcaseExplain() {
        return endcaseExplain;
    }

    public void setEndcaseExplain(String endcaseExplain) {
        this.endcaseExplain = endcaseExplain;
    }
    
    public String getEndcaseStatus() {
        return endcaseStatus;
    }

    public void setEndcaseStatus(String endcaseStatus) {
        this.endcaseStatus = endcaseStatus;
    }

    public String getRejectmsg() {
        return rejectmsg;
    }

    public void setRejectmsg(String rejectmsg) {
        this.rejectmsg = rejectmsg;
    }

    public String getParentCaseno() {
        return parentCaseno;
    }

    public void setParentCaseno(String parentCaseno) {
        this.parentCaseno = parentCaseno;
    }

    public String getSonStatus() {
        return sonStatus;
    }

    public void setSonStatus(String sonStatus) {
        this.sonStatus = sonStatus;
    }
    
    

    public String getIsCaseList() {
        return isCaseList;
    }

    public void setIsCaseList(String isCaseList) {
        this.isCaseList = isCaseList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
