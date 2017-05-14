package com.erp.controller;

import static com.erp.constant.Constant.DEF_PAGE_NUM;
import static com.erp.constant.Constant.DEF_PAGE_NUM_DEFAUL;
import static com.erp.constant.Constant.DEF_PAGE_SIZE;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.RepairDto;
import com.erp.response.BaseMessage;
import com.erp.response.DelResult;
import com.erp.response.ListResult;
import com.erp.service.impl.RepairService;

/**
 * 
 * @author liuyang 维修记录
 *
 */
@Controller
@RequestMapping("repair")
public class RepairController {

    @Autowired
    private RepairService repairService;
    private Logger LOGGER = LoggerFactory.getLogger(RepairController.class);

    /**
     * 添加
     */
    @RequestMapping(value = "save", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage save(
            @RequestParam(required = false) String repair_id,
            @RequestParam(required = false) String repair_name,
            @RequestParam(required = false) String tel,
            @RequestParam(required = false) String repair_explain,
            @RequestParam(required = false) String repair_time,
            @RequestParam(required = false) String repair_status,
            @RequestParam(required = false) String assets_equipment_id,
            @RequestParam(required = false) String assets_equipment_name,
            @RequestParam(required = false) String assets_equipment_no,
            @RequestParam(required = false) String style
            ) {
        RepairDto repairDto = new RepairDto();
        Integer assetsEquipmentId = null;
        if (!StringUtils.isEmpty(assets_equipment_id)) {
            assetsEquipmentId = Integer.parseInt(assets_equipment_id);
            repairDto.setAssets_equipment_id(assetsEquipmentId);
        }
        
        if (!StringUtils.isEmpty(repair_id)) {
            repairDto.setRepair_id(Integer.parseInt(repair_id));
        }
        repairDto.setRepair_name(repair_name);
        repairDto.setTel(tel);
        repairDto.setRepair_explain(repair_explain);
        repairDto.setStyle(style);
        repairDto.setRepair_status(repair_status);
        repairDto.setAssets_equipment_no(assets_equipment_no);
        repairDto.setAssets_equipment_name(assets_equipment_name);
        repairDto.setRepair_time(repair_time);
        LOGGER.info("--------------------> "+repairDto.toString());
        return repairService.save(repairDto);
    }
    
    /**
     * 添加
     */
    @RequestMapping(value = "saveAndupdate", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage save(@RequestBody RepairDto repairDto) {
        LOGGER.info("--------------------> "+repairDto.toString());
        return repairService.save(repairDto);
    }
    
    /**
     * 单条查询
     */
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage findById( @RequestParam(required = false) String id) {
        LOGGER.info("--------单条查询------------> "+ id);
        return repairService.findById(id);
    }
    
    /**
     * 搜索加列表
     * 根据名称进行模糊查询
     * style,类型（1-资产、2-装备）
     */
    /**
     * @param pageIndex
     * @param limit
     * @param style
     * @param repair_name
     * @param startTime
     * @param endTime
     * @param assets_equipment_name
     * @return
     */
    @RequestMapping(value = "searchList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult recordList(@RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = true) String style,
            @RequestParam(required = true) String repair_name,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String repair_status,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String assets_equipment_name) {
    	LOGGER.info("----------------recordList-----pageIndex:"+pageIndex+"------limit:"+limit+"---assets_equipment_name:"+assets_equipment_name);
    	ListResult listResult = new ListResult();
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        RepairDto repairDto = new RepairDto();
        repairDto.setStartTime(startTime);
        repairDto.setEndTime(endTime);
        
        repairDto.setAssets_equipment_name(assets_equipment_name);
        repairDto.setStyle(style);
        repairDto.setRepair_name(repair_name);
        repairDto.setRepair_status(repair_status);
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage baseMessage =  repairService.findRecord(repairDto,pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        return listResult;
    }
    
    /**
     * 搜索加列表
     * 根据名称进行模糊查询
     * style,类型（1-资产、2-装备）
     */
    /**
     * @param pageIndex
     * @param limit
     * @param style
     * @param repair_name
     * @param startTime
     * @param endTime
     * @param assets_equipment_name
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public ListResult search(@RequestParam(required = false, defaultValue = DEF_PAGE_NUM_DEFAUL) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String repair_name,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String repair_status,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String assets_equipment_name) {
        LOGGER.info("----------------recordList-----pageIndex:"+pageIndex+"------limit:"+limit+"---assets_equipment_name:"+assets_equipment_name);
        ListResult listResult = new ListResult();
        pageIndex = pageIndex - 1;
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        RepairDto repairDto = new RepairDto();
        repairDto.setStartTime(startTime);
        repairDto.setEndTime(startTime);
        
        repairDto.setAssets_equipment_name(assets_equipment_name);
        repairDto.setStyle(style);
        repairDto.setRepair_name(repair_name);
        repairDto.setRepair_status(repair_status);
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage baseMessage =  repairService.findRecord(repairDto,pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        return listResult;
    }
    
    /**
     * 根据id固定资产删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public DelResult delete(@RequestParam(value = "fid[]",required = true) String[] fid,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        DelResult delResult = new DelResult();
        BaseMessage message =  repairService.delete(fid);
        if (message.getCode() == 200) {
            delResult.setSuccess(true);
        } else {
            delResult.setSuccess(false);
        }
        return delResult;
    }
}
