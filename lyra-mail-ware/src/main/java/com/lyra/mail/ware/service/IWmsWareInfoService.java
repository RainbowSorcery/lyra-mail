package com.lyra.mail.ware.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.ware.entity.WmsWareInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 仓库信息 服务类
 * </p>
 *
 * @author lyra
 * @since 2022-01-16
 */
public interface IWmsWareInfoService extends IService<WmsWareInfo> {

    Page<WmsWareInfo> pageList(Integer current, Integer pageSize, String key);
}
