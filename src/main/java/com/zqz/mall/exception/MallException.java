package com.zqz.mall.exception;


/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: MallException
 * @Date: Created in 17:37 2023-6-13
 */
public class MallException extends RuntimeException {
    private static final long serialVersionUID = 3969589744987338569L;

    public MallException() {
    }

    public MallException(String message) {
        super(message);
    }

    public static void fail(String message) {
        throw new MallException(message);
    }
}
