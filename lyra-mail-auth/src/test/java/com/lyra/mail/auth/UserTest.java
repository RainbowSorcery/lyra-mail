package com.lyra.mail.auth;

import com.lyra.mail.auth.entity.UserInfo;
import com.lyra.mail.auth.service.IUserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
public class UserTest {
    @Autowired
    private IUserInfoService userInfoService;

    @Test
    public void test() {
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginName("365373011@qq.com");
        String s = DigestUtils.md5DigestAsHex("365373011".getBytes(StandardCharsets.UTF_8));
        userInfo.setPasswd(s);

        boolean save = userInfoService.save(userInfo);

        System.out.println(save);

    }
}
