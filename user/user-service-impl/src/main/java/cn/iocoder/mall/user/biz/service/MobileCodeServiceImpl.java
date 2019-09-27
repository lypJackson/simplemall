package cn.iocoder.mall.user.biz.service;

import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.ValidationUtil;
import cn.iocoder.mall.user.api.MobileCodeService;
import cn.iocoder.mall.user.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.biz.dao.MobileCodeMapper;
import cn.iocoder.mall.user.biz.dataobject.MobileCodeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.MobileCodeService.version}")
public class MobileCodeServiceImpl implements MobileCodeService {

    /**
     * 每条验证码的过期时间，单位：毫秒
     */
    @Value("${modules.mobile-code-service.code-expire-time-millis}")
    private int codeExpireTimes;
    /**
     * 每日发送最大数量
     */
    @Value("${modules.mobile-code-service.send-maximum-quantity-per-day}")
    private int sendMaximumQuantityPerDay;
    /**
     * 短信发送频率，单位：毫秒
     */
    @Value("${modules.mobile-code-service.send-frequency}")
    private int sendFrequency;

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


    @Override
    public void mobileSend(String mobile) {
        if (!ValidationUtil.isMobile(mobile)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "手机格式不正确");
        }
        //校验是否可以发送验证码
        MobileCodeDO lastByMobile = mobileCodeMapper.selectLastByMobile(mobile);
        if (null != lastByMobile) {
            // 超过当天发送的上限。
            if (lastByMobile.getTodayIndex() >= sendMaximumQuantityPerDay) {
                throw ServiceExceptionUtil.exception(UserErrorCodeEnum.MOBILE_CODE_EXCEED_SEND_MAXIMUM_QUANTITY_PER_DAY.getCode());
            }
            //发送过于频繁
            if (System.currentTimeMillis() - lastByMobile.getCreateTime().getTime() < sendFrequency) {
                throw ServiceExceptionUtil.exception(UserErrorCodeEnum.MOBILE_CODE_SEND_TOO_FAST.getCode());
            }
        }
        //创建验证码
        MobileCodeDO newMobileCode = new MobileCodeDO().setMobile(mobile)
                .setCode("9999")//TODO 随机4位验证码 or 6位验证码
                .setTodayIndex(lastByMobile != null ? lastByMobile.getTodayIndex() : 1)//TODO 这里暂时目前没有统计出今日发送总计条数
                .setUsed(false);
        newMobileCode.setCreateTime(new Date());
        mobileCodeMapper.insert(newMobileCode);
        //TODO 发送验证码

    }
}
