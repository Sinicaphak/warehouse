/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 8.0.31 : Database - winter_vacation
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`winter_vacation` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `winter_vacation`;

/*Table structure for table `download_history` */

DROP TABLE IF EXISTS `download_history`;

CREATE TABLE `download_history` (
  `download_history_id` int NOT NULL COMMENT '下载记录id',
  `rid` int DEFAULT NULL COMMENT 'rid',
  `user_id` int DEFAULT NULL COMMENT '下载用户id',
  `fav` int DEFAULT NULL COMMENT '是否收藏',
  PRIMARY KEY (`download_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `download_history` */

/*Table structure for table `search_history` */

DROP TABLE IF EXISTS `search_history`;

CREATE TABLE `search_history` (
  `search_history_id` int NOT NULL COMMENT '搜索记录id',
  `rid` int DEFAULT NULL COMMENT 'rid',
  `user_id` int DEFAULT NULL COMMENT '搜索用户id',
  PRIMARY KEY (`search_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `search_history` */

/*Table structure for table `song` */

DROP TABLE IF EXISTS `song`;

CREATE TABLE `song` (
  `song_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '歌曲名称',
  `artist` varchar(30) CHARACTER SET utf8mb3 DEFAULT NULL COMMENT '作者',
  `album` varchar(30) CHARACTER SET utf8mb3 DEFAULT NULL COMMENT '专辑',
  `duration` varchar(8) CHARACTER SET utf8mb3 DEFAULT NULL COMMENT '歌曲时长',
  `rid` int NOT NULL COMMENT 'rid',
  `url` varchar(200) CHARACTER SET utf8mb3 DEFAULT NULL COMMENT '直链地址',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `song` */

insert  into `song`(`song_name`,`artist`,`album`,`duration`,`rid`,`url`) values 
('我只在乎你','邓丽君','我只在乎你','04:14',57935,'https://link.hhtjim.com/kw/57935.mp3'),
('春泥','庾澄庆','哈林天堂','04:16',79270,'https://link.hhtjim.com/kw/79270.mp3'),
('十年','陈奕迅','黑白灰','03:25',80403,'https://link.hhtjim.com/kw/80403.mp3'),
('记事本','陈慧琳','闪亮每一天Shining 新歌 + 精选','04:07',86371,'https://link.hhtjim.com/kw/86371.mp3'),
('处处吻','杨千嬅','电光幻影','03:19',93890,'https://link.hhtjim.com/kw/93890.mp3'),
('偏偏喜欢你','陈百强','偏偏喜欢你','03:30',142801,'https://link.hhtjim.com/kw/142801.mp3'),
('谢谢你的爱1999','谢霆锋','谢谢你的爱1999','03:27',153883,'https://link.hhtjim.com/kw/153883.mp3'),
('吻别','张学友','吻别','05:06',157908,'https://link.hhtjim.com/kw/157908.mp3'),
('最熟悉的陌生人','萧亚轩','萧亚轩 同名专辑','04:23',216877,'https://link.hhtjim.com/kw/216877.mp3'),
('用心良苦','张宇','被遗忘的时光2-老情歌','04:36',408047,'https://link.hhtjim.com/kw/408047.mp3'),
('淡淡的情愁','张小英','','03:17',505717,'https://link.hhtjim.com/kw/505717.mp3'),
('昨夜梦魂中','石小倩','献出一份爱','03:45',528008,'https://link.hhtjim.com/kw/528008.mp3'),
('梦醒时分','陈淑桦','有时想念','04:03',572900,'https://link.hhtjim.com/kw/572900.mp3'),
('酷爱','张敬轩','酷爱','03:54',631041,'https://link.hhtjim.com/kw/631041.mp3'),
('太傻','巫启贤','心酸的情歌 (广东大碟)','05:20',845672,'https://link.hhtjim.com/kw/845672.mp3'),
('美酒加咖啡','高胜美','探戈名曲 [雷射金曲16]','02:50',1025753,'https://link.hhtjim.com/kw/1025753.mp3'),
('我用自己的方式爱你','陈明真','再度相逢','04:49',1027831,'https://link.hhtjim.com/kw/1027831.mp3'),
('千千阙歌','陈慧娴','百花齐放','04:57',16994198,'https://link.hhtjim.com/kw/16994198.mp3'),
('偏偏喜欢你','陈百强','世纪10星 永恒篇','03:30',20172751,'https://link.hhtjim.com/kw/20172751.mp3'),
('来生缘','刘德华','来生缘','03:56',45851778,'https://link.hhtjim.com/kw/45851778.mp3');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(7) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(30) CHARACTER SET utf8mb3 NOT NULL COMMENT '用户名',
  `user_password` varchar(30) CHARACTER SET utf8mb3 NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`user_id`,`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`user_password`) values 
(0000001,'admin','123456');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
