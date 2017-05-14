package com.erp.controller;

import static com.erp.constant.Constant.DEF_PAGE_NUM;
import static com.erp.constant.Constant.DEF_PAGE_NUM_DEFAUL;
import static com.erp.constant.Constant.DEF_PAGE_SIZE;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.PossessionsDto;
import com.erp.response.BaseMessage;
import com.erp.response.DelResult;
import com.erp.response.ListResult;
import com.erp.response.MessageCode;
import com.erp.service.impl.PossessionsService;

@Controller
@RequestMapping("/possessions")
public class PossessionsController {
    
    private Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private PossessionsService possessionsService;
    
    /**
     * 添加
     */
    @RequestMapping(value = "save", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage save(
            @RequestParam(required = false) String possessionsNo,
            @RequestParam(required = false) String possessionsName,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String keeper,
            @RequestParam(required = false) String possessionsExplain,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String possessionsPic
            ) {
        
        PossessionsDto possessionsDto = new PossessionsDto();
        possessionsDto.setPossessionsNo(possessionsNo);
        possessionsDto.setPossessionsName(possessionsName);
        possessionsDto.setStyle(style);
        possessionsDto.setUsername(username);
        possessionsDto.setKeeper(keeper);
        possessionsDto.setPossessionsExplain(possessionsExplain);
        possessionsDto.setStatus(status);
        possessionsDto.setPossessionsPic(possessionsPic);
        
        if (!StringUtils.isEmpty(startTime)) {
            possessionsDto.setStartTime(startTime);
        }
        
        LOGGER.info("请求参数" + possessionsDto.toString());
        return possessionsService.save(possessionsDto);
    }
    
    /**
     * 搜索加列表
     */
    @RequestMapping(value = "searchList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult recordList(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String possessionsNo,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String possessionsName) {
        ListResult listResult = new ListResult();
        LOGGER.info("----------------recordList-----pageIndex:"+pageIndex+"------limit:"+limit+"---possessionsNo:"+possessionsNo);
        if (pageIndex < 0) {
            pageIndex = 0;
        }
//        Date start_Time = null;
//        Date end_Time = null;
//        if (null != startTime) {
//            start_Time = DateUtil.parse(startTime, DateUtil.YYYYMMDD);
//        }
//        if (null != endTime) {
//            end_Time = DateUtil.parse(endTime, DateUtil.YYYYMMDD);
//        }
        PossessionsDto possessionsDto = new PossessionsDto();
        possessionsDto.setPossessionsNo(possessionsNo);
        possessionsDto.setPossessionsName(possessionsName);
        possessionsDto.setStatus(status);
        possessionsDto.setStartTime(startTime);
        possessionsDto.setEndTime(endTime);
        Sort sort = new Sort(Sort.Direction.DESC, "startTime");
        PageRequest pageRequest = new PageRequest(pageIndex, limit,sort);
        BaseMessage baseMessage = possessionsService.findPossessions(possessionsDto,pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        return listResult;
    }
    
    /**
     * 根据id删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public DelResult delfixed(@RequestParam(value = "fid[]",required = true) String[] fid,HttpServletResponse response) {
        LOGGER.info("---------------------------删除传入数据-{}" + fid);
         DelResult delResult = new DelResult();
         BaseMessage message = possessionsService.delete(fid);
         if (message.getCode() == 200) {
             delResult.setSuccess(true);
         } else {
             delResult.setSuccess(false);
         }
        return delResult;
    }
    
    /**
     * 修改
     */
    @RequestMapping(value = "update", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage updatefixed(
            @RequestParam(required = false) String possessionsId,
            @RequestParam(required = false) String possessionsNo,
            @RequestParam(required = false) String possessionsName,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String keeper,
            @RequestParam(required = false) String possessionsExplain,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String holderName,
            @RequestParam(required = false) String holderTime,
            @RequestParam(required = false) String record_use,
            @RequestParam(required = false) String record_explain,
            @RequestParam(required = false) String possessionsPic) {
        PossessionsDto possessionsDto = new PossessionsDto();
        possessionsDto.setPossessionsNo(possessionsNo);
        possessionsDto.setPossessionsName(possessionsName);
        possessionsDto.setStyle(style);
        possessionsDto.setUsername(username);
        possessionsDto.setKeeper(keeper);
        possessionsDto.setPossessionsExplain(possessionsExplain);
        possessionsDto.setStatus(status);
        possessionsDto.setHolderName(holderName);
        possessionsDto.setPossessionsPic(possessionsPic);
        possessionsDto.setRecord_explain(record_explain);
        possessionsDto.setRecord_use(record_use);
        if (!StringUtils.isEmpty(possessionsId)) {
            possessionsDto.setPossessionsId(Integer.parseInt(possessionsId));
        }
        
        if (!StringUtils.isEmpty(startTime)) {
            possessionsDto.setStartTime(startTime);
        }
        
        if (!StringUtils.isEmpty(holderTime)) {
            possessionsDto.setHolderTime(holderTime);
        }
        LOGGER.info("请求参数修改" + possessionsDto.toString());
        return possessionsService.update(possessionsDto);
    }
    
    /**
     * 根据id查询
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage findById(@RequestParam(required = true) String id) {
        LOGGER.info("查询id：{}" + id);
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        Integer ids = Integer.parseInt(id.trim());
        PossessionsDto possessionsDto = possessionsService.findById(ids);
        if (!StringUtils.isEmpty(possessionsDto)) {
            message.setData(possessionsDto);
        } else {
            message = new BaseMessage(MessageCode.NO_RESPONSE);
        }
        LOGGER.info("出参查询id：{}" , message.toString());
        return message;
    }
    
    /*
     * 根据编号固定资产查询
     */
    @RequestMapping(value = "/findByNo", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage findByNo(@RequestBody PossessionsDto possessionsDto) {
        LOGGER.info("查询参数：{}" + possessionsDto.toString());
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        message.setData(possessionsService.findByRepairNo(possessionsDto.getPossessionsNo().trim()));
        return message;

    }
    
    //---------------------------------------------------------------------------------------
    
    
    /**
     * 添加
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage insert(@RequestBody PossessionsDto possessionsDto) {
        LOGGER.info("请求参数" + possessionsDto.toString());
        return possessionsService.save(possessionsDto);
    }
    
    /**
     * 修改
     */
    @RequestMapping(value = "updateposs", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage updateposs(@RequestBody PossessionsDto possessionsDto) {
        LOGGER.info("修改请求参数" + possessionsDto.toString());
        return possessionsService.update(possessionsDto);
    }
    
    /**
     * 搜索加列表
     */
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public ListResult listAll(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM_DEFAUL) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String possessionsNo,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String possessionsName) {
        ListResult listResult = new ListResult();
        LOGGER.info("----------------recordList-----pageIndex:"+pageIndex+"------limit:"+limit+"---possessionsNo:"+possessionsNo);
        pageIndex = pageIndex - 1;
        if (pageIndex < 0) {
            pageIndex = 0;
        }
//        Date start_Time = null;
//        Date end_Time = null;
//        if (null != startTime) {
//            start_Time = DateUtil.parse(startTime, DateUtil.YYYYMMDD);
//        }
//        if (null != endTime) {
//            end_Time = DateUtil.parse(endTime, DateUtil.YYYYMMDD);
//        }
        PossessionsDto possessionsDto = new PossessionsDto();
        possessionsDto.setPossessionsNo(possessionsNo);
        possessionsDto.setPossessionsName(possessionsName);
        possessionsDto.setStatus(status);
        possessionsDto.setStartTime(startTime);
        possessionsDto.setEndTime(endTime);
        Sort sort = new Sort(Sort.Direction.DESC, "startTime");
        PageRequest pageRequest = new PageRequest(pageIndex, limit,sort);
        BaseMessage baseMessage = possessionsService.findPossessions(possessionsDto,pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        return listResult;
    }
    
}
