package wd.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wd.system.entity.UserRole;
import wd.system.mapper.UserRoleMapper;
import wd.system.service.IUserRoleService;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author WangDong
 * @since 2020-04-10
 */
@Service

public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void deleteUserRolesByRoleId(List<String> roleIds) {
        this.baseMapper.delete(new QueryWrapper<UserRole>().lambda().in(UserRole::getRoleId, roleIds));
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void deleteUserRolesByUserId(List<String> userIds) {
        this.baseMapper.delete(new QueryWrapper<UserRole>().lambda().in(UserRole::getUserId, userIds));
    }
}
