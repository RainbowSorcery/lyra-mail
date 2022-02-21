package com.lyra.mail.product.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.product.entity.PmsCategory;
import com.lyra.mail.product.entity.vo.Catalog2VO;
import com.lyra.mail.product.service.IPmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private IPmsCategoryService categoryService;

    @RequestMapping(value = {"/index.html", "/"})
    public String index(Model model) {
        List<PmsCategory> categories = categoryService.findCategoryByFirstCategory();
        model.addAttribute("categoryList", categories);

        return "index";
    }

    @GetMapping("index/catalog.json")
    @ResponseBody
    public Map<String, List<Catalog2VO>> getCatalogJson() {

        try {
            return categoryService.getCatalogJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
