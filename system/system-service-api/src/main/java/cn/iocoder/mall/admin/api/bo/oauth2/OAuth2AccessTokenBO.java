package cn.iocoder.mall.admin.api.bo.oauth2;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class OAuth2AccessTokenBO implements Serializable {
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
