package com.provider.provider3rd.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Hanlex.Liu on 2019/12/5 16:10.
 * 功能描述 :
 */

@FeignClient(name = "provider1")//这里的 provider1 是指provider1注册在eureka上的服务名
public interface Provider1Client {

    @GetMapping("test1/test1")//这里指的是具体的provider1的接口
    String test1();


    //请求方式和参数传递方式要根据服务提供方的接口设定来设定:
    //    get方式 使用@RequestParam方式传参,接收方接收参数和正常get请求一样 参数拼接在url后传递
    //    @RequestMapping(value = "/demo/getHost", method = RequestMethod.GET, produces = "application/json")
    //    public String getHost(@RequestParam("name") String name);
    //
    //    post方式 使用@RequestParam方式传参,接收方接收参数和get请求一样 参数拼接在url后传递
    //    @RequestMapping(value = "/demo/postPerson", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    //    public Person postPerson(@RequestParam("name") String name);
    //
    //    post方式 使用@RequestBody方式传参,接收方接收参数需要在参数前添加 @RequestBody 注解接受参数
    //    @RequestMapping(value = "/body/postPerson", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    //    public Person postPerson(@RequestBody Person person);
    //
    //    get方式 使用@RequestParam方式传递参数"name",接收方接收参数和正常get请求一样 参数拼接在url后传递,并将age参数放在request的header中进行传递
    //    @RequestMapping(value = "/head/getHost", method = RequestMethod.GET, produces = "application/json")
    //    public String getHost(@RequestParam("name") String name, @RequestHeader("age") Integer age);

}
