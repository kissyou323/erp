package com.erp.controller;

import static com.erp.constant.Constant.DEF_PAGE_NUM;
import static com.erp.constant.Constant.DEF_PAGE_SIZE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.CaseListDto;
import com.erp.response.BaseMessage;
import com.erp.response.ListResult;
import com.erp.response.MessageCode;
import com.erp.service.impl.CaseListService;
@Controller
@RequestMapping("/caselist")
public class CaseListController {
    private Logger log = LoggerFactory.getLogger(CaseListController.class);

    @Autowired
    private CaseListService caseListService;

    /**
     * 查询列表
     * @param userLogin
     * @param type
     * @return
     */
    @RequestMapping(value = "ListAll", method = RequestMethod.GET)
    @ResponseBody
    public ListResult findMsgByUserLoginOrType(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String caselistNo,
            @RequestParam(required = false) String caseNo) {
        
        ListResult listResult = new ListResult();
        CaseListDto caseListDto = new CaseListDto();
        caseListDto.setCaselistNo(caselistNo);
        caseListDto.setCaseNo(caseNo);
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage message = caseListService.findCaseList(caseListDto,pageRequest);
        listResult.setRows(message.getData());
        listResult.setResults(Integer.parseInt(message.getMsg()));
        return listResult;
    }
    
    /**
     * 查询列表
     * @param userLogin
     * @param type
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ListResult list(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String caselistNo,
            @RequestParam(required = false) String caseNo) {
        
        ListResult listResult = new ListResult();
        CaseListDto caseListDto = new CaseListDto();
        caseListDto.setCaselistNo(caselistNo);
        caseListDto.setCaseNo(caseNo);
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage message = caseListService.list(caseListDto,pageRequest);
        listResult.setRows(message.getData());
        listResult.setResults(Integer.parseInt(message.getMsg()));
        return listResult;
    }
    
    /**
     * 民警,我的督案单列表
     * @param userLogin
     * @param type
     * @return
     */
    @RequestMapping(value = "myCaseList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult myCaseList(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String caselistNo,
            @RequestParam(required = false) String caseNo) {
        
        ListResult listResult = new ListResult();
        CaseListDto caseListDto = new CaseListDto();
        caseListDto.setCaselistNo(caselistNo);
        caseListDto.setCaseNo(caseNo);
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage message = caseListService.findCaseListByUserLogin(caseListDto,pageRequest);
        listResult.setRows(message.getData());
        listResult.setResults(Integer.parseInt(message.getMsg()));
        return listResult;
    }
    
    /**
     * 查询单条
     * @param id
     * @return
     */
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage findById(
            @RequestParam(required = true) String id) {
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        Integer ids = Integer.parseInt(id.trim());
        CaseListDto caseListDto = caseListService.findById(ids);
        message.setData(caseListDto);
        return message;
    }
    /**
     * 添加
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage insert(
            @RequestParam(required = false) String caselistNo,
            @RequestParam(required = false) String caseNo,
            @RequestParam(required = false) String policeName,
            @RequestParam(required = false) String caseendTime,
            @RequestParam(required = false) String suggest
            ){
        
        CaseListDto caseListDto = new CaseListDto();
        caseListDto.setCaselistNo(caselistNo);
        caseListDto.setCaseNo(caseNo);
        caseListDto.setPoliceName(policeName);
//      if (!StringUtils.isEmpty(caseendTime)) {
//          caseListDto.setCaseendTime(DateUtil.parse(caseendTime, DateUtil.YYYYMMDD));
//      }
        caseListDto.setCaseendTime(caseendTime);
        caseListDto.setSuggest(suggest);
        log.info("---------------------------添加督案单传入数据-{}",caseListDto);
        return caseListService.save(caseListDto);
    }
    
    /**  
     * 添加督案单
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage save(@RequestBody  CaseListDto caseListDto ){
        log.info("---------------------------添加督案单传入数据-{}",caseListDto.toString());
        return caseListService.save(caseListDto);
    }
    
    /** 
     * 添加督案单消息
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "saveCaseListMsg", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage saveCaseListMsg(@RequestBody  CaseListDto caseListDto ){
        log.info("---------------------------添加督案单传入数据-{}",caseListDto.toString());
        return caseListService.saveCaseListMsg(caseListDto);
    }
    
    /**
     * 审核督案单消息通过
     * @param id
     * @return
     */
//    apllyYes
    @RequestMapping(value = "apllyMsgYes", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage apllyMsgYes(@RequestParam(required = false) String id) {
        return caseListService.apllyMsgYes(id);
    }
    
    
    /**  
     * 修改
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage update(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String caselistNo,
            @RequestParam(required = false) String caseNo,
            @RequestParam(required = false) String policeName,
            @RequestParam(required = false) String caseendTime,
            @RequestParam(required = false) String suggest
            ){
        CaseListDto caseListDto = new CaseListDto();
        if (!StringUtils.isEmpty(id)) {
            caseListDto.setId(Integer.parseInt(id));
        }
        caseListDto.setCaselistNo(caselistNo);
        caseListDto.setCaseNo(caseNo);
        caseListDto.setPoliceName(policeName);
//        if (!StringUtils.isEmpty(caseendTime)) {
//            caseListDto.setCaseendTime(DateUtil.parse(caseendTime, DateUtil.YYYYMMDD));
//        }
        caseListDto.setCaseendTime(caseendTime);
        caseListDto.setSuggest(suggest);
        log.info("---------------------------修改督案单传入数据-{}",caseListDto);
        return caseListService.update(caseListDto);
    }
    
    /**
     * 审核督案单通过
     * @param id
     * @return
     */
//    apllyYes
    @RequestMapping(value = "applyOk", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage applyOk(@RequestParam(required = false) String id) {
        return caseListService.apllyYes(id);
    }
    
    /**
     * 审核督案单驳回
     * @param id
     * @return
     */
    @RequestMapping(value = "applyNo", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage applyNo(@RequestParam(required = false) String id) {
        return caseListService.apllyNo(id);
    }
    
    /**
     * 
    /**
     * 民警已完成
     * @param id
     * @return
     */
    @RequestMapping(value = "caselistOk", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage caselistOk(@RequestParam(required = false) String id) {
        return caseListService.caselistOk(id);
    }
}
