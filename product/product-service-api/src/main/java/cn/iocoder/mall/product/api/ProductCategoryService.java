package cn.iocoder.mall.product.api;

import cn.iocoder.mall.product.api.bo.ProductCategoryBO;

import java.util.List;

public interface ProductCategoryService {
    /**
     * @param pid 指定父分类编号
     * @return 返回指定分类编号的子产品分类们
     */
    List<ProductCategoryBO> getListByPid(Integer pid);
}
