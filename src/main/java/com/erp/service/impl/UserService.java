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

import com.erp.config.Config;
import com.erp.constant.Constant;
import com.erp.dto.UserDto;
import com.erp.dto.filter.UserFilterDto;
import com.erp.orm.domain.User;
import com.erp.orm.repository.UserRepository;
import com.erp.response.BaseMessage;
import com.erp.response.MessageCode;
import com.erp.service.specification.UserSpecification;
import com.erp.util.PasswordUtil;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession httpSession;

    private Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private Config config ;
    
    /**
     * 用户登录
     * 
     * @param username
     * @param password
     * @return
     */
    @Transactional
    public BaseMessage login(String username, String password) {
        BaseMessage message = null;
       try {
			 User user = findByUsername(username);
        if (null != user) {
            String inputPassword = PasswordUtil.generatePassword(username, password);
            String dbPassword = user.getPassword();
            if (dbPassword.equals(inputPassword)) {
            	if (Constant.ISLOCK_1.equals(user.getIsLock())) {
            		//已锁定
            		message = new BaseMessage(MessageCode.ISLOCK_OK);
            	} else {
            		 // 登录次数加1
                    Integer count = user.getCount();
                    user.setCount(count + 1);
                    userRepository.save(user);
                    logger.info("--------------登录成功----------------------");
                    message = new BaseMessage(MessageCode.SUCCESSED);
                    message.setData(user);
                    httpSession.setAttribute("login_user", user);
                    logger.info("--httpSession----content---{}",httpSession.getAttribute(Constant.LOGIN_UER_KEY));
            	}
            }
        }
        if (null == message) {
        	logger.info("--------------user对象没找到----------------------");
            message = new BaseMessage(MessageCode.USERNAME_PASSWORD_ERROR);
        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return message;
    }

    /**
     * 根据用户名查询用户
     * 
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        List<User> list = userRepository.findByUsername(username);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * 新增用户
     */
    @Transactional
    public BaseMessage saveUser(UserDto userDto) {
        BaseMessage message = null;
        User isExistUser = findByUsername(userDto.getLoginName());
        if (null == isExistUser) {
            message = new BaseMessage(MessageCode.SUCCESSED);
            Date now = new Date();
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            user.setUserName(userDto.getUserName());
            user.setLoginName(userDto.getLoginName());
            user.setMobile(userDto.getMobile());
            user.setPassword(PasswordUtil.generatePassword(userDto.getLoginName(), config.getPwd()));
            user.setCreateAt(now);
            user.setCount(0);
            user = userRepository.save(user);
            logger.info("新增用户 id:{} 成功", user.getId());
        } else {
            message = new BaseMessage(MessageCode.USER_NAME_NOT_UNIQUE);
        }
        return message;
    }

    /**
     * 修改密码
     */
    @Transactional
    public BaseMessage updatePwd(String password,String newPwd) {
        BaseMessage message = null;
        logger.info("-updatePwd----httpSession----content---{}",httpSession.getAttribute(Constant.LOGIN_UER_KEY));
        User u = (User)httpSession.getAttribute("login_user");
        if (null != u) {
            User user = userRepository.findOne(u.getId());
            if (null != user) {
                String inputPassword = PasswordUtil.generatePassword(user.getLoginName(), password);
                if (inputPassword.equals(user.getPassword())) {
                    user.setPassword(PasswordUtil.generatePassword(user.getLoginName(), newPwd));
                    user = userRepository.save(user);
                    logger.info("修改密码 id:{} 成功", user.getId());
                    message = new BaseMessage(MessageCode.SUCCESSED);
                    message.setData(user);
                } else {
                    message = new BaseMessage(MessageCode.USERNAME_PASSWORD_ERROR);
                    logger.info("原来密码错误");
                    return message;  
                }
                
            } else {
                message = new BaseMessage(MessageCode.FAILED);
                message.setMsg("该用户不存在");
                message.setData(user);
                logger.error("该用户 id:{}不存在", user);
            }
        } else {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN); 
            logger.error("登录超时了");
        }
        return message;
    }
    
    /**
     * 充值密码
     */
    @Transactional
    public BaseMessage resetPwd(Integer id) {
        BaseMessage message = null;
        User u = (User)httpSession.getAttribute("login_user");
        if (null != u) {
            User user = userRepository.findOne(id);
            if (null != user) {
                user.setPassword(PasswordUtil.generatePassword(user.getLoginName(), config.getPwd()));
                logger.info("重置密码 参数:{} ", user.getLoginName() + config.getPwd() + user.toString());
                user = userRepository.save(user);
                logger.info("重置密码 id:{} 成功", user.getId());
                message = new BaseMessage(MessageCode.SUCCESSED);
                message.setData(user);
            } else {
                message = new BaseMessage(MessageCode.FAILED);
                message.setMsg("该用户不存在");
                message.setData(user);
                logger.error("该用户 id:{}不存在", user);
            }
        } else {
            message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            logger.error("登录超时了");
        }
        return message;
    }

    /**
     * 分页用户列表
     */
    @Transactional
    public BaseMessage findUsers(UserFilterDto filterDto, Pageable pageable) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        try {
            UserSpecification specification = new UserSpecification(filterDto);
            Page<User> page = userRepository.findAll(specification, pageable);
            baseMessage.setData(page.getContent());
            baseMessage.setMsg(String.valueOf(userRepository.count(specification)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseMessage;
    }

    /**
     * 注销用户
     * 
     * @param httpSession
     * @return
     */
    public BaseMessage logOut() {
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        httpSession.removeAttribute(Constant.LOGIN_UER_KEY);
        httpSession.invalidate();
        return message;
    }
    /**
	 * 更新用户
	 * @param userDto
	 * @return
	 */
    @Transactional
	public BaseMessage update(UserDto userDto) {
		BaseMessage message = null;
		User user = userRepository.findOne(userDto.getId());
		if (null != user) {
		    user.setLoginName(userDto.getLoginName());
		    user.setMobile(user.getMobile());
		    user.setRoleid(userDto.getRoleid());
		    user.setIsLock(userDto.getIsLock());
		    user.setUserName(userDto.getUserName());
		    user.setPermissionId(userDto.getPermissionId());
	        try {
	            User u = userRepository.save(user);
	            if (null != u) {
	                message = new BaseMessage(MessageCode.SUCCESSED);
	            } else
	                message = new BaseMessage(MessageCode.FAILED);
	        } catch (Exception e) {
	            message = new BaseMessage(MessageCode.FAILED);
	        }
		} else {
		    message = new BaseMessage(MessageCode.NO_RESPONSE );
		}
		return message;
	}
	
	/**
	 * 根据ID删除用户
	 * @param id
	 * @return
	 */
	@Transactional
	public BaseMessage delete(String[] ids) {
		BaseMessage message = null;
		for (String idStr : ids) {
            Integer id = Integer.parseInt(idStr);
            User user = userRepository.findOne(id);
            if (null != user) {
                message = new BaseMessage(MessageCode.SUCCESSED);
                userRepository.delete(id);
                message.setData(id);
            } else {
                message = new BaseMessage(MessageCode.FAILED);
                message.setMsg("该用户不存在");
                message.setData(id);
                logger.error("该用户 id:{}不存在", id);
            }
		}
		return message;
	}
	
	
	public static void main(String[] args) {
		 System.out.println(PasswordUtil.generatePassword("admin", "123456"));
	}

    public User findById(Integer ids) {
        return userRepository.findOne(ids);
    }
}
