package com.erp.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.erp.dto.EquipmentDto;
import com.erp.dto.FixedassetsDto;
import com.erp.dto.RepairDto;
import com.erp.orm.domain.Repair;
import com.erp.orm.domain.User;
import com.erp.orm.repository.RepairRepository;
import com.erp.response.BaseMessage;
import com.erp.response.MessageCode;
import com.erp.service.specification.RepairSpecification;
import com.erp.util.date.DateUtil;

@Service
public class RepairService {
	private Logger LOGGER = LoggerFactory.getLogger(RepairService.class);
	
	@Autowired
	private RepairRepository repairRepository;
	 @Autowired
	 private HttpSession httpSession;
	 @Autowired
	 private FixedassetsService fixedassetsService;
	 @Autowired
	 private EquipmentService equipmentService;
	/**
     * 添加维修记录
     * 
     * @param RecordDto
     * @return
     */
    @Transactional
    public BaseMessage save(RepairDto repairDto) {
        BaseMessage message = null;
        String username = "";
        try {
            try {
                User user = ((User) httpSession.getAttribute("login_user"));
                if (null == user) {
                    LOGGER.info(MessageCode.USER_NOT_LOGIN + "用户未登陆或超时了");
                    message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                    return message;
                }
                username = user.getUserName();
            } catch (Exception e) {
                e.printStackTrace();
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                return message;
            }
            message = new BaseMessage(MessageCode.SUCCESSED);
            Repair repair = new Repair();
            BeanUtils.copyProperties(repairDto, repair);
            repair.setRepair_time(DateUtil.parse(repairDto.getRepair_time(), DateUtil.YYYYMMDDHHMMSS));
            if (repairDto.getStyle().equals("1")) {
                //固定资产
                FixedassetsDto fixedassetsDto = new FixedassetsDto();
                fixedassetsDto.setAssetsId(repairDto.getAssets_equipment_id());
                fixedassetsDto.setStatus(repairDto.getRepair_status());
                fixedassetsService.updateforStatus(fixedassetsDto);
            } else {
                //装备
                EquipmentDto equipmentDto = new EquipmentDto();
                equipmentDto.setEquipmentId(repairDto.getAssets_equipment_id());
                equipmentDto.setStatus(repairDto.getRepair_status());
                equipmentService.updateforStatus(equipmentDto);
            }
            Repair r = repairRepository.save(repair);
            message.setData(r);
            LOGGER.info("新增维修记录id:{} 成功", repair.getRepair_id());
        } catch (Exception e) {
            e.printStackTrace();
            message = new BaseMessage(MessageCode.FAILED);
            return message;
        }
        return message;
    }
	
    
    /**
     * 修改
     * 
     * @param RecordDto
     * @return
     */
    @Transactional
    public BaseMessage update(RepairDto repairDto) {
        BaseMessage message = null;
        String username = "";
        try {
            User user = ((User) httpSession.getAttribute("login_user"));
            if (null == user) {
                LOGGER.info(MessageCode.USER_NOT_LOGIN + "用户未登陆或超时了");
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                return message;
            }
            username = user.getUserName();
        } catch (Exception e) {
            e.printStackTrace();
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            return message;
        }
        Repair rer = findByRepairAssets_id(repairDto.getAssets_equipment_id());
        if (StringUtils.isEmpty(rer)) {
            message = new BaseMessage(MessageCode.SUCCESSED);
            Repair repair = new Repair();
            BeanUtils.copyProperties(repairDto, repair);
            repair.setRepair_time(DateUtil.parse(repairDto.getRepair_time(), DateUtil.YYYYMMDDHHMMSS));
            if (repairDto.getStyle().equals("1")) {
                //固定资产
                FixedassetsDto fixedassetsDto = new FixedassetsDto();
                fixedassetsDto.setAssetsId(repairDto.getAssets_equipment_id());
                fixedassetsDto.setStatus(repairDto.getRepair_status());
                fixedassetsService.updateforStatus(fixedassetsDto);
            } else {
                //装备
                EquipmentDto equipmentDto = new EquipmentDto();
                equipmentDto.setEquipmentId(repairDto.getAssets_equipment_id());
                equipmentDto.setStatus(repairDto.getRepair_status());
                equipmentService.updateEquipment(equipmentDto);
            }
            Repair r = repairRepository.save(repair);
            message.setData(r);
            LOGGER.info("新增维修记录id:{} 成功", repair.getRepair_id());
        } else {
            message = new BaseMessage(MessageCode.NO_RESPONSE);
            message.setMsg("该记录已存在");
            LOGGER.info(MessageCode.NO_RESPONSE + "该记录已存在");
        }
        return message;
    }
    
    /**
     * 根据编号查询
     * 
     * @param assetsNo
     * @return
     */
    private Repair findByRepairNo(String repairNo) {
        List<Repair> list = repairRepository.findByrepairNo(repairNo);
        return list.isEmpty() ? null : list.get(0);
    }
    
    
    private Repair findByRepairAssets_id(Integer Assets_equipment_id) {
        List<Repair> list = repairRepository.findByRepairAssets_id(Assets_equipment_id);
        return list.isEmpty() ? null : list.get(0);
    }
	/**
     * 删除记录
     * 
     * @param fixedassetsDto
     * @return
     */
    @Transactional
    public BaseMessage delete(String[] ids) {
        BaseMessage message = null;
        for (String idStr : ids) {
            Integer id = Integer.parseInt(idStr);
            Repair repair = repairRepository.findOne(id);
            if (null != repair) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                repairRepository.delete(id);
                message.setData(id);
            } else {
                message = new BaseMessage(MessageCode.FAILED);
                message.setMsg("该维修记录不存在");
                message.setData(id);
                LOGGER.error("该维修记录 id:{}不存在", id);
            }
        }
        return message;
    }
    

    /**
     * 分页查询
     * @param filterDto
     * @param pageRequest
     * @return
     */
    @Transactional
    public BaseMessage findRecord(RepairDto repairDto,Pageable pageable) {
		BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
		Page<Repair> page = null;
		long count = 0;
		try {
			RepairSpecification recordSpecification = new RepairSpecification(repairDto);
			page = repairRepository.findAll(recordSpecification, pageable);
			count = repairRepository.count(recordSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		baseMessage.setData(page.getContent());
		baseMessage.setMsg(String.valueOf(count));
		return baseMessage;
	}

    /**
     * 单个查询
     * @param id
     * @return
     */
    public BaseMessage findById(String id) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        RepairDto repairDto =null;
        if (!StringUtils.isEmpty(id)) {
            Repair repair = repairRepository.findOne(Integer.parseInt(id));
            if (!StringUtils.isEmpty(repair)) {
                repairDto = new RepairDto();
                BeanUtils.copyProperties(repair, repairDto);
                repairDto.setRepair_time(DateUtil.parse(repair.getRepair_time(), DateUtil.YYYYMMDDHHMMSS));
            } 
        } else {
            baseMessage = new BaseMessage(MessageCode.PARAMS_ERROR);
        }
        baseMessage.setData(repairDto);
        return baseMessage;
    }
}
