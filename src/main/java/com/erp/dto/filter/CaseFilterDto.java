package com.erp.dto.filter;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * 
 *
 * @author 王建观
 * @since 2015年12月11日 下午2:52:32
 */
public class CaseFilterDto implements Serializable {

    private static final long serialVersionUID = -1508514225698812067L;

    private String caseNo;

    private String caseName;
    
    private String policeLoginName;

    private String policeName;

    private Date caseTime_start;

    private Date caseTime_end;

    private Date startTime_start;

    private Date startTime_end;

    private Date endTime_start;

    private Date endTime_end;

    private String caseStatus;

    private String fileStatus;

    private String style;
    
    private String isCaseList;
    

    /**
     * 结案状态1-未结案2-结案审核中2-已结案
     */
    private String endcaseStatus;
    
    
    /**
     * 是否是子案件1是2否
     */
    private String sonStatus;
    
    

    public String getEndcaseStatus() {
        return endcaseStatus;
    }

    public void setEndcaseStatus(String endcaseStatus) {
        this.endcaseStatus = endcaseStatus;
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
    
    public String getPoliceLoginName() {
        return policeLoginName;
    }

    public void setPoliceLoginName(String policeLoginName) {
        this.policeLoginName = policeLoginName;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public Date getCaseTime_start() {
        return caseTime_start;
    }

    public void setCaseTime_start(Date caseTime_start) {
        this.caseTime_start = caseTime_start;
    }

    public Date getCaseTime_end() {
        return caseTime_end;
    }

    public void setCaseTime_end(Date caseTime_end) {
        this.caseTime_end = caseTime_end;
    }

    public Date getStartTime_start() {
        return startTime_start;
    }

    public void setStartTime_start(Date startTime_start) {
        this.startTime_start = startTime_start;
    }

    public Date getStartTime_end() {
        return startTime_end;
    }

    public void setStartTime_end(Date startTime_end) {
        this.startTime_end = startTime_end;
    }

    public Date getEndTime_start() {
        return endTime_start;
    }

    public void setEndTime_start(Date endTime_start) {
        this.endTime_start = endTime_start;
    }

    public Date getEndTime_end() {
        return endTime_end;
    }

    public void setEndTime_end(Date endTime_end) {
        this.endTime_end = endTime_end;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
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
