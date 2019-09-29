package cn.iocoder.mall.user.biz.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.UserAddressService;
import cn.iocoder.mall.user.api.bo.UserAddressBO;
import cn.iocoder.mall.user.api.constant.UserAddressHasDefaultEnum;
import cn.iocoder.mall.user.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.api.dto.UserAddressAddDTO;
import cn.iocoder.mall.user.api.dto.UserAddressUpdateDTO;
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
        //检查是否设置为默认地址，如果是则将已有的默认地址变更
        if (UserAddressHasDefaultEnum.DEFAULT_ADDRESS_NO.getValue() == userAddressAddDTO.getHasDefault()) {
            UserAddressDO defaultUserAddress = userAddressMapper.selectHasDefault(DeletedStatusEnum.DELETED_NO.getValue(), userAddressAddDTO.getUserId()
                    , UserAddressHasDefaultEnum.DEFAULT_ADDRESS_YES.getValue());
            if (null != defaultUserAddress) {
                userAddressMapper.updateById(defaultUserAddress.getId(), new UserAddressDO().setHasDefault(
                        UserAddressHasDefaultEnum.DEFAULT_ADDRESS_NO.getValue()));
            }
        }
        //保存地址
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

    @Override
    public CommonResult removeAddress(Integer userId, Integer addressId) {
        UserAddressDO userAddress = userAddressMapper.selectByUserIdAndId(userId, addressId);

        if (null == userAddress) {//地址不存在
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_ADDRESS_NOT_EXISTENT.getCode());
        }

        if (DeletedStatusEnum.DELETED_YES.getValue().equals(userAddress.getDeleted())) {//已经删除
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_ADDRESS_IS_DELETED.getCode());
        }
        //物理删除掉地址
        userAddressMapper.updateById(addressId,
                (UserAddressDO) (new UserAddressDO().setDeleted(DeletedStatusEnum.DELETED_YES.getValue())));

        return CommonResult.success(null);
    }

    @Override
    public CommonResult updateAddress(UserAddressUpdateDTO userAddressUpdateDTO) {
        UserAddressDO userAddress = userAddressMapper
                .selectByUserIdAndId(userAddressUpdateDTO.getUserId(), userAddressUpdateDTO.getId());
        if (null == userAddress) {//地址已经不存在了
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_ADDRESS_NOT_EXISTENT.getCode());
        }
        if (DeletedStatusEnum.DELETED_YES.getValue().equals(userAddress.getDeleted())) {//地址已经被物理删除了
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_ADDRESS_IS_DELETED.getCode());
        }
        //检查是否设置为默认地址
        if (UserAddressHasDefaultEnum.DEFAULT_ADDRESS_YES.getValue() == userAddress.getHasDefault()) {
            UserAddressDO defaultUserAddress = userAddressMapper
                    .selectHasDefault(DeletedStatusEnum.DELETED_NO.getValue(),
                            userAddressUpdateDTO.getUserId(), UserAddressHasDefaultEnum.DEFAULT_ADDRESS_YES.getValue());
            if (null != defaultUserAddress && userAddressUpdateDTO.getId().equals(defaultUserAddress.getId())) {
                userAddressMapper.updateById(defaultUserAddress.getId(),
                        new UserAddressDO().setHasDefault(UserAddressHasDefaultEnum.DEFAULT_ADDRESS_YES.getValue()));
            }
        }
        //转换数据
        UserAddressDO userAddressDO = UserAddressConvert.INSTANCE.convert(userAddressUpdateDTO);
        userAddressDO.setCreateTime(new Date());
        //修改地址
        userAddressMapper.updateById(userAddressDO.getId(), userAddressDO);
        return CommonResult.success(null);
    }

    @Override
    public CommonResult<UserAddressBO> getAddress(Integer userId, Integer id) {
        UserAddressDO userAddress = userAddressMapper.selectByUserIdAndId(userId, id);
        if (null == userAddress) {
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_ADDRESS_NOT_EXISTENT.getCode());
        }
        if (DeletedStatusEnum.DELETED_YES.getValue().equals(userAddress.getDeleted())) {
            return ServiceExceptionUtil.error(DeletedStatusEnum.DELETED_YES.getValue());
        }
        UserAddressBO userAddressBO = UserAddressConvert.INSTANCE.convert(userAddress);
        return CommonResult.success(userAddressBO);
    }
}
