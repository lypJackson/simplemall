package cn.iocoder.mall.user.api.bo.user;

import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AccessTokenBO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserAuthenticationBO {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 昵称
     */
    private String nickname;


    private OAuth2AccessTokenBO token;

}
