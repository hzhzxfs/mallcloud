## 简介

一个基于Spring Cloud、Nacos、Seata、Mysql、Redis、RocketMQ、canal、ElasticSearch、minio的微服务B2B2C电商商城系统。


## 系统架构

![65c6eaa99f345e8d035a1dd7.jpg](https://s2.loli.net/2024/02/24/eZi43t1ojxcP2sn.jpg)


## 项目架构

- VO（View Object）：显示层对象，通常是 Web 向模板渲染引擎层传输的对象。
- DTO（Data Transfer Object）：数据传输对象，前端像后台进行传输的对象，类似于param。
- BO（Business Object）：业务对象，内部业务对象，只在内部传递，不对外进行传递。
- Model：模型层，此对象与数据库表结构一一对应，通过 Mapper 层向上传输数据源对象。
- Controller：主要是对外部访问控制进行转发，各类基本参数校验，或者不复用的业务简单处理等。为了简单起见，一些与事务无关的代码也在这里编写。
- FeignClient：由于微服务之间存在互相调用，这里是内部请求的接口。
- Controller：主要是对内部访问控制进行转发，各类基本参数校验，或者不复用的业务简单处理等。为了简单起见，一些与事务无关的代码也在这里编写。
- Service 层：相对具体的业务逻辑服务层。
- Manager 层：通用业务处理层，它有如下特征：
  - 1） 对第三方平台封装的层，预处理返回结果及转化异常信息，适配上层接口。
  - 2） 对 Service 层通用能力的下沉，如缓存方案、中间件通用处理。
  - 3） 与 DAO 层交互，对多个 DAO 的组合复用。
- Mapper持久层：数据访问层，与底层 MySQL进行数据交互。
- Listener：监听 `RocketMQ` 进行处理，有时候会监听`easyexcel`相关数据。

关于`FeignClient`，由于微服务之间存在互相调用，`Feign` 是http协议，理论上是为了解耦，而实际上提供方接口进行修改，调用方却没有进行修改的时候，会造成异常，所以我们抽取出来。还有就是对内暴露的接口，是很多地方都公用的，所以我们还将接口抽取了出了一个模块，方便引用。可以看到`mallcloud-api`这个模块下是所有对内`feign`接口的信息。

## 目录结构

```
mallcloud
├─mallcloud-api -- 内网接口
│  ├─mallcloud-api-auth  -- 授权对内接口
│  ├─mallcloud-api-biz  -- biz对内接口
│  ├─mallcloud-api-leaf  -- 美团分布式id生成接口
│  ├─mallcloud-api-multishop  -- 店铺对内接口
│  ├─mallcloud-api-order  -- 订单对内接口
│  ├─mallcloud-api-platform  -- 平台对内接口
│  ├─mallcloud-api-product  -- 商品对内接口
│  ├─mallcloud-api-rbac  -- 用户角色权限对内接口
│  ├─mallcloud-api-search  -- 搜索对内接口
│  └─mallcloud-api-user  -- 用户对内接口
├─mallcloud-auth  -- 授权校验模块
├─mallcloud-biz  -- mallcloud 业务代码。如图片上传/短信等
├─mallcloud-common -- 一些公共的方法
│  ├─mallcloud-common-cache  -- 缓存相关公共代码
│  ├─mallcloud-common-core  -- 公共模块核心（公共中的公共代码）
│  ├─mallcloud-common-database  -- 数据库连接相关公共代码
│  ├─mallcloud-common-order  -- 订单相关公共代码
│  ├─mallcloud-common-product  -- 商品相关公共代码
│  ├─mallcloud-common-rocketmq  -- rocketmq相关公共代码
│  └─mallcloud-common-security  -- 安全相关公共代码
├─mallcloud-gateway  -- 网关
├─mallcloud-leaf  -- 基于美团leaf的生成id服务
├─mallcloud-multishop  -- 商家端
├─mallcloud-order  -- 订单服务
├─mallcloud-payment  -- 支付服务
├─mallcloud-platform  -- 平台端
├─mallcloud-product  -- 商品服务
├─mallcloud-rbac  -- 用户角色权限模块
├─mallcloud-search  -- 搜索模块
└─mallcloud-user  -- 用户服务
```

## 部署后 API 地址

| 服务                                                 | 地址                    |
| ---------------------------------------------------- |-----------------------|
| mallcloud-gatway 网关服务                           | http://127.0.0.1:8000 |
| mallcloud-auth  授权校验服务                        | http://127.0.0.1:9101 |
| mallcloud-biz     业务代码服务（如图片上传/短信等） | http://127.0.0.1:9000 |
| mallcloud-leaf   基于美团leaf的生成id服务           | http://127.0.0.1:9100 |
| mallcloud-multishop 商家服务                        | http://127.0.0.1:9103 |
| mallcloud-order         订单服务                    | http://127.0.0.1:9106 |
| mallcloud-payment   支付服务                        | http://127.0.0.1:9113 |
| mallcloud-product    商品服务                       | http://127.0.0.1:9114 |
| mallcloud-rbac          用户角色服务                | http://127.0.0.1:9102 |
| mallcloud-search      搜索服务                      | http://127.0.0.1:9108 |
| mallcloud-user          用户服务                    | http://127.0.0.1:9105 |
