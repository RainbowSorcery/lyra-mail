package com.lyra.mail.product.service.impl;

import com.lyra.mail.product.entity.PmsAttrGroup;
import com.lyra.mail.product.entity.PmsCategory;
import com.lyra.mail.product.mapper.PmsAttrGroupMapper;
import com.lyra.mail.product.mapper.PmsCategoryMapper;
import com.lyra.mail.product.service.IPmsAttrGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 属性分组 服务实现类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@Service
public class PmsAttrGroupServiceImpl extends ServiceImpl<PmsAttrGroupMapper, PmsAttrGroup> implements IPmsAttrGroupService {
    @Autowired
    private PmsAttrGroupMapper attrGroupMapper;

    // todo 应该远程调用 之后再修改
    @Autowired
    private PmsCategoryMapper pmsCategoryMapper;

    @Override
    public PmsAttrGroup getGroupById(Long attrGroupId) {
        PmsAttrGroup attrGroup = attrGroupMapper.selectById(attrGroupId);

        Long catelogId = attrGroup.getCatelogId();
        List<Long> list = new LinkedList<>();

        getGroupCategoryPath(catelogId, list);
        Collections.reverse(list);
        attrGroup.setAttrGroupPath(list);

        return attrGroup;
    }

    private void getGroupCategoryPath(Long categoryId, List<Long> categoryPath) {
        PmsCategory pmsCategory = pmsCategoryMapper.selectById(categoryId);
        if (pmsCategory != null && pmsCategory.getCatId() != 0) {
            categoryPath.add(pmsCategory.getCatId());

            this.getGroupCategoryPath(pmsCategory.getParentCid(), categoryPath);
        }

    }

}
