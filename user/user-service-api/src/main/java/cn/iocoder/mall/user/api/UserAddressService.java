package cn.iocoder.mall.user.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.bo.UserAddressBO;
import cn.iocoder.mall.user.api.dto.UserAddressAddDTO;
import cn.iocoder.mall.user.api.dto.UserAddressUpdateDTO;

import java.util.List;

public interface UserAddressService {

    CommonResult addAddress(UserAddressAddDTO userAddressAddDTO);

    CommonResult removeAddress(Integer userId, Integer addressId);

    CommonResult updateAddress(UserAddressUpdateDTO userAddressUpdateDTO);

    CommonResult<List<UserAddressBO>> addressList(Integer userId);

    CommonResult<UserAddressBO> getAddress(Integer userId, Integer id);

    CommonResult<UserAddressBO> getDefaultAddress(Integer userId);
}
