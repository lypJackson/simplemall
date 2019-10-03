package cn.iocoder.mall.product.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductCategoryService;
import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.application.convert.ProductCategoryConvert;
import cn.iocoder.mall.product.application.vo.users.UsersProductCategoryVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users/category")
public class UsersProductCategoryController {

    @Reference(validation = "true", version = "${dubbo.provider.ProductCategoryService.version}")
    ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public CommonResult<List<UsersProductCategoryVO>> list(@RequestParam("pid") Integer pid) {
        List<ProductCategoryBO> result = productCategoryService.getListByPid(pid);

        return CommonResult.success(ProductCategoryConvert.INSTANCE.convertToVO(result));

    }

}
