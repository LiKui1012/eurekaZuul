1.EuerkaServer关闭了自我保护功能 上线需要开启
3.zuul和Hystrix是无缝结合的  不需要引用pom文件 ok
4.log输出日志文件 ok
5.zuul限流 ok
6.zuul的fallBack没有使用 ok
7.使用zuul统计接口耗时，接口请求次数  使用zuul的过滤器可以实现全局接口耗时，aop实现provider3接口访问次数统计和接口耗时 ok
8.使用Ribbon+RestTemplate调用微服务也可以实现负载均衡 ok provider4
9.配置接口请求前缀和服务名隐藏 ok
10.引入jpa 增加分表策略，引入mongo实现连表查询多数据源配置 nook
11.微服务调用之间的熔断 ok
12.将database作为公共包依赖 ok
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

博客地址
https://blog.csdn.net/qq_36720088/article/details/103417202

路由查看
http://localhost:8086/actuator/routes


bug解决myrule一定要加@Scope(value=“prototype”)
假如现在有A、B、C三个服务，当第一次请求A服务的时候可以正常返回对应的A服务，
第二次请求B服务的时候，getLoadBalancer()返回的服务实例还是A服务的实例，
而且重启之后又会一直返回B服务，此时大家都反馈路由错了的问题。
我当时感觉就是当出现线程安全问题的时候才可能出现这种情况

zuul:功能
1.接口过滤：可以对请求统一做检验，如token，请求参数
2.接口添加统一操作：记录zuul处理接口时间等，有点像aop
3.接口路由转发：内部集成ribbon，histryx，可以做到负载均衡请求，请求熔断等操作
熔断只会在接口zuul调用微服务不通的情况触发，如网络延迟或者被调用微服务宕机
4.zuul功能实现，pre，route，error，post。。可以多个pre，route...可以设置执行顺序
route会调用myrule自定义调用微服务策略。

请求路径
1.直接请求
http://localhost:8081/test1/test1
http://localhost:8082/test1/test1
http://localhost:8083/test3/test3
http://localhost:8084/test4/test4
2.zuul请求
http://localhost:8086/pro3/test3/test3
http://localhost:8086/pro3/test3/test4
http://localhost:8086/pro3/test3/testToError
http://localhost:8086/provider4/test4/test4
http://localhost:8086/pro1/test1/test1


公共包：
1.注意删除启动被调用的启动类，maven pulign打包
2.父工程module子工程，子工程partent引用父工程
3.调用方区分使用mapperScan还是jpaEnabledRes，加入被调用方的路劲


待完善，分表，不设置主键自增，增加必须指定id，这样保存的时候需要手动生成一个id
设置主键id，可以保存，但是分表有错误。