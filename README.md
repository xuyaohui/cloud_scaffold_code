# cloud_scaffold_code

基于spring cloud alibaba的微服务改造

目前还不成熟，还在优化当中

目前使用的组件如下：
nacos (注册中心和配制中心)
spring cloud gateway （网关中心）
sentinel （限流）
redis (缓存服务)

其中认证使用Jwt token，登录后分发token ，由网关校验其合法性


附录：
1. nacos使用 服务注册、配置
下载地址：https://github.com/alibaba/nacos

下载后切换到bin目录下执行：
startup.cmd -m standalone
访问地址：http://localhost:8848/nacos/index.html
用户名密码都是nacos


2. Sentinel 限流
下载： https://github.com/alibaba/Sentinel/releases

启动命令：
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
默认用户名和密码都是  sentinel

3. redis下载
下载地址： https://github.com/microsoftarchive/redis/tags

启动命令： redis-server.exe redis.windows.conf
启动时错误：Creating Server TCP listening socket 127.0.0.1:6379: bind: No error

