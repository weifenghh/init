package com.wf.user.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 玉米排骨汤
 * @Date 2023/12/31 9:49
 * @Package com.wf.user.annotation
 * @Version 1.0
 * @Since 1.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

String mustRole() default "";

}
