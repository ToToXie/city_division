package wd.city_division.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("wd.city_division.mapper")
public class MybatisPlusConfig {

}
