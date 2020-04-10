package wd.city_division.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wd.city_division.config.ShiroRealm;
import wd.city_division.entity.FebsConstant;
import wd.city_division.entity.User;
import wd.city_division.entity.UserRole;
import wd.city_division.mapper.UserMapper;
import wd.city_division.service.IUserRoleService;
import wd.city_division.service.IUserService;

import java.util.Date;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author WangDong
 * @since 2020-04-10
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final IUserRoleService userRoleService;
    private final ShiroRealm shiroRealm;

    @Override
    public User findByName(String username) {
        QueryWrapper<User> ew = new QueryWrapper();
        ew.eq("username", username);
        return this.baseMapper.selectOne(ew);
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void regist(String username, String password) {
        User user = new User();
//        user.setPassword(Md5Util.encrypt(username, password));
        user.setPassword(password);
        user.setUsername(username);
        user.setCreateTime(new Date());
        user.setStatus(User.STATUS_VALID);
        user.setSex(User.SEX_UNKNOW);
        user.setAvatar(User.DEFAULT_AVATAR);
        user.setTheme(User.THEME_BLACK);
        user.setIsTab(User.TAB_OPEN);
        user.setDescription("注册用户");
        this.save(user);

        UserRole ur = new UserRole();
        ur.setUserId(user.getUserId());
        ur.setRoleId(FebsConstant.REGISTER_ROLE_ID);
        this.userRoleService.save(ur);
    }
}
