//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package wd.city_division.annotation;

import com.wuwenze.poi.config.Options;
import com.wuwenze.poi.convert.ReadConverter;
import com.wuwenze.poi.convert.WriteConverter;
import com.wuwenze.poi.validator.Validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelField {
    String value() default "";

    String name() default "";

    short width() default -1;

    boolean required() default false;

    String comment() default "";

    int maxLength() default -1;

    String dateFormat() default "";

    Class<? extends Options> options() default ExcelField.Void.class;

    String writeConverterExp() default "";

    Class<? extends WriteConverter> writeConverter() default ExcelField.Void.class;

    String readConverterExp() default "";

    Class<? extends ReadConverter> readConverter() default ExcelField.Void.class;

    String regularExp() default "";

    String regularExpMessage() default "";

    Class<? extends Validator> validator() default ExcelField.Void.class;

    public static class Void implements Options, ReadConverter, WriteConverter, Validator {
        public Void() {
        }

        public String[] get() {
            return new String[0];
        }

        public String convert(Object value) {
            return null;
        }

        public String valid(Object value) {
            return null;
        }
    }
}
