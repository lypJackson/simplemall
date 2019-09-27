package cn.iocoder.mall.admin.api;

import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AccessTokenBO;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AuthenticationBO;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2CreateTokenDTO;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2GetTokenDTO;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2RefreshTokenDTO;

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

    /**
     * 刷新令牌，获得新的accessToken 信息
     *
     * @param oAuth2RefreshTokenDTO resreshToken信息
     * @return accessToken信息
     */
    OAuth2AccessTokenBO refreshToken(OAuth2RefreshTokenDTO oAuth2RefreshTokenDTO);
}
