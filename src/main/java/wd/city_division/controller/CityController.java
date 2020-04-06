package wd.city_division.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wd.city_division.aop.MyRateLimiter;
import wd.city_division.aop.WebLog;
import wd.city_division.common.ApiResponse;
import wd.city_division.entity.City;
import wd.city_division.service.ICityService;

import java.util.List;

/**
 * <p>
 * 三级行政划分数据 前端控制器
 * </p>
 *
 * @author WangDong
 * @since 2020-04-02
 */
@RestController
@RequestMapping("/city")
@Api(tags = "三级行政划分")
@Slf4j
public class CityController {
    @Autowired
    private ICityService cityService;
    @GetMapping("/init")
    @ApiOperation("数据准备,插入数据")
    @WebLog(description = "数据准备,插入数据")
    @MyRateLimiter(value = 1,timeout = 300)
    public ApiResponse initForData(){
        log.info("数据准备,插入数据");
        List<City> init = null;
        try {
            init = cityService.init();
            boolean b = cityService.saveBatch(init,init.size());
        } catch (Exception e) {
            log.info("不必多次插入" );
        }
        return ApiResponse.<String>builder().code(200)
                .message("操作成功")
                .data("数据插入完成")
                .build();
    }
    @GetMapping("/code")
    @ApiOperation("根据编码查询下级地区")
    @WebLog(description = "根据编码查询下级地区")
    public String getByCode(@RequestParam(defaultValue = "-1") String code){
        List<City> cities = cityService.listByCode(code);
        return cities.toString();
    }
    @GetMapping("/name")
    @ApiOperation("根据关键字(省、市、县)模糊查询下级地区")
    @WebLog(description = "根据关键字(省、市、县)模糊查询下级地区")
    public String getByName(@RequestParam(defaultValue = "全国") String name){
        List<City> cities = cityService.listByName(name);
        return cities.toString();
    }
    @GetMapping("/test")
    @ApiOperation("测试缓存")
    @WebLog(description = "测试缓存")
    public String test(@RequestParam(defaultValue = "-1") String code){
        List<City> cities = cityService.test(code);
        return " --- ";
    }


}

