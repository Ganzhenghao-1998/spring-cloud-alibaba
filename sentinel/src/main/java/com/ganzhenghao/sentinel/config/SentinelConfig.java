package com.ganzhenghao.sentinel.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 哨兵配置
 *
 * @author Ganzhenghao
 * @version 1.0
 * @date 2021/9/1 13:54
 */
@Configuration
@EnableAspectJAutoProxy
public class SentinelConfig {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

}
