/*
 Navicat Premium Data Transfer

 Source Server         : 123
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : landscape

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 03/06/2024 17:50:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `AdminID` int(0) NOT NULL,
  `Name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ContactInfo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`AdminID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '王五', '16230459810', '1');
INSERT INTO `admin` VALUES (2, '赵六', '18642301596', '2');

-- ----------------------------
-- Table structure for attraction
-- ----------------------------
DROP TABLE IF EXISTS `attraction`;
CREATE TABLE `attraction`  (
  `AttractionID` int(0) NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `DetailedAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `OpeningHours` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CityName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`AttractionID`) USING BTREE,
  INDEX `attraction_ibfk_1`(`CityName`) USING BTREE,
  CONSTRAINT `attraction_ibfk_1` FOREIGN KEY (`CityName`) REFERENCES `city` (`Name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attraction
-- ----------------------------
INSERT INTO `attraction` VALUES (1, '黄果树瀑布', '黄果树瀑布，即黄果树大瀑布。古称白水河瀑布，亦名“黄葛墅”瀑布或“黄葛树”瀑布，贵州民间自古以来就流传有黄果树瀑布的神话故事，黄果树瀑布的名称就来自这个神话故事中结“黄果”的树。', '贵州省安顺市镇宁布依族苗族自治县', '8:00', '安顺');
INSERT INTO `attraction` VALUES (2, '龙宫', '龙宫景区由龙潭秘境和通漩田园两大主题片区组成，拥有中国最长最美水溶洞（一、二进龙宫）、中国最大洞中佛堂（观音洞）、中国最大洞中岩溶瀑布（龙门飞瀑）、中国原子能机构测定的世界天然辐射率最低的地方，世界最大的水旱溶洞集群，世界上最大植物汉字“龙”字田等得天独厚的自然资源，和以布依、苗族为主的多样民族文化等多种旅游资源；其丰富的喀斯特地质地貌，奇妙的自然景观，多彩的民族风情旷世稀有；独特的龙文化、淳朴的宗教信仰，清新的田园气息交相辉映，绘就一幅怡然自得的人间仙境画卷。', '贵州省安顺市西秀区龙宫镇', '8:00', '安顺');
INSERT INTO `attraction` VALUES (3, '关岭古生物化石群国家地质公园', '贵州关岭化石群国家地质公园埋藏的化石形成于距今二亿二千万年前(晚三叠世)的海湾环境。主要化石门类包括：鱼龙、海龙、楯齿龙等海生爬行动物化石及海百合、菊石、双壳类、牙形石、鹦鹉螺、腕足类、鱼类和陆地生长异地保存的古植物化石。', '贵州省安顺市关岭布依族苗族自治县', '8:00', '安顺');
INSERT INTO `attraction` VALUES (4, '贵州省博物馆', '贵州省博物馆，占地面积70860平方米，总建筑面积46450平方米。建筑大致分为中部、东部和西部三部分，东区为公众服务区，包含临展、非遗展演、摄影与书画展、4D影院、青少年数字体验馆，中区为核心功能区，一层和二层为“民族贵州”基本陈列展厅，三层为“历史贵州”基本陈列展厅，其他区域为大厅、餐厅、文物库房等功能用房，西区为博物馆行政办公区。', '贵州省贵阳市观山湖区', '9:00', '贵阳');
INSERT INTO `attraction` VALUES (5, '织金洞', '织金洞，位于贵州省织金县官寨乡，地处乌江源流之一的六冲河南岸，距省城贵阳120千米。公园占地面积170平方千米，海拔900~1670米。现为国家地质公园、国家自然遗产、国家AAAAA级旅游景区、世界地质公园。2015年9月19日，织金洞国家地质公园被联合国教科文组织正式批准加入世界地质公园，填补了贵州没有世界地质公园的空白，成为贵州省第一个世界地质公园。', '贵州省毕节市织金县官寨乡', '9:00', '毕节');
INSERT INTO `attraction` VALUES (6, '威宁草海', '威宁草海生态旅游区，位于贵州西部威宁县境内，是贵州最大的天然淡水湖，海拔2171.7米，位于威宁自治县城西南侧，总面积为1.2万公顷，常年水域面积约25平方千米。是人禽共生、和谐相处的十大候鸟活动场所之一，栖息着鸟类200余种、珍稀鸟类70余种，被誉为“鸟类王国”。', '贵州省毕节市威宁县', '9:00', '毕节');
INSERT INTO `attraction` VALUES (7, '百里杜鹃', '百里杜鹃风景区，位于贵州省西北部、毕节市中部。是国家级森林公园，国家生态旅游示范区，国家AAAAA级旅游景区。景区由普底、金坡、方家坪、花王等多个景区组成，景区内绵延分布着面积达125.8平方千米的原始杜鹃林带，被誉为“地球彩带、世界花园”。', '贵州省毕节市大方县、黔西县交界处', '9:00', '毕节');
INSERT INTO `attraction` VALUES (8, '格凸河', '格凸河位于贵州安顺紫云县，距离安顺103公里，在这里，有现代国际流行的低空跳伞、蹦绳、翼服飞行的惊险刺激，也有古朴传统的苗家蜘蛛人徒手攀岩、上刀山下火海的非物质文化展示；有令人心驰神往的亚鲁王文化，也有世外桃花源一般的避世圣地大河苗寨；有举世无双的大穿洞燕王宫及万燕归巢绝景，也有国内最深的竖井和世界最高、保存最完好的古河道遗迹盲谷。格凸河景区集岩溶、山、水、洞、石、林组合之精髓，融雄、奇、险、峻、幽、古为一身，是自然与人文风光锦画，是鲜存于现代文明中古朴、原始、生态、自然、雄美的一方净土，民俗、文化、历史、自然在这里交融汇聚，形成多种规模宏大、绚丽迷人的美。多美格凸河，一个美的不可思议的地方，一个洗心避世的灵山，一个户外运动休闲的天堂。', '贵州省安顺市紫云县', '9:00', '安顺');
INSERT INTO `attraction` VALUES (9, '遵义会议会址', '遵义会议会址，位于贵州省遵义市红花岗区子尹路96号，1935年1月初，中国工农红军长征到达遵义后，中华苏维埃共和国中央革命军事委员会总司部与一局（负责作点）即驻在这幢楼房里。1月15日至17日，遵义会议（即中共中央政治局扩大会议）就在主楼楼上原房主的小客厅举行。这次会议确立了以毛泽东为代表的新的中央领导集体。遵义会议成为中国共产党的历史上一个生死攸关的转折点。', '贵州省遵义市红花岗区', '9:00', '遵义');
INSERT INTO `attraction` VALUES (10, '赤水丹霞国家地质公园', '贵州赤水丹霞国家地质公园位于贵州省赤水市，地处四川盆地南缘，紧靠黔北大娄山北麓，扬子准地台西部。是青年早期丹霞地貌的代表，其面积达1200多平方公里，是全国面积最大的丹霞地貌。赤水丹霞含一个国家级自然保护区、两个国家森林公园和一个国家级风景名胜区，并处在长江上游珍稀特有鱼类国家级自然保护区的核心区。主要包括赤水国家级风景名胜区十丈洞景区、丙安竹海景区、赤水桫椤国家级自然保护区和赤水竹海国家森林公园。', '贵州省遵义市赤水市', '9:00', '遵义');
INSERT INTO `attraction` VALUES (11, '梵净山', '武陵山脉主峰，海拔2572米，位于贵州省铜仁市的江口、印江、松桃三县交界处，梵净山是中国南方最早从海洋抬升为陆地的地方之一，保存了亚热带原生生态系统，并孑遗着7000万至200万年前的古老珍稀物种 ，繁衍着野生动植物7100多种，是黔金丝猴唯一的栖息地。梵净山是西南一座具有2000多年历史的文化名山，早在春秋战国时期，梵净山就属楚国“黔中地”，秦朝属“黔中郡”，汉代属“武陵郡”，以后一直是“武陵蛮”崇拜的神山、圣山。', '贵州省铜仁市江口、印江、松桃三县交界处', '7:00', '铜仁');
INSERT INTO `attraction` VALUES (12, '黔南州荔波樟江景区', '樟江，位于中国贵州省南部，是打狗河左岸支流。荔波喀斯特于2007年6月27日在第三十一届世界遗产大会上被评列入世界自然遗产。', '贵州省黔南布依族苗族自治州荔波县', '8:00', '黔南');
INSERT INTO `attraction` VALUES (13, '马岭河峡谷', '马岭河峡谷国家级风景名胜区位于贵州省黔西南布依族苗族自治州，马岭河峡谷集雄、奇、险、秀为一体，谷宽50—150米、谷深120-280米。马岭河峡谷是一条在造山运动中剖削深切的大裂水地缝，谷内群瀑飞流，翠竹倒挂，溶洞相连，两岸古树名木点缀其间，千姿百态。', '贵州省黔西南布依族苗族自治州兴义市', '8:00', '黔西南');
INSERT INTO `attraction` VALUES (14, '肇兴侗寨', '肇兴侗寨，位于贵州省黔东南苗族侗族自治州黎平县东南部，占地18万平方米，截止2012年统计，居民1100余户，6000多人，是全国最大的侗族村寨之一，素有“侗乡第一寨”之美誉。肇兴侗寨四面环山，寨子建于山中盆地，两条小溪汇成一条小河穿寨而过。寨中房屋为干栏式吊脚楼，鳞次栉比，错落有致，全部用杉木建造，硬山顶覆小青瓦，古朴实用。肇兴侗寨分内姓外姓，对外全为陆姓侗族，分为五大房族，分居五个自然片区，当地称之为“团”。分为仁团、义团、礼团、智团、信团五团。', '贵州省黔东南州黎平县', NULL, '黔东南');
INSERT INTO `attraction` VALUES (15, '镇远古镇', '镇远古镇，位于贵州省黔东南苗族侗族自治州镇远县，河水蜿蜒，以“S”形穿城而过，北岸为旧府城，南岸为旧卫城。两城池皆为明代所建，现尚存部分城墙和城门。城内外古建筑、传统民居、历史码头数量颇多。', '贵州省黔东南苗族侗族自治州镇远县', NULL, '黔东南');
INSERT INTO `attraction` VALUES (16, '西江千户苗寨', '11111', '贵州省黔东南苗族侗族自治州雷山县西江镇南贵村', '7:00', '黔东南');

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Description` varchar(555) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('六盘水', '六盘水历史悠久，春秋时期为牂牁国属地。“三线”建设时期，缔造了“三线”文化。1978年，设立省辖六盘水市。市境内考古发现距今20多万年的“盘县大洞人”，为贵州发现的最早智人，被评为当年全国十大考古发现之首。素有“四省立交桥”之称。贵昆、南昆、内昆、水红铁路在此交汇，沪昆高铁境内段和安六城际铁路相继建成通车，在全省率先实现“县县通高铁”。沪昆、杭瑞、都香、宜六、水兴等高速公路穿境而过，实现“县县通高速”。全市有彝族火把节、苗族跳花节、回族开斋节、仡佬族吃新节等丰富多样的节庆文化，有牂牁江湖滨旅游度假区、野玉海国际山地旅游度假区、乌蒙大草原旅游景区等重点旅游景区。');
INSERT INTO `city` VALUES ('安顺', '安顺市，贵州省辖地级市，位于贵州省中西部，地处长江水系乌江流域和珠江水系北盘江流域的分水岭地带，形成中部高、南北底；西北高、东南低的地势，是世界上典型的喀斯特地貌集中地区，属典型的高原型湿润亚热带季风气候，总面积9267平方千米。安顺市素有“黔之腹、滇之喉、蜀粤之唇齿”之称，是黔中经济区重要增长极、黔中城市群重要中心城市；是贵州省历史文化名城，拥有穿洞文化、夜郎文化、牂牁文化、屯堡文化等独特的历史文化遗存；全市风景区面积占幅员面积的12％以上，是国家最早确定的甲类旅游开放城市、中国优秀旅游城市、中国最美丽城市、中国十大特色休闲城市、中国十大秀美之城，拥有黄果树、龙宫等5A级景区，高荡、阿歪寨、秀水、小河湾、塘约、大坝等一批国家级、省级示范性美丽乡村、民族特色村寨。');
INSERT INTO `city` VALUES ('毕节', '毕节市历史文化悠久。秦时，为蜀郡属地。汉，为益州所辖。晋，属益州、朱提郡。唐代，置牂牁、乌撒部。宋代，置罗氏鬼国辖乌撒部、毗那部。元代、明代，分属水西宣慰司等部，清置大定府（州）。民国时期，为贵州省第四行政督察专员公署，中华人民共和国成立后设毕节专员公署，1970年，更名为毕节地区行政公署。2011年，撤销毕节地区设立地级毕节市。毕节市是川、滇、黔、渝结合部区域性中心城市，西南地区区域性重要综合交通枢纽，珠三角连接西南地区、长三角连接东盟地区的重要通道。毕节磷矿储量名列全国前茅，铁矿、铜矿、铅矿、稀土矿储量处在贵州第一；生物资源多样，有马铃薯之乡、白蒜之乡等众多“地理标志”；水能资源丰富，河湖水系纵横交错。毕节是古夜郎政治经济文化中心之一，中国南方古人类文化发祥地。毕节风光景色旖旎，被誉为“洞天湖地、花海鹤乡、避暑天堂”；毕节气候清凉宜人，是避暑旅游城市观测点。毕节是三省红都，长江以南最后一块革命根据地。');
INSERT INTO `city` VALUES ('贵阳', 'hhhhhhh');
INSERT INTO `city` VALUES ('遵义', '遵义市地处中国西南地区、贵州省北部，南临贵阳市，北倚重庆市，西接四川省，是西南地区承接南北、连接东西、通江达海的重要交通枢纽，成渝—黔中经济区走廊的核心区和主廊道，黔渝合作的桥头堡、主阵地和先行区。遵义市曾获得全国文明城市、国家森林城市、国家卫生城市、双拥模范城市、中国优秀旅游城市、国家园林城市等多项称号，同时也是中国三大名酒“茅五剑”之一的茅台酒的故乡。遵义市是首批国家历史文化名城，中国共产党在遵义召开了著名的“遵义会议”，成为了党的生死攸关的转折点，被称为“转折之城，会议之都”。遵义市拥有世界文化遗产海龙屯、世界自然遗产赤水丹霞，有中国长寿之乡、中国高品质绿茶产区、中国名茶之乡、国家全域旅游示范区、中国吉他制造之乡等称号。');
INSERT INTO `city` VALUES ('铜仁', '铜仁市历史沿革源远流长，秦代为黔中郡腹部地区，汉时改隶武陵郡，蜀汉时始有县治。唐代分属思州、锦州、黔州。宋末元初设思州、思南两宣慰司，元代设置“铜人大小江蛮夷军民长官司”。明永乐十一年撤思州、思南宣慰司，于今境设铜仁、思南、石阡、乌罗4府，并划归新建的贵州省管辖。铜仁是书法之乡，明清之际涌现了周冕、周以湘、王道行、潘登云、严寅亮、鄢师竹六位书法家。有国家级自然保护区2个，国家级风景名胜区3个，省级风景名胜区9个，国家矿山公园1个，国家级喀斯特地质公园1个。沪昆铁路、沪昆高速公路、杭瑞高速公路、铜大高速公路、思剑高速公路穿境而过。');
INSERT INTO `city` VALUES ('黔东南', '黔东南苗族侗族自治州是全国苗族侗族人口最集中的地区，被誉为生态之州、歌舞之州、神奇之州，各族人民在这片民族文化沃土躬耕垄亩，是世界乡土文化基金会确定的18个生态文化保护圈之一，侗族大歌被列为世界非物质文化遗产，苗族服饰、古歌、银饰等53项72个保护点列入国家非物质文化遗产名录。㵲阳河、云台山、雷公山等自然景区山水秀美风光独特，镇远古城、西江千户苗寨、肇兴千户侗寨、下司古镇等文化景区历史悠久。');
INSERT INTO `city` VALUES ('黔南', '1956年8月8日，设立黔南布依族苗族自治州。黔南布依族苗族自治州曾是南方出海丝绸之路的重要通道，也是黔中通往川桂湘滇的故道，商贾云集、物流通达。黔南境内航空、铁路、公路、河运纵横交错；黔南先后涌现出抗清名将辽东巡抚、山海关总兵丘禾嘉，护国先驱戴勘、黄齐生，中共一大代表邓恩铭烈士等一批英雄人物。');
INSERT INTO `city` VALUES ('黔西南', '黔西南州黄金分布广，储量大，品质高，2005年，被中国黄金协会命名为“中国金州”，黔西南州山川秀丽，气候宜人，文化底蕴深厚，清代乾隆年间创办了著名的笔山书院。州府兴义人杰地灵，涌现出张之洞、王伯群、王文华、何应钦、刘显世等大批人物。');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `EmployeeID` int(0) NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `WorkHours` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ContactInfo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `IDCard` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`EmployeeID`, `IDCard`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '王小', '安保', '8:00', '14623980012', '520198807193560');

-- ----------------------------
-- Table structure for freeattraction
-- ----------------------------
DROP TABLE IF EXISTS `freeattraction`;
CREATE TABLE `freeattraction`  (
  `AttractionID` int(0) NOT NULL,
  `ReservationRequired` tinyint(1) NULL DEFAULT NULL,
  `VisitorLimit` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`AttractionID`) USING BTREE,
  CONSTRAINT `freeattraction_ibfk_1` FOREIGN KEY (`AttractionID`) REFERENCES `attraction` (`AttractionID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of freeattraction
-- ----------------------------
INSERT INTO `freeattraction` VALUES (3, 1, 100000);
INSERT INTO `freeattraction` VALUES (4, 1, 100000);
INSERT INTO `freeattraction` VALUES (9, 1, 100000);
INSERT INTO `freeattraction` VALUES (14, NULL, NULL);
INSERT INTO `freeattraction` VALUES (15, NULL, NULL);

-- ----------------------------
-- Table structure for operate
-- ----------------------------
DROP TABLE IF EXISTS `operate`;
CREATE TABLE `operate`  (
  `CompanyID` int(0) NOT NULL,
  `AttractionID` int(0) NOT NULL,
  PRIMARY KEY (`CompanyID`, `AttractionID`) USING BTREE,
  INDEX `operate_ibfk_2`(`AttractionID`) USING BTREE,
  CONSTRAINT `operate_ibfk_1` FOREIGN KEY (`CompanyID`) REFERENCES `travelcompany` (`CompanyID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operate_ibfk_2` FOREIGN KEY (`AttractionID`) REFERENCES `attraction` (`AttractionID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operate
-- ----------------------------
INSERT INTO `operate` VALUES (2, 1);

-- ----------------------------
-- Table structure for paidattraction
-- ----------------------------
DROP TABLE IF EXISTS `paidattraction`;
CREATE TABLE `paidattraction`  (
  `AttractionID` int(0) NOT NULL,
  `VisitorLimit` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`AttractionID`) USING BTREE,
  CONSTRAINT `paidattraction_ibfk_1` FOREIGN KEY (`AttractionID`) REFERENCES `attraction` (`AttractionID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paidattraction
-- ----------------------------
INSERT INTO `paidattraction` VALUES (1, 100000);
INSERT INTO `paidattraction` VALUES (2, 100000);
INSERT INTO `paidattraction` VALUES (5, 100000);
INSERT INTO `paidattraction` VALUES (6, 100000);
INSERT INTO `paidattraction` VALUES (7, 100000);
INSERT INTO `paidattraction` VALUES (8, 100000);
INSERT INTO `paidattraction` VALUES (10, 100000);
INSERT INTO `paidattraction` VALUES (11, 100000);
INSERT INTO `paidattraction` VALUES (12, 100000);
INSERT INTO `paidattraction` VALUES (13, 100000);

-- ----------------------------
-- Table structure for responsible
-- ----------------------------
DROP TABLE IF EXISTS `responsible`;
CREATE TABLE `responsible`  (
  `AttractionID` int(0) NOT NULL,
  `EmployeeID` int(0) NOT NULL,
  PRIMARY KEY (`AttractionID`, `EmployeeID`) USING BTREE,
  INDEX `responsible_ibfk_2`(`EmployeeID`) USING BTREE,
  CONSTRAINT `responsible_ibfk_1` FOREIGN KEY (`AttractionID`) REFERENCES `attraction` (`AttractionID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `responsible_ibfk_2` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of responsible
-- ----------------------------
INSERT INTO `responsible` VALUES (1, 1);

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket`  (
  `AttractionID` int(0) NOT NULL,
  `Type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`AttractionID`, `Type`) USING BTREE,
  CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`AttractionID`) REFERENCES `attraction` (`AttractionID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES (1, '儿童', 30.00);
INSERT INTO `ticket` VALUES (1, '学生', 30.00);
INSERT INTO `ticket` VALUES (1, '成人', 50.00);
INSERT INTO `ticket` VALUES (2, '儿童', 30.00);
INSERT INTO `ticket` VALUES (2, '学生', 30.00);
INSERT INTO `ticket` VALUES (2, '成人', 50.00);
INSERT INTO `ticket` VALUES (5, '儿童', 30.00);
INSERT INTO `ticket` VALUES (5, '学生', 30.00);
INSERT INTO `ticket` VALUES (5, '成人', 50.00);
INSERT INTO `ticket` VALUES (6, '儿童', 30.00);
INSERT INTO `ticket` VALUES (6, '学生', 30.00);
INSERT INTO `ticket` VALUES (6, '成人', 50.00);
INSERT INTO `ticket` VALUES (7, '儿童', 30.00);
INSERT INTO `ticket` VALUES (7, '学生', 30.00);
INSERT INTO `ticket` VALUES (7, '成人', 50.00);
INSERT INTO `ticket` VALUES (8, '儿童', 30.00);
INSERT INTO `ticket` VALUES (8, '学生', 30.00);
INSERT INTO `ticket` VALUES (8, '成人', 50.00);
INSERT INTO `ticket` VALUES (10, '儿童', 30.00);
INSERT INTO `ticket` VALUES (10, '学生', 30.00);
INSERT INTO `ticket` VALUES (10, '成人', 50.00);
INSERT INTO `ticket` VALUES (11, '儿童', 30.00);
INSERT INTO `ticket` VALUES (11, '学生', 30.00);
INSERT INTO `ticket` VALUES (11, '成人', 50.00);
INSERT INTO `ticket` VALUES (12, '儿童', 30.00);
INSERT INTO `ticket` VALUES (12, '学生', 30.00);
INSERT INTO `ticket` VALUES (12, '成人', 50.00);
INSERT INTO `ticket` VALUES (13, '儿童', 30.00);
INSERT INTO `ticket` VALUES (13, '学生', 30.00);
INSERT INTO `ticket` VALUES (13, '成人', 50.00);

-- ----------------------------
-- Table structure for ticketpurchaserecord
-- ----------------------------
DROP TABLE IF EXISTS `ticketpurchaserecord`;
CREATE TABLE `ticketpurchaserecord`  (
  `AttractionID` int(0) NOT NULL,
  `VisitorID` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `PurchaseTime` datetime(0) NULL,
  `VisitTime` datetime(0) NULL,
  PRIMARY KEY (`AttractionID`, `VisitorID`, `VisitTime`, `PurchaseTime`) USING BTREE,
  INDEX `ticketpurchaserecord_ibfk_2`(`VisitorID`) USING BTREE,
  CONSTRAINT `ticketpurchaserecord_ibfk_1` FOREIGN KEY (`AttractionID`) REFERENCES `attraction` (`AttractionID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ticketpurchaserecord_ibfk_2` FOREIGN KEY (`VisitorID`) REFERENCES `visitor` (`IDCard`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ticketpurchaserecord
-- ----------------------------
INSERT INTO `ticketpurchaserecord` VALUES (1, '52020010103246X', '2024-05-16 00:00:00', '2024-05-16 00:00:00');
INSERT INTO `ticketpurchaserecord` VALUES (1, '52020010103246X', '2024-05-25 02:13:22', '2024-05-29 02:13:13');
INSERT INTO `ticketpurchaserecord` VALUES (2, '52020010103246X', '2024-05-25 02:15:41', '2024-05-25 02:15:39');
INSERT INTO `ticketpurchaserecord` VALUES (1, '52220041213019X', '2024-06-02 06:22:42', '2024-06-02 06:22:41');

-- ----------------------------
-- Table structure for travelcompany
-- ----------------------------
DROP TABLE IF EXISTS `travelcompany`;
CREATE TABLE `travelcompany`  (
  `CompanyID` int(0) NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`CompanyID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travelcompany
-- ----------------------------
INSERT INTO `travelcompany` VALUES (2, '贵州黄果树旅游集团股份有限公司', '贵州省安顺市黄果树旅游区黄果树镇博园路南');

-- ----------------------------
-- Table structure for visitor
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor`  (
  `IDCard` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ContactInfo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`IDCard`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of visitor
-- ----------------------------
INSERT INTO `visitor` VALUES ('52020010103246X', '梅波', '14562213047');
INSERT INTO `visitor` VALUES ('52219990111265X', '孙小', '18566453211');
INSERT INTO `visitor` VALUES ('522200410186521', '钟芬', '18922301567');
INSERT INTO `visitor` VALUES ('52220041213019X', '吴华', '16623127895');

-- ----------------------------
-- View structure for companyattractions
-- ----------------------------
DROP VIEW IF EXISTS `companyattractions`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `companyattractions` AS select `tc`.`CompanyID` AS `CompanyID`,`tc`.`Name` AS `Name`,`tc`.`Address` AS `Address`,`o`.`AttractionID` AS `AttractionID` from (`travelcompany` `tc` join `operate` `o` on((`tc`.`CompanyID` = `o`.`CompanyID`)));

-- ----------------------------
-- View structure for companyinfo
-- ----------------------------
DROP VIEW IF EXISTS `companyinfo`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `companyinfo` AS select `travelcompany`.`CompanyID` AS `CompanyID`,`travelcompany`.`Name` AS `Name`,`travelcompany`.`Address` AS `Address` from `travelcompany`;

-- ----------------------------
-- Procedure structure for InsertAttractionAndUpdateCityDescription
-- ----------------------------
DROP PROCEDURE IF EXISTS `InsertAttractionAndUpdateCityDescription`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAttractionAndUpdateCityDescription`(
    IN p_AttractionID INT,
    IN p_Name VARCHAR(255),
    IN p_Description TEXT,
    IN p_DetailedAddress VARCHAR(255),
    IN p_OpeningHours VARCHAR(255),
    IN p_CityName VARCHAR(255),
    IN p_NewCityDescription VARCHAR(555)
)
BEGIN
    DECLARE cityExists INT;
    
    -- 检查城市是否存在
    SELECT COUNT(*) INTO cityExists
    FROM `city`
    WHERE `Name` = p_CityName;
    
    IF cityExists = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid CityName: The specified city does not exist.';
    ELSE
        -- 插入新的景点
        INSERT INTO `attraction` (`AttractionID`, `Name`, `Description`, `DetailedAddress`, `OpeningHours`, `CityName`)
        VALUES (p_AttractionID, p_Name, p_Description, p_DetailedAddress, p_OpeningHours, p_CityName);
        
        -- 更新城市的描述
        UPDATE `city`
        SET `Description` = p_NewCityDescription
        WHERE `Name` = p_CityName;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for updateAttraction
-- ----------------------------
DROP PROCEDURE IF EXISTS `updateAttraction`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAttraction`(
    IN p_attractionID INT,
    IN p_name VARCHAR(255),
    IN p_detailedAddress VARCHAR(255),
    IN p_openingHours VARCHAR(255),
    IN p_cityName VARCHAR(255),
    IN p_reservationRequired VARCHAR(255),
    IN p_visitorLimit INT
)
BEGIN
    UPDATE Attraction
    SET Name = p_name,
        DetailedAddress = p_detailedAddress,
        OpeningHours = p_openingHours,
        CityName = p_cityName
    WHERE AttractionID = p_attractionID;

    IF p_reservationRequired = '是' THEN
        UPDATE freeattraction
        SET ReservationRequired = TRUE,
            VisitorLimit = p_visitorLimit
        WHERE AttractionID = p_attractionID;
    ELSEIF p_reservationRequired = '否' THEN
        UPDATE freeattraction
        SET ReservationRequired = FALSE,
            VisitorLimit = p_visitorLimit
        WHERE AttractionID = p_attractionID;
    ELSE
        UPDATE paidattraction
        SET VisitorLimit = p_visitorLimit
        WHERE AttractionID = p_attractionID;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for UpdateAttractionInfo
-- ----------------------------
DROP PROCEDURE IF EXISTS `UpdateAttractionInfo`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateAttractionInfo`(
    IN p_AttractionID INT,
    IN p_Name VARCHAR(255),
    IN p_Description TEXT,
    IN p_DetailedAddress VARCHAR(255),
    IN p_OpeningHours VARCHAR(255),
    IN p_CityName VARCHAR(255),
    IN p_FreeReservationRequired TINYINT(1),
    IN p_FreeVisitorLimit INT,
    IN p_PaidVisitorLimit INT,
    IN p_UpdateFreeAttraction TINYINT(1), -- 1: Update freeattraction, 0: Don't update
    IN p_UpdatePaidAttraction TINYINT(1)  -- 1: Update paidattraction, 0: Don't update
)
BEGIN
    -- Update attraction table
    UPDATE `attraction`
    SET
        `Name` = p_Name,
        `Description` = p_Description,
        `DetailedAddress` = p_DetailedAddress,
        `OpeningHours` = p_OpeningHours,
        `CityName` = p_CityName
    WHERE
        `AttractionID` = p_AttractionID;

    -- Conditionally update freeattraction table if p_UpdateFreeAttraction is 1
    IF p_UpdateFreeAttraction = 1 THEN
        UPDATE `freeattraction`
        SET
            `ReservationRequired` = p_FreeReservationRequired,
            `VisitorLimit` = p_FreeVisitorLimit
        WHERE
            `AttractionID` = p_AttractionID;
    END IF;

    -- Conditionally update paidattraction table if p_UpdatePaidAttraction is 1
    IF p_UpdatePaidAttraction = 1 THEN
        UPDATE `paidattraction`
        SET
            `VisitorLimit` = p_PaidVisitorLimit
        WHERE
            `AttractionID` = p_AttractionID;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for UpdateCityDescription
-- ----------------------------
DROP PROCEDURE IF EXISTS `UpdateCityDescription`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateCityDescription`(IN p_CityName VARCHAR(255), IN p_NewDescription VARCHAR(555))
BEGIN
  -- Declare a variable to store the attraction's city name
  DECLARE v_CityName VARCHAR(255);

  -- Get the city name from the attraction table
  SELECT CityName INTO v_CityName
  FROM attraction
  WHERE CityName = p_CityName
  LIMIT 1;

  -- Update the city's description in the city table if the city exists
  IF v_CityName IS NOT NULL THEN
    UPDATE city
    SET Description = p_NewDescription
    WHERE Name = v_CityName;
  END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for UpdateCompanyID
-- ----------------------------
DROP PROCEDURE IF EXISTS `UpdateCompanyID`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateCompanyID`(IN oldCompanyID INT, IN newCompanyID INT)
BEGIN
    -- Step 1: Update the operate table to reflect the new CompanyID
    UPDATE operate
    SET CompanyID = newCompanyID
    WHERE CompanyID = oldCompanyID;
    
    -- Step 2: Update the travelcompany table to the new CompanyID
    UPDATE travelcompany
    SET CompanyID = newCompanyID
    WHERE CompanyID = oldCompanyID;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for UpdateEmployee
-- ----------------------------
DROP PROCEDURE IF EXISTS `UpdateEmployee`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateEmployee`(
    IN p_EmployeeID INT,
    IN p_IDCard VARCHAR(18),
    IN p_Name VARCHAR(255),
    IN p_Position VARCHAR(255),
    IN p_WorkHours VARCHAR(255),
    IN p_ContactInfo VARCHAR(255)
)
BEGIN
    UPDATE employee
    SET 
        Name = p_Name,
        Position = p_Position,
        WorkHours = p_WorkHours,
        ContactInfo = p_ContactInfo
    WHERE 
        EmployeeID = p_EmployeeID AND
        IDCard = p_IDCard;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
