package com.lyra.mail.product.mapper;

import com.lyra.mail.product.entity.PmsAttrGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyra.mail.product.entity.vo.ItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 属性分组 Mapper 接口
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface PmsAttrGroupMapper extends BaseMapper<PmsAttrGroup> {

    List<ItemVO.SpuItemAttrVO> selectAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId, @Param("categoryId") Long categoryId);
}
