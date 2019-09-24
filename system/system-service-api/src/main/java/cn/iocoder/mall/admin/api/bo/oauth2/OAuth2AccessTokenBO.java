package cn.iocoder.mall.admin.api.bo.oauth2;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors
public class OAuth2AccessTokenBO {
    /**
     * 访问token
     */
    private String accessToken;
    /**
     * 刷洗token
     */
    private String refreshToken;
    /**
     * 过期时间，单位：秒
     */
    private Integer expiresIn;
}
