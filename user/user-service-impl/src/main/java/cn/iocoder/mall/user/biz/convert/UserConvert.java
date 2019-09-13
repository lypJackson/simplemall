package cn.iocoder.mall.user.biz.convert;

import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.biz.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mappings({})
    UserBO convert(UserDO userDO);
}
