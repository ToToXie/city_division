package wd.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import wd.city_division.config.ShiroRealm;
import wd.city_division.entity.FebsConstant;
import wd.city_division.util.SortUtil;
import wd.system.entity.QueryRequest;
import wd.system.entity.Role;
import wd.system.entity.RoleMenu;
import wd.system.mapper.RoleMapper;
import wd.system.service.IRoleMenuService;
import wd.system.service.IRoleService;
import wd.system.service.IUserRoleService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author WangDong
 * @since 2020-04-10
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    private final IRoleMenuService roleMenuService;
    private final IUserRoleService userRoleService;
    private final ShiroRealm shiroRealm;

    @Override
    public List<Role> findUserRole(String username) {
        return this.baseMapper.findUserRole(username);
    }

    @Override
    public List<Role> findRoles(Role role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(role.getRoleName())) {
            queryWrapper.lambda().like(Role::getRoleName, role.getRoleName());
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Role> findRoles(Role role, QueryRequest request) {
        Page<Role> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countRole(role));
        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, false);
        return this.baseMapper.findRolePage(page, role);
    }

    @Override
    public Role findByName(String roleName) {
        return this.baseMapper.selectOne(new QueryWrapper<Role>().lambda().eq(Role::getRoleName, roleName));
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void createRole(Role role) {
        role.setCreateTime(LocalDateTime.now());
        this.baseMapper.insert(role);
        this.saveRoleMenus(role);
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void updateRole(Role role) {
        role.setModifyTime(LocalDateTime.now());
        this.updateById(role);
        List<String> roleIdList = new ArrayList<>();
        roleIdList.add(String.valueOf(role.getRoleId()));
        this.roleMenuService.deleteRoleMenusByRoleId(roleIdList);
        saveRoleMenus(role);

        shiroRealm.clearCache();
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void deleteRoles(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(StringPool.COMMA));
        this.baseMapper.delete(new QueryWrapper<Role>().lambda().in(Role::getRoleId, list));

        this.roleMenuService.deleteRoleMenusByRoleId(list);
        this.userRoleService.deleteUserRolesByRoleId(list);
    }

    private void saveRoleMenus(Role role) {
        if (StringUtils.isNotBlank(role.getMenuIds())) {
            String[] menuIds = role.getMenuIds().split(StringPool.COMMA);
            List<RoleMenu> roleMenus = new ArrayList<>();
            Arrays.stream(menuIds).forEach(menuId -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(Long.valueOf(menuId));
                roleMenu.setRoleId(role.getRoleId());
                roleMenus.add(roleMenu);
            });
            roleMenuService.saveBatch(roleMenus);
        }
    }

}
