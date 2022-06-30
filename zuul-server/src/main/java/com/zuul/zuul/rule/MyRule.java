package com.zuul.zuul.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Hanlex.Liu on 2019/8/30 09:16.
 * 功能描述 :
 */

@Component
public class MyRule extends AbstractLoadBalancerRule {

    private static final Logger logger = LoggerFactory.getLogger(MyRule.class);


    public Server choose(Object arg0) {  //在重载的choose方法中定义你自己的负载均衡策略
        if(arg0 != null){
            logger.info(arg0.toString());
        }
        ILoadBalancer lb = this.getLoadBalancer();
        List<Server> upServerList = lb.getReachableServers(); //可用服务列表
        List<Server> allServerList = lb.getAllServers();
        upServerList.forEach(server -> {
            System.out.println(server.toString());
        });
        int index = (int)(Math.random() * (upServerList.size()));
        logger.info(upServerList.get(index).toString());
        return upServerList.get(index);
    }


    @Override
    public void initWithNiwsConfig(IClientConfig arg0) {
        // TODO Auto-generated method stub

    }

}

