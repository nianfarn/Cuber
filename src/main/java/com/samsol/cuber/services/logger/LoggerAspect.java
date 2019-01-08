package com.samsol.cuber.services.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoggerAspect {
    private static Logger logger = LogManager.getLogger();

    @Value("${jwt.header}")
    private String tokenHeader;
    @Pointcut("execution(public * com.samsol.cuber.controllers..*.*(javax.servlet.http.HttpServletRequest, ..))")
    public void getRequests(){}

    @Before("getRequests()")
    public void showRequestAttr(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        logger.trace("================================================");
        logger.trace("Get request:");
        logger.trace("      url: "+request.getRequestURI());
        logger.trace("      token: "+request.getHeader(tokenHeader).substring(7));
        logger.trace("      method name: "+joinPoint.getSignature().getName());
        logger.trace("      method class: "+joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterReturning(pointcut = "getRequests()", returning = "retVal")
    public void showResponseParam(Object retVal){
        ResponseEntity responseEntity = (ResponseEntity) retVal;
        logger.trace("Server response: ");
        logger.trace("      status-code: "+responseEntity.getStatusCodeValue());
        logger.trace("================================================");
    }

    @Pointcut("within(com.samsol.cuber.services.crud..*)")
    public void crudManagement(){}

    @AfterReturning(value = "crudManagement()", returning = "ret")
    public void showSuccessMessages(JoinPoint p, Object ret) {
        String secondArg;
        Class returnedType = ((MethodSignature) p.getSignature()).getReturnType();
        if(returnedType.cast(ret) == null){
            secondArg = "nothing";
        } else {
            secondArg = returnedType.getName();
        }
        logger.info("{} is successfully proceed and returned {}", p.getSignature().getName(), secondArg);
    }
}
