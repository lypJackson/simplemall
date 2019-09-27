package cn.iocoder.mall.admin.api;

import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AccessTokenBO;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AuthenticationBO;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2CreateTokenDTO;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2GetTokenDTO;

/**
 * Oauth2 服务接口
 */
public interface OAuth2Service {

    /**
     * 根据身份信息，创建accessToken 信息
     *
     * @param oAuth2CreateTokenDTO 身份信息DTO
     * @return accessToken 信息
     */
    OAuth2AccessTokenBO createToken(OAuth2CreateTokenDTO oAuth2CreateTokenDTO);

    /**
     * 通过 accessToken 获得身份信息
     *
     * @param oauth2GetTokenDTO accessToken 信息
     * @return 身份信息
     */
    OAuth2AuthenticationBO getAuthentication(OAuth2GetTokenDTO oauth2GetTokenDTO);
}
