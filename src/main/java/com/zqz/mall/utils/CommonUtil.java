package com.zqz.mall.utils;

import cn.hutool.core.util.StrUtil;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: CommonUtil
 * @Date: Created in 14:24 2023-6-14
 */
public class CommonUtil {


    public static String genToken(String src) {
        if (StrUtil.isBlank(src)) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            String result = new BigInteger(1, md.digest()).toString(16);
            if (result.length() == 31) {
                result = result + "-";
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
