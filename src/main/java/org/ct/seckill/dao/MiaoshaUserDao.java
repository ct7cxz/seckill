package org.ct.seckill.dao;

import org.ct.seckill.domain.MiaoshaUser;
import org.ct.seckill.dto.LoginDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MiaoshaUserDao {
    List<MiaoshaUser> isUserExists(MiaoshaUser miaoshaUser);

    List<MiaoshaUser> getById(LoginDto loginDto);
}
