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

import com.erp.dto.CaseDto;
import com.erp.dto.EquipmentDto;
import com.erp.dto.FixedassetsDto;
import com.erp.dto.PossessionsDto;
import com.erp.dto.RecordDto;
import com.erp.orm.domain.Case;
import com.erp.orm.domain.Equipment;
import com.erp.orm.domain.Fixedassets;
import com.erp.orm.domain.Possessions;
import com.erp.orm.domain.Record;
import com.erp.orm.domain.User;
import com.erp.orm.repository.RecordRepository;
import com.erp.response.BaseMessage;
import com.erp.response.MessageCode;
import com.erp.service.specification.RecordSpecification;
import com.erp.util.date.DateUtil;

@Service
public class RecordService {
	private Logger LOGGER = LoggerFactory.getLogger(RecordService.class);
	
	@Autowired
	private RecordRepository recordRepository;
	 @Autowired
	 private HttpSession httpSession;
	 @Autowired
	 private FixedassetsService fixedassetsService;
	 
	 @Autowired
	 private EquipmentService equipmentService;
	 
	 @Autowired
	 private CaseService caseService;
	 
	 @Autowired
	 private PossessionsService possessionsService;
	 
	 
	 
	/**
     * 借出还入
     * 
     * @param RecordDto
     * @return
     */
    @Transactional
    public BaseMessage save(RecordDto recordDto) {
        BaseMessage message = null;
        message = new BaseMessage(MessageCode.SUCCESSED);
        try {
            if (recordDto.getStyle().equals("1")) {
                /**
                 * 固定资产
                 */
                Fixedassets fixedassets = fixedassetsService.findByAssetsNo(recordDto.getRecordNo());
                if (StringUtils.isEmpty(fixedassets)) {
                    message = new BaseMessage(MessageCode.NO_RESPONSE);
                    return message;
                }
//                if (fixedassets.getStatus() == 3) {
//                    message = new BaseMessage(MessageCode.ASSETSNO_NOT_NULL);
//                    return message;
//                }
                FixedassetsDto fixedassetsDto = new FixedassetsDto();
                fixedassetsDto.setAssetsId(fixedassets.getAssetsId());
                fixedassetsDto.setStatus(recordDto.getStatus());
                message = fixedassetsService.updateforStatus(fixedassetsDto);
            } else if (recordDto.getStyle().equals("2")) {
                /**
                 * 装备管理
                 */
                Equipment equipment = equipmentService.findByEquipmentNo(recordDto.getRecordNo());
                if (StringUtils.isEmpty(equipment)) {
                    message = new BaseMessage(MessageCode.NO_RESPONSE);
                    return message;
                }
//                if (equipment.getStatus().equals("3")) {
//                    message = new BaseMessage(MessageCode.ASSETSNO_NOT_NULL);
//                    return message;
//                }
                EquipmentDto equipmentDto = new EquipmentDto();
                equipmentDto.setEquipmentId(equipment.getEquipmentId());
                equipmentDto.setStatus(recordDto.getStatus());
                message = equipmentService.updateforStatus(equipmentDto);
            } else if (recordDto.getStyle().equals("3")) {
                /**
                 * 案件管理
                 */
                Case cases = caseService.findByCaseNo(recordDto.getRecordNo());
                if (StringUtils.isEmpty(cases)) {
                    message = new BaseMessage(MessageCode.NO_RESPONSE);
                    return message;
                }
//                if (cases.getFileStatus().equals("3")) {
//                    message = new BaseMessage(MessageCode.ASSETSNO_NOT_NULL);
//                    return message;
//                }
                CaseDto casedto = new CaseDto();
                casedto.setCaseId(cases.getCaseId());
                casedto.setFileStatus(recordDto.getStatus());
                caseService.updateStatus(casedto);
            } else if (recordDto.getStyle().equals("4")) {
                /**
                 * 涉案财物管理
                 */
                Possessions possessions = possessionsService.findByRepairNo(recordDto.getRecordNo());
                if (StringUtils.isEmpty(possessions)) {
                    message = new BaseMessage(MessageCode.NO_RESPONSE);
                    return message;
                }
                PossessionsDto possessionsDto = new PossessionsDto();
                possessionsDto.setPossessionsId(possessions.getPossessionsId());
                possessionsDto.setStatus(recordDto.getStatus());
                message = possessionsService.updateStatus(possessionsDto);
            }
            Record record = new Record();
            LOGGER.info("recordDto = {}", recordDto);
            BeanUtils.copyProperties(recordDto, record);
            LOGGER.info("record = {}", record);
            User user = ((User)httpSession.getAttribute("login_user"));
            String username = "";
            if (null != user) {
                if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getUserName())) {
                    message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                    return message;
                }
                /**
                 * 用户登录名|用户名
                 */
                username = user.getLoginName() + ";" +user.getUserName();
            } else {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                return message;
            }
            /**
             * 
             * 使用人登录名 | 使用人名字
             * 
             */
            record.setUseDepartment(recordDto.getUseDepartment_login() + ";" + recordDto.getUseDepartment());
            record.setAgent(username);
            record.setRecordTime(DateUtil.parse(recordDto.getRecordTime(), DateUtil.YYYYMMDDHHMMSS));
            Record r = recordRepository.save(record);
            message.setData(r);
            LOGGER.info("新增借出还入 id:{} 成功", record.getRecordNo());
        } catch (Exception e) {
            e.printStackTrace();
            message = new BaseMessage(MessageCode.FAILED);
            return message;
        }
        return message;
    }
	
    /**
     * 根据编号查询
     * 
     * @param assetsNo
     * @return
     */
    private Record findByRecordNo(String recordNo) {
        List<Record> list = recordRepository.findByAssetsNo(recordNo);
        return list.isEmpty() ? null : list.get(0);
    }
    
	/**
     * 删除记录
     * 
     * @param fixedassetsDto
     * @return
     */
    @Transactional
    public BaseMessage delete(Integer id) {
        BaseMessage message = null;
        Record record = recordRepository.findOne(id);
        if (null != record) {
            message = new BaseMessage(MessageCode.SUCCESSED);
            recordRepository.delete(id);
            message.setData(id);
        } else {
            message = new BaseMessage(MessageCode.FAILED);
            message.setMsg("该不存在");
            message.setData(id);
            LOGGER.error("该 id:{}不存在", id);
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
    public BaseMessage findRecord(RecordDto recordDto,Pageable pageable) {
		BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
		Page<Record> page = null;
		long count = 0;
		try {
			RecordSpecification recordSpecification = new RecordSpecification(recordDto);
			page = recordRepository.findAll(recordSpecification, pageable);
			count = recordRepository.count(recordSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		baseMessage.setData(page.getContent());
		baseMessage.setMsg(String.valueOf(count));
		return baseMessage;
	}

    /**
     * 查询未还的记录
     * @return
     */
    public List<Record> findRecordList() {
    	return recordRepository.findRecordList();
    }
}
