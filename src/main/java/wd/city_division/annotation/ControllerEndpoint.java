package wd.city_division.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 同意一场处理器会拦截每个异常，这个注解的作用是
 *       一个方法调用过程中，可以产生多种异常，
 *       加上这个注解之后，可以抹除这种差异，
 *       统一显示成 @ControllerEndpoint里面标明的信息
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerEndpoint {

    String operation() default "";
    String exceptionMessage() default "系统内部异常";
}
