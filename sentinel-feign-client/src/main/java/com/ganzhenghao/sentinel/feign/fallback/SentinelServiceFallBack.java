package com.ganzhenghao.sentinel.feign.fallback;

import com.ganzhenghao.sentinel.feign.SentinelService;
import org.springframework.stereotype.Component;

/**
 * @author Ganzhenghao
 * @version 1.0
 * @date 2021/9/1 16:03
 */
@Component
public class SentinelServiceFallBack implements SentinelService {

    @Override
    public String sentinel_exception() {
        return null;
    }

    @Override
    public String sentinel_boolean() {
        return null;
    }

    @Override
    public String sentinel_annotation(String id) {
        return "调用端服务降级!";
    }

    @Override
    public String sentinel_async() {
        return null;
    }
}
