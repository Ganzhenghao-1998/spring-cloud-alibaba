package com.ganzhenghao.sentinel.controller;

import com.ganzhenghao.sentinel.feign.SentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 哨兵假装客户端控制器
 *
 * @author Ganzhenghao
 * @version 1.0
 * @date 2021/9/1 15:11
 */
@RequestMapping("/sentinel-client")
@RestController
public class SentinelFeignClientController {

    @Qualifier("com.ganzhenghao.sentinel.feign.SentinelService")
    @Autowired
    private SentinelService sentinelService;

    @GetMapping("/ex")
    public Object getEx() {

        return sentinelService.sentinel_exception();
        //return restTemplate.getForObject("http://sentinel/sentinel/ex", String.class);
    }

    @GetMapping("/anno/{id}")
    public Object getAnno(@PathVariable("id") String id) {
        return sentinelService.sentinel_annotation(id);
    }




}
