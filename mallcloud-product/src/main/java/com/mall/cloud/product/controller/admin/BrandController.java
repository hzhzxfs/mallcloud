package com.mall.cloud.product.controller.admin;

import com.mall.cloud.api.product.vo.BrandVO;
import com.mall.cloud.common.response.ServerResponseEntity;
import com.mall.cloud.product.service.BrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//品牌信息

@RestController("adminBrandController")
@RequestMapping("/admin/brand")
@Tag(name = "admin-品牌信息")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/get_brand_by_category_id")
    @Parameter(name = "categoryId", description = "分类id" )
    @Operation(summary = "根据分类，获取品牌列表" , description = "根据分类，获取品牌列表")
    public ServerResponseEntity<List<BrandVO>> getBrandByCategoryId(@RequestParam Long categoryId) {
        return ServerResponseEntity.success(brandService.getBrandByCategoryId(categoryId));
    }
}
