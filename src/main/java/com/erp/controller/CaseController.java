package com.erp.controller;

import static com.erp.constant.Constant.DEF_PAGE_NUM;
import static com.erp.constant.Constant.DEF_PAGE_NUM_DEFAUL;
import static com.erp.constant.Constant.DEF_PAGE_SIZE;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.CaseDto;
import com.erp.dto.filter.CaseFilterDto;
import com.erp.orm.domain.Case;
import com.erp.response.BaseMessage;
import com.erp.response.CaseResult;
import com.erp.response.DelResult;
import com.erp.response.ListResult;
import com.erp.response.MessageCode;
import com.erp.service.impl.CaseService;
import com.erp.util.date.DateUtil;

@Controller
@RequestMapping("/case")
public class CaseController {
    
    private Logger log = LoggerFactory.getLogger(CaseController.class);
    
    @Autowired
    private CaseService caseService;
    
    /**
     * 添加
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage insert(
            @RequestParam(required = false) String caseNo,
            @RequestParam(required = false) String caseName,
            @RequestParam(required = false) String policeLoginName,
            @RequestParam(required = false) String policeName,
            @RequestParam(required = false) String caseTime,
            @RequestParam(required = false) String caseAddress,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String caseStatus,
            @RequestParam(required = false) String fileStatus,
            @RequestParam(required = false) String caseExplain,
            @RequestParam(required = false) String possessionsNo,
            @RequestParam(required = false) String possessionsName,
            @RequestParam(required = false) String style
            ){
        CaseDto caseDto = new CaseDto();
        caseDto.setCaseNo(caseNo);
        caseDto.setCaseName(caseName);
        caseDto.setPoliceLoginName(policeLoginName);
        caseDto.setPoliceName(policeName);
//      if (null != caseTime) 
//          caseDto.setCaseTime(DateUtil.parse(caseTime, DateUtil.YYYYMMDD));
//      caseDto.setCaseAddress(caseAddress);
//      if (null != startTime)
//          caseDto.setStartTime(DateUtil.parse(startTime, DateUtil.YYYYMMDD));
//      if (null != endTime)
//            caseDto.setEndTime(DateUtil.parse(endTime, DateUtil.YYYYMMDD));
        caseDto.setCaseStatus(caseStatus);
        caseDto.setFileStatus(fileStatus);
        caseDto.setCaseExplain(caseExplain);
        caseDto.setPossessionsNo(possessionsNo);
        caseDto.setCaseTime(caseTime);
        caseDto.setStartTime(startTime);
        caseDto.setEndTime(endTime);
        caseDto.setPossessionsName(possessionsName);
        caseDto.setStyle(style);
        log.info("---------------------------添加案卷传入数据-{}",caseDto.toString());
        return caseService.saveCase(caseDto);
    }
    
    /**
     * 串并案件
     * @param caseDto
     * @return
     */
    @RequestMapping(value = "merge", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage merge(@RequestBody CaseDto caseDto){
        return caseService.merge(caseDto);
    }
    
    
    /**
     * 添加
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage save(@RequestBody CaseDto caseDto){
        log.info("---------------------------添加案卷传入数据-{}",caseDto.toString());
        return caseService.insert(caseDto);
    }
    
    /**
     * 新页面修改案件
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "updateCase", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage updateCase(@RequestBody CaseDto caseDto){
        log.info("---------------------------修改案卷传入数据-{}",caseDto.toString());
        return caseService.update(caseDto);
    }
    
    
    /**
     * 删除
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public DelResult delfixed(@RequestParam(value = "fid[]",required = true) String[] fid,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        DelResult delResult = new DelResult();
        log.info("删除id：{}" + fid[0]);
        BaseMessage message = caseService.delCase(fid);
        if (message.getCode() == 200) {
            delResult.setSuccess(true);
        } else {
            delResult.setSuccess(false);
        }
        return delResult;
    }
    
    /**
     * 修改
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage update(
            @RequestParam(required = false) String caseId,
            @RequestParam(required = false) String caseNo,
            @RequestParam(required = false) String caseName,
            @RequestParam(required = false) String policeLoginName,
            @RequestParam(required = false) String policeName,
            @RequestParam(required = false) String caseTime,
            @RequestParam(required = false) String caseAddress,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String caseStatus,
            @RequestParam(required = false) String fileStatus,
            @RequestParam(required = false) String caseExplain,
            @RequestParam(required = false) String possessionsNo,
            @RequestParam(required = false) String possessionsName,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String record_use,
            @RequestParam(required = false) String record_explain
            ){
        CaseDto caseDto = new CaseDto();
        if (!StringUtils.isEmpty(caseId))
            caseDto.setCaseId(Integer.parseInt(caseId));
        caseDto.setCaseNo(caseNo);
        caseDto.setCaseName(caseName);
        caseDto.setPoliceLoginName(policeLoginName);
        caseDto.setPoliceName(policeName);
//        if (null != caseTime) 
//            caseDto.setCaseTime(DateUtil.parse(caseTime, DateUtil.YYYYMMDD));
//        caseDto.setCaseAddress(caseAddress);
//        if (null != startTime)
//            caseDto.setStartTime(DateUtil.parse(startTime, DateUtil.YYYYMMDD));
//        if (null != endTime)
//            caseDto.setEndTime(DateUtil.parse(endTime, DateUtil.YYYYMMDD));
        caseDto.setCaseStatus(caseStatus);
        caseDto.setFileStatus(fileStatus);
        caseDto.setCaseExplain(caseExplain);
        caseDto.setPossessionsNo(possessionsNo);
        caseDto.setPossessionsName(possessionsName);
        caseDto.setRecord_use(record_use);
        caseDto.setRecord_explain(record_explain);
        caseDto.setCaseTime(caseTime);
        caseDto.setStartTime(startTime);
        caseDto.setEndTime(endTime);
        caseDto.setStyle(style);
        log.info("---------------------------修改案件传入数据-{}",caseDto);
        return caseService.update(caseDto);
    }
    
     /**
     * 用户列表 分页
     */
    @RequestMapping(value = "ListAll", method = RequestMethod.GET)
    @ResponseBody
    public ListResult findUsers(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String caseNo,
            @RequestParam(required = false) String caseName,
            @RequestParam(required = false) String policeName,
            @RequestParam(required = false) String caseTime_start,
            @RequestParam(required = false) String caseTime_end,
            @RequestParam(required = false) String startTime_start,
            @RequestParam(required = false) String startTime_end,
            @RequestParam(required = false) String endTime_start,
            @RequestParam(required = false) String endTime_end,
            @RequestParam(required = false) String caseStatus,
            @RequestParam(required = false) String fileStatus,
            @RequestParam(required = false) String style
            ){
        ListResult listResult = new ListResult();
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        CaseFilterDto caseFilterDto = new CaseFilterDto();
        caseFilterDto.setCaseNo(caseNo);
        caseFilterDto.setCaseName(caseName);
        caseFilterDto.setPoliceName(policeName);
        caseFilterDto.setCaseStatus(caseStatus);
        caseFilterDto.setFileStatus(fileStatus);
        caseFilterDto.setStyle(style);
        if (!StringUtils.isEmpty(caseTime_start))
            caseFilterDto.setCaseTime_start(DateUtil.parse(caseTime_start, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(caseTime_end))
            caseFilterDto.setCaseTime_end(DateUtil.parse(caseTime_end, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(startTime_start))
            caseFilterDto.setStartTime_start(DateUtil.parse(startTime_start, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(startTime_end))
            caseFilterDto.setStartTime_end(DateUtil.parse(startTime_end, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(endTime_start))
            caseFilterDto.setEndTime_start(DateUtil.parse(endTime_start, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(endTime_end))
            caseFilterDto.setEndTime_end(DateUtil.parse(endTime_end, DateUtil.YYYYMMDD));
        Sort sort = new Sort(Sort.Direction.DESC, "caseTime");
        PageRequest pageRequest = new PageRequest(pageIndex, limit,sort);
        BaseMessage baseMessage =  caseService.findCase(caseFilterDto, pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        log.info("-------------------------案件列表入参-{}",caseFilterDto.toString());
        log.info("-------------------------案件列表出参-{}",baseMessage.getData().toString());
        return listResult;
    }
    
    /**
     * 我的案件列表
     */
    @RequestMapping(value = "myCaseList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult myCaseList(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String caseNo,
            @RequestParam(required = false) String caseName,
            @RequestParam(required = false) String caseTime_start,
            @RequestParam(required = false) String caseTime_end,
            @RequestParam(required = false) String startTime_start,
            @RequestParam(required = false) String startTime_end,
            @RequestParam(required = false) String endTime_start,
            @RequestParam(required = false) String endTime_end,
            @RequestParam(required = false) String caseStatus,
            @RequestParam(required = false) String fileStatus,
            @RequestParam(required = false) String style
            ){
        ListResult listResult = new ListResult();
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        CaseFilterDto caseFilterDto = new CaseFilterDto();
        caseFilterDto.setCaseNo(caseNo);
        caseFilterDto.setCaseName(caseName);
        caseFilterDto.setCaseStatus(caseStatus);
        caseFilterDto.setFileStatus(fileStatus);
        caseFilterDto.setStyle(style);
        if (!StringUtils.isEmpty(caseTime_start))
            caseFilterDto.setCaseTime_start(DateUtil.parse(caseTime_start, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(caseTime_end))
            caseFilterDto.setCaseTime_end(DateUtil.parse(caseTime_end, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(startTime_start))
            caseFilterDto.setStartTime_start(DateUtil.parse(startTime_start, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(startTime_end))
            caseFilterDto.setStartTime_end(DateUtil.parse(startTime_end, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(endTime_start))
            caseFilterDto.setEndTime_start(DateUtil.parse(endTime_start, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(endTime_end))
            caseFilterDto.setEndTime_end(DateUtil.parse(endTime_end, DateUtil.YYYYMMDD));
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage baseMessage =  caseService.myCaseList(caseFilterDto, pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        return listResult;
    }
    
    /**
     * 审核案件列表
     */
    @RequestMapping(value = "applyCaseList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult applyCaseList(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String caseNo,
            @RequestParam(required = false) String caseName,
            @RequestParam(required = false) String caseTime_start,
            @RequestParam(required = false) String caseTime_end,
            @RequestParam(required = false) String startTime_start,
            @RequestParam(required = false) String startTime_end,
            @RequestParam(required = false) String endTime_start,
            @RequestParam(required = false) String endTime_end,
            @RequestParam(required = false) String caseStatus,
            @RequestParam(required = false) String fileStatus,
            @RequestParam(required = false) String style
            ){
        ListResult listResult = new ListResult();
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        CaseFilterDto caseFilterDto = new CaseFilterDto();
        caseFilterDto.setCaseNo(caseNo);
        caseFilterDto.setCaseName(caseName);
        caseFilterDto.setCaseStatus(caseStatus);
        caseFilterDto.setFileStatus(fileStatus);
        caseFilterDto.setStyle(style);
        if (!StringUtils.isEmpty(caseTime_start))
            caseFilterDto.setCaseTime_start(DateUtil.parse(caseTime_start, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(caseTime_end))
            caseFilterDto.setCaseTime_end(DateUtil.parse(caseTime_end, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(startTime_start))
            caseFilterDto.setStartTime_start(DateUtil.parse(startTime_start, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(startTime_end))
            caseFilterDto.setStartTime_end(DateUtil.parse(startTime_end, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(endTime_start))
            caseFilterDto.setEndTime_start(DateUtil.parse(endTime_start, DateUtil.YYYYMMDD));
        if (!StringUtils.isEmpty(endTime_end))
            caseFilterDto.setEndTime_end(DateUtil.parse(endTime_end, DateUtil.YYYYMMDD));
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage baseMessage =  caseService.applyCaseList(caseFilterDto, pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        return listResult;
    }
    
    /**
     * 根据id查询
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public CaseResult findById(@RequestParam(required = true) String id) {
        log.info("查询id：{}" + id);
        Integer ids = Integer.parseInt(id.trim());
        return caseService.findByCaseId(ids);

    }
    
    /**
     * 根据id查询案件详情
     */
    @RequestMapping(value = "/findCaseDetailById", method = RequestMethod.GET)
    @ResponseBody
    public CaseResult findCaseDetailById(@RequestParam(required = true) String id) {
        log.info("查询id：{}" + id);
        Integer ids = Integer.parseInt(id.trim());
        return caseService.findCaseDetailById(ids);

    }    
    
    /**
     * 根据案件编号查询案件列表
     */
    @RequestMapping(value = "/findCaseByNo", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage findCaseByNo(@RequestParam(value = "ids[]",required = true) String[] ids) {
        log.info("findCaseByNo查询id：{}" + ids);
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        List<Case> list = new ArrayList<Case>();
        for (int i = 0; i<ids.length; i++) {
            Case cases = caseService.findByCaseNo(ids[i]);
            list.add(cases);
        }
        baseMessage.setData(list);
        log.info("findCaseByNo出参 = {}",baseMessage);
        return baseMessage;
    } 
    
    /**
     * 结案|申请结案
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "endcase", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage endCase(
            @RequestParam(required = false) String caseId,
            @RequestParam(required = false) String caseNo,
            @RequestParam(required = false) String caseAddUrl,
            @RequestParam(required = false) String endcaseExplain
            ){
        CaseDto caseDto = new CaseDto();
        if (!StringUtils.isEmpty(caseId))
            caseDto.setCaseId(Integer.parseInt(caseId));
        caseDto.setCaseAddUrl(caseAddUrl);
        caseDto.setCaseNo(caseNo);
        caseDto.setEndcaseExplain(endcaseExplain);
        log.info("---------------------------结案案件传入数据-{}",caseDto);
        return caseService.endCase(caseDto);
    }
    
    /**
     * 结案审核通过
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "endCaseYes", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage endCaseYes(@RequestParam(required = false) String caseId){
        CaseDto caseDto = new CaseDto();
        if (!StringUtils.isEmpty(caseId))
            caseDto.setCaseId(Integer.parseInt(caseId));
        log.info("---------------------------结案审核通过-{}",caseDto.toString());
        return caseService.endCaseYes(caseDto);
    }
    
    /**
     * 结案审核驳回
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "rejectCase", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage rejectCase(@RequestParam(required = false) String caseId,
            @RequestParam(required = false) String rejectmsg
            ){
        CaseDto caseDto = new CaseDto();
        if (!StringUtils.isEmpty(caseId))
            caseDto.setCaseId(Integer.parseInt(caseId));
        caseDto.setRejectmsg(rejectmsg);
        log.info("---------------------------结案审核通过-{}",caseDto.toString());
        return caseService.rejectCase(caseDto);
    }
    
    
    /*
     * 根据编号查询
     */
    @RequestMapping(value = "/findByNo", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage findByNo(@RequestBody CaseDto caseDto) {
        log.info("查询id：{}" + caseDto.toString());
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        message.setData(caseService.findByCaseNo(caseDto.getCaseNo()).getPoliceLoginName());
        return message;
    }
    
    /*
     * 根据编号查询
     */
    @RequestMapping(value = "/findByNoAndName", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage findByNoAndName(@RequestBody CaseDto caseDto) {
        log.info("查询id：{}" + caseDto.toString());
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        message.setData(caseService.findByCaseNo(caseDto.getCaseNo()));
        return message;
    }
    
    /**
    * 新页面 列表 分页
    */
   @RequestMapping(value = "listCase", method = RequestMethod.GET)
   @ResponseBody
   public ListResult listCase(
           @RequestParam(required = false, defaultValue = DEF_PAGE_NUM_DEFAUL) int pageIndex,
           @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
           @RequestParam(required = false) String caseNo,
           @RequestParam(required = false) String caseName,
           @RequestParam(required = false) String policeName,
           @RequestParam(required = false) String caseTime_start,
           @RequestParam(required = false) String caseTime_end,
           @RequestParam(required = false) String startTime_start,
           @RequestParam(required = false) String startTime_end,
           @RequestParam(required = false) String endTime_start,
           @RequestParam(required = false) String endTime_end,
           @RequestParam(required = false) String caseStatus,
           @RequestParam(required = false) String fileStatus,
           @RequestParam(required = false) String style
           ){
       ListResult listResult = new ListResult();
       pageIndex = pageIndex - 1;
       if (pageIndex < 0) {
           pageIndex = 0;
       }
       CaseFilterDto caseFilterDto = new CaseFilterDto();
       caseFilterDto.setCaseNo(caseNo);
       caseFilterDto.setCaseName(caseName);
       caseFilterDto.setPoliceName(policeName);
       caseFilterDto.setCaseStatus(caseStatus);
       caseFilterDto.setFileStatus(fileStatus);
       caseFilterDto.setStyle(style);
       if (!StringUtils.isEmpty(caseTime_start))
           caseFilterDto.setCaseTime_start(DateUtil.parse(caseTime_start, DateUtil.YYYYMMDD));
       if (!StringUtils.isEmpty(caseTime_end))
           caseFilterDto.setCaseTime_end(DateUtil.parse(caseTime_end, DateUtil.YYYYMMDD));
       if (!StringUtils.isEmpty(startTime_start))
           caseFilterDto.setStartTime_start(DateUtil.parse(startTime_start, DateUtil.YYYYMMDD));
       if (!StringUtils.isEmpty(startTime_end))
           caseFilterDto.setStartTime_end(DateUtil.parse(startTime_end, DateUtil.YYYYMMDD));
       if (!StringUtils.isEmpty(endTime_start))
           caseFilterDto.setEndTime_start(DateUtil.parse(endTime_start, DateUtil.YYYYMMDD));
       if (!StringUtils.isEmpty(endTime_end))
           caseFilterDto.setEndTime_end(DateUtil.parse(endTime_end, DateUtil.YYYYMMDD));
       PageRequest pageRequest = new PageRequest(pageIndex, limit);
       BaseMessage baseMessage =  caseService.findCase(caseFilterDto, pageRequest);
       listResult.setRows(baseMessage.getData());
       listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
       return listResult;
   }
}
