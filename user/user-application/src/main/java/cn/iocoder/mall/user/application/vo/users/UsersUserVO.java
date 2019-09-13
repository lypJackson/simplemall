package cn.iocoder.mall.user.application.vo.users;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UsersUserVO {

    private Integer id;
    private String mobile;
    private String nickname;
    private String avatar;
}
