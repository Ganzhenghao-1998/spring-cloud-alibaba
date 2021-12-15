package com.ganzhenghao.sentinel.feign;

import com.ganzhenghao.sentinel.feign.fallback.SentinelServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Ganzhenghao
 * @version 1.0
 * @date 2021/9/1 15:14
 */
@FeignClient(value = "sentinel"
        ,fallback = SentinelServiceFallBack.class
        ,path = "/sentinel"
)
public interface SentinelService {

    /**
     * 抛出异常的方式 定义资源 SphU
     * @return {@link Object}
     */
    @GetMapping("/ex")
    String  sentinel_exception();


    /**
     * 哨兵布尔
     *
     * @return {@link String}
     */
    @GetMapping("/bool")
    String sentinel_boolean();


    /**
     * 哨兵注释
     *
     * @param id id
     * @return {@link String}
     */
    @GetMapping("/anno/{id}")
    String sentinel_annotation(@PathVariable("id") String id);

    /**
     * 哨兵异步
     *
     * @return {@link String}
     */
    @GetMapping("/async")
    String sentinel_async();

}
