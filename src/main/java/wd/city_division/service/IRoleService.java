package wd.city_division.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wd.city_division.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author WangDong
 * @since 2020-04-10
 */
public interface IRoleService extends IService<Role> {
    List<Role> findUserRole(String username);
}
