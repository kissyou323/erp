/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : erp

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-05-08 21:29:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_case
-- ----------------------------
DROP TABLE IF EXISTS `t_case`;
CREATE TABLE `t_case` (
  `case_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `case_no` varchar(50) DEFAULT NULL COMMENT '案件编号',
  `case_name` varchar(60) DEFAULT NULL COMMENT '案件名称',
  `police_login_name` varchar(50) DEFAULT NULL COMMENT '办案民警的登录名称其实也是唯一的编号',
  `police_name` varchar(50) DEFAULT NULL COMMENT '经办民警',
  `case_time` datetime DEFAULT NULL COMMENT '案发时间',
  `case_address` varchar(100) DEFAULT NULL COMMENT '案发地点',
  `start_time` datetime DEFAULT NULL COMMENT '入库时间',
  `end_time` datetime DEFAULT NULL COMMENT '结案时间',
  `case_status` varchar(5) DEFAULT NULL COMMENT '案件状态（1-未结案、2-已结案3案件撤销4案件合并',
  `file_status` varchar(5) DEFAULT NULL COMMENT '档案状态(1-还入2-建档5-归档3借出4其他6-移交)',
  `qrcode` varchar(200) DEFAULT NULL COMMENT '二维码图片url',
  `modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `modify_name` varchar(30) DEFAULT NULL COMMENT '最后修改人',
  `case_explain` text COMMENT '简要案情',
  `possessions_no` varchar(255) DEFAULT NULL COMMENT '涉案财物编号 ',
  `possessions_name` varchar(100) DEFAULT NULL COMMENT '涉案财物名称',
  `style` varchar(10) DEFAULT NULL COMMENT '案件类型（1-行政、2-刑事）',
  `case_add_url` varchar(255) DEFAULT NULL COMMENT '结案时上传的登记图片',
  `endcase_explain` text COMMENT '结案描述',
  `endcase_status` varchar(255) DEFAULT '1' COMMENT '结案状态1-未结案-2结案审核中3-已结案',
  `rejectmsg` text COMMENT '驳回意见',
  `parent_caseno` varchar(255) DEFAULT NULL COMMENT '父案件编号',
  `son_status` varchar(255) DEFAULT '2' COMMENT '是否是子案件1是2否',
  `is_caseList` varchar(255) DEFAULT '0' COMMENT '是否已经督案0未督案1已经督案',
  PRIMARY KEY (`case_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_case
-- ----------------------------
INSERT INTO `t_case` VALUES ('8', 'A125353625', '盗窃案', 'wjg', '王建观', '2016-07-20 20:21:34', '安徽省歙县', '2016-07-21 20:21:42', '2016-08-06 17:00:43', '2', '5', 'A125353625.png', '2016-08-06 17:00:43', 'wjg', '被盗窃100辆电动车', '', null, '2', '6f0e1470473535515.jpg;60a11470473538154.png;', 'adsfsdafsdaf', '3', null, '2121', '1', '1');
INSERT INTO `t_case` VALUES ('9', '1221221', '杀人案件', 'wjg', '王建观', '2016-07-13 20:25:01', '杭州西湖', '2016-07-21 20:25:10', '2016-08-06 10:03:00', '2', null, '1221221.png', '2016-08-06 14:34:16', 'wjg', '杀死了100人', '', null, '1', '4b4b1470320099389.jpg;856c1470317289351.jpg;', '1111111', '3', null, '2121', '1', '0');
INSERT INTO `t_case` VALUES ('10', 'JKLOIUJ', '强奸案', 'wjg', '王建观', '2016-07-21 22:04:01', '沙特阿拉伯', '2016-07-21 22:04:01', '2016-08-27 21:04:44', '2', null, 'JKLOIUJ.png', '2016-08-27 21:04:44', '超级管理员', '', '', null, '2', '1472303079983.jpg;1472303081897.jpg;', 'sddssdsdsdsd', '3', null, '2121', '1', '0');
INSERT INTO `t_case` VALUES ('13', 'APLPL', '贪污案', 'gaozhenbo', '高振博', '2016-07-23 00:00:00', '歙县', '2016-07-23 00:00:00', null, '1', '2', 'APLPL.png', '2016-08-25 15:42:16', '高振博', '2332323232323223', '苹果电脑1', '苹果电脑1', '1', '1472110932101.png;1472110934033.jpg;', 'wewewewewewewe', '2', '', null, '2', '0');
INSERT INTO `t_case` VALUES ('15', '2121', '杀人案', 'wjg', 'wjg', '2016-07-23 00:00:00', '歙县', '2016-07-23 00:00:00', '2016-07-23 00:00:00', '2', '5', '2121.jpg', '2016-07-23 10:01:04', 'wjg', '的方式发生大幅啊', '332', '苹果电脑1', '2', '4b4b1470320099389.jpg;856c1470317289351.jpg;', null, '3', null, '2121', '1', '0');
INSERT INTO `t_case` VALUES ('16', '3232', '3232', 'wjg', 'wjg', '2016-07-23 00:00:00', 'wefwefwe', '2016-07-23 00:00:00', '2016-07-23 00:00:00', '2', '5', '3232.jpg', '2016-07-23 10:02:48', 'wjg', 'ewfwf', '332', '苹果电脑1', '1', '4b4b1470320099389.jpg;856c1470317289351.jpg;', null, '3', null, '2121', '1', '0');
INSERT INTO `t_case` VALUES ('17', '1212121', '21212', 'wjg', 'wjg', '2016-07-23 00:00:00', '232332', '2016-07-23 00:00:00', null, '2', '5', '1212121.jpg', '2016-07-23 10:30:43', 'wjg', 'weweewe', '332', '苹果电脑1', '1', '4b4b1470320099389.jpg;856c1470317289351.jpg;', null, '3', null, '2121', '1', '0');
INSERT INTO `t_case` VALUES ('18', '2323', '2332', 'wjg', '王建观', '2016-08-17 00:00:00', '2332', '2016-08-23 00:00:00', null, '1', '5', '2323.png', '2016-12-07 15:54:48', '超级管理员', '23323232', '', '', '1', '4b4b1470320099389.jpg;856c1470317289351.jpg;', null, '3', null, '2121', '1', '0');
INSERT INTO `t_case` VALUES ('19', '21212323', '伤人案', 'gaozhenbo', '高振博', '2016-07-23 00:00:00', '歙县', '2016-07-23 00:00:00', null, '2', null, '21212323.png', '2016-07-30 09:33:35', 'wjg', '唐自成伤人', '苹果电脑1', '苹果电脑1', '2', '6f1f1469287611259.jpg', '2212121212121', '3', null, '2121', '1', '0');
INSERT INTO `t_case` VALUES ('24', '2121', '1221', null, null, null, null, null, '2016-07-23 00:00:00', '4', '5', null, null, null, '合并的案件', null, null, '1', null, null, '3', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('26', 'A2016080926', '王其刚等人打架案', 'wjg', '王建观', '2016-08-11 16:33:11', '紫阳广场', '2016-08-11 16:33:14', '2016-09-01 15:05:27', '2', '5', 'A2016080926.png', '2016-12-07 15:54:11', '超级管理员', '2016年8月1日8时33分，吴志伟通过110指令13855950772，报称：我父母跟别人打架，具体情况不清楚，我在赶过去的路上，要求来人处理。民警到达现场，经了解系王其刚、胡仙仙夫妇与张翠琴、吴国辉夫妇因让车一事发生口角，后发生打架，拟受案调查。', '', '', '1', '1471189272457.png;1471189273934.png;', 'ewewewewe', '3', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('27', '2w3', '32', 'wjg', '王建观', '2016-08-17 00:00:00', '2323', '2016-08-12 00:00:00', null, '1', '2', '2w3.png', '2016-08-11 16:57:54', 'wjg', '232332', '32', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('28', '2323', '2332', 'wjg', '王建观', '2016-08-17 00:00:00', '2332', '2016-08-23 00:00:00', null, '1', '2', '2323.png', '2016-08-12 10:39:34', 'wjg', '23323232', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('29', 'A0000000001', '盗窃案', 'gaozhenbo', '高振博', '2016-08-25 15:38:04', '杭州', '2016-08-25 15:38:06', null, '1', '2', 'A0000000001.png', '2016-08-25 15:38:36', '超级管理员', '在杭州文二西路被盗窃一辆黑色电动车', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('30', 'A00000000001', '案件1', 'wjg', '王建观', '2016-08-27 21:33:27', '杭州', '2016-08-27 21:33:28', null, '1', '2', 'A00000000001.png', '2016-08-27 21:33:34', '超级管理员', 'test', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('31', 'A00000000002', '案件2', 'wjg', '王建观', '2016-08-27 21:36:43', '杭州', '2016-08-27 21:36:44', null, '1', '2', 'A00000000002.png', '2016-08-27 21:36:53', '超级管理员', 'test', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('32', 'A00000000003', '案件2', 'wjg', '王建观', '2016-08-27 21:37:16', '杭州', '2016-08-27 21:37:18', null, '1', '2', 'A00000000003.png', '2016-08-27 21:37:21', '超级管理员', 'test', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('33', 'A00000000004', '案件4', 'wjg', '王建观', '2016-08-27 21:37:42', '杭州', '2016-08-27 21:37:44', null, '1', '2', 'A00000000004.png', '2016-08-27 21:37:45', '超级管理员', '', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('34', 'A00000000005', '案件5', 'wjg', '王建观', '2016-08-27 21:38:01', '杭州', '2016-08-27 21:38:02', null, '1', '2', 'A00000000005.png', '2016-08-27 21:38:08', '超级管理员', 'test', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('35', 'A00000000006', '案件6', 'wjg', '王建观', '2016-08-27 21:38:26', '杭州', '2016-08-27 21:38:27', null, '1', '2', 'A00000000006.png', '2016-08-27 21:38:33', '超级管理员', 'test6', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('36', 'A00000000007', '案件7', 'wjg', '王建观', '2016-08-27 21:38:55', '杭州', '2016-08-27 21:38:56', null, '1', '2', 'A00000000007.png', '2016-08-27 21:39:00', '超级管理员', 'test', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('37', 'A00000000008', '案件8', 'wjg', '王建观', '2016-08-27 21:39:18', '杭州', '2016-08-27 21:39:19', null, '1', '2', 'A00000000008.png', '2016-08-27 21:39:26', '超级管理员', '', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('38', 'A00000000009', '案件9', 'wjg', '王建观', '2016-08-27 21:39:51', '杭州', '2016-08-27 21:39:53', null, '1', '2', 'A00000000009.png', '2016-08-27 21:39:56', '超级管理员', 'test', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('39', 'A00000000010', '案件10', 'wjg', '王建观', '2016-08-27 21:40:17', '杭州', '2016-08-27 21:40:18', null, '1', '2', 'A00000000010.png', '2016-08-27 21:40:24', '超级管理员', 'test', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('40', 'A00000000011', '案件11', 'wjg', '王建观', '2016-08-27 21:40:43', '杭州', '2016-08-27 21:40:45', null, '1', '2', 'A00000000011.png', '2016-08-27 21:40:46', '超级管理员', '', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('41', 'A00000000012', '案件12', 'wjg', '王建观', '2016-08-27 21:41:05', '杭州', '2016-08-27 21:41:06', null, '1', '3', 'A00000000012.png', '2016-08-27 21:41:10', '超级管理员', 'test', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('42', 'A00000000013', '案件13', 'wjg', '王建观', '2016-08-27 21:41:29', '杭州', '2016-08-27 21:41:30', null, '1', '2', 'A00000000013.png', '2016-08-27 21:41:32', '超级管理员', '', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('43', 'A00000000014', '案件14', 'wjg', '王建观', '2016-08-27 21:41:49', '杭州', '2016-08-27 21:41:51', null, '1', '2', 'A00000000014.png', '2016-08-27 21:41:58', '超级管理员', 'test', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('44', 'A00000000015', '案件15', 'wjg', '王建观', '2016-08-27 21:42:20', '杭州', '2016-08-27 21:42:21', null, '1', '2', 'A00000000015.png', '2016-08-27 21:42:24', '超级管理员', 'test', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('45', 'A00000000016', '案件16', 'wjg', '王建观', '2016-08-27 21:42:57', '杭州', '2016-08-27 21:42:58', null, '1', '2', 'A00000000016.png', '2016-08-27 21:43:01', '超级管理员', 'test', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('46', 'A00000000017', '案件17', 'wjg', '王建观', '2016-08-27 21:43:36', '杭州', '2016-08-27 21:43:38', null, '1', '1', 'A00000000017.png', '2016-08-28 01:25:43', '超级管理员', 'test', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('47', 'A00000000018', '案件18', 'wjg', '王建观', '2016-08-27 21:44:08', '杭州', '2016-01-27 21:44:10', '2016-08-28 00:53:08', '2', '5', 'A00000000018.png', '2016-09-03 11:14:34', '超级管理员', 'test', '', '', '1', '1472316768385.jpg;1472316787258.jpg;', '', '3', null, 'A00000000024', '1', '0');
INSERT INTO `t_case` VALUES ('48', 'A00000000019', '案件19', 'wjg', '王建观', '2016-08-27 21:44:35', '杭州', '2016-08-27 21:44:36', null, '1', '3', 'A00000000019.png', '2016-08-27 21:44:39', '超级管理员', 'test', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('49', 'A00000000020', '案件20', 'wjg', '王建观', '2016-08-27 21:45:06', '杭州', '2016-08-27 21:45:07', null, '2', '2', 'A00000000020.png', '2016-08-27 21:45:11', '超级管理员', 'test', '', '', '1', null, null, '3', null, 'A00000000023', '1', '1');
INSERT INTO `t_case` VALUES ('50', 'A00000000021', '案件21', 'wjg', '王建观', '2016-08-27 21:45:53', '杭州', '2016-08-27 21:45:55', null, '2', '5', 'A00000000021.png', '2016-08-27 21:45:58', '超级管理员', 'test', '', '', '1', null, null, '3', null, 'A00000000022', '1', '0');
INSERT INTO `t_case` VALUES ('51', 'A00000000022', '案件22', 'wjg', '王建观', '2016-08-27 21:46:18', '杭州', '2016-08-27 21:46:20', null, '2', '5', 'A00000000022.png', '2016-08-27 21:46:23', '超级管理员', 'test', '', '', '1', null, null, '3', null, 'A00000000022', '1', '0');
INSERT INTO `t_case` VALUES ('52', 'A00000000022', 'dsdsd', null, null, null, null, null, '2016-08-27 23:16:04', '4', '5', null, null, null, '', null, null, '1', null, null, '3', null, null, '2', '1');
INSERT INTO `t_case` VALUES ('54', 'A00000000023', '案件22', 'wjg', '王建观', '2016-08-28 09:35:12', '杭州', '2016-08-28 09:35:13', null, '2', '2', 'A00000000023.png', '2016-08-28 09:36:42', '超级管理员', '是打发斯蒂芬', '', '', '2', null, null, '3', null, 'A00000000023', '1', '0');
INSERT INTO `t_case` VALUES ('55', 'A00000000024', 'A00000000024', 'wjg', '王建观', '2016-08-28 09:58:12', '杭州', '2016-08-28 09:58:13', null, '2', '5', 'A00000000024.png', '2016-08-28 09:58:24', '超级管理员', '是的是的', '', '', '1', null, null, '3', null, 'A00000000024', '1', '0');
INSERT INTO `t_case` VALUES ('56', 'A00000000025', 'A00000000025', 'wjg', '王建观', '2016-08-28 09:58:41', '杭州', '2016-08-28 09:58:42', null, '1', '5', 'A00000000025.png', '2016-12-07 15:57:20', '超级管理员', '都是定时', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('57', 'A00000000026', 'A00000000026', 'wjg', '王建观', '2016-08-28 09:58:59', '杭州', '2016-08-28 09:59:00', null, '1', '2', 'A00000000026.png', '2016-08-28 09:59:02', '超级管理员', '多大的', '', '', '1', null, null, '1', null, null, '2', '0');
INSERT INTO `t_case` VALUES ('58', 'A00000000027', 'A00000000027', 'wjg', '王建观', '2016-08-28 09:59:15', '杭州', '2016-08-28 09:59:17', null, '2', '5', 'A00000000027.png', '2016-09-02 22:28:47', '超级管理员', 'test', '', '', '1', null, null, '3', null, 'A2016090102320001', '1', '0');
INSERT INTO `t_case` VALUES ('59', 'A2016090102320001', 'cf盗号案件', 'wjg', '王建观', '2016-09-01 23:20:55', '杭州', '2016-08-02 23:20:56', null, '2', '5', 'A2016090102320001.png', '2016-09-03 11:16:48', '超级管理员', '王建观的账号被盗了', '20160901155359743;20160901155532321;eerre;1212;1221;T111111;T1000;', '天龙狙击枪;意大利炮;ererer;12;2121;T111111;T1000;', '1', null, null, '3', null, 'A2016090102320001', '1', '1');
INSERT INTO `t_case` VALUES ('60', 'A2016090102320001', 'cf盗号案件', null, null, null, null, null, '2016-12-07 16:02:20', '4', '5', null, null, null, 'cf盗号案件', null, null, '1', null, null, '3', null, null, '2', '1');
INSERT INTO `t_case` VALUES ('61', 'A00000000023', '王建观哈哈哈哈哈', null, null, null, null, null, '2016-12-07 16:04:11', '4', '2', null, null, null, '说到底是的多多所', null, null, '1', null, null, '3', null, null, '2', '1');
INSERT INTO `t_case` VALUES ('62', 'A00000000024', 'A00000000024', null, null, null, null, null, '2016-12-07 16:06:14', '4', '5', null, null, null, '', null, null, '1', null, null, '3', null, null, '2', '1');

-- ----------------------------
-- Table structure for t_caselist
-- ----------------------------
DROP TABLE IF EXISTS `t_caselist`;
CREATE TABLE `t_caselist` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `caselist_no` varchar(50) DEFAULT NULL COMMENT '督案单编号',
  `case_id` int(11) DEFAULT NULL COMMENT '案件编号',
  `case_no` varchar(50) DEFAULT NULL COMMENT '案件编号',
  `police_name` varchar(50) DEFAULT NULL COMMENT '经办民警',
  `suggest` text COMMENT '执法建议',
  `audit` varchar(50) DEFAULT NULL COMMENT '审核人',
  `possessions_explain` text COMMENT '涉案财物移交情况',
  `caseend_time` datetime DEFAULT NULL COMMENT '查证时间',
  `reason` text COMMENT '未完成描述',
  `state` varchar(10) DEFAULT NULL COMMENT '督案单状态1-审核中2-正在执行-3已完成4-未完成5-驳回',
  `remarks` text COMMENT '备注',
  `url` varchar(200) DEFAULT NULL COMMENT '纸质督案单地址',
  `caselist_status` varchar(255) DEFAULT NULL COMMENT '督案单类型(1-督案单2-督案消息单)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_caselist
-- ----------------------------
INSERT INTO `t_caselist` VALUES ('14', 'eeeew', '10', 'JKLOIUJ', 'gaozhenbo', '233232', 'wjg', null, null, null, '5', null, 'b0ba1469356058777.jpg;', '1');
INSERT INTO `t_caselist` VALUES ('15', 'A125353625_da', '8', 'A125353625', 'wjg', '多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习多学习学习', 'tzc', null, '2017-09-01 00:00:00', null, '2', null, 'b0801470465788899.jpg;c0811470465795900.jpg;', '1');
INSERT INTO `t_caselist` VALUES ('16', 'dfdfsdfs', '8', 'A125353625', 'wjg', 'sdfdsafdasdsa', 'tzc', null, '2016-08-27 16:25:02', null, '3', null, '62351470471911333.jpg;1a4d1470471916229.jpg;', '2');
INSERT INTO `t_caselist` VALUES ('17', 'sdfsdfsdf', '8', 'A125353625', 'wjg', 'sddfssdfa', 'tzc', null, '2016-09-10 00:00:00', null, '3', null, '3fed1470472573281.jpg;', '2');
INSERT INTO `t_caselist` VALUES ('18', 'dewweew', '10', 'JKLOIUJ', 'wjg', 'sdafsadfasdfasdfsad', 'tzc', null, '2017-09-10 17:19:08', null, '2', null, '30401470475154902.jpg;', '1');
INSERT INTO `t_caselist` VALUES ('19', 'sdfsda', '10', 'JKLOIUJ', 'wjg', '', 'tzc', null, '2016-09-10 00:00:00', null, '2', null, '', '1');
INSERT INTO `t_caselist` VALUES ('20', 'weew', '26', 'A2016080926', 'wjg', 'sdfsadfasd', 'tzc', null, '2016-08-24 17:58:55', null, '5', null, '', '1');
INSERT INTO `t_caselist` VALUES ('21', '122121', '26', 'A2016080926', 'wjg', 'weweweweew', 'tzc', null, '2017-08-24 18:06:40', null, '5', null, '1472033203472.jpg;1472033205022.jpg;', '1');
INSERT INTO `t_caselist` VALUES ('22', 'A1212121', '49', 'A00000000020', 'wjg', '212121', 'tzc', null, '2016-08-28 00:36:53', null, '2', null, '1472315815812.jpg;', '2');
INSERT INTO `t_caselist` VALUES ('23', 'A12212121', '49', 'A00000000020', 'wjg', 'sdfsdfsdf', 'tzc', null, '2016-08-28 00:37:53', null, '2', null, '1472315879832.jpg;', '1');
INSERT INTO `t_caselist` VALUES ('24', '2323', '49', 'A00000000020', 'wjg', 'weewwe', 'tzc', null, '2016-08-25 00:00:00', null, '1', null, '1472316674739.jpg;', '2');
INSERT INTO `t_caselist` VALUES ('25', 'A201602020202', '59', 'A2016090102320001', 'wjg', '测试定时定时', 'tzc', null, '2016-09-03 23:13:38', null, '2', null, '1472872444160.jpg;1472872446469.jpg;', '2');

-- ----------------------------
-- Table structure for t_endcase
-- ----------------------------
DROP TABLE IF EXISTS `t_endcase`;
CREATE TABLE `t_endcase` (
  `case_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `case_no` varchar(50) DEFAULT NULL COMMENT '案件编号',
  `case_add_url` varchar(255) DEFAULT NULL COMMENT '结案登记图片',
  `endcase_explain` text COMMENT '结案说明',
  PRIMARY KEY (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_endcase
-- ----------------------------

-- ----------------------------
-- Table structure for t_equipment
-- ----------------------------
DROP TABLE IF EXISTS `t_equipment`;
CREATE TABLE `t_equipment` (
  `equipment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '装备id',
  `equipment_no` varchar(30) DEFAULT NULL COMMENT '装备编号',
  `equipment_name` varchar(50) DEFAULT NULL,
  `style` varchar(30) DEFAULT NULL COMMENT '型号|规格',
  `equipment_explain` text,
  `keeper` varchar(20) DEFAULT NULL COMMENT '保管员',
  `start_time` datetime DEFAULT NULL COMMENT '入库时间',
  `over_time` datetime DEFAULT NULL COMMENT '报废时间',
  `status` varchar(5) DEFAULT NULL COMMENT '状态（1-库存、2-完好、3-借出、4-报废、5-维修检验、6-未入库）',
  `qrcode` varchar(200) DEFAULT NULL COMMENT '二维码图片url',
  `modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `modify_name` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`equipment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_equipment
-- ----------------------------
INSERT INTO `t_equipment` VALUES ('5', 'XGSD22053443', '消防水枪1', 'EROUOIER1', '各种瑕疵各种瞎扯1111', '石全1', '2016-04-07 00:00:00', '2016-05-01 00:00:59', '3', 'XGSD22053443.png', '2016-09-01 14:28:47', '超级管理员');
INSERT INTO `t_equipment` VALUES ('8', 'XG43823', '消防水枪', 'EROUOIER', '各种瑕疵各种瞎扯', '王建观', '2016-05-01 09:08:50', '2016-05-01 09:08:50', '3', 'XG43823.jpg', '2016-12-06 18:27:35', '超级管理员');
INSERT INTO `t_equipment` VALUES ('9', 'XG4233823', '消防水枪', 'EROUOIER', '各种瑕疵各种瞎扯', '王建观', '2016-05-01 09:08:50', '2016-05-01 09:08:50', '3', 'XG4233823.jpg', '2016-05-14 09:06:29', 'xwl');
INSERT INTO `t_equipment` VALUES ('10', 'XG14233823', '消防水枪', 'EROUOIER', '各种瑕疵各种瞎扯', '王建观', '2016-05-01 09:08:50', '2016-05-01 09:08:50', '3', 'XG14233823.jpg', '2016-05-15 08:53:13', 'xwl');
INSERT INTO `t_equipment` VALUES ('11', 'XG2114233823', '消防水枪', 'EROUOIER', '各种瑕疵各种瞎扯', '王建观', '2016-05-01 09:08:50', '2016-05-01 09:08:50', '3', 'XG2114233823.jpg', '2016-05-15 08:35:06', 'xwl');
INSERT INTO `t_equipment` VALUES ('14', '560TNT1', 'TNT炸药1', 'TNT1', '从高振博哪儿购买的炸药1', '石全1', '2016-07-07 00:00:00', '2016-09-27 00:00:00', '1', '560TNT1.png', '2016-10-02 13:25:50', '超级管理员');
INSERT INTO `t_equipment` VALUES ('18', 'fdeerew', 'wewe', 'wewewe', 'weewwewewe', 'wewe', '2016-07-15 02:12:00', '2016-07-15 02:12:00', '1', 'fdeerew.png', '2017-03-19 23:17:09', '超级管理员');
INSERT INTO `t_equipment` VALUES ('19', 'fdfd', 'fdfd', 'dffd', 'dffd', 'dffd', '2016-07-15 02:41:00', '2016-07-15 02:41:02', '1', 'fdfd.jpg', '2016-07-15 02:41:16', 'wjg');
INSERT INTO `t_equipment` VALUES ('20', 'ew3', 'ewwe', 'wew', 'wfw', 'wewe', '2016-07-15 03:30:00', '2016-07-15 03:30:59', '1', 'ew3.jpg', '2016-07-24 16:30:03', 'wjg');
INSERT INTO `t_equipment` VALUES ('22', 'fdfdwerewrew', '3232', 'fdf', 'rewrewrew', 'dfdfd', '2016-07-22 00:00:00', '2016-07-22 00:00:00', '1', 'fdfdwerewrew.jpg', '2016-07-22 23:50:55', 'wjg');
INSERT INTO `t_equipment` VALUES ('23', '111', '1', '11', '255125115', '11', '2016-07-24 00:00:00', '2016-07-24 00:00:00', '3', '111.jpg', '2016-12-06 16:59:49', '超级管理员');
INSERT INTO `t_equipment` VALUES ('24', 'P1111', '喷头', 'PENTOU', '喂喂喂喂喂喂喂喂喂喂喂喂喂喂喂喂额呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃', '王建观', '2016-08-04 00:00:00', null, '1', 'P1111.png', '2016-08-04 15:44:21', 'wjg');

-- ----------------------------
-- Table structure for t_fixedassets
-- ----------------------------
DROP TABLE IF EXISTS `t_fixedassets`;
CREATE TABLE `t_fixedassets` (
  `assets_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `assets_no` varchar(30) NOT NULL COMMENT '资产编号',
  `assets_name` varchar(50) DEFAULT NULL COMMENT '资产名称',
  `style` varchar(30) DEFAULT NULL COMMENT '型号规格',
  `use_department` varchar(255) DEFAULT NULL COMMENT '默认使用人',
  `assets_explain` text COMMENT '资产说明',
  `keeper` varchar(20) DEFAULT NULL COMMENT '保管员',
  `start_time` datetime DEFAULT NULL COMMENT '入库时间',
  `modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `modify_name` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  `qrcode` varchar(200) DEFAULT NULL COMMENT '二维码地址',
  `status` int(20) DEFAULT NULL COMMENT '状态（1-库存、2-完好、3-借出、4-报废、5-维修检验、6-未入库）',
  `use_person` varchar(255) DEFAULT NULL COMMENT '默认使用人',
  PRIMARY KEY (`assets_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_fixedassets
-- ----------------------------
INSERT INTO `t_fixedassets` VALUES ('11', '444441', '232323f12', '232312', '喂喂喂', '速度发撒旦法师打法是否撒反对撒范德萨发撒旦法倒速度发撒旦法师打法是否撒反对撒范德萨发撒旦法倒速度发撒旦法师打法是否撒反对撒范德萨发撒旦法倒weweewwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww', '2323212', '2016-07-16 11:14:49', '2016-12-06 15:37:07', '肖伟丽', '444441.png', '3', '问问我');
INSERT INTO `t_fixedassets` VALUES ('13', 'we', 'dgd', 'bd', '12122121', 'sf', 'weasf', '2016-07-18 12:00:00', '2016-12-06 14:54:16', '超级管理员', 'we.jpg', '5', '2212121');
INSERT INTO `t_fixedassets` VALUES ('16', '231', 'erw', 'we', null, '2332', 'qwe', '2016-05-26 00:00:00', '2016-07-06 12:15:27', 'wjg', '231.jpg', '1', '3');
INSERT INTO `t_fixedassets` VALUES ('18', 'HASEE', '神州战神', 'Z6-i78154S2', null, '6500购入', '王建观', '2015-10-01 00:00:00', '2016-07-06 17:04:03', 'wjg', 'HASEE.jpg', '1', '5');
INSERT INTO `t_fixedassets` VALUES ('19', 'pro', 'mac pro 13', 'mac pro 13', null, '7499购入', '肖伟丽', '2016-06-01 00:00:00', '2016-07-06 17:04:08', 'wjg', 'pro.jpg', '1', '4');
INSERT INTO `t_fixedassets` VALUES ('20', 'HSMF007', '黄山毛峰', 'MJ001', null, '茶中正品，好茶道', '王建观', '2016-07-05 00:00:00', '2016-07-06 17:04:12', 'wjg', 'HSMF007.jpg', '1', null);
INSERT INTO `t_fixedassets` VALUES ('21', 'B1-001', '魅族手机', 'NOTE2', null, '魅族手机爱重启', '王建观', '2016-07-05 00:00:00', '2016-07-05 13:31:58', 'wjg', 'B1-001.jpg', '3', null);
INSERT INTO `t_fixedassets` VALUES ('22', '4444', '2121', '2121', '55', '2121', '2121', '2016-07-15 04:04:00', '2016-07-15 04:04:20', 'wjg', '4444.jpg', '1', '55');
INSERT INTO `t_fixedassets` VALUES ('23', 'ASD', '桌子', 'IKLN', null, 'sdfs', '王建观', '2016-07-17 23:08:00', '2016-08-18 11:15:18', 'wjg', 'ASD.jpg', '5', null);
INSERT INTO `t_fixedassets` VALUES ('24', 'OKML', '电脑', 'IL:', '', 'sdds', '王建观', '2016-07-17 23:10:00', '2016-12-07 15:46:39', '超级管理员', 'OKML.png', '6', '');
INSERT INTO `t_fixedassets` VALUES ('25', 'sdssd', '灭火器1', 'REJREO', '事业一部', '扯淡扯扯澈澈', '肖伟丽', '2016-07-18 00:00:00', '2016-12-06 15:21:25', '超级管理员', 'sdssd.jpg', '3', '王建观');
INSERT INTO `t_fixedassets` VALUES ('26', 'sdss1d', '灭火器1', 'REJREO', '事业一部', '扯淡扯扯澈澈', '肖伟丽', '2015-03-03 12:09:09', '2016-07-19 20:24:02', 'wjg', 'sdss1d.jpg', '1', '王建观');
INSERT INTO `t_fixedassets` VALUES ('27', 'sf', 'er', 'y', 'sf', 'ff', 'u', '2016-07-05 00:00:00', '2016-07-19 21:10:36', 'wjg', 'sf.jpg', '1', 'fs');
INSERT INTO `t_fixedassets` VALUES ('28', 's222f', 'er', 'y', 'sf', 'ff', 'u', '2016-07-05 00:00:00', '2016-07-19 21:16:43', 'wjg', 's222f.jpg', '1', 'fs');
INSERT INTO `t_fixedassets` VALUES ('30', 'qwr', 'ret', 'wt', 'gw', 'trey', 'yert', '2016-07-18 00:00:00', '2016-12-06 16:24:06', '超级管理员', 'qwr.jpg', '3', 'wq');
INSERT INTO `t_fixedassets` VALUES ('31', 'saf', 'dsf', 'gf', 'sg', '是的sd实打实的十大的实打实的实打实的所得税', 'ghd', '2016-07-18 00:00:00', '2016-08-18 11:20:03', 'wjg', 'saf.png', '5', 'gsg');
INSERT INTO `t_fixedassets` VALUES ('32', 'et', 'fas', '', '', 'asfgg', '', '2016-07-18 00:00:00', '2016-07-19 21:31:14', 'wjg', 'et.jpg', '1', '');
INSERT INTO `t_fixedassets` VALUES ('33', 'asf', 'ew', 'et', 'sf', 'saf', 'yr', '2016-07-18 00:00:00', '2016-12-06 16:18:10', '超级管理员', 'asf.jpg', '3', 'sfrew');
INSERT INTO `t_fixedassets` VALUES ('37', 'dfssdfsd', 'sdfadsfads', 'adsfads', '', 'dsafads', 'sdafadsfads', null, '2016-09-01 14:45:02', '超级管理员', 'dfssdfsd.png', '1', '');
INSERT INTO `t_fixedassets` VALUES ('38', 'asfwewe', 'wewe', 'weew', null, 'wewewe', 'weew', null, '2016-07-22 23:08:34', 'wjg', 'asfwewe.jpg', '1', null);
INSERT INTO `t_fixedassets` VALUES ('39', 'weew', 'wewe', 'wewe', '王建观', 'wewewewewe', 'wewe', '2016-08-04 10:28:22', '2016-08-06 20:36:42', 'wjg', 'weew.png', '3', '王建观');
INSERT INTO `t_fixedassets` VALUES ('40', 'weewewewwe', 'ewew', 'wewe', 'wewe', 'ewwe', 'weew', '2016-07-22 00:00:00', '2016-09-01 14:30:29', '超级管理员', 'weewewewwe.png', '3', 'wewe');
INSERT INTO `t_fixedassets` VALUES ('41', '12', '1212', '1212', '', '', 'admin', '2016-12-22 00:00:00', '2016-12-06 16:30:42', '超级管理员', '12.png', '1', '');

-- ----------------------------
-- Table structure for t_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_msg`;
CREATE TABLE `t_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '内容',
  `content` text,
  `start_time` datetime DEFAULT NULL COMMENT '发布时间',
  `user_login` varchar(255) DEFAULT NULL COMMENT '收件人',
  `type` int(11) DEFAULT NULL COMMENT ' 1-通知 2-督案单消息3-结案到期通知',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=397 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_msg
-- ----------------------------
INSERT INTO `t_msg` VALUES ('329', '尊敬的【王建观】同志，您好，您于【2016-05-15 08:49:46.0】，在【xwl】处借了【消防水枪】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '王建观', '1');
INSERT INTO `t_msg` VALUES ('330', '尊敬的管理员同志，【王建观】同志于【2016-05-15 08:49:46.0】在【xwl】处借了【消防水枪】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('331', '尊敬的【admin】同志，您好，您于【2016-05-15 08:53:13.0】，在【xwl】处借了【消防水枪】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('332', '尊敬的管理员同志，【admin】同志于【2016-05-15 08:53:13.0】在【xwl】处借了【消防水枪】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('333', '尊敬的【】同志，您好，您于【2016-05-15 08:53:43.0】，在【xwl】处借了【消防水枪44444TMD】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '', '1');
INSERT INTO `t_msg` VALUES ('334', '尊敬的管理员同志，【】同志于【2016-05-15 08:53:43.0】在【xwl】处借了【消防水枪44444TMD】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('335', '尊敬的【wang-jg1】同志，您好，您于【2016-05-15 09:01:43.0】，在【xwl】处借了【分为访问】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'wang-jg1', '1');
INSERT INTO `t_msg` VALUES ('336', '尊敬的管理员同志，【wang-jg1】同志于【2016-05-15 09:01:43.0】在【xwl】处借了【分为访问】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('337', '尊敬的【肖伟丽】同志，您好，您于【2016-05-14 09:06:29.0】，在【xwl】处借了【消防水枪】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '肖伟丽', '1');
INSERT INTO `t_msg` VALUES ('338', '尊敬的管理员同志，【肖伟丽】同志于【2016-05-14 09:06:29.0】在【xwl】处借了【消防水枪】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('339', '尊敬的【】同志，您好，您于【2016-07-05 13:30:15.0】，在【wjg】处借了【魅族手机】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '', '1');
INSERT INTO `t_msg` VALUES ('340', '尊敬的管理员同志，【】同志于【2016-07-05 13:30:15.0】在【wjg】处借了【魅族手机】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('341', '尊敬的【】同志，您好，您于【2016-07-05 13:30:26.0】，在【wjg】处借了【魅族手机】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '', '1');
INSERT INTO `t_msg` VALUES ('342', '尊敬的管理员同志，【】同志于【2016-07-05 13:30:26.0】在【wjg】处借了【魅族手机】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('343', '尊敬的【】同志，您好，您于【2016-07-05 13:31:58.0】，在【wjg】处借了【魅族手机】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '', '1');
INSERT INTO `t_msg` VALUES ('344', '尊敬的管理员同志，【】同志于【2016-07-05 13:31:58.0】在【wjg】处借了【魅族手机】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('345', '尊敬的【null】同志，您好，您于【2016-07-08 17:55:12.0】，在【wjg】处借了【电脑】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '', '1');
INSERT INTO `t_msg` VALUES ('346', '尊敬的管理员同志，【null】同志于【2016-07-08 17:55:12.0】在【wjg】处借了【电脑】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('347', '尊敬的【null】同志，您好，您于【2016-07-08 17:55:26.0】，在【wjg】处借了【电脑】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '', '1');
INSERT INTO `t_msg` VALUES ('348', '尊敬的管理员同志，【null】同志于【2016-07-08 17:55:26.0】在【wjg】处借了【电脑】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('349', '尊敬的【王建观】同志，您好，您于【2016-07-08 17:57:45.0】，在【wjg】处借了【苹果电脑】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '王建观', '1');
INSERT INTO `t_msg` VALUES ('350', '尊敬的管理员同志，【王建观】同志于【2016-07-08 17:57:45.0】在【wjg】处借了【苹果电脑】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('351', '尊敬的【王建观】同志，您好，您于【2016-07-08 17:59:03.0】，在【wjg】处借了【分为访问】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '王建观', '1');
INSERT INTO `t_msg` VALUES ('352', '尊敬的管理员同志，【王建观】同志于【2016-07-08 17:59:03.0】在【wjg】处借了【分为访问】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('353', '尊敬的【weew】同志，您好，您于【2016-08-06 20:33:21.0】，在【wjg】处借了【wewe】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'weew', '1');
INSERT INTO `t_msg` VALUES ('354', '尊敬的管理员同志，【weew】同志于【2016-08-06 20:33:21.0】在【wjg】处借了【wewe】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('355', '尊敬的【wewe】同志，您好，您于【2016-08-08 00:00:00.0】，在【wjg】处借了【wewe】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'wewe', '1');
INSERT INTO `t_msg` VALUES ('356', '尊敬的管理员同志，【wewe】同志于【2016-08-08 00:00:00.0】在【wjg】处借了【wewe】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('357', '尊敬的【sdsd】同志，您好，您于【2016-08-15 00:00:00.0】，在【wjg】处借了【wewe】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'sdsd', '1');
INSERT INTO `t_msg` VALUES ('358', '尊敬的管理员同志，【sdsd】同志于【2016-08-15 00:00:00.0】在【wjg】处借了【wewe】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('359', '尊敬的【weew】同志，您好，您于【2016-08-30 00:00:00.0】，在【wjg】处借了【wewe】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'weew', '1');
INSERT INTO `t_msg` VALUES ('360', '尊敬的管理员同志，【weew】同志于【2016-08-30 00:00:00.0】在【wjg】处借了【wewe】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('361', '尊敬的【sdaasd】同志，您好，您于【2016-08-30 00:00:00.0】，在【wjg】处借了【消防水枪】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'sdaasd', '1');
INSERT INTO `t_msg` VALUES ('362', '尊敬的管理员同志，【sdaasd】同志于【2016-08-30 00:00:00.0】在【wjg】处借了【消防水枪】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('363', '尊敬的【sdsd】同志，您好，您于【2016-08-09 00:00:00.0】，在【wjg】处借了【笔记本电脑】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'sdsd', '1');
INSERT INTO `t_msg` VALUES ('364', '尊敬的管理员同志，【sdsd】同志于【2016-08-09 00:00:00.0】在【wjg】处借了【笔记本电脑】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('365', '尊敬的【王建观】同志，您好，您于【2016-08-30 22:23:41.0】，在【超级管理员】处借了【案件12】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '王建观', '1');
INSERT INTO `t_msg` VALUES ('366', '尊敬的管理员同志，【王建观】同志于【2016-08-30 22:23:41.0】在【超级管理员】处借了【案件12】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('367', '尊敬的【王建观】同志，您好，您于【2016-08-30 22:23:52.0】，在【超级管理员】处借了【案件12】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '王建观', '1');
INSERT INTO `t_msg` VALUES ('368', '尊敬的管理员同志，【王建观】同志于【2016-08-30 22:23:52.0】在【超级管理员】处借了【案件12】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('369', '尊敬的【王建观】同志，您好，您于【2016-08-30 22:24:05.0】，在【超级管理员】处借了【案件12】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '王建观', '1');
INSERT INTO `t_msg` VALUES ('370', '尊敬的管理员同志，【王建观】同志于【2016-08-30 22:24:05.0】在【超级管理员】处借了【案件12】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('371', '尊敬的【王建观】同志，您好，您于【2016-12-06 15:21:09.0】，在【超级管理员】处借了【ret】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '王建观', '1');
INSERT INTO `t_msg` VALUES ('372', '尊敬的管理员同志，【王建观】同志于【2016-12-06 15:21:09.0】在【超级管理员】处借了【ret】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('373', '尊敬的【王建观s】同志，您好，您于【2016-12-06 15:21:21.0】，在【超级管理员】处借了【灭火器1】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', '王建观s', '1');
INSERT INTO `t_msg` VALUES ('374', '尊敬的管理员同志，【王建观s】同志于【2016-12-06 15:21:21.0】在【超级管理员】处借了【灭火器1】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('375', '尊敬的【xwl】同志，您好，您于【2016-12-06 15:36:40.0】，在【肖伟丽】处借了【232323f12】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'xwl', '1');
INSERT INTO `t_msg` VALUES ('376', '尊敬的管理员同志，【xwl】同志于【2016-12-06 15:36:40.0】在【肖伟丽】处借了【232323f12】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('377', '尊敬的【null|石全】同志，您好，您于【2016-12-06 16:18:06.0】，在【admin|超级管理员】处借了【ew】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'null|石全', '1');
INSERT INTO `t_msg` VALUES ('378', '尊敬的管理员同志，【null|石全】同志于【2016-12-06 16:18:06.0】在【admin|超级管理员】处借了【ew】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('379', '尊敬的【gaozhenbo|高振博】同志，您好，您于【2016-12-06 16:22:44.0】，在【admin|超级管理员】处借了【ret】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'gaozhenbo|高振博', '1');
INSERT INTO `t_msg` VALUES ('380', '尊敬的管理员同志，【gaozhenbo|高振博】同志于【2016-12-06 16:22:44.0】在【admin|超级管理员】处借了【ret】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('381', '尊敬的【wjg|王建观】同志，您好，您于【2016-12-06 16:24:01.0】，在【admin|超级管理员】处借了【ret】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'wjg|王建观', '1');
INSERT INTO `t_msg` VALUES ('382', '尊敬的管理员同志，【wjg|王建观】同志于【2016-12-06 16:24:01.0】在【admin|超级管理员】处借了【ret】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('383', '尊敬的【wjg|王建观】同志，您好，您于【2016-12-06 16:59:18.0】，在【admin|超级管理员】处借了【消防水枪】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'wjg|王建观', '1');
INSERT INTO `t_msg` VALUES ('384', '尊敬的管理员同志，【wjg|王建观】同志于【2016-12-06 16:59:18.0】在【admin|超级管理员】处借了【消防水枪】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('385', '尊敬的【tzc|唐自成】同志，您好，您于【2016-12-06 16:59:43.0】，在【admin|超级管理员】处借了【1】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'tzc|唐自成', '1');
INSERT INTO `t_msg` VALUES ('386', '尊敬的管理员同志，【tzc|唐自成】同志于【2016-12-06 16:59:43.0】在【admin|超级管理员】处借了【1】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('387', '尊敬的【null|】同志，您好，您于【null】，在【admin|超级管理员】处借了【A00000000027】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'null|', '1');
INSERT INTO `t_msg` VALUES ('388', '尊敬的管理员同志，【null|】同志于【null】在【admin|超级管理员】处借了【A00000000027】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('389', '尊敬的【wangjianguan;大神观】同志，您好，您于【2016-12-06 17:28:55.0】，在【admin|超级管理员】处借了【案件20】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'wangjianguan', '1');
INSERT INTO `t_msg` VALUES ('390', '尊敬的管理员同志，【wangjianguan;大神观】同志于【2016-12-06 17:28:55.0】在【admin|超级管理员】处借了【案件20】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('391', '尊敬的【wangjianguan;大神观】同志，您好，您于【2016-12-06 18:08:15.0】，在【admin|超级管理员】处借了【ererer】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'wangjianguan', '1');
INSERT INTO `t_msg` VALUES ('392', '尊敬的管理员同志，【wangjianguan;大神观】同志于【2016-12-06 18:08:15.0】在【admin|超级管理员】处借了【ererer】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('393', '尊敬的【wangjianguan;大神观】同志，您好，您于【2016-12-06 18:27:31.0】，在【admin;超级管理员】处借了【消防水枪】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'wangjianguan', '1');
INSERT INTO `t_msg` VALUES ('394', '尊敬的管理员同志，【wangjianguan;大神观】同志于【2016-12-06 18:27:31.0】在【admin;超级管理员】处借了【消防水枪】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');
INSERT INTO `t_msg` VALUES ('395', '尊敬的【xwl;肖伟丽】同志，您好，您于【2016-12-06 18:29:07.0】，在【admin;超级管理员】处借了【案件19】，尚未归还，请在下班前到管理员处归还。谢谢！', '2016-12-07 15:14:00', 'xwl', '1');
INSERT INTO `t_msg` VALUES ('396', '尊敬的管理员同志，【xwl;肖伟丽】同志于【2016-12-06 18:29:07.0】在【admin;超级管理员】处借了【案件19】，尚未归还，请在督促其处归还。谢谢！', '2016-12-07 15:14:00', 'admin', '1');

-- ----------------------------
-- Table structure for t_possessions
-- ----------------------------
DROP TABLE IF EXISTS `t_possessions`;
CREATE TABLE `t_possessions` (
  `possessions_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `possessions_no` varchar(50) DEFAULT NULL COMMENT '涉案财物编号',
  `possessions_name` varchar(100) DEFAULT NULL COMMENT '涉案财物名称',
  `style` varchar(50) DEFAULT NULL COMMENT '型号规格',
  `username` varchar(50) DEFAULT NULL COMMENT '财物持有人',
  `start_time` datetime DEFAULT NULL COMMENT '入库时间',
  `keeper` varchar(50) DEFAULT NULL COMMENT '保管员',
  `possessions_explain` text COMMENT '财物说明',
  `status` varchar(5) DEFAULT NULL COMMENT '状态（1-库存3-借出2-持有人领回4-未入库5-移交）7-已销毁',
  `modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `modify_name` varchar(50) DEFAULT NULL COMMENT '最后修改人',
  `holder_name` varchar(50) DEFAULT NULL COMMENT '持有者姓名(领回)',
  `qrcode` varchar(255) DEFAULT NULL COMMENT '二维码',
  `holder_time` datetime DEFAULT NULL COMMENT '领回时间',
  `possessions_pic` varchar(255) DEFAULT NULL COMMENT '涉案财物图片',
  `possType` varchar(255) DEFAULT NULL COMMENT '涉案财物类型 1 收缴  2 扣押',
  PRIMARY KEY (`possessions_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_possessions
-- ----------------------------
INSERT INTO `t_possessions` VALUES ('1', '332', '苹果电脑1', 'MAC', '王建观', '2016-05-07 00:00:00', '王建观', '奖励的', '3', '2016-07-10 00:00:00', 'wjg', '', null, null, '', '1');
INSERT INTO `t_possessions` VALUES ('3', 'GDSAFG', '笔记本电脑', '笔记本电脑', '高振博', '2016-05-14 00:00:00', '高振博', '分为访问访问问访问f', '3', '2016-08-06 21:02:37', 'wjg', '', null, null, '', '1');
INSERT INTO `t_possessions` VALUES ('4', 'YUHYHY', '电脑', 'LENVOV', '王建观', '2016-07-08 00:00:00', '王建观', '问问我', '3', '2016-07-08 00:00:00', 'wjg', '1', null, '2016-07-29 00:00:00', '', '1');
INSERT INTO `t_possessions` VALUES ('5', 'T1000', 'T1000', 'T1000', 'T1000', '2016-08-04 22:19:36', 'wjg', 'T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000T1000', '2', '2016-08-04 22:19:40', 'wjg', 'wjg', null, null, '8a1e1470320159099.jpg;b4eb1470320163021.jpg;27741470320188218.png', '1');
INSERT INTO `t_possessions` VALUES ('6', 'T1001', 'T100011', '11', '111', '2016-08-04 21:29:50', '111', '1111111', '2', '2016-08-04 21:30:21', 'wjg', '1111', null, '2016-08-04 21:29:52', null, '1');
INSERT INTO `t_possessions` VALUES ('7', 'T111111', 'T111111', 'T111111', 'T111111T111111', '2016-08-05 00:00:00', 'T111111', 'T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111T111111', '2', '2016-08-05 14:24:53', 'wjg', '王建观', null, '2016-08-05 14:24:51', '12ac1470369072417.jpg;ca481470320046094.png;bd6c1470320072022.jpg', '1');
INSERT INTO `t_possessions` VALUES ('8', '1212', '12', '12', '12', '2016-08-26 00:00:00', '12', '1212', '1', '2016-08-14 22:26:45', 'wjg', '', null, null, 'e18d1471184803712.jpg;12fd1471184775800.jpg;92041471184778077.jpg', '1');
INSERT INTO `t_possessions` VALUES ('9', '1221', '2121', '21', '122121', '2016-01-23 00:00:00', 'admin', '12121212', '5', '2016-09-03 11:26:30', '超级管理员', '', '1221.png', null, '356a1471185296185.jpg;44641471185301771.jpg;403e1471185304748.jpg', null);
INSERT INTO `t_possessions` VALUES ('10', 'eerre', 'ererer', 'rereer', 'erer', '2016-08-31 00:00:00', 'rere', 'ererer', '3', '2016-12-06 18:08:19', '超级管理员', '', 'eerre.png', null, '1471188522113.jpg;1471187776324.jpg;1471188085846.jpg', '1');
INSERT INTO `t_possessions` VALUES ('11', '20160901155359743', '天龙狙击枪', 'bat', '王建观', '2016-09-30 00:00:00', '王建观', '是大多数都是实打实的都是', '7', '2016-09-03 11:39:44', '超级管理员', '', '20160901155359743.png', null, '1472716422834.jpg;1472716438069.jpg;1472716424544.jpg', '1');
INSERT INTO `t_possessions` VALUES ('12', '20160901155532321', '意大利炮', '11111', '王建观', '2016-09-23 00:00:00', '王建观', '说到底是是的恩恩', '1', '2016-09-01 15:55:32', '超级管理员', '', '20160901155532321.png', null, '1472716526014.jpg;1472716529107.jpg;1472716530750.jpg;', '1');

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `record_no` varchar(50) DEFAULT NULL COMMENT '编号（涉案财物编号、案件编号、装备编号、资产编号）',
  `record_style` varchar(50) DEFAULT NULL COMMENT '型号',
  `record_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `record_time` datetime DEFAULT NULL COMMENT '借出（还入）时间',
  `use_department` varchar(20) DEFAULT NULL COMMENT '使用人',
  `agent` varchar(50) DEFAULT NULL COMMENT '经办人',
  `use_explain` text COMMENT '使用说明（还入可为空）',
  `status` varchar(5) DEFAULT NULL COMMENT '状态（3-借出、1-还入）',
  `style` varchar(5) DEFAULT NULL COMMENT '类型（1-资产、2-装备 3-案卷 4-涉案财物）',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES ('13', '232323', '2323', '232323', '2016-05-15 08:46:41', null, 'xwl', '王建观', '3', '1');
INSERT INTO `t_record` VALUES ('14', '231', 'we', 'erw', '2016-05-15 08:46:50', 'admin', 'xwl', 'admin', '3', '1');
INSERT INTO `t_record` VALUES ('15', '24', 'et', '3', '2016-05-15 08:46:56', 'wang-jg1', 'xwl', 'wang-jg1', '3', '1');
INSERT INTO `t_record` VALUES ('16', '232323', '2323', '232323', '2016-05-15 08:47:03', null, 'xwl', '', '1', '1');
INSERT INTO `t_record` VALUES ('17', 'XGSD22053443', 'EROUOIER', '消防水枪', '2016-05-15 08:49:46', '王建观', 'xwl', '王建观', '3', '2');
INSERT INTO `t_record` VALUES ('18', 'XG43', 'EROUOIER', '消防水枪', '2016-05-15 08:52:42', 'admin', 'xwl', 'admin', '3', '2');
INSERT INTO `t_record` VALUES ('19', 'XG14233823', 'EROUOIER', '消防水枪', '2016-05-15 08:53:13', 'admin', 'xwl', 'admin', '3', '2');
INSERT INTO `t_record` VALUES ('20', 'REER', 'EROUOIER', '消防水枪', '2016-05-15 08:53:23', 'wang-jg1', 'xwl', 'wang-jg1', '3', '2');
INSERT INTO `t_record` VALUES ('21', 'XGSD22053443', 'EROUOIER', '消防水枪44444TMD', '2016-05-15 08:53:43', '', 'xwl', '', '1', '2');
INSERT INTO `t_record` VALUES ('22', '违法', '3', '2323', '2016-05-15 08:54:07', 'admin', 'xwl', 'admin', '3', '3');
INSERT INTO `t_record` VALUES ('23', 'uijijewjiififiigifej', '3', '王建观王建观王建观王建观', '2016-05-15 08:54:18', 'admin', 'xwl', 'admin', '3', '3');
INSERT INTO `t_record` VALUES ('24', 'GDSAFG', '32让2', '分为访问', '2016-05-15 09:01:43', 'wang-jg1', 'xwl', 'wang-jg1', '3', '4');
INSERT INTO `t_record` VALUES ('25', '24', 'et', '3', '2016-05-14 09:05:09', null, 'xwl', '肖伟丽', '3', '1');
INSERT INTO `t_record` VALUES ('27', 'XG4233823', 'EROUOIER', '消防水枪', '2016-05-14 09:06:29', '肖伟丽', 'xwl', '肖伟丽2333333333', '3', '2');
INSERT INTO `t_record` VALUES ('28', '24', 'et', '3', '2016-05-15 11:44:50', '王建观', 'xwl', '王建观323232', '3', '1');
INSERT INTO `t_record` VALUES ('29', '24', 'et', '3', '2016-05-15 11:44:50', '王建观', 'xwl', '王建观323232', '3', '1');
INSERT INTO `t_record` VALUES ('30', '24', 'et', '3', '2016-05-15 11:44:50', '王建观', 'xwl', '王建观323232', '3', '1');
INSERT INTO `t_record` VALUES ('31', 'we', 'bd', 'dgd', '2016-05-15 21:28:41', '', 'xwl', '', '1', '1');
INSERT INTO `t_record` VALUES ('32', 'HASEE', 'Z6-i78154S2', '神州战神', '2016-06-29 22:05:43', null, 'wjg', null, '1', '1');
INSERT INTO `t_record` VALUES ('33', 'HASEE', 'Z6-i78154S2999', '神州战神', '2016-06-29 22:12:19', null, 'wjg', null, '1', '1');
INSERT INTO `t_record` VALUES ('34', 'HASEE', 'Z6-i78154S2', '神州战神', '2016-06-29 22:12:46', null, 'wjg', null, '1', '1');
INSERT INTO `t_record` VALUES ('35', '22222', '2323', '232323', '2016-07-03 12:41:30', null, 'wjg', null, '1', '1');
INSERT INTO `t_record` VALUES ('36', '2222211', '2323', '232323', '2016-07-03 12:41:37', null, 'wjg', null, '1', '1');
INSERT INTO `t_record` VALUES ('37', 'pro', 'mac pro 13', 'mac pro 13', '2016-07-05 11:03:14', null, 'wjg', null, '1', '1');
INSERT INTO `t_record` VALUES ('38', '231', 'we', 'erw', '2016-07-05 11:51:50', '', 'wjg', '', '3', '1');
INSERT INTO `t_record` VALUES ('40', 'B1-001', 'NOTE2', '魅族手机', '2016-07-05 13:30:15', '', 'wjg', '', '3', '1');
INSERT INTO `t_record` VALUES ('41', 'B1-001', 'NOTE2', '魅族手机', '2016-07-05 13:30:26', '', 'wjg', '', '1', '1');
INSERT INTO `t_record` VALUES ('42', 'B1-001', 'NOTE2', '魅族手机', '2016-07-05 13:31:58', '', 'wjg', '', '3', '1');
INSERT INTO `t_record` VALUES ('43', 'HASEE', 'Z6-i78154S2', '神州战神', '2016-07-06 17:03:08', '高振博', 'wjg', '借出去了', '3', '1');
INSERT INTO `t_record` VALUES ('44', 'pro', 'mac pro 13', 'mac pro 13', '2016-07-06 17:03:26', '石全', 'wjg', '送你了', '3', '1');
INSERT INTO `t_record` VALUES ('45', 'HSMF007', 'MJ001', '黄山毛峰', '2016-07-06 17:03:37', '孙爽', 'wjg', '全借出去', '3', '1');
INSERT INTO `t_record` VALUES ('46', 'HASEE', 'Z6-i78154S2', '神州战神', '2016-07-06 17:04:03', '', 'wjg', '', '1', '1');
INSERT INTO `t_record` VALUES ('47', 'pro', 'mac pro 13', 'mac pro 13', '2016-07-06 17:04:08', '', 'wjg', '', '1', '1');
INSERT INTO `t_record` VALUES ('48', 'HSMF007', 'MJ001', '黄山毛峰', '2016-07-06 17:04:12', '', 'wjg', '', '1', '1');
INSERT INTO `t_record` VALUES ('49', '560TNT1', 'TNT1', 'TNT炸药1', '2016-07-07 16:07:19', '', 'wjg', '', '3', '2');
INSERT INTO `t_record` VALUES ('50', '560TNT1', 'TNT1', 'TNT炸药1', '2016-07-07 16:08:27', '王建观', 'wjg', '借出的时候忘记登记这个字段了，现在一起写上，我接炸药去炸宿舍', '1', '2');
INSERT INTO `t_record` VALUES ('51', 'YUHYHY', 'LENVOV', '电脑', '2016-07-08 17:55:12', null, 'wjg', null, '1', '4');
INSERT INTO `t_record` VALUES ('52', 'YUHYHY', 'LENVOV', '电脑', '2016-07-08 17:55:26', null, 'wjg', null, '3', '4');
INSERT INTO `t_record` VALUES ('53', '332', 'MAC', '苹果电脑', '2016-07-08 17:57:45', '王建观', 'wjg', '王建观王建观王建观', '3', '4');
INSERT INTO `t_record` VALUES ('54', 'GDSAFG', '32让2', '分为访问', '2016-07-08 17:59:03', '王建观', 'wjg', '王建观还入', '1', '4');
INSERT INTO `t_record` VALUES ('55', '2332', '3', '3321111111', '2016-07-10 22:58:13', '', 'wjg', '', '3', '3');
INSERT INTO `t_record` VALUES ('56', '44444', '23231', '232323f1', '2016-07-15 04:05:50', '', 'wjg', '', '1', '1');
INSERT INTO `t_record` VALUES ('57', 'weew', null, 'wewe', '2016-08-06 20:33:21', 'weew', 'wjg', 'wewewe', '3', '1');
INSERT INTO `t_record` VALUES ('58', 'weew', null, 'wewe', '2016-08-08 00:00:00', 'wewe', 'wjg', 'weewwe', '3', '1');
INSERT INTO `t_record` VALUES ('59', 'weew', null, 'wewe', '2016-08-15 00:00:00', 'sdsd', 'wjg', 'fsdfdsa', '3', '1');
INSERT INTO `t_record` VALUES ('60', 'weew', null, 'wewe', '2016-08-30 00:00:00', 'weew', 'wjg', 'weewew', '3', '1');
INSERT INTO `t_record` VALUES ('61', 'XG43823', null, '消防水枪', '2016-08-30 00:00:00', 'sdaasd', 'wjg', 'dsadsa', '3', '2');
INSERT INTO `t_record` VALUES ('62', '1221221', null, '杀人案件', '2016-08-02 00:00:00', 'sdds', 'wjg', 'sddsds', '3', '3');
INSERT INTO `t_record` VALUES ('63', '1221221', null, '杀人案件', '2016-08-04 00:00:00', 'sdsd', 'wjg', 'dsds', '3', '3');
INSERT INTO `t_record` VALUES ('64', '1221221', null, '杀人案件', '2016-08-16 00:00:00', 'sdds', 'wjg', 'sdsdds', '3', '3');
INSERT INTO `t_record` VALUES ('65', 'GDSAFG', null, '笔记本电脑', '2016-08-09 00:00:00', 'sdsd', 'wjg', 'sdsdsdds', '3', '4');
INSERT INTO `t_record` VALUES ('66', 'A00000000017', null, '案件17', '2016-08-28 00:56:27', 'sddsds', '超级管理员', 'dsdsds', '3', '3');
INSERT INTO `t_record` VALUES ('67', 'A00000000017', '3', '案件17', '2016-08-28 01:11:22', null, '超级管理员', null, '3', '3');
INSERT INTO `t_record` VALUES ('68', 'A00000000017', '3', '案件17', '2016-08-28 01:16:05', null, '超级管理员', null, '3', '3');
INSERT INTO `t_record` VALUES ('69', 'A00000000017', '3', '案件17', '2016-08-28 01:19:56', null, '超级管理员', null, '3', '3');
INSERT INTO `t_record` VALUES ('70', 'A00000000017', '1', '案件17', '2016-08-28 01:25:43', null, '超级管理员', null, '1', '3');
INSERT INTO `t_record` VALUES ('71', 'we', null, 'dgd', '2016-09-09 00:00:00', 'wewe', '超级管理员', 'wewewe', '3', '1');
INSERT INTO `t_record` VALUES ('72', 'we', null, 'dgd', '2016-08-25 00:00:00', 'wewe', '超级管理员', 'wewe', '3', '1');
INSERT INTO `t_record` VALUES ('73', 'we', null, 'dgd', '2016-08-18 00:00:00', 'wewe', '超级管理员', 'wewewe', '3', '1');
INSERT INTO `t_record` VALUES ('74', 'we', null, 'dgd', '2016-08-18 00:00:00', 'wwe', '超级管理员', 'wewe', '1', '1');
INSERT INTO `t_record` VALUES ('75', 'A00000000012', null, '案件12', '2016-08-30 22:23:41', '王建观', '超级管理员', '', '3', '3');
INSERT INTO `t_record` VALUES ('76', 'A00000000012', null, '案件12', '2016-08-30 22:23:52', '王建观', '超级管理员', '', '1', '3');
INSERT INTO `t_record` VALUES ('77', 'A00000000012', null, '案件12', '2016-08-30 22:24:05', '王建观', '超级管理员', '是的范德萨', '3', '3');
INSERT INTO `t_record` VALUES ('78', 'qwr', null, 'ret', '2016-12-06 15:21:09', '王建观', '超级管理员', '神神道道所', '3', '1');
INSERT INTO `t_record` VALUES ('79', 'sdssd', null, '灭火器1', '2016-12-06 15:21:21', '王建观s', '超级管理员', '多少岁', '3', '1');
INSERT INTO `t_record` VALUES ('80', '444441', null, '232323f12', '2016-12-06 15:36:40', 'xwl', '肖伟丽', 'sdsddsdsdsdssdddddsssddfdfdsdsdsdasda', '3', '1');
INSERT INTO `t_record` VALUES ('81', 'asf', null, 'ew', '2016-12-06 16:18:06', 'null|石全', 'admin|超级管理员', 'sddsdssssssdsddssd', '3', '1');
INSERT INTO `t_record` VALUES ('82', 'qwr', null, 'ret', '2016-12-06 16:22:44', 'gaozhenbo|高振博', 'admin|超级管理员', 'sdsdd', '1', '1');
INSERT INTO `t_record` VALUES ('83', 'qwr', null, 'ret', '2016-12-06 16:24:01', 'wjg|王建观', 'admin|超级管理员', 'sdsdds', '3', '1');
INSERT INTO `t_record` VALUES ('84', 'XG43823', null, '消防水枪', '2016-12-06 16:59:18', 'wjg|王建观', 'admin|超级管理员', '112212121', '1', '2');
INSERT INTO `t_record` VALUES ('85', '111', null, '1', '2016-12-06 16:59:43', 'tzc|唐自成', 'admin|超级管理员', '', '3', '2');
INSERT INTO `t_record` VALUES ('86', 'A00000000027', null, 'A00000000027', null, 'null|', 'admin|超级管理员', '', '3', '3');
INSERT INTO `t_record` VALUES ('87', 'A00000000020', null, '案件20', '2016-12-06 17:28:55', 'wangjianguan;大神观', 'admin|超级管理员', 'A00000000020A00000000020A00000000020A00000000020A00000000020', '3', '3');
INSERT INTO `t_record` VALUES ('88', 'eerre', null, 'ererer', '2016-12-06 18:08:15', 'wangjianguan;大神观', 'admin|超级管理员', '', '3', '4');
INSERT INTO `t_record` VALUES ('89', 'XG43823', null, '消防水枪', '2016-12-06 18:27:31', 'wangjianguan;大神观', 'admin;超级管理员', '是的说到底是', '3', '2');
INSERT INTO `t_record` VALUES ('90', 'A00000000019', null, '案件19', '2016-12-06 18:29:07', 'xwl;肖伟丽', 'admin;超级管理员', '但是是的多所', '3', '3');

-- ----------------------------
-- Table structure for t_repair
-- ----------------------------
DROP TABLE IF EXISTS `t_repair`;
CREATE TABLE `t_repair` (
  `repair_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `repair_name` varchar(50) DEFAULT NULL COMMENT '维修人/单位',
  `tel` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `repair_explain` text COMMENT '维修说明',
  `repair_time` datetime DEFAULT NULL COMMENT '维修时间',
  `repair_status` varchar(5) DEFAULT NULL COMMENT '维修结果状态（5-维修中\\1-已维修\\4-已报废）',
  `assets_equipment_name` varchar(255) DEFAULT NULL COMMENT '装备资产名称',
  `assets_equipment_id` int(11) DEFAULT NULL COMMENT '资产|装备id',
  `style` varchar(255) DEFAULT NULL COMMENT '类型（1-资产、2-装备）',
  `assets_equipment_no` varchar(30) DEFAULT NULL COMMENT '装备资产编号',
  PRIMARY KEY (`repair_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_repair
-- ----------------------------
INSERT INTO `t_repair` VALUES ('24', '13', '23', '2332', '2016-07-05 00:00:00', '1', 'mac pro 13', '19', '1', 'pro');
INSERT INTO `t_repair` VALUES ('26', '1', '15381002812', '11', '2016-06-27 00:00:00', '1', 'mac pro 13', '19', '1', 'pro');
INSERT INTO `t_repair` VALUES ('27', '23', '15381002812', '23323233223233w23323233223233w23323233223233w23323233223233w23323233223233w23323233223233w23323233223233w23323233223233w23323233223233w23323233223233w23323233223233w23323233223233w23323233223233w23323233223233w23323233223233w23323233223233w23323233223233w', '2016-07-11 00:00:00', '4', '3232', '13', '2', '2323');
INSERT INTO `t_repair` VALUES ('28', 'wewewe', 'wewewe', 'weweewew', '2016-08-18 11:19:00', '1', 'wewe', '39', '1', 'weew');
INSERT INTO `t_repair` VALUES ('29', 'weew', 'weew', 'weewew', '2016-08-18 11:18:56', '5', '桌子', '23', '1', 'ASD');
INSERT INTO `t_repair` VALUES ('30', '3232323', '15381002812', 'wewewewertbrbgrtbrt', '2016-08-18 11:19:40', '5', 'dsf', '31', '1', 'saf');
INSERT INTO `t_repair` VALUES ('31', '咪咕数媒', '15381002812', '  如果一个活动的事务存在,则运行在一个嵌套的事务中.如果没有活动的事务,则按REQUIRED属性执行.它使用了一个单独的事务, 这个事务拥有多个可以回滚的保证点.内部事务回滚不会对外部事务造成影响, 它只对DataSourceTransactionManager 事务管理器起效.\n     其实大家最感到困惑的是REQUIRED_NEW和NESTED两种不同的传播机制，功能类似，都涉及到了事务嵌套的问题，那两者有何区别呢？该如何正确使用这两种模式呢？  如果一个活动的事务存在,则运行在一个嵌套的事务中.如果没有活动的事务,则按REQUIRED属性执行.它使用了一个单独的事务, 这个事务拥有多个可以回滚的保证点.内部事务回滚不会对外部事务造成影响, 它只对DataSourceTransactionManager 事务管理器起效.\n     其实大家最感到困惑的是REQUIRED_NEW和NESTED两种不同的传播机制，功能类似，都涉及到了事务嵌套的问题，那两者有何区别呢？该如何正确使用这两种模式呢？', '2016-12-06 14:50:54', '5', 'dgd', '13', '1', 'we');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` varchar(10) NOT NULL COMMENT '角色id',
  `role_name` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `left_json` text COMMENT '左边的json',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '民警', null);
INSERT INTO `t_role` VALUES ('2', '管理员', null);
INSERT INTO `t_role` VALUES ('3', '所内领导', null);

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `role_id` varchar(255) NOT NULL,
  `permission_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_suspect
-- ----------------------------
DROP TABLE IF EXISTS `t_suspect`;
CREATE TABLE `t_suspect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `suspect_name` varchar(255) DEFAULT NULL COMMENT '嫌疑人名称',
  `identity` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  `mobile_no` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `remarks` text COMMENT '备注',
  `case_no` varchar(255) DEFAULT NULL COMMENT '案件编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_suspect
-- ----------------------------
INSERT INTO `t_suspect` VALUES ('1', '王建观', '341021199401198057', '15381002812', '222222222222', 'JKLOIUJ');
INSERT INTO `t_suspect` VALUES ('2', '肖伟丽', '341021199302209769', '15381002801', '222222222222', 'JKLOIUJ');
INSERT INTO `t_suspect` VALUES ('3', '王建观', '341021199401198057', '15381002812', '222222222222', 'JKLOIUJ');
INSERT INTO `t_suspect` VALUES ('4', '肖伟丽', '341021199302209769', '15381002801', '222222222222', 'JKLOIUJ');
INSERT INTO `t_suspect` VALUES ('5', '王建观', '341021199401198057', '15381002812', '222222222222', 'JKLOIUJ');
INSERT INTO `t_suspect` VALUES ('26', '王建观', '2323', '323232', '3232233', '21212323');
INSERT INTO `t_suspect` VALUES ('27', '肖伟丽', '3232', '3232', '3232', '21212323');
INSERT INTO `t_suspect` VALUES ('28', '22', '', '', '', 'A125353625');
INSERT INTO `t_suspect` VALUES ('29', '', '', '222', '', 'A125353625');
INSERT INTO `t_suspect` VALUES ('30', '', '222', '', '', 'A125353625');
INSERT INTO `t_suspect` VALUES ('31', '', '', '', '222', 'A125353625');
INSERT INTO `t_suspect` VALUES ('32', '', '222', '', '', 'A125353625');
INSERT INTO `t_suspect` VALUES ('33', '1221', '22121', '2121', '2121', '2121');
INSERT INTO `t_suspect` VALUES ('34', 'ewwe', 'wewe', 'wwe', 'wewe', '2121');
INSERT INTO `t_suspect` VALUES ('35', '王建观', '', '15381002812', '', 'A00000000023');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `password` varchar(60) DEFAULT NULL COMMENT '密码',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `count` int(11) DEFAULT NULL COMMENT '登录次数',
  `createAt` datetime DEFAULT NULL COMMENT '创建时间',
  `islock` varchar(2) DEFAULT NULL COMMENT '是否锁定（1-锁定2-解锁）',
  `role_id` varchar(255) DEFAULT NULL COMMENT '角色id 1民警 2管理员 3所内领导',
  `permission_id` varchar(255) DEFAULT NULL COMMENT '菜单json（多个用，隔开）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'A66ABB5684C45962D887564F08346E8D', '超级管理员', '15381002812', '864', '2016-04-30 00:00:00', '2', '2', null);
INSERT INTO `t_user` VALUES ('7', 'wjg', 'C0F4C78144E63B5B8322AD3F4159EC65', '王建观', '15381002812', '34', '2016-05-08 01:01:34', '2', '1', null);
INSERT INTO `t_user` VALUES ('8', 'wangjianguan', '650A7BD71079352F0B5CAE5704DCEA85', '大神观', '15381002812', '6', '2016-05-14 10:19:07', '2', '2', null);
INSERT INTO `t_user` VALUES ('10', 'gaozhenbo', '4119F19BF3E1D1D743D96F4B790AA5D5', '高振博', '1366666666', '19', '2016-07-08 14:48:38', '2', '1', '');
INSERT INTO `t_user` VALUES ('11', 'xwl', 'C983613283E98560E722AFB061812CE2', '肖伟丽', '15381002801', '14', '2016-07-11 00:32:52', '2', '2', '');
INSERT INTO `t_user` VALUES ('12', 'shiquan', '30618643C669B3C684F9C7C37A459505', '石全', '15381002812', '1', '2016-07-11 13:54:56', '2', '1', null);
INSERT INTO `t_user` VALUES ('13', 'admin11', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('14', 'tzc', 'BD3EB135D5DCE8D18E1E813ED472157D', '唐自成', '15381002812', '15', '2016-08-06 14:42:45', '2', '3', null);
INSERT INTO `t_user` VALUES ('15', 'wjg11', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('16', 'wjg12', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('17', 'wjg13', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('18', 'wjg14', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('19', 'wjg15', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('20', 'wjg16', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('21', 'wjg17', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('22', 'wjg18', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('23', 'wjg19', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('24', 'wjg10', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('25', 'wjg21', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('26', 'wjg22', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '3', null);
INSERT INTO `t_user` VALUES ('27', 'wjg111', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('28', 'wjg112', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('29', 'wjg113', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('30', 'wjg114', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('31', 'wjg115', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('32', 'wjg116', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('34', 'wjg118', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('35', 'wjg119', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('36', 'wjg110', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('37', 'wjg1100', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('38', 'wjg1111', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('39', 'wjg1111', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('40', 'wjg11122', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('41', 'wjg222', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('42', 'wjg221', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('43', 'wjg2222', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('44', 'wjg334', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('45', 'wjg444', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('46', 'wjg14444', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('47', 'wjg12121211', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('48', 'wjg121211', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('49', 'wjg12211', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('50', 'wjg1211', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('51', 'wjg123321', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('52', 'wjg5611', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('53', 'wjg4311', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('54', 'wjg165651', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('55', 'wjg17781', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('56', 'wjg566511', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('57', 'wjg343411', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('58', 'wjg176761', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
INSERT INTO `t_user` VALUES ('59', 'wjg181', '5C7F48250EDBAB29F05B2F8B6044039F', '11', '111', '0', '2016-08-04 23:10:37', '2', '1', null);
