1.EuerkaServer关闭了自我保护功能 上线需要开启
3.zuul和Hystrix是无缝结合的  不需要引用pom文件 ok
4.log输出日志文件
5.zuul限流 ok
6.zuul的fallBack没有使用 ok
7.使用zuul统计接口耗时，接口请求次数  使用zuul的过滤器可以实现全局接口耗时，aop实现provider3接口访问次数统计和接口耗时 ok
8.使用Ribbon+RestTemplate调用微服务也可以实现负载均衡 ok provider4
9.配置接口请求前缀和服务名隐藏 ok
10.引入mysql 增加分表策略
请求路径
1.可以直接请求
http://localhost:8083/test3/test3 
http://localhost:8081/test1/test1
http://localhost:8082/test1/test1
2.可以隐藏服务名请求
http://localhost:8086/pro3/test3/test3
3.不影藏服务名请求
http://localhost:8086/provider4/test4/test4
4.负责均衡
http://localhost:8086/pro3/test3/test4
http://localhost:8086/provider4/test4/test4



https://blog.csdn.net/qq_36720088/article/details/103417202



路由查看
http://localhost:8086/actuator/routes