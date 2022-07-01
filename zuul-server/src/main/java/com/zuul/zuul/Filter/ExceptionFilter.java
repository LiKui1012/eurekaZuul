package com.zuul.zuul.Filter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zuul.zuul.Constants.Constants;
import org.springframework.stereotype.Component;

/**
 * Created by Hanlex.Liu on 2019/11/28 08:55.
 * 功能描述 : 异常统一处理(不包括post异常)
 */

@Component
public class ExceptionFilter extends BaseFilter {

    /**
     * Created by Hanlex.Liu on 2019/11/28 08:55.
     * 功能描述 :方法返回字符串数据，代表当前过滤器的类型。可选值有-pre, route, post, error
     */
    @Override
    public String filterType() {
        return Constants.FilterType.ERROR.value;
    }

    /**
     * Created by Hanlex.Liu on 2019/11/28 08:55.
     * 功能描述 : 返回int数据，用于为同种类filterType的多个过滤器定制执行顺序，返回值越小，执行顺序越优先。
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * Created by Hanlex.Liu on 2019/11/28 08:55.
     * 功能描述 :返回boolean数据，代表当前filter是否生效
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }
    /**
     * Created by Hanlex.Liu on 2019/11/28 08:55.
     * 功能描述 :
     * 具体的过滤执行逻辑。
     * 如pre类型的过滤器，可以通过对请求的验证来决定是否将请求路由到服务上；
     * 如post类型的过滤器，可以对服务响应结果做加工处理（如为每个响应增加footer数据）。
     * Java知音公众号内回复“后端面试”， 送你一份Java面试题宝典
     */


    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        //异常捕捉
        Throwable throwable = ctx.getThrowable();
        System.out.println("异常过滤"+throwable);
        //ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        //ctx.set("error.exception", throwable.getCause());
        //Log log = threadLocals.get();
        //log.setErrMsg("{\"err_code\":\"" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR + "\",\"err_msg\":\"" + throwable.getCause() + "\"}");
        return null;
    }
}
