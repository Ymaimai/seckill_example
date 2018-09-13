package com.example.seckill.service;

import com.example.seckill.dao.SeckillUserDao;
import com.example.seckill.entities.SeckillUser;
import com.example.seckill.result.CodeMsg;
import com.example.seckill.util.MD5Util;
import com.example.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class SeckillUserService {
    @Autowired
    SeckillUserDao seckillUserDao;

    public SeckillUser getById(BigInteger id) {
        return seckillUserDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo) {
        if (loginVo == null) {
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        SeckillUser user = getById(new BigInteger(mobile));
        if (user == null) {
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)) {
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }
}