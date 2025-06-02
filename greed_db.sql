CREATE DATABASE  IF NOT EXISTS `greed_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `greed_db`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: greed_db
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `board_saves`
--

DROP TABLE IF EXISTS `board_saves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_saves` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `rule_name` varchar(100) NOT NULL,
  `rows_num` int NOT NULL,
  `cols_num` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_saves`
--

LOCK TABLES `board_saves` WRITE;
/*!40000 ALTER TABLE `board_saves` DISABLE KEYS */;
INSERT INTO `board_saves` VALUES (12,'left','Game of Life',7,17,'2025-06-01 06:07:54'),(13,'right','Game of Life',7,13,'2025-06-01 06:09:26'),(14,'gun','Game of Life',40,40,'2025-06-01 06:19:20');
/*!40000 ALTER TABLE `board_saves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custom_rules`
--

DROP TABLE IF EXISTS `custom_rules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `custom_rules` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `rule` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_rules`
--

LOCK TABLES `custom_rules` WRITE;
/*!40000 ALTER TABLE `custom_rules` DISABLE KEYS */;
INSERT INTO `custom_rules` VALUES (1,'vaaa bobrito bondito','B123/S23');
/*!40000 ALTER TABLE `custom_rules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `live_cells`
--

DROP TABLE IF EXISTS `live_cells`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live_cells` (
  `id` int NOT NULL AUTO_INCREMENT,
  `save_id` int NOT NULL,
  `row_pos` int NOT NULL,
  `col_pos` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `save_id` (`save_id`),
  CONSTRAINT `live_cells_ibfk_1` FOREIGN KEY (`save_id`) REFERENCES `board_saves` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live_cells`
--

LOCK TABLES `live_cells` WRITE;
/*!40000 ALTER TABLE `live_cells` DISABLE KEYS */;
INSERT INTO `live_cells` VALUES (168,12,0,9),(169,12,0,11),(170,12,1,4),(171,12,1,9),(172,12,1,12),(173,12,2,5),(174,12,2,6),(175,12,2,12),(176,12,2,13),(177,12,3,0),(178,12,3,1),(179,12,3,10),(180,12,3,14),(181,12,3,15),(182,12,3,16),(183,12,4,0),(184,12,4,1),(185,12,4,12),(186,12,4,13),(187,12,5,9),(188,12,5,12),(189,12,6,9),(190,12,6,11),(191,13,0,2),(192,13,0,3),(193,13,1,1),(194,13,1,3),(195,13,2,0),(196,13,2,7),(197,13,2,8),(198,13,3,0),(199,13,3,3),(200,13,3,6),(201,13,3,9),(202,13,3,11),(203,13,3,12),(204,13,4,0),(205,13,4,7),(206,13,4,8),(207,13,4,11),(208,13,4,12),(209,13,5,1),(210,13,5,3),(211,13,6,2),(212,13,6,3),(213,14,6,26),(214,14,6,27),(215,14,7,25),(216,14,7,27),(217,14,8,10),(218,14,8,12),(219,14,8,24),(220,14,8,31),(221,14,8,32),(222,14,9,5),(223,14,9,10),(224,14,9,13),(225,14,9,24),(226,14,9,27),(227,14,9,30),(228,14,9,33),(229,14,9,35),(230,14,9,36),(231,14,10,6),(232,14,10,7),(233,14,10,13),(234,14,10,14),(235,14,10,24),(236,14,10,31),(237,14,10,32),(238,14,10,35),(239,14,10,36),(240,14,11,1),(241,14,11,2),(242,14,11,11),(243,14,11,15),(244,14,11,16),(245,14,11,25),(246,14,11,27),(247,14,12,1),(248,14,12,2),(249,14,12,13),(250,14,12,14),(251,14,12,26),(252,14,12,27),(253,14,13,10),(254,14,13,13),(255,14,14,10),(256,14,14,12);
/*!40000 ALTER TABLE `live_cells` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-02 23:04:39
