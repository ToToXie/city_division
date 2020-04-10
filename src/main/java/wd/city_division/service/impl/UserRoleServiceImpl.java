package wd.city_division.service.impl;

import wd.city_division.entity.UserRole;
import wd.city_division.mapper.UserRoleMapper;
import wd.city_division.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
