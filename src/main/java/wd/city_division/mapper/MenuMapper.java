package wd.city_division.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wd.city_division.entity.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author WangDong
 * @since 2020-04-10
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> findUserPermissions(String username);
}
