package com.lyra.mail.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyra.mail.product.entity.PmsProductAttrValue;
import com.lyra.mail.product.mapper.PmsProductAttrValueMapper;
import com.lyra.mail.product.service.IPmsProductAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * spu属性值 服务实现类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@Service
public class PmsProductAttrValueServiceImpl extends ServiceImpl<PmsProductAttrValueMapper, PmsProductAttrValue> implements IPmsProductAttrValueService {
    @Autowired
    private PmsProductAttrValueMapper pmsProductAttrValueMapper;

    @Override
    @Transactional
    public void updateAttrs(Long spuId, List<PmsProductAttrValue> attrValues) {
        QueryWrapper<PmsProductAttrValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("spu_id", spuId);
        pmsProductAttrValueMapper.delete(queryWrapper);

        List<PmsProductAttrValue> attrValueList = attrValues.stream().map((item) -> {
            item.setSpuId(spuId);
            return item;
        }).collect(Collectors.toList());

        this.saveBatch(attrValueList);
    }

    @Override
    public List<PmsProductAttrValue> attrValueList(Long spuId) {
        QueryWrapper<PmsProductAttrValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("spu_id", spuId);

        return pmsProductAttrValueMapper.selectList(queryWrapper);
    }
}
