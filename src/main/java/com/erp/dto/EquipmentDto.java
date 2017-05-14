package com.erp.dto;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.erp.util.date.DateUtil;

public class EquipmentDto {
    private int equipmentId;

    // 装备编号
    private String equipmentNo;

    // 装备名称
    private String equipmentName;

    // 型号|规格
    private String style;

    // 装备说明
    private String equipmentExplain;

    // 保管员
    private String keeper;

    // 入库时间
    private String startTime;

    // 报废时间
    private String overTime;

    // 状态（1-库存、2-完好、3-借出、4-报废、5-维修检验、6-未入库）
    private String status;

    // 二维码
    private String qrcode;

    // 最后修改时间
    private String modifyTime;

    // 最后修改人
    private String modifyName;

    // 借出还入使用人
    private String record_use;

    // 借出还入说明
    private String record_explain;

    // 结束时间
    private String endTime;
    
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static void main(String[] args) {
        EquipmentDto equipmentDto = new EquipmentDto();
        equipmentDto.setEquipmentNo("XGSD05");
        equipmentDto.setEquipmentName("消防水枪");
        equipmentDto.setStyle("EROUOIER");
        equipmentDto.setEquipmentExplain("各种瑕疵各种瞎扯");
        equipmentDto.setKeeper("王建观");
        equipmentDto.setStartTime(DateUtil.parse(new Date(), DateUtil.YYYYMMDDHHMMSS));
        equipmentDto.setOverTime(DateUtil.parse(new Date(), DateUtil.YYYYMMDDHHMMSS));
        equipmentDto.setStatus("1");
        equipmentDto.setQrcode("11111.jpg");
        equipmentDto.setModifyTime(DateUtil.parse(new Date(), DateUtil.YYYYMMDDHHMMSS));
        equipmentDto.setModifyName("刘杨");
        System.out.println(equipmentDto);
    }

}
