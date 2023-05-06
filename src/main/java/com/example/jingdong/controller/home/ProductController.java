package com.example.jingdong.controller.home;

import com.example.jingdong.bo.ProductBo;
import com.example.jingdong.bo.ProductCategoryBo;
import com.example.jingdong.domain.Result;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.pojo.Shop;
import com.example.jingdong.service.ProductService;
import com.example.jingdong.service.ShopService;
import com.example.jingdong.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
