package wd.city_division.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wd.city_division.annotation.MyRateLimiter;
import wd.city_division.annotation.WebLog;
import wd.city_division.common.FebsResponse;
import wd.city_division.entity.User;
import wd.city_division.exception.FebsException;
import wd.city_division.service.IUserService;
import wd.city_division.util.Md5Util;

import javax.validation.constraints.NotBlank;

/**
 * @author MrBird
 */
//@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "登陆相关")
public class LoginController extends BaseController {

    private final IUserService userService;
//    private final ValidateCodeService validateCodeService;
//    private final ILoginLogService loginLogService;

    @PostMapping("login")
    @ApiOperation("登录用户")
    @MyRateLimiter(3)
    @WebLog(description = "登录操作")
    public FebsResponse login(
            @RequestParam String username,
            @RequestParam String password,
//            @NotBlank(message = "{required}") String verifyCode,
            @RequestParam boolean rememberMe
//            , HttpServletRequest request
    ) throws FebsException {
//        HttpSession session = request.getSession();
//        validateCodeService.check(session.getId(), verifyCode);
        password = Md5Util.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        super.login(token);
        // 保存登录日志
//        LoginLog loginLog = new LoginLog();
//        loginLog.setUsername(username);
//        loginLog.setSystemBrowserInfo();
//        this.loginLogService.saveLoginLog(loginLog);

        return new FebsResponse().success();
    }


    @PostMapping("regist")
    @ApiOperation("注册用户")
    @WebLog(description = "注册用户")
    public FebsResponse regist(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password) throws FebsException {
        User user = userService.findByName(username);
        if (user != null) {
            throw new FebsException("该用户名已存在");
        }
        this.userService.regist(username, password);
        return new FebsResponse().success();
    }

//    @GetMapping("index/{username}")
//    public FebsResponse index(@NotBlank(message = "{required}") @PathVariable String username) {
//        // 更新登录时间
//        this.userService.updateLoginTime(username);
//        Map<String, Object> data = new HashMap<>(5);
//        // 获取系统访问记录
//        Long totalVisitCount = this.loginLogService.findTotalVisitCount();
//        data.put("totalVisitCount", totalVisitCount);
//        Long todayVisitCount = this.loginLogService.findTodayVisitCount();
//        data.put("todayVisitCount", todayVisitCount);
//        Long todayIp = this.loginLogService.findTodayIp();
//        data.put("todayIp", todayIp);
//        // 获取近期系统访问记录
//        List<Map<String, Object>> lastSevenVisitCount = this.loginLogService.findLastSevenDaysVisitCount(null);
//        data.put("lastSevenVisitCount", lastSevenVisitCount);
//        User param = new User();
//        param.setUsername(username);
//        List<Map<String, Object>> lastSevenUserVisitCount = this.loginLogService.findLastSevenDaysVisitCount(param);
//        data.put("lastSevenUserVisitCount", lastSevenUserVisitCount);
//        return new FebsResponse().success().data(data);
//    }

//    @GetMapping("images/captcha")
//    @Limit(key = "get_captcha", period = 60, count = 10, name = "获取验证码", prefix = "limit")
//    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, FebsException {
//        validateCodeService.create(request, response);
//    }
}
