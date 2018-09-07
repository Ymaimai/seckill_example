package com.example.seckill.service;

import com.example.seckill.dao.UserDao;
import com.example.seckill.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    public boolean tx() {
        User u1 = new User();
        u1.setId(2);//数据库中没有一条id=2的数据
        u1.setName("用户2");
        userDao.insert(u1);

        User u2 = new User();
        u2.setId(1);//数据库中已有一条id=1的数据
        u2.setName("用户1");
        userDao.insert(u2);

        return true;
    }
}
