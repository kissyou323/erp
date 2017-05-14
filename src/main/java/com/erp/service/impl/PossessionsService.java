package com.erp.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.erp.config.Config;
import com.erp.constant.Constant;
import com.erp.dto.FixedassetsDto;
import com.erp.dto.PossessionsDto;
import com.erp.orm.domain.CaseList;
import com.erp.orm.domain.Possessions;
import com.erp.orm.domain.User;
import com.erp.orm.repository.PossessionsRepository;
import com.erp.response.BaseMessage;
import com.erp.response.MessageCode;
import com.erp.service.specification.PossessionsSpecification;
import com.erp.util.CreateImg;
import com.erp.util.date.DateUtil;

@Service
public class PossessionsService {
    private Logger LOGGER = LoggerFactory.getLogger(PossessionsService.class);
    
    @Autowired
    private PossessionsRepository possessionsRepository;
     @Autowired
     private HttpSession httpSession;
     @Autowired
     private FixedassetsService fixedassetsService;
     @Autowired
     private EquipmentService equipmentService;
     @Autowired
     private RecordService recordService;
     @Autowired
     private Config config ;
     
     
    /**
     * 添加
     * 
     * @param RecordDto
     * @return
     */
    @Transactional
    public BaseMessage save(PossessionsDto possessionsDto) {
        BaseMessage message = null;
        message = new BaseMessage(MessageCode.SUCCESSED);
        /**
         * 设置涉案财物编号
         */
        possessionsDto.setPossessionsNo(DateUtil.parse(new Date(), DateUtil.SXF_TIME_LONG));
        Possessions exispossessions = findByRepairNo(possessionsDto.getPossessionsNo());
        if (null == exispossessions) {
            Possessions possessions = new Possessions();
            BeanUtils.copyProperties(possessionsDto, possessions);
            possessions.setStartTime(DateUtil.parse(possessionsDto.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
            possessions.setHolderTime(DateUtil.parse(possessionsDto.getHolderTime(), DateUtil.YYYYMMDDHHMMSS));
            String username = "";
            User user = null;
           try {
                user = ((User)httpSession.getAttribute("login_user"));
                if (null == user) {
                    message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                    LOGGER.info("登录超时");
                    return message;
                } else  {
                    username = user.getUserName();
                }
            } catch (Exception e) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                LOGGER.info("登录超时");
                return message;
            }
           LOGGER.info("开始生成二维码---");
           String url = config.getQrcode() + possessions.getPossessionsNo() + Constant.IMG_SUFFIX;
           try {
               CreateImg.create(url, toLongString(possessionsDto));
               LOGGER.info("----------二维码地址 - {}", url);
               possessions.setQrcode(possessions.getPossessionsNo()+Constant.IMG_SUFFIX);
               LOGGER.info("二维码生成 qrcode:{} 成功", possessions.getPossessionsNo()+Constant.IMG_SUFFIX);
           } catch (Exception e) {
               message = new BaseMessage(MessageCode.QRCODE_ERROR);
               return message;
           }
            LOGGER.info("---possessions {}",possessions.toString());
            possessions.setModifyTime(new Date());
            possessions.setModifyName(username);
            possessions = possessionsRepository.save(possessions);
            message.setData(possessions);
        } else {
            message = new BaseMessage(MessageCode.ASSETSNO_NOT_NULL);
            message.setMsg("该涉案财物已存在");
            LOGGER.info("该涉案财物已存在");
        }
        return message;
    }
    
    /**
     * 根据编号查询
     * 
     * @param assetsNo
     * @return
     */
    public Possessions findByRepairNo(String possessionsNo) {
        List<Possessions> list = possessionsRepository.findByPossessionsNo(possessionsNo);
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
            Possessions possessions = possessionsRepository.findOne(id);
            if (null != possessions) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                possessionsRepository.delete(id);
                message.setData(id);
            } else {
                message = new BaseMessage(MessageCode.FAILED);
                message.setMsg("该固定资产不存在");
                message.setData(id);
                LOGGER.error("该固定 id:{}不存在", id);
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
    public BaseMessage findPossessions(PossessionsDto possessionsDto,Pageable pageable) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        Page<Possessions> page = null;
        long count = 0;
        try {
            PossessionsSpecification recordSpecification = new PossessionsSpecification(possessionsDto);
            page = possessionsRepository.findAll(recordSpecification, pageable);
            count = possessionsRepository.count(recordSpecification);
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseMessage.setData(page.getContent());
        baseMessage.setMsg(String.valueOf(count));
        return baseMessage;
    }
    
    /**
     * 修改
     * 
     * @param fixedassetsDto
     * @return
     */
    @Transactional
    public BaseMessage update(PossessionsDto possessionsDto) {
        Possessions exispossessions = possessionsRepository.findOne(possessionsDto.getPossessionsId());
        BaseMessage message = null;
        try {
            if (null != exispossessions) {
                Possessions possessions = new Possessions();
                message = new BaseMessage(MessageCode.SUCCESSED);
                BeanUtils.copyProperties(possessionsDto, possessions);
                possessions.setStartTime(DateUtil.parse(possessionsDto.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
                possessions.setHolderTime(DateUtil.parse(possessionsDto.getHolderTime(), DateUtil.YYYYMMDDHHMMSS));
                String username = "";
                User user = null;
               try {
                    user = ((User)httpSession.getAttribute("login_user"));
                    if (null == user) {
                        message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                        LOGGER.info("登录超时");
                        return message;
                    } else  {
                        username = user.getUserName();
                    }
                } catch (Exception e) {
                    message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                    LOGGER.info("登录超时");
                    return message;
                }
                LOGGER.info("---possessions {}",possessions.toString());
                LOGGER.info("开始生成二维码---");
                String url = config.getQrcode() + possessions.getPossessionsNo() + Constant.IMG_SUFFIX;
                try {
                    CreateImg.create(url, toLongString(possessionsDto));
                    LOGGER.info("----------二维码地址 - {}", url);
                    possessions.setQrcode(possessions.getPossessionsNo()+Constant.IMG_SUFFIX);
                    LOGGER.info("二维码生成 qrcode:{} 成功", possessions.getPossessionsNo()+Constant.IMG_SUFFIX);
                } catch (Exception e) {
                    message = new BaseMessage(MessageCode.QRCODE_ERROR);
                    return message;
                }
                
                possessions.setModifyTime(new Date());
                possessions.setModifyName(username);
                possessions = possessionsRepository.save(possessions);
                message.setData(possessions);
            } else {
                message = new BaseMessage(MessageCode.NO_RESPONSE);
                message.setMsg("该涉案财物不存在");
                LOGGER.info("该涉案财物不存在");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            message = new BaseMessage(MessageCode.FAILED);
            message.setMsg("涉案财物修改异常");
            LOGGER.info("涉案财物修改异常");
        }
        return message;
    }
    
    /**
     * 根据id查询
     * 
     * @param username
     * @return
     */
    public PossessionsDto findById(Integer possessionsId) {
        PossessionsDto possessionsDto = null;
        Possessions possessions = possessionsRepository.findOne(possessionsId);
        if (!StringUtils.isEmpty(possessions)) {
            possessionsDto = new PossessionsDto();
            BeanUtils.copyProperties(possessions, possessionsDto);
            if (!StringUtils.isEmpty(possessions.getStartTime()))
                possessionsDto.setStartTime(DateUtil.parse(possessions.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
            if (!StringUtils.isEmpty(possessions.getHolderTime()))
                possessionsDto.setHolderTime(DateUtil.parse(possessions.getHolderTime(), DateUtil.YYYYMMDDHHMMSS));
            if (!StringUtils.isEmpty(possessions.getModifyTime()))
                possessionsDto.setModifyTime(DateUtil.parse(possessions.getModifyTime(), DateUtil.YYYYMMDDHHMMSS));
        }
        return possessionsDto;
    }

    
    
    /**
     * 修改
     * 
     * @param fixedassetsDto
     * @return
     */
    @Transactional
    public BaseMessage updateStatus(PossessionsDto possessionsDto) {
        Possessions exispossessions = possessionsRepository.findOne(possessionsDto.getPossessionsId());
        BaseMessage message = null;
        try {
            if (null != exispossessions) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                String username = "";
                User user = null;
               try {
                    user = ((User)httpSession.getAttribute("login_user"));
                    if (null == user) {
                        message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                        LOGGER.info("登录超时");
                        return message;
                    } else  {
                        username = user.getUserName();
                    }
                } catch (Exception e) {
                    message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                    LOGGER.info("登录超时");
                    return message;
                }
                LOGGER.info("---possessions {}",exispossessions.toString());
                exispossessions.setPossessionsId(possessionsDto.getPossessionsId());
                exispossessions.setStatus(possessionsDto.getStatus());
                exispossessions.setModifyTime(new Date());
                exispossessions.setModifyName(username);
                possessionsRepository.save(exispossessions);
                message.setData(exispossessions);
            } else {
                message = new BaseMessage(MessageCode.NO_RESPONSE);
                message.setMsg("该涉案财物不存在");
                LOGGER.info("该涉案财物不存在");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            message = new BaseMessage(MessageCode.FAILED);
            message.setMsg("涉案财物修改异常");
            LOGGER.info("涉案财物修改异常");
        }
        return message;
    }
    
    
    /**
     * 二维码中显示的文字
     * @param fixedassetsDto
     * @return
     */
    public String toLongString(PossessionsDto possessionsDto) {
        String str = possessionsDto.getPossessionsNo() +";涉案财物信息 [ 名称=" + possessionsDto.getPossessionsName() + "]";
        LOGGER.info("二维码中的信息：{}",str);
        return str;
    }
    
    /**
     * 查询出最后修改时间已经大于半年了，且已经移交的收缴涉案财物
     * @return
     */
    public List<Possessions> getPossessionsStaut_7() {
        return possessionsRepository.getPossessionsStaut_7();
    }
    
}
