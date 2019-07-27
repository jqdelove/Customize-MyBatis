package com.jinqi.mybatis.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Select注解
 */

@Retention(RetentionPolicy.RUNTIME)//生命周期
@Target(ElementType.METHOD)//标注的位置
public @interface Select {

    /**
     * 配置SQL语句
     * @return
     */
    String value();
}
