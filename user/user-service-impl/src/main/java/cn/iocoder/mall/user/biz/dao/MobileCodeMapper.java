package cn.iocoder.mall.user.biz.dao;

import cn.iocoder.mall.user.biz.dataobject.MobileCodeDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileCodeMapper extends BaseMapper<MobileCodeDO> {

    default MobileCodeDO selectLastByMobile(String mobile) {
        QueryWrapper<MobileCodeDO> query = new QueryWrapper<MobileCodeDO>()
                .eq("mobile", mobile)
                .orderByDesc("id")
                .last("limit 1");
        return selectOne(query);
    }
}
