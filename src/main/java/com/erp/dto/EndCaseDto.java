package com.erp.dto;

import com.erp.orm.domain.EndCase;

public class EndCaseDto {

    private int caseId;

    private String caseNo;

    private String caseAddUrl;

    private String endcaseExplain;

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

    public EndCaseDto(EndCase endCase) {
       if(null !=endCase){
           this.caseId=endCase.getCaseId();
           this.caseNo =endCase.getCaseNo();
           this.caseAddUrl = endCase.getCaseAddUrl();
           this.endcaseExplain = endCase.getEndcaseExplain();
       }
    }
    
    

}
