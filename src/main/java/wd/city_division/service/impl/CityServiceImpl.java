package wd.city_division.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wd.city_division.entity.City;
import wd.city_division.mapper.CityMapper;
import wd.city_division.service.ICityService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 三级行政划分数据 服务实现类
 * </p>
 *
 * @author WangDong
 * @since 2020-04-02
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {
    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);
    @Autowired
    private CityMapper cityMapper;
    private RestTemplate restTemplate;
    public static final String url = "https://mxnzp.com/api/address/list?app_id=pnjgrriit6wghnlo&app_secret=YXBoenBlWnZmanhLWTRxenNCL1p3Zz09";
    @Override
    public List<City> init() throws Exception {
        if(count() > 0){
            throw new Exception("数据已经导入！ 无需再点击");
        }
        restTemplate = new RestTemplate();
        JSONObject forObject = restTemplate.getForObject(url, JSONObject.class);
        JSONArray jsonArray = forObject.getJSONArray("data");
        City[] cities = new City[jsonArray.size()];
        List<City> cities1 = jsonArray.toJavaList(City.class);
        List<City> list = new ArrayList<>(4000);
        for (City it :cities1 ) {
            it.setLevel(1);
            if(it.getPchilds() != null){
                list.add(it);
                it.getPchilds().stream().forEach(item -> {
                    item.setLevel(2);
                    item.setParentCode(it.getCode());
                    list.add(item);
                    if(item.getCchilds() != null){
                        item.getCchilds().stream().forEach(one -> {
                            one.setLevel(3);
                            one.setParentCode(item.getCode());
                            list.add(one);});
                    }
                });
            }
        }

        return list;
    }

    @Override
//    @Transactional(rollbackFor = Throwable.class)
    public List<City> test(String code) {
        QueryWrapper<City> ew = new QueryWrapper();
        ew.eq("code" ,code );
        City one = cityMapper.selectOne(ew);
        log.warn("+++++++++++++++++++");

        City two = cityMapper.selectOne(ew);
        log.warn("+++++++++++++++++++");
//        Assert.isTrue(one == two,"他们不相等了");

        return null;
    }

    /**
     * @Description: 根据编码查询某一地区及以下
     **/
    @Override
    public List<City> listByCode(String code) {
        City city = new City();
        QueryWrapper<City> ew = new QueryWrapper();
//        ew.select("select id ,name");
        ew.eq("code" ,code );
        City one = cityMapper.selectOne(ew);
        List<City> list;
        if(one == null) {
            list = cityMapper.getAll();
        }else {
            list = cityMapper.listChildsById(one.getId());
        }
//        logger.info(list.toString());
        return list;
    }
    @Override
    public List<City> listByName(String name) {
        City city = new City();
        QueryWrapper<City> ew = new QueryWrapper();
//        ew.select("select id ,name");
        ew.like("name", name+"%");
        List<City> cities = cityMapper.selectList(ew);
//        cities = cities.stream().sorted((a, b) -> b.getLevel() - a.getLevel()).collect(Collectors.toList());
        Collections.sort(cities, (a, b) -> b.getLevel() - a.getLevel());
        List<City> list = new ArrayList<>(cities.size());

        for (City one : cities) {
            list.addAll(cityMapper.listChildsById(one.getId()));
        }
        return list;
    }
}
