package cn.iocoder.mall.admin.api;

import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AuthenticationBO;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2GetTokenDTO;

/**
 * Oauth2 服务接口
 */
public interface OAuth2Service {

    /**
     * 通过 accessToken 获得身份信息
     *
     * @param oauth2GetTokenDTO accessToken 信息
     * @return 身份信息
     */
    OAuth2AuthenticationBO getAuthentication(OAuth2GetTokenDTO oauth2GetTokenDTO);
}
