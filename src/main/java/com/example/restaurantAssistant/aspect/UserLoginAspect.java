package com.example.restaurantAssistant.aspect;

import com.alibaba.fastjson.JSON;
import com.example.restaurantAssistant.constant.RedisConst;
import com.example.restaurantAssistant.enums.ResultEnum;
import com.example.restaurantAssistant.enums.StateEnum;
import com.example.restaurantAssistant.exception.SellException;
import com.example.restaurantAssistant.pojo.User;
import com.example.restaurantAssistant.service.UserService;
import com.example.restaurantAssistant.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Aspect
@Component
public class UserLoginAspect {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    //定义切面 拦截所有用户接口 排除登录与注册接口
    @Pointcut("(execution(public * com.example.restaurantAssistant.controller.user.User*.*(..))"+
            " && !execution(public * com.example.restaurantAssistant.controller.user.UserController.login(..))"+
            " && !execution(public * com.example.restaurantAssistant.controller.user.UserController.register(..))" +
            " && !execution(public * com.example.restaurantAssistant.controller.user.UserController.wechatLogin(..)))" +
            " || execution(public * com.example.restaurantAssistant.controller.dinner.DinnerController.*(..))")
    public void checkLogin(){}


    //执行切面 当用户未登录时 不允许访问用户信息接口
    @Before("checkLogin()")
    public void doCheckLogin(){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        assert attributes != null;
        HttpServletResponse response = attributes.getResponse();
        HttpServletRequest request = attributes.getRequest();

//        //查询cookie
//        Cookie cookie = CookieUtil.getCookie(CookieConst.TOKEN,request);
//        if (cookie == null){
//            log.warn("【登录校验】cookie中查不到token");
//            throw new SellException(ResultEnum.USER_NOT_LOGGED_IN);
//        }
        String token = request.getHeader("token");
        //通过token拼接键去redis中查询值
        String userJson = stringRedisTemplate.opsForValue().get(String.format(RedisConst.TOKEN_PREFIX, token));

        if (StringUtils.isEmpty(userJson)){
            log.warn("【登录校验】redis中查不到user信息");
            throw new SellException(ResultEnum.USER_NOT_LOGGED_IN);
        }

        //cookie redis都存在
        User user = JSON.parseObject(userJson,User.class);
        //校验数据库用户是否存在
        User checkUser = userService.findOne(user.getId());
        if (checkUser == null){
            log.warn("【登录校验】用户信息错误或用户不存在 提交信息={}",user);
            throw new SellException(ResultEnum.USER_PARAMS_ERROR);
        }

        //校验用户状态是否为正常
        if(StateEnum.STATE_NO.getCode().equals(checkUser.getState())){
            //用户状态为已禁用 清除redis中的健值
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConst.TOKEN_PREFIX, token));
//            //清除cookie中的token
//            assert response != null;
//            CookieUtil.setCookie(CookieConst.TOKEN,null,0, response);
            throw new SellException(ResultEnum.USER_HAS_BEEN_LOCKED);
        }

        //赋值用户信息 这里使用UserVO对象 去掉不宜展示的用户敏感信息
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(checkUser,userVO);

        request.setAttribute("userVO",userVO);
    }

}
