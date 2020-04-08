package wd.city_division.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @Description:
 * @Param:
 * @Return:
 * @Author: WangDong
 * @Date: 2020/4/5 23:13
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Order(5)
public @interface WebLog {

    /**
     * @Description: 日志描述信息
     * @Param: []
     * @Return: java.lang.String
     * @Author: WangDong
     * @Date: 2020/4/5 23:13
     **/
    String description() default "";

}