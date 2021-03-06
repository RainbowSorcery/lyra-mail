package com.lyra.mail.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyra.mail.product.entity.PmsAttr;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyra.mail.product.entity.vo.BaseListVO;
import com.lyra.mail.product.entity.vo.PmsAttrVO;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface IPmsAttrService extends IService<PmsAttr> {

    void saveAttrVo(PmsAttrVO attrVO);

    IPage<BaseListVO> baseList(Long categoryId, Integer pageSize, Integer current, String keyword, String attrType);

    BaseListVO info(Long attrId);

    void updateAttr(BaseListVO baseListVO);

    /**
     * 查询可以被检索的ids
     * @param attrsIdList 所有属性id
     * @return 可以被检索的id
     */
    List<Long> getSearchableAttrIds(List<Long> attrsIdList);
}
