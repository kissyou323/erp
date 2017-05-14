package com.erp.controller;

import static com.erp.constant.Constant.DEF_PAGE_NUM;
import static com.erp.constant.Constant.DEF_PAGE_SIZE;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.MsgDto;
import com.erp.response.BaseMessage;
import com.erp.response.ListResult;
import com.erp.response.MessageCode;
import com.erp.service.impl.MsgService;
import com.erp.util.date.DateUtil;

@Controller
@RequestMapping("/msg")
public class MsgController {
    private Logger log = LoggerFactory.getLogger(MsgController.class);

    @Autowired
    private MsgService msgService;

    /**
     * 查询列表
     * @param userLogin
     * @param type
     * @return
     */
    @RequestMapping(value = "MsgListAll", method = RequestMethod.GET)
    @ResponseBody
    public ListResult findMsg(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String type) {
        
        ListResult listResult = new ListResult();
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        Date start_Time = null;
        Date end_Time = null;
        if (null != startTime) {
            start_Time = DateUtil.parse(startTime, DateUtil.YYYYMMDDHHMMSS);
        }
        if (null != endTime) {
            end_Time = DateUtil.parse(endTime, DateUtil.YYYYMMDDHHMMSS);
        }
        MsgDto msgDto = new MsgDto();
        msgDto.setStartTime(start_Time);
        msgDto.setEndTime(end_Time);
        if (!StringUtils.isEmpty(type))
            msgDto.setType(Integer.parseInt(type));
        log.info("MsgListAll入参 ==" + msgDto.toString());
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage baseMessage =  msgService.findMsg(msgDto,pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        return listResult;
    }
    
    /**
     * 查询单条
     * @param id
     * @return
     */
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage findById(
            @RequestParam(required = true) String id) {
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        Integer ids = Integer.parseInt(id.trim());
        message.setData(msgService.findById(ids));
        return message;
    }
}
