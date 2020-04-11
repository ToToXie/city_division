package wd.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wd.system.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author WangDong
 * @since 2020-04-10
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    Long countRole(@Param("role") Role role);

    /**
     * 通过用户名查找用户角色
     *
     * @param username 用户名
     * @return 用户角色集合
     */
    List<Role> findUserRole(String username);

    /**
     * 查找角色详情
     *
     * @param page 分页
     * @param role 角色
     * @return IPage<User>
     */
    <T> IPage<Role> findRolePage(Page<T> page, @Param("role") Role role);


}
