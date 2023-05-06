package com.example.jingdong.controller.home;

import com.example.jingdong.domain.Result;
import com.example.jingdong.dto.DateTimeDTO;
import com.example.jingdong.dto.SeatDTO;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.enums.StateEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.pojo.Shop;
import com.example.jingdong.pojo.ShopCategory;
import com.example.jingdong.service.PreDinnerService;
import com.example.jingdong.service.ShopCategoryService;
import com.example.jingdong.service.ShopService;
import com.example.jingdong.utils.ResultUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/home")
public class ShopController {

    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private PreDinnerService preDinnerService;

    //获取受特所有店铺分类信息
    @GetMapping("categories")
    public Result<ShopCategory> getShopCategory(){
        List<ShopCategory> categoryList = shopCategoryService.findAll();
        if (categoryList.isEmpty()){
            throw new SellException(ResultEnum.SHOP_CATEGORY_NOT_EXIST);
        }
        return ResultUtil.success(categoryList);
    }

    //获取首页热门店铺
    @GetMapping("hotShops")
    public Result<List<Shop>> getHotShops(@RequestParam(name = "page",defaultValue = "1")Integer page,
                                          @RequestParam(name = "size",defaultValue = "10")Integer size ){
        page = page <= 1 ? 0 : page-1;
        Pageable pageable = PageRequest.of(page,size);
        Page<Shop> pageInfo = shopService.findAllByRecommend(pageable,
                StateEnum.RECOMMEND_YES.getCode());
        if (pageInfo.isEmpty()){
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }
        return ResultUtil.success(pageInfo.getContent());
    }

    @GetMapping("getDateTimeList")
    public Result<DateTimeDTO> getDateTimeList(@RequestParam(name = "pre", defaultValue = "1") Integer pre) {
        DateTimeDTO dto = new DateTimeDTO();
        if(pre == 1){
            dto.setDateList(this.setDateList());
            dto.setTimeList(this.setTimeList());
        } else {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M.d");
            dto.setDateList(Collections.singletonList(LocalDate.now().format(dateFormat)));
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm");
            dto.setTimeList(Collections.singletonList(LocalTime.now().format(timeFormat)));
        }
        return ResultUtil.success(dto);
    }

    @ApiOperation(value = "查询餐桌是否空闲")
    @GetMapping("dinnerSeatList")
    public Result<List<SeatDTO>> dinnerSeatList(@RequestParam("dinnerTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date dinnerTime) {
        if (Objects.isNull(dinnerTime)) {
            throw new SellException(ResultEnum.DINNER_FORM_PARAM_ERROR);
        }
        return ResultUtil.success(preDinnerService.dinnerSeat(dinnerTime));
    }

    private List<String> setDateList() {
        List<String> dateList = Lists.newArrayList();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M.d");
        for (int i = 1; i < 11; i++) {
            String date = dateFormat.format(calendar.getTime());
            dateList.add(date);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dateList;
    }

    private List<String> setTimeList() {
        List<String> timeList = Lists.newArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("H:00");
        for (int i = 1; i <= 12; i++) {
            String date = dateFormat.format(calendar.getTime());
            timeList.add(date);
            calendar.add(Calendar.HOUR_OF_DAY, 1);
        }
        return timeList;
    }


    //根据分类id查找店铺
    @GetMapping("category/shop/{cateId}")
    public Result<List<Shop>> getShopsByCategory( @PathVariable("cateId")Integer cateId ){
        List<Shop> shopList = shopService.findShopsByCategory(cateId);

        if ( shopList == null ){
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }

        return ResultUtil.success(shopList);
    }


    //根据id查找店铺
    @GetMapping("shop/{id}")
    public Result<Shop> getShop( @PathVariable("id")Integer id ){
        Shop shop = shopService.findOne(id);
        if ( shop == null ){
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }
        return ResultUtil.success(shop);
    }



}
