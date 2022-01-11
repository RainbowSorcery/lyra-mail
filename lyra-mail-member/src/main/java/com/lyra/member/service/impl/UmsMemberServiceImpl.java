package com.lyra.member.service.impl;

import com.lyra.member.entity.UmsMember;
import com.lyra.member.mapper.UmsMemberMapper;
import com.lyra.member.service.IUmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员 服务实现类
 * </p>
 *
 * @author lyra
 * @since 2022-01-10
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements IUmsMemberService {

}
