package com.lyra.mail.product.mapper;

import com.lyra.mail.product.entity.PmsAttrAttrgroupRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 属性&属性分组关联 Mapper 接口
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface PmsAttrAttrgroupRelationMapper extends BaseMapper<PmsAttrAttrgroupRelation> {

    void deleteAttrRelationList(List<PmsAttrAttrgroupRelation> attrAttrgroupRelations);
}
