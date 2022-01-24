package com.lyra.mail.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyra.mail.admin.entity.SysUser;
import com.lyra.mail.admin.mapper.SysUserMapper;
import com.lyra.mail.admin.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author lyra
 * @since 2022-01-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
