package cn.iocoder.mall.product.application.vo.users;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UsersProductCategoryVO {

    private Integer id;

    private String name;

    private String picUrl;

}
