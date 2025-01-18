package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect {

    @Before("@annotation(com.itheima.anno.LogOperation)")
    public void before() {
        log.info("MyAspect -> before ...");
    }

}
