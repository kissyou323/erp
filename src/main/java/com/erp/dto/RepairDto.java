package com.erp.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public class RepairDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer repair_id;

	private String repair_name;

	private String tel;

	private String repair_explain;

	private String repair_time;

	private String repair_status;

	private Integer assets_equipment_id;
	
	private String assets_equipment_name;

	private String style;
	
	private String startTime;

    private String endTime;
    
    private String assets_equipment_no;

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

	public String getRepair_time() {
		return repair_time;
	}

	public void setRepair_time(String repair_time) {
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

	public String getAssets_equipment_name() {
		return assets_equipment_name;
	}

	public void setAssets_equipment_name(String assets_equipment_name) {
		this.assets_equipment_name = assets_equipment_name;
	}

	public void setStyle(String style) {
		this.style = style;
	}

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    
    public String getAssets_equipment_no() {
        return assets_equipment_no;
    }

    public void setAssets_equipment_no(String assets_equipment_no) {
        this.assets_equipment_no = assets_equipment_no;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
	

}
