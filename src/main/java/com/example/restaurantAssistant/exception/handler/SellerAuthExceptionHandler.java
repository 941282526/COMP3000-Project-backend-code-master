package com.example.restaurantAssistant.exception.handler;

import com.example.restaurantAssistant.exception.SellerAuthException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerAuthExceptionHandler {


    /**
     * 用户未授权登录异常类
     * @return 当捕获时跳转登录页面
     */
    @ExceptionHandler(value = SellerAuthException.class)
    public ModelAndView handlerAuthException(SellerAuthException e){

        //未登录 跳转登录页面
        ModelAndView mv = new ModelAndView("shop/login");
        mv.addObject("message", e.getMessage());
        return mv;
    }


}
