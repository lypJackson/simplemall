package cn.iocoder.mall.user.application.convert;

import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.application.vo.users.UsersUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mappings({})
    UsersUserVO convert2(UserBO result);
}
