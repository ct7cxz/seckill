package org.ct.seckill.service;

import org.ct.seckill.dao.UserDao;
import org.ct.seckill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Cacheable(value = "users", key = "#p0.id")
    public List<User> getUserList(User user) {
        return userDao.getUserList(user);
    }

    //@Transactional
    public void getAccount() {
        User user1 = new User();
        user1.setId(1);
        user1.setAccount(500.0);
        userDao.updateUser(user1);
        int i = 1 / 0;
        User user2 = new User();
        user2.setId(2);
        user2.setAccount(500.0);
        userDao.updateUser(user2);
    }
}
