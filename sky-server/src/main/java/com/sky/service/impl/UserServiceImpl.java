package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private UserMapper userMapper;

    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    public User wxLogin(UserLoginDTO userLoginDTO) {

        //Get openid from Weixin api
        String openid = getOpenId(userLoginDTO.getCode());
        if(openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        //Check current user is new
        User user = userMapper.getByOpenId(openid);

        //If user is new, insert user info into database
        if(user == null) {
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }
        //Return user object
        return user;
    }

    private String getOpenId(String code) {
        Map<String, String> mapLogin = new HashMap<>();
        mapLogin.put("appid", weChatProperties.getAppid());
        mapLogin.put("secret", weChatProperties.getSecret());
        mapLogin.put("js_code", code);
        mapLogin.put("grant_type", "authorization_code");
        String jsonLogin = HttpClientUtil.doGet(WX_LOGIN, mapLogin);

        JSONObject jsonObjectLogin = JSON.parseObject(jsonLogin);
        String openid = jsonObjectLogin.getString("openid");

        return openid;
    }
}
