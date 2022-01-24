package com.lyra.mail.ware.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.ware.entity.WmsWareInfo;
import com.lyra.mail.ware.mapper.WmsWareInfoMapper;
import com.lyra.mail.ware.service.IWmsWareInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 仓库信息 服务实现类
 * </p>
 *
 * @author lyra
 * @since 2022-01-16
 */
@Service
public class WmsWareInfoServiceImpl extends ServiceImpl<WmsWareInfoMapper, WmsWareInfo> implements IWmsWareInfoService {
    @Autowired
    private WmsWareInfoMapper wareInfoMapper;

    @Override
    public Page<WmsWareInfo> pageList(Integer current, Integer pageSize, String key) {
        QueryWrapper<WmsWareInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.eq("id", key).or();
            queryWrapper.like("name", key);
        }

        return wareInfoMapper.selectPage(new Page<>(current, pageSize), queryWrapper);
    }
}
