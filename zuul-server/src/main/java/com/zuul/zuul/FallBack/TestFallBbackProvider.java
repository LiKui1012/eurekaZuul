package com.zuul.zuul.FallBack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 如果需要在Zuul网关服务中增加容错处理fallback，需要实现接口ZuulFallbackProvider
 * spring-cloud框架，在Edgware版本(包括)之后，声明接口ZuulFallbackProvider过期失效，
 * 提供了新的ZuulFallbackProvider的子接口 - FallbackProvider
 * 在老版本中提供的ZuulFallbackProvider中，定义了两个方法。
 * - String getRoute()
 * 当前的fallback容错处理逻辑处理的是哪一个服务。可以使用通配符‘*’代表为全部的服务提供容错处理。
 * 如果只为某一个服务提供容错，返回对应服务的spring.application.name值。
 * - ClientHttpResponse fallbackResponse()
 * 当服务发生错误的时候，如何容错。
 * 新版本中提供的FallbackProvider提供了新的方法。
 * - ClientHttpResponse fallbackResponse(Throwable cause)
 * 如果使用新版本中定义的接口来做容错处理，容错处理逻辑，只运行子接口中定义的新方法。也就是有参方法。
 * 是为远程服务发生异常的时候，通过异常的类型来运行不同的容错逻辑。
 */
@Component
@Slf4j
public class TestFallBbackProvider implements FallbackProvider {
    /**
     * return - 返回fallback处理哪一个服务。返回的是服务的名称
     * 推荐 - 为指定的服务定义特性化的fallback逻辑。
     * 推荐 - 提供一个处理所有服务的fallback逻辑。
     * 好处 - 服务某个服务发生超时，那么指定的fallback逻辑执行。如果有新服务上线，未提供fallback逻辑，有一个通用的。
     */
    /**
     * 返回值表示需要针对此微服务进行回退处理(必须是在注册中心中的service-id).
     * Zuul目前只支持服务级别的熔断，对具体某个URL的熔断暂不支持
     * @return
     */

    /**
     * 重要 该类在项目启动的时候初始化调用getRoute(),在zuul 过滤route 之后，在post中，post到客户端之前处理fallbackResponse
     * 这里只是对于服务请求不通的处理（熔断），并没有对调用服务的异常进行处理，调用服务的异常由自己处理
     * 举例，如果getRoute 返回provider3，表示对provider3进行熔断，如果provider3宕机则返回自定义的处理结果，
     * 如果provider1宕机则不熔断，返回500报错
     * @return
     */
    @Override
    public String getRoute() {
       log.info("info日志");
       log.error("error日志");
        //return "eureka-client";
        //服务Id ，若需要所有服务调用都支持回退，返回null 或者 * 即可
//        return "*";
        return "provider3";
    }


    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        log.info("route路由:"+route);
        return new ClientHttpResponse() {
            /**
             * 客户端向网关发送服务成功，网关向api服务请求失败，不应该把api的404 500 等问题抛给客户端
             * 网关和api服务对客户端来说都是黑盒子。
             * @return
             * @throws IOException
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }
            @Override
            public String getStatusText() throws IOException {
                return "{code:0,message:\"服务器异常!\"}";
            }
            @Override
            public void close() {
            }

            /**
             * 微服务出现宕机后，客户端再次请求就会返回fallback中的预设值
             * @return
             * @throws IOException
             */

            @Override
            public InputStream getBody() throws IOException {
                //服务异常时，输出此处内容，并打印错误日志
                log.error("error appear ");
                return new ByteArrayInputStream(getStatusText().getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }

}