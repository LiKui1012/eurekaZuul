package com.zuul.zuul.Filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zuul.zuul.Constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Created by Hanlex.Liu on 2019/10/31 09:19.
 * 功能描述 : 验证登录用户过滤器
 */

@Component
public class PreFilter extends BaseFilter {


    @Autowired
    private DiscoveryClient discoveryClient;


    /**
     *  过滤器执行的时间
     *  pre 路由器接受请求之前
     *  route 路由器进行路由时
     *  post 路由器发送请求之后
     * @return
     */
    @Override
    public String filterType() {
        return Constants.FilterType.PRE.value;
    }

    /**
     * 返回数字代表多过过滤器(过滤器链)中的过滤器的执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回布尔值代表当前请求是否需要路由
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response= ctx.getResponse();

        // 跨域请求一共会进行两次请求 先发送options 是否可以请求
        // 调试可发现一共拦截两次 第一次请求为options，第二次为正常的请求 eg：get请求

        //放通所有第一次options请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())){
            ctx.setSendZuulResponse(false); //验证请求不进行路由
            ctx.setResponseStatusCode(HttpStatus.OK.value());//返回验证成功的状态码
            ctx.set("isSuccess", true);
            return null;
        }

        if(true){//通过 标识当前拦截器不拦截
            ctx.setSendZuulResponse(true);
        }else {//不通过 标识当前拦截器拦截
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());//设置http状态码401
            ctx.setResponseBody("authentication failure : request is not allowed");//设置响应
        }
        return null;
    }
}
