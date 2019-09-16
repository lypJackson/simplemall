package cn.iocoder.mall.user.api;

import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.api.dto.UserUpdateDTO;

public interface UserService {

    UserBO getUser(Integer userId);

    /**
     * 更新用户基本信息
     *
     * @param userUpdateDTO 更新 DTO
     * @return 更新结果
     */
    Boolean updateUser(UserUpdateDTO userUpdateDTO);
}
