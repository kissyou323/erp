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
import com.erp.dto.FixedassetsDto;
import com.erp.dto.RecordDto;
import com.erp.dto.filter.FixedassetsFilterDto;
import com.erp.orm.domain.Fixedassets;
import com.erp.orm.domain.User;
import com.erp.orm.repository.FixedassetsRepository;
import com.erp.response.BaseMessage;
import com.erp.response.MessageCode;
import com.erp.service.specification.FixedAssetsSpecification;
import com.erp.util.CreateImg;
import com.erp.util.date.DateUtil;

/**
 * 
 * @author liuyang
 *
 */
@Service
public class FixedassetsService {

    private Logger LOGGER = LoggerFactory.getLogger(FixedassetsService.class);

    @Autowired
    private FixedassetsRepository fixRepository;
    @Autowired
    private RecordService recordService;
    @Autowired
	private HttpSession httpSession;
	@Autowired
	private Config config ;
    /**
     * 增加
     * 
     * @param fixedassetsDto
     * @return
     */
    @Transactional
    public BaseMessage saveFixed(FixedassetsDto fixedassetsDto) {
    	LOGGER.info("-------------固定资产service 入参：",fixedassetsDto);
        Fixedassets existFixedAssets = findByAssetsNo(fixedassetsDto.getAssetsNo());
        BaseMessage message = null;
        try {
        	if (null == existFixedAssets) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                Fixedassets fixedassets = new Fixedassets();
                Date now = new Date();
                BeanUtils.copyProperties(fixedassetsDto, fixedassets);
                fixedassets.setStartTime(DateUtil.parse(fixedassetsDto.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
                String username = "";
               try {
    				 username = ((User)httpSession.getAttribute("login_user")).getUserName();
                if (null == username) {
                	message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                	return message;
                }
    			} catch (Exception e) {
    				message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                	return message;
    			}
                fixedassets.setModifyTime(now);
                fixedassets.setModifyName(username);
                LOGGER.info("开始生成二维码---");
                String url = config.getQrcode()+fixedassets.getAssetsNo()+Constant.IMG_SUFFIX;
    			try {
//    				QrCodeUtil.encode(toLongString(fixedassetsDto),url);
    			    CreateImg.create(url, toLongString(fixedassetsDto));
    				LOGGER.info("----------二维码地址 - {}", url);
    				fixedassets.setQrcode(fixedassets.getAssetsNo()+Constant.IMG_SUFFIX);
    				fixedassets.setStatus(Integer.parseInt(fixedassetsDto.getStatus()));
    				LOGGER.info("二维码生成 qrcode:{} 成功", fixedassets.getAssetsNo()+Constant.IMG_SUFFIX);
    			} catch (Exception e) {
    				message = new BaseMessage(MessageCode.QRCODE_ERROR);
    				return message;
    			}
                Fixedassets fixe = fixRepository.save(fixedassets);
                message.setData(fixe);
                LOGGER.info("新增固定资产 id:{} 成功", fixe.getAssetsId());
            } else {
                message = new BaseMessage(MessageCode.ASSETSNO_NOT_NULL);
            }
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("新增异常");
            message = new BaseMessage(MessageCode.FAILED);
		}
        return message;
    }
    /**
     * 二维码中显示的文字
     * @param fixedassetsDto
     * @return
     */
	public String toLongString(FixedassetsDto fixedassetsDto) {
//		String state = "";
//		if (fixedassetsDto.getStatus().equals("1")) {
//			state = "库存";
//		} else if (fixedassetsDto.getStatus().equals("2")) {
//			state = "完好";
//		} else if (fixedassetsDto.getStatus().equals("3")) {
//			state = "借出";
//		} else if (fixedassetsDto.getStatus().equals("4")) {
//			state = "报废";
//		} else if (fixedassetsDto.getStatus().equals("5")) {
//			state = "维修检验";
//		} else if (fixedassetsDto.getStatus().equals("6")) {
//			state = "未入库";
//		}
		String str = fixedassetsDto.getAssetsNo() +";固定资产信息 [ 名称=" + fixedassetsDto.getAssetsName() + ", 规格类型="
				+ fixedassetsDto.getStyle() 
				+ ", 保管员=" + fixedassetsDto.getKeeper() + "]";
		LOGGER.info("二维码中的信息：{}",str);
		return str;
	}

    /**
     * 根据编号查询
     * 
     * @param assetsNo
     * @return
     */
    public Fixedassets findByAssetsNo(String assetsNo) {
        List<Fixedassets> list = fixRepository.findByAssetsNo(assetsNo);
        return list.isEmpty() ? null : list.get(0);
    }
    
    /**
     * 根据id查询
     * 
     * @param assetsNo
     * @return
     */
    public FixedassetsDto findByAssetsId(Integer id) {
        FixedassetsDto fixedassetsDto = new FixedassetsDto();
        try {
             Fixedassets fixedassets = fixRepository.findOne(id);
            if (!StringUtils.isEmpty(fixedassets)) {
                BeanUtils.copyProperties(fixedassets, fixedassetsDto);
                fixedassetsDto.setStatus(fixedassets.getStatus()+"");
                if (!StringUtils.isEmpty(fixedassets.getStartTime()))
                    fixedassetsDto.setStartTime(DateUtil.parse(fixedassets.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
                if (!StringUtils.isEmpty(fixedassets.getModifyTime()))
                    fixedassetsDto.setModifyTime(DateUtil.parse(fixedassets.getModifyTime(), DateUtil.YYYYMMDDHHMMSS));
            } else {
                LOGGER.info("资产信息不存在：{}",fixedassets);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("根据ID查询异常");
        }
        return fixedassetsDto;
    }

    /**
     * 修改固定资产
     * 
     * @param fixedassetsDto
     * @return
     */
    @Transactional
    public BaseMessage updatefixed(FixedassetsDto fixedassetsDto) {
    	Fixedassets existFix = fixRepository.findOne(fixedassetsDto.getAssetsId());
        BaseMessage message = null;
        String username = "";
        User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
			if (null == user) {
				message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
				LOGGER.info("login time out");
				return message;
			} else {
			    username = user.getUserName();
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
			return message;
		}
        if (null != existFix) {
            Fixedassets fixedassets = new Fixedassets();
            message = new BaseMessage(MessageCode.SUCCESSED);
            BeanUtils.copyProperties(fixedassetsDto, fixedassets);
            fixedassets.setStartTime(DateUtil.parse(fixedassetsDto.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
            //借出还入操作条件：在库存状态下操作借出，只有借出状态下操作库存
            if ( (Constant.STATUS_3.equals(fixedassetsDto.getStatus()) && Constant.STATUS_1.equals(existFix.getStatus()+"")) || (Constant.STATUS_1.equals(fixedassetsDto.getStatus()) && Constant.STATUS_3.equals(existFix.getStatus()+""))) {
                 RecordDto recordDto = new RecordDto();
                 recordDto.setRecordNo(fixedassetsDto.getAssetsNo());
                 recordDto.setRecordStyle(fixedassetsDto.getStyle());
                 recordDto.setRecordName(fixedassetsDto.getAssetsName());
                 recordDto.setRecordTime(DateUtil.parse(new Date(), DateUtil.YYYYMMDDHHMMSS));
                 recordDto.setUseDepartment(fixedassetsDto.getRecord_use());
                 recordDto.setAgent(username);
                 recordDto.setUseExplain(fixedassetsDto.getRecord_explain());
                 recordDto.setStatus(fixedassetsDto.getStatus()+"");
                 recordDto.setStyle(Constant.STYLE_1);
             	recordService.save(recordDto);
             }
            LOGGER.info("开始生成二维码---");
            String url = config.getQrcode() + fixedassets.getAssetsNo() + Constant.IMG_SUFFIX;
			try {
//				QrCodeUtil.encode(toLongString(fixedassetsDto),url);
			    CreateImg.create(url, toLongString(fixedassetsDto));
				LOGGER.info("----------二维码地址 - {}", url);
				fixedassets.setQrcode(fixedassets.getAssetsNo()+Constant.IMG_SUFFIX);
				fixedassets.setModifyName(username);
				fixedassets.setStatus(Integer.parseInt(fixedassetsDto.getStatus()));
				fixedassets.setModifyTime(new Date());
				LOGGER.info("二维码生成 qrcode:{} 成功", fixedassets.getAssetsNo()+Constant.IMG_SUFFIX);
			} catch (Exception e) {
				message = new BaseMessage(MessageCode.QRCODE_ERROR);
				return message;
			}
            existFix = fixRepository.save(fixedassets);
            message.setData(existFix);
        } else {
            message = new BaseMessage(MessageCode.NO_RESPONSE);
            message.setMsg("该固定资产不存在");
        }
        return message;
    }

    /**
     * 删除固定资产
     * 
     * @param fixedassetsDto
     * @return
     */
    @Transactional
    public BaseMessage delfixed(String[] ids) {
        BaseMessage message = null;
        for (String idStr : ids) {
            Integer id = Integer.parseInt(idStr);
            Fixedassets fixedassets = fixRepository.findOne(id);
            if (null != fixedassets) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                fixRepository.delete(id);
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
     * 删除固定资产
     * @param id
     * @return
     */
    public BaseMessage delete(String id) {
        BaseMessage message = null;
        if (!StringUtils.isEmpty(id)) {
            User user = ((User) httpSession.getAttribute("login_user"));
            Integer assetsNo = Integer.parseInt(id);
            if (null != user) {
                Fixedassets fixedassets = fixRepository.findOne(assetsNo);
                if (null != fixedassets) {
                    message = new BaseMessage(MessageCode.SUCCESSED);
                    fixRepository.delete(assetsNo);
                    message.setData(assetsNo);
                } else {
                    message = new BaseMessage(MessageCode.NO_RESPONSE);
                    message.setMsg("该固定资产不存在");
                    message.setData(assetsNo);
                    LOGGER.error("该固定 id:{}不存在", id);
                }
            } else {
                LOGGER.info("----------登录超时");
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                return message;
            }
        } else {
            LOGGER.info("----------请求id为空");
            message = new BaseMessage(MessageCode.FAILED);
            return message;
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
    public BaseMessage findfixedasset(FixedassetsFilterDto filterDto,Pageable pageable) {
		BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
		Page<Fixedassets> page = null;
		List<FixedassetsDto> list = new ArrayList<FixedassetsDto>();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long count = 0;
		try {
			FixedAssetsSpecification fixedAssetsSpecification = new FixedAssetsSpecification(filterDto);
			page = fixRepository.findAll(fixedAssetsSpecification, pageable);
			count = fixRepository.count(fixedAssetsSpecification);
			for (Fixedassets fts : page.getContent()) {
			    FixedassetsDto fixedassetsDto = new FixedassetsDto();
			    BeanUtils.copyProperties(fts, fixedassetsDto);
			    Calendar calendar = Calendar.getInstance();
			    if (null != fts.getStartTime()) {
			        calendar.setTimeInMillis(fts.getStartTime().getTime());
	                fixedassetsDto.setStartTime(formatter.format(calendar.getTime()));
			    }
			    if (null != fts.getModifyTime()) {
                    calendar.setTimeInMillis(fts.getModifyTime().getTime());
                    fixedassetsDto.setModifyTime(formatter.format(calendar.getTime()));
                }
			    fixedassetsDto.setStatus(fts.getStatus()+"");
			    list.add(fixedassetsDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		baseMessage.setData(list);
		baseMessage.setMsg(String.valueOf(count));
		return baseMessage;
	}
    /**
     * 修改固定资产状态
     * @param fixedassetsDto
     * @return
     */
    @Transactional
    public BaseMessage updateforStatus(FixedassetsDto fixedassetsDto) {
        Fixedassets fixedassets = fixRepository.findOne(fixedassetsDto.getAssetsId());
        BaseMessage message = null;
        String username = "";
        try {
            username = ((User) httpSession.getAttribute("login_user")).getUserName();
            if (null == username) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                return message;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            return message;
        }
        if (null != fixedassets) {
            message = new BaseMessage(MessageCode.SUCCESSED);
//            LOGGER.info("开始生成二维码---");
//            String url =  config.getQrcode() + fixedassets.getAssetsNo() + Constant.IMG_SUFFIX;
//            try {
//                fixedassets.setQrcode(fixedassets.getAssetsNo()+Constant.IMG_SUFFIX);
                fixedassets.setModifyName(username);
                fixedassets.setStatus(Integer.parseInt(fixedassetsDto.getStatus()));
                fixedassets.setModifyTime(new Date());
                BeanUtils.copyProperties(fixedassets, fixedassetsDto);
////                QrCodeUtil.encode(toLongString(fixedassetsDto),url);
//                CreateImg.create(url, toLongString(fixedassetsDto));
//                LOGGER.info("----------fixedassets - {}", fixedassets.toString());
//                LOGGER.info("----------fixedassetsDto - {}", fixedassetsDto.toString());
//                LOGGER.info("----------二维码地址 - {}", url);
//                LOGGER.info("二维码生成 qrcode:{} 成功", fixedassets.getAssetsNo()+Constant.IMG_SUFFIX);
//            } catch (Exception e) {
//                message = new BaseMessage(MessageCode.QRCODE_ERROR);
//            }
            fixedassets = fixRepository.save(fixedassets);
            message.setData(fixedassets);
        } else {
            message = new BaseMessage(MessageCode.NO_RESPONSE);
            message.setMsg("该固定资产不存在");
        }
        return message;
    }
}
