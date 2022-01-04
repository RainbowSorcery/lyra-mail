package com.lyra.mail.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.product.entity.PmsAttr;
import com.lyra.mail.product.entity.PmsAttrAttrgroupRelation;
import com.lyra.mail.product.entity.PmsAttrGroup;
import com.lyra.mail.product.entity.PmsCategory;
import com.lyra.mail.product.entity.vo.BaseListVO;
import com.lyra.mail.product.entity.vo.PmsAttrVO;
import com.lyra.mail.product.mapper.PmsAttrAttrgroupRelationMapper;
import com.lyra.mail.product.mapper.PmsAttrGroupMapper;
import com.lyra.mail.product.mapper.PmsAttrMapper;
import com.lyra.mail.product.mapper.PmsCategoryMapper;
import com.lyra.mail.product.service.IPmsAttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@Service
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrMapper, PmsAttr> implements IPmsAttrService {
    @Autowired
    private PmsAttrMapper attrMapper;

    @Autowired
    private PmsAttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    @Autowired
    private PmsCategoryMapper categoryMapper;


    @Override
    @Transactional
    public void saveAttrVo(PmsAttrVO attrVO) {
        PmsAttr attr = new PmsAttr();
        BeanUtils.copyProperties(attrVO, attr);
        attrMapper.insert(attr);

        PmsAttrAttrgroupRelation attrAttrgroupRelation = new PmsAttrAttrgroupRelation();
        // 可以从PmsAttr对象中获取到id
        attrAttrgroupRelation.setAttrId(attr.getAttrId());
        attrAttrgroupRelation.setAttrGroupId(attrVO.getAttrGroupId());
        attrAttrgroupRelationMapper.insert(attrAttrgroupRelation);
    }

    @Override
    public IPage<BaseListVO> baseList(Long categoryId, Integer pageSize, Integer current, String keyword) {
        QueryWrapper<PmsAttr> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("catelog_id", categoryId);
        if (!StringUtils.isNullOrEmpty(keyword)) {
            queryWrapper.or().eq("attr_id", keyword);
            queryWrapper.or().like("attr_name", keyword);
        }

        IPage<PmsAttr> pmsAttrIPage = new Page<>(current, pageSize);
        attrMapper.selectPage(pmsAttrIPage, queryWrapper);

        List<PmsAttr> records = pmsAttrIPage.getRecords();

        List<BaseListVO> baseListVOS = new ArrayList<>();

        records.forEach((record) -> {
            PmsCategory pmsCategory = null;
            if (record.getCatelogId() != null) {
                pmsCategory = categoryMapper.selectById(record.getCatelogId());
            }

            BaseListVO baseListVO = new BaseListVO();
            BeanUtils.copyProperties(record, baseListVO);
            if (pmsCategory != null) {
                baseListVO.setCategoryName(pmsCategory.getName());
            }
            baseListVOS.add(baseListVO);
        });

        // todo  分组名称未获取到 只搜索一个值做感觉不对 以后再维护

        IPage<BaseListVO> baseListVOIPage = new Page<>();
        baseListVOIPage.setRecords(baseListVOS);
        baseListVOIPage.setPages(pmsAttrIPage.getPages());
        baseListVOIPage.setCurrent(pmsAttrIPage.getCurrent());
        baseListVOIPage.setSize(pmsAttrIPage.getSize());
        baseListVOIPage.setTotal(pmsAttrIPage.getTotal());

        return baseListVOIPage;
    }
}
