package wd.city_division.propertise;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 只提供了常用的属性，如果有需要，自己添加
 */
@ConfigurationProperties(prefix = "druid")
@Data
public class DruidProperties {

	private String url;

	private String username;

	private String password;

	private String driverClass;

	private int maxActive;

	private int minIdle;

	private int initialSize;

	private boolean testOnBorrow;

}
