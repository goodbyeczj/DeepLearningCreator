package com.rkb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-11-22 下午6:46
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,reason = "该用户名已被注册")
public class UserDaoException extends  RuntimeException{

}
