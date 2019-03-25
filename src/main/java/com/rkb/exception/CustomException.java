package com.rkb.exception;

import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-12-26 上午10:01
 */
@Component
public class CustomException extends Exception{
    public String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
