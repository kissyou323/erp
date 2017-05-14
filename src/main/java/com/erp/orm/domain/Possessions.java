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
@Table(name = "t_possessions")
public class Possessions implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "possessions_id", unique = true, nullable = false)
    private Integer possessionsId;

    @Column(name = "possessions_no")
    private String possessionsNo;

    @Column(name = "possessions_name")
    private String possessionsName;

    @Column(name = "style")
    private String style;

    @Column(name = "username")
    private String username;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "keeper")
    private String keeper;

    @Column(columnDefinition = "TEXT", name = "possessions_explain")
    private String possessionsExplain;

    @Column(name = "status")
    private String status;

    @Column(name = "qrcode")
    private String qrcode;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "modify_name")
    private String modifyName;

    @Column(name = "holder_name")
    private String holderName;

    @Column(name = "holder_time")
    private Date holderTime;

    @Column(name = "possessions_pic")
    private String possessionsPic;
    
    @Column(name = "possType")
    private String possType;

    public Integer getPossessionsId() {
        return possessionsId;
    }

    public void setPossessionsId(Integer possessionsId) {
        this.possessionsId = possessionsId;
    }

    public String getPossessionsNo() {
        return possessionsNo;
    }

    public void setPossessionsNo(String possessionsNo) {
        this.possessionsNo = possessionsNo;
    }

    public String getPossessionsName() {
        return possessionsName;
    }

    public void setPossessionsName(String possessionsName) {
        this.possessionsName = possessionsName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getKeeper() {
        return keeper;
    }

    public void setKeeper(String keeper) {
        this.keeper = keeper;
    }

    public String getPossessionsExplain() {
        return possessionsExplain;
    }

    public void setPossessionsExplain(String possessionsExplain) {
        this.possessionsExplain = possessionsExplain;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public Date getHolderTime() {
        return holderTime;
    }

    public void setHolderTime(Date holderTime) {
        this.holderTime = holderTime;
    }

    public String getPossessionsPic() {
        return possessionsPic;
    }

    public void setPossessionsPic(String possessionsPic) {
        this.possessionsPic = possessionsPic;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
    

    public String getPossType() {
        return possType;
    }

    public void setPossType(String possType) {
        this.possType = possType;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
