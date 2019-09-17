package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.application.po.UserAddressAddPO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/address")
@Controller
public class UserAddressController {


    @PostMapping("add")
    public CommonResult addAddress(@Validated UserAddressAddPO userAddressAddPO) {
        System.out.println();
//        Integer userId = UserSecurityContextHolder.getContext().getUserId();
//        UserAddressAddDTO userAddressAddDTO = UserAddressConvert.INSTANCE.convert(userAddressAddPO);
//        userAddressAddDTO.setUserId(userId);
        return null;
    }
}
