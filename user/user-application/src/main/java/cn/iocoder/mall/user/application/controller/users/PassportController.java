package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.UserService;
import cn.iocoder.mall.user.api.bo.user.UserAuthenticationBO;
import cn.iocoder.mall.user.api.dto.UserAuthenticationByMobileCodeDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录注册
 */
@RestController
@RequestMapping("users/passport")
public class PassportController {

    @Reference(validation = "true", version = "${dubbo.provider.UserService.version}")
    UserService userService;

    @PostMapping("/mobile/register")
    public CommonResult<UserAuthenticationBO> mobileRegister(UserAuthenticationByMobileCodeDTO userAuthenticationByMobileCodeDTO) {
        return CommonResult.success(userService.authenticationByMobileCode(userAuthenticationByMobileCodeDTO));
    }
}
