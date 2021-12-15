package com.ganzhenghao.sentinel.controller;

import com.alibaba.csp.sentinel.AsyncEntry;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ganzhenghao.sentinel.service.AnnotationReturnService;
import com.ganzhenghao.sentinel.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 哨兵控制器
 *
 * @author Ganzhenghao
 * @version 1.0
 * @date 2021/8/31 17:07
 */
@RequestMapping("/sentinel")
@RestController
@Slf4j
public class SentinelController {


    @Autowired
    private AsyncService asyncService;
    @Autowired
    private AnnotationReturnService annotationReturnService;

    /**
     * 抛出异常的方式 定义资源 SphU
     *
     * @return {@link Object}
     */
    @GetMapping("/ex")
    public String sentinel_exception() {
        try (Entry entry = SphU.entry("exception")) {
            return "Hello Sentinel!";
        } catch (BlockException e) {
            return "Blocked By Exception";
        }
    }

    /**
     * 布尔方式定义资源 使用的 SphO
     *
     * @return {@link Object}
     */
    @GetMapping("/bool")
    public String sentinel_boolean() {
        // 资源名可使用任意有业务语义的字符串
        if (SphO.entry("boolean")) {
            // 务必保证finally会被执行
            try {
                /*
                  被保护的业务逻辑
                 */
                return "Hello Sentinel!";
            } finally {
                SphO.exit();
            }
        } else {
            // 资源访问阻止，被限流或被降级
            // 进行相应的处理操作
            return "Blocked By Boolean!";
        }
    }

    // 原本的业务方法.
    @SentinelResource(
            value = "annotation",
            blockHandler = "sentinel_annotation_blockHandler",
            fallback = "sentinel_annotation_fallback"
    )
    @GetMapping("/anno/{id}")
    public String sentinel_annotation(@PathVariable String id) {

        return annotationReturnService.isThrowException(id);
    }


    // blockHandler 函数，原方法调用被限流/降级/系统保护的时候调用
    public String sentinel_annotation_blockHandler(@PathVariable String id,BlockException ex) {
        log.error("sentinel_annotation_blockHandler!");
        return "Blocked By Annotation!";
    }

    // blockHandler 函数，原方法调用被限流/降级/系统保护的时候调用
    public String sentinel_annotation_fallback(@PathVariable String id,BlockException ex) {
        log.error("sentinel_annotation_fallback!");
        return "Blocked By Annotation!";
    }


    @GetMapping("/async")
    public String sentinel_async() {
        try {
            AsyncEntry entry = SphU.asyncEntry("async");
            // 异步调用.
            asyncService.async(entry);
            return "Hello Sentinel!";
        } catch (BlockException ex) {
            // Request blocked.
            // Handle the exception (e.g. retry or fallback).
            return "Blocked By Async!";
        }

    }


}
