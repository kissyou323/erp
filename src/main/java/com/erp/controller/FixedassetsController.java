package com.erp.controller;

import static com.erp.constant.Constant.DEF_PAGE_NUM;
import static com.erp.constant.Constant.DEF_PAGE_NUM_DEFAUL;
import static com.erp.constant.Constant.DEF_PAGE_SIZE;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
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

import com.erp.dto.FixedassetsDto;
import com.erp.dto.filter.FixedassetsFilterDto;
import com.erp.response.BaseMessage;
import com.erp.response.DelResult;
import com.erp.response.ListResult;
import com.erp.response.MessageCode;
import com.erp.service.impl.FixedassetsService;
import com.erp.util.date.DateUtil;

/**
 * 
 * @author liuyang 固定资产
 *
 */
@Controller
@RequestMapping("fixedassets")
public class FixedassetsController {

    @Autowired
    private FixedassetsService fixedassetsService;
    private Logger LOGGER = LoggerFactory.getLogger(FixedassetsController.class);

    /**
     * 固定资产添加
     */
    @RequestMapping(value = "savefixed", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage saveFixed(
            @RequestParam(required = false) String assetsNo,
            @RequestParam(required = false) String assetsName,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String keeper,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String assetsExplain,
            HttpServletRequest req,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        FixedassetsDto fixedassetsDto = new FixedassetsDto();
        fixedassetsDto.setAssetsNo(assetsNo);
        fixedassetsDto.setAssetsName(assetsName);
        fixedassetsDto.setStyle(style);
        fixedassetsDto.setKeeper(keeper);
        fixedassetsDto.setStartTime(startTime);
        fixedassetsDto.setStatus(status);
        fixedassetsDto.setAssetsExplain(assetsExplain);
        LOGGER.info("请求参数=" + fixedassetsDto.toString());
        return fixedassetsService.saveFixed(fixedassetsDto);
    }
    
    /**
     * 固定资产添加
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage save(@RequestBody FixedassetsDto fixedassetsDto,
            HttpServletRequest req,HttpServletResponse response) {
        LOGGER.info("请求参数=" + fixedassetsDto.toString());
        return fixedassetsService.saveFixed(fixedassetsDto);
    }
    
    /**
     * 固定资产修改
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage updatefixed(@RequestBody FixedassetsDto fixedassetsDto,
            HttpServletRequest req,HttpServletResponse response) {
        LOGGER.info("请求参数修改" + fixedassetsDto.toString());
        return fixedassetsService.updatefixed(fixedassetsDto);
    }
    

    /**
     * 固定资产修改
     */
    @RequestMapping(value = "updatefixed", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage updatefixed(
            @RequestParam(required = false) String assetsId,
            @RequestParam(required = false) String assetsNo,
            @RequestParam(required = false) String assetsName,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String keeper,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String assetsExplain,
            @RequestParam(required = false) String record_use,
            @RequestParam(required = false) String record_explain,
            @RequestParam(required = false) String useDepartment,
            @RequestParam(required = false) String use_person,
            HttpServletRequest req,HttpServletResponse response) {
        FixedassetsDto fixedassetsDto = new FixedassetsDto();
        fixedassetsDto.setAssetsId(Integer.parseInt(assetsId));
        fixedassetsDto.setAssetsNo(assetsNo);
        fixedassetsDto.setAssetsName(assetsName);
        fixedassetsDto.setStyle(style);
        fixedassetsDto.setKeeper(keeper);
        fixedassetsDto.setStartTime(startTime);
        fixedassetsDto.setStatus(status);
        fixedassetsDto.setAssetsExplain(assetsExplain);
        fixedassetsDto.setRecord_use(record_use);
        fixedassetsDto.setRecord_explain(record_explain);
        fixedassetsDto.setUseDepartment(useDepartment);
        fixedassetsDto.setUse_person(use_person);
        LOGGER.info("请求参数修改" + fixedassetsDto.toString());
        return fixedassetsService.updatefixed(fixedassetsDto);
    }

    /**
     * 搜索加列表 根据固定资产编号、固定资产名称进行模糊查询 根据入库时间进行精确查询
     * 
     */
    @RequestMapping(value = "fixedassetList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult fixedassetList(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String assetsNo,
            @RequestParam(required = false) String assetsName,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String useDepartment,
            @RequestParam(required = false) String usePerson,
            HttpServletResponse response) {
        LOGGER.info("----------------fixedassetList-----pageIndex:" + pageIndex + "------limit:" + limit + "---startTime:" + startTime);
        response.setHeader("Access-Control-Allow-Origin", "*");
        ListResult listResult = new ListResult();
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        Date start_Time = null;
        Date end_Time = null;
        if (null != startTime) {
            start_Time = DateUtil.parse(startTime, DateUtil.YYYYMMDD);
        }
        if (null != endTime) {
            end_Time = DateUtil.parse(endTime, DateUtil.YYYYMMDD);
        }
        FixedassetsFilterDto filterDto = new FixedassetsFilterDto();
        filterDto.setAssetsNo(assetsNo);
        filterDto.setAssetsName(assetsName);
        filterDto.setStartTime(start_Time);
        filterDto.setEndTime(end_Time);
        filterDto.setStyle(style);
        filterDto.setStatus(status);
        filterDto.setUseDepartment(useDepartment);
        filterDto.setUsePerson(usePerson);
        Sort sort = new Sort(Sort.Direction.DESC, "startTime");
        PageRequest pageRequest = new PageRequest(pageIndex, limit,sort);
        BaseMessage baseMessage =  fixedassetsService.findfixedasset(filterDto, pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        LOGGER.info("固定资产列表入参 ={}",filterDto.toString());
        LOGGER.info("固定资产列表出参={}",listResult.toString());
        return listResult;
    }
    
    /**
     * 搜索加列表 根据固定资产编号、固定资产名称进行模糊查询 根据入库时间进行精确查询
     * 
     */
    @RequestMapping(value = "fixedassetByList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult fixedassetByList(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM_DEFAUL) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String assetsNo,
            @RequestParam(required = false) String assetsName,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String status,
            HttpServletResponse response) {
        LOGGER.info("----------------fixedassetList-----pageIndex:" + pageIndex + "------limit:" + limit + "---startTime:" + startTime);
        response.setHeader("Access-Control-Allow-Origin", "*");
        ListResult listResult = new ListResult();
        pageIndex = pageIndex - 1;
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        Date start_Time = null;
        Date end_Time = null;
        if (null != startTime) {
            start_Time = DateUtil.parse(startTime, DateUtil.YYYYMMDD);
        }
        if (null != endTime) {
            end_Time = DateUtil.parse(endTime, DateUtil.YYYYMMDD);
        }
        FixedassetsFilterDto filterDto = new FixedassetsFilterDto();
        filterDto.setAssetsNo(assetsNo);
        filterDto.setAssetsName(assetsName);
        filterDto.setStartTime(start_Time);
        filterDto.setEndTime(end_Time);
        filterDto.setStyle(style);
        filterDto.setStatus(status);
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage baseMessage =  fixedassetsService.findfixedasset(filterDto, pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        LOGGER.info("资产查询出参,{}",listResult.toString());
        return listResult;
    }

    /**
     * 根据id固定资产删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public DelResult delfixed(@RequestParam(value = "fid[]",required = true) String[] fid,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        DelResult delResult = new DelResult();
        LOGGER.info("删除id：{}" + fid);
        BaseMessage message = fixedassetsService.delfixed(fid);
        if (message.getCode() == 200) {
            delResult.setSuccess(true);
        } else {
            delResult.setSuccess(false);
        }
        return delResult;
    }
    
    /**
     * 根据id固定资产删除
     */
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage delete(@RequestParam(required = true) String id) {
        LOGGER.info("删除id：{}" + id);
        BaseMessage message = fixedassetsService.delete(id);
        return message;
    }

    /**
     * 根据id固定资产查询 数组
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage findById(@RequestParam(required = true) String id) {
        LOGGER.info("查询id：{}" + id);
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        Integer ids = Integer.parseInt(id.trim());
        FixedassetsDto fixedassetsDto = fixedassetsService.findByAssetsId(ids);
        if (StringUtils.isEmpty(fixedassetsDto)) {
            message = new BaseMessage(MessageCode.NO_RESPONSE);
        }
        message.setData(fixedassetsDto);
        return message;
    }

    /**
     * 根据编号固定资产查询
     */
    @RequestMapping(value = "/findByNo", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage findByNo(@RequestBody FixedassetsDto fixedassetsDto) {
        LOGGER.info("查询id：{}" + fixedassetsDto.toString());
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        message.setData(fixedassetsService.findByAssetsNo(fixedassetsDto.getAssetsNo().trim()));
        return message;
    }
}
