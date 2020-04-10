package wd.city_division.test.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.Arrays;
import java.util.List;

/**
 * @program: city_division
 * @description:
 * @author: wd
 * @create: 2020-04-10 22:10
 **/

public class JsonTest {
    public static void main(String[] args) {
        Integer[] one = {1,2,3,4,5};
        List<Integer> integers = Arrays.asList(one);
        System.out.println(JSON.toJSONString(one));
        System.out.println(JSON.toJSONString(integers));
        System.out.println("----------------------");
        String string = JSONArray.toJSONString(one);
        System.out.println(JSONArray.toJSONString(one));
        System.out.println(JSONArray.toJSONString(integers));
        System.out.println(JSON.toJSONString(integers));
//        System.out.println(JSONArray.parse(one));
//        System.out.println(JSONArray.parseArray(one));
//        System.out.println(JSONArray.parseObject(one));

    }
}
