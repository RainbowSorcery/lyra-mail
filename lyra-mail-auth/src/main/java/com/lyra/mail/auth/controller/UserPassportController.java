package com.lyra.mail.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lyra.mail.auth.constant.UserConstant;
import com.lyra.mail.auth.entity.UserInfo;
import com.lyra.mail.auth.service.IUserInfoService;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.common.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user/passport")
public class UserPassportController {
    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/login")
    public Result login(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        UserInfo login = userInfoService.login(userInfo);

        if (login != null) {
            // 将ip存储至redis中以此来避免恶意用户伪装token
            String ip = IpUtil.getIpAddress(request);

            HashMap<String, String> resultMap = new HashMap<>();
            String token = UUID.randomUUID().toString();
            Map<String, Object> tokenValueMap = new HashMap<>();
            tokenValueMap.put("ip", ip);
            tokenValueMap.put("userId", login.getId());
            String tokenValueJson = null;
            try {
                tokenValueJson = objectMapper.writeValueAsString(tokenValueMap);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            String userToken = UserConstant.USER_TOKEN_CACHE + token;
            redisTemplate.opsForValue().set(userToken, tokenValueJson, 30, TimeUnit.DAYS);

            resultMap.put("nickName", login.getNickName());
            resultMap.put("token", token);


            return Result.ok(resultMap);
        }

        return null;
    }
}
