package wd.city_division.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wd.city_division.entity.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author WangDong
 * @since 2020-04-10
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> findUserPermissions(String username);

}
