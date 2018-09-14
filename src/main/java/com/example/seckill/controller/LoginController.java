package com.example.seckill.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.seckill.result.CodeMsg;
import com.example.seckill.result.Result;
import com.example.seckill.service.SeckillUserService;
import com.example.seckill.util.ValidatorUtil;
import com.example.seckill.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    SeckillUserService userService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info(loginVo.toString());
        userService.login(response, loginVo);
        return Result.success(true);
        // return null;
        //登录
        //String token = userService.login(response, loginVo);
        //return Result.success(token);
    }
}
