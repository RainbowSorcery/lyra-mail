package com.lyra.mail.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyra.mail.admin.entity.Admin;
import com.lyra.mail.admin.mapper.AdminMapper;
import com.lyra.mail.admin.service.IAdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
}
