package com.lyra.mail.product.service;

import com.lyra.mail.product.entity.PmsAttrGroup;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
