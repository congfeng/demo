
-- ----------------------------
--  Table structure for `music`
-- ----------------------------
CREATE TABLE `music` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `author` varchar(32) NOT NULL DEFAULT '',
  `filename` varchar(256) NOT NULL DEFAULT '',
  `filesize` varchar(12) NOT NULL DEFAULT '',
  `soundsize` varchar(12) NOT NULL DEFAULT '',
  `category` tinyint(4) NOT NULL DEFAULT '0',
  `collects` int(11) NOT NULL DEFAULT '0',
  `plays` int(11) NOT NULL DEFAULT '0',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `c_t` int(11) NOT NULL DEFAULT '0',
  `u_t` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='音乐明细';

-- ----------------------------
--  Table structure for `music_collect`
-- ----------------------------
CREATE TABLE `music_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ct` int(11) NOT NULL DEFAULT '0',
  `music_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(32) NOT NULL DEFAULT '',
  `author` varchar(32) NOT NULL DEFAULT '',
  `filesize` varchar(12) NOT NULL DEFAULT '',
  `soundsize` varchar(12) NOT NULL DEFAULT '',
  `category` tinyint(4) NOT NULL DEFAULT '0',
  `filename` varchar(256) NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `c_t` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='音乐收藏';

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
--  Table structure for `operation_log`
-- ----------------------------
CREATE TABLE `operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `user_name` varchar(32) NOT NULL DEFAULT '',
  `related_no` varchar(32) NOT NULL DEFAULT '',
  `related_name` varchar(64) NOT NULL DEFAULT '',
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `time` int(11) NOT NULL DEFAULT '0',
  `desc` varchar(256) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- ----------------------------
