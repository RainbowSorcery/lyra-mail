package com.lyra.mail.product.service;

import com.lyra.mail.product.entity.PmsProductAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * spu属性值 服务类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface IPmsProductAttrValueService extends IService<PmsProductAttrValue> {

    List<PmsProductAttrValue> attrValueList(Long spuId);

    void updateAttrs(Long spuId, List<PmsProductAttrValue> attrValues);
}
