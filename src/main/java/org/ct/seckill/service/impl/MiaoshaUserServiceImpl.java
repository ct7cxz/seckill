package org.ct.seckill.service.impl;

import org.ct.seckill.dao.MiaoshaUserDao;
import org.ct.seckill.domain.MiaoshaUser;
import org.ct.seckill.dto.LoginDto;
import org.ct.seckill.result.MsgCode;
import org.ct.seckill.service.IMiaoshaUserService;
import org.ct.seckill.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理秒杀用户信息的业务层
 */

@Service
public class MiaoshaUserServiceImpl implements IMiaoshaUserService {

    @Autowired
    private MiaoshaUserDao miaoshaUserDao;


    /**
     * 判断用户密码是否正确
     *
     * @param loginDto
     * @return
     */
    @Override
    public MsgCode isUserExists(LoginDto loginDto) {
        MiaoshaUser miaoshaUser = new MiaoshaUser();
        miaoshaUser.setId(new Long(loginDto.getMobile()));
        miaoshaUser.setPassword(loginDto.getPassword());
        if (!getById(loginDto)) {
            return MsgCode.ERROR_MOBILE_NULL;
        }
        MiaoshaUser user = miaoshaUserDao.getById(loginDto).get(0);
        String DBPass = user.getPassword();
        String salt = user.getSalt();
        String pass = Md5Util.formPassToDBPass(loginDto.getPassword(), salt);
        System.out.println("将服务端密码加密后的密码:"+pass);
        if (DBPass.equals(pass)) {
            return MsgCode.SUCCESS;
        } else {
            return MsgCode.ERROR_PASSWORD_FAIL;
        }

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
}
