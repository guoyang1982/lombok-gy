package com.gy.annotation;

/**
 * @author guoyang
 * @date 2022/11/1 11:36 上午
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface AddHelloWorld {

}
