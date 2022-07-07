package com.provider.provider3rd.feignClient;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class Provider1ClientFallBack implements FallbackFactory<Provider1Client> {
    @Override
    public Provider1Client create(Throwable cause) {
        return new Provider1Client() {
            @Override
            public String  test1() {
                System.out.println("provider1服务调用异常");
                return "provider1服务调用异常";
            }
        };
    }
}
