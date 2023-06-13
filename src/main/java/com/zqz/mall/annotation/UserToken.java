package com.zqz.mall.annotation;

import java.lang.annotation.*;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: UserToken
 * @Date: Created in 17:44 2023-6-12
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserToken {

    String name() default "user";
}
