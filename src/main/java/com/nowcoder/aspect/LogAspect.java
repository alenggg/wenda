package com.nowcoder.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Author: aleng
 * Date  : 2018/6/27
 * Time  : 10:55
 * Description:aop
 **/
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    /**
     * 第一个*是返回值
     */
    @Before("execution(* com.nowcoder.controller.*.*(..))")
    //joinPoint表示一个切点,可以获取参数
    public void beforeMethod(JoinPoint joinPoint){
        logger.info("before method" + new Date());
//        StringBuilder sb = new StringBuilder();
//        for (Object arg : joinPoint.getArgs()) {
//            sb.append("arg:" + arg.toString() + "|");
//        }
//        logger.info("before method: " + sb.toString());

    }

    @After("execution(* com.nowcoder.controller.*.*(..))")
    public void afterMethod(){
        logger.info("after method" + new Date());
    }

}
