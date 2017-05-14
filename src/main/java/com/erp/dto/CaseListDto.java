package com.erp.dto;


public class CaseListDto {
	/**
	 * 案卷id
	 */
	private int id;

	/**
	 * 督案单编号 -
	 */
	private String caselistNo;

	/**
	 * 案件ID
	 * 
	 */
	private String case_id;
	
	/**
	 * 案卷编号 -
	 */
	private String caseNo;

	/**
	 * 办案民警 --
	 */
	private String policeName;

	/*
	 * 执法建议 --
	 */
	private String suggest;

	/**
	 * 审核人 --
	 */
	private String audit;

	/**
	 * 涉案财物移交情况 --
	 */
	private String possessionsExplain;

	/**
	 * 查证时间 --
	 */
	private String caseendTime;

	/**
	 * 未通过理由 --
	 */
	private String reason;

	private String state;

	/*
	 * 备注
	 */
	private String remarks;

	/**
	 * 纸质督案单地址
	 */
	private String url;
	
	private String caselistStatus;
	

	public String getCaselistStatus() {
        return caselistStatus;
    }

    public void setCaselistStatus(String caselistStatus) {
        this.caselistStatus = caselistStatus;
    }
    
    

    public String getCase_id() {
        return case_id;
    }

    public void setCase_id(String case_id) {
        this.case_id = case_id;
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

	

	public String getCaseendTime() {
        return caseendTime;
    }

    public void setCaseendTime(String caseendTime) {
        this.caseendTime = caseendTime;
    }

    public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}




    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((audit == null) ? 0 : audit.hashCode());
        result = prime * result + ((caseNo == null) ? 0 : caseNo.hashCode());
        result = prime * result + ((case_id == null) ? 0 : case_id.hashCode());
        result = prime * result
                + ((caseendTime == null) ? 0 : caseendTime.hashCode());
        result = prime * result
                + ((caselistNo == null) ? 0 : caselistNo.hashCode());
        result = prime * result
                + ((caselistStatus == null) ? 0 : caselistStatus.hashCode());
        result = prime * result + id;
        result = prime * result
                + ((policeName == null) ? 0 : policeName.hashCode());
        result = prime
                * result
                + ((possessionsExplain == null) ? 0 : possessionsExplain
                        .hashCode());
        result = prime * result + ((reason == null) ? 0 : reason.hashCode());
        result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((suggest == null) ? 0 : suggest.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CaseListDto other = (CaseListDto) obj;
        if (audit == null) {
            if (other.audit != null)
                return false;
        } else if (!audit.equals(other.audit))
            return false;
        if (caseNo == null) {
            if (other.caseNo != null)
                return false;
        } else if (!caseNo.equals(other.caseNo))
            return false;
        if (case_id == null) {
            if (other.case_id != null)
                return false;
        } else if (!case_id.equals(other.case_id))
            return false;
        if (caseendTime == null) {
            if (other.caseendTime != null)
                return false;
        } else if (!caseendTime.equals(other.caseendTime))
            return false;
        if (caselistNo == null) {
            if (other.caselistNo != null)
                return false;
        } else if (!caselistNo.equals(other.caselistNo))
            return false;
        if (caselistStatus == null) {
            if (other.caselistStatus != null)
                return false;
        } else if (!caselistStatus.equals(other.caselistStatus))
            return false;
        if (id != other.id)
            return false;
        if (policeName == null) {
            if (other.policeName != null)
                return false;
        } else if (!policeName.equals(other.policeName))
            return false;
        if (possessionsExplain == null) {
            if (other.possessionsExplain != null)
                return false;
        } else if (!possessionsExplain.equals(other.possessionsExplain))
            return false;
        if (reason == null) {
            if (other.reason != null)
                return false;
        } else if (!reason.equals(other.reason))
            return false;
        if (remarks == null) {
            if (other.remarks != null)
                return false;
        } else if (!remarks.equals(other.remarks))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (suggest == null) {
            if (other.suggest != null)
                return false;
        } else if (!suggest.equals(other.suggest))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }
	
	
}
