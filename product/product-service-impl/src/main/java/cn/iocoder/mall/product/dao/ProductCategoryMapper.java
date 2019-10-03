package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.ProductCategoryDO;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ProductCategoryMapper {

    List<ProductCategoryDO> selectListByPidAndStatusOrderBySort(@RequestParam("pid") Integer pid,
                                                                @RequestParam("status") Integer status);
}
