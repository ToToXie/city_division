package wd.city_division.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wd.city_division.entity.City;

import java.util.List;

/**
 * <p>
 * 三级行政划分数据 Mapper 接口
 * </p>
 *
 * @author WangDong
 * @since 2020-04-02
 */
@Repository
public interface CityMapper extends BaseMapper<City> {
    List<City> listChildsById(@Param("id") Integer id);

    /**
     * @Description: 查询某地区及以下
     **/
    List<City> getChilds(@Param("id") Integer id);
    /**
     * @Description: 查询全国
     **/
    List<City> getAll();
}
