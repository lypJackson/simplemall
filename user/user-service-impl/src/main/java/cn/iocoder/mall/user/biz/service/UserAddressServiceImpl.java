package cn.iocoder.mall.user.biz.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.UserAddressService;
import cn.iocoder.mall.user.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.api.dto.UserAddressAddDTO;
import org.springframework.stereotype.Service;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true",version = "${dubbo.provider.UserAddressService.version}")
public class UserAddressServiceImpl implements UserAddressService {

    @Override
    public CommonResult addAddress(UserAddressAddDTO userAddressAddDTO) {
        System.out.println("调用UserAddress服务层");
        if (userAddressAddDTO.getUserId()==null){
            throw new IllegalArgumentException(String.format("满减送促销(%s) 的优惠类型不正确", "232323"));
        }
        return null;
    }
}
