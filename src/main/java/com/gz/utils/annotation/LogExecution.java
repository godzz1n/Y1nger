package com.gz.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 注解在运行时保留，这样可以在运行时通过反射获取到
@Target({ElementType.METHOD, ElementType.TYPE}) // 注解可以应用于方法或类上
public @interface LogExecution {
    String value() default ""; // 可以添加一些配置参数，比如日志级别、消息等，默认为空
}
