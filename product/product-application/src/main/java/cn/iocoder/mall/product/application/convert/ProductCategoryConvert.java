package cn.iocoder.mall.product.application.convert;

import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.application.vo.users.UsersProductCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductCategoryConvert {

    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    @Mappings({})
    List<UsersProductCategoryVO> convertToVO(List<ProductCategoryBO> categoryList);
}
