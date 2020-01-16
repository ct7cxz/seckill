package org.ct.seckill.config;

import com.alibaba.druid.util.StringUtils;
import org.ct.seckill.domain.MiaoshaUser;
import org.ct.seckill.service.IMiaoshaUserService;
import org.ct.seckill.service.impl.MiaoshaUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配置全局登录秒杀系统的用户，若已经登录，则从redis缓存当中获取登录用户
 */

@Component
public class UserArgumentResolvers implements HandlerMethodArgumentResolver {

    @Autowired
    private IMiaoshaUserService miaoshaUserService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> type = methodParameter.getParameterType();
        return type == MiaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        HttpServletResponse response = (HttpServletResponse) nativeWebRequest.getNativeResponse();

        String cookieParam = request.getParameter(MiaoshaUserServiceImpl.COOKIE_NAME);
        String cookieToken = getUserByCookie(request);
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(cookieParam)) {
            return null;
        }
        String token = StringUtils.isEmpty(cookieParam) ? cookieToken : cookieParam;
        return miaoshaUserService.getUserByToken(response, token);
    }

    private String getUserByCookie(HttpServletRequest request) {
        //获取所有的cookie信息，从中取到token的值
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (cookieName .equals(MiaoshaUserServiceImpl.COOKIE_NAME) ) {
               return cookie.getValue();
            }
        }
        return "";
    }
}
