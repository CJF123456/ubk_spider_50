/*
Navicat MySQL Data Transfer

Source Server         : 10.0.0.51
Source Server Version : 50610
Source Host           : 10.0.0.51:3306
Source Database       : statscdata

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-11-07 14:13:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for country_datafield_month
-- ----------------------------
DROP TABLE IF EXISTS `country_datafield_month`;
CREATE TABLE `country_datafield_month` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL DEFAULT '0',
  `value` varchar(100) DEFAULT NULL,
  `level` tinyint(4) NOT NULL DEFAULT '1',
  `end` tinyint(4) NOT NULL DEFAULT '0',
  `code` varchar(5000) DEFAULT '',
  `unit` varchar(30) DEFAULT '',
  `frequency` int(11) NOT NULL DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `updatetask` int(11) DEFAULT '0',
  `cid` varchar(255) DEFAULT NULL,
  `cpid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `datafield_level_index` (`level`),
  KEY `datafield_pid_index` (`pid`),
  KEY `datafield_value_index` (`value`)
) ENGINE=MyISAM AUTO_INCREMENT=9966 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for country_datafield_quarter
-- ----------------------------
DROP TABLE IF EXISTS `country_datafield_quarter`;
CREATE TABLE `country_datafield_quarter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL DEFAULT '0',
  `value` varchar(100) DEFAULT NULL,
  `level` tinyint(4) NOT NULL DEFAULT '1',
  `end` tinyint(4) NOT NULL DEFAULT '0',
  `code` varchar(5000) DEFAULT '',
  `unit` varchar(30) DEFAULT '',
  `frequency` int(11) NOT NULL DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `updatetask` int(11) DEFAULT '0',
  `cid` varchar(255) DEFAULT NULL,
  `cpid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `datafield_level_index` (`level`),
  KEY `datafield_pid_index` (`pid`),
  KEY `datafield_value_index` (`value`)
) ENGINE=MyISAM AUTO_INCREMENT=625 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for country_datafield_year
-- ----------------------------
DROP TABLE IF EXISTS `country_datafield_year`;
CREATE TABLE `country_datafield_year` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL DEFAULT '0',
  `value` varchar(100) DEFAULT NULL,
  `level` tinyint(4) NOT NULL DEFAULT '1',
  `end` tinyint(4) NOT NULL DEFAULT '0',
  `code` varchar(500) DEFAULT '',
  `unit` varchar(30) DEFAULT '',
  `frequency` int(11) NOT NULL DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `updatetask` int(11) DEFAULT '0',
  `cid` varchar(255) DEFAULT NULL,
  `cpid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `datafield_level_index` (`level`),
  KEY `datafield_pid_index` (`pid`),
  KEY `datafield_value_index` (`value`)
) ENGINE=MyISAM AUTO_INCREMENT=55239 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for country_month_data
-- ----------------------------
DROP TABLE IF EXISTS `country_month_data`;
CREATE TABLE `country_month_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_id` bigint(11) NOT NULL,
  `date_year` varchar(4) DEFAULT NULL,
  `date_month` varchar(2) DEFAULT NULL,
  `datavalue` double DEFAULT NULL,
  `area_id` int(11) NOT NULL,
  `industry_id` int(11) DEFAULT NULL,
  `charascope` varchar(100) DEFAULT NULL,
  `date_year_int` int(11) NOT NULL,
  `date_month_int` int(11) NOT NULL,
  `insert_time` date DEFAULT '0000-00-00',
  `updatetask` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sub_id_20151229q` (`sub_id`) USING BTREE,
  KEY `area_id_20151229q` (`area_id`) USING BTREE,
  KEY `sub_id_2_20151229q` (`sub_id`,`date_year`,`date_month`,`area_id`) USING BTREE,
  KEY `date_year_int_20151229q` (`date_year_int`) USING BTREE,
  KEY `date_month_int_20151229q` (`date_month_int`) USING BTREE,
  KEY `sub_id_3_20151229q` (`sub_id`,`area_id`,`date_year_int`,`date_month_int`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=53500 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for country_quarter_data
-- ----------------------------
DROP TABLE IF EXISTS `country_quarter_data`;
CREATE TABLE `country_quarter_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_id` bigint(11) NOT NULL,
  `date_year` varchar(4) DEFAULT NULL,
  `date_month` varchar(2) DEFAULT NULL,
  `datavalue` double DEFAULT NULL,
  `area_id` int(11) NOT NULL,
  `industry_id` int(11) DEFAULT NULL,
  `charascope` varchar(100) DEFAULT NULL,
  `date_year_int` int(11) NOT NULL,
  `date_month_int` int(11) NOT NULL,
  `insert_time` date DEFAULT '0000-00-00',
  `updatetask` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sub_id_20151229q` (`sub_id`) USING BTREE,
  KEY `area_id_20151229q` (`area_id`) USING BTREE,
  KEY `sub_id_2_20151229q` (`sub_id`,`date_year`,`date_month`,`area_id`) USING BTREE,
  KEY `date_year_int_20151229q` (`date_year_int`) USING BTREE,
  KEY `date_month_int_20151229q` (`date_month_int`) USING BTREE,
  KEY `sub_id_3_20151229q` (`sub_id`,`area_id`,`date_year_int`,`date_month_int`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=14056 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for country_year_data
-- ----------------------------
DROP TABLE IF EXISTS `country_year_data`;
CREATE TABLE `country_year_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_id` bigint(11) NOT NULL,
  `date_year` varchar(4) DEFAULT NULL,
  `date_month` varchar(2) DEFAULT NULL,
  `datavalue` double DEFAULT NULL,
  `area_id` int(11) NOT NULL,
  `industry_id` int(11) DEFAULT NULL,
  `charascope` varchar(100) DEFAULT NULL,
  `date_year_int` int(11) NOT NULL,
  `date_month_int` int(11) NOT NULL,
  `insert_time` date DEFAULT '0000-00-00',
  `updatetask` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sub_id_20151229q` (`sub_id`) USING BTREE,
  KEY `area_id_20151229q` (`area_id`) USING BTREE,
  KEY `sub_id_2_20151229q` (`sub_id`,`date_year`,`date_month`,`area_id`) USING BTREE,
  KEY `date_year_int_20151229q` (`date_year_int`) USING BTREE,
  KEY `date_month_int_20151229q` (`date_month_int`) USING BTREE,
  KEY `sub_id_3_20151229q` (`sub_id`,`area_id`,`date_year_int`,`date_month_int`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=297427 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for region_datafield_month
-- ----------------------------
DROP TABLE IF EXISTS `region_datafield_month`;
CREATE TABLE `region_datafield_month` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL DEFAULT '0',
  `value` varchar(100) DEFAULT NULL,
  `level` tinyint(4) NOT NULL DEFAULT '1',
  `end` tinyint(4) NOT NULL DEFAULT '0',
  `code` varchar(5000) DEFAULT '',
  `unit` varchar(30) DEFAULT '',
  `frequency` int(11) NOT NULL DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `updatetask` int(11) DEFAULT '0',
  `cid` varchar(255) DEFAULT NULL,
  `cpid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `datafield_level_index` (`level`),
  KEY `datafield_pid_index` (`pid`),
  KEY `datafield_value_index` (`value`)
) ENGINE=MyISAM AUTO_INCREMENT=506 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for region_datafield_quarter
-- ----------------------------
DROP TABLE IF EXISTS `region_datafield_quarter`;
CREATE TABLE `region_datafield_quarter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL DEFAULT '0',
  `value` varchar(100) DEFAULT NULL,
  `level` tinyint(4) NOT NULL DEFAULT '1',
  `end` tinyint(4) NOT NULL DEFAULT '0',
  `code` varchar(5000) DEFAULT '',
  `unit` varchar(30) DEFAULT '',
  `frequency` int(11) NOT NULL DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `cid` varchar(255) DEFAULT NULL,
  `cpid` varchar(255) DEFAULT NULL,
  `updatetask` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `datafield_level_index` (`level`),
  KEY `datafield_pid_index` (`pid`),
  KEY `datafield_value_index` (`value`)
) ENGINE=MyISAM AUTO_INCREMENT=265 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for region_datafield_year
-- ----------------------------
DROP TABLE IF EXISTS `region_datafield_year`;
CREATE TABLE `region_datafield_year` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL DEFAULT '0',
  `value` varchar(100) DEFAULT NULL,
  `level` tinyint(4) NOT NULL DEFAULT '1',
  `end` tinyint(4) NOT NULL DEFAULT '0',
  `code` varchar(500) DEFAULT '',
  `unit` varchar(30) DEFAULT '',
  `frequency` int(11) NOT NULL DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `updatetask` int(11) DEFAULT '0',
  `cid` varchar(255) DEFAULT NULL,
  `cpid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `datafield_level_index` (`level`),
  KEY `datafield_pid_index` (`pid`),
  KEY `datafield_value_index` (`value`)
) ENGINE=MyISAM AUTO_INCREMENT=3348 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for region_month_data
-- ----------------------------
DROP TABLE IF EXISTS `region_month_data`;
CREATE TABLE `region_month_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_id` bigint(11) NOT NULL,
  `date_year` varchar(4) DEFAULT NULL,
  `date_month` varchar(2) DEFAULT NULL,
  `datavalue` double DEFAULT NULL,
  `area_id` int(11) NOT NULL,
  `industry_id` int(11) DEFAULT NULL,
  `charascope` varchar(100) DEFAULT NULL,
  `date_year_int` int(11) NOT NULL,
  `date_month_int` int(11) NOT NULL,
  `insert_time` date DEFAULT '0000-00-00',
  `updatetask` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sub_id_20151229q` (`sub_id`) USING BTREE,
  KEY `area_id_20151229q` (`area_id`) USING BTREE,
  KEY `sub_id_2_20151229q` (`sub_id`,`date_year`,`date_month`,`area_id`) USING BTREE,
  KEY `date_year_int_20151229q` (`date_year_int`) USING BTREE,
  KEY `date_month_int_20151229q` (`date_month_int`) USING BTREE,
  KEY `sub_id_3_20151229q` (`sub_id`,`area_id`,`date_year_int`,`date_month_int`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=110426 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for region_quarter_data
-- ----------------------------
DROP TABLE IF EXISTS `region_quarter_data`;
CREATE TABLE `region_quarter_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_id` bigint(11) NOT NULL,
  `date_year` varchar(4) DEFAULT NULL,
  `date_month` varchar(2) DEFAULT NULL,
  `datavalue` double DEFAULT NULL,
  `area_id` int(11) NOT NULL,
  `industry_id` int(11) DEFAULT NULL,
  `charascope` varchar(100) DEFAULT NULL,
  `date_year_int` int(11) NOT NULL,
  `date_month_int` int(11) NOT NULL,
  `insert_time` date DEFAULT '0000-00-00',
  `updatetask` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sub_id_20151229q` (`sub_id`) USING BTREE,
  KEY `area_id_20151229q` (`area_id`) USING BTREE,
  KEY `sub_id_2_20151229q` (`sub_id`,`date_year`,`date_month`,`area_id`) USING BTREE,
  KEY `date_year_int_20151229q` (`date_year_int`) USING BTREE,
  KEY `date_month_int_20151229q` (`date_month_int`) USING BTREE,
  KEY `sub_id_3_20151229q` (`sub_id`,`area_id`,`date_year_int`,`date_month_int`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=29873 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for region_year_data
-- ----------------------------
DROP TABLE IF EXISTS `region_year_data`;
CREATE TABLE `region_year_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_id` bigint(11) NOT NULL,
  `date_year` varchar(4) DEFAULT NULL,
  `date_month` varchar(2) DEFAULT NULL,
  `datavalue` double DEFAULT NULL,
  `area_id` int(11) NOT NULL,
  `industry_id` int(11) DEFAULT NULL,
  `charascope` varchar(100) DEFAULT NULL,
  `date_year_int` int(11) NOT NULL,
  `date_month_int` int(11) NOT NULL,
  `insert_time` date DEFAULT '0000-00-00',
  `updatetask` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sub_id_20151229q` (`sub_id`) USING BTREE,
  KEY `area_id_20151229q` (`area_id`) USING BTREE,
  KEY `sub_id_2_20151229q` (`sub_id`,`date_year`,`date_month`,`area_id`) USING BTREE,
  KEY `date_year_int_20151229q` (`date_year_int`) USING BTREE,
  KEY `date_month_int_20151229q` (`date_month_int`) USING BTREE,
  KEY `sub_id_3_20151229q` (`sub_id`,`area_id`,`date_year_int`,`date_month_int`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=719109 DEFAULT CHARSET=utf8;
