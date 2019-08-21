/*
 Navicat Premium Data Transfer

 Source Server         : hotdog
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : hotdog

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 21/08/2019 17:20:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件链接',
  `publish_date` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `author` varchar(156) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `original` tinyint(4) NULL DEFAULT NULL COMMENT '是否原创',
  `site_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点id',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `page_view` bigint(20) NULL DEFAULT NULL COMMENT '文章浏览量',
  `site_tab_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点tabid',
  PRIMARY KEY (`id`, `title`, `url`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for site
-- ----------------------------
DROP TABLE IF EXISTS `site`;
CREATE TABLE `site`  (
  `site_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站点id',
  `site` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点名',
  `site_host` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '域名',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '添加日期',
  `state` tinyint(4) NULL DEFAULT 0 COMMENT '状态： 0：不启用 1：启用',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `site_tag_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签id',
  `site_zh` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点中文名',
  `target_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '爬取目标网页地址',
  `site_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点网址',
  `sort` int(255) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`site_code`, `site_tag_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '站点信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of site
-- ----------------------------
INSERT INTO `site` VALUES ('100', 'V2EX', 'www.v2ex.com', '2019-08-20 16:31:57', 1, '2019-08-19 17:20:47', '5000', 'V2EX', 'https://www.v2ex.com/?tab=hot', 'https://www.v2ex.com/', 0);
INSERT INTO `site` VALUES ('101', 'ITHome', 'www.ithome.com', '2019-08-20 16:32:37', 1, '2019-08-20 16:32:09', '5000', 'IT之家', NULL, 'https://www.ithome.com/', 0);
INSERT INTO `site` VALUES ('102', 'ZhiHu', 'www.zhihu.com', '2019-08-20 16:33:33', 1, '2019-08-20 16:33:38', '5000', '知乎', NULL, 'https://www.zhihu.com/', 0);
INSERT INTO `site` VALUES ('103', 'WeiBo', 's.weibo.com', '2019-08-20 16:39:19', 0, '2019-08-20 16:39:22', '5000', '微博', NULL, 'https://s.weibo.com/', 0);

-- ----------------------------
-- Table structure for site_hot_data
-- ----------------------------
DROP TABLE IF EXISTS `site_hot_data`;
CREATE TABLE `site_hot_data`  (
  `site_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站点id',
  `site_tab_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站点tabid',
  `data` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站点数据',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`site_code`, `site_tab_code`, `create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '热点数据信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for site_tab
-- ----------------------------
DROP TABLE IF EXISTS `site_tab`;
CREATE TABLE `site_tab`  (
  `site_tab_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站点榜单代码',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点对应的榜单名称',
  `site_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点id',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `target_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '榜单url',
  `state` tinyint(4) NULL DEFAULT NULL COMMENT '状态',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`site_tab_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of site_tab
-- ----------------------------
INSERT INTO `site_tab` VALUES ('10001', '最热榜', '100', '2019-08-20 10:53:34', '2019-08-19 17:43:57', 'https://www.v2ex.com/?tab=hot', 1, 0);
INSERT INTO `site_tab` VALUES ('10101', '24小时阅读榜', '101', '2019-08-20 16:37:09', '2019-08-20 16:37:14', 'https://www.ithome.com', 1, 0);
INSERT INTO `site_tab` VALUES ('10201', '最热榜', '102', '2019-08-20 16:38:01', '2019-08-20 16:38:05', 'https://www.zhihu.com/hot', 1, 0);
INSERT INTO `site_tab` VALUES ('10301', '最热榜', '103', '2019-08-20 16:40:56', '2019-08-20 16:41:00', 'https://s.weibo.com/top/summary', 1, 0);

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签代码',
  `tag` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标签名',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `state` tinyint(4) NULL DEFAULT NULL COMMENT '状态',
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`tag_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '站点标签信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('5000', '默认', 0, 1, '2019-08-19 17:12:26', '2019-08-19 17:12:31');

SET FOREIGN_KEY_CHECKS = 1;
