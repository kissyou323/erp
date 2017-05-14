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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.erp.constant.Constant;
import com.erp.dto.CaseListDto;
import com.erp.dto.MsgDto;
import com.erp.orm.domain.Case;
import com.erp.orm.domain.CaseList;
import com.erp.orm.domain.User;
import com.erp.orm.repository.CaseListRepository;
import com.erp.orm.repository.CaseRepository;
import com.erp.orm.repository.UserRepository;
import com.erp.response.BaseMessage;
import com.erp.response.MessageCode;
import com.erp.service.specification.CaseListSpecification;
import com.erp.util.date.DateUtil;

@Service
public class CaseListService {

	private Logger logger = LoggerFactory.getLogger(CaseListService.class);

	@Autowired
	private CaseListRepository caseListRepository;
	@Autowired
    private CaseRepository caseRepository;
	
	@Autowired
    private MsgService msgService;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private UserRepository userRepository;
	/**
	 * 新增督案单
	 * 
	 * @param msgDto
	 * @return
	 */
	@Transactional
	public BaseMessage save(CaseListDto caseListDto) {
	    BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
		String username = "";
		User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
            if (null == user) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                logger.info("登录超时");
                return message;
            } else {
                username = user.getLoginName();
            }
        } catch (Exception e) {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            logger.info("登录超时");
            return message;
        }
		try {
		    List<User> u = userRepository.findByUsername(caseListDto.getAudit());
		    if (u.size() <= 0 || !u.get(0).getRoleid().equals("3")) {
		        logger.info("您输入的审核人不是所内领导");
		        message = new BaseMessage(MessageCode.PARAMS_ERROR);
		        return message;
		    }
		    CaseList caseList = caseListRepository.findByCaseid(caseListDto.getCase_id());
		    if (caseList == null) {
		        if (user.getRoleid().equals("2")) {
		            //管理员
		            logger.info("管理员添加督案单消息成功");
                    caseList = new CaseList();
                    message = new BaseMessage(MessageCode.SUCCESSED);
                    BeanUtils.copyProperties(caseListDto, caseList);
                    caseList.setCase_id(Integer.parseInt(caseListDto.getCase_id()));
                    caseList.setSuggest(caseListDto.getSuggest());
                    caseList.setCaseendTime(DateUtil.parse(caseListDto.getCaseendTime(), DateUtil.YYYYMMDDHHMMSS));
                    caseList.setAudit(caseListDto.getAudit());
                    caseList.setCaselistStatus("1"); //案件督案单
                    caseList.setState("1");//直接将状态赋值成审核
                    logger.info("添加督案单caseListDto -- {}" ,caseListDto);
                    logger.info("添加督案单caseList -- {}" ,caseList);
                    caseListRepository.save(caseList);
                    logger.info("添加督案单成功");
		        } else if (user.getRoleid().equals("3")) {
		            logger.info("所内领导添加督案单消息成功");
		            caseList = new CaseList();
	                message = new BaseMessage(MessageCode.SUCCESSED);
	                BeanUtils.copyProperties(caseListDto, caseList);
	                caseList.setCase_id(Integer.parseInt(caseListDto.getCase_id()));
	                caseList.setSuggest(caseListDto.getSuggest());
	                caseList.setAudit(username);
	                caseList.setCaselistStatus("1"); //案件督案单
	                caseList.setState("2");//直接将状态赋值成正在执行
	                caseList.setCaseendTime(DateUtil.parse(caseListDto.getCaseendTime(), DateUtil.YYYYMMDDHHMMSS));
	                logger.info("添加督案单caseListDto -- {}" ,caseListDto);
	                logger.info("添加督案单caseList -- {}" ,caseList);
	                
	                if (!StringUtils.isEmpty(caseListDto.getCase_id())) {
	                    Case cases = caseRepository.findOne(Integer.parseInt(caseListDto.getCase_id()));
	                    cases.setIsCaseList("1");//设置已经督案
	                    caseRepository.save(cases);
	                    logger.info("设置已经督案");
	                }
	                
	                MsgDto msgDto = new MsgDto();
	                String str = "尊敬的"+caseList.getPoliceName()+"同志，您好，您的案件编号为"+caseList.getCaseNo()+"由"+ caseList.getAudit() +"新增了督案单。";
	                str += "执法建议是:"+caseList.getSuggest() + "， 请及时处理,谢谢！！";
	                logger.info(str);
	                msgDto.setContent(str);
	                msgDto.setStartTime(new Date());
	                msgDto.setUserLogin(caseList.getPoliceName());
	                msgDto.setType(Constant.MSG_TYPE_2);
	                
	                caseListRepository.save(caseList);
	                logger.info("添加督案单成功");
	                msgService.save(msgDto);
	                logger.info("添加督案单提醒消息成功");
		        } else {
		            message = new BaseMessage(MessageCode.INTERFACE_NO_ACCESS);
	                logger.error("没有此接口访问权限");
	                return message;
		        }
		    } else {
		        logger.info(caseListDto.getCaselistNo() + "督案单已存在");
		        message = new BaseMessage(MessageCode.CASELISTNOREAPERT);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    logger.info("添加督案单异常");
			message = new BaseMessage(MessageCode.FAILED);
		}
		return message;
	}
	
	
	/**
     * 新增督案单消息
     * 
     * @param msgDto
     * @return
     */
    @Transactional
    public BaseMessage saveCaseListMsg(CaseListDto caseListDto) {
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        String username = "";
        User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
            if (null == user) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                logger.info("登录超时");
                return message;
            } else {
                username = user.getLoginName();
            }
        } catch (Exception e) {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            logger.info("登录超时");
            return message;
        }
        try {
            List<User> u = userRepository.findByUsername(caseListDto.getAudit());
            if (u.size() <= 0 || !u.get(0).getRoleid().equals("3")) {
                logger.info("您输入的审核人不是所内领导");
                message = new BaseMessage(MessageCode.PARAMS_ERROR);
                return message;
            }
            CaseList caseList = caseListRepository.findByCaseListNo(caseListDto.getCaselistNo());
            if (caseList == null) {
                if (user.getRoleid().equals("2")) {
                    //管理员
                    logger.info("管理员添加督案单消息成功");
                    caseList = new CaseList();
                    message = new BaseMessage(MessageCode.SUCCESSED);
                    BeanUtils.copyProperties(caseListDto, caseList);
                    caseList.setCase_id(Integer.parseInt(caseListDto.getCase_id()));
                    caseList.setSuggest(caseListDto.getSuggest());
                    caseList.setCaseendTime(DateUtil.parse(caseListDto.getCaseendTime(), DateUtil.YYYYMMDDHHMMSS));
                    caseList.setAudit(caseListDto.getAudit());
                    caseList.setCaselistStatus("2"); //案件督案单消息
                    caseList.setState("1");//直接将状态赋值成审核
                    logger.info("添加督案单caseListDto -- {}" ,caseListDto);
                    logger.info("添加督案单caseList -- {}" ,caseList);
                    caseListRepository.save(caseList);
                    logger.info("添加督案单成功");
                } else if (user.getRoleid().equals("3")) {
                    logger.info("所内领导添加督案单消息成功");
                    caseList = new CaseList();
                    message = new BaseMessage(MessageCode.SUCCESSED);
                    BeanUtils.copyProperties(caseListDto, caseList);
                    caseList.setCase_id(Integer.parseInt(caseListDto.getCase_id()));
                    caseList.setSuggest(caseListDto.getSuggest());
                    caseList.setAudit(username);
                    caseList.setCaselistStatus("2");  //案件督案单消息
                    caseList.setState("2");//直接将状态赋值成正在执行
                    caseList.setCaseendTime(DateUtil.parse(caseListDto.getCaseendTime(), DateUtil.YYYYMMDDHHMMSS));
                    logger.info("添加督案单caseListDto -- {}" ,caseListDto);
                    logger.info("添加督案单caseList -- {}" ,caseList);
                    
                    MsgDto msgDto = new MsgDto();
                    String str = "尊敬的"+caseList.getPoliceName()+"同志，您好，您的案件编号为"+caseList.getCaseNo()+"由"+ caseList.getAudit() +"新增了督案单。";
                    str += "执法建议是:"+caseList.getSuggest() + "， 请及时处理,谢谢！！";
                    logger.info(str);
                    msgDto.setContent(str);
                    msgDto.setStartTime(new Date());
                    msgDto.setUserLogin(caseList.getPoliceName());
                    msgDto.setType(Constant.MSG_TYPE_2);
                    
                    caseListRepository.save(caseList);
                    logger.info("添加督案单成功");
                    msgService.save(msgDto);
                    logger.info("添加督案单提醒消息成功");
                } else {
                    message = new BaseMessage(MessageCode.INTERFACE_NO_ACCESS);
                    logger.error("没有此接口访问权限");
                    return message;
                }
            } else {
                logger.info(caseListDto.getCaselistNo() + "督案单消息已存在");
                message = new BaseMessage(MessageCode.ASSETSNO_NOT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("添加督案单异常");
            message = new BaseMessage(MessageCode.FAILED);
        }
        return message;
    }
    
    /**
     * 通过审核督案消息
     * @param id
     * @return
     */
    public BaseMessage apllyMsgYes(String id){
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        String username = "";
        User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
            if (null == user) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                logger.info("登录超时");
                return message;
            } else {
                username = user.getLoginName();
            }
        } catch (Exception e) {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            logger.info("登录超时");
            return message;
        }
        if (!StringUtils.isEmpty(id)) {
            CaseList caseList = caseListRepository.findOne(Integer.parseInt(id));
            caseList.setState("2");//设置督案单通过审核,进入民警处理状态
            logger.info("督案单消息 --设置案件通过审核,进入民警处理状态",caseList);
            MsgDto msgDto = new MsgDto();
            String str = "尊敬的"+caseList.getPoliceName()+"同志，您好，您的案件编号为"+caseList.getCaseNo()+"由"+ caseList.getAudit() +"新增了督案单消息。";
            str += "执法建议是:"+caseList.getSuggest() + "， 请及时处理,谢谢！！";
            logger.info(str);
            msgDto.setContent(str);
            msgDto.setStartTime(new Date());
            msgDto.setUserLogin(caseList.getPoliceName());
            msgDto.setType(Constant.MSG_TYPE_2);
            logger.info("添加督案单生成消息-{}",msgDto);
            
            caseListRepository.save(caseList);
            logger.info("添加督案单消息成功");
            msgService.save(msgDto);
            logger.info("添加督案单消息提醒消息成功");
            
            Case cases = caseRepository.findOne(caseList.getCase_id());
            cases.setIsCaseList("1");//设置已经督案
            caseRepository.save(cases);
            logger.info("设置已经督案");
        } else {
            logger.info("督案单不存在");
            message = new BaseMessage(MessageCode.NO_RESPONSE);
        }
       
        return message;
    }
	
	
	/**
	 * 通过审核督案单
	 * @param id
	 * @return
	 */
	public BaseMessage apllyYes(String id){
	    BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        String username = "";
        User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
            if (null == user) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                logger.info("登录超时");
                return message;
            } else {
                username = user.getLoginName();
            }
        } catch (Exception e) {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            logger.info("登录超时");
            return message;
        }
        if (!StringUtils.isEmpty(id)) {
            CaseList caseList = caseListRepository.findOne(Integer.parseInt(id));
            caseList.setState("2");//设置督案单通过审核,进入民警处理状态
            logger.info("设置案件通过审核,进入民警处理状态",caseList);
            MsgDto msgDto = new MsgDto();
            String str = "尊敬的"+caseList.getPoliceName()+"同志，您好，您的案件编号为"+caseList.getCaseNo()+"由"+ caseList.getAudit() +"新增了督案单。";
            str += "执法建议是:"+caseList.getSuggest() + "， 请及时处理,谢谢！！";
            logger.info(str);
            msgDto.setContent(str);
            msgDto.setStartTime(new Date());
            msgDto.setUserLogin(caseList.getPoliceName());
            msgDto.setType(Constant.MSG_TYPE_2);
            logger.info("添加督案单生成消息-{}",msgDto);
            
            Case cases = caseRepository.findOne(caseList.getCase_id());
            cases.setIsCaseList("1");//设置已经督案
            caseRepository.save(cases);
            logger.info("设置已经督案--{}",cases);
            
            caseListRepository.save(caseList);
            logger.info("添加督案单成功");
            msgService.save(msgDto);
            logger.info("添加督案单提醒消息成功");
        } else {
            logger.info("督案单不存在");
            message = new BaseMessage(MessageCode.NO_RESPONSE);
        }
       
        return message;
	}
	
	/**
	 * 驳回督案单
	 * @param id
	 * @return
	 */
	public BaseMessage apllyNo(String id){
	    BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        String username = "";
        User user = null;
        try {
            user = ((User) httpSession.getAttribute("login_user"));
            if (null == user) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                logger.info("登录超时");
                return message;
            } else {
                username = user.getLoginName();
            }
        } catch (Exception e) {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            logger.info("登录超时");
            return message;
        }
        
        if (!StringUtils.isEmpty(id)) {
            CaseList caseList = caseListRepository.findOne(Integer.parseInt(id));
            caseList.setState("5");//设置督案单驳回,进入管理员重新提交审核状态
            caseListRepository.save(caseList);
            logger.info("驳回督案单成功");
        } else {
            logger.info("督案单不存在");
            message = new BaseMessage(MessageCode.NO_RESPONSE);
        }
        
        return message;
	}
	
	/**
	 * 民警已完成
	 * @param id
	 * @return
	 */
	 public BaseMessage caselistOk(String id) {
	     BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
	        String username = "";
	        User user = null;
	        try {
	            user = ((User) httpSession.getAttribute("login_user"));
	            if (null == user) {
	                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
	                logger.info("登录超时");
	                return message;
	            } else {
	                username = user.getLoginName();
	            }
	        } catch (Exception e) {
	            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
	            logger.info("登录超时");
	            return message;
	        }
	        if (!StringUtils.isEmpty(id)) {
	            CaseList caseList = caseListRepository.findOne(Integer.parseInt(id));
	            caseList.setState("3");//设置督案单完成,进入管理员重新提交审核状态
	            caseListRepository.save(caseList);
	            logger.info("完成督案单消息成功");
	        } else {
	            logger.info("督案单不存在");
	            message = new BaseMessage(MessageCode.NO_RESPONSE);
	        }
	        return null;
	    }
	
	/**
	 * 修改
	 * @param caseListDto
	 * @return
	 */
	@Transactional
    public BaseMessage update(CaseListDto caseListDto) {
        BaseMessage message = null;
        try {
            CaseList caseList = caseListRepository.findByCaseListNo(caseListDto.getCaselistNo());
            if (caseList != null) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                BeanUtils.copyProperties(caseListDto, caseList);
                caseListRepository.save(caseList);
                logger.info("修改督案单成功");
            } else {
                logger.info(caseListDto.getCaselistNo() + "督案单不存在");
            }
        } catch (Exception e) {
            message = new BaseMessage(MessageCode.FAILED);
        }
        return message;
    }

	/**
	 * 民警的我的督案单列表
	 * 
	 * @param userLogin
	 * @param type
	 * @return
	 */
	public BaseMessage findCaseListByUserLogin(CaseListDto caseListDto,Pageable pageable) {
		BaseMessage message = null;
		String username = "";
		try {
			username = ((User) httpSession.getAttribute("login_user"))
					.getLoginName();
			if (null == username) {
				message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
				logger.info("登录超时");
				return message;
			}
		} catch (Exception e) {
			message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
			logger.info("登录超时");
			return message;
		}
		long count = 0;
		try {
		    caseListDto.setPoliceName(username);
		    
		    CaseListSpecification caseListSpecification = new CaseListSpecification(caseListDto);
			message = new BaseMessage(MessageCode.SUCCESSED);
			Page<CaseList> page  = caseListRepository.findAll(caseListSpecification,pageable);
			count = caseListRepository.count(caseListSpecification);
			message.setData(page.getContent());
			message.setMsg(String.valueOf(count));
		} catch (Exception e) {
			e.printStackTrace();
			message = new BaseMessage(MessageCode.FAILED);
		}
		return message;
	}
	
	
	/**
     * 管理员督案单列表
     * 
     * @param userLogin
     * @param type
     * @return
     */
    public BaseMessage findCaseList(CaseListDto caseListDto,Pageable pageable) {
        BaseMessage message = null;
//        String username = "";
//        try {
//            username = ((User) httpSession.getAttribute("login_user"))
//                    .getLoginName();
//            if (null == username) {
//                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
//                logger.info("登录超时");
//                return message;
//            }
//        } catch (Exception e) {
//            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
//            logger.info("登录超时");
//            return message;
//        }
        long count = 0;
        try {
            CaseListSpecification caseListSpecification = new CaseListSpecification(caseListDto);
            message = new BaseMessage(MessageCode.SUCCESSED);
            Page<CaseList> page  = caseListRepository.findAll(caseListSpecification,pageable);
            count = caseListRepository.count(caseListSpecification);
            message.setData(page.getContent());
            message.setMsg(String.valueOf(count));
        } catch (Exception e) {
            e.printStackTrace();
            message = new BaseMessage(MessageCode.FAILED);
        }
        return message;
    }
	
	/**
     * 领导的的我的督案单列表
     * 
     * @param userLogin
     * @param type
     * @return
     */
    public BaseMessage list(CaseListDto caseListDto,Pageable pageable) {
        BaseMessage message = null;
        String username = "";
        try {
            username = ((User) httpSession.getAttribute("login_user"))
                    .getLoginName();
            if (null == username) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
                logger.info("登录超时");
                return message;
            }
        } catch (Exception e) {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            logger.info("登录超时");
            return message;
        }
        long count = 0;
        try {
            caseListDto.setAudit(username);
            CaseListSpecification caseListSpecification = new CaseListSpecification(caseListDto);
            message = new BaseMessage(MessageCode.SUCCESSED);
            Page<CaseList> page  = caseListRepository.findAll(caseListSpecification,pageable);
            count = caseListRepository.count(caseListSpecification);
            message.setData(page.getContent());
            message.setMsg(String.valueOf(count));
        } catch (Exception e) {
            e.printStackTrace();
            message = new BaseMessage(MessageCode.FAILED);
        }
        return message;
    }

	/**
	 * 查询单条
	 * 
	 * @param id
	 * @return
	 */
	public CaseListDto findById(Integer id) {
	    CaseListDto caseListDto = new CaseListDto();
		CaseList caseList = caseListRepository.findOne(id);
		BeanUtils.copyProperties(caseList, caseListDto);
		caseListDto.setCaseendTime(DateUtil.parse(caseList.getCaseendTime(), DateUtil.YYYYMMDDHHMMSS));
		return caseListDto;
	}

	public CaseList findByCaseListNo(String caseListNo) {
        return caseListRepository.findByCaseListNo(caseListNo);
    }
	
	/**
	 * 定时查询当天
	 * 
	 * @param userLogin
	 * @param type
	 * @return
	 */
	public List<CaseList> findCaseList() {
		return caseListRepository.findCaseList();
	}

}
