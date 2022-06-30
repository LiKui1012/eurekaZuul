package com.zuul.zuul.Constants;

/**
 * Created by Hanlex.Liu on 2019/10/31 09:31.
 * 功能描述 : 全局静态枚举
 */

public class Constants {


    /**
     * zuul 过滤器 过滤方式
     */
    public enum FilterType {

        PRE("pre"),
        ROUTE("route"),
        POST("post"),
        ERROR("error");

        public String value;

        FilterType(String value){
            this.value = value;
        }
    }

    /**
     * token相关
     */
    public enum TokenParam {

        TOKEN_HEADER("Authorization"),
        TOKEN_PREFIX("Bearer ");

        public String value;

        TokenParam(String value){
            this.value = value;
        }
    }

}
