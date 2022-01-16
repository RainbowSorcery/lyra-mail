package com.lyra.mail.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.product.entity.PmsSpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyra.mail.product.entity.vo.SpuVO;

/**
 * <p>
 * spu信息 服务类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface IPmsSpuInfoService extends IService<PmsSpuInfo> {

    void saveSpuInfo(SpuVO spuVO);

    void saveSpuInfo(PmsSpuInfo spuInfo);

    Page<PmsSpuInfo> SpuInfoListPage(Integer current, Integer pageSize, String key, Long catelogId, Long brandId, Integer status);
}
