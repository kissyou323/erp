package com.erp.dto.filter;

import java.util.Date;

/**
 * 
 *
 * @author 王建观
 * @since 2015年12月11日 下午2:52:32
 */
public class RecordFilterDto {

	private String recordNo;
	
	private String recordName;
	
	private Date recordTime;
	
	private String style;

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	
    
}
