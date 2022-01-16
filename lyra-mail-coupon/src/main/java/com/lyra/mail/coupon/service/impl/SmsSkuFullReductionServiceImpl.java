package com.lyra.mail.coupon.service.impl;

import com.lyra.mail.common.to.MemberPrice;
import com.lyra.mail.common.to.SkuReductionTO;
import com.lyra.mail.coupon.entity.SmsMemberPrice;
import com.lyra.mail.coupon.entity.SmsSkuFullReduction;
import com.lyra.mail.coupon.entity.SmsSkuLadder;
import com.lyra.mail.coupon.mapper.SmsSkuFullReductionMapper;
import com.lyra.mail.coupon.service.ISmsMemberPriceService;
import com.lyra.mail.coupon.service.ISmsSkuFullReductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyra.mail.coupon.service.ISmsSkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品满减信息 服务实现类
 * </p>
 *
 * @author lyra
 * @since 2022-01-14
 */
@Service
public class SmsSkuFullReductionServiceImpl extends ServiceImpl<SmsSkuFullReductionMapper, SmsSkuFullReduction> implements ISmsSkuFullReductionService {
    @Autowired
    private SmsSkuFullReductionMapper smsSkuFullReductionMapper;

    @Autowired
    private ISmsSkuLadderService skuLadderService;

    @Autowired
    private ISmsMemberPriceService memberPriceService;

    @Override
    @Transactional
    public void saveSkuFullReduction(SkuReductionTO skuReductionTO) {
        SmsSkuFullReduction smsSkuFullReduction = new SmsSkuFullReduction();
        BeanUtils.copyProperties(skuReductionTO, smsSkuFullReduction);

        smsSkuFullReductionMapper.insert(smsSkuFullReduction);

        SmsSkuLadder smsSkuLadder = new SmsSkuLadder();
        BeanUtils.copyProperties(skuReductionTO, smsSkuLadder);
        skuLadderService.save(smsSkuLadder);

        List<MemberPrice> memberPrice = skuReductionTO.getMemberPrice();

        List<SmsMemberPrice> smsMemberPrices = memberPrice.stream().map((memberPrice1 -> {
            SmsMemberPrice smsMemberPrice = new SmsMemberPrice();
            smsMemberPrice.setSkuId(skuReductionTO.getSkuId());
            smsMemberPrice.setMemberLevelId(memberPrice1.getId());
            smsMemberPrice.setMemberLevelName(memberPrice1.getName());
            smsMemberPrice.setMemberPrice(memberPrice1.getPrice());
            smsMemberPrice.setAddOther(true);
            return smsMemberPrice;
        })).collect(Collectors.toList());


        memberPriceService.saveBatch(smsMemberPrices);

    }
}
