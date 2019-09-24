package cn.iocoder.mall.user.api;

import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.api.bo.user.UserAuthenticationBO;
import cn.iocoder.mall.user.api.dto.UserAuthenticationByMobileCodeDTO;
import cn.iocoder.mall.user.api.dto.UserUpdateDTO;

public interface UserService {

    UserAuthenticationBO authenticationByMobileCode(UserAuthenticationByMobileCodeDTO userAuthenticationByMobileCodeDTO);

    UserBO getUser(Integer userId);

    /**
     * 更新用户基本信息
     *
     * @param userUpdateDTO 更新 DTO
     * @return 更新结果
     */
    Boolean updateUser(UserUpdateDTO userUpdateDTO);
}
