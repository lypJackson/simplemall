package cn.iocoder.mall.user.biz.service;

import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.ValidationUtil;
import cn.iocoder.mall.user.api.UserService;
import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.api.bo.user.UserAuthenticationBO;
import cn.iocoder.mall.user.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.api.dto.UserAuthenticationByMobileCodeDTO;
import cn.iocoder.mall.user.api.dto.UserUpdateDTO;
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
    public UserAuthenticationBO authenticationByMobileCode(UserAuthenticationByMobileCodeDTO userAuthenticationByMobileCodeDTO) {
        String mobile = userAuthenticationByMobileCodeDTO.getMobile();
        String code = userAuthenticationByMobileCodeDTO.getCode();
        if (!ValidationUtil.isMobile(mobile)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "手机格式不正确");
        }


        return null;
    }


    @Override
    public UserBO getUser(Integer userId) {
        return UserConvert.INSTANCE.convert(userMapper.selectById(userId));
    }

    @Override
    public Boolean updateUser(UserUpdateDTO userUpdateDTO) {
        // 校验用户存在
        if (userMapper.selectById(userUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.USER_NOT_EXISTS.getCode());
        }
        return true;
    }
}
