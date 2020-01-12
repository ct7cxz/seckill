package org.ct.seckill.service;

import org.ct.seckill.dto.LoginDto;
import org.ct.seckill.result.MsgCode;

public interface IMiaoshaUserService {
    MsgCode isUserExists(LoginDto loginDto);

    boolean getById(LoginDto loginDto);
}
