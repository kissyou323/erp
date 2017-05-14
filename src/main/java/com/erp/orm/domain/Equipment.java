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
@Table(name = "t_equipment")
public class Equipment implements Serializable {

	private static final long serialVersionUID = 5623380575087386621L;

	// 装备id
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "equipment_id", unique = true, nullable = false)
	private int equipmentId;

	// 装备编号
	@Column(name = "equipment_no")
	private String equipmentNo;

	// 装备名称
	@Column(name = "equipment_name")
	private String equipmentName;

	// 型号|规格
	@Column(name = "style")
	private String style;

	// 装备说明
	@Column(columnDefinition = "TEXT",name = "equipment_explain")
	private String equipmentExplain;

	// 保管员
	@Column(name = "keeper")
	private String keeper;

	// 入库时间
	@Column(name = "start_time")
	private Date startTime;

	// 报废时间
	@Column(name = "over_time")
	private Date overTime;

	// 状态（1-库存、2-完好、3-借出、4-报废、5-维修检验、6-未入库）
	@Column(name = "status")
	private String status;

	// 二维码
	@Column(name = "qrcode")
	private String qrcode;

	//最后修改时间
	@Column(name = "modify_time")
	private Date modifyTime;

	// 最后修改人
	@Column(name = "modify_name")
	private String modifyName;

	public int getEquipmentId() {
		return equipmentId;
	}


	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}


	public String getEquipmentNo() {
		return equipmentNo;
	}


	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}


	public String getEquipmentName() {
		return equipmentName;
	}


	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}


	public String getStyle() {
		return style;
	}


	public void setStyle(String style) {
		this.style = style;
	}


	public String getEquipmentExplain() {
		return equipmentExplain;
	}


	public void setEquipmentExplain(String equipmentExplain) {
		this.equipmentExplain = equipmentExplain;
	}


	public String getKeeper() {
		return keeper;
	}


	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getOverTime() {
		return overTime;
	}


	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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


	@Override
	public String toString() {
		return com.alibaba.fastjson.JSON.toJSONString(this);
	}

}
