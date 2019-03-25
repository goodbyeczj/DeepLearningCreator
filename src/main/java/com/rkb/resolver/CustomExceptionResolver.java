package com.rkb.resolver;

import com.rkb.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-12-26 上午10:05
 */
@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Autowired
    CustomException customException;
    @ResponseBody
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();
        if(ex instanceof CustomException) {
            customException = (CustomException) ex;
        } else {
            customException.setMessage("系统发生未知错误");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg",customException.getMessage());
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }
}
