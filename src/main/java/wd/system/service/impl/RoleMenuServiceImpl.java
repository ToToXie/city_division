package wd.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wd.system.entity.RoleMenu;
import wd.system.mapper.RoleMenuMapper;
import wd.system.service.IRoleMenuService;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author WangDong
 * @since 2020-04-10
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {
    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleMenusByRoleId(List<String> roleIds) {
        this.baseMapper.delete(new QueryWrapper<RoleMenu>().lambda().in(RoleMenu::getRoleId, roleIds));
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleMenusByMenuId(List<String> menuIds) {
        this.baseMapper.delete(new QueryWrapper<RoleMenu>().lambda().in(RoleMenu::getMenuId, menuIds));
    }
}
