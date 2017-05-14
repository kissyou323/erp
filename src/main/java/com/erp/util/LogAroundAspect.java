package com.erp.util;  
  
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAroundAspect {  
    
    private Logger logger = LoggerFactory.getLogger(LogAroundAspect.class);  
    
    // Controller层切点
    @Pointcut("@annotation(com.erp.util.SystemControllerLog)")
    public  void controllerAspect() {}
    
    /**
     * 环绕通知 用于拦截Controller层记录用户的操作
     *
     * @param pjp 切点
     */
    @Around("controllerAspect()")
    public Object aroundExec(ProceedingJoinPoint pjp) throws Throwable {
    	Object args[] = pjp.getArgs();
    	String argsStr = null != args && args.length > 0 ? Arrays.toString(args) : null;
    	logger.info("SystemControllerLog print Controller-Class: " + pjp.getTarget() + ", Method: " + pjp.getSignature().getName() + ", Args: " + argsStr);
    	Object result = pjp.proceed();
    	logger.info("SystemControllerLog print Controller-Class: " + " ReturnValue: " + result);
    	return result;
	}
    
} 