package com.erp.service.impl;

import java.util.ArrayList;
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
import com.erp.dto.CaseDto;
import com.erp.dto.MsgDto;
import com.erp.dto.RecordDto;
import com.erp.dto.SuspectDto;
import com.erp.dto.filter.CaseFilterDto;
import com.erp.orm.domain.Case;
import com.erp.orm.domain.CaseList;
import com.erp.orm.domain.Suspect;
import com.erp.orm.domain.User;
import com.erp.orm.repository.CaseRepository;
import com.erp.orm.repository.SuspectRepository;
import com.erp.response.BaseMessage;
import com.erp.response.CaseResult;
import com.erp.response.MessageCode;
import com.erp.service.specification.CaseSpecification;
import com.erp.service.specification.SuspectSpecification;
import com.erp.util.CreateImg;
import com.erp.util.StringUtil;
import com.erp.util.date.DateUtil;
@Service
public class CaseService {

	@Autowired
	private CaseRepository caseRepository;

    @Autowired
    private MsgService msgService;
	@Autowired
    private RecordService recordService;
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private Config config ;
	@Autowired
    private HttpSession httpSession;
	@Autowired
	private SuspectRepository suspectRepository;
	/***
	 * 添加
	 * @param equipment
	 * @return
	 */
	@Transactional
	public BaseMessage saveCase(CaseDto caseDto) {
		BaseMessage message = null;
		User user = null;
		try {
			Case isCase = findByCaseNo(caseDto.getCaseNo());
			user = (User)httpSession.getAttribute("login_user");
			if (null != user) {
			    if (null == isCase) {
	                Case cases = new Case();
	                message = new BaseMessage(MessageCode.SUCCESSED);
	                BeanUtils.copyProperties(caseDto, cases);
	                cases.setModifyTime(new Date());
	                cases.setModifyName(user.getUserName());
	                String url =  config.getQrcode() + caseDto.getCaseNo() + Constant.IMG_SUFFIX;
	                try {
//	                    QrCodeUtil.encode(toLongString(caseDto),url);
	                    CreateImg.create(url, toLongString(caseDto));
	                    logger.info("----------二维码地址 - {}", url);
	                    cases.setQrcode(caseDto.getCaseNo()+ Constant.IMG_SUFFIX);
	                    logger.info("二维码生成 qrcode:{} 成功",caseDto.getCaseNo() + Constant.IMG_SUFFIX);
	                } catch (Exception e) {
	                    message = new BaseMessage(MessageCode.QRCODE_ERROR);
	                }
	                cases = caseRepository.save(cases);
	                logger.info("新增案件 id:{} 成功", cases.getCaseId());
	            } else {
	                message = new BaseMessage(MessageCode.ASSETSNO_NOT_NULL);
	                logger.info("新增案卷编号已存在");
	            }
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("新增异常");
			message = new BaseMessage(MessageCode.ASSETSNO_NOT_NULL);
		}
		return message;
	}
	
	/***
     * 新页面添加
     * @param equipment
     * @return
     */
    @Transactional
    public BaseMessage insert(CaseDto caseDto) {
        BaseMessage message = null;
        User user = null;
        try {
            Case isCase = findByCaseNo(caseDto.getCaseNo());
            user = (User)httpSession.getAttribute("login_user");
            if (null != user) {
                if (null == isCase) {
                    Case cases = new Case();
                    message = new BaseMessage(MessageCode.SUCCESSED);
                    // 时间格式化转换
                    BeanUtils.copyProperties(caseDto, cases);
                    if (!StringUtils.isEmpty(caseDto.getCaseTime()))
                        cases.setCaseTime(DateUtil.parse(caseDto.getCaseTime(), DateUtil.YYYYMMDDHHMMSS));
                    if (!StringUtils.isEmpty(caseDto.getStartTime()))
                        cases.setStartTime(DateUtil.parse(caseDto.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
                    if (!StringUtils.isEmpty(caseDto.getEndTime()))
                        cases.setEndTime(DateUtil.parse(caseDto.getEndTime(), DateUtil.YYYYMMDDHHMMSS));
                    cases.setModifyTime(new Date());
                    cases.setModifyName(user.getUserName());
                    
                    //设置案件默认值
                    //EndcaseStatus结案状态1-未结案-2结案审核中3-已结案    案件状态为已结案,结案状态则为已结案
                    if (!caseDto.getCaseStatus().equals(Constant.CASESTATUS_1)) {
                        cases.setEndcaseStatus(Constant.ENDCAST_STATUS_3);
                    } else {
                        cases.setEndcaseStatus(Constant.ENDCAST_STATUS_1);
                    }
                    //是否子案件1是2否
                    cases.setSonStatus(Constant.SON_STATUS_2);
                    //是否已经督案0未督案1已经督案
                    cases.setIsCaseList(Constant.ISCASELIST_O);
                    
                    String url =  config.getQrcode() + caseDto.getCaseNo() + Constant.IMG_SUFFIX;
                    try {
                        CreateImg.create(url, toLongString(caseDto));
                        logger.info("二维码地址 - {}", url);
                        cases.setQrcode(caseDto.getCaseNo() + Constant.IMG_SUFFIX);
                        logger.info("二维码生成 qrcode:{} 成功",caseDto.getCaseNo() + Constant.IMG_SUFFIX);
                    } catch (Exception e) {
                        message = new BaseMessage(MessageCode.QRCODE_ERROR);
                    }
                    logger.info("新增案件的对象:{}",cases);
                    cases = caseRepository.save(cases);
                    logger.info("新增案件 id:{} 成功", cases.getCaseId());
                    if (null != caseDto.getSuspectDtoList() && caseDto.getSuspectDtoList().size() > 0 && caseDto.getStyle().equals(Constant.STYLE_2)) {
                        for (SuspectDto suspectDto : caseDto.getSuspectDtoList()) {
                            if (!StringUtil.isNullObject(suspectDto,true)) { //isNullObject判断suspectDto中的属性是否为空,或者为null ,true代表数值类型0就是为空的
                                Suspect suspect = new Suspect();
                                BeanUtils.copyProperties(suspectDto, suspect);
                                suspect.setCaseNo(cases.getCaseNo());
                                Suspect sust = suspectRepository.save(suspect);
                                logger.info("新增嫌疑人 id:{} 成功", cases.getCaseId());
                            }
                        }
                    }
                } else {
                    message = new BaseMessage(MessageCode.ASSETSNO_NOT_NULL);
                    logger.info("新增案卷编号已存在");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("insert case exception");
            message = new BaseMessage(MessageCode.FAILED);
        }
        logger.info("case save result is {}",message);
        return message;
    }
	
	
	public String toLongString(CaseDto caseDto) {
//		String caseState = "";
//		if (caseDto.getCaseStatus().equals("1")) {
//			caseState = "未结案";
//		} else if (caseDto.getCaseStatus().equals("2")) {
//			caseState = "已结案";
//		}  else if (caseDto.getCaseStatus().equals("3")) {
//			caseState = "案件撤销";
//		} 
		
		String str = caseDto.getCaseNo() +";案卷信息 [ 案卷名称=" + caseDto.getCaseName()
				+ ", 案发时间=" + caseDto.getCaseTime()
				 + "办案民警="+caseDto.getPoliceName()+ "]";
		return str;
	}
	
	/***
	 * 删除
	 * @param equipment
	 * @return
	 */
	@Transactional
	public BaseMessage delCase(String[] ids) {
		BaseMessage message = null;
        for (String idStr : ids) {
            Integer id = Integer.parseInt(idStr);
            Case isCase = caseRepository.findOne(id);
            if (null != isCase) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                caseRepository.delete(id);
                message.setData(id);
            } else {
                message = new BaseMessage(MessageCode.FAILED);
                message.setMsg("该案件不存在");
                message.setData(id);
                logger.error("该案件 id:{}不存在", id);
            }
        }
        return message;
	}
	
	
	/***
	 * 修改
	 * @param equipment
	 * @return
	 */
	@Transactional
	public BaseMessage updateCase(CaseDto caseDto) {
		BaseMessage message = null;
		String username =  "";
		User user = null;
		try {
		    user = ((User) httpSession.getAttribute("login_user"));
			if (null == user) {
				message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
				logger.error("登录超时了");
				return message;
			} else {
			    username = user.getUserName();
			}
		} catch (Exception e) {
			message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
			logger.error("登录异常，或者超时了");
			return message;
		}
		try {
			Case isCase =  findByCaseNo(caseDto.getCaseNo());
			if (null != isCase) {
				Case cases = new Case();
				message = new BaseMessage(MessageCode.SUCCESSED);
				cases.setCaseNo(caseDto.getCaseNo());
				cases.setCaseAddUrl(caseDto.getCaseAddUrl());
				cases.setEndcaseExplain(caseDto.getEndcaseExplain());
				BeanUtils.copyProperties(cases, caseDto);
				if (!isCase.getFileStatus().equals(caseDto.getFileStatus())) {
					if ((Constant.STATUS_3.equals(caseDto.getFileStatus()) && Constant.STATUS_1.equals(isCase.getFileStatus())) || (Constant.STATUS_1.equals(caseDto.getFileStatus()) && Constant.STATUS_3.equals(isCase.getFileStatus())) ) {
		            	RecordDto recordDto = new RecordDto();
		            	recordDto.setRecordNo(caseDto.getCaseNo());
		                recordDto.setRecordStyle(caseDto.getFileStatus());
		                recordDto.setRecordName(caseDto.getCaseName());
		                recordDto.setRecordTime(DateUtil.parse(new Date(), DateUtil.YYYYMMDDHHMMSS));
		                recordDto.setUseDepartment(caseDto.getRecord_use());
		                recordDto.setAgent(username);
		                recordDto.setUseExplain(caseDto.getRecord_explain());
		                recordDto.setStatus(caseDto.getFileStatus()+"");
		                recordDto.setStyle(Constant.STYLE_3);
		            	recordService.save(recordDto);
		            }
				}
				String url =  config.getQrcode() + caseDto.getCaseNo() + Constant.IMG_SUFFIX;
				try {
//					QrCodeUtil.encode(toLongString(caseDto),url);
				    CreateImg.create(url, toLongString(caseDto));
					logger.info("----------二维码地址 - {}", url);
					cases.setQrcode(caseDto.getCaseNo()+ Constant.IMG_SUFFIX);
					logger.info("二维码生成 qrcode:{} 成功",caseDto.getCaseNo() + Constant.IMG_SUFFIX);
				} catch (Exception e) {
					e.printStackTrace();
					message = new BaseMessage(MessageCode.QRCODE_ERROR);
				}
				cases.setModifyTime(new Date());
				cases.setModifyName(username);
				cases = caseRepository.save(cases);
				logger.info("修改案件 id:{} 成功", cases.getCaseId());
			} else {
				message = new BaseMessage(MessageCode.NO_RESOURCE);
				logger.error("修改的用户不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = new BaseMessage(MessageCode.NO_RESOURCE);
		}
		return message;
	}
	
	/***
     * 修改
     * @param 
     * @return
     */
    @Transactional
    public BaseMessage update(CaseDto caseDto) {
        BaseMessage message = null;
        String username =  "";
        User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
            if (null == user) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                logger.error("登录超时了");
                return message;
            } else {
                username = user.getUserName();
            }
        } catch (Exception e) {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            logger.error("登录异常，或者超时了");
            return message;
        }
        try {
            Case cases =  findByCaseNo(caseDto.getCaseNo());
            if (null != cases) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                cases.setCaseNo(caseDto.getCaseNo());
                cases.setCaseName(caseDto.getCaseName());
                cases.setPoliceLoginName(caseDto.getPoliceLoginName());
                cases.setPoliceName(caseDto.getPoliceName());
                cases.setPossessionsNo(caseDto.getPossessionsNo());
                cases.setPossessionsName(caseDto.getPossessionsName());
                cases.setCaseAddress(caseDto.getCaseAddress());
                cases.setCaseStatus(caseDto.getCaseStatus());
                cases.setStyle(caseDto.getStyle());
                cases.setEndTime(cases.getEndTime());
                cases.setCaseExplain(caseDto.getCaseExplain());
                if (!StringUtils.isEmpty(caseDto.getCaseTime()))
                    cases.setCaseTime(DateUtil.parse(caseDto.getCaseTime(), DateUtil.YYYYMMDDHHMMSS));
                if (!StringUtils.isEmpty(caseDto.getStartTime()))
                    cases.setStartTime(DateUtil.parse(caseDto.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
                if (!StringUtils.isEmpty(caseDto.getEndTime()))
                    cases.setEndTime(DateUtil.parse(caseDto.getEndTime(), DateUtil.YYYYMMDDHHMMSS));
                if (!cases.getFileStatus().equals(caseDto.getFileStatus())) {
                    if ((Constant.STATUS_3.equals(caseDto.getFileStatus()) && Constant.STATUS_1.equals(cases.getFileStatus())) || (Constant.STATUS_1.equals(caseDto.getFileStatus()) && Constant.STATUS_3.equals(cases.getFileStatus())) ) {
                        RecordDto recordDto = new RecordDto();
                        recordDto.setRecordNo(caseDto.getCaseNo());
                        recordDto.setRecordStyle(caseDto.getFileStatus());
                        recordDto.setRecordName(caseDto.getCaseName());
                        recordDto.setRecordTime(DateUtil.parse(new Date(), DateUtil.YYYYMMDDHHMMSS));
                        recordDto.setUseDepartment(caseDto.getRecord_use());
                        recordDto.setAgent(username);
                        recordDto.setUseExplain(caseDto.getRecord_explain());
                        recordDto.setStatus(caseDto.getFileStatus()+"");
                        recordDto.setStyle(Constant.STYLE_3);
                        recordService.save(recordDto);
                    }
                }
                cases.setFileStatus(caseDto.getFileStatus());
                String url =  config.getQrcode() + caseDto.getCaseNo() + Constant.IMG_SUFFIX;
                try {
//                    QrCodeUtil.encode(toLongString(caseDto),url);
                    CreateImg.create(url, toLongString(caseDto));
                    logger.info("----------二维码地址 - {}", url);
                    cases.setQrcode(caseDto.getCaseNo()+ Constant.IMG_SUFFIX);
                    logger.info("二维码生成 qrcode:{} 成功",caseDto.getCaseNo() + Constant.IMG_SUFFIX);
                } catch (Exception e) {
                    e.printStackTrace();
                    message = new BaseMessage(MessageCode.QRCODE_ERROR);
                }
                cases.setModifyTime(new Date());
                cases.setModifyName(username);
                cases = caseRepository.save(cases);
                logger.info("修改案件 id:{} 成功", cases.getCaseId());
                
                if (caseDto.getSuspectDtoList().size() > 0 && caseDto.getStyle().equals(Constant.STYLE_2)) {
                    for (SuspectDto suspectDto : caseDto.getSuspectDtoList()) {
                        if (!StringUtil.isNullObject(suspectDto,true)) { //isNullObject判断suspectDto中的属性是否为空,或者为null ,true代表数值类型0就是为空的
                            Suspect suspect = new Suspect();
                            BeanUtils.copyProperties(suspectDto, suspect);
                            suspect.setCaseNo(cases.getCaseNo());
                            Suspect sust = suspectRepository.save(suspect);
                            logger.info("修改嫌疑人 id:{} 成功", cases.getCaseId());
                        }
                    }
                }
            } else {
                message = new BaseMessage(MessageCode.NO_RESPONSE);
                logger.error("修改的用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = new BaseMessage(MessageCode.FAILED);
            logger.error("修改异常");
        }
        return message;
    }
    
	
	/**
	 * 案件列表
	 * @param caseFilterDto
	 * @param pageable
	 * @return
	 */
	public BaseMessage findCase(CaseFilterDto caseFilterDto,Pageable pageable) {
		BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
		long count = 0;
		try {
		    caseFilterDto.setSonStatus(Constant.SON_STATUS_2);
		    CaseSpecification caseSpecification = new CaseSpecification(caseFilterDto);
	        Page<Case> page = caseRepository.findAll(caseSpecification, pageable);
	        count = caseRepository.count(caseSpecification);
	        baseMessage.setData(page.getContent());
            baseMessage.setMsg(String.valueOf(count));
            logger.info("------案件查询service出参-{}",baseMessage.getData().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
		return baseMessage;
	}
	
	/**
	 * 我的案件信息
	 * @param caseFilterDto
	 * @param pageable
	 * @return
	 */
	public BaseMessage myCaseList(CaseFilterDto caseFilterDto,Pageable pageable) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        long count = 0;
        User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
            if (!StringUtils.isEmpty(user)) {
                caseFilterDto.setPoliceLoginName(user.getLoginName());
                caseFilterDto.setSonStatus(Constant.SON_STATUS_2);
                CaseSpecification caseSpecification = new CaseSpecification(caseFilterDto);
                Page<Case> page = caseRepository.findAll(caseSpecification, pageable);
                count = caseRepository.count(caseSpecification);
                baseMessage.setData(page.getContent());
                baseMessage.setMsg(String.valueOf(count));
            } else {
                logger.info("登录超时------------");
                baseMessage = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            }
        } catch (Exception e) {
            logger.info("登录异常,或者超时------------");
            baseMessage = new BaseMessage(MessageCode.FAILED);
            e.printStackTrace();
        }
        return baseMessage;
    }
	
	/**
     * 审核案件列表
     * @param caseFilterDto
     * @param pageable
     * @return
     */
    public BaseMessage applyCaseList(CaseFilterDto caseFilterDto,Pageable pageable) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        long count = 0;
        User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
            if (!StringUtils.isEmpty(user)) {
                caseFilterDto.setSonStatus(Constant.SON_STATUS_2);
                caseFilterDto.setEndcaseStatus(Constant.ENDCAST_STATUS_2);
                CaseSpecification caseSpecification = new CaseSpecification(caseFilterDto);
                Page<Case> page = caseRepository.findAll(caseSpecification, pageable);
                count = caseRepository.count(caseSpecification);
                baseMessage.setData(page.getContent());
                baseMessage.setMsg(String.valueOf(count));
            } else {
                logger.info("登录超时------------");
                baseMessage = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            }
        } catch (Exception e) {
            logger.info("登录异常,或者超时------------");
            baseMessage = new BaseMessage(MessageCode.FAILED);
            e.printStackTrace();
        }
        return baseMessage;
    }
	
	/**
     * 根据案件编号查询案件
     * 
     * @param username
     * @return
     */
    public Case findByCaseNo(String caseNo) {
        List<Case> list = caseRepository.getCaseByCaseNo(caseNo);
        return list.isEmpty() ? null : list.get(0);
    }
    

	/**
     * 根据案件id查询案件
     * 
     * @param username
     * @return
     */
    public CaseResult findByCaseId(Integer id) {
        CaseResult caseResult = new CaseResult(MessageCode.SUCCESSED);
        CaseDto caseDto = new CaseDto();
        SuspectDto suspectDto = new SuspectDto();
        try {
            logger.info("查询案件");

            //普通案件
            Case cases = caseRepository.findOne(id);
            BeanUtils.copyProperties(cases, caseDto);
            caseDto.setStartTime(DateUtil.parse(cases.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
            caseDto.setEndTime(DateUtil.parse(cases.getEndTime(), DateUtil.YYYYMMDDHHMMSS));
            caseDto.setCaseTime(DateUtil.parse(cases.getCaseTime(), DateUtil.YYYYMMDDHHMMSS));
            
            if (cases.getStyle().equals("2")) {
                //刑事案件
                logger.info("刑事案件就要添加嫌疑人");
              suspectDto.setCaseNo(cases.getCaseNo());
              SuspectSpecification suspectSpecification = new SuspectSpecification(
                      suspectDto);
              List<Suspect> list = suspectRepository
                      .findAll(suspectSpecification);

              List<SuspectDto> listSuspectDto = new ArrayList<SuspectDto>();
              for (Suspect suspect : list) {
                  SuspectDto sutDto = new SuspectDto();
                  BeanUtils.copyProperties(suspect, sutDto);
                  listSuspectDto.add(sutDto);
              }
              caseDto.setSuspectDtoList(listSuspectDto);
            }
            
//            if (cases.getSonStatus().equals("2")) {
//                logger.info("不等于空就代表是普通案件");
//                suspectDto.setCaseNo(cases.getCaseNo());
//                SuspectSpecification suspectSpecification = new SuspectSpecification(
//                        suspectDto);
//                List<Suspect> list = suspectRepository
//                        .findAll(suspectSpecification);
//
//                List<SuspectDto> listSuspectDto = new ArrayList<SuspectDto>();
//                for (Suspect suspect : list) {
//                    SuspectDto sutDto = new SuspectDto();
//                    BeanUtils.copyProperties(suspect, sutDto);
//                    listSuspectDto.add(sutDto);
//                }
//                caseDto.setSuspectDtoList(listSuspectDto);
//            } else {
//                //串并案件
//                logger.info("查询主案件");
//                if (!StringUtils.isEmpty(cases.getCaseNo())) {
//                    List<Case> caseList = caseRepository.findParentNo(cases
//                            .getCaseNo());
//                    List<CaseDto> dtoList = new ArrayList<CaseDto>();
//                    for (Case cs : caseList) {
//                        CaseDto c = new CaseDto();
//                        BeanUtils.copyProperties(cs, c);
//                        dtoList.add(c);
//                    }
//                    caseResult.setList(dtoList);
//                } else {
//                    logger.info("案件编号不存在" + cases.getCaseNo());
//                    caseResult = new CaseResult(MessageCode.PARAMS_ERROR);
//                }
//
//            }
            caseResult.setCaseDto(caseDto);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.info("查询异常");
            caseResult = new CaseResult(MessageCode.FAILED);
        }
        
        return caseResult;
    }
    
    
    /***
     * 结案
     * @param equipment
     * @return
     */
    @Transactional
    public BaseMessage endCase(CaseDto caseDto) {
        BaseMessage message = null;
        String username =  "";
        User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
            if (null == user) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                logger.error("登录超时了");
                return message;
            } else {
                username = user.getUserName();
            }
        } catch (Exception e) {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            logger.error("登录异常，或者超时了");
            return message;
        }
        try {
//            Case cases =  findByCaseNo(caseDto.getCaseNo());
            Case cases = caseRepository.findOne(caseDto.getCaseId());
            String str = "";
            if (null != cases) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                cases.setCaseAddUrl(caseDto.getCaseAddUrl());
                cases.setEndcaseExplain(caseDto.getEndcaseExplain());
                //角色1为民警 则提交案件审核
                if (user.getRoleid().equals("1")) {
                    cases.setEndcaseStatus(Constant.STYLE_2);
                    str = "尊敬的管理员"+username+"同志，您好，由民警"+cases.getPoliceName()+"处理的案件编号为"+cases.getCaseNo()+"的编号正在申请结案,请审核";
                } else if (user.getRoleid().equals("2")) {
                  //角色2为管理员 则结案
                    cases.setCaseStatus(Constant.STYLE_2);
                    cases.setEndTime(new Date());
                    cases.setEndcaseStatus("3");
                    str = "尊敬的管理员"+username+"同志，您好，案件编号为"+cases.getCaseNo()+"的编号已经结案,请及时处理涉案财物的移交，涉案财物编号：【"+cases.getPossessionsNo()+"】";
                }
                cases.setModifyTime(new Date());
                cases.setModifyName(username);
                cases = caseRepository.save(cases);
                logger.info("修改案件 id:{} 成功", cases.getCaseId());
                
                logger.info("添加消息");
                MsgDto msgDto = new MsgDto();
                logger.info(str);
                msgDto.setContent(str);
                msgDto.setStartTime(new Date());
                msgDto.setUserLogin(username);
                msgDto.setType(Constant.MSG_TYPE_1);
                msgService.save(msgDto);
                logger.info("添加提醒消息成功");
                
            } else {
                message = new BaseMessage(MessageCode.NO_RESPONSE);
                logger.error("修改的用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = new BaseMessage(MessageCode.NO_RESPONSE);
        }
        return message;
    }
    
    /***
     * 结案审核通过
     * @param equipment
     * @return
     */
    @Transactional
    public BaseMessage endCaseYes(CaseDto caseDto) {
        BaseMessage message = null;
        String username =  "";
        User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
            if (null == user) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                logger.error("登录超时了");
                return message;
            } else {
                username = user.getUserName();
            }
            if (!user.getRoleid().equals("2")) {
                message = new BaseMessage(MessageCode.INTERFACE_NO_ACCESS);
                logger.error("没有此接口访问权限");
                return message;
            }
        } catch (Exception e) {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            logger.error("登录异常，或者超时了");
            return message;
        }
        try {
            Case cases =  caseRepository.findOne(caseDto.getCaseId());
            if (null != cases) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                cases.setCaseStatus(Constant.STYLE_2);
                cases.setEndcaseStatus("3");
                cases.setEndTime(new Date());
                cases.setModifyTime(new Date());
                cases.setModifyName(username);
                cases = caseRepository.save(cases);
                logger.info("审核案件通过 id:{} 成功", cases.getCaseId());
            } else {
                message = new BaseMessage(MessageCode.NO_RESOURCE);
                logger.error("审核案件的用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = new BaseMessage(MessageCode.NO_RESOURCE);
        }
        return message;
    }
    
    /***
     * 结案审核驳回
     * @param equipment
     * @return
     */
    @Transactional
    public BaseMessage rejectCase(CaseDto caseDto) {
        BaseMessage message = null;
        String username =  "";
        User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
            if (null == user) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                logger.error("登录超时了");
                return message;
            } else {
                username = user.getUserName();
            }
            if (!user.getRoleid().equals("2")) {
                message = new BaseMessage(MessageCode.INTERFACE_NO_ACCESS);
                logger.error("没有此接口访问权限");
                return message;
            }
        } catch (Exception e) {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            logger.error("登录异常，或者超时了");
            return message;
        }
        try {
            Case cases =  caseRepository.findOne(caseDto.getCaseId());
            if (null != cases) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                cases.setEndcaseStatus("1");
                cases.setRejectmsg(caseDto.getRejectmsg());
                cases.setModifyTime(new Date());
                cases.setModifyName(username);
                cases = caseRepository.save(cases);
                logger.info("审核案件驳回 id:{} 成功", cases.getCaseId());
            } else {
                message = new BaseMessage(MessageCode.NO_RESOURCE);
                logger.error("审核案件的用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = new BaseMessage(MessageCode.NO_RESOURCE);
        }
        return message;
    }
    
    /**
     * 
     * 串并案件
     * @param caseDto
     * @return
     */
    @Transactional
    public synchronized BaseMessage merge(CaseDto caseDto) {
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        try {
            
        //校验用户是否登录
        String username =  "";
        User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
            if (null == user) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                logger.error("登录超时了");
                return message;
            } else {
                username = user.getUserName();
            }
        } catch (Exception e) {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            logger.error("登录异常，或者超时了");
            return message;
        }
        
        //查询要串并案件是否有案件串并状态的案件(主案件)
        List<Case> listCase = new ArrayList<Case>();  //此列表是非主案件
        Case one_case = null;//主案件
        int falg = 0;
        for (int i = 0; i < caseDto.getCaseNos().length ; i++ ) {
            String caseNo = caseDto.getCaseNos()[i];
            if (!StringUtils.isEmpty(caseNo)) {
                Case cs = findByCaseNo(caseNo);
                if (!StringUtils.isEmpty(cs)) {
                    if (cs.getCaseStatus().equals("4")) {
                        //caseStatus为4的就是已经串并过的主案件
                        one_case = new Case();
                        logger.info("串并信息中存在合并案件:{}",cs.toString());
                        if (falg > 1) {
                            logger.info("合并案件中不允许存在两笔已合并案件(主案件)");
                            message = new BaseMessage(MessageCode.NOT_CASECASESTATUS);
                            return message;
                        }
                        BeanUtils.copyProperties(cs, one_case);
                        falg = falg + 1;
                    } else {
                        logger.info("串并信息中的普通案件:{}",cs.toString());
                        listCase.add(cs);
                    } 
                } else {
                    logger.info("串并的案件中存在不合法的案件");
                    message = new BaseMessage(MessageCode.NOT_CASENOISNOT);
                    return message;
                }
            }
        }
        
        //插入合并案件信息
        if (null == one_case) {
            /**
             * one_case为空,代表案件提交上来的数据没有主案件,则将基本信息插入案件信息表中
             */
            logger.info("one_case为空开始");
            //添加主案件
            Case insertCase = new Case();
            logger.info("caseDto -- "+caseDto.getCaseNos().toString()+"    "+caseDto.getCaseNo());
            if (!StringUtil.isHave(caseDto.getCaseNos(), caseDto.getCaseNo())){
                message = new BaseMessage(MessageCode.NOT_CASENO);
                logger.error("合并主案件编号必须是子案件编号中的一个");
                return message;
            }
            insertCase.setCaseNo(caseDto.getCaseNo());
            insertCase.setCaseName(caseDto.getCaseName());
            insertCase.setEndTime(DateUtil.parse(caseDto.getEndTime(), DateUtil.YYYYMMDDHHMMSS));
            insertCase.setCaseStatus("4");
            insertCase.setFileStatus(caseDto.getFileStatus());
            insertCase.setCaseExplain(caseDto.getCaseExplain());
            insertCase.setStyle(caseDto.getStyle());
            logger.info("添加主案件开始-----------------------{}",insertCase.toString());
            //设置案件默认值
            insertCase.setEndcaseStatus(Constant.ENDCAST_STATUS_3);
            insertCase.setSonStatus(Constant.SON_STATUS_2);
            insertCase.setIsCaseList(Constant.ISCASELIST_1);
            Case disCase =  caseRepository.save(insertCase);
            logger.info("添加主案件结束-{}",disCase.toString());
            
            //修改成子案件
            for (Case scase : listCase) {
                scase.setSonStatus("1");//修改为子案件
                scase.setParentCaseno(disCase.getCaseNo());//将主案件的编号设置到子案件的父案件编号中   
                scase.setCaseStatus("2");//设置案件状态为已结案
                scase.setEndcaseStatus("3");//设置结案状态为已结案
                scase.setFileStatus(caseDto.getFileStatus());
                logger.info("添加子案件开始-----------------------{}",scase.toString());
                Case sonCase =  caseRepository.save(scase);
                logger.info("添加子案件结束-{}",sonCase.toString());
            }
            logger.info("one_case为空结束");
        } else {
          /**
           * one_case不为空,代表案件提交上来的数据有主案件,则将listCase的数据划分到one_case案件下
           */
            logger.info("one_case不为空开始");
           //修改成子案件
            for (Case scase : listCase) {
                scase.setSonStatus("1");//修改为子案件
                scase.setParentCaseno(one_case.getCaseNo());//将主案件的编号设置到子案件的父案件编号中   
                scase.setCaseStatus("2");//设置案件状态为已结案
                scase.setEndcaseStatus("3");//设置结案状态为已结案
                scase.setFileStatus(caseDto.getFileStatus());
                logger.info("添加子案件开始-----------------------{}",scase.toString());
                Case sonCase =  caseRepository.save(scase);
                logger.info("添加子案件结束-{}",sonCase.toString());
            }
            logger.info("one_case不为空结束");
        }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("串并出现异常了--{}",e);
            message = new BaseMessage(MessageCode.FAILED);
        }
        return message;
    }

    
    /**
     * 根据id查询案件详情
     * @param ids
     * @return
     */
    public CaseResult findCaseDetailById(Integer id) {
        CaseResult caseResult = new CaseResult(MessageCode.SUCCESSED);
        CaseDto caseDto = new CaseDto();
        SuspectDto suspectDto = new SuspectDto();
        List<CaseDto> listCaseDto = null;
        try {
            logger.info("查询案件详情");

            //基本案件信息
            Case cases = caseRepository.findOne(id);
            if (!StringUtils.isEmpty(cases)) {
                BeanUtils.copyProperties(cases, caseDto);
                caseDto.setStartTime(DateUtil.parse(cases.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
                caseDto.setEndTime(DateUtil.parse(cases.getEndTime(), DateUtil.YYYYMMDDHHMMSS));
                caseDto.setCaseTime(DateUtil.parse(cases.getCaseTime(), DateUtil.YYYYMMDDHHMMSS));
               
                /**
                 *案件状态不是串并案件 
                 */
                if  (!cases.getCaseStatus().equals(Constant.CASESTATUS_4)) {
                    /**
                     * 判断是刑事案件,才去取案件嫌疑人
                     */
                    if (cases.getStyle().equals("2")) {
                        //刑事案件
                        logger.info("刑事案件就要添加嫌疑人");
                      suspectDto.setCaseNo(cases.getCaseNo());
                      SuspectSpecification suspectSpecification = new SuspectSpecification(suspectDto);
                      List<Suspect> list = suspectRepository.findAll(suspectSpecification);
                      List<SuspectDto> listSuspectDto = new ArrayList<SuspectDto>();
                      for (Suspect suspect : list) {
                          SuspectDto sutDto = new SuspectDto();
                          BeanUtils.copyProperties(suspect, sutDto);
                          listSuspectDto.add(sutDto);
                      }
                      caseDto.setSuspectDtoList(listSuspectDto);
                    }
                } else {
                   /**
                    * else 里面代表案件状态是串并案件
                    */
                    logger.info("案件状态为串并状态");
                    /**
                     * 根据串并案件编号查询子案件
                     */
                    List<Case>  lists = caseRepository.findParentNo(cases.getCaseNo());
                    listCaseDto = new ArrayList<CaseDto>();
                    for  (Case sonCase : lists ) {
                        /**
                         * 取出子案件并封装到dto对象中去
                         */
                        CaseDto soncaseDto = new CaseDto();
                        BeanUtils.copyProperties(sonCase, soncaseDto);
                        soncaseDto.setStartTime(DateUtil.parse(sonCase.getStartTime(), DateUtil.YYYYMMDDHHMMSS));
                        soncaseDto.setEndTime(DateUtil.parse(sonCase.getEndTime(), DateUtil.YYYYMMDDHHMMSS));
                        soncaseDto.setCaseTime(DateUtil.parse(sonCase.getCaseTime(), DateUtil.YYYYMMDDHHMMSS));
                        
                        /**
                         * 取子案件的嫌疑人,判断是刑事案件,才去取案件嫌疑人
                         */
                        if (sonCase.getStyle().equals("2")) {
                            //刑事案件
                          logger.info("子案件-刑事案件就要添加嫌疑人");
                          suspectDto.setCaseNo(sonCase.getCaseNo());
                          SuspectSpecification suspectSpecification = new SuspectSpecification(suspectDto);
                          List<Suspect> list = suspectRepository.findAll(suspectSpecification);
                          List<SuspectDto> listSuspectDto = new ArrayList<SuspectDto>();
                          for (Suspect suspect : list) {
                              SuspectDto sutDto = new SuspectDto();
                              BeanUtils.copyProperties(suspect, sutDto);
                              listSuspectDto.add(sutDto);
                          }
                          soncaseDto.setSuspectDtoList(listSuspectDto);
                        }
                        /**
                         * 将子案件添加到list
                         */
                        listCaseDto.add(soncaseDto);
                    }
                }
                caseResult.setList(listCaseDto);
                caseResult.setCaseDto(caseDto);
            } else {
                caseResult = new CaseResult(MessageCode.NO_RESPONSE);
                logger.info("没有查询到结果");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.info("查询异常");
            caseResult = new CaseResult(MessageCode.FAILED);
        }
        
        return caseResult;
    }
    
    
    /***
     * 修改状态
     * @param 
     * @return
     */
    @Transactional
    public BaseMessage updateStatus(CaseDto caseDto) {
        BaseMessage message = null;
        String username =  "";
        User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
            if (null == user) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                logger.error("登录超时了");
                return message;
            } else {
                username = user.getUserName();
            }
        } catch (Exception e) {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            logger.error("登录异常，或者超时了");
            return message;
        }
        try {
            Case cases =  caseRepository.findOne(caseDto.getCaseId());
            if (null != cases) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                cases.setCaseId(caseDto.getCaseId());
                cases.setFileStatus(caseDto.getFileStatus());
                caseRepository.save(cases);
            } else {
                message = new BaseMessage(MessageCode.NO_RESPONSE);
                logger.error("修改的用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = new BaseMessage(MessageCode.FAILED);
            logger.error("修改异常");
        }
        return message;
    }
    
    /**
     * 入库时间减去当前时间等于-22的代表着入库至今已经有23三天了
     * 查出未结案的行政案件在第23天通知民警
     * @return
     */
    public List<Case> getEndCase_23() {
        return caseRepository.getEndCase_23();
    }
    
}
