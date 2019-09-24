package cn.iocoder.mall.user.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.dto.UserAddressAddDTO;

public interface UserAddressService {

    CommonResult addAddress(UserAddressAddDTO userAddressAddDTO);
}
