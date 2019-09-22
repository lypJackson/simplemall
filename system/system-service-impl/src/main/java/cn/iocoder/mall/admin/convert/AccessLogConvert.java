package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.dto.systemlog.ExceptionLogAddDTO;
import cn.iocoder.mall.admin.dataobject.ExceptionLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccessLogConvert {

    AccessLogConvert INSTANCE = Mappers.getMapper(AccessLogConvert.class);

    @Mappings({})
    ExceptionLogDO convert(ExceptionLogAddDTO exceptionLogAddDTO);

}
