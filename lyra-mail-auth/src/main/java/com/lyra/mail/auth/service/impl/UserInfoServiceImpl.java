package com.lyra.mail.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyra.mail.auth.entity.UserInfo;
import com.lyra.mail.auth.mapper.UserInfoMapper;
import com.lyra.mail.auth.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService  {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo login(UserInfo userInfo) {
        String md5Passwd = DigestUtils.md5DigestAsHex(userInfo.getPasswd().getBytes(StandardCharsets.UTF_8));

        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", userInfo.getLoginName());
        queryWrapper.eq("passwd", md5Passwd);


        return userInfoMapper.selectOne(queryWrapper);
    }
}
