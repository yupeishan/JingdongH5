/*
 Navicat MySQL Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : wechat_shop

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 01/12/2022 22:47:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `user_id` int NULL DEFAULT 0 COMMENT '用户ID',
  `consignee_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人',
  `tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人电话',
  `area` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省市区',
  `detailed_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详细地址',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0普通地址 1默认地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (14, 11, '张三', '12312341234', '浙江省杭州市滨江区', '幸福小区1幢1号', 1, '2022-11-12 13:16:58', '2022-11-12 13:17:13');
INSERT INTO `address` VALUES (77, 26, '123', '12312341234', '浙江省杭州市滨江区', '123', 0, '2022-11-19 20:03:29', '2022-11-19 20:03:29');
INSERT INTO `address` VALUES (83, 10, '于沛汕', '12312341234', '浙江省杭州市上城区', '浦沿街道大通公寓2幢1103', 1, '2022-11-21 18:21:26', '2022-11-30 13:29:04');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `detail_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单详情编号',
  `order_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属订单号',
  `product_id` int NOT NULL COMMENT '商品ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `price` decimal(8, 2) NOT NULL COMMENT '购买价格，当前价格,单位分',
  `quantity` int NOT NULL COMMENT '购买数量',
  `img_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `order_no`(`order_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1664009422750881116', '1664009422734950213', 1, '苹果', 10.00, 2, 'https://images.gaoshiyi.top/static/apple.png', '2022-09-24 16:50:23', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1664009422906578398', '1664009422734950213', 2, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-09-24 16:50:23', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1664012652391200598', '1664012652391149570', 3, '薯片', 10.00, 5, 'https://images.gaoshiyi.top/static/chip.png', '2022-09-24 17:44:12', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1664012652438317216', '1664012652391149570', 5, '黄瓜', 3.00, 10, 'https://images.gaoshiyi.top/static/cucumber.png', '2022-09-24 17:44:12', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1664185240514935531', '1664185240510339965', 1, '苹果', 10.00, 1, 'https://images.gaoshiyi.top/static/apple.png', '2022-09-26 17:40:40', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1664185240546729711', '1664185240510339965', 2, '西瓜', 30.00, 2, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-09-26 17:40:40', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1664189197456410370', '1664189197455188399', 6, '西红柿', 5.00, 1, 'https://images.gaoshiyi.top/static/tomato.png', '2022-09-26 18:46:37', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1664189197461536227', '1664189197455188399', 7, '猪肉', 30.00, 2, 'https://images.gaoshiyi.top/static/pork.png', '2022-09-26 18:46:37', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665140407440871719', '1665140407438427615', 7, '猪肉', 30.00, 10, 'https://images.gaoshiyi.top/static/pork.png', '2022-10-07 19:00:07', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665140407493402029', '1665140407438427615', 8, '羊肉', 60.00, 5, 'https://images.gaoshiyi.top/static/mutton.png', '2022-10-07 19:00:07', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665140480750198790', '1665140480750173216', 7, '猪肉', 30.00, 10, 'https://images.gaoshiyi.top/static/pork.png', '2022-10-07 19:01:20', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665140480766312683', '1665140480750173216', 8, '羊肉', 60.00, 5, 'https://images.gaoshiyi.top/static/mutton.png', '2022-10-07 19:01:20', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665217588250548810', '1665217588247142709', 1, '苹果', 10.00, 20, 'https://images.gaoshiyi.top/static/apple.png', '2022-10-08 16:26:28', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665217588439936750', '1665217588247142709', 2, '西瓜', 30.00, 50, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-10-08 16:26:28', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665217651737538827', '1665217651737643725', 1, '苹果', 10.00, 20, 'https://images.gaoshiyi.top/static/apple.png', '2022-10-08 16:27:31', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665217651737889748', '1665217651737643725', 2, '西瓜', 30.00, 50, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-10-08 16:27:31', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665217686338532891', '1665217686335738327', 1, '苹果', 10.00, 20, 'https://images.gaoshiyi.top/static/apple.png', '2022-10-08 16:28:06', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665217686342698401', '1665217686335738327', 2, '西瓜', 30.00, 50, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-10-08 16:28:06', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665217711260775089', '1665217711258818696', 1, '苹果', 10.00, 20, 'https://images.gaoshiyi.top/static/apple.png', '2022-10-08 16:28:31', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665217711264544238', '1665217711258818696', 2, '西瓜', 30.00, 50, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-10-08 16:28:31', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665217728504487066', '1665217728502394641', 83, '薯片', 10.00, 10, 'https://images.gaoshiyi.top/static/chip.png', '2022-10-08 16:28:48', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665217728509630226', '1665217728502394641', 147, '香蕉', 15.00, 5, 'https://images.gaoshiyi.top/static/banana.png', '2022-10-08 16:28:48', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665217754598403043', '1665217754595261267', 5, '黄瓜', 3.00, 20, 'https://images.gaoshiyi.top/static/cucumber.png', '2022-10-08 16:29:14', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665217754601585445', '1665217754595261267', 7, '猪肉', 30.00, 8, 'https://images.gaoshiyi.top/static/pork.png', '2022-10-08 16:29:14', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665480327706725356', '1665480327704140440', 1, '苹果', 10.00, 8, 'https://images.gaoshiyi.top/static/apple.png', '2022-10-11 17:25:27', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1665480327767696961', '1665480327704140440', 5, '黄瓜', 3.00, 3, 'https://images.gaoshiyi.top/static/cucumber.png', '2022-10-11 17:25:27', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668001339603222681', '1668001339602538995', 1, '苹果', 10.00, 8, 'https://images.gaoshiyi.top/static/apple.png', '2022-11-09 21:42:19', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668001339678222371', '1668001339602538995', 5, '黄瓜', 3.00, 3, 'https://images.gaoshiyi.top/static/cucumber.png', '2022-11-09 21:42:19', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668505667260821212', '1668505667197296511', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-15 17:47:48', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668507798472693559', '1668507798472641376', 26, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-15 18:23:18', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668514657487753765', '1668514657486870240', 1, '苹果', 10.00, 1, 'https://images.gaoshiyi.top/static/apple.png', '2022-11-15 20:17:37', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668514657546506523', '1668514657486870240', 2, '香蕉', 30.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-15 20:17:37', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668514657548749788', '1668514657486870240', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-15 20:17:37', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668514657550951502', '1668514657486870240', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-15 20:17:37', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668514657551458659', '1668514657486870240', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-15 20:17:37', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668514746681793967', '1668514746680880566', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-15 20:19:06', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668514746684222640', '1668514746680880566', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-15 20:19:06', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668514845722828793', '1668514845721860968', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-15 20:20:45', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668514845726848605', '1668514845721860968', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-15 20:20:45', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668514883842468683', '1668514883841627268', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-15 20:21:23', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668514883843290680', '1668514883841627268', 99, '香蕉', 2.00, 3, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-15 20:21:23', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668515364415654099', '1668515364415936465', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-15 20:29:24', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668515369019738172', '1668515369019506334', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-15 20:29:29', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668515377874535008', '1668515377874484219', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-15 20:29:37', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668515648435555793', '1668515648434623493', 3, '薯片', 10.00, 1, 'https://images.gaoshiyi.top/static/chip.png', '2022-11-15 20:34:08', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668516264938992940', '1668516264938885115', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-15 20:44:24', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668517254461792612', '1668517254459809581', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-15 21:00:54', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668517601733938057', '1668517601732751923', 9, '苹果', 10.00, 1, 'https://images.gaoshiyi.top/static/apple.png', '2022-11-15 21:06:41', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668517627313584692', '1668517627312469018', 21, '黄瓜', 3.00, 1, 'https://images.gaoshiyi.top/static/cucumber.png', '2022-11-15 21:07:07', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668517722015875063', '1668517722014643915', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-15 21:08:42', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668517875060289847', '1668517875059741408', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-15 21:11:15', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668517875061103920', '1668517875059741408', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-15 21:11:15', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668517933003393184', '1668517933003943026', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-15 21:12:13', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668518098633961417', '1668518098632293106', 104, '商品测试4', 0.10, 1, 'https://images.gaoshiyi.top/static/hamburger.jpg', '2022-11-15 21:14:58', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668518185174285168', '1668518185174397095', 9, '苹果', 10.00, 1, 'https://images.gaoshiyi.top/static/apple.png', '2022-11-15 21:16:25', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668519880674594363', '1668519880673731351', 2, '香蕉', 30.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-15 21:44:40', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668519880769937219', '1668519880673731351', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-15 21:44:40', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668519880770830114', '1668519880673731351', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-15 21:44:40', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668519880772867463', '1668519880673731351', 99, '香蕉', 2.00, 2, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-15 21:44:40', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668580379244890667', '1668580379243155094', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 14:32:59', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668595703849239674', '1668595703549943264', 2, '香蕉', 30.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 18:48:24', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668595704289477446', '1668595703549943264', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-16 18:48:25', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668595704289570952', '1668595703549943264', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-16 18:48:25', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668595704699851047', '1668595703549943264', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 18:48:25', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668596047777437304', '1668596047776537900', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 18:54:07', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668596137850876877', '1668596137849478829', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-16 18:55:37', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668600075995208740', '1668600075995639273', 1, '苹果', 10.00, 1, 'https://images.gaoshiyi.top/static/apple.png', '2022-11-16 20:01:16', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668600159000168568', '1668600159000198519', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-16 20:02:39', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668600180716944109', '1668600180715214548', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 20:03:00', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668601267158521117', '1668601267127383346', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-16 20:21:07', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668602418286861541', '1668602418284450391', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-16 20:40:18', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668602658369965416', '1668602658358834263', 10, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 20:44:18', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668604271165565428', '1668604271165514274', 18, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 21:11:11', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668604518896233879', '1668604518872324628', 18, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 21:15:18', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668604563980686528', '1668604563979536160', 26, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 21:16:04', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668604590003814384', '1668604590003342448', 34, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 21:16:30', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668604919636114345', '1668604919636628162', 34, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 21:21:59', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668604967561379849', '1668604967540202872', 42, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 21:22:47', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668605233698254676', '1668605233657736154', 50, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 21:27:13', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668605311777742178', '1668605311776813862', 58, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 21:28:31', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668605598408516179', '1668605598345891642', 58, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 21:33:18', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668605609472966199', '1668605609471644930', 66, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 21:33:29', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668605633380353848', '1668605633380765479', 66, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 21:33:53', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668605647930318954', '1668605647930523419', 74, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 21:34:07', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668605706471250283', '1668605706471655497', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 21:35:06', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668605831059555653', '1668605831059484579', 10, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 21:37:11', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668605870679990559', '1668605870679368368', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 21:37:50', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668607645741689606', '1668607645718303924', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-16 22:07:25', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668607737773691567', '1668607737773901567', 2, '香蕉', 30.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 22:08:57', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668607879218539584', '1668607879216106614', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-16 22:11:19', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668607879219697176', '1668607879216106614', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 22:11:19', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668607928343355025', '1668607928343401209', 2, '香蕉', 30.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 22:12:08', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668607928382987551', '1668607928343401209', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-16 22:12:08', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668607928408781534', '1668607928343401209', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-16 22:12:08', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668607928408788811', '1668607928343401209', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 22:12:08', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668607939001834480', '1668607939001296276', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-16 22:12:19', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668607939001844022', '1668607939001296276', 2, '香蕉', 30.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 22:12:19', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668607939012899883', '1668607939001296276', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-16 22:12:19', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668607939070547145', '1668607939001296276', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 22:12:19', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668609225656872438', '1668609225656234071', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 22:33:45', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668609443169899185', '1668609443168754887', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-16 22:37:23', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668609443170727193', '1668609443168754887', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-16 22:37:23', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668609443179992901', '1668609443168754887', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 22:37:23', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668609656602580195', '1668609656602241557', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-16 22:40:56', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668609656604920425', '1668609656602241557', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 22:40:56', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668609689865392774', '1668609689864799747', 10, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-16 22:41:29', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668609864907359731', '1668609864906677786', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 22:44:24', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668609886621698451', '1668609886601946386', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-16 22:44:46', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668667846901455022', '1668667846838640577', 1, '苹果', 10.00, 1, 'https://images.gaoshiyi.top/static/apple.png', '2022-11-17 14:50:47', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668667847089659134', '1668667846838640577', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-17 14:50:47', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668667847089695065', '1668667846838640577', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-17 14:50:47', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668667847089742832', '1668667846838640577', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-17 14:50:47', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668667847089857640', '1668667846838640577', 2, '香蕉', 30.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-17 14:50:47', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668667947915949155', '1668667947914676476', 10, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-17 14:52:27', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668667978367500090', '1668667978366864511', 18, '西瓜', 30.00, 1, 'https://images.gaoshiyi.top/static/watermelon.png', '2022-11-17 14:52:58', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668668039110615435', '1668668039109991072', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-17 14:53:59', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668766494774374340', '1668766494774319503', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-18 18:14:54', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668859411778293297', '1668859411747180426', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-19 20:03:32', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668859586861898507', '1668859586861460051', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-19 20:06:26', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668859785476646023', '1668859785476246541', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-19 20:09:45', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668861475025461405', '1668861475025912341', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-19 20:37:55', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668861494955125276', '1668861494954732511', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-19 20:38:14', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668861524350280890', '1668861524350988855', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-19 20:38:44', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668862178698662167', '1668862178698179022', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-19 20:49:38', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668862249733945692', '1668862249733548760', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-19 20:50:49', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668865375675423321', '1668865375675567901', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-19 21:42:55', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1668923131643653658', '1668923131642194852', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-20 13:45:31', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1669025946591864981', '1669025946560605253', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-21 18:19:06', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1669036241609656830', '1669036241609149273', 99, '香蕉', 2.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-21 21:10:41', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1669036385055850772', '1669036385039253843', 99, '香蕉', 20.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-21 21:13:05', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1669036445801728154', '1669036445801964121', 1, '苹果', 10.00, 1, 'https://images.gaoshiyi.top/static/apple.png', '2022-11-21 21:14:05', '2022-11-22 20:21:45');
INSERT INTO `order_detail` VALUES ('1669444673789928730', '1669444673788508756', 99, '香蕉', 20.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-26 14:37:53', '2022-11-26 14:37:53');
INSERT INTO `order_detail` VALUES ('1669444693262188167', '1669444693262941055', 99, '香蕉', 20.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-26 14:38:13', '2022-11-26 14:38:13');
INSERT INTO `order_detail` VALUES ('1669444739435533765', '1669444739434972775', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-26 14:38:59', '2022-11-26 14:38:59');
INSERT INTO `order_detail` VALUES ('1669444854443744541', '1669444854442666021', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-26 14:40:54', '2022-11-26 14:40:54');
INSERT INTO `order_detail` VALUES ('1669444854444292184', '1669444854442666021', 99, '香蕉', 20.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-26 14:40:54', '2022-11-26 14:40:54');
INSERT INTO `order_detail` VALUES ('1669445636281112994', '1669445636281718811', 5, '黄瓜', 3.00, 1, 'https://images.gaoshiyi.top/static/cucumber.png', '2022-11-26 14:53:56', '2022-11-26 14:53:56');
INSERT INTO `order_detail` VALUES ('1669445636281875814', '1669445636281718811', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-26 14:53:56', '2022-11-26 14:53:56');
INSERT INTO `order_detail` VALUES ('1669445654463772346', '1669445654463980610', 98, '芒果', 20.00, 1, 'https://images.gaoshiyi.top/static/mango.png', '2022-11-26 14:54:14', '2022-11-26 14:54:14');
INSERT INTO `order_detail` VALUES ('1669445720665296035', '1669445720650187379', 99, '香蕉', 20.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-26 14:55:20', '2022-11-26 14:55:20');
INSERT INTO `order_detail` VALUES ('1669784995470830944', '1669784995468136550', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-30 13:09:56', '2022-11-30 13:09:56');
INSERT INTO `order_detail` VALUES ('1669785279797712401', '1669785279796796497', 99, '香蕉', 20.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-30 13:14:39', '2022-11-30 13:14:39');
INSERT INTO `order_detail` VALUES ('1669785312666763003', '1669785312665246755', 3, '薯片', 10.00, 1, 'https://images.gaoshiyi.top/static/chip.png', '2022-11-30 13:15:12', '2022-11-30 13:15:12');
INSERT INTO `order_detail` VALUES ('1669785487421515505', '1669785487421248325', 99, '香蕉', 20.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-30 13:18:07', '2022-11-30 13:18:07');
INSERT INTO `order_detail` VALUES ('1669785501361803656', '1669785501361760751', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-11-30 13:18:21', '2022-11-30 13:18:21');
INSERT INTO `order_detail` VALUES ('1669785549199383630', '1669785549199395657', 99, '香蕉', 20.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-30 13:19:09', '2022-11-30 13:19:09');
INSERT INTO `order_detail` VALUES ('1669785936478697020', '1669785936477203379', 99, '香蕉', 20.00, 2, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-30 13:25:36', '2022-11-30 13:25:36');
INSERT INTO `order_detail` VALUES ('1669787839179169471', '1669787839178304505', 99, '香蕉', 20.00, 1, 'https://images.gaoshiyi.top/static/banana.png', '2022-11-30 13:57:19', '2022-11-30 13:57:19');
INSERT INTO `order_detail` VALUES ('1669901822305539674', '1669901822303238073', 97, '草莓', 40.00, 1, 'https://images.gaoshiyi.top/static/strawberry.png', '2022-12-01 21:37:02', '2022-12-01 21:37:02');

-- ----------------------------
-- Table structure for order_table
-- ----------------------------
DROP TABLE IF EXISTS `order_table`;
CREATE TABLE `order_table`  (
  `order_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单号',
  `transaction_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信支付流水号',
  `user_id` int UNSIGNED NOT NULL COMMENT '用户id',
  `consignee_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名字',
  `tel` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户电话',
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户地址',
  `courier_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '快递单号',
  `user_open_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户微信openid',
  `shop_id` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '商铺id',
  `shop_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商铺名称',
  `order_amount` decimal(8, 2) NOT NULL COMMENT '订单总金额',
  `pay_state` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0等待支付 1支付成功',
  `order_state` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0未支付 1已支付  2已取消 3未发货  4已发货  ',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_number`) USING BTREE,
  INDEX `user_openid`(`user_open_id`) USING BTREE,
  INDEX `order_no`(`order_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_table
-- ----------------------------
INSERT INTO `order_table` VALUES ('1664009422734950213', '4200001578202210042327717915', 10, '张三', '18212341234', '浙江省杭州市滨江区', '123123123', 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 50.00, 1, 4, '2022-10-04 18:06:43', '2022-09-24 16:50:23', '2022-11-13 17:59:10');
INSERT INTO `order_table` VALUES ('1664012652391149570', NULL, 10, '张三', '18212341234', '浙江省杭州市滨江区', '123123123123', 'oroeFwonWZ57gWZQEtqY0o_yttmU', 2, '测试店铺2', 80.00, 1, 4, '2022-09-28 18:51:54', '2022-09-24 17:44:12', '2022-11-13 17:59:13');
INSERT INTO `order_table` VALUES ('1664185240510339965', NULL, 10, '李四', '15612341234', '浙江省杭州市滨江区', '123123123123', '123456789', 3, '测试店铺3', 70.00, 1, 2, '2022-09-27 11:16:10', '2022-09-26 17:40:40', '2022-11-13 17:57:50');
INSERT INTO `order_table` VALUES ('1664189197455188399', NULL, 10, '李四', '15612341234', '浙江省杭州市滨江区', '123456', '987654321', 4, '测试店铺4', 65.00, 1, 4, '2022-09-27 11:16:10', '2022-09-26 18:46:37', '2022-11-13 17:57:50');
INSERT INTO `order_table` VALUES ('1665140407438427615', NULL, 10, '李四', '15612341234', '浙江省杭州市滨江区', NULL, '987654321', 5, '测试店铺5', 600.00, 0, 0, NULL, '2022-10-07 19:00:07', '2022-11-13 17:57:52');
INSERT INTO `order_table` VALUES ('1665140480750173216', NULL, 10, '王五', '13712341234', '浙江省杭州市上城区', NULL, '123456789', 6, '测试店铺6', 600.00, 0, 0, NULL, '2022-10-07 19:01:20', '2022-11-13 17:57:52');
INSERT INTO `order_table` VALUES ('1665217588247142709', NULL, 10, '王五', '13712341234', '浙江省杭州市上城区', NULL, '123456789', 7, '测试店铺7', 1700.00, 0, 0, NULL, '2022-10-08 16:26:28', '2022-11-13 17:57:53');
INSERT INTO `order_table` VALUES ('1665217651737643725', NULL, 10, '王五', '13712341234', '浙江省杭州市上城区', NULL, '987654321', 8, '测试店铺8', 1700.00, 0, 0, NULL, '2022-10-08 16:27:31', '2022-11-13 17:57:54');
INSERT INTO `order_table` VALUES ('1665217686335738327', NULL, 10, '王五', '13712341234', '浙江省杭州市上城区', NULL, '1111122222', 9, '测试店铺9', 1700.00, 0, 0, NULL, '2022-10-08 16:28:06', '2022-11-14 17:03:09');
INSERT INTO `order_table` VALUES ('1665217711258818696', NULL, 10, '王五', '13712341234', '浙江省杭州市上城区', NULL, 'aaaaabbbbb', 10, '测试店铺10', 1700.00, 0, 0, NULL, '2022-10-08 16:28:31', '2022-11-13 17:57:57');
INSERT INTO `order_table` VALUES ('1665217728502394641', NULL, 10, '王五', '13712341234', '浙江省杭州市上城区', NULL, 'aaaaabbbbb', 11, '测试店铺11', 175.00, 0, 1, NULL, '2022-10-08 16:28:48', '2022-11-14 20:24:36');
INSERT INTO `order_table` VALUES ('1665217754595261267', NULL, 10, '王五', '13712341234', '浙江省杭州市上城区', '1111100000', '1111122222', 12, '测试店铺12', 300.00, 1, 4, NULL, '2022-10-08 16:29:14', '2022-11-13 17:58:00');
INSERT INTO `order_table` VALUES ('1665480327704140440', '4200001656202211145794297690', 10, '王五', '15612341234', '浙江省杭州市滨江区', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 13, '测试店铺13', 0.01, 1, 3, '2022-11-14 21:42:15', '2022-10-11 17:25:27', '2022-11-14 21:42:15');
INSERT INTO `order_table` VALUES ('1668001339602538995', '123465789', 10, '王五', '15612341234', '浙江省杭州市滨江区', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 14, '测试店铺14', 89.00, 1, 2, '2022-11-15 15:19:59', '2022-11-09 21:42:19', '2022-11-15 16:07:45');
INSERT INTO `order_table` VALUES ('1668505667197296511', '123465789', 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 2.00, 1, 2, '2022-11-15 17:48:23', '2022-11-15 17:47:48', '2022-11-15 17:48:27');
INSERT INTO `order_table` VALUES ('1668507798472641376', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 4, '测试店铺4', 30.00, 0, 0, NULL, '2022-11-15 18:23:18', '2022-11-15 18:23:18');
INSERT INTO `order_table` VALUES ('1668514657486870240', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 102.00, 0, 0, NULL, '2022-11-15 20:17:37', '2022-11-15 20:17:37');
INSERT INTO `order_table` VALUES ('1668514746680880566', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 22.00, 0, 0, NULL, '2022-11-15 20:19:06', '2022-11-15 20:19:06');
INSERT INTO `order_table` VALUES ('1668514845721860968', '123465789', 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 22.00, 1, 2, '2022-11-15 20:22:01', '2022-11-15 20:20:45', '2022-11-15 20:22:05');
INSERT INTO `order_table` VALUES ('1668514883841627268', NULL, 10, '富贵园', '11111111111', '天津市天津市河西区刚刚好', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 26.00, 0, 1, NULL, '2022-11-15 20:21:23', '2022-11-15 20:21:58');
INSERT INTO `order_table` VALUES ('1668515364415936465', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 2.00, 0, 0, NULL, '2022-11-15 20:29:24', '2022-11-15 20:29:24');
INSERT INTO `order_table` VALUES ('1668515369019506334', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 20.00, 0, 0, NULL, '2022-11-15 20:29:29', '2022-11-15 20:29:29');
INSERT INTO `order_table` VALUES ('1668515377874484219', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 20.00, 0, 0, NULL, '2022-11-15 20:29:37', '2022-11-15 20:29:37');
INSERT INTO `order_table` VALUES ('1668515648434623493', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 10.00, 0, 0, NULL, '2022-11-15 20:34:08', '2022-11-15 20:34:08');
INSERT INTO `order_table` VALUES ('1668516264938885115', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 40.00, 0, 0, NULL, '2022-11-15 20:44:25', '2022-11-15 20:44:25');
INSERT INTO `order_table` VALUES ('1668517254459809581', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 2.00, 0, 0, NULL, '2022-11-15 21:00:54', '2022-11-15 21:00:54');
INSERT INTO `order_table` VALUES ('1668517601732751923', '123465789', 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 2, '测试店铺2', 10.00, 1, 3, '2022-11-15 21:06:46', '2022-11-15 21:06:41', '2022-11-15 21:06:46');
INSERT INTO `order_table` VALUES ('1668517627312469018', '123465789', 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 3, '测试店铺3', 3.00, 1, 3, '2022-11-15 21:08:36', '2022-11-15 21:07:07', '2022-11-15 21:08:36');
INSERT INTO `order_table` VALUES ('1668517722014643915', '123465789', 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 40.00, 1, 3, '2022-11-15 21:09:01', '2022-11-15 21:08:42', '2022-11-15 21:09:01');
INSERT INTO `order_table` VALUES ('1668517875059741408', NULL, 10, '111', '11111111111', '北京市北京市东城区111', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 60.00, 0, 0, NULL, '2022-11-15 21:11:15', '2022-11-15 21:11:15');
INSERT INTO `order_table` VALUES ('1668517933003943026', NULL, 10, '111', '11111111111', '北京市北京市东城区111', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 20.00, 0, 0, NULL, '2022-11-15 21:12:13', '2022-11-15 21:12:13');
INSERT INTO `order_table` VALUES ('1668518098632293106', NULL, 10, '111', '11111111111', '北京市北京市东城区111', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 0.10, 0, 0, NULL, '2022-11-15 21:14:58', '2022-11-15 21:14:58');
INSERT INTO `order_table` VALUES ('1668518185174397095', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 2, '测试店铺2', 10.00, 0, 0, NULL, '2022-11-15 21:16:25', '2022-11-15 21:16:25');
INSERT INTO `order_table` VALUES ('1668519880673731351', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 94.00, 0, 0, NULL, '2022-11-15 21:44:40', '2022-11-15 21:44:40');
INSERT INTO `order_table` VALUES ('1668580379243155094', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 2.00, 0, 0, NULL, '2022-11-16 14:32:59', '2022-11-16 14:32:59');
INSERT INTO `order_table` VALUES ('1668595703549943264', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 92.00, 0, 0, NULL, '2022-11-16 18:48:25', '2022-11-16 18:48:25');
INSERT INTO `order_table` VALUES ('1668596047776537900', '123465789', 10, '123', '11111111111', '北京市北京市东城区123', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 2.00, 1, 3, '2022-11-16 18:55:20', '2022-11-16 18:54:07', '2022-11-16 18:55:20');
INSERT INTO `order_table` VALUES ('1668596137849478829', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 20.00, 0, 0, NULL, '2022-11-16 18:55:37', '2022-11-16 18:55:37');
INSERT INTO `order_table` VALUES ('1668600075995639273', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 10.00, 0, 0, NULL, '2022-11-16 20:01:16', '2022-11-16 20:01:16');
INSERT INTO `order_table` VALUES ('1668600159000198519', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 20.00, 0, 0, NULL, '2022-11-16 20:02:39', '2022-11-16 20:02:39');
INSERT INTO `order_table` VALUES ('1668600180715214548', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 2.00, 0, 0, NULL, '2022-11-16 20:03:00', '2022-11-16 20:03:00');
INSERT INTO `order_table` VALUES ('1668601267127383346', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 20.00, 0, 0, NULL, '2022-11-16 20:21:07', '2022-11-16 20:21:07');
INSERT INTO `order_table` VALUES ('1668602418284450391', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 40.00, 0, 0, NULL, '2022-11-16 20:40:18', '2022-11-16 20:40:18');
INSERT INTO `order_table` VALUES ('1668602658358834263', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 2, '测试店铺2', 30.00, 0, 0, NULL, '2022-11-16 20:44:18', '2022-11-16 20:44:18');
INSERT INTO `order_table` VALUES ('1668604271165514274', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 3, '测试店铺3', 30.00, 0, 0, NULL, '2022-11-16 21:11:11', '2022-11-16 21:11:11');
INSERT INTO `order_table` VALUES ('1668604518872324628', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 3, '测试店铺3', 30.00, 0, 0, NULL, '2022-11-16 21:15:18', '2022-11-16 21:15:18');
INSERT INTO `order_table` VALUES ('1668604563979536160', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 4, '测试店铺4', 30.00, 0, 0, NULL, '2022-11-16 21:16:04', '2022-11-16 21:16:04');
INSERT INTO `order_table` VALUES ('1668604590003342448', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 5, '测试店铺5', 30.00, 0, 0, NULL, '2022-11-16 21:16:30', '2022-11-16 21:16:30');
INSERT INTO `order_table` VALUES ('1668604919636628162', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 5, '测试店铺5', 30.00, 0, 0, NULL, '2022-11-16 21:21:59', '2022-11-16 21:21:59');
INSERT INTO `order_table` VALUES ('1668604967540202872', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 6, '测试店铺6', 30.00, 0, 0, NULL, '2022-11-16 21:22:47', '2022-11-16 21:22:47');
INSERT INTO `order_table` VALUES ('1668605233657736154', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 7, '测试店铺7', 30.00, 0, 0, NULL, '2022-11-16 21:27:13', '2022-11-16 21:27:13');
INSERT INTO `order_table` VALUES ('1668605311776813862', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 8, '测试店铺8', 30.00, 0, 0, NULL, '2022-11-16 21:28:31', '2022-11-16 21:28:31');
INSERT INTO `order_table` VALUES ('1668605598345891642', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 8, '测试店铺8', 30.00, 0, 0, NULL, '2022-11-16 21:33:18', '2022-11-16 21:33:18');
INSERT INTO `order_table` VALUES ('1668605609471644930', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 9, '测试店铺9', 30.00, 0, 0, NULL, '2022-11-16 21:33:29', '2022-11-16 21:33:29');
INSERT INTO `order_table` VALUES ('1668605633380765479', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 9, '测试店铺9', 30.00, 0, 0, NULL, '2022-11-16 21:33:53', '2022-11-16 21:33:53');
INSERT INTO `order_table` VALUES ('1668605647930523419', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 10, '测试店铺10', 30.00, 0, 0, NULL, '2022-11-16 21:34:07', '2022-11-16 21:34:07');
INSERT INTO `order_table` VALUES ('1668605706471655497', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 2.00, 0, 0, NULL, '2022-11-16 21:35:06', '2022-11-16 21:35:06');
INSERT INTO `order_table` VALUES ('1668605831059484579', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 2, '测试店铺2', 30.00, 0, 0, NULL, '2022-11-16 21:37:11', '2022-11-16 21:37:11');
INSERT INTO `order_table` VALUES ('1668605870679368368', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 2.00, 0, 0, NULL, '2022-11-16 21:37:50', '2022-11-16 21:37:50');
INSERT INTO `order_table` VALUES ('1668607645718303924', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 40.00, 0, 0, NULL, '2022-11-16 22:07:25', '2022-11-16 22:07:25');
INSERT INTO `order_table` VALUES ('1668607737773901567', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 30.00, 0, 0, NULL, '2022-11-16 22:08:57', '2022-11-16 22:08:57');
INSERT INTO `order_table` VALUES ('1668607879216106614', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 22.00, 0, 0, NULL, '2022-11-16 22:11:19', '2022-11-16 22:11:19');
INSERT INTO `order_table` VALUES ('1668607928343401209', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 92.00, 0, 0, NULL, '2022-11-16 22:12:08', '2022-11-16 22:12:08');
INSERT INTO `order_table` VALUES ('1668607939001296276', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 92.00, 0, 0, NULL, '2022-11-16 22:12:19', '2022-11-16 22:12:19');
INSERT INTO `order_table` VALUES ('1668609225656234071', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 2.00, 0, 0, NULL, '2022-11-16 22:33:45', '2022-11-16 22:33:45');
INSERT INTO `order_table` VALUES ('1668609443168754887', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 62.00, 0, 0, NULL, '2022-11-16 22:37:23', '2022-11-16 22:37:23');
INSERT INTO `order_table` VALUES ('1668609656602241557', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 22.00, 0, 0, NULL, '2022-11-16 22:40:56', '2022-11-16 22:40:56');
INSERT INTO `order_table` VALUES ('1668609689864799747', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 2, '测试店铺2', 30.00, 0, 0, NULL, '2022-11-16 22:41:29', '2022-11-16 22:41:29');
INSERT INTO `order_table` VALUES ('1668609864906677786', '123465789', 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 2.00, 1, 3, '2022-11-16 22:44:26', '2022-11-16 22:44:24', '2022-11-16 22:44:26');
INSERT INTO `order_table` VALUES ('1668609886601946386', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 2.00, 0, 0, NULL, '2022-11-16 22:44:46', '2022-11-16 22:44:46');
INSERT INTO `order_table` VALUES ('1668667846838640577', '123465789', 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 102.00, 1, 3, '2022-11-17 14:50:51', '2022-11-17 14:50:47', '2022-11-17 14:50:51');
INSERT INTO `order_table` VALUES ('1668667947914676476', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 2, '测试店铺2', 30.00, 0, 0, NULL, '2022-11-17 14:52:27', '2022-11-17 14:52:27');
INSERT INTO `order_table` VALUES ('1668667978366864511', '123465789', 10, '123', '12345678910', '北京市北京市东城区123', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 3, '测试店铺3', 30.00, 1, 3, '2022-11-17 14:53:00', '2022-11-17 14:52:58', '2022-11-17 14:53:00');
INSERT INTO `order_table` VALUES ('1668668039109991072', NULL, 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'oroeFwonWZ57gWZQEtqY0o_yttmU', 1, '测试店铺1', 2.00, 0, 0, NULL, '2022-11-17 14:53:59', '2022-11-17 14:53:59');
INSERT INTO `order_table` VALUES ('1668766494774319503', '123465789', 13, '123', '12345678910', '江苏省南京市玄武区123', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 2.00, 1, 2, '2022-11-18 18:14:57', '2022-11-18 18:14:54', '2022-11-18 18:15:23');
INSERT INTO `order_table` VALUES ('1668859411747180426', NULL, 26, '123', '12312341234', '浙江省杭州市滨江区123', NULL, '', 1, '测试店铺1', 2.00, 0, 0, NULL, '2022-11-19 20:03:32', '2022-11-19 20:03:32');
INSERT INTO `order_table` VALUES ('1668859586861460051', NULL, 26, '123', '12312341234', '浙江省杭州市滨江区123', NULL, '', 1, '测试店铺1', 2.00, 0, 0, NULL, '2022-11-19 20:06:26', '2022-11-19 20:06:26');
INSERT INTO `order_table` VALUES ('1668859785476246541', NULL, 26, '123', '12312341234', '浙江省杭州市滨江区123', NULL, '', 1, '测试店铺1', 20.00, 0, 0, NULL, '2022-11-19 20:09:45', '2022-11-19 20:09:45');
INSERT INTO `order_table` VALUES ('1668861475025912341', NULL, 26, '123', '12312341234', '浙江省杭州市滨江区123', NULL, '', 1, '测试店铺1', 2.00, 0, 0, NULL, '2022-11-19 20:37:55', '2022-11-19 20:37:55');
INSERT INTO `order_table` VALUES ('1668861494954732511', NULL, 26, '123', '12312341234', '浙江省杭州市滨江区123', NULL, '', 1, '测试店铺1', 2.00, 0, 0, NULL, '2022-11-19 20:38:14', '2022-11-19 20:38:14');
INSERT INTO `order_table` VALUES ('1668861524350988855', NULL, 26, '123', '12312341234', '浙江省杭州市滨江区123', NULL, '', 1, '测试店铺1', 20.00, 0, 0, NULL, '2022-11-19 20:38:44', '2022-11-19 20:38:44');
INSERT INTO `order_table` VALUES ('1668862178698179022', '123465789', 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 2.00, 1, 3, '2022-11-19 20:49:48', '2022-11-19 20:49:38', '2022-11-19 20:49:48');
INSERT INTO `order_table` VALUES ('1668862249733548760', '123465789', 10, '张三', '12312341234', '浙江省杭州市滨江区江南大道路123号', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 2.00, 1, 3, '2022-11-21 16:31:51', '2022-11-19 20:50:49', '2022-11-21 16:31:51');
INSERT INTO `order_table` VALUES ('1668865375675567901', NULL, 26, '123', '12312341234', '浙江省杭州市滨江区123', NULL, '', 1, '测试店铺1', 20.00, 0, 0, NULL, '2022-11-19 21:42:55', '2022-11-19 21:42:55');
INSERT INTO `order_table` VALUES ('1668923131642194852', '123465789', 10, '111', '11111111111', '浙江省杭州市滨江区111', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 2.00, 1, 2, '2022-11-20 13:45:33', '2022-11-20 13:45:31', '2022-11-21 18:24:13');
INSERT INTO `order_table` VALUES ('1669025946560605253', '123465789', 10, '于沛汕', '12312341234', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州阿图什市尤库日伊始塔村', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 2.00, 1, 3, '2022-11-21 18:19:08', '2022-11-21 18:19:07', '2022-11-21 18:19:08');
INSERT INTO `order_table` VALUES ('1669036241609149273', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 2.00, 1, 3, '2022-11-21 21:11:07', '2022-11-21 21:10:41', '2022-11-21 21:11:07');
INSERT INTO `order_table` VALUES ('1669036385039253843', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 20.00, 1, 2, '2022-11-21 21:13:05', '2022-11-21 21:13:05', '2022-11-21 21:13:19');
INSERT INTO `order_table` VALUES ('1669036445801964121', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', '123456789', 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 10.00, 1, 6, '2022-11-21 21:14:06', '2022-11-21 21:14:05', '2022-11-21 21:20:43');
INSERT INTO `order_table` VALUES ('1669444673788508756', NULL, 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 20.00, 0, 0, NULL, '2022-11-26 14:37:54', '2022-11-26 14:37:54');
INSERT INTO `order_table` VALUES ('1669444693262941055', NULL, 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 20.00, 0, 0, NULL, '2022-11-26 14:38:13', '2022-11-26 14:38:13');
INSERT INTO `order_table` VALUES ('1669444739434972775', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 20.00, 1, 3, '2022-11-26 14:39:00', '2022-11-26 14:38:59', '2022-11-26 14:39:00');
INSERT INTO `order_table` VALUES ('1669444854442666021', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 60.00, 1, 2, '2022-11-26 14:43:26', '2022-11-26 14:40:54', '2022-11-26 14:43:29');
INSERT INTO `order_table` VALUES ('1669445636281718811', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 23.00, 1, 3, '2022-11-26 14:53:57', '2022-11-26 14:53:56', '2022-11-26 14:53:57');
INSERT INTO `order_table` VALUES ('1669445654463980610', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 20.00, 1, 3, '2022-11-26 14:54:18', '2022-11-26 14:54:14', '2022-11-26 14:54:18');
INSERT INTO `order_table` VALUES ('1669445720650187379', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 20.00, 1, 2, '2022-11-26 14:55:33', '2022-11-26 14:55:20', '2022-11-26 14:55:35');
INSERT INTO `order_table` VALUES ('1669784995468136550', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 40.00, 1, 3, '2022-11-30 13:09:58', '2022-11-30 13:09:56', '2022-11-30 13:09:58');
INSERT INTO `order_table` VALUES ('1669785279796796497', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 20.00, 1, 2, '2022-11-30 13:14:41', '2022-11-30 13:14:39', '2022-11-30 13:14:46');
INSERT INTO `order_table` VALUES ('1669785312665246755', NULL, 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 10.00, 0, 0, NULL, '2022-11-30 13:15:12', '2022-11-30 13:15:12');
INSERT INTO `order_table` VALUES ('1669785487421248325', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 20.00, 1, 3, '2022-11-30 13:18:17', '2022-11-30 13:18:07', '2022-11-30 13:18:17');
INSERT INTO `order_table` VALUES ('1669785501361760751', NULL, 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 40.00, 0, 0, NULL, '2022-11-30 13:18:21', '2022-11-30 13:18:21');
INSERT INTO `order_table` VALUES ('1669785549199395657', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 20.00, 1, 3, '2022-11-30 13:19:10', '2022-11-30 13:19:09', '2022-11-30 13:19:10');
INSERT INTO `order_table` VALUES ('1669785936477203379', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 40.00, 1, 3, '2022-11-30 13:25:37', '2022-11-30 13:25:36', '2022-11-30 13:25:37');
INSERT INTO `order_table` VALUES ('1669787839178304505', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 20.00, 1, 3, '2022-11-30 13:57:20', '2022-11-30 13:57:19', '2022-11-30 13:57:20');
INSERT INTO `order_table` VALUES ('1669901822303238073', '123465789', 10, '于沛汕', '12312341234', '浙江省杭州市上城区浦沿街道大通公寓2幢1103', NULL, 'omufd6sH0RA1_1af5lhLwis6KzjA', 1, '测试店铺1', 40.00, 1, 3, '2022-12-01 21:37:03', '2022-12-01 21:37:02', '2022-12-01 21:37:03');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名字',
  `price` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '商品价格',
  `old_price` decimal(20, 2) NOT NULL COMMENT '商品原价',
  `sales` int NOT NULL DEFAULT 0 COMMENT '商品销量',
  `stock` int NOT NULL COMMENT '库存',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品类型',
  `shop_id` int NOT NULL DEFAULT 0 COMMENT '所属商铺id',
  `state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '0下架 1上架',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 148 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '苹果', 10.00, 20.00, 1, 996, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 1, 1, '2022-10-20 18:49:16', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (2, '香蕉', 30.00, 60.00, 200, 993, 'https://images.gaoshiyi.top/static/banana.png', 'fruit', 1, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (3, '薯片', 10.00, 20.00, 88, 999, 'https://images.gaoshiyi.top/static/chip.png', 'snack', 1, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (5, '黄瓜', 3.00, 8.00, 32, 999, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 1, 1, '2022-07-30 16:54:08', '2022-11-26 14:53:57');
INSERT INTO `product` VALUES (6, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 1, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (7, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 1, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (8, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 1, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (9, '苹果', 10.00, 20.00, 100, 998, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 2, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (10, '西瓜', 30.00, 60.00, 200, 996, 'https://images.gaoshiyi.top/static/watermelon.png', 'fruit', 2, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (13, '黄瓜', 3.00, 8.00, 32, 1000, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 2, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (14, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 2, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (15, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 2, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (16, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 2, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (17, '苹果', 10.00, 20.00, 100, 1000, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 3, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (18, '西瓜', 30.00, 60.00, 200, 997, 'https://images.gaoshiyi.top/static/watermelon.png', 'fruit', 3, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (19, '薯片', 10.00, 20.00, 88, 1000, 'https://images.gaoshiyi.top/static/chip.png', 'snack', 3, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (21, '黄瓜', 3.00, 8.00, 32, 999, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 3, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (22, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 3, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (23, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 3, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (24, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 3, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (25, '苹果', 10.00, 20.00, 100, 1000, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 4, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (26, '西瓜', 30.00, 60.00, 200, 998, 'https://images.gaoshiyi.top/static/watermelon.png', 'fruit', 4, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (27, '薯片', 10.00, 20.00, 88, 1000, 'https://images.gaoshiyi.top/static/chip.png', 'snack', 4, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (29, '黄瓜', 3.00, 8.00, 32, 1000, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 4, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (30, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 4, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (31, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 4, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (32, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 4, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (33, '苹果', 10.00, 20.00, 100, 1000, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 5, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (34, '西瓜', 30.00, 60.00, 200, 998, 'https://images.gaoshiyi.top/static/watermelon.png', 'fruit', 5, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (35, '薯片', 10.00, 20.00, 88, 1000, 'https://images.gaoshiyi.top/static/chip.png', 'snack', 5, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (37, '黄瓜', 3.00, 8.00, 32, 1000, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 5, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (38, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 5, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (39, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 5, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (40, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 5, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (41, '苹果', 10.00, 20.00, 100, 1000, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 6, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (42, '西瓜', 30.00, 60.00, 200, 999, 'https://images.gaoshiyi.top/static/watermelon.png', 'fruit', 6, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (43, '薯片', 10.00, 20.00, 88, 1000, 'https://images.gaoshiyi.top/static/chip.png', 'snack', 6, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (45, '黄瓜', 3.00, 8.00, 32, 1000, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 6, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (46, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 6, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (47, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 6, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (48, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 6, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (49, '苹果', 10.00, 20.00, 100, 1000, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 7, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (50, '西瓜', 30.00, 60.00, 200, 999, 'https://images.gaoshiyi.top/static/watermelon.png', 'fruit', 7, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (51, '薯片', 10.00, 20.00, 88, 1000, 'https://images.gaoshiyi.top/static/chip.png', 'snack', 7, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (53, '黄瓜', 3.00, 8.00, 32, 1000, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 7, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (54, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 7, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (55, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 7, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (56, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 7, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (57, '苹果', 10.00, 20.00, 100, 1000, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 8, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (58, '西瓜', 30.00, 60.00, 200, 998, 'https://images.gaoshiyi.top/static/watermelon.png', 'fruit', 8, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (59, '薯片', 10.00, 20.00, 88, 1000, 'https://images.gaoshiyi.top/static/chip.png', 'snack', 8, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (61, '黄瓜', 3.00, 8.00, 32, 1000, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 8, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (62, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 8, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (63, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 8, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (64, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 8, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (65, '苹果', 10.00, 20.00, 100, 1000, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 9, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (66, '西瓜', 30.00, 60.00, 200, 998, 'https://images.gaoshiyi.top/static/watermelon.png', 'fruit', 9, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (67, '薯片', 10.00, 20.00, 88, 1000, 'https://images.gaoshiyi.top/static/chip.png', 'snack', 9, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (69, '黄瓜', 3.00, 8.00, 32, 1000, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 9, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (70, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 9, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (71, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 9, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (72, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 9, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (73, '苹果', 10.00, 20.00, 100, 1000, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 10, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (74, '西瓜', 30.00, 60.00, 200, 999, 'https://images.gaoshiyi.top/static/watermelon.png', 'fruit', 10, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (75, '薯片', 10.00, 20.00, 88, 1000, 'https://images.gaoshiyi.top/static/chip.png', 'snack', 10, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (77, '黄瓜', 3.00, 8.00, 32, 1000, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 10, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (78, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 10, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (79, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 10, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (80, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 10, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (81, '苹果', 10.00, 20.00, 100, 1000, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 11, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (82, '西瓜', 30.00, 60.00, 200, 1000, 'https://images.gaoshiyi.top/static/watermelon.png', 'fruit', 11, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (83, '薯片', 10.00, 20.00, 88, 1000, 'https://images.gaoshiyi.top/static/chip.png', 'snack', 11, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (85, '黄瓜', 3.00, 8.00, 32, 1000, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 11, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (86, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 11, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (87, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 11, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (88, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 11, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (97, '草莓', 40.00, 60.00, 0, 986, 'https://images.gaoshiyi.top/static/strawberry.png', 'fruit', 1, 1, '2022-10-20 20:48:12', '2022-12-01 21:37:03');
INSERT INTO `product` VALUES (98, '芒果', 20.00, 30.00, 0, 976, 'https://images.gaoshiyi.top/static/mango.png', 'fruit', 1, 1, '2022-10-20 20:49:58', '2022-11-26 14:54:18');
INSERT INTO `product` VALUES (99, '香蕉', 20.00, 30.00, 0, 962, 'https://images.gaoshiyi.top/static/banana.png', 'fruit', 1, 1, '2022-10-20 21:04:53', '2022-11-30 13:57:20');
INSERT INTO `product` VALUES (101, '商品测试1', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 1, 1, '2022-11-05 16:11:12', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (102, '商品测试2', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 1, 1, '2022-11-05 16:11:39', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (103, '商品测试3', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 1, 1, '2022-11-05 16:12:01', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (104, '商品测试4', 0.10, 0.10, 0, 999, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 1, 1, '2022-11-05 16:12:17', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (106, '苹果', 10.00, 20.00, 100, 1000, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 12, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (107, '西瓜', 30.00, 60.00, 200, 1000, 'https://images.gaoshiyi.top/static/watermelon.png', 'fruit', 12, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (108, '薯片', 10.00, 20.00, 88, 1000, 'https://images.gaoshiyi.top/static/chip.png', 'snack', 12, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (109, '黄瓜', 3.00, 8.00, 32, 1000, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 12, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (110, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 12, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (111, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 12, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (112, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 12, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (113, '草莓', 40.00, 60.00, 0, 1000, 'https://images.gaoshiyi.top/static/strawberry.png', 'fruit', 12, 1, '2022-10-20 20:48:12', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (116, '商品测试1', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 12, 1, '2022-11-05 16:11:12', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (117, '商品测试2', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 12, 1, '2022-11-05 16:11:39', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (118, '商品测试3', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 12, 1, '2022-11-05 16:12:01', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (119, '商品测试4', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 12, 1, '2022-11-05 16:12:17', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (120, '苹果', 10.00, 20.00, 100, 1000, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 13, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (121, '西瓜', 30.00, 60.00, 200, 1000, 'https://images.gaoshiyi.top/static/watermelon.png', 'fruit', 13, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (122, '薯片', 10.00, 20.00, 88, 1000, 'https://images.gaoshiyi.top/static/chip.png', 'snack', 13, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (123, '黄瓜', 3.00, 8.00, 32, 1000, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 13, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (124, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 13, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (125, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 13, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (126, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 13, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (127, '草莓', 40.00, 60.00, 0, 1000, 'https://images.gaoshiyi.top/static/strawberry.png', 'fruit', 13, 1, '2022-10-20 20:48:12', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (130, '商品测试1', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 13, 1, '2022-11-05 16:11:12', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (131, '商品测试2', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 13, 1, '2022-11-05 16:11:39', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (132, '商品测试3', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 13, 1, '2022-11-05 16:12:01', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (133, '苹果', 10.00, 20.00, 100, 1000, 'https://images.gaoshiyi.top/static/apple.png', 'fruit', 14, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (134, '西瓜', 30.00, 60.00, 200, 1000, 'https://images.gaoshiyi.top/static/watermelon.png', 'fruit', 14, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (135, '薯片', 10.00, 20.00, 88, 1000, 'https://images.gaoshiyi.top/static/chip.png', 'snack', 14, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (136, '黄瓜', 3.00, 8.00, 200, 1000, 'https://images.gaoshiyi.top/static/cucumber.png', 'vegetable', 14, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (137, '西红柿', 5.00, 10.00, 66, 1000, 'https://images.gaoshiyi.top/static/tomato.png', 'vegetable', 14, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (138, '猪肉', 30.00, 50.00, 100, 1000, 'https://images.gaoshiyi.top/static/pork.png', 'meat', 14, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (139, '羊肉', 60.00, 99.00, 500, 1000, 'https://images.gaoshiyi.top/static/mutton.png', 'meat', 14, 1, '2022-07-30 16:54:08', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (140, '草莓', 40.00, 60.00, 0, 1000, 'https://images.gaoshiyi.top/static/strawberry.png', 'fruit', 14, 1, '2022-10-20 20:48:12', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (143, '商品测试1', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 14, 1, '2022-11-05 16:11:12', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (144, '商品测试2', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 14, 1, '2022-11-05 16:11:39', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (145, '商品测试3', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 14, 1, '2022-11-05 16:12:01', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (146, '商品测试4', 0.10, 0.10, 0, 1000, 'https://images.gaoshiyi.top/static/hamburger.jpg', 'test', 14, 1, '2022-11-05 16:12:17', '2022-11-22 20:21:17');
INSERT INTO `product` VALUES (147, '香蕉', 15.00, 20.00, 50, 1000, 'https://images.gaoshiyi.top/static/banana.png', 'fruit', 11, 1, '2022-11-14 16:52:06', '2022-11-22 20:21:17');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '类目id',
  `shop_id` int NOT NULL DEFAULT 0 COMMENT '所属店铺id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类目名字',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类目类型',
  `state` tinyint NOT NULL DEFAULT 1 COMMENT '状态 1正常 2禁用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, 1, '新鲜水果', 'fruit', 1, '2022-07-30 15:31:34', '2022-11-10 17:02:06');
INSERT INTO `product_category` VALUES (2, 1, '休闲食品', 'snack', 1, '2022-07-30 15:34:06', '2022-11-10 17:54:13');
INSERT INTO `product_category` VALUES (3, 1, '时令蔬菜', 'vegetable', 1, '2022-07-30 15:34:19', '2022-11-10 17:53:51');
INSERT INTO `product_category` VALUES (4, 1, '肉蛋家禽', 'meat', 1, '2022-07-30 15:34:28', '2022-10-21 18:38:43');
INSERT INTO `product_category` VALUES (12, 1, '分类测试', 'test', 1, '2022-11-05 16:08:25', '2022-11-05 16:08:25');
INSERT INTO `product_category` VALUES (17, 2, '新鲜水果', 'fruit', 1, '2022-11-10 17:55:45', '2022-11-10 17:55:45');
INSERT INTO `product_category` VALUES (18, 2, '休闲食品', 'snack', 1, '2022-11-10 17:55:45', '2022-11-10 17:55:45');
INSERT INTO `product_category` VALUES (19, 2, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:55:45', '2022-11-10 17:55:45');
INSERT INTO `product_category` VALUES (20, 2, '肉蛋家禽', 'meat', 1, '2022-11-10 17:55:45', '2022-11-10 17:55:45');
INSERT INTO `product_category` VALUES (21, 3, '新鲜水果', 'fruit', 1, '2022-11-10 17:57:09', '2022-11-10 17:57:09');
INSERT INTO `product_category` VALUES (22, 3, '休闲食品', 'snack', 1, '2022-11-10 17:57:09', '2022-11-10 17:57:09');
INSERT INTO `product_category` VALUES (23, 3, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:57:09', '2022-11-10 17:57:09');
INSERT INTO `product_category` VALUES (24, 3, '肉蛋家禽', 'meat', 1, '2022-11-10 17:57:09', '2022-11-10 17:57:09');
INSERT INTO `product_category` VALUES (25, 4, '新鲜水果', 'fruit', 1, '2022-11-10 17:57:09', '2022-11-10 17:57:09');
INSERT INTO `product_category` VALUES (26, 4, '休闲食品', 'snack', 1, '2022-11-10 17:57:09', '2022-11-10 17:57:09');
INSERT INTO `product_category` VALUES (27, 4, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:57:09', '2022-11-10 17:57:09');
INSERT INTO `product_category` VALUES (28, 4, '肉蛋家禽', 'meat', 1, '2022-11-10 17:57:10', '2022-11-10 17:57:10');
INSERT INTO `product_category` VALUES (29, 5, '新鲜水果', 'fruit', 1, '2022-11-10 17:57:10', '2022-11-10 17:57:10');
INSERT INTO `product_category` VALUES (30, 5, '休闲食品', 'snack', 1, '2022-11-10 17:57:10', '2022-11-10 17:57:10');
INSERT INTO `product_category` VALUES (31, 5, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:57:10', '2022-11-10 17:57:10');
INSERT INTO `product_category` VALUES (32, 5, '肉蛋家禽', 'meat', 1, '2022-11-10 17:57:10', '2022-11-10 17:57:10');
INSERT INTO `product_category` VALUES (33, 6, '新鲜水果', 'fruit', 1, '2022-11-10 17:57:10', '2022-11-10 17:57:10');
INSERT INTO `product_category` VALUES (34, 6, '休闲食品', 'snack', 1, '2022-11-10 17:57:10', '2022-11-10 17:57:10');
INSERT INTO `product_category` VALUES (35, 6, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:57:10', '2022-11-10 17:57:10');
INSERT INTO `product_category` VALUES (36, 6, '肉蛋家禽', 'meat', 1, '2022-11-10 17:57:10', '2022-11-10 17:57:10');
INSERT INTO `product_category` VALUES (37, 7, '新鲜水果', 'fruit', 1, '2022-11-10 17:57:10', '2022-11-10 17:57:10');
INSERT INTO `product_category` VALUES (38, 7, '休闲食品', 'snack', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (39, 7, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (40, 7, '肉蛋家禽', 'meat', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (41, 8, '新鲜水果', 'fruit', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (42, 8, '休闲食品', 'snack', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (43, 8, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (44, 8, '肉蛋家禽', 'meat', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (45, 9, '新鲜水果', 'fruit', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (46, 9, '休闲食品', 'snack', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (47, 9, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (48, 9, '肉蛋家禽', 'meat', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (49, 10, '新鲜水果', 'fruit', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (50, 10, '休闲食品', 'snack', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (51, 10, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (52, 10, '肉蛋家禽', 'meat', 1, '2022-11-10 17:57:11', '2022-11-10 17:57:11');
INSERT INTO `product_category` VALUES (53, 11, '新鲜水果', 'fruit', 1, '2022-11-10 17:57:12', '2022-11-10 17:57:12');
INSERT INTO `product_category` VALUES (54, 11, '休闲食品', 'snack', 1, '2022-11-10 17:57:12', '2022-11-10 17:57:12');
INSERT INTO `product_category` VALUES (55, 11, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:57:12', '2022-11-10 17:57:12');
INSERT INTO `product_category` VALUES (56, 11, '肉蛋家禽', 'meat', 1, '2022-11-10 17:57:12', '2022-11-10 17:57:12');
INSERT INTO `product_category` VALUES (57, 12, '新鲜水果', 'fruit', 1, '2022-11-10 17:59:02', '2022-11-10 17:59:02');
INSERT INTO `product_category` VALUES (58, 12, '休闲食品', 'snack', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (59, 12, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (60, 12, '肉蛋家禽', 'meat', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (61, 13, '新鲜水果', 'fruit', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (62, 13, '休闲食品', 'snack', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (63, 13, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (64, 13, '肉蛋家禽', 'meat', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (65, 14, '新鲜水果', 'fruit', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (66, 14, '休闲食品', 'snack', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (67, 14, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (68, 14, '肉蛋家禽', 'meat', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (69, 15, '新鲜水果', 'fruit', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (70, 15, '休闲食品', 'snack', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (71, 15, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:59:03', '2022-11-10 17:59:03');
INSERT INTO `product_category` VALUES (72, 15, '肉蛋家禽', 'meat', 1, '2022-11-10 17:59:04', '2022-11-10 17:59:04');
INSERT INTO `product_category` VALUES (73, 16, '新鲜水果', 'fruit', 1, '2022-11-10 17:59:04', '2022-11-10 17:59:04');
INSERT INTO `product_category` VALUES (74, 16, '休闲食品', 'snack', 1, '2022-11-10 17:59:04', '2022-11-10 17:59:04');
INSERT INTO `product_category` VALUES (75, 16, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:59:04', '2022-11-10 17:59:04');
INSERT INTO `product_category` VALUES (76, 16, '肉蛋家禽', 'meat', 1, '2022-11-10 17:59:04', '2022-11-10 17:59:04');
INSERT INTO `product_category` VALUES (77, 17, '新鲜水果', 'fruit', 1, '2022-11-10 17:59:04', '2022-11-10 17:59:04');
INSERT INTO `product_category` VALUES (78, 17, '休闲食品', 'snack', 1, '2022-11-10 17:59:04', '2022-11-10 17:59:04');
INSERT INTO `product_category` VALUES (79, 17, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:59:04', '2022-11-10 17:59:04');
INSERT INTO `product_category` VALUES (80, 17, '肉蛋家禽', 'meat', 1, '2022-11-10 17:59:04', '2022-11-10 17:59:04');
INSERT INTO `product_category` VALUES (81, 18, '新鲜水果', 'fruit', 1, '2022-11-10 17:59:04', '2022-11-10 17:59:04');
INSERT INTO `product_category` VALUES (82, 18, '休闲食品', 'snack', 1, '2022-11-10 17:59:04', '2022-11-10 17:59:04');
INSERT INTO `product_category` VALUES (83, 18, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:59:04', '2022-11-10 17:59:04');
INSERT INTO `product_category` VALUES (84, 18, '肉蛋家禽', 'meat', 1, '2022-11-10 17:59:04', '2022-11-10 17:59:04');
INSERT INTO `product_category` VALUES (85, 19, '新鲜水果', 'fruit', 1, '2022-11-10 17:59:05', '2022-11-10 17:59:05');
INSERT INTO `product_category` VALUES (86, 19, '休闲食品', 'snack', 1, '2022-11-10 17:59:05', '2022-11-10 17:59:05');
INSERT INTO `product_category` VALUES (87, 19, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:59:05', '2022-11-10 17:59:05');
INSERT INTO `product_category` VALUES (88, 19, '肉蛋家禽', 'meat', 1, '2022-11-10 17:59:05', '2022-11-10 17:59:05');
INSERT INTO `product_category` VALUES (89, 20, '新鲜水果', 'fruit', 1, '2022-11-10 17:59:05', '2022-11-10 17:59:05');
INSERT INTO `product_category` VALUES (90, 20, '休闲食品', 'snack', 1, '2022-11-10 17:59:05', '2022-11-10 17:59:05');
INSERT INTO `product_category` VALUES (91, 20, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:59:05', '2022-11-10 17:59:05');
INSERT INTO `product_category` VALUES (92, 20, '肉蛋家禽', 'meat', 1, '2022-11-10 17:59:05', '2022-11-10 17:59:05');
INSERT INTO `product_category` VALUES (93, 21, '新鲜水果', 'fruit', 1, '2022-11-10 17:59:05', '2022-11-10 17:59:05');
INSERT INTO `product_category` VALUES (94, 21, '休闲食品', 'snack', 1, '2022-11-10 17:59:05', '2022-11-10 17:59:05');
INSERT INTO `product_category` VALUES (95, 21, '时令蔬菜', 'vegetable', 1, '2022-11-10 17:59:05', '2022-11-10 17:59:05');
INSERT INTO `product_category` VALUES (96, 21, '肉蛋家禽', 'meat', 1, '2022-11-10 17:59:05', '2022-11-10 17:59:05');

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller`  (
  `seller_id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '卖家id',
  `shop_id` int NOT NULL COMMENT '所管理的店铺',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `real_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员真实姓名',
  `open_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信openid',
  `privilege` tinyint NOT NULL COMMENT '店铺管理员权限',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `state` tinyint NOT NULL DEFAULT 1 COMMENT '管理员状态',
  `last_login` timestamp NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`seller_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `open_id`(`open_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '卖家信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seller
-- ----------------------------
INSERT INTO `seller` VALUES (1, 1, 'sellerAdmin', 'e10adc3949ba59abbe56e057f20f883e', '张三', 'o7dDg5pyc1w3wJ5IfPCaUHRyrnTE', 0, 'admin@admin.com', 1, '2022-11-27 19:32:58', '2022-09-30 14:54:47', '2022-11-27 19:32:58');
INSERT INTO `seller` VALUES (2, 1, 'test123', 'e10adc3949ba59abbe56e057f20f883e', '测试', NULL, 1, '123@123.com', 1, '2022-11-05 16:05:30', '2022-10-25 22:45:08', '2022-11-10 16:55:22');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '类目id',
  `cate_id` int NOT NULL COMMENT '点便所属分类id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '店铺名字',
  `sales` int NOT NULL DEFAULT 0 COMMENT '累计销量',
  `express_limit` smallint NOT NULL DEFAULT 0 COMMENT '起送价格',
  `express_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '运费',
  `discount` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '店铺折扣描述',
  `img_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '店铺图片',
  `state` tinyint NOT NULL DEFAULT 1 COMMENT '状态 1正常 2禁用',
  `recommend` tinyint NOT NULL DEFAULT 0 COMMENT '是否推荐 1是 0否',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '店铺表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES (1, 1, '测试店铺1', 2001, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (2, 1, '测试店铺2', 1000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (3, 1, '测试店铺3', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (4, 1, '测试店铺4', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (5, 1, '测试店铺5', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (6, 1, '测试店铺6', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (7, 1, '测试店铺7', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (8, 1, '测试店铺8', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (9, 1, '测试店铺9', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (10, 1, '测试店铺10', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (11, 1, '测试店铺11', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (12, 1, '测试店铺12', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (13, 1, '测试店铺13', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (14, 1, '测试店铺14', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (15, 1, '测试店铺15', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (16, 1, '测试店铺16', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (17, 2, '测试店铺17', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (18, 2, '测试店铺18', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (19, 2, '测试店铺19', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (20, 2, '测试店铺20', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (21, 2, '测试店铺21', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (22, 2, '测试店铺22', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (23, 2, '测试店铺23', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (24, 2, '测试店铺24', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (25, 2, '测试店铺25', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (26, 2, '测试店铺26', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (27, 2, '测试店铺27', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (28, 2, '测试店铺28', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (29, 2, '测试店铺29', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (30, 2, '测试店铺30', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (31, 2, '测试店铺31', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (32, 2, '测试店铺32', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (33, 2, '测试店铺33', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (34, 2, '测试店铺34', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (35, 2, '测试店铺35', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');
INSERT INTO `shop` VALUES (36, 2, '测试店铺36', 2000, 0, 0.00, '双十一促销 所有商品9折起！', 'https://images.gaoshiyi.top/static/shop.png', 1, 1, '2022-07-30 11:14:55', '2022-11-22 20:21:02');

-- ----------------------------
-- Table structure for shop_category
-- ----------------------------
DROP TABLE IF EXISTS `shop_category`;
CREATE TABLE `shop_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '类目id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类目名字',
  `img_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类目图片',
  `state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态 1正常 2禁用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '店铺分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_category
-- ----------------------------
INSERT INTO `shop_category` VALUES (1, '超市便利', 'https://images.gaoshiyi.top/static/supermarket.png', 1, '2022-09-20 18:17:13', '2022-11-22 20:20:37');
INSERT INTO `shop_category` VALUES (2, '蔬菜水果', 'https://images.gaoshiyi.top/static/vegetable.png', 1, '2022-09-20 18:17:13', '2022-11-22 20:20:37');
INSERT INTO `shop_category` VALUES (3, '饮品小吃', 'https://images.gaoshiyi.top/static/drink.png', 1, '2022-09-20 18:17:14', '2022-11-22 20:20:37');
INSERT INTO `shop_category` VALUES (4, '每日优选', 'https://images.gaoshiyi.top/static/daily-best.png', 1, '2022-09-20 18:17:14', '2022-11-22 20:20:37');
INSERT INTO `shop_category` VALUES (5, '火锅烧烤', 'https://images.gaoshiyi.top/static/hot-pot.png', 1, '2022-09-20 18:17:14', '2022-11-22 20:20:37');
INSERT INTO `shop_category` VALUES (6, '鲜花绿植', 'https://images.gaoshiyi.top/static/flower.png', 1, '2022-09-20 18:17:14', '2022-11-22 20:20:37');
INSERT INTO `shop_category` VALUES (7, '医药健康', 'https://images.gaoshiyi.top/static/medicine.png', 1, '2022-09-20 18:17:14', '2022-11-22 20:20:37');
INSERT INTO `shop_category` VALUES (8, '家居时尚', 'https://images.gaoshiyi.top/static/furniture.png', 1, '2022-09-20 18:17:15', '2022-11-22 20:20:37');
INSERT INTO `shop_category` VALUES (9, '烘培蛋糕', 'https://images.gaoshiyi.top/static/cake.png', 1, '2022-09-20 18:17:15', '2022-11-22 20:20:37');
INSERT INTO `shop_category` VALUES (10, '轻食沙拉', 'https://images.gaoshiyi.top/static/light-food.png', 1, '2022-09-20 18:17:15', '2022-11-22 20:20:37');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `password` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `open_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信标识openid',
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `head_portrait` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像地址',
  `money` decimal(20, 2) NULL DEFAULT 0.00 COMMENT '红包',
  `coupons` tinyint NULL DEFAULT 0 COMMENT '优惠券',
  `gold_coin` decimal(20, 2) NULL DEFAULT 0.00 COMMENT '金币',
  `iou` decimal(20, 2) NULL DEFAULT 0.00 COMMENT '白条',
  `state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '0禁止 1正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
SET FOREIGN_KEY_CHECKS = 1;
