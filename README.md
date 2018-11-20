## 如梦安全系统
maven、spring boot、spring security、thymeleaf权限管理系统。

需要 jdk1.8

本地用户名：admin、test 

密码：test

## 文档
- sql文件请查看`docs/mysql.sql`。
- [环境划分](https://git.dreamlu.net/dream-vip/dream-docs/src/master/service/service-agreement.md)
- `application.yml`放置公共配置，`application-xxx.yml`放置`差异`性环境的配置。
- 详细文档：https://git.dreamlu.net/dream-vip/dream-docs
- [版本更新说明](docs/version.md)
- [数据字典](docs/dictionary.md)

## 最佳实践
1. 先设计好数据库，注意表名，字段名填写清楚。

2. 运行 `src/test/java/net/dreamlu/generator/MysqlGenerator.java` 中的main方法生成基础代码。

3. 代码生成`jdbc配置`和`模板`详见`test/resources`。

## TODO
- ~~ueditor插件优化，方便个性化配置和指定上传目录。~~

- ~~excel导入导出例子，LuTool中增强导出~~

- ~~异常信息的国际化处理。（思路：跟dream-cloud一样重写error处理，定义规则{error.xxx}直接读取国际化，然后返回）~~

- ~~登录锁定，登录超过n次，锁定账号~~

- ~~字典~~

- 尝试扩展Easyui Accordion

- IE兼容性调试

- Activiti工作流？？？

- mail 邮件

- 定时任务管理

## demo 演示
- 地址：http://demo.dreamlu.net

- 账号vs密码：test、test

## 协议
### 🌹权益
- 允许用于学习、毕设、公司项目、私活、二次开发等。

### 🚫禁止
1. 直接将本项目挂淘宝等商业平台出售。
2. 非界面代码60%以上相识度的二次开源，二次开源需先联系本人。

`注意`：若禁止条款被发现有权追讨19999的授权费。



环境划分：dev（开发）、test（测试）、ontest（线上测试）、prod（正式），默认dev
