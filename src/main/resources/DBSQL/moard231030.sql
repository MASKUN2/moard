-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.22-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- moard 데이터베이스 구조 내보내기
DROP DATABASE IF EXISTS `moard`;
CREATE DATABASE IF NOT EXISTS `moard` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci */;
USE `moard`;

-- 테이블 moard.accounts 구조 내보내기
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE IF NOT EXISTS `accounts` (
  `account_id` varchar(20) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `account_pw` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `profile_picture` tinytext DEFAULT NULL,
  `about_me` tinytext DEFAULT NULL,
  `role` enum('user','manager') NOT NULL DEFAULT 'user',
  `status` enum('enable','disable') NOT NULL DEFAULT 'enable',
  `createdate` datetime NOT NULL,
  `updatedate` datetime NOT NULL,
  PRIMARY KEY (`account_id`) USING BTREE,
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 테이블 데이터 moard.accounts:~1 rows (대략적) 내보내기
DELETE FROM `accounts`;
INSERT INTO `accounts` (`account_id`, `nickname`, `account_pw`, `email`, `profile_picture`, `about_me`, `role`, `status`, `createdate`, `updatedate`) VALUES
	('test_user', 'test_nikname', '*A4B6157319038724E3560894F7F932C8886EBFCF', 'test@email.com', NULL, NULL, 'user', 'enable', '2023-10-29 14:31:27', '2023-10-29 14:31:27');

-- 테이블 moard.tech_forum_posts 구조 내보내기
DROP TABLE IF EXISTS `tech_forum_posts`;
CREATE TABLE IF NOT EXISTS `tech_forum_posts` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(20) NOT NULL,
  `title` tinytext NOT NULL,
  `content` text NOT NULL,
  `views` bigint(20) NOT NULL DEFAULT 0,
  `likes` bigint(20) NOT NULL DEFAULT 0,
  `dislikes` bigint(20) NOT NULL DEFAULT 0,
  `createdate` datetime NOT NULL,
  `updatedate` datetime NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FK_tech_forum_posts_accounts` (`account_id`),
  CONSTRAINT `FK_tech_forum_posts_accounts` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 테이블 데이터 moard.tech_forum_posts:~0 rows (대략적) 내보내기
DELETE FROM `tech_forum_posts`;

-- 테이블 moard.test 구조 내보내기
DROP TABLE IF EXISTS `test`;
CREATE TABLE IF NOT EXISTS `test` (
  `id` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 테이블 데이터 moard.test:~6 rows (대략적) 내보내기
DELETE FROM `test`;
INSERT INTO `test` (`id`) VALUES
	('A'),
	('B'),
	('C'),
	('D'),
	('F'),
	('G');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
