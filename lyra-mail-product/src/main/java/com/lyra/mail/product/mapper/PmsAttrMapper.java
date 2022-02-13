package com.lyra.mail.product.mapper;

import com.lyra.mail.product.entity.PmsAttr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品属性 Mapper 接口
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface PmsAttrMapper extends BaseMapper<PmsAttr> {

    List<Long> searchableAttrIds(@Param("attrsIdList") List<Long> attrsIdList);
}
