package cn.iocoder.mall.user.biz.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.ValidationUtil;
import cn.iocoder.mall.user.api.UserService;
import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.api.bo.user.UserAuthenticationBO;
import cn.iocoder.mall.user.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.api.dto.UserAuthenticationByMobileCodeDTO;
import cn.iocoder.mall.user.api.dto.UserUpdateDTO;
import cn.iocoder.mall.user.biz.constant.UserConstants;
import cn.iocoder.mall.user.biz.convert.UserConvert;
import cn.iocoder.mall.user.biz.dao.UserMapper;
import cn.iocoder.mall.user.biz.dao.UserRegisterMapper;
import cn.iocoder.mall.user.biz.dataobject.MobileCodeDO;
import cn.iocoder.mall.user.biz.dataobject.UserDO;
import cn.iocoder.mall.user.biz.dataobject.UserRegisterDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.UserService.version}")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRegisterMapper userRegisterMapper;
    @Autowired
    private MobileCodeServiceImpl mobileCodeService;

    @Override
    public UserAuthenticationBO authenticationByMobileCode(UserAuthenticationByMobileCodeDTO userAuthenticationByMobileCodeDTO) {
        String mobile = userAuthenticationByMobileCodeDTO.getMobile();
        String code = userAuthenticationByMobileCodeDTO.getCode();
        if (!ValidationUtil.isMobile(mobile)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "手机格式不正确");
        }
        //校验验证码是否正确
        MobileCodeDO mobileCodeDO = mobileCodeService.validLastMobileCod(mobile, code);
        //获得用户
        UserDO user = userMapper.selectByMobile(mobile);
        if (null == user) {
            user = new UserDO().setMobile(mobile)
                    .setStatus(UserConstants.STATUS_ENABLE);
            user.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
            userMapper.insert(user);
            //插入注册信息
            createUserRegister(user);

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


    private void createUserRegister(UserDO userDO) {
        UserRegisterDO userRegisterDO = new UserRegisterDO().setId(userDO.getId())
                .setCreateTime(new Date());
        userRegisterMapper.insert(userRegisterDO);
    }
}