--  Records of `music`
-- ----------------------------
INSERT INTO music(name,author,filename,filesize,category) VALUES 
('醒来','艾米丽·王嘉宝','艾米丽·王嘉宝 - 醒来.mp3','5.57MB',10),
('八圣吉祥颂','未知','八圣吉祥颂.mp3','178.22KB',10),
('观世音菩萨','陈星','陈星 - 观世音菩萨.mp3','4.65MB',10),
('江月初照人','陈悦','陈悦 - 江月初照人.mp3','12.51MB',10),
('醒来(童音版)','未知','佛教音乐-醒来(童音版).mp3','4.56MB',10),
('I Remember','郭采洁','郭采洁 - I Remember.mp3','8.34MB',10),
('见或不见','何晟铭','何晟铭 - 见或不见.mp3','7.92MB',10),
('八十八佛','何禹萱','何禹萱 - 八十八佛.mp3','8.41MB',10),
('香巴拉并不遥远','黑鸭子','黑鸭子 - 香巴拉并不遥远.mp3','10.74MB',10),
('情仇爱恨','华语群星','华语群星 - 情仇爱恨.mp3','4.71MB',10),
('忏悔文','黄慧音','黄慧音 - 忏悔文.mp3','18.36MB',10),
('七朵莲花','霍尊','霍尊-七朵莲花.mp3','2.99MB',10),
('上师久住祈祷文','敬善媛','敬善媛-上师久住祈祷文.mp3','1.41MB',10),
('寻找生命中的贵人','敬善媛','敬善媛-寻找生命中的贵人.mp3','1.79MB',10),
('赞佛偈','敬善媛','敬善媛-赞佛偈.mp3','5.90MB',10),
('半壶纱','刘珂矣','刘珂矣 - 半壶纱.mp3','3.46MB',10),
('芙蓉雨','刘珂矣','刘珂矣 - 芙蓉雨.mp3','9.50MB',10),
('忘尘谷','刘珂矣','刘珂矣 - 忘尘谷.mp3','8.70MB',10),
('阿弥陀佛','路勇','路勇-阿弥陀佛.mp3','3.34MB',10),
('放手也是一种幸福','路勇','路勇-放手也是一种幸福.mp3','3.99MB',10),
('菩提树下','路勇','路勇-菩提树下.mp3','3.66MB',10),
('我佛慈悲','路勇','路勇-我佛慈悲.mp3','3.51MB',10),
('一切都是最好的安排','路勇','路勇-一切都是最好的安排.mp3','3.19MB',10),
('愿做菩萨那朵莲','路勇','路勇-愿做菩萨那朵莲.mp3','3.71MB',10),
('仓央嘉措情歌 (二胡_古筝_埙_笛)','群星','群星 - 仓央嘉措情歌 (二胡_古筝_埙_笛).mp3','16.18MB',10),
('聆听','群星','群星 - 聆听.mp3','10.63MB',10),
('忏悔发愿文','如是我闻','如是我闻 - 忏悔发愿文.mp3','12.94MB',10),
('万物生','萨顶顶','萨顶顶 - 万物生 (其他).mp3','10.66MB',10),
('佛说','桑吉平措','桑吉平措 - 佛说.mp3','9.48MB',10),
('观音菩萨悲赞','桑吉平措','桑吉平措 - 观音菩萨悲赞.mp3','3.45MB',10),
('禅境','桑吉平措','桑吉平措-禅境.mp3','4.24MB',10),
('禅韵','桑吉平措','桑吉平措-禅韵.mp3','3.21MB',10),
('相见','桑吉平措','桑吉平措-相见.mp3','3.54MB',10),
('劝世因果歌','上官萍','上官萍 - 劝世因果歌.mp3','9.09MB',10),
('青莲','陶莹','陶莹-青莲.mp3','6.06MB',10),
('阿弥陀佛在心间','小娟&山谷里的居民','小娟 & 山谷里的居民 - 阿弥陀佛在心间.mp3','8.51MB',10),
('回旋曲','小娟&山谷里的居民','小娟 & 山谷里的居民 - 回旋曲.mp3','10.04MB',10),
('如果','小娟&山谷里的居民','小娟 & 山谷里的居民 - 如果.mp3','9.85MB',10),
('微风往事','小娟&山谷里的居民','小娟 & 山谷里的居民 - 微风往事.mp3','12.02MB',10),
('微光中的歌吟','小娟&山谷里的居民','小娟 & 山谷里的居民 - 微光中的歌吟.mp3','10.18MB',10),
('一窗清响','小娟&山谷里的居民','小娟 & 山谷里的居民 - 一窗清响.mp3','10.97MB',10),
('佛陀的爱','新加坡净宗学会','新加坡净宗学会 - 佛陀的爱.mp3','8.49MB',10),
('放下','印良法师','印良法师 - 放下.mp3','9.17MB',10),
('礼赞释迦牟尼佛','印良法师','印能法师-礼赞释迦牟尼佛.mp3','4.53MB',10);
INSERT INTO music(name,author,filename,filesize,category) VALUES 
('百字明咒','王菲','佛教音乐-百字明咒(王菲).mp3','725.49KB',30),
('佛顶尊胜陀罗尼(宝锋居士 唱颂版)','宝锋居士','佛教音乐-佛顶尊胜陀罗尼(宝锋居士 唱颂版).mp3','4.76MB',30),
('佛顶尊胜陀罗尼(瑜伽燄口-尊胜咒)','未知','佛教音乐-佛顶尊胜陀罗尼(瑜伽燄口-尊胜咒).mp3','1.28MB',30),
('怀业祈祷文-大加持云','未知','佛教音乐-怀业祈祷文-大加持云.mp3','1.10MB',30),
('楞严咒','海城大悲寺僧众','佛教音乐-楞严咒(海城大悲寺僧众念诵).mp3','5.94MB',30),
('绿度母心咒','未知','佛教音乐-绿度母心咒.mp3','4.04MB',30),
('消災吉祥神咒','未知','佛教音乐-消災吉祥神咒.mp3','10.86MB',30),
('心经(王菲第三界世界佛教论坛)','王菲','佛教音乐-心经(王菲第三界世界佛教论坛).mp3','5.15MB',30),
('药师灌顶真言','未知','佛教音乐-药师灌顶真言.mp3','9.28MB',30),
('汉传大悲咒-最好听的大悲咒','未知','汉传大悲咒-最好听的大悲咒.mp3','4.67MB',30),
('六字大明咒','黑鸭子','黑鸭子 - 六字大明咒.mp3','5.78MB',30),
('般若心咒 (乡谣版)','马常胜','马常胜 - 般若心咒 (乡谣版).mp3','9.04MB',30),
('大悲咒','马常胜','马常胜 - 大悲咒.mp3','10.23MB',30),
('金刚萨埵百字明咒','马常胜','马常胜 - 金刚萨埵百字明咒.mp3','12.67MB',30),
('莲师除障文','马常胜','马常胜 - 莲师除障文.mp3','13.45MB',30),
('文殊菩萨心咒','马常胜','马常胜 - 文殊菩萨心咒.mp3','13.46MB',30),
('绿度母心咒','桑吉平措','桑吉平措 - 绿度母心咒.mp3','10.04MB',30),
('药王度母心咒','桑吉平措','桑吉平措 - 药王度母心咒.mp3','4.51MB',30),
('十一面观音根本咒','未知','十一面观音根本咒.mp3','5.75MB',30),
('心经梵唱','未知','心经梵唱.mp3','4.22MB',30),
('黄财神心咒','祖古·白玛奥色上师','祖古·白玛奥色上师 - 黄财神心咒.mp3','8.30MB',30),
('故郷の原风景上师 - 大悲咒','祖古·白玛奥色宗次郎','祖古·白玛奥色宗次郎 (のむら そうじろう) - 故郷の原风景上师 - 大悲咒.mp3','10.55MB',30);
INSERT INTO music(name,author,filename,filesize,category) VALUES 
('拜愿','佛光山梵呗团','佛光山梵呗团 - 拜愿.mp3','50.08MB',40),
('戒定真香赞','佛光山梵呗团','佛光山梵呗团 - 戒定真香赞.mp3','7.26MB',40),
('炉香赞','佛光山梵呗团','佛光山梵呗团 - 炉香赞.mp3','16.75MB',40),
('三皈依','佛光山梵呗团','佛光山梵呗团 - 三皈依.mp3','8.93MB',40),
('宝鼎赞 佛光山','未知','佛教音乐 - 宝鼎赞 佛光山.mp3','938.00KB',40),
('晨钟偈','未知','佛教音乐 - 晨钟偈.mp3','6.16MB',40),
('大自在摄受祈祷文-大加持云','华语群星','华语群星 - 大自在摄受祈祷文-大加持云.mp3','490.84KB',40),
('三皈依','黄慧音','黄慧音 - 三皈依.mp3','8.93MB',40),
('飞天吉祥 (赞佛偈)','敬善媛','敬善媛 - 飞天吉祥 (赞佛偈).mp3','14.94MB',40),
('空行母·吉尊玛住世祈请颂','敬善媛','敬善媛 - 空行母·吉尊玛住世祈请颂.mp3','8.71MB',40),
('祈祷文','兰卡措','兰卡措 - 祈祷文.mp3','5.03MB',40),
('大自在祈祷文 - 清唱版','日嘎上师','日嘎上师 - 大自在祈祷文 - 清唱版.mp3','1.26MB',40),
('炉香赞','如是我闻','如是我闻 - 炉香赞.mp3','11.54MB',40),
('弥陀赞','如是我闻','如是我闻 - 弥陀赞.mp3','15.37MB',40),
('南无千华台上盧舍那佛','如是我闻','如是我闻 - 南无千华台上盧舍那佛.mp3','6.83MB',40),
('三皈依回向偈','如是我闻','如是我闻 - 三皈依回向偈.mp3','3.27MB',40),
('药师佛拜愿','如是我闻','如是我闻 - 药师佛拜愿.mp3','19.44MB',40),
('赞佛偈','如是我闻','如是我闻 - 赞佛偈.mp3','11.75MB',40),
('文殊祈请文、心咒、回向','萨迦法王','萨迦法王 - 文殊祈请文、心咒、回向.mp3','4.69MB',40),
('浴佛偈颂','耀一法师','耀一法师 - 浴佛偈颂.mp3','14.60MB',40),
('妙音天女赞','印能法师','印能法师 - 妙音天女赞.mp3','8.52MB',40),
('大自在祈祷文','扎西俄热仁波切','扎西俄热仁波切 - 大自在祈祷文.mp3','5.88MB',40);
INSERT INTO music(name,author,filename,filesize,category) VALUES 
('阳关三叠','未知','《阳关三叠》.mp3','4.44MB',50),
('瑜伽-宁静的美妙音乐','阿黛之声','阿黛之声-瑜伽-宁静的美妙音乐.mp3','1.17MB',50),
('阿弥陀佛四字宏名','未知','阿弥陀佛四字宏名.mp3','4.43MB',50),
('梵音大悲咒','白玛多吉','白玛多吉 - 梵音大悲咒.mp3','10.55MB',50),
('观音十法 贝诺法王口传','贝诺法王','贝诺法王 - 观音十法 贝诺法王口传.mp3','9.92MB',50),
('欸乃','曾成伟','曾成伟 - 欸乃.mp3','16.58MB',50),
('田野静悄悄','朝克吉勒图','朝克吉勒图 - 田野静悄悄.mp3','8.24MB',50),
('乱红 (笛)','陈悦','陈悦 - 乱红 (笛).mp3','12.01MB',50),
('归去来辞','成公亮','成公亮 - 归去来辞.mp3','9.99MB',50),
('良宵引','成公亮','成公亮 - 良宵引.mp3','12.09MB',50),
('忘忧','成公亮','成公亮 - 忘忧.mp3','4.94MB',50),
('纯净的心境','未知','纯音乐 - 纯净的心境.mp3','3.34MB',50),
('风的歌声','未知','纯音乐 - 风的歌声.mp3','8.70MB',50),
('风清云淡','未知','纯音乐 - 风清云淡.mp3','8.93MB',50),
('呼吸','未知','纯音乐 - 呼吸.mp3','8.36MB',50),
('怀古','未知','纯音乐 - 怀古.mp3','13.68MB',50),
('寂静之声','未知','纯音乐 - 寂静之声.mp3','3.17MB',50),
('静海莲心','未知','纯音乐 - 静海莲心.mp3','6.10MB',50),
('梁祝 (古琴)','未知','纯音乐 - 梁祝 (古琴).mp3','7.76MB',50),
('如来云水月瑜伽冥想音乐舒缓减压','未知','纯音乐 - 如来云水月瑜伽冥想音乐舒缓减压 - 纯音乐.mp3','51.88MB',50),
('山中','未知','纯音乐 - 山中.mp3','3.37MB',50),
('水碧霞光','未知','纯音乐 - 水碧霞光.mp3','6.76MB',50),
('天使也灿烂','未知','纯音乐 - 天使也灿烂.mp3','7.14MB',50),
('心的体验','未知','纯音乐 - 心的体验.mp3','5.74MB',50),
('星月池塘','未知','纯音乐 - 星月池塘.mp3','6.02MB',50),
('悠然心','未知','纯音乐 - 悠然心.mp3','7.16MB',50),
('瑜伽冥想音乐','未知','纯音乐 - 瑜伽冥想音乐 - 纯音乐版.mp3','5.51MB',50),
('瑜伽轻音乐','未知','纯音乐 - 瑜伽轻音乐 - 纯音乐版.mp3','3.94MB',50),
('瑜伽音乐古音印度纯音乐','未知','纯音乐 - 瑜伽音乐古音印度纯音乐.mp3','5.91MB',50),
('瑜伽音乐平静的心','未知','纯音乐 - 瑜伽音乐平静的心.mp3','8.69MB',50),
('普庵咒(古琴)','未知','纯音乐-普庵咒(古琴).mp3','7.54MB',50),
('心灵瑜伽音乐集','未知','纯音乐-心灵瑜伽音乐集.mp3','7.36MB',50),
('茶悟聚友','戴亚','戴亚 - 茶悟聚友.mp3','5.66MB',50),
('空','邓伟标','邓伟标 - 空.mp3','9.94MB',50),
('无极','邓伟标','邓伟标 - 无极.mp3','17.50MB',50),
('佛说阿弥陀经','佛光山梵呗团','佛光山梵呗团 - 佛说阿弥陀经.mp3','36.59MB',50),
('妙法莲华经观世音菩萨普门品','佛光山梵呗团','佛光山梵呗团 - 妙法莲华经观世音菩萨普门品.mp3','47.64MB',50),
('大悲咒 - 伽蓝赞 寺院 唱颂版','寺院','佛教音乐 - 大悲咒 - 伽蓝赞 寺院 唱颂版.mp3','6.63MB',50),
('黄财神心咒','希阿荣博堪布','佛教音乐 - 黄财神心咒(希阿荣博堪布).mp3','874.33KB',50),
('消灾吉祥神咒','黄思婷','佛教音乐 - 消灾吉祥神咒 - 佛歌 黄思婷.mp3','4.87MB',50),
('八十八佛忏','未知','佛教音乐-八十八佛忏.mp3','20.73MB',50),
('般若波罗密多心经(梵唱)','未知','佛教音乐-般若波罗密多心经(梵唱).mp3','4.62MB',50),
('茶禅一味','未知','佛教音乐-茶禅一味.mp3','1.90MB',50),
('禅院钟声(古筝)','未知','佛教音乐-禅院钟声(古筝)-佛曲.mp3','2.25MB',50),
('大悲忏2','未知','佛教音乐-大悲忏2.mp3','41.04MB',50),
('法鼓山禅修早课','未知','佛教音乐-法鼓山禅修早课.mp3','32.60MB',50),
('佛顶尊胜陀罗尼(宝锋居士 唱颂版)','宝锋居士','佛教音乐-佛顶尊胜陀罗尼(宝锋居士 唱颂版).mp3','4.76MB',50),
('佛顶尊胜陀罗尼(瑜伽燄口-尊胜咒)','未知','佛教音乐-佛顶尊胜陀罗尼(瑜伽燄口-尊胜咒).mp3','1.28MB',50),
('静心极乐','未知','佛教音乐-静心极乐.mp3','4.54MB',50),
('绿度母心咒','未知','佛教音乐-绿度母心咒.mp3','4.04MB',50),
('普门品念诵','未知','佛教音乐-普门品念诵.mp3','22.44MB',50),
('普贤菩萨行愿品念诵','未知','佛教音乐-普贤菩萨行愿品念诵.mp3','18.57MB',50),
('晚课一','未知','佛教音乐-晚课一.mp3','12.28MB',50),
('心经(王菲第三界世界佛教论坛)','王菲','佛教音乐-心经(王菲第三界世界佛教论坛).mp3','5.15MB',50),
('药师灌顶真言','未知','佛教音乐-药师灌顶真言.mp3','9.28MB',50),
('瑜伽焰口-1','未知','佛教音乐-瑜伽焰口-1.mp3','14.15MB',50),
('瑜伽焰口-3','未知','佛教音乐-瑜伽焰口-3.mp3','12.60MB',50),
('瑜伽焰口-4','未知','佛教音乐-瑜伽焰口-4.mp3','12.43MB',50),
('瑜伽焰口-5','未知','佛教音乐-瑜伽焰口-5.mp3','10.56MB',50),
('瑜伽焰口-6','未知','佛教音乐-瑜伽焰口-6.mp3','8.95MB',50),
('早课二','未知','佛教音乐-早课二.mp3','12.15MB',50),
('佛宝赞 唱颂版','寺院','佛教音乐、2 - 寺院 - 佛宝赞 唱颂版.mp3','15.34MB',50),
('大悲咒','寺院','佛经 - 大悲咒 - 寺院.mp3','13.19MB',50),
('沉默是金','付娜','付娜 - 沉默是金.mp3','10.04MB',50),
('天竺少女','付娜','付娜 - 天竺少女.mp3','10.27MB',50),
('风铃','甘仕良','甘仕良 - 风铃.mp3','10.36MB',50),
('优雅恬静的古典瑜伽音乐','未知','古典音乐-优雅恬静的古典瑜伽音乐.mp3','1.87MB',50),
('关山月','未知','关山月.mp3','1.88MB',50),
('离骚','管平湖','管平湖 - 离骚.mp3','23.86MB',50),
('汉传大悲咒-最好听的大悲咒','未知','汉传大悲咒-最好听的大悲咒.mp3','4.67MB',50),
('六字大明咒','黑鸭子','黑鸭子 - 六字大明咒.mp3','5.78MB',50),
('英雄的黎明','横山菁児','横山菁児 (よこやま せいじ) - 英雄的黎明 (《三国志》动画电影开篇曲).mp3','8.24MB',50),
('梵唱大悲咒 - 古筝版纯音乐','华语群星','华语群星 - 梵唱大悲咒 - 古筝版纯音乐.mp3','23.16MB',50),
('金刚经 佛光山版','未知','金刚经 佛光山版 净口业真言 金刚经 44分钟.mp3','100.73MB',50),
('经文 佛说阿弥陀经','寺院','经文 佛说阿弥陀经 寺院版 唱颂.mp3','11.03MB',50),
('辩才天女心咒','敬善媛','敬善媛 - 辩才天女心咒.mp3','9.30MB',50),
('静虚吟','未知','静虚吟.mp3','1.28MB',50),
('上师长久住世祈请文','堪布贝玛千贝仁波切','堪布贝玛千贝仁波切 - 上师长久住世祈请文.mp3','7.85MB',50),
('高山流水','李炜','李炜(古筝演奏家) - 高山流水.mp3','7.23MB',50),
('渡·红尘','林海','林海 - 渡·红尘.mp3','13.70MB',50),
('阿弥陀佛','路勇','路勇 - 阿弥陀佛.mp3','8.36MB',50),
('般若心咒 (乡谣版)','马常胜','马常胜 - 般若心咒 (乡谣版).mp3','9.04MB',50),
('金刚萨埵百字明咒','马常胜','马常胜 - 金刚萨埵百字明咒.mp3','12.67MB',50),
('莲师除障文','马常胜','马常胜 - 莲师除障文.mp3','13.45MB',50),
('紫莲祥云(禅修静坐音乐)','孟庭苇','孟庭苇-紫莲祥云(禅修静坐音乐).mp3','22.38MB',50),
('爱的祈祷文 - 纯音乐版','南方舍得','南方舍得 - 爱的祈祷文 - 纯音乐版.mp3','14.43MB',50),
('忏悔文','齐豫','齐豫 - 忏悔文.mp3','34.79MB',50),
('大吉祥天女咒','齐豫','齐豫 - 大吉祥天女咒.mp3','33.84MB',50),
('梵音大悲咒','齐豫','齐豫 - 梵音大悲咒.mp3','10.39MB',50),
('灵谷','群星','群星 - 灵谷.mp3','9.69MB',50),
('平沙落雁','群星','群星 - 平沙落雁.mp3','19.70MB',50),
('清尘','群星','群星 - 清尘.mp3','13.32MB',50),
('印度绝美瑜伽音乐','群星','群星 - 印度绝美瑜伽音乐.mp3','4.01MB',50),
('清尘雅琴','群星','群星-清尘雅琴.mp3','5.75MB',50),
('清净心','群星','群星-清净心.mp3','949.13KB',50),
('清凉心','群星','群星-清凉心.mp3','3.83MB',50),
('秋水悠悠 古琴、箫、巴乌','群星','群星-秋水悠悠 古琴、箫、巴乌.mp3','2.51MB',50),
('新加坡净宗学会 清净法身佛 Mqms2','群星','群星-新加坡净宗学会 清净法身佛 Mqms2.mp3','6.50MB',50),
('第一时白文','如是我闻','如是我闻 - 第一时白文.mp3','9.53MB',50),
('白度母心咒','桑吉平措','桑吉平措 - 白度母心咒.mp3','11.92MB',50),
('绿度母心咒','桑吉平措','桑吉平措 - 绿度母心咒.mp3','10.04MB',50),
('释迦摩尼佛心咒','桑吉平措','桑吉平措 - 释迦摩尼佛心咒.mp3','9.08MB',50),
('药王度母心咒','桑吉平措','桑吉平措 - 药王度母心咒.mp3','4.51MB',50),
('十一面观音根本咒','未知','十一面观音根本咒.mp3','5.75MB',50),
('瑜伽练习背景音乐','网络歌手','网络歌手 - 瑜伽练习背景音乐.mp3','3.03MB',50),
('瑜伽练习背景音乐','网络歌手','网络歌手-瑜伽练习背景音乐.mp3','2.28MB',50),
('问道','未知','问道.mp3','4.62MB',50),
('莲心不染','巫娜','巫娜 - 莲心不染.mp3','14.29MB',50),
('清夜弹琴','巫娜','巫娜 - 清夜弹琴.mp3','4.92MB',50),
('四季轮回','巫娜','巫娜 - 四季轮回.mp3','11.85MB',50),
('心法青山','巫娜','巫娜 - 心法青山.mp3','13.01MB',50),
('禅茶一味','巫娜','巫娜-禅茶一味.mp3','4.35MB',50),
('禅定','巫娜','巫娜-禅定.mp3','3.14MB',50),
('古琴禅修','巫娜','巫娜-古琴禅修.mp3','4.92MB',50),
('古琴之约','巫娜','巫娜-古琴之约.mp3','3.47MB',50),
('花开见佛','巫娜','巫娜-花开见佛.mp3','6.01MB',50),
('静水流深','巫娜','巫娜-静水流深.mp3','3.28MB',50),
('莲花处处开','巫娜','巫娜-莲花处处开.mp3','2.75MB',50),
('心游太玄','巫娜','巫娜-心游太玄.mp3','2.46MB',50),
('一念心清净','巫娜','巫娜-一念心清净.mp3','3.29MB',50),
('自在心呼吸','巫娜','巫娜-自在心呼吸.mp3','2.93MB',50),
('无知','未知','无知.mp3','4.68MB',50),
('潇湘水云','吴景略','吴景略 - 潇湘水云.mp3','7.41MB',50),
('舞韵瑜伽 - 静心','未知','舞韵瑜伽 - 静心.mp3','5.16MB',50),
('悟','未知','悟.mp3','4.18MB',50),
('蝶恋花','项斯华','项斯华 - 蝶恋花.mp3','10.69MB',50),
('心经梵唱','未知','心经梵唱.mp3','4.22MB',50),
('清净法身佛','新加坡净宗学会','新加坡净宗学会 - 清净法身佛.mp3','8.71MB',50),
('淮提咒','雨果唱片','雨果唱片 - 淮提咒.mp3','8.93MB',50),
('流水','雨果唱片','雨果唱片 - 流水.mp3','8.52MB',50),
('欧鹭忘机','雨果唱片','雨果唱片 - 欧鹭忘机.mp3','13.02MB',50),
('自然的生灭','张维良','张维良 - 自然的生灭.mp3','17.03MB',50),
('众生','未知','众生.mp3','5.39MB',50),
('Only Wish (惟愿)','CNicholas Cheung','CNicholas Cheung - Only Wish (惟愿).mp3','10.28MB',50),
('第一次','Gary Malkin','Gary Malkin - 第一次.mp3','2.40MB',50),
('莲语的呢喃','Various Artists','Various Artists - 莲语的呢喃.mp3','15.05MB',50),
('柔情莲心','Various Artists','Various Artists - 柔情莲心.mp3','12.14MB',50);
