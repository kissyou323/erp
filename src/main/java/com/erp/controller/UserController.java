package com.erp.controller;

import static com.erp.constant.Constant.DEF_PAGE_NUM;
import static com.erp.constant.Constant.DEF_PAGE_NUM_DEFAUL;
import static com.erp.constant.Constant.DEF_PAGE_SIZE;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.UserDto;
import com.erp.dto.filter.UserFilterDto;
import com.erp.response.BaseMessage;
import com.erp.response.DelResult;
import com.erp.response.ListResult;
import com.erp.response.MessageCode;
import com.erp.service.impl.UserService;
import com.erp.util.date.DateUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	

	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * 1 民警
	 */
	private final static String ROLEID_1 = "1";
	/**
     * 3 所内领导
     */
    private final static String ROLEID_3 = "3";
	

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * 
     * @param params
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseMessage login(@RequestBody UserDto userDto,HttpServletRequest req,HttpServletResponse response) {
        String username = userDto.getLoginName();
        String password = userDto.getPassword();
        log.info("-------------------------------------"+username+"----"+password);
        if (username == null || password == null) {
            new BaseMessage(MessageCode.PARAMS_ERROR);
        }
        BaseMessage ss = userService.login(username, password);
        log.info("-------------------------------------"+ss.getCode()+"--------"+ss.getData());
        return ss;
    }

    /**
     * 新增用户
     */
    @RequestMapping(value = "saveUser", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage saveUser(
            @RequestParam(required = false) String loginName,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String isLock,
            @RequestParam(required = false) String roleid,
            @RequestParam(required = false) String permissionId) {
        UserDto userDto = new UserDto();
        try {
            userDto.setLoginName(loginName);
            userDto.setUserName(userName);
            userDto.setMobile(mobile);
            userDto.setIsLock(isLock);
            userDto.setRoleid(roleid);
            userDto.setPermissionId(permissionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userService.saveUser(userDto);
    }
    
    /**
     * 新增用户
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }
    
    /**
     * 修改密码
     */
    @RequestMapping(value = "updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage updatePwd(@RequestBody UserDto userDto) {
        log.info("修改密码入参---------{}",userDto.toString());
        String password = userDto.getPassword();
        String newPwd = userDto.getNewPwd();
        return userService.updatePwd(password,newPwd);
    }
    
    /**
     * 修改密码
     */
    @RequestMapping(value = "resetpasswd", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage resetPwd(String id) {
        log.info("重置入参---------{}",id);
        int iid = 0;
        if (!StringUtils.isEmpty(id)) {
            iid = Integer.parseInt(id);
        }
        return userService.resetPwd(iid);
    }

    /**
     * 用户列表 分页 查出全部
     */
    @RequestMapping(value = "userList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult findUsers(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String loginName,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String username) {
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        ListResult listResult = new ListResult();
        UserFilterDto filterDto = new UserFilterDto();
        filterDto.setUsername(username);
        filterDto.setMobile(mobile);
        filterDto.setLoginName(loginName);
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage baseMessage = userService.findUsers(filterDto, pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        return listResult;
    }
    
    /**
     * 用户列表 只查出民警
     * 
     */
    @RequestMapping(value = "getListByRole_1", method = RequestMethod.GET)
    @ResponseBody
    public ListResult getListByRole_1(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM_DEFAUL) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String loginName,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String username) {
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        ListResult listResult = new ListResult();
        UserFilterDto filterDto = new UserFilterDto();
        filterDto.setUsername(username);
        filterDto.setMobile(mobile);
        filterDto.setLoginName(loginName);
        filterDto.setRoleId(ROLEID_1);
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage baseMessage = userService.findUsers(filterDto, pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        return listResult;
    }
    
    /**
     * 用户列表 只查出 所内领导
     * 
     */
    @RequestMapping(value = "getListByRole_3", method = RequestMethod.GET)
    @ResponseBody
    public ListResult getListByRole_3(
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM_DEFAUL) int pageIndex,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int limit,
            @RequestParam(required = false) String loginName,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String username) {
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        ListResult listResult = new ListResult();
        UserFilterDto filterDto = new UserFilterDto();
        filterDto.setUsername(username);
        filterDto.setMobile(mobile);
        filterDto.setLoginName(loginName);
        filterDto.setRoleId(ROLEID_3);
        PageRequest pageRequest = new PageRequest(pageIndex, limit);
        BaseMessage baseMessage = userService.findUsers(filterDto, pageRequest);
        listResult.setRows(baseMessage.getData());
        listResult.setResults(Integer.parseInt(baseMessage.getMsg()));
        return listResult;
    }

    /**
     * 注销当前用户
     */
    @RequestMapping(value = "logOut")
    @ResponseBody
    public BaseMessage getLogOut() {
        return userService.logOut();
    }

    /**
     * 删除用户
     * @param userDto
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public DelResult delfixed(@RequestParam(value = "fid[]",required = true) String[] fid,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        DelResult delResult = new DelResult();
    	BaseMessage message =  userService.delete(fid);
    	if (message.getCode() == 200) {
            delResult.setSuccess(true);
        } else {
            delResult.setSuccess(false);
        }
    	return delResult;
    }
    
    
    /**
     * 修改用户
     * @param userDto
     */
    @RequestMapping(value = "update", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage update(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String loginName,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String count,
            @RequestParam(required = false) String createAt,
            @RequestParam(required = false) String isLock,
            @RequestParam(required = false) String roleid,
            @RequestParam(required = false) String permissionId) {
        UserDto userDto = new UserDto();
        if (!StringUtils.isEmpty(id)) {
            userDto.setId(Integer.parseInt(id));
        }
        Date create_At = null;
        if (null != createAt) {
            create_At = DateUtil.parse(createAt, DateUtil.YYYYMMDD);
        }
        userDto.setLoginName(loginName);
        userDto.setUserName(userName);
        userDto.setMobile(mobile);
        userDto.setRoleid(roleid);
        userDto.setIsLock(isLock);
        userDto.setCreateAt(create_At);
        userDto.setPermissionId(permissionId);
    	log.info("---controller入参user {}" ,userDto.toString());
    	return userService.update(userDto);
    }
    
    /**
     * 修改用户
     * @param userDto
     */
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage updateUser(@RequestBody UserDto userDto) {
        log.info("---controller入参user {}" ,userDto.toString());
        return userService.update(userDto);
    }
    
    /*
     * 根据编号
     */
    @RequestMapping(value = "/findByNo", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage findByNo(@RequestBody UserDto userDto) {
        log.info("查询id：{}" + userDto.toString());
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        message.setData(userService.findByUsername(userDto.getLoginName().trim()));
        return message;
    }
    
    /*
     * 根据id查询
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage findById(@RequestParam(required = true) String id) {
        log.info("查询id：{}" + id);
        BaseMessage message = new BaseMessage(MessageCode.SUCCESSED);
        Integer ids = Integer.parseInt(id.trim());
        message.setData(userService.findById(ids));
        return message;
    }
}
