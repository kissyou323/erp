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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.EquipmentDto;
import com.erp.response.BaseMessage;
import com.erp.response.DelResult;
import com.erp.response.ListResult;
import com.erp.response.MessageCode;
import com.erp.service.impl.EquipmentService;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

    private Logger log = LoggerFactory.getLogger(EquipmentController.class);

    @Autowired
    private EquipmentService equipmentService;

    /**
     * 添加
     * 
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage insert(
            @RequestParam(required = false) String equipmentNo,
            @RequestParam(required = false) String equipmentName,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String equipmentExplain,
            @RequestParam(required = false) String keeper,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String overTime,
            @RequestParam(required = false) String status) {
        EquipmentDto equipmentDto = new EquipmentDto();
        equipmentDto.setEquipmentNo(equipmentNo);
        equipmentDto.setEquipmentName(equipmentName);
        equipmentDto.setStyle(style);
        equipmentDto.setEquipmentExplain(equipmentExplain);
        equipmentDto.setKeeper(keeper);
        equipmentDto.setStatus(status);
        equipmentDto.setStartTime(startTime);
        equipmentDto.setOverTime(overTime);
        log.info("---------------------------添加装备传入数据-{}", equipmentDto);
        return equipmentService.saveEquipment(equipmentDto);
    }

    
    /**
     * 删除
     * 
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DelResult delete(
            @RequestParam(value = "fid[]", required = true) String[] fid,
            HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        DelResult delResult = new DelResult();
        BaseMessage message = equipmentService.delEquipment(fid);
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
        log.info("删除id：{}" + id);
        BaseMessage message = equipmentService.delete(id);
        return message;
    }
    
    /**
     * 修改
     * 
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage update(
            @RequestParam(required = false) String equipmentId,
            @RequestParam(required = false) String equipmentNo,
            @RequestParam(required = false) String equipmentName,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String equipmentExplain,
            @RequestParam(required = false) String keeper,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String overTime,
            @RequestParam(required = false) String record_use,
            @RequestParam(required = false) String record_explain,
            @RequestParam(required = false) String status) {
        EquipmentDto equipmentDto = new EquipmentDto();
        equipmentDto.setEquipmentNo(equipmentNo);
        equipmentDto.setEquipmentName(equipmentName);
        equipmentDto.setStyle(style);
        equipmentDto.setEquipmentExplain(equipmentExplain);
        equipmentDto.setKeeper(keeper);
        equipmentDto.setStatus(status);
//        Date start_Time = null;
//        Date over_time = null;
//        if (!StringUtils.isEmpty(startTime))
//            start_Time = DateUtil.parse(startTime, DateUtil.YYYYMMDD);
//        if (!StringUtils.isEmpty(overTime))
//            over_time = DateUtil.parse(overTime, DateUtil.YYYYMMDD);
        if (!StringUtils.isEmpty(equipmentId))
            equipmentDto.setEquipmentId(Integer.parseInt(equipmentId));
        equipmentDto.setStartTime(startTime);
        equipmentDto.setOverTime(overTime);
        equipmentDto.setRecord_use(record_use);
        equipmentDto.setRecord_explain(record_explain);
        log.info("---------------------------修改装备传入数据-{}", equipmentDto);
        return equipmentService.updateEquipment(equipmentDto);
    }

    /**
     * 用户列表 分页
     */
    @RequestMapping(value = "ListAll", method = RequestMethod.GET)
    @ResponseBody
    public ListResult findUsers(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String equipmentNo,
            @RequestParam(required = false) String equipmentName,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String status) {
        ListResult listResult = new ListResult();
        if (pageIndex < 0) {
            pageIndex = 0;
        }
//        Date start_Time = null;
//        Date end_Time = null;
//        if (null != startTime) {
//            start_Time = DateUtil.parse(startTime, DateUtil.YYYYMMDDHHMMSS);
//        }
//        if (null != endTime) {
//            end_Time = DateUtil.parse(endTime, DateUtil.YYYYMMDDHHMMSS);
//        }

        EquipmentDto filterDto = new EquipmentDto();
        filterDto.setStartTime(startTime);
        filterDto.setEndTime(endTime);
        filterDto.setStyle(style);
        filterDto.setStatus(status);
        filterDto.setEquipmentNo(equipmentNo);
        filterDto.setEquipmentName(equipmentName);
        log.info("----------------装备查询入参 {}", filterDto.toString());
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage baseMessage = equipmentService.findEquipment(filterDto,pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        log.info("----------------装备查询出参 {}", listResult.toString());
        return listResult;
    }
    
    /**
     * 用户列表 分页
     */
    @RequestMapping(value = "ListEquipment", method = RequestMethod.GET)
    @ResponseBody
    public ListResult List(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM_DEFAUL) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String equipmentNo,
            @RequestParam(required = false) String equipmentName,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String status) {
        ListResult listResult = new ListResult();
        pageIndex = pageIndex - 1;
        if (pageIndex < 0) {
            pageIndex = 0;
        }
//        Date start_Time = null;
//        Date end_Time = null;
//        if (null != startTime) {
//            start_Time = DateUtil.parse(startTime, DateUtil.YYYYMMDDHHMMSS);
//        }
//        if (null != endTime) {
//            end_Time = DateUtil.parse(endTime, DateUtil.YYYYMMDDHHMMSS);
//        }

        EquipmentDto equipmentDto = new EquipmentDto();
        equipmentDto.setStartTime(startTime);
        equipmentDto.setEndTime(endTime);
        equipmentDto.setStyle(style);
        equipmentDto.setStatus(status);
        equipmentDto.setEquipmentNo(equipmentNo);
        equipmentDto.setEquipmentName(equipmentName);
        log.info("----------------装备查询入参 {}", equipmentDto.toString());
        Sort sort = new Sort(Sort.Direction.DESC, "startTime");
        PageRequest pageRequest = new PageRequest(pageIndex, limit,sort);
        BaseMessage baseMessage = equipmentService.findEquipment(equipmentDto,pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        log.info("----------------装备查询出参 {}", listResult.toString());
        return listResult;
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage findById(@RequestParam(required = true) String id) {
        log.info("查询id：{}" + id);
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        Integer ids = Integer.parseInt(id.trim());
        EquipmentDto equipmentDto = equipmentService.findByEquipmentId(ids);
        if (!StringUtils.isEmpty(equipmentDto)) {
            message.setData(equipmentDto);
        } else {
            message = new BaseMessage(MessageCode.NO_RESPONSE);
        }
        log.info("出参查询id：{}" , message.toString());
        return message;
    }

    @RequestMapping(value = "/findByNo", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage findByNo(@RequestBody EquipmentDto equipmentDto) {
        log.info("查询id：{}" + equipmentDto.toString());
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        message.setData(equipmentService.findByEquipmentNo(equipmentDto.getEquipmentNo()));
        return message;

    }
    
    /**
     * 添加
     * 
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage insert(@RequestBody EquipmentDto equipmentDto) {
        log.info("---------------------------添加装备传入数据-{}",equipmentDto.toString());
        return equipmentService.saveEquipment(equipmentDto);
    }
    
    /**
     * 修改
     * 
     * @param equipmentDto
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage update(@RequestBody EquipmentDto equipmentDto ) {
        log.info("---------------------------修改装备传入数据-{}", equipmentDto.toString());
        return equipmentService.updateEquipment(equipmentDto);
    }
    
}
