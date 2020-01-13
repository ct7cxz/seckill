package org.ct.seckill.service;

import org.ct.seckill.domain.MiaoshaUser;
import org.ct.seckill.dto.LoginDto;

import javax.servlet.http.HttpServletResponse;

public interface IMiaoshaUserService {
    MiaoshaUser isUserExists(HttpServletResponse response, LoginDto loginDto);

    boolean getById(LoginDto loginDto);

    MiaoshaUser getUserByToken(HttpServletResponse response, String token);
}
