package com.ganzhenghao.sentinel.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ganzhenghao
 * @version 1.0
 * @date 2021/9/1 15:10
 */
@Configuration
public class OpenFeignConfig {

/*    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }*/


    /**
     * feign的日志级别配置
     * NONE [性能最佳,适用于生产]: 不记录任何日志(默认值)
     * BASIC [适用于生产环境追踪问题]: 仅记录请求方法,URL,响应状态代码以及执行时间
     * HEADERS : 记录BASIC级别的基础上,记录请求和响应的header
     * FULL[比较适用于开发及测试环境定位问题]: 记录请求和相应的header,body和元数据
     *
     * @return {@link Logger.Level}
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }



}
