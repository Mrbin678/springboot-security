## 版本更新说明
docs目录下`mysql.sql`默认为`全量sql`，`update_时间.sql`为`老版本用户`增量sql。

### 注意`
- 跨多个版本，`update_时间.sql`对应的sql都需要执行，
- `dream-security`版本号和`lutool`保持一致。

### v1.0.0 2018-05-01
- lutool不再内置log4j2（项目中可以根据自己的喜好修改）。
- 修改默认的`logging.pattern.console`，添加行号和方法名打印（方便排插问题）。
- lutool添加`Swagger2`自动配置。
- lutool内置控制器`http-cache`处理，针对get请求。
- lutool内置`ApiVersion`用于接口版本管理。
- lutool新增`CollectionUtils,ClassUtils,ObjectUtils`。
- lutool`BeanUtils`添加`bean diff`功能。
- lutool`Bean-Validator`国际化文件可配置文件化。
- lutool-excel修复解析长数字格式和导出String类型空指针问题（感谢`pig`团队@李寻欢）。
- lutool-excel添加`EasyCsv`，导出200万数据20秒。
- 修复lutool-ueditor图片上传没创建目录的问题（感谢VIP群友反馈）
- 修复dream-tpl在某些环境中乱码导致无法启动（感谢VIP群友反馈）
- dream-security国际化调整，添加国际化切换。

### v0.0.9 2018-04-21
- tpl中添加 spring bean 的引用方法`{{ dream.use("sec") }}`
- 内置数字和时间格式化函数`FmtFun`供thymeleaf和Tpl使用，具体请查看【tpl模板测试】`tpl-test.html`和`dream-docs thymeleaf vs Tpl fmt 时间、数字格式化`
- 添加字典和字典函数`SysDictFunc`
- 添加jsonp和cors跨域配置，详见`dream-docs lutool prop 配置`
- 添加spring task定时任务、async异步调用、spring cache demo演示（详见test包）
- 添加Dockerfile，和文档，详见`dream-docs docker 镜像构建`
- 文件下载支持断点续传

`升级：` 0.0.8需要执行`update-20180412.sql`。

### v0.0.8 2018-04-13
- 添加内置tpl，可用于邮件、短信等模板，同时已经和`thymeleaf`深度集成。添加集成演示。
- 修改操作日志。
- 修改list页面生成`easyui columns`。
- 修复`datagrid、treegrid`表头菜单。
- `AdminVO`中`organizationId`类型修改。
- 添加 `Results.status(boolean)` 方法，统一处理数据库操作返回。
- 优化代码生成方法详见 `test/java/net/dreamlu/generator/MysqlGenerator.java` 。

`升级：` 执行`docs/update-20180412.sql`，添加演示的资源。
`注意：` 
- 本次直接跳过 `0.0.6`、`0.0.7`。
- `mybatis-plus` 逻辑删除弃用。

### v0.0.5 2018-04-01
- 添加admin锁定功能，登录5次密码错误，锁定账号。