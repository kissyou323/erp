package com.erp.response;

import java.util.List;

import com.erp.dto.CaseDto;

/**
 * 案件查询响应类
 * 
 * @author 王建观
 * @date 2016年7月22日下午2:21:45 描述：
 */
public class CaseResult extends Message {

    /**
     * 响应案件信息
     */
    private CaseDto caseDto;

    /**
     * 响应案件列表
     */
    private List<CaseDto> list;


    public CaseDto getCaseDto() {
        return caseDto;
    }

    public void setCaseDto(CaseDto caseDto) {
        this.caseDto = caseDto;
    }

    public List<CaseDto> getList() {
        return list;
    }

    public void setList(List<CaseDto> list) {
        this.list = list;
    }

    public CaseResult(MessageCode messageCode) {
        this.code = messageCode.getCode();
        this.msg = messageCode.getMsg();
    }
    @Override
    public String toString() {
        return "CaseResult [caseDto=" + caseDto + ", list=" + list + ", code="
                + code + "]";
    }

}
