
-- ----------------------------
--  Table structure for `music`
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '',
  `author` varchar(32) NOT NULL DEFAULT '',
  `size` varchar(12) NOT NULL DEFAULT '',
  `category` tinyint(4) NOT NULL DEFAULT '0',
  `collects` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `music_collect`
-- ----------------------------
DROP TABLE IF EXISTS `music_collect`;
CREATE TABLE `music_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ct` int(11) NOT NULL DEFAULT '0',
  `music_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(32) NOT NULL DEFAULT '',
  `author` varchar(32) NOT NULL DEFAULT '',
  `size` varchar(12) NOT NULL DEFAULT '',
  `category` tinyint(4) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ct` int(11) NOT NULL DEFAULT '0',
  `ut` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `phone` varchar(60) NOT NULL DEFAULT '',
  `password` varchar(60) NOT NULL DEFAULT '',
  `is_temple` tinyint(4) NOT NULL DEFAULT '0',
  `nick` varchar(60) NOT NULL DEFAULT '',
  `portrait` varchar(60) NOT NULL DEFAULT '',
  `cover` varchar(60) NOT NULL DEFAULT '',
  `description` varchar(60) NOT NULL DEFAULT '',
  `level_name` varchar(60) NOT NULL DEFAULT '',
  `level` int(11) NOT NULL DEFAULT '0',
  `score` int(11) NOT NULL DEFAULT '0',
  `merits` int(11) NOT NULL DEFAULT '0',
  `pray_num` bigint(20) NOT NULL DEFAULT '0',
  `client_id` varchar(60) NOT NULL DEFAULT '',
  `union_id` varchar(60) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `music`
-- ----------------------------
INSERT INTO music(name,author,size,category) VALUES 
('醒来','艾米丽·王嘉宝','10mb',10),
('八圣吉祥颂','','10mb',10),
('观世音菩萨','陈星','10mb',10),
('江月初照人','陈悦','10mb',10),
('佛教音乐-醒来(童音版)','','10mb',10),
('I Remember','郭采洁','10mb',10),
('见或不见','何晟铭','10mb',10),
('八十八佛','何禹萱','10mb',10),
('香巴拉并不遥远','黑鸭子','10mb',10),
('情仇爱恨','华语群星','10mb',10),
('忏悔文','黄慧音','10mb',10),
('七朵莲花','霍尊','10mb',10),
('上师久住祈祷文','敬善媛','10mb',10),
('寻找生命中的贵人','敬善媛','10mb',10),
('赞佛偈','敬善媛','10mb',10),
('半壶纱','刘珂矣','10mb',10),
('芙蓉雨','刘珂矣','10mb',10),
('忘尘谷','刘珂矣','10mb',10),
('阿弥陀佛','路勇','10mb',10),
('放手也是一种幸福','路勇','10mb',10),
('菩提树下','路勇','10mb',10),
('我佛慈悲','路勇','10mb',10),
('一切都是最好的安排','路勇','10mb',10),
('愿做菩萨那朵莲','路勇','10mb',10),
('仓央嘉措情歌','群星','10mb',10),
('聆听','群星','10mb',10),
('万物生','萨顶顶','10mb',10),
('佛说','桑吉平措','10mb',10),
('观音菩萨悲赞','桑吉平措','10mb',10),
('禅境','桑吉平措','10mb',10),
('禅韵','桑吉平措','10mb',10),
('相见','桑吉平措','10mb',10),
('劝世因果歌','上官萍','10mb',10),
('青莲','陶莹','10mb',10),
('阿弥陀佛在心间','小娟&山谷里的居民','10mb',10),
('回旋曲','小娟&山谷里的居民','10mb',10),
('如果','小娟&山谷里的居民','10mb',10),
('微风往事','小娟&山谷里的居民','10mb',10),
('微光中的歌吟','小娟&山谷里的居民','10mb',10),
('一窗清响','小娟&山谷里的居民','10mb',10),
('佛陀的爱','新加坡净宗学会','10mb',10),
('放下','印良法师','10mb',10),
('礼赞释迦牟尼佛','印良法师','10mb',10);
INSERT INTO music(name,author,size,category) VALUES 
('佛教音乐-百字明咒','王菲','12mb',30),
('佛教音乐-佛顶尊胜陀罗尼','宝锋居士','12mb',30),
('佛教音乐-佛顶尊胜陀罗尼(瑜伽燄口-尊胜咒)','test','12mb',30),
('佛教音乐-怀业祈祷文-大加持云','test','12mb',30),
('佛教音乐-楞严咒','海城大悲寺僧众念诵','12mb',30),
('佛教音乐-绿度母心咒','test','12mb',30),
('佛教音乐-消災吉祥神咒','test','12mb',30),
('佛教音乐-心经','王菲(王菲第三界世界佛教论坛)','12mb',30),
('佛教音乐-药师灌顶真言','test','12mb',30),
('汉传大悲咒-最好听的大悲咒','test','12mb',30),
('六字大明咒','黑鸭子','12mb',30),
('般若心咒 (乡谣版)','马常胜','12mb',30),
('大悲咒','马常胜','12mb',30),
('金刚萨埵百字明咒','马常胜','12mb',30),
('莲师除障文','马常胜','12mb',30),
('文殊菩萨心咒','马常胜','12mb',30),
('绿度母心咒','桑吉平措','12mb',30),
('药王度母心咒','桑吉平措','12mb',30),
('十一面观音根本咒','test','12mb',30),
('心经梵唱','test','12mb',30),
('黄财神心咒','祖古·白玛奥色上师','12mb',30),
('故郷の原风景上师 - 大悲咒','祖古·白玛奥色宗次郎','12mb',30);
dddd