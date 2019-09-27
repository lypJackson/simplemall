package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.constant.UserTypeEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.OAuth2Service;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AccessTokenBO;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2RefreshTokenDTO;
import cn.iocoder.mall.user.api.MobileCodeService;
import cn.iocoder.mall.user.api.UserService;
import cn.iocoder.mall.user.api.bo.user.UserAuthenticationBO;
import cn.iocoder.mall.user.api.dto.UserAuthenticationByMobileCodeDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录注册
 */
@RestController
@RequestMapping("users/passport")
public class PassportController {

    @Reference(validation = "true", version = "${dubbo.provider.UserService.version}")
    UserService userService;

    @Reference(validation = "true", version = "${dubbo.provider.MobileCodeService.version}")
    MobileCodeService mobileCodeService;

    @Reference(validation = "true", version = "${dubbo.consumer.OAuth2Service.version}")
    OAuth2Service oAuth2Service;

    @PostMapping("/mobile/register")
    public CommonResult<UserAuthenticationBO> mobileRegister(UserAuthenticationByMobileCodeDTO userAuthenticationByMobileCodeDTO) {
        return CommonResult.success(userService.authenticationByMobileCode(userAuthenticationByMobileCodeDTO));
    }

    @PostMapping("/mobile/send_register_code")
    public CommonResult<Boolean> mobileSend(@RequestParam("mobile") String mobile) {
        mobileCodeService.mobileSend(mobile);
        return CommonResult.success(true);
    }

    @PostMapping("/refresh_token")
    public CommonResult<OAuth2AccessTokenBO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return CommonResult.success(oAuth2Service.refreshToken(new OAuth2RefreshTokenDTO().setRefreshToken(refreshToken)
                .setUserType(UserTypeEnum.USER.getValue())));
    }
}
