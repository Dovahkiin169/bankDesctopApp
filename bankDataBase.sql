-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: localhost    Database: bank
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_account` (
  `login_id` int unsigned DEFAULT NULL,
  `account_number` int unsigned NOT NULL,
  `account_type` varchar(20) DEFAULT NULL,
  `account_currency` varchar(3) DEFAULT NULL,
  `account_balance` decimal(50,2) DEFAULT NULL,
  KEY `login_id` (`login_id`),
  CONSTRAINT `bank_account_ibfk_1` FOREIGN KEY (`login_id`) REFERENCES `users` (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employes`
--

DROP TABLE IF EXISTS `employes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employes` (
  `login_id_e` int unsigned NOT NULL AUTO_INCREMENT,
  `password_e` varchar(255) DEFAULT NULL,
  `email_address_e` varchar(70) DEFAULT NULL,
  `name_e` varchar(50) DEFAULT NULL,
  `surename_e` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id_e`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employes`
--

LOCK TABLES `employes` WRITE;
/*!40000 ALTER TABLE `employes` DISABLE KEYS */;
/*!40000 ALTER TABLE `employes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_history`
--

DROP TABLE IF EXISTS `transaction_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_history` (
  `login_id` int unsigned DEFAULT NULL,
  `account_number_from` int unsigned NOT NULL,
  `account_number_to` int unsigned NOT NULL,
  `amount` decimal(50,2) DEFAULT NULL,
  `transaction_date` date DEFAULT NULL,
  KEY `login_id` (`login_id`),
  CONSTRAINT `transaction_history_ibfk_1` FOREIGN KEY (`login_id`) REFERENCES `users` (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_history`
--

LOCK TABLES `transaction_history` WRITE;
/*!40000 ALTER TABLE `transaction_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `login_id` int unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `email_address` varchar(70) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `name_2` varchar(50) DEFAULT NULL,
  `surename` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `register_date` date DEFAULT NULL,
  `pesel` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`login_id`),
  UNIQUE KEY `pesel_UNIQUE` (`pesel`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;