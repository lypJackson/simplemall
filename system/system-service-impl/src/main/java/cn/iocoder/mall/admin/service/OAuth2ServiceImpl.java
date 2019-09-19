package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.admin.api.OAuth2Service;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AuthenticationBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2GetTokenDTO;
import cn.iocoder.mall.admin.convert.OAuth2Convert;
import cn.iocoder.mall.admin.dao.OAuth2AccessTokenMapper;
import cn.iocoder.mall.admin.dataobject.OAuth2AccessTokenDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.OAuth2Service.version:1.0.0}")
public class OAuth2ServiceImpl implements OAuth2Service {

    @Autowired
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;


    @Override
    public OAuth2AuthenticationBO getAuthentication(OAuth2GetTokenDTO oauth2GetTokenDTO) {
        OAuth2AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectById(oauth2GetTokenDTO.getAccessToken());
        if (accessTokenDO == null) { // 不存在
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.OAUTH2_INVALID_TOKEN_NOT_FOUND.getCode());
        }
        if (accessTokenDO.getExpiresTime().getTime() < System.currentTimeMillis()) { // 已过期
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.OAUTH2_INVALID_TOKEN_EXPIRED.getCode());
        }
        if (!accessTokenDO.getValid()) { // 无效
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.OAUTH2_INVALID_TOKEN_INVALID.getCode());
        }
        if (!oauth2GetTokenDTO.getUserType().equals(accessTokenDO.getUserType())) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.OAUTH2_INVALID_TOKEN_INVALID.getCode());
        }
        // 转换返回
        return OAuth2Convert.INSTANCE.convertToAuthentication(accessTokenDO);
    }
}
