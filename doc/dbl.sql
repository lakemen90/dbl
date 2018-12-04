/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.5.61 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dbl` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `dbl`;

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`id`,`param_key`,`param_value`,`status`,`remark`) values 
(1,'111','111',1,'111');

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '字典名称',
  `type` varchar(100) NOT NULL COMMENT '字典类型',
  `code` varchar(100) NOT NULL COMMENT '字典码',
  `value` varchar(1000) NOT NULL COMMENT '字典值',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记  -1：已删除  0：正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`name`,`type`,`code`,`value`,`order_num`,`remark`,`del_flag`) values 
(1,'性别','sex','0','女',0,NULL,0),
(2,'性别','sex','1','男',1,NULL,0),
(3,'性别','sex','2','未知',3,NULL,0);

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='系统日志';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`username`,`operation`,`method`,`params`,`time`,`ip`,`create_date`) values 
(1,'admin','修改用户','com.j.dbl.admin.sys.controller.SysUserController.update()','{\"createTime\":1478833871000,\"deptId\":1,\"deptName\":\"人人开源集团\",\"email\":\"jiangbin@qq.com\",\"mobile\":\"13612345678\",\"roleIdList\":[],\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"userId\":1,\"username\":\"admin\"}',81,'10.112.12.53','2018-09-17 15:47:42'),
(2,'admin','修改用户','com.j.dbl.admin.sys.controller.SysUserController.update()','{\"createTime\":1478833871000,\"deptId\":1,\"deptName\":\"人人开源集团\",\"email\":\"jiangbin@qq.com\",\"mobile\":\"13612345678\",\"password\":\"5f9c50b9d370e553b076ecf20870baab6dff1d061fb15868b62ca17f04b70a16\",\"roleIdList\":[],\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"userId\":1,\"username\":\"admin\"}',35,'10.112.12.53','2018-09-17 15:48:09'),
(3,'admin','保存用户','com.j.dbl.admin.sys.controller.SysUserController.save()','{\"createTime\":1537170592564,\"deptId\":2,\"deptName\":\"厦门分公司\",\"email\":\"jiangbin@qq.com\",\"mobile\":\"12222233344\",\"password\":\"89cda4866247f90af1d5c7c8219b19260aed7100fb162cb0c89cb3d5db1bdbcc\",\"roleIdList\":[],\"salt\":\"MErXdZyeAbl8qgm9agJG\",\"status\":1,\"userId\":2,\"username\":\"jiangbin\"}',36,'10.112.12.53','2018-09-17 15:49:52'),
(4,'admin','修改用户','com.j.dbl.admin.sys.controller.SysUserController.update()','{\"createTime\":1537170592000,\"deptId\":2,\"deptName\":\"厦门分公司\",\"email\":\"jiangbin@qq.com\",\"mobile\":\"12222233344\",\"roleIdList\":[],\"salt\":\"MErXdZyeAbl8qgm9agJG\",\"status\":0,\"userId\":2,\"username\":\"jiangbin\"}',35,'10.112.12.53','2018-09-17 15:50:02'),
(5,'admin','修改用户','com.j.dbl.admin.sys.controller.SysUserController.update()','{\"createTime\":1537170592000,\"deptId\":2,\"deptName\":\"厦门分公司\",\"email\":\"jiangbin@qq.com\",\"mobile\":\"12222233344\",\"password\":\"2c632232352b116e69575acf66069cb6b7800b40e1c82e0a12c8b5cf6521fe7a\",\"roleIdList\":[],\"salt\":\"MErXdZyeAbl8qgm9agJG\",\"status\":1,\"userId\":2,\"username\":\"jiangbin\"}',30,'10.112.12.53','2018-09-17 15:50:41'),
(6,'admin','保存用户','com.j.dbl.admin.sys.controller.SysUserController.save()','{\"createTime\":1537170657396,\"deptId\":1,\"deptName\":\"集团总部\",\"email\":\"111\",\"mobile\":\"111\",\"password\":\"9c535f9d2a23235a8d6028d6770b969fd7679a86a41f33ffa2e287f45ccdae37\",\"roleIdList\":[],\"salt\":\"5Xg968gXesFCzshRohx7\",\"status\":1,\"userId\":3,\"username\":\"111\"}',37,'10.112.12.53','2018-09-17 15:50:57'),
(7,'admin','保存用户','com.j.dbl.admin.sys.controller.SysUserController.save()','{\"createTime\":1537170665634,\"deptId\":1,\"deptName\":\"集团总部\",\"email\":\"222\",\"mobile\":\"222\",\"password\":\"1135f9f728308f05963ff6035e4d140981c3bf668b72ed03520cbf5a42c383a5\",\"roleIdList\":[],\"salt\":\"A244SjvNoBCV9kXKODC9\",\"status\":1,\"userId\":4,\"username\":\"222\"}',32,'10.112.12.53','2018-09-17 15:51:05'),
(8,'admin','保存用户','com.j.dbl.admin.sys.controller.SysUserController.save()','{\"createTime\":1537170673859,\"deptId\":1,\"deptName\":\"集团总部\",\"email\":\"333\",\"mobile\":\"333\",\"password\":\"d499b7998d499a36d15b37e3f40cd2f9a63ecd936d38970c5663c999c766d466\",\"roleIdList\":[],\"salt\":\"XG8yfngkpPjxEFjFo9kr\",\"status\":1,\"userId\":5,\"username\":\"333\"}',31,'10.112.12.53','2018-09-17 15:51:13'),
(9,'admin','删除用户','com.j.dbl.admin.sys.controller.SysUserController.delete()','[20]',1867,'10.112.12.53','2018-09-17 15:59:13'),
(10,'admin','删除用户','com.j.dbl.admin.sys.controller.SysUserController.delete()','[19,18]',90,'10.112.12.53','2018-09-17 16:00:35'),
(11,'admin','保存角色','com.j.dbl.admin.sys.controller.SysRoleController.save()','{\"createTime\":1537174198512,\"deptId\":2,\"deptIdList\":[2],\"deptName\":\"厦门分公司\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,27,29,31,32,33,34,35,36,37,38,39,40],\"remark\":\"厦门分公司管理员\",\"roleId\":1,\"roleName\":\"管理员\"}',76,'10.112.12.53','2018-09-17 16:49:58'),
(12,'admin','保存配置','com.j.dbl.admin.sys.controller.SysConfigController.save()','{\"id\":1,\"paramKey\":\"111\",\"paramValue\":\"111\",\"remark\":\"111\",\"status\":1}',45,'10.112.12.53','2018-09-18 14:32:26');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`parent_id`,`name`,`url`,`perms`,`type`,`icon`,`order_num`) values 
(1,0,'系统管理',NULL,NULL,0,'fa fa-cog',0),
(2,1,'管理员管理','modules/sys/user.html',NULL,1,'fa fa-user',1),
(3,1,'角色管理','modules/sys/role.html',NULL,1,'fa fa-user-secret',2),
(4,1,'菜单管理','modules/sys/menu.html',NULL,1,'fa fa-th-list',3),
(5,1,'SQL监控','druid/sql.html',NULL,1,'fa fa-bug',4),
(15,2,'查看',NULL,'sys:user:list,sys:user:info',2,NULL,0),
(16,2,'新增',NULL,'sys:user:save,sys:role:select',2,NULL,0),
(17,2,'修改',NULL,'sys:user:update,sys:role:select',2,NULL,0),
(18,2,'删除',NULL,'sys:user:delete',2,NULL,0),
(19,3,'查看',NULL,'sys:role:list,sys:role:info',2,NULL,0),
(20,3,'新增',NULL,'sys:role:save,sys:menu:perms',2,NULL,0),
(21,3,'修改',NULL,'sys:role:update,sys:menu:perms',2,NULL,0),
(22,3,'删除',NULL,'sys:role:delete',2,NULL,0),
(23,4,'查看',NULL,'sys:menu:list,sys:menu:info',2,NULL,0),
(24,4,'新增',NULL,'sys:menu:save,sys:menu:select',2,NULL,0),
(25,4,'修改',NULL,'sys:menu:update,sys:menu:select',2,NULL,0),
(26,4,'删除',NULL,'sys:menu:delete',2,NULL,0),
(27,1,'参数管理','modules/sys/config.html','sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete',1,'fa fa-sun-o',6),
(29,1,'系统日志','modules/sys/log.html','sys:log:list',1,'fa fa-file-text-o',7),
(36,1,'字典管理','modules/sys/dict.html',NULL,1,'fa fa-bookmark-o',6),
(37,36,'查看',NULL,'sys:dict:list,sys:dict:info',2,NULL,6),
(38,36,'新增',NULL,'sys:dict:save',2,NULL,6),
(39,36,'修改',NULL,'sys:dict:update',2,NULL,6),
(40,36,'删除',NULL,'sys:dict:delete',2,NULL,6);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`remark`,`create_time`) values 
(1,'管理员','厦门分公司管理员','2018-09-17 16:49:58');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values 
(1,1,1),
(2,1,2),
(3,1,15),
(4,1,16),
(5,1,17),
(6,1,18),
(7,1,3),
(8,1,19),
(9,1,20),
(10,1,21),
(11,1,22),
(12,1,4),
(13,1,23),
(14,1,24),
(15,1,25),
(16,1,26),
(17,1,5),
(18,1,27),
(19,1,29),
(20,1,31),
(21,1,32),
(22,1,33),
(23,1,34),
(24,1,35),
(25,1,36),
(26,1,37),
(27,1,38),
(28,1,39),
(29,1,40);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='系统用户';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`username`,`password`,`salt`,`email`,`mobile`,`status`,`create_time`) values 
(1,'admin','5f9c50b9d370e553b076ecf20870baab6dff1d061fb15868b62ca17f04b70a16','YzcmCZNvbXocrsz9dm8e','jiangbin@qq.com','13612345678',1,'2016-11-11 11:11:11'),
(2,'jiangbin','2c632232352b116e69575acf66069cb6b7800b40e1c82e0a12c8b5cf6521fe7a','MErXdZyeAbl8qgm9agJG','jiangbin@qq.com','12222233344',1,'2018-09-17 15:49:52'),
(3,'111','9c535f9d2a23235a8d6028d6770b969fd7679a86a41f33ffa2e287f45ccdae37','5Xg968gXesFCzshRohx7','111','111',1,'2018-09-17 15:50:57'),
(4,'222','1135f9f728308f05963ff6035e4d140981c3bf668b72ed03520cbf5a42c383a5','A244SjvNoBCV9kXKODC9','222','222',1,'2018-09-17 15:51:05'),
(5,'333','d499b7998d499a36d15b37e3f40cd2f9a63ecd936d38970c5663c999c766d466','XG8yfngkpPjxEFjFo9kr','333','333',1,'2018-09-17 15:51:13'),
(7,'444','d499b7998d499a36d15b37e3f40cd2f9a63ecd936d38970c5663c999c766d466','XG8yfngkpPjxEFjFo9kr','333','333',1,'2018-09-17 15:51:13'),
(8,'000','d499b7998d499a36d15b37e3f40cd2f9a63ecd936d38970c5663c999c766d466','XG8yfngkpPjxEFjFo9kr','333','333',1,'2018-09-17 15:51:13'),
(9,'555','d499b7998d499a36d15b37e3f40cd2f9a63ecd936d38970c5663c999c766d466','XG8yfngkpPjxEFjFo9kr','333','333',1,'2018-09-17 15:51:13'),
(10,'666','d499b7998d499a36d15b37e3f40cd2f9a63ecd936d38970c5663c999c766d466','XG8yfngkpPjxEFjFo9kr','333','333',1,'2018-09-17 15:51:13'),
(11,'777','d499b7998d499a36d15b37e3f40cd2f9a63ecd936d38970c5663c999c766d466','XG8yfngkpPjxEFjFo9kr','333','333',1,'2018-09-17 15:51:13'),
(12,'888','d499b7998d499a36d15b37e3f40cd2f9a63ecd936d38970c5663c999c766d466','XG8yfngkpPjxEFjFo9kr','333','333',1,'2018-09-17 15:51:13'),
(13,'999','d499b7998d499a36d15b37e3f40cd2f9a63ecd936d38970c5663c999c766d466','XG8yfngkpPjxEFjFo9kr','333','333',1,'2018-09-17 15:51:13'),
(14,'112','d499b7998d499a36d15b37e3f40cd2f9a63ecd936d38970c5663c999c766d466','XG8yfngkpPjxEFjFo9kr','333','333',1,'2018-09-17 15:51:13'),
(15,'113','d499b7998d499a36d15b37e3f40cd2f9a63ecd936d38970c5663c999c766d466','XG8yfngkpPjxEFjFo9kr','333','333',1,'2018-09-17 15:51:13'),
(16,'114','d499b7998d499a36d15b37e3f40cd2f9a63ecd936d38970c5663c999c766d466','XG8yfngkpPjxEFjFo9kr','333','333',1,'2018-09-17 15:51:13'),
(17,'224','d499b7998d499a36d15b37e3f40cd2f9a63ecd936d38970c5663c999c766d466','XG8yfngkpPjxEFjFo9kr','333','333',1,'2018-09-17 15:51:13');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

/*Data for the table `sys_user_role` */

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `age` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `test` */

insert  into `test`(`id`,`name`,`age`) values 
(2,'张三',27),
(3,'张三',21),
(4,'张三',21),
(5,'张三',21),
(6,'张三',21),
(7,'张三',21),
(8,'张三',21),
(9,'张三',21);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
