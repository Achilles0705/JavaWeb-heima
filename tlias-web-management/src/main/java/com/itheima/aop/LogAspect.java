package com.itheima.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行目标方法
        Object result = joinPoint.proceed();
        //执行时长(毫秒)
        long costTime = System.currentTimeMillis() - beginTime;

        //获取请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        //获取参数
        Object[] args = joinPoint.getArgs();
        String methodParams = "";
        try {
            methodParams = objectMapper.writeValueAsString(args);
        } catch (Exception e) {
            log.error("参数序列化失败", e);
            methodParams = Arrays.toString(args); // fallback to simple string representation
        }
        //获取返回值
        String returnValue = objectMapper.writeValueAsString(result);


        // 获取操作人ID
        Integer operateEmpId = CurrentHolder.getCurrentId();


        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(operateEmpId); //  设置操作人ID
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setMethodParams(methodParams);
        operateLog.setReturnValue(returnValue);
        operateLog.setCostTime(costTime);

        log.info("记录操作日志：{}", operateLog);

        //保存日志
        operateLogMapper.insert(operateLog);

        return result;
    }

}

