package wd.city_division.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wd.city_division.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author WangDong
 * @since 2020-04-10
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 通过用户名查找用户角色
     *
     * @param username 用户名
     * @return 用户角色集合
     */
    List<Role> findUserRole(String username);


}
