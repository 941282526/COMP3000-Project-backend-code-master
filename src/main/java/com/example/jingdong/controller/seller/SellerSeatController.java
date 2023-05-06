package com.example.jingdong.controller.seller;

import com.example.jingdong.domain.Result;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.pojo.SeatConfig;
import com.example.jingdong.service.SeatConfigService;
import com.example.jingdong.utils.ResultUtil;
import com.example.jingdong.vo.SellerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Controller
@RequestMapping("/seller/seat")
public class SellerSeatController {

    @Autowired
    private SeatConfigService seatConfigService;

    //返回商品视图页面
    @GetMapping("listPage")
    public ModelAndView listPage(){
        return new ModelAndView("shop/seat-list");
    }

    //根据店铺id 及筛选条件 查询店铺所有商品 返回商品信息
    //默认查询状态为上架（1）的商品
    @GetMapping("list")
    @ResponseBody
    public Result<HashMap<String,Object>> list(HttpServletRequest request,
                                               @RequestParam(value = "page",defaultValue = "1") Integer page,
                                               @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                               @RequestParam(value = "state",defaultValue = "1") Integer state,
                                               @RequestParam(value = "type",defaultValue = "") String type,
                                               @RequestParam(value = "name",defaultValue = "") String name){
        //获取请求中的用户信息
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");
        //构建分页对象
        Pageable pageable = PageRequest.of(page-1,limit);

        //条件查询商品信息
        Page<SeatConfig> pageList = seatConfigService.list(pageable);
        HashMap<String,Object> map = new HashMap<>();
        map.put("page", pageList);

        return ResultUtil.success(map);
    }

    /**
     * 返回商品编辑视图页面
     * 当id有值时为编辑已有商品 需获取商品信息 回传前端
     * 当id没有值时 为新增商品
     * @param id    商品id 为可选参数
     * @return      商品编辑视图页面
     */
    @GetMapping("editPage")
    public ModelAndView edit(HttpServletRequest request,
                             @RequestParam(value = "id",required = false) Integer id){

        //获取商品信息
        SeatConfig seatConfig = new SeatConfig();
        if (id != null){
            seatConfig = seatConfigService.findOne(id);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("seat", seatConfig);
        mv.setViewName("shop/seat-edit");

        return mv;
    }

    //编辑商品信息
    @PostMapping("edit")
    @ResponseBody
    public Result<Object> edit(HttpServletRequest request,
                               @Valid SeatConfig seatConfigForm,
                               BindingResult bindingResult){
        //输入参数判断
        if (bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                                Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        //获取请求中的用户
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");

        SeatConfig result;

        //id不为空 执行更新操作
        if (seatConfigForm.getId() != null){
            //执行更新操作
            SeatConfig old = seatConfigService.findOne(seatConfigForm.getId());
            seatConfigForm.setCreateTime(old.getCreateTime());
            seatConfigForm.setUpdateTime(old.getUpdateTime());
            result = seatConfigService.save(seatConfigForm);
        }else {
            //商品所属店铺id即当前用户店铺id
            result = seatConfigService.save(seatConfigForm);
        }

        //判断是否成功
        if (result != null){
            return ResultUtil.success(result);
        }

        return ResultUtil.error(ResultEnum.PRODUCT_EDIT_FAIL);
    }


    @PostMapping("delete")
    @ResponseBody
    public Result<Object> delete(HttpServletRequest request,
                                 @RequestParam("id") Integer id ){

        //获取请求中的用户 用于权限判断
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");

        //JPA删除无返回值 在service方法中进行了try catch抛出异常
        seatConfigService.delete(id,sellerVO);

        //未抛出异常 说明删除成功 返回信息
        return ResultUtil.success();
    }



}
