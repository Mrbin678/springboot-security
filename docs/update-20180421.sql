-- v0.0.8 升级到0.0.9

--
-- Table structure for table `t_sys_dict`
--

DROP TABLE IF EXISTS `t_sys_dict`;
CREATE TABLE `t_sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码ID',
  `dict_type` varchar(64) DEFAULT NULL COMMENT '类别',
  `dict_desc` varchar(128) DEFAULT NULL COMMENT '描述',
  `dict_key` varchar(64) NOT NULL DEFAULT '' COMMENT '键',
  `dict_value` varchar(128) NOT NULL DEFAULT '' COMMENT '值',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='字典';

LOCK TABLES `t_sys_dict` WRITE;
INSERT INTO `t_sys_dict` VALUES (1,'adminSex','性别-男','0','男',0,'2018-04-21 12:45:34'),(2,'adminSex','性别-女','1','女',1,'2018-04-21 12:45:34'),(3,'dbStatus','数据库状态-失效','0','失效',0,'2018-04-21 12:46:37'),(4,'dbStatus','数据库状态-正常','1','正常',1,'2018-04-21 12:46:59'),(5,'adminLocked','锁定-否','0','否',0,'2018-04-21 12:50:56'),(6,'adminLocked','锁定-是','1','是',1,'2018-04-21 12:51:15'),(7,'adminType','admin类型-管理员','0','管理员',1,'2018-04-21 12:52:27'),(8,'adminType','admin类型-用户','1','用户',0,'2018-04-21 12:52:49'),(9,'resourceType','资源类型-菜单','0','菜单',0,'2018-04-21 12:53:51'),(10,'resourceType','资源类型-按钮','1','按钮',1,'2018-04-21 12:54:09'),(11,'resourceOpened','资源菜单打开状态-打开','1','打开',0,'2018-04-21 12:55:41'),(12,'resourceOpened','资源菜单打开状态-关闭','0','关闭',1,'2018-04-21 12:56:07'),(13,'resourceOpenMode','资源打开方式-ajax','ajax','ajax',0,'2018-04-21 15:17:03'),(14,'resourceOpenMode','资源打开方式-iframe','iframe','iframe',0,'2018-04-21 15:17:25');
UNLOCK TABLES;

INSERT INTO `t_resource` VALUES
(235,'字典管理','sysDict:manager','/sysDict/manager',NULL,'字典管理','glyphicon-list-alt ',221,0,1,1,0,'2018-04-15 20:17:32','2018-04-15 21:48:26'),
(236,'列表','sysDict:dataGrid','/sysDict/dataGrid','ajax','资源列表','glyphicon-th-list',235,0,1,1,1,'2018-04-15 20:17:32','2018-04-15 21:59:40'),
(237,'添加','sysDict:add','/sysDict/add','ajax','资源添加','glyphicon-plus',235,0,1,1,1,'2018-04-15 20:17:32','2018-04-15 21:32:45'),
(238,'编辑','sysDict:edit','/sysDict/edit','ajax','资源编辑','glyphicon-pencil',235,0,1,1,1,'2018-04-15 20:17:32','2018-04-15 21:32:55');

INSERT INTO `t_role_resource` VALUES (null,1,235),(null,1,236),(null,1,237),(null,1,238),(null,8,235),(null,8,236);
