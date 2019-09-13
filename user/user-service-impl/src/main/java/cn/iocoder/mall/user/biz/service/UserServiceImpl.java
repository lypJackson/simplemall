package cn.iocoder.mall.user.biz.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.user.api.UserService;
import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.biz.convert.UserConvert;
import cn.iocoder.mall.user.biz.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.UserService.version}")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBO getUser(Integer userId) {
        if (true){
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.MOBILE_CODE_USED.getCode());
        }
        return UserConvert.INSTANCE.convert(userMapper.selectById(userId));
    }
}
