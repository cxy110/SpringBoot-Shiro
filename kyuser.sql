/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.5.61-MariaDB : Database - kyuser
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kyuser` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kyuser`;

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `created_user` varchar(50) DEFAULT NULL,
  `modified_user` varchar(50) DEFAULT NULL,
  `permission` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `roles` */

insert  into `roles`(`id`,`name`,`created_time`,`modified_time`,`created_user`,`modified_user`,`permission`) values 
(1,'管理员',NULL,NULL,NULL,NULL,'user:free'),
(2,'群主',NULL,NULL,NULL,NULL,'user:login');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL,
  `valid` tinyint(4) DEFAULT NULL,
  `lastlogin` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`email`,`phone`,`created_time`,`modified_time`,`salt`,`valid`,`lastlogin`) values 
(1,'admin','12345',NULL,NULL,NULL,NULL,NULL,1,NULL),
(2,'aa','12345',NULL,NULL,NULL,NULL,NULL,2,NULL),
(3,'12345','6abebdfdca07899fbd010cbdcbeb363f',NULL,NULL,'2019-05-08 11:03:15',NULL,NULL,1,NULL),
(6,'admin','827ccb0eea8a706c4c34a16891f84e7b',NULL,NULL,'2019-05-08 14:53:37','2019-05-08 14:53:37',NULL,1,NULL),
(7,'cxy','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,'2019-05-08 15:21:19','2019-05-08 15:21:19',NULL,1,NULL),
(8,'cxY','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,'2019-05-08 15:32:31','2019-05-08 15:32:31',NULL,1,NULL),
(9,'bb','827ccb0eea8a706c4c34a16891f84e7b',NULL,NULL,'2019-05-08 15:43:52','2019-05-08 15:43:52',NULL,1,NULL),
(10,'123','202cb962ac59075b964b07152d234b70',NULL,NULL,'2019-05-08 15:48:15','2019-05-08 15:48:15',NULL,1,NULL),
(11,'cc','c20ad4d76fe97759aa27a0c99bff6710',NULL,NULL,'2019-05-08 15:50:30','2019-05-08 15:50:30',NULL,1,NULL),
(13,'asd','76d80224611fc919a5d54f0ff9fba446',NULL,NULL,'2019-05-08 16:01:21','2019-05-08 16:01:21',NULL,1,'2019-05-14 18:21:24'),
(14,'qq','099b3b060154898840f0ebdfb46ec78f',NULL,NULL,'2019-05-09 16:09:52','2019-05-09 16:09:52',NULL,1,NULL),
(16,'zzz','cf67355a3333e6e143439161adc2d82e',NULL,NULL,'2019-05-13 11:54:48','2019-05-13 11:54:48',NULL,1,'2019-05-13 11:55:22');

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `user_roles` */

insert  into `user_roles`(`id`,`user_id`,`role_id`) values 
(1,1,2),
(2,2,1),
(6,NULL,1),
(7,NULL,1),
(8,NULL,1),
(9,NULL,1),
(10,NULL,1),
(11,12,1),
(12,13,1),
(13,14,1),
(15,16,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
