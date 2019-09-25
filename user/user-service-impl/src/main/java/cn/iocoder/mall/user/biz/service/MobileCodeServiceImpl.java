package cn.iocoder.mall.user.biz.service;

import cn.iocoder.mall.user.biz.dataobject.MobileCodeDO;
import org.springframework.stereotype.Service;

@Service
public class MobileCodeServiceImpl {

    /**
     * 校验手机号的最后一个手机验证码是否有效
     *
     * @param mobile 手机号
     * @param code 验证码
     * @return 手机验证码信息
     */
    public MobileCodeDO validLastMobileCod(String mobile, String code) {

        return null;
    }

}
