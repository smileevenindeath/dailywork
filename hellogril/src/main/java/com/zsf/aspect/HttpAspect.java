package com.zsf.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: hellogril
 * @ClassName: HttpAspect
 * @description: aop实现
 * @author: 周生锋
 * @create: 2018-11-27 15:45
 **/
@Aspect //声明这是切点函数
@Component // 交给spring容器管理
public class HttpAspect {
    /**
     * 使用spring自带的日志组件
     */
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.zsf.controller.GrilController.*(..))")

    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        // System.out.println("aspect    before   aspect");
        logger.info("aspect before aspect");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //  url
        logger.info("url = {}", request.getRequestURI());
        //  method
        logger.info("method = {}", request.getMethod());
        // ip
        logger.info("ip = {}", request.getRemoteAddr());
        // 类方法
        logger.info("class_method = {}", joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        //参数
        logger.info("args = {}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {
        // System.out.println("aspect    after   aspect");
        logger.info("aspect after aspect");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object.toString());
    }

}
