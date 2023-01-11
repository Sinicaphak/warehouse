/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 8.0.31 : Database - studentstatusmanagement
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`studentstatusmanagement` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `studentstatusmanagement`;

/*Table structure for table `class` */

DROP TABLE IF EXISTS `class`;

CREATE TABLE `class` (
  `class_no` int(4) unsigned zerofill DEFAULT NULL COMMENT '班级编号',
  `student_no` int(9) unsigned zerofill NOT NULL COMMENT '学生学号',
  `admission_time` date DEFAULT NULL COMMENT '入班日期',
  PRIMARY KEY (`student_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `class` */

insert  into `class`(`class_no`,`student_no`,`admission_time`) values 
(0001,000000001,'2023-01-04');

/*Table structure for table `class_information` */

DROP TABLE IF EXISTS `class_information`;

CREATE TABLE `class_information` (
  `class_no` int(4) unsigned zerofill NOT NULL COMMENT '班级编号',
  `grade_no` int(1) unsigned zerofill DEFAULT NULL COMMENT '年级编号',
  `collage_name` varchar(30) DEFAULT NULL COMMENT '学院名称',
  PRIMARY KEY (`class_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `class_information` */

insert  into `class_information`(`class_no`,`grade_no`,`collage_name`) values 
(0001,1,'物理与信息工程学院');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_no` int(9) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '学生学号',
  `student_sexual` varchar(3) DEFAULT NULL COMMENT '学生性别',
  `class_no` int(4) unsigned zerofill DEFAULT NULL COMMENT '班级编号',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `student_name` varchar(20) DEFAULT NULL COMMENT '学生姓名',
  PRIMARY KEY (`student_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

/*Data for the table `student` */

insert  into `student`(`student_no`,`student_sexual`,`class_no`,`phone`,`student_name`) values 
(000000001,'男',0001,'17712345678','小黄');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
