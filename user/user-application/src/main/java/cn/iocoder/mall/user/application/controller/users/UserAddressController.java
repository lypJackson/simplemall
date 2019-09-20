package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.application.po.UserAddressAddPO;
import cn.iocoder.mall.user.sdk.annotation.RequiresLogin;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("users/address")
@Controller
//@Validated
public class UserAddressController {


    @PostMapping("add")
//    @RequiresLogin
    public CommonResult addAddress(@RequestBody @Validated UserAddressAddPO userAddressAddPO) {
        System.out.println();
//        Integer userId = UserSecurityContextHolder.getContext().getUserId();
//        UserAddressAddDTO userAddressAddDTO = UserAddressConvert.INSTANCE.convert(userAddressAddPO);
//        userAddressAddDTO.setUserId(userId);
        return null;
    }

    @DeleteMapping("remove")
    public CommonResult removeAddress(@NotNull(message = "用户编号不能为空") @RequestParam("id") Integer id) {
//        Integer userId = UserSecurityContextHolder.getContext().getUserId();
//        return userAddressService.removeAddress(userId, id);
        return null;
    }
}
