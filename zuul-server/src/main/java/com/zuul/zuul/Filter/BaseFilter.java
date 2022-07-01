package com.zuul.zuul.Filter;

import com.netflix.zuul.ZuulFilter;

/**
 * Created by Hanlex.Liu on 2019/11/28 09:24.
 * 功能描述 :
 * pre - 前置过滤器，在请求被路由前执行，通常用于处理身份认证，日志记录等；
 * route - 在路由执行后，服务调用前被调用；
 * error - 任意一个filter发生异常的时候执行或远程服务调用没有反馈的时候执行（超时），通常用于处理异常；
 * post - 在route或error执行后被调用，一般用于收集服务信息，统计服务性能指标等，也可以对response结果做特殊处理。
 */
public abstract class BaseFilter extends ZuulFilter {

    //为什么创建一个BaseFilter的抽象类呢,因为在这里,我们可以定义一个在所有继承BaseFilter的子类中使用的变量,
    //比如这里的ThreadLocal对象,我是用来记录日志的,它是针对线程的变量,也就是说当前这里的threadLocals是线程安全的,不会因为多线程冲突
    //static final ThreadLocal<Log> threadLocals = new ThreadLocal<Log>();



    //使用方法
    //第一步:创建一个日志对象,具体参数根据项目要求定义,
    //Log log = new Log();
    //第二部:将log放进当前线程,由线程携带,保证线程安全
    //threadLocals.set(log);
    //第三步:修改log内容:
    //Log log = threadLocals.get();
    //log.setErrMsg("错误信息!");

    //此三步在先后顺序正确的情况下,可以在任意继承本抽象类的子类中使用
}
