package com.erp.dto;

import com.alibaba.fastjson.JSON;

public class PossessionsDto {

    private Integer possessionsId;

    private String possessionsNo;

    private String possessionsName;

    private String style;

    private String username;

    private String startTime;

    private String keeper;

    private String possessionsExplain;

    private String status;
    
    private String possType;

    private String modifyTime;

    private String modifyName;

    private String holderName;

    private String holderTime;

    private String possessionsPic;

    // 借出还入使用人
    private String record_use;

    // 借出还入说明
    private String record_explain;

    private String endTime;

    public Integer getPossessionsId() {
        return possessionsId;
    }

    public void setPossessionsId(Integer possessionsId) {
        this.possessionsId = possessionsId;
    }

    public String getPossessionsNo() {
        return possessionsNo;
    }

    
    public String getPossType() {
        return possType;
    }

    public void setPossType(String possType) {
        this.possType = possType;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
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

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
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

    public String getHolderTime() {
        return holderTime;
    }

    public void setHolderTime(String holderTime) {
        this.holderTime = holderTime;
    }

    public String getPossessionsPic() {
        return possessionsPic;
    }

    public void setPossessionsPic(String possessionsPic) {
        this.possessionsPic = possessionsPic;
    }

    public String getRecord_use() {
        return record_use;
    }

    public void setRecord_use(String record_use) {
        this.record_use = record_use;
    }

    public String getRecord_explain() {
        return record_explain;
    }

    public void setRecord_explain(String record_explain) {
        this.record_explain = record_explain;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
