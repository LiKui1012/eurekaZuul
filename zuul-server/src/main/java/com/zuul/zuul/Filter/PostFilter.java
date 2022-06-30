package com.zuul.zuul.Filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zuul.zuul.Constants.Constants;
import org.springframework.stereotype.Component;

/**
 * Created by Hanlex.Liu on 2019/10/31 09:19.
 * 功能描述 : 验证登录用户过滤器
 */

@Component
public class PostFilter extends BaseFilter {

    /**
     *  过滤器执行的时间
     *  pre 路由器接受请求之前
     *  route 路由器进行路由时
     *  post 路由器发送请求之后
     * @return
     */
    @Override
    public String filterType() {
        return Constants.FilterType.POST.value;
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
        HttpServletResponse response = ctx.getResponse();
        //此处注意只有在POST的Filter中才可以捕获response!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return null;
    }
}
