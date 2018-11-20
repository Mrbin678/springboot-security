-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: dream
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
INSERT INTO `persistent_logins` VALUES ('admin','UQn6lZv5IyBMGBOCt3crxA==','bUoIYNTupCDq9V8H7Iv/NA==','2018-04-21 07:49:06');
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_admin`
--

DROP TABLE IF EXISTS `t_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(10) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `email` varchar(36) NOT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `sex` tinyint(2) NOT NULL DEFAULT '0' COMMENT '性别',
  `age` tinyint(2) DEFAULT '0' COMMENT '年龄',
  `user_type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '用户类别[0:管理员,1:普通员工]',
  `organization_id` int(11) DEFAULT NULL COMMENT '组织id',
  `locked` tinyint(2) DEFAULT '0' COMMENT '是否锁定[0:正常,1:锁定]',
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态[0:失效,1:正常]',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_admin_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_admin`
--

LOCK TABLES `t_admin` WRITE;
/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
INSERT INTO `t_admin` VALUES (1,'admin','$2a$11$se0ks5Wl79IerVfRLo1l1e8Tf/Cnxactib6u57Y01RAjd9sXFJ2CS','管理员','596392912@qq.com','15321111111',0,0,0,1,0,1,'2018-01-30 10:08:41','2018-04-16 14:59:38'),(2,'test','$2a$11$VzpOQ9qEgqD3O2suUJ1ZVuD6IEkUr5DPeRHk0fwx1TO0Iv9zBTOtC','测试','596392912@qq.com',NULL,0,0,1,6,0,1,'2018-03-28 04:26:31','2018-04-14 23:21:52');
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_admin_role`
--

DROP TABLE IF EXISTS `t_admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `admin_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `idx_user_role_ids` (`admin_id`,`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='用户角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_admin_role`
--

