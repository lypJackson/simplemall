package cn.iocoder.mall.product.service;

import cn.iocoder.mall.product.api.ProductCategoryService;
import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.api.constant.ProductCategoryConstants;
import cn.iocoder.mall.product.convert.ProductCategoryConvert;
import cn.iocoder.mall.product.dao.ProductCategoryMapper;
import cn.iocoder.mall.product.dataobject.ProductCategoryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ProductCategoryService.version}")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategoryBO> getListByPid(Integer pid) {
        List<ProductCategoryDO> categoryList = productCategoryMapper.selectListByPidAndStatusOrderBySort(pid, ProductCategoryConstants.STATUS_ENABLE);
        return ProductCategoryConvert.INSTANCE.convertToBO(categoryList);
    }
}
