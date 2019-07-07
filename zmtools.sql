/*
Navicat MySQL Data Transfer

Source Server         : zmTools
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : zmtools

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-07-07 21:39:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_permission
-- ----------------------------
DROP TABLE IF EXISTS `tbl_permission`;
CREATE TABLE `tbl_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `url` varchar(128) DEFAULT NULL COMMENT '接口路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_permission
-- ----------------------------
INSERT INTO `tbl_permission` VALUES ('1', 'video_update', '/api/video/update');
INSERT INTO `tbl_permission` VALUES ('2', 'video_delete', '/api/video/delete');
INSERT INTO `tbl_permission` VALUES ('3', 'video_add', '/api/video/add');
INSERT INTO `tbl_permission` VALUES ('4', 'order_list', '/api/order/list');
INSERT INTO `tbl_permission` VALUES ('5', 'user_list', '/api/user/list');

-- ----------------------------
-- Table structure for tbl_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `description` varchar(64) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_role
-- ----------------------------
INSERT INTO `tbl_role` VALUES ('1', 'admin', '普通管理员');
INSERT INTO `tbl_role` VALUES ('2', 'root', '超级管理员');
INSERT INTO `tbl_role` VALUES ('3', 'editor', '审核人员');

-- ----------------------------
-- Table structure for tbl_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_permission`;
CREATE TABLE `tbl_role_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_role_permission
-- ----------------------------
INSERT INTO `tbl_role_permission` VALUES ('1', '3', '1');
INSERT INTO `tbl_role_permission` VALUES ('2', '3', '2');
INSERT INTO `tbl_role_permission` VALUES ('3', '3', '3');
INSERT INTO `tbl_role_permission` VALUES ('4', '2', '1');
INSERT INTO `tbl_role_permission` VALUES ('5', '2', '2');
INSERT INTO `tbl_role_permission` VALUES ('6', '2', '3');
INSERT INTO `tbl_role_permission` VALUES ('7', '2', '4');

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) DEFAULT NULL COMMENT '用户名',
  `password` varchar(256) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL,
  `salt` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1', 'zhangsan', '07d23ea17b20c3973b5856b10674a2d5', null, null);
INSERT INTO `tbl_user` VALUES ('2', 'lisi', '9b26b07a1395317f27f9f93a30bfa346', null, null);
INSERT INTO `tbl_user` VALUES ('3', 'jack', 'aab74f19be97f57f3ee9ae12bea9641a', null, null);

-- ----------------------------
-- Table structure for tbl_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `tbl_userinfo`;
CREATE TABLE `tbl_userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_userinfo
-- ----------------------------
INSERT INTO `tbl_userinfo` VALUES ('1', 'zlw', '123', '18');
INSERT INTO `tbl_userinfo` VALUES ('2', 'mm', '123', '18');
INSERT INTO `tbl_userinfo` VALUES ('3', 'zxc', '132456789', '20');

-- ----------------------------
-- Table structure for tbl_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_role`;
CREATE TABLE `tbl_user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `remarks` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user_role
-- ----------------------------
INSERT INTO `tbl_user_role` VALUES ('1', '3', '1', 'zhangsan是editor');
INSERT INTO `tbl_user_role` VALUES ('2', '1', '3', 'jack是admin');
INSERT INTO `tbl_user_role` VALUES ('3', '2', '3', 'jack是root');
INSERT INTO `tbl_user_role` VALUES ('4', '3', '3', 'jack是editor');
INSERT INTO `tbl_user_role` VALUES ('5', '1', '2', 'lisi是admin');
INSERT INTO `tbl_user_role` VALUES ('6', '1', '1', 'zhangsan是admin');
