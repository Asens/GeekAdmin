package com.geekutil.common.auth;

import java.lang.annotation.*;

/**
 * @author Asens
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Auth {

    String[] value() default {};

    String[] roles() default {};
}
