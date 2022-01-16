package com.lyra.mail.coupon.service.impl;

import com.lyra.mail.coupon.entity.SmsHomeSubject;
import com.lyra.mail.coupon.mapper.SmsHomeSubjectMapper;
import com.lyra.mail.coupon.service.ISmsHomeSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】 服务实现类
 * </p>
 *
 * @author lyra
 * @since 2022-01-14
 */
@Service
public class SmsHomeSubjectServiceImpl extends ServiceImpl<SmsHomeSubjectMapper, SmsHomeSubject> implements ISmsHomeSubjectService {

}
