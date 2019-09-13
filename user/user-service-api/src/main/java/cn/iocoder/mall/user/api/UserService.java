package cn.iocoder.mall.user.api;

import cn.iocoder.mall.user.api.bo.UserBO;

public interface UserService {

    UserBO getUser(Integer userId);
}
