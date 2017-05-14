package com.erp.controller.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.erp.annotation.Resource;
import com.erp.constant.Constant;
import com.erp.orm.domain.User;
import com.erp.response.BaseMessage;
import com.erp.response.MessageCode;
import com.erp.service.impl.UserService;
import com.erp.util.PermissionsUtil;


/**
 * 
 * @author webstorm
 *
 */
@Component
@Scope("prototype")
public class ControllerInterceptor implements MethodInterceptor {
    private static Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        logger.debug("start controller method interceptor");
        Object message = null;
        Resource resource = invocation.getMethod().getAnnotation(Resource.class);
        if (null != resource) {
            User user = (User) httpSession.getAttribute(Constant.LOGIN_UER_KEY);

            // 用户登录
            if (null == user) {
                message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
            } else {
                // 用户是否有访问权限
                String roleid = user.getRoleid();
                List<String> rolePer = PermissionsUtil.getPermissions(roleid);
                boolean result = rolePer.contains(resource.value());
                if (!result) {
                    message = new BaseMessage(MessageCode.INTERFACE_NO_ACCESS);
                }
            }
        }

        if (null == message) {
            message = invocation.proceed();
        }
        logger.debug("end controller method interceptor");
        return message;
    }

}
