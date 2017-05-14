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

/**
 * 
 * @author 刘杨
 *
 */
/**
 * @author webstorm 固定资产表
 *
 */
@Entity
@Table(name = "t_fixedassets")
public class Fixedassets implements Serializable {

    private static final long serialVersionUID = 8098061493953225588L;

    // id
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "assets_id", unique = true, nullable = false)
    private Integer assetsId;

    // 资产编号
    @Column(name = "assets_no")
    private String assetsNo;

    // 资产名称
    @Column(name = "assets_name")
    private String assetsName;

    // style
    @Column(name = "style")
    private String style;

    // 资产说明
    @Column(columnDefinition = "TEXT",name = "assets_explain")
    private String assetsExplain;

    // 保管员
    @Column(name = "keeper")
    private String keeper;

    // 入库时间
    @Column(name = "start_time")
    private Date startTime;
    
    @Column(name = "use_department")
    private String useDepartment;
    
    @Column(name = "use_person")
    private String use_person;

    // 最后修改时间
    @Column(name = "modify_time")
    private Date modifyTime;

    // 最后修改人
    @Column(name = "modify_name")
    private String modifyName;

    // 二维码地址
    @Column(name = "qrcode")
    private String qrcode;

    // 状态
    @Column(name = "status")
    private Integer status;

    public Integer getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(Integer assetsId) {
        this.assetsId = assetsId;
    }

    public String getAssetsNo() {
        return assetsNo;
    }

    public void setAssetsNo(String assetsNo) {
        this.assetsNo = assetsNo;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getAssetsExplain() {
        return assetsExplain;
    }

    public void setAssetsExplain(String assetsExplain) {
        this.assetsExplain = assetsExplain;
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

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getUseDepartment() {
        return useDepartment;
    }

    public void setUseDepartment(String useDepartment) {
        this.useDepartment = useDepartment;
    }

    public String getUse_person() {
        return use_person;
    }

    public void setUse_person(String use_person) {
        this.use_person = use_person;
    }

    @Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
