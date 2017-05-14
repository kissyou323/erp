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
@Table(name = "t_record")
public class Record implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "record_id", unique = true, nullable = false)
	private Integer recordId;

	/**
	 * 编号（涉案财物编号、案件编号、装备编号、资产编号）
	 */
	@Column(name = "record_no")
	private String recordNo;

	/**
	 * 型号
	 */
	@Column(name = "record_style")
	private String recordStyle;

	/**
	 * 名称
	 */
	@Column(name = "record_name")
	private String recordName;

	/**
	 * 借出（还入）时间
	 */
	@Column(name = "record_time")
	private Date recordTime;

	/**
	 * 使用人
	 */
	@Column(name = "use_department")
	private String useDepartment;

	/**
	 * 经办人
	 */
	@Column(name = "agent")
	private String agent;

	/**
	 * 使用说明（还入可为空）
	 */
	@Column(columnDefinition = "TEXT",name = "use_explain")
	private String useExplain;

	/**
	 * 状态（3-借出、1-还入）
	 */
	@Column(name = "status")
	private String status;

	/**
	 * 类型（1-资产、2-装备 3-案卷 4-涉案财物）
	 */
	@Column(name = "style")
	private String style;

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

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
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

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
