package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //处理异常
    @ExceptionHandler
    public Result handleException(Exception e) {//方法形参中指定能够处理的异常类型
        log.error("程序出现了虫子", e);
        //捕获到异常之后，响应一个标准的Result
        return Result.error("对不起,操作失败,请联系管理员");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("程序出现了虫子", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + " 已存在");
    }

    @ExceptionHandler(ClazzNotEmptyException.class)  //  处理 ClassNotEmptyException
    public Result handleClazzNotEmptyException(ClazzNotEmptyException e) {
        log.error("班级非空异常", e);
        return Result.error(e.getMessage()); // 返回自定义的错误信息
    }

    @ExceptionHandler(DeptNotEmptyException.class)  //  处理 ClassNotEmptyException
    public Result handleDeptNotEmptyException(DeptNotEmptyException e) {
        log.error("部门非空异常", e);
        return Result.error(e.getMessage()); // 返回自定义的错误信息
    }
}