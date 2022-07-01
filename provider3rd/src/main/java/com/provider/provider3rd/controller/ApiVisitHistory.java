package com.provider.provider3rd.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
public class ApiVisitHistory {

    private Logger log = LoggerFactory.getLogger(ApiVisitHistory.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义切面
     * - 此处代表com.smile.demo.controller包下的所有接口都会被统计
     */

    @Pointcut("execution(* com.provider.provider3rd.controller..*.*(..))")
    public void pointCut() {

    }

    /**
     * 在接口原有的方法执行前，将会首先执行此处的代码
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        log.info("类名：{}", joinPoint.getSignature().getDeclaringType().getSimpleName());
        log.info("方法名:{}", joinPoint.getSignature().getName());
    }

    /**
     * 只有正常返回才会执行此方法
     * 如果程序执行失败，则不执行此方法
     */
    @AfterReturning(returning = "returnVal", pointcut = "pointCut()")
    public void doAfterReturning(JoinPoint joinPoint, Object returnVal) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 执行成功则计数加一
        AtomicCounter.getInstance().increase();
        log.info("URI:[{}], 耗费时间:[{}] ms, 访问次数:{}", request.getRequestURI(), System.currentTimeMillis() - startTime.get(), AtomicCounter.getInstance().getValue());
    }

    /**
     * 当接口报错时执行此方法
     */
    @AfterThrowing(pointcut = "pointCut()")
    public void doAfterThrowing(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("接口访问失败，URI:[{}], 耗费时间:[{}] ms", request.getRequestURI(), System.currentTimeMillis() - startTime.get());
    }
}

