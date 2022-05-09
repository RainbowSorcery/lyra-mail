package com.lyra.mail.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyra.mail.product.entity.PmsAttr;
import com.lyra.mail.product.entity.PmsAttrGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyra.mail.product.entity.vo.AttrGroupRelationVO;
import com.lyra.mail.product.entity.vo.AttrGroupWithAttr;
import com.lyra.mail.product.entity.vo.ItemVO;

import java.util.List;

/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface IPmsAttrGroupService extends IService<PmsAttrGroup> {

    PmsAttrGroup getGroupById(Long attrGroupId);

    List<PmsAttr> categoryAttrList(Long attrGroupId);

    void removeAttrRelation(List<AttrGroupRelationVO> attrGroupRelationVOS);

    IPage<PmsAttr> noAttrRelation(Long attrGroupId, Integer pageSize, Integer current, String keyword);

    void addAttrRelation(List<AttrGroupRelationVO> attrGroupRelationVO);

    List<AttrGroupWithAttr> getAttrByAttrCategory(String categoryId);

    List<ItemVO.SpuItemAttrVO> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId);
}
