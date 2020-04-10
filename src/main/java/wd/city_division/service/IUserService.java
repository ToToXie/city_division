package wd.city_division.service;

import wd.city_division.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author WangDong
 * @since 2020-04-10
 */
public interface IUserService extends IService<User> {
    User findByName(String username);

    /**
     * 注册用户
     *
     * @param username 用户名
     * @param password 密码
     */
    void regist(String username, String password);
}
