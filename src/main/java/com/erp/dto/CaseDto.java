package com.erp.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.erp.util.date.DateUtil;

public class CaseDto {

    /**
     * id
     */
    private int caseId;

    /**
     * 案卷编号
     */
    private String caseNo;

    /**
     * 案卷名字
     */
    private String caseName;

    private String policeLoginName;

    /**
     * 办案民警
     */
    private String policeName;

    /*
     * 案发时间
     */
    private String caseTime;

    /**
     * 案发地点
     */
    private String caseAddress;

    /**
     * 入库时间
     */
    private String startTime;

    /**
     * 结案时间
     */
    private String endTime;

    /**
     * 案卷状态 案件状态（1-未结案、2-已结案3案件撤销4案件合并）
     */
    private String caseStatus;

    /**
     * 档案状态(1-还入2-建档5-归档3借出4其他6-移交)
     */
    private String fileStatus;

    /**
     * 二维码图片url
     */
    private String qrcode;

    /**
     * 最后修改时间
     */
    private String modifyTime;

    /**
     * 最后修改人
     */
    private String modifyName;

    /**
     * 简要案情
     */
    private String caseExplain;

    /**
     * 涉案财物id
     */
    private String possessionsNo;

    /**
     * 涉案财物名称
     */
    private String possessionsName;

    /**
     * 案件类型（行政、刑事）
     */
    private String style;

    /**
     * 借出还入使用人
     */
    private String record_use;

    /**
     * 借出还入说明
     */
    private String record_explain;

    /**
     * 结案时上传登记的图片
     */
    private String caseAddUrl;

    /**
     * 结案时的描述
     */
    private String endcaseExplain;
    
    /**
     * 结案状态1-未结案2-结案审核中3-已结案  4-已驳回
     */
    private String endcaseStatus;
    
    /**
     * 申请结案的驳回意见
     */
    private String rejectmsg;
    
    /**
     * 父案件编号
     */
    private String parentCaseno;
    
    /**
     * 是否是子案件1是2否
     */
    private String sonStatus;
    
    /**
     * 是否已经督案0未督案1已经督案
     */
    private String isCaseList;
    
    
    //----------------------------------------------添加犯罪嫌疑人--------------------------------------------------------
    private List<SuspectDto> suspectDtoList;
    //----------------------------------------------添加犯罪嫌疑人--------------------------------------------------------
    

    //----------------------------------------------串并--------------------------------------------------------
    private String[] caseNos;
    //----------------------------------------------串并--------------------------------------------------------
    
    
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

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getCaseTime() {
        return caseTime;
    }

    public void setCaseTime(String caseTime) {
        this.caseTime = caseTime;
    }

    public String getCaseAddress() {
        return caseAddress;
    }

    public void setCaseAddress(String caseAddress) {
        this.caseAddress = caseAddress;
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

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
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

    public String getCaseExplain() {
        return caseExplain;
    }

    public void setCaseExplain(String caseExplain) {
        this.caseExplain = caseExplain;
    }

    public String getPoliceLoginName() {
        return policeLoginName;
    }

    public void setPoliceLoginName(String policeLoginName) {
        this.policeLoginName = policeLoginName;
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

    public String getCaseAddUrl() {
        return caseAddUrl;
    }

    public void setCaseAddUrl(String caseAddUrl) {
        this.caseAddUrl = caseAddUrl;
    }

    public String getEndcaseExplain() {
        return endcaseExplain;
    }

    public void setEndcaseExplain(String endcaseExplain) {
        this.endcaseExplain = endcaseExplain;
    }
    
    public String getEndcaseStatus() {
        return endcaseStatus;
    }

    public void setEndcaseStatus(String endcaseStatus) {
        this.endcaseStatus = endcaseStatus;
    }

    public String getRejectmsg() {
        return rejectmsg;
    }

    public void setRejectmsg(String rejectmsg) {
        this.rejectmsg = rejectmsg;
    }

    public String getParentCaseno() {
        return parentCaseno;
    }

    public void setParentCaseno(String parentCaseno) {
        this.parentCaseno = parentCaseno;
    }

    public String getSonStatus() {
        return sonStatus;
    }

    public void setSonStatus(String sonStatus) {
        this.sonStatus = sonStatus;
    }
    
    public List<SuspectDto> getSuspectDtoList() {
        return suspectDtoList;
    }

    public void setSuspectDtoList(List<SuspectDto> suspectDtoList) {
        this.suspectDtoList = suspectDtoList;
    }

    public String[] getCaseNos() {
        return caseNos;
    }

    public void setCaseNos(String[] caseNos) {
        this.caseNos = caseNos;
    }
    
    

    public String getIsCaseList() {
        return isCaseList;
    }

    public void setIsCaseList(String isCaseList) {
        this.isCaseList = isCaseList;
    }
    
    

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
    
    public static void main(String[] args) {
        CaseDto caseDto = new CaseDto();
        caseDto.setCaseNo("JKLOIUJ");
        caseDto.setCaseName("强奸案");
//        caseDto.setPoliceLoginName("admin");
//        caseDto.setPoliceName("wjg");
//        caseDto.setCaseTime(new Date());
//        caseDto.setCaseAddress("沙特阿拉伯");
//        caseDto.setStartTime(new Date());
        caseDto.setEndTime(DateUtil.parse(new Date(), DateUtil.YYYYMMDDHHMMSS));
        caseDto.setCaseStatus("4");
        caseDto.setFileStatus("1");
        caseDto.setQrcode("111.jpg");
        caseDto.setStyle("2");
        
        List<SuspectDto> suspectDtoList = new ArrayList<SuspectDto>();
        
        for (int i = 0; i < 10; i ++){
            
            SuspectDto  s1 = new  SuspectDto();
            s1.setCaseNo("JKLOIUJ");
            s1.setIdentity("341021199401198057");
            s1.setMobileNo("15381002812");
            s1.setRemarks("222222222222");
            s1.setSuspectName("王建观");
            
            SuspectDto  s2 = new  SuspectDto();
            s2.setCaseNo("JKLOIUJ");
            s2.setIdentity("341021199302209769");
            s2.setMobileNo("15381002801");
            s2.setRemarks("222222222222");
            s2.setSuspectName("肖伟丽");
            
            suspectDtoList.add(s1);
            suspectDtoList.add(s2);
        }
//        caseDto.setSuspectDtoList(suspectDtoList);
        String a[] = new String[]{"A125353625","1221221","JKLOIUJ"};
        caseDto.setCaseNos(a);
        System.out.println(caseDto.toString());
    }

}
