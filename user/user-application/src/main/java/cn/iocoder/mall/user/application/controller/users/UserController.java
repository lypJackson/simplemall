package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.UserService;
import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.application.convert.UserConvert;
import cn.iocoder.mall.user.application.vo.users.UsersUserVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/users/user")
public class UserController {

    @Reference(validation = "true", version = "${dubbo.provider.UserService.version}")
    private UserService userService;

    @GetMapping("/info")
    public CommonResult<UsersUserVO> info() {
        UserBO userResult = userService.getUser(112);
        return success(UserConvert.INSTANCE.convert2(userResult));
    }

}
