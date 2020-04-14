package wd.city_division.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wd.city_division.filter.TimeFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: city_division
 * @description: 配置化注册过滤器
 * @author: wd
 * @create: 2020-04-14 17:47
 **/
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<TimeFilter> timeFilter() {
        FilterRegistrationBean<TimeFilter> timeFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        TimeFilter timeFilter = new TimeFilter();
        timeFilterFilterRegistrationBean.setFilter(timeFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        timeFilterFilterRegistrationBean.setUrlPatterns(urls);
        timeFilterFilterRegistrationBean.setName("TimeFilter");
        return timeFilterFilterRegistrationBean;

    }
}
