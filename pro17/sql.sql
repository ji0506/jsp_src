-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.11.2-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- board 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `board` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci */;
USE `board`;

-- 함수 board.GET_ARTICLENO 구조 내보내기
DELIMITER //
CREATE FUNCTION `GET_ARTICLENO`(f_articleNo INT(10)
) RETURNS int(10)
BEGIN 
	DECLARE RTN_ARTIClE_NO INT(10); 
	
	WITH RECURSIVE board AS (
		SELECT b.*
	   FROM t_board b WHERE b.articleNo = f_articleNo
	   UNION ALL
	   SELECT b.*
	   FROM board l INNER JOIN t_board b ON  l.articleNo = b.parentNO
	)
	SELECT 
		articleNo INTO RTN_ARTIClE_NO
	FROM board;

   RETURN RTN_ARTIClE_NO;
END//
DELIMITER ;

-- 테이블 board.t_board 구조 내보내기
CREATE TABLE IF NOT EXISTS `t_board` (
  `articleNo` int(10) NOT NULL AUTO_INCREMENT,
  `parentNO` int(10) DEFAULT 0,
  `title` varchar(500) DEFAULT NULL,
  `content` varchar(4000) DEFAULT NULL,
  `imageFileName` varchar(100) DEFAULT NULL,
  `writedate` datetime NOT NULL DEFAULT current_timestamp(),
  `id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`articleNo`),
  KEY `FK_ID` (`id`),
  CONSTRAINT `FK_ID` FOREIGN KEY (`id`) REFERENCES `t_member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 board.t_board:~10 rows (대략적) 내보내기
/*!40000 ALTER TABLE `t_board` DISABLE KEYS */;
INSERT INTO `t_board` (`articleNo`, `parentNO`, `title`, `content`, `imageFileName`, `writedate`, `id`) VALUES
	(1, 0, '테스트글입니다.', '테스트글입니다', NULL, '2023-06-05 17:15:42', 'hong'),
	(2, 0, '안녕하세요.', '상품후기입니다.', NULL, '2023-06-05 17:15:43', 'hong'),
	(3, 2, '답변입니다', '상품후기에대한 답변입니다.', NULL, '2023-06-05 17:15:44', 'hong'),
	(4, 0, '김유신입니다.', '김유신테스트글입니다.', NULL, '2023-06-05 17:15:45', 'hong'),
	(5, 3, '답변입니다.', '상품이좋습니다.', NULL, '2023-06-05 17:15:46', 'hong'),
	(6, 2, '상품후기입니다..', '이순신씨의 상품 사용후기를 올립니다!!', NULL, '2023-06-05 17:15:47', 'hong'),
	(9, 0, 'TEST', 'TEST', 'test3.png', '2023-06-08 18:39:53', 'hong'),
	(10, 9, 'test232323', ' test232323', 'image.jpg', '2023-06-09 15:46:19', 'lee'),
	(11, 10, 'test 답글에답글', 'test 답글에답글', 'test3.png', '2023-06-09 15:54:03', 'lee'),
	(12, 0, 'test 0', 'test 0', 'test2.png', '2023-06-09 15:54:45', 'hong');
/*!40000 ALTER TABLE `t_board` ENABLE KEYS */;

-- 테이블 board.t_member 구조 내보내기
CREATE TABLE IF NOT EXISTS `t_member` (
  `id` varchar(10) NOT NULL,
  `pwd` varchar(10) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `joinData` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 board.t_member:~3 rows (대략적) 내보내기
/*!40000 ALTER TABLE `t_member` DISABLE KEYS */;
INSERT INTO `t_member` (`id`, `pwd`, `name`, `email`, `joinData`) VALUES
	('hong', '12345', '홍길동2', 'hong@test.com', '2023-06-05 15:26:08'),
	('lee', 'test2', 'test2', 'test2', '2023-06-05 15:59:54'),
	('test2', 'test2', 'test2', 'test2', '2023-06-05 15:59:54');
/*!40000 ALTER TABLE `t_member` ENABLE KEYS */;

-- 뷰 board.v_board 구조 내보내기
-- VIEW 종속성 오류를 극복하기 위해 임시 테이블을 생성합니다.
CREATE TABLE `v_board` (
	`articleNo` INT(11) NULL,
	`topId` INT(11) NULL
) ENGINE=MyISAM;

-- 뷰 board.v_board 구조 내보내기
-- 임시 테이블을 제거하고 최종 VIEW 구조를 생성
DROP TABLE IF EXISTS `v_board`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `v_board` AS WITH RECURSIVE board AS (
	SELECT b.*, b.articleNo AS topId
	FROM t_board b WHERE b.parentNO = 0
	UNION ALL
	SELECT b.*, l.topId 
	FROM board l INNER JOIN t_board b ON  l.articleNo = b.parentNO
)
SELECT 
	articleNo,topId
FROM board ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
