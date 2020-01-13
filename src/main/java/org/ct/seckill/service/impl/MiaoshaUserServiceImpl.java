package org.ct.seckill.service.impl;

import org.ct.seckill.dao.MiaoshaUserDao;
import org.ct.seckill.domain.MiaoshaUser;
import org.ct.seckill.dto.LoginDto;
import org.ct.seckill.exception.GlobalException;
import org.ct.seckill.redis.MiaoshaUserKey;
import org.ct.seckill.redis.RedisService;
import org.ct.seckill.result.MsgCode;
import org.ct.seckill.service.IMiaoshaUserService;
import org.ct.seckill.util.Md5Util;
import org.ct.seckill.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理秒杀用户信息的业务层
 */

@Service
public class MiaoshaUserServiceImpl implements IMiaoshaUserService {

    //存放cookie的key值
    public final static String COOKIE_NAME = "token";

    //存放redis与cookie的有效期
    public final static int expireSecond = 3600 * 24 * 2;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MiaoshaUserDao miaoshaUserDao;


    /**
     * 判断用户密码是否正确
     *
     * @param loginDto
     * @return
     */
    @Override
    public MiaoshaUser isUserExists(HttpServletResponse response, LoginDto loginDto) {
        MiaoshaUser miaoshaUser = new MiaoshaUser();
        miaoshaUser.setId(new Long(loginDto.getMobile()));
        miaoshaUser.setPassword(loginDto.getPassword());
        if (!getById(loginDto)) {
            throw new GlobalException(MsgCode.ERROR_MOBILE_NULL);
        }
        MiaoshaUser user = miaoshaUserDao.getById(loginDto).get(0);
        String DBPass = user.getPassword();
        String salt = user.getSalt();
        String pass = Md5Util.formPassToDBPass(loginDto.getPassword(), salt);
        if (!DBPass.equals(pass)) {
            throw new GlobalException(MsgCode.ERROR_PASSWORD_FAIL);
        }
        //设置token完成分布式会话
        String token = UUIDUtil.getUUID();
        addCookie(response, user ,token);
        return user;
    }

    private void addCookie(HttpServletResponse response, MiaoshaUser user,String token) {
        MiaoshaUserKey miaoshaUserKey = new MiaoshaUserKey(expireSecond, token);
        Cookie cookie = new Cookie(COOKIE_NAME, token);
        redisService.set(miaoshaUserKey, token, user);
        cookie.setMaxAge(miaoshaUserKey.expireSecond());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 判断用户是否存在
     *
     * @param loginDto
     * @return
     */
    @Override
    public boolean getById(LoginDto loginDto) {
        return miaoshaUserDao.getById(loginDto).size() == 1 ? true : false;
    }

    /**
     * 根据cookie中的token值，从缓存中获取用户的信息
     *
     * @param token
     * @return
     */
    @Override
    public MiaoshaUser getUserByToken(HttpServletResponse response, String token) {
        MiaoshaUserKey miaoshaUserKey = new MiaoshaUserKey(expireSecond, token);
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser miaoshaUser = redisService.get(miaoshaUserKey, token, MiaoshaUser.class);
        if (StringUtils.isEmpty(miaoshaUser)) {
            redisService.set(miaoshaUserKey, token, miaoshaUser);
            addCookie(response, miaoshaUser,token);
        }
        return miaoshaUser;
    }
}
