package cn.iocoder.mall.user.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Accessors(chain = true)
public class UserAuthenticationByMobileCodeDTO {

    @NotEmpty(message = "手机号不能为空")
    @Length(max = 11, min = 11, message = "账号长度为11为")
    @Pattern(regexp = "^[0-9]+$", message = "手机号必须都是数字")
    private String mobile;

    @NotEmpty(message = "手机验证码不能为空")
    @Length(max = 6, min = 4, message = "手机验证码长度为 4-6 位")
    @Pattern(regexp = "^[0-9]+$", message = "手机验证码必须都是数字")
    private String code;
}
