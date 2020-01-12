package org.ct.seckill.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数校验工具类
 */
public class ValidationUtil {

    private final static Pattern PATTERN_REGES = Pattern.compile("1\\d{10}");

    /**
     * 判断是否是手机号码
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        if(StringUtils.isEmpty(mobile)) {
            return false;
        }else {
            Matcher matcher = PATTERN_REGES.matcher(mobile);
            return matcher.matches();
        }
    }

}
