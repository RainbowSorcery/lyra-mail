package com.lyra.mail.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyra.mail.auth.entity.UserInfo;

public interface IUserInfoService extends IService<UserInfo> {
    UserInfo login(UserInfo userInfo);
}
