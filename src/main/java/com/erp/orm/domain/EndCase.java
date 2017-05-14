package com.erp.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_endcase")
public class EndCase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "case_id", unique = true, nullable = false)
    private int caseId;

    @Column(name = "case_no")
    private String caseNo;

    @Column(name = "case_add_url")
    private String caseAddUrl;

    @Column(name = "endcase_explain",columnDefinition="text")
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

}
