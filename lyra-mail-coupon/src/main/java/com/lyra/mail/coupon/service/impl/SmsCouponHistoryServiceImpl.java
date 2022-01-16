package com.lyra.mail.coupon.service.impl;

import com.lyra.mail.coupon.entity.SmsCouponHistory;
import com.lyra.mail.coupon.mapper.SmsCouponHistoryMapper;
import com.lyra.mail.coupon.service.ISmsCouponHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券领取历史记录 服务实现类
 * </p>
 *
 * @author lyra
 * @since 2022-01-14
 */
@Service
public class SmsCouponHistoryServiceImpl extends ServiceImpl<SmsCouponHistoryMapper, SmsCouponHistory> implements ISmsCouponHistoryService {

}
