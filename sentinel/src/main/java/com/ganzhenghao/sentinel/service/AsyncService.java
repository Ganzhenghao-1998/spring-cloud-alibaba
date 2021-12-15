package com.ganzhenghao.sentinel.service;

import com.alibaba.csp.sentinel.AsyncEntry;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Ganzhenghao
 * @version 1.0
 * @date 2021/9/1 11:43
 */
@Service
public class AsyncService {


    @Async
    public void async(AsyncEntry entry) {

        System.out.println("异步开始!");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            entry.exit();
        }

        System.out.println("异步完成!");

    }

}
