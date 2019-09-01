package com.geekutil.common.log;

import java.lang.annotation.*;

/**
 * @author Asens
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SystemLog {

    int TYPE_OPERATION = 0;
    int TYPE_LOGIN = 1;
    int TYPE_REGISTER = 2;

    int type() default 0;

    String message() default "";

}
