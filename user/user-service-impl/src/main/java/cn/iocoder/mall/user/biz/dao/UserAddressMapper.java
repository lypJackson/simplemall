package cn.iocoder.mall.user.biz.dao;

import cn.iocoder.mall.user.biz.dataobject.UserAddressDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressMapper {

    int insert(UserAddressDO userAddressDO);

    int updateById(@Param("id") Integer id, @Param("userAddressDO") UserAddressDO userAddressDO);

    UserAddressDO selectHasDefault(Integer deleted, Integer userId, Integer hasDefault);

    List<UserAddressDO> selectByUserIdAndDeleted(Integer deleted, Integer userId);
}
