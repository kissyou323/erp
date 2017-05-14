package com.erp.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_caselist")
public class CaseList implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 案卷id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    

    /**
     * 督案单编号
     */
    @Column(name = "caselist_no")
    private String caselistNo;
    /**
     * 案卷ID
     * 
     */
    @Column(name = "case_id")
    private int case_id;
    /**
     * 案卷编号
     */
    @Column(name = "case_no")
    private String caseNo;

    /**
     * 办案民警
     */
    @Column(name = "police_name")
    private String policeName;

    /*
     * 执法建议
     */
    @Column(columnDefinition = "TEXT",name = "suggest")
    private String suggest;

    /**
     * 审核人
     */
    @Column(name = "audit")
    private String audit;

    /**
     * 涉案财物移交情况
     */
    @Column(columnDefinition = "TEXT",name = "possessions_explain")
    private String possessionsExplain;

    /**
     * 查证时间
     */
    @Column(name = "caseend_time")
    private Date caseendTime;

    /**
     * 未通过理由
     */
    @Column(columnDefinition = "TEXT",name = "reason")
    private String reason;

    /**
     * 是否通告(1否2是)
     */
    @Column(name = "state")
    private String state;

    /*
     * 备注
     */
    @Column(columnDefinition = "TEXT",name = "remarks")
    private String remarks;

    /**
     * 纸质督案单地址
     */
    @Column(name = "url")
    private String url;
    
    @Column(name = "caselist_status")
    private String caselistStatus;
    
    

    public String getCaselistStatus() {
        return caselistStatus;
    }

    public void setCaselistStatus(String caselistStatus) {
        this.caselistStatus = caselistStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaselistNo() {
        return caselistNo;
    }

    public void setCaselistNo(String caselistNo) {
        this.caselistNo = caselistNo;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getPossessionsExplain() {
        return possessionsExplain;
    }

    public void setPossessionsExplain(String possessionsExplain) {
        this.possessionsExplain = possessionsExplain;
    }

    public Date getCaseendTime() {
        return caseendTime;
    }

    public void setCaseendTime(Date caseendTime) {
        this.caseendTime = caseendTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    @Override
    public String toString() {
        return "CaseList [id=" + id + ", caselistNo=" + caselistNo
                + ", caseNo=" + caseNo + ", policeName=" + policeName
                + ", suggest=" + suggest + ", audit=" + audit
                + ", possessionsExplain=" + possessionsExplain
                + ", caseendTime=" + caseendTime + ", reason=" + reason
                + ", state=" + state + ", remarks=" + remarks + ", url=" + url
                + ", caselistStatus=" + caselistStatus + "]";
    }

}