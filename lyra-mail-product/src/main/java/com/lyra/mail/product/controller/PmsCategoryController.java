package com.lyra.mail.product.controller;



import com.lyra.mail.common.result.Result;
import com.lyra.mail.product.entity.PmsCategory;
import com.lyra.mail.product.service.IPmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.List;

/**
 * <p>
 * 商品三级分类 前端控制器
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@RestController
@RequestMapping("/product/category")
public class PmsCategoryController {
    @Autowired
    private IPmsCategoryService categoryService;


    @GetMapping("/list/tree")
    public Result treeList() {
        List<PmsCategory> categoryList =
                categoryService.categoryListByTree();

        return Result.ok(categoryList);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody List<Long> categoryIds) {
        categoryService.logicDeleteByIds(categoryIds);

        return Result.ok();
    }

    @PostMapping("/update/sort")
    public Result updateList(@RequestBody List<PmsCategory> pmsCategories) {
        categoryService.updateBatchById(pmsCategories);

        return Result.ok();
    }

    @PostMapping("/add")
    public Result add(@RequestBody PmsCategory category) {
        if (category == null) {
            return Result.error();
        }

        categoryService.save(category);
        return Result.ok();
    }

    @GetMapping("/getCategoryById")
    public Result getCategoryById(@RequestParam Long categoryId) {
        PmsCategory byId = categoryService.getById(categoryId);

        return Result.ok(byId);
    }

    @PostMapping("/update")
    public Result update(@RequestBody PmsCategory pmsCategory) {
//        categoryService.updateById(pmsCategory);
        categoryService.updateDetails(pmsCategory);

        return Result.ok();
    }
}
