package cn.iocoder.common.framework.util;

/**
 * 检验工具类
 */
public class ValidationUtil {
    public static boolean isMobile(String mobile) {
        if (null == mobile || mobile.length() != 11) {
            return false;
        }
        return true;
    }
}
