package com.erp.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSON;
@Entity
@Table(name = "t_repair")
public class Repair {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "repair_id", unique = true, nullable = false)
	private Integer repair_id;

	@Column(name = "repair_name")
	private String repair_name;

	@Column(name = "tel")
	private String tel;

	@Column(columnDefinition = "TEXT",name = "repair_explain")
	private String repair_explain;

	@Column(name = "repair_time")
	private Date repair_time;

	/**
	 * 维修结果状态（5-维修中\1-已维修\4-已报废）
	 */
	@Column(name = "repair_status")
	private String repair_status;

	@Column(name = "assets_equipment_name")
	private String assets_equipment_name;
	
	@Column(name = "assets_equipment_id")
	private Integer assets_equipment_id;
	
	@Column(name = "assets_equipment_no")
	private String assets_equipment_no;
	
	@Column(name = "style")
	private String style;

	public Integer getRepair_id() {
		return repair_id;
	}

	public void setRepair_id(Integer repair_id) {
		this.repair_id = repair_id;
	}

	public String getRepair_name() {
		return repair_name;
	}

	public void setRepair_name(String repair_name) {
		this.repair_name = repair_name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRepair_explain() {
		return repair_explain;
	}

	public void setRepair_explain(String repair_explain) {
		this.repair_explain = repair_explain;
	}

	public Date getRepair_time() {
		return repair_time;
	}

	public void setRepair_time(Date repair_time) {
		this.repair_time = repair_time;
	}

	public String getRepair_status() {
		return repair_status;
	}

	public void setRepair_status(String repair_status) {
		this.repair_status = repair_status;
	}

	public Integer getAssets_equipment_id() {
		return assets_equipment_id;
	}

	public void setAssets_equipment_id(Integer assets_equipment_id) {
		this.assets_equipment_id = assets_equipment_id;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getAssets_equipment_name() {
		return assets_equipment_name;
	}

	public void setAssets_equipment_name(String assets_equipment_name) {
		this.assets_equipment_name = assets_equipment_name;
	}

	
	public String getAssets_equipment_no() {
        return assets_equipment_no;
    }

    public void setAssets_equipment_no(String assets_equipment_no) {
        this.assets_equipment_no = assets_equipment_no;
    }

    @Override
	public String toString() {
		return JSON.toJSONString(this) ;
	}
	
	
}
