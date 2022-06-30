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

    @Override
    public String filterType() {
        return Constants.FilterType.ERROR.value;
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        //异常捕捉
        //Throwable throwable = ctx.getThrowable();
        //ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        //ctx.set("error.exception", throwable.getCause());
        //Log log = threadLocals.get();
        //log.setErrMsg("{\"err_code\":\"" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR + "\",\"err_msg\":\"" + throwable.getCause() + "\"}");
        return null;
    }
}
