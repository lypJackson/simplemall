package cn.iocoder.mall.user.api;

public interface MobileCodeService {

    /**
     * 发送验证码
     *
     * @param mobile 手机号
     */
    void mobileSend(String mobile);
}
