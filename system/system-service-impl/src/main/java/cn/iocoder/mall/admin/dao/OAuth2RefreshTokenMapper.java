package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.OAuth2RefreshTokenDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Value;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2RefreshTokenMapper extends BaseMapper<OAuth2RefreshTokenDO> {
}
