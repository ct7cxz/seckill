package org.ct.seckill.dao;

import org.ct.seckill.domain.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> getUserList(User user);

    void updateUser(User user1);
}
