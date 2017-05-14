package com.erp.controller;

import static com.erp.constant.Constant.DEF_PAGE_NUM;
import static com.erp.constant.Constant.DEF_PAGE_NUM_DEFAUL;
import static com.erp.constant.Constant.DEF_PAGE_SIZE;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.RecordDto;
import com.erp.response.BaseMessage;
import com.erp.response.ListResult;
import com.erp.service.impl.RecordService;
import com.erp.util.date.DateUtil;

/**
 * 
 * @author liuyang 固定资产
 *
 */
@Controller
@RequestMapping("record")
public class RecordController {

    @Autowired
    private RecordService recordService;
    private Logger LOGGER = LoggerFactory.getLogger(RecordController.class);

    /**
     * 搜索加列表
     * 根据编号、名称进行模糊查询
     * 根据借入借出时间进行精确查询
     * style,//类型（1-资产、2-装备 3-案卷 4-涉案财物）
     */
    @RequestMapping(value = "searchList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult recordList(@RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String recordStyle,
            @RequestParam(required = false) String recordNo,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String recordName,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime) {
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        RecordDto recordDto = new RecordDto();
        Date start_Time = null;
        Date end_Time = null;
        if (null != startTime) {
            start_Time = DateUtil.parse(startTime, DateUtil.YYYYMMDD);
            recordDto.setStartTime(start_Time);
        }
        if (null != endTime) {
            end_Time = DateUtil.parse(endTime, DateUtil.YYYYMMDD);
            recordDto.setEndTime(end_Time);
        }
        recordDto.setRecordNo(recordNo);
        recordDto.setRecordStyle(recordStyle);
        recordDto.setRecordName(recordName);
        recordDto.setStatus(status);
        recordDto.setStyle(style);
        Sort sort = new Sort(Sort.Direction.DESC, "recordTime");
        PageRequest pageRequest = new PageRequest(pageIndex, limit,sort);
        BaseMessage baseMessage = recordService.findRecord(recordDto,pageRequest);
        ListResult listResult = new ListResult();
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        return listResult;
    }
    
    /**
     * 搜索加列表
     * 根据编号、名称进行模糊查询
     * 根据借入借出时间进行精确查询
     * style,//类型（1-资产、2-装备 3-案卷 4-涉案财物）
     */
    @RequestMapping(value = "ListAll", method = RequestMethod.GET)
    @ResponseBody
    public ListResult ListAll(@RequestParam(required = false, defaultValue = DEF_PAGE_NUM_DEFAUL) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String recordStyle,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String recordName,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime) {
        pageIndex = pageIndex - 1;
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        RecordDto recordDto = new RecordDto();
        Date start_Time = null;
        Date end_Time = null;
        if (null != startTime) {
            start_Time = DateUtil.parse(startTime, DateUtil.YYYYMMDD);
            recordDto.setStartTime(start_Time);
        }
        if (null != endTime) {
            end_Time = DateUtil.parse(endTime, DateUtil.YYYYMMDD);
            recordDto.setEndTime(end_Time);
        }
        recordDto.setRecordStyle(recordStyle);
        recordDto.setRecordName(recordName);
        recordDto.setStatus(status);
        recordDto.setStyle(style);
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage baseMessage = recordService.findRecord(recordDto,pageRequest);
        ListResult listResult = new ListResult();
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        return listResult;
    }
    
    /**
     * 根据id固定资产删除
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseMessage delete(@PathVariable Integer id) {

        LOGGER.info("删除id：{}" + id);
        return recordService.delete(id);

    }

    /**
     * 添加
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage save(@RequestBody RecordDto recordDto) {
        LOGGER.info("--------------------> "+recordDto.toString());
        return recordService.save(recordDto);
    }
}
