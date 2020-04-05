package wd.city_division.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wd.city_division.entity.City;

import java.util.List;

/**
 * <p>
 * 三级行政划分数据 服务类
 * </p>
 *
 * @author WangDong
 * @since 2020-04-02
 */

public interface ICityService extends IService<City> {
    List<City> init() throws Exception;
    List<City> test(String code);
    List<City> listByCode(String code);
    List<City> listByName(String name);

}