LOCK TABLES `t_admin_role` WRITE;
/*!40000 ALTER TABLE `t_admin_role` DISABLE KEYS */;
INSERT INTO `t_admin_role` VALUES (74,1,1),(75,1,2),(76,1,7),(77,1,8),(78,2,8),(63,13,2),(64,14,7),(53,15,8);
/*!40000 ALTER TABLE `t_admin_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_organization`
--

DROP TABLE IF EXISTS `t_organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '组织名',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `code` varchar(64) NOT NULL COMMENT '编号',
  `icon_cls` varchar(32) DEFAULT NULL COMMENT '图标',
  `pid` int(11) DEFAULT NULL COMMENT '父级主键',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态[0:失效,1:正常]',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='组织机构';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_organization`
--

LOCK TABLES `t_organization` WRITE;
/*!40000 ALTER TABLE `t_organization` DISABLE KEYS */;
INSERT INTO `t_organization` VALUES (1,'总经办','王家桥','01','glyphicon-apple',NULL,0,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),(3,'技术部','','02','glyphicon-heart',NULL,1,1,'2015-10-01 13:10:42','2018-04-14 16:30:15'),(5,'产品部','','03','glyphicon-glass',NULL,2,1,'2015-12-06 12:15:30','2018-04-14 16:30:10'),(6,'测试组','','04','glyphicon-retweet ',3,0,1,'2015-12-06 13:12:18','2018-03-24 04:33:21');
/*!40000 ALTER TABLE `t_organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_resource`
--

DROP TABLE IF EXISTS `t_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `permissions` varchar(32) DEFAULT NULL COMMENT '资源的权限',
  `url` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `open_mode` varchar(32) DEFAULT NULL COMMENT '打开方式 ajax,iframe',
  `description` varchar(255) DEFAULT NULL COMMENT '资源介绍',
  `icon_cls` varchar(32) DEFAULT NULL COMMENT '资源图标',
  `pid` int(11) DEFAULT NULL COMMENT '父级资源id',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态[0:失效,1:正常]',
  `opened` tinyint(1) NOT NULL DEFAULT '0' COMMENT '打开状态',
  `resource_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '资源类别',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=239 DEFAULT CHARSET=utf8 COMMENT='资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_resource`
--

LOCK TABLES `t_resource` WRITE;
/*!40000 ALTER TABLE `t_resource` DISABLE KEYS */;
INSERT INTO `t_resource` VALUES
(1,'权限管理','','',NULL,'系统管理','glyphicon-th ',NULL,0,1,1,0,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(11,'资源管理','','/resource/manager','ajax','资源管理','glyphicon-list-alt',1,1,1,1,0,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(12,'角色管理','','/role/manager','ajax','角色管理','glyphicon-lock',1,2,1,1,0,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(13,'用户管理','','/admin/manager','ajax','用户管理','glyphicon-user',1,3,1,1,0,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(14,'部门管理','','/organization/manager','ajax','部门管理','glyphicon-globe',1,4,1,1,0,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(111,'列表','','/resource/treeGrid','ajax','资源列表','glyphicon-th-list',11,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(112,'添加','resource:add','/resource/add','ajax','资源添加','glyphicon-plus',11,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(113,'编辑','resource:edit','/resource/edit','ajax','资源编辑','glyphicon-pencil',11,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(114,'删除','resource:delete','/resource/delete','ajax','资源删除','glyphicon-remove',11,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(121,'列表','','/role/dataGrid','ajax','角色列表','glyphicon-th-list',12,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(122,'添加','role:add','/role/add','ajax','角色添加','glyphicon-plus',12,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(123,'编辑','role:edit','/role/edit','ajax','角色编辑','glyphicon-pencil',12,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(124,'删除','role:delete','/role/delete','ajax','角色删除','glyphicon-remove',12,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(125,'授权','role:grant','/role/grant','ajax','角色授权','glyphicon-ok ',12,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(131,'列表','','/admin/dataGrid','ajax','用户列表','glyphicon-th-list',13,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(132,'添加','admin:add','/admin/add','ajax','用户添加','glyphicon-plus',13,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(133,'编辑','admin:edit','/admin/edit','ajax','用户编辑','glyphicon-pencil',13,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(134,'删除','admin:delete','/admin/delete','ajax','用户删除','glyphicon-remove',13,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(141,'列表','','/organization/treeGrid','ajax','用户列表','glyphicon-th-list',14,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(142,'添加','organization:add','/organization/add','ajax','部门添加','glyphicon-plus',14,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(143,'编辑','organization:edit','/organization/edit','ajax','部门编辑','glyphicon-pencil',14,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(144,'删除','organization:delete','/organization/delete','ajax','部门删除','glyphicon-remove',14,0,1,1,1,'2014-02-19 01:00:00','2018-03-24 04:33:21'),
(221,'系统管理','','',NULL,NULL,'glyphicon-cog ',NULL,3,1,0,0,'2015-12-01 11:44:20','2018-04-15 21:41:32'),
(222,'视频教程','','',NULL,NULL,'glyphicon-facetime-video',NULL,2,1,0,0,'2015-12-06 12:40:42','2018-03-24 04:33:21'),
(223,'官方网站','','http://www.dreamlu.net','iframe',NULL,'glyphicon-phone',222,0,1,1,0,'2015-12-06 12:42:42','2018-03-24 04:33:21'),
(224,'jfinal视频','','http://blog.dreamlu.net/blog/79','iframe',NULL,'glyphicon-film',222,1,1,1,0,'2015-12-06 12:45:28','2018-03-24 05:01:18'),
(226,'修改密码','admin:edit:pwd','/admin/editPwdPage','ajax',NULL,'glyphicon-warning-sign',NULL,4,1,1,1,'2015-12-07 20:23:06','2018-03-24 04:33:21'),
(227,'登录日志','','/sysLog/manager','ajax',NULL,'glyphicon-info-sign',221,2,1,1,0,'2016-09-30 22:10:53','2018-04-15 21:47:58'),
(228,'Druid监控','','/druid','iframe',NULL,'glyphicon-dashboard',221,3,1,1,0,'2016-09-30 22:12:50','2018-04-15 21:48:09'),
(229,'系统图标','','/icons.html','ajax',NULL,'glyphicon-picture',221,1,1,1,0,'2016-12-24 15:53:47','2018-04-15 21:48:18'),
(230,'文章管理','','','ajax',NULL,'glyphicon-book',NULL,1,1,1,0,'2016-12-24 15:53:47','2018-04-14 15:32:13'),
(231,'新建文章','','/blog/create','ajax',NULL,'glyphicon-file',230,0,1,1,0,'2016-12-24 15:53:47','2018-03-24 04:33:21'),
(232,'ueditor上传',NULL,'/ueditor','ajax',NULL,'glyphicon-camera icon-purple',231,0,1,0,1,'2018-03-23 23:06:11','2018-03-24 05:08:21'),
(233,'Excel导入导出',NULL,'/excel/index.html','ajax',NULL,'glyphicon-duplicate ',230,0,1,1,0,'2018-03-24 15:20:04','2018-04-15 21:31:53'),
(234,'tpl模板测试',NULL,'/test/tpl.html','ajax',NULL,'glyphicon-leaf ',230,0,1,0,0,'2018-04-12 23:04:39','2018-04-12 23:04:39'),
(235,'字典管理','sysDict:manager','/sysDict/manager',NULL,'字典管理','glyphicon-list-alt ',221,0,1,1,0,'2018-04-15 20:17:32','2018-04-15 21:48:26'),
(236,'列表','sysDict:dataGrid','/sysDict/dataGrid','ajax','资源列表','glyphicon-th-list',235,0,1,1,1,'2018-04-15 20:17:32','2018-04-15 21:59:40'),
(237,'添加','sysDict:add','/sysDict/add','ajax','资源添加','glyphicon-plus',235,0,1,1,1,'2018-04-15 20:17:32','2018-04-15 21:32:45'),
(238,'编辑','sysDict:edit','/sysDict/edit','ajax','资源编辑','glyphicon-pencil',235,0,1,1,1,'2018-04-15 20:17:32','2018-04-15 21:32:55');
/*!40000 ALTER TABLE `t_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '角色名',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `icon_cls` varchar(32) DEFAULT NULL COMMENT '角色图标',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序号',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态[0:失效,1:正常]',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'admin','超级管理员','glyphicon-lock ',0,1,'2018-03-24 04:33:21','2018-04-14 16:32:35'),(2,'de','技术部经理','glyphicon-plane ',0,1,'2018-03-24 04:33:21','2018-04-14 16:32:42'),(7,'pm','产品部经理','glyphicon-ok-circle ',0,1,'2018-03-24 04:33:21','2018-04-14 16:32:47'),(8,'test','测试账户','glyphicon-calendar ',0,1,'2018-03-24 04:33:21','2018-04-14 16:32:52');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_resource`
--

DROP TABLE IF EXISTS `t_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `resource_id` int(11) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`),
  KEY `idx_role_resource_ids` (`role_id`,`resource_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=683 DEFAULT CHARSET=utf8 COMMENT='角色资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_resource`
--

LOCK TABLES `t_role_resource` WRITE;
/*!40000 ALTER TABLE `t_role_resource` DISABLE KEYS */;
INSERT INTO `t_role_resource` VALUES (644,1,1),(645,1,11),(650,1,12),(656,1,13),(661,1,14),(646,1,111),(647,1,112),(648,1,113),(649,1,114),(651,1,121),(652,1,122),(653,1,123),(654,1,124),(655,1,125),(657,1,131),(658,1,132),(659,1,133),(660,1,134),(662,1,141),(663,1,142),(664,1,143),(665,1,144),(674,1,221),(671,1,222),(672,1,223),(673,1,224),(682,1,226),(680,1,227),(681,1,228),(679,1,229),(666,1,230),(667,1,231),(668,1,232),(669,1,233),(670,1,234),(675,1,235),(676,1,236),(677,1,237),(678,1,238),(437,2,1),(438,2,13),(439,2,131),(440,2,132),(441,2,133),(445,2,221),(442,2,222),(443,2,223),(444,2,224),(446,2,227),(447,2,228),(158,3,1),(159,3,11),(164,3,12),(170,3,13),(175,3,14),(160,3,111),(161,3,112),(162,3,113),(163,3,114),(165,3,121),(166,3,122),(167,3,123),(168,3,124),(169,3,125),(171,3,131),(172,3,132),(173,3,133),(174,3,134),(176,3,141),(177,3,142),(178,3,143),(179,3,144),(359,7,1),(360,7,14),(361,7,141),(362,7,142),(363,7,143),(367,7,221),(364,7,222),(365,7,223),(366,7,224),(368,7,226),(546,8,1),(547,8,11),(549,8,12),(551,8,13),(553,8,14),(548,8,111),(550,8,121),(552,8,131),(554,8,141),(563,8,221),(560,8,222),(561,8,223),(562,8,224),(564,8,227),(565,8,228),(566,8,229),(555,8,230),(556,8,231),(557,8,232),(558,8,233),(559,8,234);
/*!40000 ALTER TABLE `t_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_dict`
--

DROP TABLE IF EXISTS `t_sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_dict`
--

LOCK TABLES `t_sys_dict` WRITE;
/*!40000 ALTER TABLE `t_sys_dict` DISABLE KEYS */;
INSERT INTO `t_sys_dict` VALUES (1,'adminSex','性别-男','0','男',0,'2018-04-21 12:45:34'),(2,'adminSex','性别-女','1','女',1,'2018-04-21 12:45:34'),(3,'dbStatus','数据库状态-失效','0','失效',0,'2018-04-21 12:46:37'),(4,'dbStatus','数据库状态-正常','1','正常',1,'2018-04-21 12:46:59'),(5,'adminLocked','锁定-否','0','否',0,'2018-04-21 12:50:56'),(6,'adminLocked','锁定-是','1','是',1,'2018-04-21 12:51:15'),(7,'adminType','admin类型-管理员','0','管理员',1,'2018-04-21 12:52:27'),(8,'adminType','admin类型-用户','1','用户',0,'2018-04-21 12:52:49'),(9,'resourceType','资源类型-菜单','0','菜单',0,'2018-04-21 12:53:51'),(10,'resourceType','资源类型-按钮','1','按钮',1,'2018-04-21 12:54:09'),(11,'resourceOpened','资源菜单打开状态-打开','1','打开',0,'2018-04-21 12:55:41'),(12,'resourceOpened','资源菜单打开状态-关闭','0','关闭',1,'2018-04-21 12:56:07'),(13,'resourceOpenMode','资源打开方式-ajax','ajax','ajax',0,'2018-04-21 15:17:03'),(14,'resourceOpenMode','资源打开方式-iframe','iframe','iframe',0,'2018-04-21 15:17:25');
/*!40000 ALTER TABLE `t_sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_log`
--

DROP TABLE IF EXISTS `t_sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(20) DEFAULT NULL COMMENT '登陆名',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `operation` varchar(64) DEFAULT NULL COMMENT '操作',
  `class_method` varchar(100) NOT NULL COMMENT '类-方法',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  `client_ip` varchar(255) NOT NULL DEFAULT '' COMMENT '客户端ip',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_log`
--

LOCK TABLES `t_sys_log` WRITE;
/*!40000 ALTER TABLE `t_sys_log` DISABLE KEYS */;
INSERT INTO `t_sys_log` VALUES (1,'admin','admin,de,pm,test','编辑角色','net.dreamlu.system.web.RoleController.edit();','name=admin&description=超级管理员&id=1&iconCls=glyphicon-lock &seq=0&status=1&','0:0:0:0:0:0:0:1','2018-04-14 16:32:35'),(2,'admin','admin,de,pm,test','编辑角色','net.dreamlu.system.web.RoleController.edit();','name=de&description=技术部经理&id=2&iconCls=glyphicon-plane &seq=0&status=1&','0:0:0:0:0:0:0:1','2018-04-14 16:32:42'),(3,'admin','admin,de,pm,test','编辑角色','net.dreamlu.system.web.RoleController.edit();','name=pm&description=产品部经理&id=7&iconCls=glyphicon-ok-circle &seq=0&status=1&','0:0:0:0:0:0:0:1','2018-04-14 16:32:47'),(4,'admin','admin,de,pm,test','编辑角色','net.dreamlu.system.web.RoleController.edit();','name=test&description=测试账户&id=8&iconCls=glyphicon-calendar &seq=0&status=1&','0:0:0:0:0:0:0:1','2018-04-14 16:32:52'),(5,'test',NULL,'登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=nvxr&remember-me=true&username=test&','0:0:0:0:0:0:0:1','2018-04-14 23:13:11'),(6,'admin','admin,de,pm,test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=vy7e&remember-me=true&username=admin&','0:0:0:0:0:0:0:1','2018-04-14 23:15:00'),(7,'admin','admin,de,pm,test','编辑用户','net.dreamlu.system.web.AdminController.edit();','sex=0&organizationId=6&password=******&roleIds=8&phone=&name=测试&id=2&userType=1&locked=0&email=596392912@qq.com&age=0&username=test&status=1&','0:0:0:0:0:0:0:1','2018-04-14 23:16:43'),(8,'test','test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=s87u&remember-me=true&username=test&','0:0:0:0:0:0:0:1','2018-04-14 23:17:12'),(9,'admin','admin,de,pm,test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=r5yw&remember-me=true&username=admin&','0:0:0:0:0:0:0:1','2018-04-15 00:28:04'),(10,'test','test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=8c6b&remember-me=true&username=test&','0:0:0:0:0:0:0:1','2018-04-15 08:50:22'),(11,'test','test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=k5mk&remember-me=true&username=test&','0:0:0:0:0:0:0:1','2018-04-15 10:43:28'),(12,'test','test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=9ssr&remember-me=true&username=test&','0:0:0:0:0:0:0:1','2018-04-15 14:09:28'),(13,'admin','admin,de,pm,test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=jdcn&remember-me=true&username=admin&','0:0:0:0:0:0:0:1','2018-04-15 21:31:41'),(14,'admin','admin,de,pm,test','角色授权','net.dreamlu.system.web.RoleController.grant();','id=1&resourceIds=1,11,111,112,113,114,12,121,122,123,124,125,13,131,132,133,134,14,141,142,143,144,230,231,232,233,234,222,223,224,221,227,228,229,235,237,238,226&','0:0:0:0:0:0:0:1','2018-04-15 21:44:58'),(15,'admin','admin,de,pm,test','角色授权','net.dreamlu.system.web.RoleController.grant();','id=1&resourceIds=1,11,111,112,113,114,12,121,122,123,124,125,13,131,132,133,134,14,141,142,143,144,230,231,232,233,234,222,223,224,221,227,228,229,235,236,237,238,226&','0:0:0:0:0:0:0:1','2018-04-15 21:46:05'),(16,'admin','admin,de,pm,test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=5cmn&remember-me=true&username=admin&','0:0:0:0:0:0:0:1','2018-04-15 21:46:43'),(17,'admin','admin,de,pm,test','角色授权','net.dreamlu.system.web.RoleController.grant();','id=1&resourceIds=1,11,111,112,113,114,12,121,122,123,124,125,13,131,132,133,134,14,141,142,143,144,230,231,232,233,234,222,223,224,221,235,236,237,238,229,227,228,226&','0:0:0:0:0:0:0:1','2018-04-15 21:49:24'),(18,'admin','admin,de,pm,test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=8kbe&remember-me=true&username=admin&','0:0:0:0:0:0:0:1','2018-04-15 21:49:37'),(19,'admin','admin,de,pm,test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=kbss&remember-me=true&username=admin&','0:0:0:0:0:0:0:1','2018-04-15 22:00:36'),(20,'admin','admin,de,pm,test','修改密码','net.dreamlu.system.web.AdminController.editUserPwd();','oldPwd=test&pwd=test&rePwd=test&','0:0:0:0:0:0:0:1','2018-04-16 14:55:20'),(21,'admin','admin,de,pm,test','修改密码','net.dreamlu.system.web.AdminController.editUserPwd();','oldPwd=test&pwd=test&rePwd=test&','0:0:0:0:0:0:0:1','2018-04-16 14:57:32'),(22,'admin','admin,de,pm,test','修改密码','net.dreamlu.system.web.AdminController.editUserPwd();','oldPwd=test&pwd=test&rePwd=test&','0:0:0:0:0:0:0:1','2018-04-16 14:59:37'),(23,'admin','admin,de,pm,test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=ebcw&remember-me=true&username=admin&','0:0:0:0:0:0:0:1','2018-04-17 19:08:32'),(24,'admin','admin,de,pm,test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=vhwm&remember-me=true&username=admin&','0:0:0:0:0:0:0:1','2018-04-18 20:44:05'),(25,'admin','admin,de,pm,test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=vqrg&remember-me=true&username=admin&','0:0:0:0:0:0:0:1','2018-04-19 20:49:55'),(26,'admin','admin,de,pm,test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=46xj&remember-me=true&username=admin&','0:0:0:0:0:0:0:1','2018-04-20 20:15:06'),(27,'test','test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=Da53&remember-me=true&username=test&','0:0:0:0:0:0:0:1','2018-04-21 09:41:59'),(28,'admin','admin,de,pm,test','登录成功','net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();','password=******&code=rufb&remember-me=true&username=admin&','0:0:0:0:0:0:0:1','2018-04-21 12:42:32');
/*!40000 ALTER TABLE `t_sys_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-21 16:07:50
