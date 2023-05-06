package com.example.jingdong.controller.dinner;

import com.example.jingdong.domain.Result;
import com.example.jingdong.dto.DinnerDTO;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.form.DinnerForm;
import com.example.jingdong.service.PreDinnerService;
import com.example.jingdong.utils.ResultUtil;
import com.example.jingdong.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @author yg
 */
@Slf4j
@RestController
@RequestMapping("/dinner")
public class DinnerController {

    @Autowired
    private PreDinnerService preDinnerService;

    @ApiOperation(value = "订餐保存信息")
    @PostMapping("dinnerSave")
    public Result<Integer> dinnerSave(HttpServletRequest request, @Validated DinnerForm dinnerForm) {
        if (Objects.isNull(dinnerForm)) {
            throw new SellException(ResultEnum.DINNER_FORM_PARAM_ERROR);
        }
        UserVO userVO = (UserVO) request.getAttribute("userVO");
        dinnerForm.setUserId(userVO.getId());
        return ResultUtil.success(preDinnerService.dinnerSave(dinnerForm));
    }

    @ApiOperation(value = "用餐状态变更")
    @GetMapping("dinnerUpdateStatus")
    public Result<Boolean> dinnerUpdateStatus(HttpServletRequest request, @RequestParam("status") Integer status) {
        UserVO userVO = (UserVO) request.getAttribute("userVO");
        return ResultUtil.success(preDinnerService.dinnerUpdateStatus(userVO.getId(), status));
    }

    @ApiOperation(value = "订餐信息查询")
    @GetMapping("dinnerQuery")
    public Result<List<DinnerDTO>> dinnerQuery(HttpServletRequest request, @RequestParam("status") List<Integer> status) {
        UserVO userVO = (UserVO) request.getAttribute("userVO");
        return ResultUtil.success(preDinnerService.dinnerQuery(userVO.getId(), status));
    }

    @GetMapping("dinnerQueueNumber")
    public Result<Integer> dinnerQueueNumber(HttpServletRequest request) {
        UserVO userVO = (UserVO) request.getAttribute("userVO");
        return ResultUtil.success(preDinnerService.dinnerQueueNumber(userVO.getId()));
    }
}
