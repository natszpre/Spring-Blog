CREATE DATABASE IF NOT EXISTS blogdb;
GRANT ALL PRIVILEGES ON blogdb.* TO natalia@localhost IDENTIFIED BY 'natalia';

USE blogdb;

CREATE TABLE `post` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `user` varchar(50) DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL,
  `content` text,
  `date` datetime DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 

CREATE TABLE `post` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `user` varchar(50) DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL,
  `content` text,
  `date` datetime DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 