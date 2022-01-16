package com.lyra.mail.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.product.entity.PmsAttr;
import com.lyra.mail.product.entity.PmsAttrAttrgroupRelation;
import com.lyra.mail.product.entity.PmsAttrGroup;
import com.lyra.mail.product.entity.PmsCategory;
import com.lyra.mail.product.entity.vo.AttrGroupRelationVO;
import com.lyra.mail.product.entity.vo.AttrGroupWithAttr;
import com.lyra.mail.product.mapper.PmsAttrAttrgroupRelationMapper;
import com.lyra.mail.product.mapper.PmsAttrGroupMapper;
import com.lyra.mail.product.mapper.PmsAttrMapper;
import com.lyra.mail.product.mapper.PmsCategoryMapper;
import com.lyra.mail.product.service.IPmsAttrGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private PmsCategoryMapper pmsCategoryMapper;

    @Autowired
    private PmsAttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    @Autowired
    private PmsAttrMapper attrMapper;

    @Override
    public List<PmsAttr> categoryAttrList(Long attrGroupId) {
        QueryWrapper<PmsAttrAttrgroupRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_group_id", attrGroupId);
        List<PmsAttrAttrgroupRelation> pmsAttrAttrgroupRelations = attrAttrgroupRelationMapper.selectList(queryWrapper);

        List<PmsAttr> pmsAttrs = new ArrayList<>();

        pmsAttrAttrgroupRelations.forEach((pmsAttrId) -> {
            PmsAttr attr = attrMapper.selectById(pmsAttrId);

            if (attr != null) {
                pmsAttrs.add(attr);
            }
        });

        return pmsAttrs;
    }

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

    @Override
    @Transactional
    public void removeAttrRelation(List<AttrGroupRelationVO> attrGroupRelationVOS) {
        List<PmsAttrAttrgroupRelation> attrAttrgroupRelations = attrGroupRelationVOS.stream().map((attrGroupRelationVO -> {
            PmsAttrAttrgroupRelation attrAttrgroupRelation = new PmsAttrAttrgroupRelation();
            BeanUtils.copyProperties(attrGroupRelationVO, attrAttrgroupRelation);
            return attrAttrgroupRelation;
        })).collect(Collectors.toList());

        attrAttrgroupRelationMapper.deleteAttrRelationList(attrAttrgroupRelations);

    }

    @Override
    public IPage<PmsAttr> noAttrRelation(Long attrGroupId, Integer pageSize, Integer current, String keyword) {

        // 业务思路: 查出所当前id下关联的所有属性 并将当前分组已关联的属性排除 排除条件可以根据当前groupId检索 然后noIn进行排除
        // 然后查询

        // 当前分组只能关联自己所属分类下的属性
        PmsAttrGroup attrGroup = attrGroupMapper.selectById(attrGroupId);
        Long categoryId = attrGroup.getCatelogId();

        // 只能管理啊其他分组未关联的属性
        // 当前分类下的其他分组
        List<PmsAttrGroup> otherGroups =
                attrGroupMapper.selectList(new QueryWrapper<PmsAttrGroup>().eq("catelog_id", categoryId));
        List<Long> otherGroupIds = otherGroups.stream().map(PmsAttrGroup::getAttrGroupId).collect(Collectors.toList());


        IPage<PmsAttr> pmsAttrIPage = new Page<>(current, pageSize);

        List<PmsAttrAttrgroupRelation> attrAttrgroupRelations =
                    attrAttrgroupRelationMapper.selectList(new QueryWrapper<PmsAttrAttrgroupRelation>().in("attr_group_id", otherGroupIds));

        List<Long> collect = attrAttrgroupRelations.stream().map(PmsAttrAttrgroupRelation::getAttrId).collect(Collectors.toList());

        // 添加当前管理的列表进行排除

        QueryWrapper<PmsAttr> queryWrapper = new QueryWrapper<PmsAttr>().eq("catelog_id", categoryId);
        if (collect.size() > 0) {
            queryWrapper.notIn("attr_id", collect);
        }

        attrMapper.selectPage(pmsAttrIPage,queryWrapper);


        return pmsAttrIPage;
    }

    @Override
    public void addAttrRelation(List<AttrGroupRelationVO> attrGroupRelationVO) {
        List<PmsAttrAttrgroupRelation> attrAttrgroupRelations = attrGroupRelationVO.stream().map((attrGroupRelationVO1 -> {
            PmsAttrAttrgroupRelation attrAttrgroupRelation = new PmsAttrAttrgroupRelation();
            BeanUtils.copyProperties(attrGroupRelationVO1, attrAttrgroupRelation);

            return attrAttrgroupRelation;
        })).collect(Collectors.toList());

        attrAttrgroupRelations.forEach((attrAttrgroupRelation) -> {
            attrAttrgroupRelationMapper.insert(attrAttrgroupRelation);
        });
    }

    @Override
    public List<AttrGroupWithAttr> getAttrByAttrCategory(String categoryId) {
        List<PmsAttrGroup> groups = attrGroupMapper.selectList(new QueryWrapper<PmsAttrGroup>().eq("catelog_id", categoryId));

        List<AttrGroupWithAttr> attrGroupWithAttrs = groups.stream().map((attrGroup) -> {
            AttrGroupWithAttr attrGroupWithAttr = new AttrGroupWithAttr();
            BeanUtils.copyProperties(attrGroup, attrGroupWithAttr);

            List<PmsAttr> pmsAttrs = this.categoryAttrList(attrGroup.getAttrGroupId());
            attrGroupWithAttr.setAttrs(pmsAttrs);

            return attrGroupWithAttr;
        }).collect(Collectors.toList());


        return attrGroupWithAttrs;
    }
}
