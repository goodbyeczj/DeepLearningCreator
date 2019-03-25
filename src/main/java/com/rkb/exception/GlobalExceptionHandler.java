package com.rkb.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-12-10 下午4:32
 */
@ControllerAdvice(basePackages = "com.rkb.controller")
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, Object> exception(Exception e) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("全局捕获异常统计");
        e.printStackTrace();
        map.put("info", "error");
        return map;
    }
}
