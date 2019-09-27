package cn.iocoder.mall.user.biz.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.user.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.biz.dao.MobileCodeMapper;
import cn.iocoder.mall.user.biz.dataobject.MobileCodeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MobileCodeServiceImpl {

    @Value("${modules.mobile-code-service.code-expire-time-millis}")
    private int codeExpireTimes;


    @Autowired
    private MobileCodeMapper mobileCodeMapper;

    /**
     * 校验手机号的最后一个手机验证码是否有效
     *
     * @param mobile 手机号
     * @param code   验证码
     * @return 手机验证码信息
     */
    public MobileCodeDO validLastMobileCod(String mobile, String code) {
        MobileCodeDO mobileCodeDO = mobileCodeMapper.selectLastByMobile(mobile);
        //若验证码不存在，抛出异常
        if (null == mobileCodeDO) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.MOBILE_CODE_NOT_FOUND.getCode());
        }
        //验证码已过期
        if (System.currentTimeMillis() - mobileCodeDO.getCreateTime().getTime() >= codeExpireTimes) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.MOBILE_CODE_EXPIRED.getCode());
        }
        //验证码已使用
        if (mobileCodeDO.getUsed()) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.MOBILE_CODE_USED.getCode());
        }
        if (!mobileCodeDO.getCode().equals(code)) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.MOBILE_CODE_NOT_CORRECT.getCode());
        }
        return mobileCodeDO;
    }

    /**
     * 更新手机验证码已使用
     *
     * @param id     验证码编号
     * @param userId 用户编号
     */
    public void useMobileCode(Integer id, Integer userId) {
        MobileCodeDO mobileCodeDO = new MobileCodeDO().setId(id).setUsed(true).setUsedUserId(userId).setUsedTime(new Date());
        mobileCodeMapper.updateById(mobileCodeDO);
    }
}
