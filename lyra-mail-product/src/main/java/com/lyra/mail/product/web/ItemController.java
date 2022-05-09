package com.lyra.mail.product.web;

import com.lyra.mail.product.entity.vo.ItemVO;
import com.lyra.mail.product.service.IPmsSkuInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ItemController {
    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private IPmsSkuInfoService skuInfoService;

    @GetMapping("/{skuId}.html")
    public String item(@PathVariable Long skuId, Model model) {
        log.info("skuId:{}", skuId);

        ItemVO itemVO = skuInfoService.item(skuId);
        model.addAttribute("item", itemVO);

        return "item";
    }
}
