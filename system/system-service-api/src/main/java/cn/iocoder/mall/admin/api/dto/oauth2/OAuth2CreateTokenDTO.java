package cn.iocoder.mall.admin.api.dto.oauth2;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.admin.api.constant.ResourceTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class OAuth2CreateTokenDTO implements Serializable {

    @NotNull(message = "用户编号不能为空")
    private Integer userId;

    @NotNull(message = "用户类型不能为空")
    @InEnum(value = ResourceTypeEnum.class, message = "用户类型必须是 {value}")
    private Integer userType;

}
