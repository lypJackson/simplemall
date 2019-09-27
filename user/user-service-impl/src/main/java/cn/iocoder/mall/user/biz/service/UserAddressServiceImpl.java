package cn.iocoder.mall.user.biz.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.UserAddressService;
import cn.iocoder.mall.user.api.bo.UserAddressBO;
import cn.iocoder.mall.user.api.constant.UserAddressHasDefaultEnum;
import cn.iocoder.mall.user.api.dto.UserAddressAddDTO;
import cn.iocoder.mall.user.biz.convert.UserAddressConvert;
import cn.iocoder.mall.user.biz.dao.UserAddressMapper;
import cn.iocoder.mall.user.biz.dataobject.UserAddressDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.UserAddressService.version}")
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public CommonResult addAddress(UserAddressAddDTO userAddressAddDTO) {
        UserAddressDO userAddressDO = UserAddressConvert.INSTANCE.convert(userAddressAddDTO);
        userAddressDO.setCreateTime(new Date());
        userAddressDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        //检查是否设置为默认地址
        if (UserAddressHasDefaultEnum.DEFAULT_ADDRESS_NO.getValue() == userAddressAddDTO.getHasDefault()) {
            UserAddressDO defaultUserAddress = userAddressMapper.selectHasDefault(DeletedStatusEnum.DELETED_NO.getValue(), userAddressAddDTO.getUserId()
                    , UserAddressHasDefaultEnum.DEFAULT_ADDRESS_YES.getValue());
            if (null != defaultUserAddress) {
                userAddressMapper.updateById(defaultUserAddress.getId(), new UserAddressDO().setHasDefault(
                        UserAddressHasDefaultEnum.DEFAULT_ADDRESS_NO.getValue()));
            }
        }

        int result = userAddressMapper.insert(userAddressDO);
        return CommonResult.success(result);
    }


    @Override
    public CommonResult<List<UserAddressBO>> addressList(Integer userId) {
        List<UserAddressDO> userAddressDOList = userAddressMapper
                .selectByUserIdAndDeleted(DeletedStatusEnum.DELETED_NO.getValue(), userId);
        List<UserAddressBO> userAddressBOList = UserAddressConvert.INSTANCE.convertUserAddressBOList(userAddressDOList);

        return CommonResult.success(userAddressBOList);
    }
}
