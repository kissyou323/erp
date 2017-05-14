package com.erp.dto;

import com.alibaba.fastjson.JSON;

public class FixedassetsDto {
    private Integer assetsId;

    // 资产编号
    private String assetsNo;

    // 资产名称
    private String assetsName;

    // style
    private String style;

    // 资产说明
    private String assetsExplain;

    // 保管员
    private String keeper;

    // 入库时间
    private String startTime;

    // 最后修改时间
    private String modifyTime;

    // 最后修改人
    private String modifyName;

    // 二维码地址
    private String qrcode;

    // 状态
    private String status;
    //使用部门
    private String useDepartment;
    //使用人
    private String use_person;

    // 借出还入使用人
    private String record_use;

    // 借出还入说明
    private String record_explain;

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

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public String getQrcode() {
        return qrcode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
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

    public static void main(String[] args) {
        FixedassetsDto fixedassetsDto = new FixedassetsDto();
        fixedassetsDto.setAssetsNo("sdssd");
        fixedassetsDto.setAssetsName("灭火器1");
        fixedassetsDto.setStyle("REJREO");
        fixedassetsDto.setAssetsExplain("扯淡扯扯澈澈");
        fixedassetsDto.setKeeper("肖伟丽");
        fixedassetsDto.setStartTime("2015-03-03 12:09:09");
        fixedassetsDto.setModifyName("刘杨");
        fixedassetsDto.setQrcode("REJREO.jpg");
        fixedassetsDto.setStatus("1");
        fixedassetsDto.setUse_person("王建观");
        fixedassetsDto.setUseDepartment("事业一部");
        System.out.println(fixedassetsDto.toString());
    }

}
