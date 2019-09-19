package cn.iocoder.mall.user.sdk.interceptor;

import cn.iocoder.common.framework.constant.UserTypeEnum;
import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.util.MallUtil;
import cn.iocoder.mall.admin.api.OAuth2Service;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AuthenticationBO;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2GetTokenDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User 安全拦截器
 */
@Component
public class UserSecurityInterceptor implements HandlerInterceptor {


    @Reference(validation = "true", version = "${dubbo.consumer.OAuth2Service.version:1.0.0}")
    OAuth2Service oAuth2Service;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MallUtil.setUserType(request, UserTypeEnum.USER.getValue());

        // 根据 accessToken 获得认证信息，判断是谁
        String accessToken = HttpUtil.obtainAuthorization(request);
        OAuth2AuthenticationBO authentication = null;
        ServiceException serviceException = null;
        if (StringUtils.hasText(accessToken)) {
            try {
                authentication = oAuth2Service.getAuthentication(new OAuth2GetTokenDTO().setAccessToken(accessToken)
                        .setUserType(UserTypeEnum.USER.getValue()));
            } catch (ServiceException e) {
                serviceException = e;
            }
        }


        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
