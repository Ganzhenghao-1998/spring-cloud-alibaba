package com.ganzhenghao.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取Nacos配置中心的值
 *
 * @author Ganzhenghao
 * @version 1.0
 * @date 2021/8/31 11:40
 */
@RequestMapping("/nacos")
@RefreshScope
@RestController
public class NacosConfigController {

    @Value("${myname}")
    private String name;

    @GetMapping("/get")
    public Map<String,String> getValue() {
        Map<String,String> map = new HashMap<>();
        map.put("name", name);
        return map;
    }

    @GetMapping("/index")
    public String getName(){
        return "a";
    }



}
