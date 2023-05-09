package com.example.restaurantAssistant.exception.handler;

import com.example.restaurantAssistant.exception.RightsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class RightsExceptionHandler {


    /**
     * 管理员权限不足异常类handler
     * @return 当捕获时跳转错误页面
     */
    @ExceptionHandler(value = RightsException.class)
    public ModelAndView handlerAuthException(RightsException e){

        //跳转错误页面 并赋值错误信息
        ModelAndView mv = new ModelAndView("shop/error");
        mv.addObject("errorMsg", e.getMessage());

        return mv;
    }


}
