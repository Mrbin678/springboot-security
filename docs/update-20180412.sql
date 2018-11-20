-- v0.0.5 升级到0.0.8

INSERT INTO `t_resource` VALUES (234,'tpl模板测试',NULL,'/test/tpl.html','ajax',NULL,'glyphicon-leaf ',230,0,1,0,0,'2018-04-12 23:04:39','2018-04-12 23:04:39');

INSERT INTO `t_role_resource` VALUES (559,8,234);

ALTER TABLE `dream`.`t_sys_log`
CHANGE COLUMN `method_name` `operation` VARCHAR(64) NULL DEFAULT NULL COMMENT '操作' AFTER `role_name`,
CHANGE COLUMN `class_name` `class_method` VARCHAR(100) NOT NULL COMMENT '类-方法' ,
CHANGE COLUMN `client_ip` `client_ip` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '客户端ip' ;

-- 给设置密码添加权限表达式
update t_resource set permissions = 'admin:edit:pwd' where id = '226';
