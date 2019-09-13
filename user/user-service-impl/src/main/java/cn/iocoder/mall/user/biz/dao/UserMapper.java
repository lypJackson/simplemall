package cn.iocoder.mall.user.biz.dao;

import cn.iocoder.mall.user.biz.dataobject.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    UserDO selectById(@Param("id") Integer id);
}
