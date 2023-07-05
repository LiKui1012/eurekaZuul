package com.provider.provider3rd.controller;

import javax.annotation.Resource;

import com.provider.provider3rd.feignClient.Provider1Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hanlex.Liu on 2019/11/5 16:58.
 * 功能描述 :
 */

@RestController
@RequestMapping("/test3")
public class TestController {

    @Resource
    private Provider1Client provider1Client;//引入privider1对应的 feignClient

    @GetMapping("test3")
    public Object test(String count){
        System.out.println(
                "进入test3"
        );
        int a=0;
        int b=1;
        int c=a/b;
        System.out.println("1/0="+c);
        //这里的设置2秒统计接口耗时，同时也要修改xml中的ribbon连接时间，因为zuul的超时是集成ribbon的，连接时间，处理时间默认分别是0.5 和1秒
       if(count.equals("2")){
           try {
               Thread.sleep(2 * 1000);
           }catch (InterruptedException e){
           }
           System.out.println(
                   "等待2秒"
           );
       }
        if(count.equals("3")){
            try {
                Thread.sleep(3 * 1000);
            }catch (InterruptedException e){
            }
            System.out.println(
                    "等待3秒"
            );
        }

        if(count.equals("4")){
            try {
                Thread.sleep(4 * 1000);
            }catch (InterruptedException e){
            }
            System.out.println(
                    "等待4秒"
            );
        }

        if(count.equals("5")){
            try {
                Thread.sleep(5 * 1000);
            }catch (InterruptedException e){
            }
            System.out.println(
                    "等待5秒"
            );
        }


        if(count.equals("6")){
            try {
                Thread.sleep(6 * 1000);
            }catch (InterruptedException e){
            }
            System.out.println(
                    "等待6秒"
            );
        }

        if(count.equals("7")){
            try {
                Thread.sleep(7 * 1000);
            }catch (InterruptedException e){
            }
            System.out.println(
                    "等待7秒"
            );
        }

        if(count.equals("8")){
            try {
                Thread.sleep(8 * 1000);
            }catch (InterruptedException e){
            }
            System.out.println(
                    "等待8秒"
            );
        }

        if(count.equals("9")){
            try {
                Thread.sleep(9 * 1000);
            }catch (InterruptedException e){
            }
            System.out.println(
                    "等待9秒"
            );
        }

        //只会返回异常，zuul不会处理被调用者的异常，只会当被调用者宕机才会触发熔断
        int d=b/a;
        System.out.println("0/1="+d);

        return "789JQK";
    }

    @GetMapping("test4")
    public Object test4(){
        String provider1Str = provider1Client.test1(); //使用 feignClient 中的方法 请求 provider1 中的接口
        return "test4" + provider1Str;
    }


    /**
     * 模拟错误访问
     * @return
     */
    @RequestMapping("/testToError")
    public String testToError() throws Exception{
        try {
            Integer.parseInt("slfda");
            return "success";
        }catch (Exception e){
            throw e;
        }
    }


}
