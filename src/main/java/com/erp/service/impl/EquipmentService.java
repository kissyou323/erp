package com.erp.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.erp.config.Config;
import com.erp.constant.Constant;
import com.erp.dto.EquipmentDto;
import com.erp.dto.RecordDto;
import com.erp.orm.domain.Equipment;
import com.erp.orm.domain.User;
import com.erp.orm.repository.EquipmentRepository;
import com.erp.response.BaseMessage;
import com.erp.response.MessageCode;
import com.erp.service.specification.EquipmentSpecification;
import com.erp.util.CreateImg;
import com.erp.util.date.DateUtil;
@Service
public class EquipmentService {

	@Autowired
	private EquipmentRepository equipmentRepository;
	@Autowired
    private RecordService recordService;
	@Autowired
	private Config config ;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
    private HttpSession httpSession;
	
	/***
	 * 添加
	 * @param equipment
	 * @return
	 */
	@Transactional
	public BaseMessage saveEquipment(EquipmentDto equipmentDto) {
		BaseMessage message = null;
		User user = null;
		try {
		    user = ((User)httpSession.getAttribute("login_user"));
		    if (null != user) {
    			Equipment isEquipment = findByEquipmentNo(equipmentDto.getEquipmentNo());
    			if (null == isEquipment) {
    				Equipment equipment = new Equipment();
    				message = new BaseMessage(MessageCode.SUCCESSED);
    				BeanUtils.copyProperties(equipmentDto, equipment);
    				equipment.setModifyTime(new Date());
    				equipment.setStartTime(DateUtil.parse(equipmentDto.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
    				equipment.setOverTime(DateUtil.parse(equipmentDto.getOverTime(), DateUtil.YYYYMMDDHHMMSS));
    				equipment.setModifyName(user.getUserName());
    				String url =  config.getQrcode() + equipment.getEquipmentNo() + Constant.IMG_SUFFIX;
    				try {
//    					QrCodeUtil.encode(toLongString(equipmentDto),url);
    				    CreateImg.create(url, toLongString(equipmentDto));
    					logger.info("----------二维码地址 - {}", url);
    					equipment.setQrcode(equipment.getEquipmentNo()+ Constant.IMG_SUFFIX);
    					logger.info("二维码生成 qrcode:{} 成功",equipment.getEquipmentNo() + Constant.IMG_SUFFIX);
    				} catch (Exception e) {
    				    e.printStackTrace();
    					message = new BaseMessage(MessageCode.QRCODE_ERROR);
    					return message;
    				}
    				equipment = equipmentRepository.save(equipment);
    				logger.info("新增装备 id:{} 成功", equipment.getEquipmentId());
    			} else {
    				message = new BaseMessage(MessageCode.ASSETSNO_NOT_NULL);
    			}
		    } else {
		        message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
		    }
		} catch (Exception e) {
			e.printStackTrace();
			message = new BaseMessage(MessageCode.SYSTEM_ERROR);
		}
		return message;
	}
	
	public String toLongString(EquipmentDto equipmentDto) {
		String str =  equipmentDto.getEquipmentNo() + ";装备信息 [ 装备名称=" + equipmentDto.getEquipmentName() + ", 规格类型="
				+ equipmentDto.getStyle() 
				+ ", 保管员=" + equipmentDto.getKeeper() + "]";
		return str;
	}
	
	/***
	 * 删除
	 * @param equipment
	 * @return
	 */
	@Transactional
	public BaseMessage delEquipment(String[] ids) {
		BaseMessage message = null;
		try {
		    for (String idStr : ids) {
		        Integer id = Integer.parseInt(idStr);
		        Equipment isEquipment = equipmentRepository.findOne(id);
                if (null != isEquipment) {
                    Equipment equipment = new Equipment();
                    message = new BaseMessage(MessageCode.SUCCESSED);
                    equipmentRepository.delete(id);
                    logger.info("删除装备 id:{} 成功", equipment.getEquipmentId());
                } else {
                    message = new BaseMessage(MessageCode.NO_RESOURCE);
                }
		    }
		} catch (Exception e) {
			e.printStackTrace();
			message = new BaseMessage(MessageCode.NO_RESOURCE);
		}
		return message;
	}
	
	
	/***
	 * 修改
	 * @param equipment
	 * @return
	 */
	@Transactional
	public BaseMessage updateEquipment(EquipmentDto equipmentDto) {
		logger.info("进入service，入参："+equipmentDto);
		BaseMessage message = null;
		User user = ((User)httpSession.getAttribute("login_user"));
		String username = "";
        try {
            if (null != user) {
                username = user.getUserName();
                if (null == username) {
                    message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                    return message;
                }
            } else {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                return message;
            }
		} catch (Exception e) {
			e.printStackTrace();
			message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
			return message;
		}
        logger.info("-----1---username："+username);
		try {
			Equipment isEquipment = equipmentRepository.findOne(equipmentDto.getEquipmentId());
			logger.info("-----2---isEquipment："+isEquipment);
			if (null != isEquipment) {
				Equipment equipment = new Equipment();
				message = new BaseMessage(MessageCode.SUCCESSED);
				BeanUtils.copyProperties(equipmentDto, equipment);
				equipment.setStartTime(DateUtil.parse(equipmentDto.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
                equipment.setOverTime(DateUtil.parse(equipmentDto.getOverTime(), DateUtil.YYYYMMDDHHMMSS));
				if (!isEquipment.getStatus().equals(equipmentDto.getStatus())) {
					if ((Constant.STATUS_3.equals(equipmentDto.getStatus()) && Constant.STATUS_1.equals(isEquipment.getStatus())) || (Constant.STATUS_1.equals(equipmentDto.getStatus()) && Constant.STATUS_3.equals(isEquipment.getStatus())) ) {
		            	RecordDto recordDto = new RecordDto();
		            	recordDto.setRecordNo(equipmentDto.getEquipmentNo());
		                recordDto.setRecordStyle(equipmentDto.getStyle());
		                recordDto.setRecordName(equipmentDto.getEquipmentName());
		                recordDto.setRecordTime(DateUtil.parse(new Date(), DateUtil.YYYYMMDDHHMMSS));
		                recordDto.setUseDepartment(equipmentDto.getRecord_use());
		                recordDto.setAgent(username);
		                recordDto.setUseExplain(equipmentDto.getRecord_explain());
		                recordDto.setStatus(equipmentDto.getStatus()+"");
		                recordDto.setStyle(Constant.STYLE_2);
		            	recordService.save(recordDto);
		            }
				}
				equipment.setModifyTime(new Date());
				equipment.setModifyName(username);
				String url = config.getQrcode() + equipment.getEquipmentNo() + Constant.IMG_SUFFIX;
				try {
//					QrCodeUtil.encode(toLongString(equipmentDto),url);
				    CreateImg.create(url, toLongString(equipmentDto));
					logger.info("----------二维码地址 - {}", url);
					equipment.setQrcode(equipment.getEquipmentNo()+ Constant.IMG_SUFFIX);
					logger.info("二维码生成 qrcode:{} 成功",equipment.getEquipmentNo() + Constant.IMG_SUFFIX);
				} catch (Exception e) {
					message = new BaseMessage(MessageCode.QRCODE_ERROR);
					return message;
				}
				equipment = equipmentRepository.save(equipment);
				logger.info("修改装备 id:{} 成功", equipment.getEquipmentId());
			} else {
				message = new BaseMessage(MessageCode.NO_RESPONSE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = new BaseMessage(MessageCode.NO_RESPONSE);
		}
		return message;
	}
	
	/***
     * 修改状态
     * @param equipment
     * @return
     */
    @Transactional
    public BaseMessage updateforStatus(EquipmentDto equipmentDto) {
        logger.info("进入service，入参："+equipmentDto);
        BaseMessage message = null;
        User user = ((User)httpSession.getAttribute("login_user"));
        String username = "";
        try {
            if (null != user) {
                username = user.getUserName();
                if (null == username) {
                    message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                    return message;
                }
            } else {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                return message;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            return message;
        }
        logger.info("-----1---username："+username);
        try {
            Equipment equipment = equipmentRepository.findOne(equipmentDto.getEquipmentId());
            logger.info("-----2---isEquipment："+equipment);
            if (null != equipment) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                equipment.setModifyTime(new Date());
                equipment.setModifyName(username);
                equipment.setStatus(equipmentDto.getStatus());
//                String url = config.getQrcode()+equipment.getEquipmentNo()+Constant.IMG_SUFFIX;
                try {
                    BeanUtils.copyProperties(equipment, equipmentDto);
//                    QrCodeUtil.encode(toLongString(equipmentDto),url);
//                    CreateImg.create(url, toLongString(equipmentDto));
//                    logger.info("----------二维码地址 - {}", url);
//                    equipment.setQrcode(equipment.getEquipmentNo()+ Constant.IMG_SUFFIX);
//                    logger.info("二维码生成 qrcode:{} 成功",equipment.getEquipmentNo() + Constant.IMG_SUFFIX);
                } catch (Exception e) {
                    message = new BaseMessage(MessageCode.QRCODE_ERROR);
                    return message;
                }
                equipment = equipmentRepository.save(equipment);
                logger.info("修改装备 id:{} 成功", equipment.getEquipmentId());
            } else {
                message = new BaseMessage(MessageCode.NO_RESOURCE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = new BaseMessage(MessageCode.NO_RESOURCE);
        }
        return message;
    }
	
	/**
	 * 分页查询
	 * @param equipmentFilterDto
	 * @param pageable
	 * @return
	 */
	public BaseMessage findEquipment(EquipmentDto equipmentDto,Pageable pageable) {
		BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
		Page<Equipment> page = null;
		List<EquipmentDto> list = new ArrayList<EquipmentDto>();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long count = 0;
		try {
            EquipmentSpecification equipmentSpecification = new EquipmentSpecification(equipmentDto);
            page = equipmentRepository.findAll(equipmentSpecification, pageable);
            count = equipmentRepository.count(equipmentSpecification);
            for (Equipment eqt : page.getContent()) {
                EquipmentDto eqtDto = new EquipmentDto();
                BeanUtils.copyProperties(eqt, eqtDto);
                Calendar calendar = Calendar.getInstance();
                if (null != eqt.getStartTime()) {
                    calendar.setTimeInMillis(eqt.getStartTime().getTime());
                    eqtDto.setStartTime(formatter.format(calendar.getTime()));
                }
                
                if (null != eqt.getModifyTime()) {
                    calendar.setTimeInMillis(eqt.getModifyTime().getTime());
                    eqtDto.setModifyTime(formatter.format(calendar.getTime()));
                }
                if (null != eqt.getOverTime()) {
                    calendar.setTimeInMillis(eqt.getOverTime().getTime());
                    eqtDto.setOverTime(formatter.format(calendar.getTime()));
                }
                list.add(eqtDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		baseMessage.setData(list);
		baseMessage.setMsg(String.valueOf(count));
		return baseMessage;
	}
	
    /**
     * 根据装备No查询装备
     * 
     * @param username
     * @return
     */
    public Equipment findByEquipmentNo(String EquipmentNo) {
        List<Equipment> list = equipmentRepository.findByEquipmentNo(EquipmentNo);
        return list.isEmpty() ? null : list.get(0);
    }
    
    /**
     * 根据装备id查询装备
     * 
     * @param username
     * @return
     */
    public EquipmentDto findByEquipmentId(int EquipmentNo) {
        EquipmentDto equipmentDto =null;
        List<Equipment> list = equipmentRepository.findByEquipmentId(EquipmentNo);
        Equipment equipment = list.isEmpty() ? null : list.get(0);
        if (!StringUtils.isEmpty(equipment)) {
            equipmentDto = new EquipmentDto();
            BeanUtils.copyProperties(equipment, equipmentDto);
            if (!StringUtils.isEmpty(equipment.getStartTime()))
                equipmentDto.setStartTime(DateUtil.parse(equipment.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
            if (!StringUtils.isEmpty(equipment.getOverTime()))
                equipmentDto.setOverTime(DateUtil.parse(equipment.getOverTime(), DateUtil.YYYYMMDDHHMMSS));
            if (!StringUtils.isEmpty(equipment.getModifyTime()))
                equipmentDto.setModifyTime(DateUtil.parse(equipment.getModifyTime(), DateUtil.YYYYMMDDHHMMSS));
        }
        return equipmentDto;
    }

    public BaseMessage delete(String id) {
        BaseMessage message = null;
        if (!StringUtils.isEmpty(id)) {
            User user = ((User) httpSession.getAttribute("login_user"));
            Integer equipmentId = Integer.parseInt(id);
            if (null != user) {
                Equipment equipment = equipmentRepository.findOne(equipmentId);
                if (null != equipment) {
                    message = new BaseMessage(MessageCode.SUCCESSED);
                    equipmentRepository.delete(equipmentId);
                    message.setData(equipmentId);
                } else {
                    message = new BaseMessage(MessageCode.NO_RESPONSE);
                    message.setMsg("该装备不存在");
                    message.setData(equipmentId);
                    logger.error("该固定 id:{}不存在", id);
                }
            } else {
                logger.info("----------登录超时");
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                return message;
            }
        } else {
            logger.info("----------请求id为空");
            message = new BaseMessage(MessageCode.FAILED);
            return message;
        }
        return message;
    }
    
}
