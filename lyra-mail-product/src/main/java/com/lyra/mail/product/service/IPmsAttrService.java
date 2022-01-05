package com.lyra.mail.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyra.mail.product.entity.PmsAttr;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyra.mail.product.entity.vo.BaseListVO;
import com.lyra.mail.product.entity.vo.PmsAttrVO;

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
}
