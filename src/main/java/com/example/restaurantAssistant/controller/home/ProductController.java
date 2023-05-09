package com.example.restaurantAssistant.controller.home;

import com.example.restaurantAssistant.bo.ProductBo;
import com.example.restaurantAssistant.bo.ProductCategoryBo;
import com.example.restaurantAssistant.domain.Result;
import com.example.restaurantAssistant.enums.ResultEnum;
import com.example.restaurantAssistant.exception.SellException;
import com.example.restaurantAssistant.pojo.Shop;
import com.example.restaurantAssistant.service.ProductService;
import com.example.restaurantAssistant.service.ShopService;
import com.example.restaurantAssistant.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopService shopService;

    //查询店铺下所有商品
    @GetMapping("shop/{shopId}")
    public Result<List<ProductCategoryBo>> getProductsByShopId( @PathVariable("shopId")Integer shopId ){
        Shop shop = shopService.findOne(shopId);
        if ( shop == null ){
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }
        List<ProductCategoryBo> productCategoryBos = productService.findByShopId(shopId);
        if (productCategoryBos.isEmpty()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        return ResultUtil.success(productCategoryBos);
    }

    @GetMapping("query/shop/{shopId}")
    public Result<List<ProductCategoryBo>> queryProductsByShopId( @PathVariable("shopId")Integer shopId, @RequestParam("keyWord") String keyWord){
        Shop shop = shopService.findOne(shopId);
        if ( shop == null ){
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }
        List<ProductCategoryBo> productCategoryBos = productService.findByShopId(shopId, keyWord);
        if (productCategoryBos.isEmpty()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        return ResultUtil.success(productCategoryBos);
    }

    //查询店铺销量前20的商品
    @GetMapping("hot/{shopId}")
    public Result<List<ProductBo>> getHotByShopId(@PathVariable("shopId")Integer shopId){
        Shop shop = shopService.findOne(shopId);
        if ( shop == null ){
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }
        List<ProductBo> list = productService.findHotByShopId(shopId);
        if (list.isEmpty()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        return ResultUtil.success(list);
    }

    @GetMapping("/query/hot/{shopId}")
    public Result<List<ProductBo>> queryHotByShopId(@PathVariable("shopId")Integer shopId, @RequestParam("keyWord") String keyWord ){
        Shop shop = shopService.findOne(shopId);
        if ( shop == null ){
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }
        List<ProductBo> list = productService.findHotByShopId(shopId, keyWord);
        if (list.isEmpty()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        return ResultUtil.success(list);
    }

}
