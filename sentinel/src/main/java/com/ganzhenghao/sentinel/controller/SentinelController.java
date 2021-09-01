package com.ganzhenghao.sentinel.controller;

import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 哨兵控制器
 *
 * @author Ganzhenghao
 * @version 1.0
 * @date 2021/8/31 17:07
 */
@RequestMapping("/sentinel")
@RestController
public class SentinelController {


    /**
     * 限流规则 - QPS
     *
     * @return {@link Object}
     */
    @GetMapping("/qps")
    public Object sentinel_qps() {
        try{
           SphU.entry("Hello");
            return "Hello Sentinel !";
        } catch (BlockException e) {
            return "Blocked";
        }

    }

    /**
     * Sentinel限流规则创建
     */
    @PostConstruct
    public void initFlowRule() {
        // 1. 创建存放限流规则的集合
        List<FlowRule> rules = new ArrayList<>();
        // 2. 创建限流规则
        FlowRule rule = new FlowRule();
        // 2.1 定义资源, 表示Sentinel会对哪个资源生效
        rule.setResource("Hello");
        // 2.2 定义限流规则类型  RuleConstant.FLOW_GRADE_QPS : QPS限流类型
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(2);//代表QPS每秒通过的请求个数
        // 3. 存入集合
        rules.add(rule);
        // 4. 加载先流规则
        FlowRuleManager.loadRules(rules);
    }


}
