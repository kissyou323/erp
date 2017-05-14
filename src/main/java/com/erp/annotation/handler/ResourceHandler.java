package com.erp.annotation.handler;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author webstorm
 *
 */
public class ResourceHandler {
    @Autowired(required = true)
    private HttpServletRequest request;

    /**
     * 对用户请求的URL进行检查
     * <p/>
     * 主要处理如下几件事宜
     * <ul>
     * <li>判断签名登录</li>
     * <li>判断是否授权</li>
     * </ul>
     *
     */
    @Around("@annotation(com.erp.annotation.Resource)")
    public Object filterByAnnotation(ProceedingJoinPoint point) throws Throwable {
        return point.proceed();
    }
}
