package com.ganzhenghao.sentinel.service;

import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.stereotype.Service;

/**
 * @author Ganzhenghao
 * @version 1.0
 * @date 2021/12/8 10:35
 */
@Service
public class AnnotationReturnService {


    @Trace
    @Tags(
            {
                    @Tag(key = "isThrowException_return", value = "returnedObj"),
                    @Tag(key = "isThrowException_arg[id]", value = "arg[0]")
            }
    )
    public String isThrowException(String id) {
        if (id == null || id.equals("1")) {
            throw new RuntimeException("当id为空或1时,抛出的异常!");
        }else if (id.equalsIgnoreCase("lazy")){
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello Sentinel!";
    }

}
