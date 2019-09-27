package cn.iocoder.mall.user.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.bo.UserAddressBO;
import cn.iocoder.mall.user.api.dto.UserAddressAddDTO;

import java.util.List;

public interface UserAddressService {

    CommonResult addAddress(UserAddressAddDTO userAddressAddDTO);

    CommonResult<List<UserAddressBO>> addressList(Integer userId);
}
