package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.UserAddressService;
import cn.iocoder.mall.user.api.bo.UserAddressBO;
import cn.iocoder.mall.user.api.dto.UserAddressAddDTO;
import cn.iocoder.mall.user.api.dto.UserAddressUpdateDTO;
import cn.iocoder.mall.user.application.convert.UserAddressConvert;
import cn.iocoder.mall.user.application.po.UserAddressAddPO;
import cn.iocoder.mall.user.application.po.UserAddressUpdatePO;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("users/address")
@Controller
//@Validated
public class UserAddressController {

    @Reference(validation = "true", version = "${dubbo.provider.UserAddressService.version}")
    UserAddressService userAddressService;

    @GetMapping("list")
    public CommonResult<List<UserAddressBO>> addressList() {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return userAddressService.addressList(userId);
    }


    @PostMapping("add")
//    @RequiresLogin
    public CommonResult addAddress(@RequestBody @Validated UserAddressAddPO userAddressAddPO) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        UserAddressAddDTO userAddressAddDTO = UserAddressConvert.INSTANCE.convert(userAddressAddPO);
        userAddressAddDTO.setUserId(userId);
        return userAddressService.addAddress(userAddressAddDTO);
    }

    @DeleteMapping("remove")
    public CommonResult removeAddress(@NotNull(message = "地址主键id不能为空") @RequestParam("id") Integer id) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return userAddressService.removeAddress(userId, id);
    }

    @PutMapping("update")
    public CommonResult updateAddress(@RequestBody @Validated UserAddressUpdatePO userAddressUpdatePO) {
        final Integer userId = UserSecurityContextHolder.getContext().getUserId();
        UserAddressUpdateDTO userAddressUpdateDTO = UserAddressConvert.INSTANCE.convert(userAddressUpdatePO);
        userAddressUpdateDTO.setUserId(userId);
        return userAddressService.updateAddress(userAddressUpdateDTO);
    }

    @GetMapping("address")
    public CommonResult<UserAddressBO> getAddress(@NotNull(message = "地址主键id不能为空") @RequestParam("id") Integer id) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return userAddressService.getAddress(userId, id);
    }

    @GetMapping("default_address")
    public CommonResult<UserAddressBO> getDefaultAddress() {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return userAddressService.getDefaultAddress(userId);
    }
}
