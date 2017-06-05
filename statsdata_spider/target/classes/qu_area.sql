/*
Navicat MySQL Data Transfer

Source Server         : 10.0.0.51
Source Server Version : 50610
Source Host           : 10.0.0.51:3306
Source Database       : statscdata

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-11-07 14:13:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qu_area
-- ----------------------------
DROP TABLE IF EXISTS `qu_area`;
CREATE TABLE `qu_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_code` varchar(20) DEFAULT NULL,
  `ind_code` varchar(4) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `effective_date` date NOT NULL,
  `expiry_date` date NOT NULL DEFAULT '9999-12-31',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `ind_code` (`ind_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qu_area
-- ----------------------------
INSERT INTO `qu_area` VALUES ('1', 'qg', '00', '全国', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('2', 'bj', '11', '北京市', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('3', 'tj', '12', '天津市', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('4', 'hb', '13', '河北省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('5', 'sx', '14', '山西省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('6', 'nm', '15', '内蒙古自治区', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('7', 'ln', '21', '辽宁省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('8', 'jl', '22', '吉林省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('9', 'hlj', '23', '黑龙江省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('10', 'sh', '31', '上海市', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('11', 'js', '32', '江苏省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('12', 'zj', '33', '浙江省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('13', 'ah', '34', '安徽省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('14', 'fj', '35', '福建省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('15', 'jx', '36', '江西省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('16', 'sd', '37', '山东省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('17', 'hn', '41', '河南省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('18', 'hb', '42', '湖北省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('19', 'hn', '43', '湖南省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('20', 'gd', '44', '广东省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('21', 'gx', '45', '广西壮族自治区', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('22', 'hn', '46', '海南省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('23', 'cq', '50', '重庆市', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('24', 'sc', '51', '四川省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('25', 'gz', '52', '贵州省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('26', 'yn', '53', '云南省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('27', 'xz', '54', '西藏自治区', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('28', 'sx', '61', '陕西省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('29', 'gs', '62', '甘肃省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('30', 'qh', '63', '青海省', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('31', 'nx', '64', '宁夏回族自治区', '2011-10-11', '9999-12-31');
INSERT INTO `qu_area` VALUES ('32', 'xj', '65', '新疆维吾尔自治区', '2011-10-11', '9999-12-31');
