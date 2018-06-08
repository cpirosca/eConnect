-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: econnect
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignments`
--

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Teacher_id` int(11) DEFAULT NULL,
  `Due_date` datetime DEFAULT NULL,
  `Description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher_assignments_idx` (`Teacher_id`),
  CONSTRAINT `teacher_assignments` FOREIGN KEY (`Teacher_id`) REFERENCES `teachers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (1,'CP: The Escape Room',NULL,NULL,'The man needs to escape the dungeon and he can move only if the road isn`t blocked by bolders. He can move forward, backward, left, right, up or down. HINT: Use everything you have learnt!'),(2,'CP: The Palindrome',NULL,NULL,'The problem is simple, for any given input check if it`s a palindrome or not.'),(3,'EMS: The Running Circuit',NULL,NULL,'The objective is to create a fully functional circuit that can be build from the components that your lab teacher gave you. HINT: Google is your best friend.'),(4,'CP',NULL,NULL,'Maineeee test de la 12'),(5,'CP',NULL,NULL,''),(6,'CP',NULL,NULL,'test testetest'),(7,'CP',NULL,NULL,'ACESTA ESTE UN EXEMPLU\n');
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grades`
--

DROP TABLE IF EXISTS `grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Grade` float NOT NULL DEFAULT '0',
  `Student_id` char(13) NOT NULL,
  `Teacher_id` int(11) DEFAULT NULL,
  `Subject_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student_grades_idx` (`Student_id`),
  KEY `teacher_grades_idx` (`Teacher_id`),
  KEY `subjects_grades_idx` (`Subject_id`),
  CONSTRAINT `student_grades` FOREIGN KEY (`Student_id`) REFERENCES `students` (`CNP`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `subjects_grades` FOREIGN KEY (`Subject_id`) REFERENCES `subjects` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `teacher_grades` FOREIGN KEY (`Teacher_id`) REFERENCES `teachers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grades`
--

LOCK TABLES `grades` WRITE;
/*!40000 ALTER TABLE `grades` DISABLE KEYS */;
INSERT INTO `grades` VALUES (4,7,'1970314212456',NULL,3),(5,5,'1970314212456',NULL,4),(7,7,'1970401785652',NULL,0),(8,5,'1970401785652',NULL,5),(9,8,'1970401785652',NULL,6),(10,7,'1970401785652',NULL,7),(11,0,'1970401785652',NULL,8),(12,7,'1970401785652',NULL,10),(13,8,'1970521205568',NULL,2),(14,9,'1970521205568',NULL,3),(15,7,'1970521205568',NULL,6),(16,6,'1970521205568',NULL,7),(17,7,'1970521205568',NULL,8),(18,5,'1970521205568',NULL,9),(19,5,'1970830123452',NULL,0),(20,0,'1970830123452',NULL,2),(21,7,'1970830123452',NULL,3),(22,7,'1970830123452',NULL,6),(23,8,'1970830123452',NULL,8),(24,6,'1970830123452',NULL,10),(25,6,'1971223521123',NULL,0),(26,4,'1971223521123',NULL,3),(27,0,'1971223521123',NULL,4),(28,7,'1971223521123',NULL,5),(29,5,'1971223521123',NULL,6),(30,7,'1971223521123',NULL,7),(31,9,'1981230212425',NULL,4),(32,0,'1981230212425',NULL,5),(33,8,'1981230212425',NULL,6),(34,5,'1981230212425',NULL,8),(35,7,'1981230212425',NULL,9),(36,8,'1981230212425',NULL,10),(37,5,'1970521205568',NULL,0),(65,9,'1970314212456',NULL,0);
/*!40000 ALTER TABLE `grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_name`
--

DROP TABLE IF EXISTS `group_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_name` (
  `group_name` varchar(45) NOT NULL,
  `group_id` varchar(45) NOT NULL,
  PRIMARY KEY (`group_name`),
  KEY `group_student_idx` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_name`
--

LOCK TABLES `group_name` WRITE;
/*!40000 ALTER TABLE `group_name` DISABLE KEYS */;
INSERT INTO `group_name` VALUES ('30422','1'),('25145','2'),('54852','3'),('30125','4'),('21652','5');
/*!40000 ALTER TABLE `group_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `note`
--

DROP TABLE IF EXISTS `note`;
/*!50001 DROP VIEW IF EXISTS `note`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `note` AS SELECT 
 1 AS `CNP`,
 1 AS `Grade`,
 1 AS `Subject`,
 1 AS `Student_FName`,
 1 AS `Student_LName`,
 1 AS `Teacher_username_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `CNP` char(13) NOT NULL,
  `subject_id` int(11) NOT NULL,
  PRIMARY KEY (`CNP`,`subject_id`),
  KEY `subjects_idx` (`subject_id`),
  CONSTRAINT `student_schedule` FOREIGN KEY (`CNP`) REFERENCES `students` (`CNP`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `subjects` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES ('1970314212456',0),('1970401785652',0),('1970830123452',0),('1971223521123',0),('1970314212456',1),('1970314212456',2),('1970521205568',2),('1970830123452',2),('1970314212456',3),('1970521205568',3),('1970830123452',3),('1971223521123',3),('1970314212456',4),('1971223521123',4),('1981230212425',4),('1970314212456',5),('1970401785652',5),('1971223521123',5),('1981230212425',5),('1970401785652',6),('1970521205568',6),('1970830123452',6),('1971223521123',6),('1981230212425',6),('1970401785652',7),('1970521205568',7),('1971223521123',7),('1970401785652',8),('1970521205568',8),('1970830123452',8),('1981230212425',8),('1970521205568',9),('1981230212425',9),('1970401785652',10),('1970830123452',10),('1981230212425',10);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `studentinfo`
--

DROP TABLE IF EXISTS `studentinfo`;
/*!50001 DROP VIEW IF EXISTS `studentinfo`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `studentinfo` AS SELECT 
 1 AS `CNP`,
 1 AS `Student_FName`,
 1 AS `Student_LName`,
 1 AS `Group_Name`,
 1 AS `Specialization`,
 1 AS `Assignment`,
 1 AS `Assignment_Description`,
 1 AS `Teacher_FName`,
 1 AS `Teacher_LName`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `CNP` char(13) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Group_name` varchar(45) NOT NULL,
  `User_id` int(11) DEFAULT NULL,
  `Specialization` varchar(45) NOT NULL,
  PRIMARY KEY (`CNP`,`Group_name`),
  UNIQUE KEY `User_id_UNIQUE` (`User_id`),
  KEY `student_group_idx` (`Group_name`),
  CONSTRAINT `student_account` FOREIGN KEY (`User_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student_group` FOREIGN KEY (`Group_name`) REFERENCES `group_name` (`group_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES ('1970314212456','Calin','Pirosca','30422',5,'Calculatoare Engleza'),('1970401785652','Andrei','Rad','30422',2,'Calculatoare Engleza'),('1970521205568','Raul','Tronciu','30125',6,'Calculatoare Romana'),('1970830123452','Mihai','Suciu','21652',10,'Automatica Romana'),('1971223521123','Razvan','Rus','30422',3,'Calculatoare Engleza'),('1981230212425','Maximilian','Timofte','25145',11,'Automatica Engleza');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subjects` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` VALUES (8,'ALG'),(3,'ALP'),(0,'CP'),(10,'DSD'),(2,'EMS'),(5,'EN'),(6,'GE'),(9,'LD'),(1,'MA'),(7,'P'),(4,'Sport');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_group`
--

DROP TABLE IF EXISTS `teacher_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_group` (
  `teacher_id` int(11) NOT NULL,
  `group_id` varchar(45) NOT NULL,
  `id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `group_id_idx` (`group_id`),
  KEY `group_teacher_idx` (`teacher_id`),
  CONSTRAINT `group_id` FOREIGN KEY (`group_id`) REFERENCES `group_name` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `group_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`teacher_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_group`
--

LOCK TABLES `teacher_group` WRITE;
/*!40000 ALTER TABLE `teacher_group` DISABLE KEYS */;
INSERT INTO `teacher_group` VALUES (1,'1','1'),(1,'2','2'),(2,'1','3'),(2,'4','4');
/*!40000 ALTER TABLE `teacher_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teachers` (
  `id` int(11) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Subject_id` int(11) NOT NULL,
  `Title` varchar(45) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`teacher_id`),
  KEY `Subjects_teachers_idx` (`Subject_id`),
  KEY `teacher_group_idx` (`teacher_id`),
  CONSTRAINT `sujectstachers` FOREIGN KEY (`Subject_id`) REFERENCES `subjects` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usr_tacher` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (8,'Marius','Joldos',0,'Engineer',1),(9,'Septimiu','Muresan',2,'Engineer',2);
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams_4_projects`
--

DROP TABLE IF EXISTS `teams_4_projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teams_4_projects` (
  `group_name` varchar(45) NOT NULL,
  `Assignment_id` int(11) NOT NULL,
  PRIMARY KEY (`Assignment_id`,`group_name`),
  KEY `teams_assingnments_idx` (`Assignment_id`),
  KEY `asd_idx` (`group_name`),
  CONSTRAINT `team_assignment` FOREIGN KEY (`Assignment_id`) REFERENCES `assignments` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `team_group` FOREIGN KEY (`group_name`) REFERENCES `group_name` (`group_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams_4_projects`
--

LOCK TABLES `teams_4_projects` WRITE;
/*!40000 ALTER TABLE `teams_4_projects` DISABLE KEYS */;
INSERT INTO `teams_4_projects` VALUES ('30422',1),('21652',2),('30125',2),('30422',2),('21652',3),('25145',4),('30422',4),('25145',5),('30422',5),('25145',6),('30422',6),('25145',7),('30422',7);
/*!40000 ALTER TABLE `teams_4_projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `hash` varchar(200) NOT NULL,
  `Access_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `user_access_idx` (`Access_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'test','123',3),(2,'andrei','123',3),(3,'rusrazvan','123',3),(4,'theadmin','123',1),(5,'calinpirosca','123',3),(6,'tronciuraul','123',3),(7,'secretara','123',1),(8,'mariusjoldos','123',2),(9,'septimiumuresan','123',2),(10,'suciumihai','123',3),(11,'smecheru97','123',3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `note`
--

/*!50001 DROP VIEW IF EXISTS `note`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `note` AS select `students`.`CNP` AS `CNP`,`grades`.`Grade` AS `Grade`,`subjects`.`name` AS `Subject`,`students`.`FirstName` AS `Student_FName`,`students`.`LastName` AS `Student_LName`,`teachers`.`id` AS `Teacher_username_id` from ((((`grades` join `students` on((`grades`.`Student_id` = `students`.`CNP`))) join `subjects` on((`grades`.`Subject_id` = `subjects`.`id`))) join `teachers` on((`teachers`.`Subject_id` = `subjects`.`id`))) join `schedule` on(((`subjects`.`id` = `schedule`.`subject_id`) and (`schedule`.`CNP` = `students`.`CNP`)))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `studentinfo`
--

/*!50001 DROP VIEW IF EXISTS `studentinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `studentinfo` AS select `students`.`CNP` AS `CNP`,`students`.`FirstName` AS `Student_FName`,`students`.`LastName` AS `Student_LName`,`students`.`Group_name` AS `Group_Name`,`students`.`Specialization` AS `Specialization`,`assignments`.`Name` AS `Assignment`,`assignments`.`Description` AS `Assignment_Description`,`teachers`.`FirstName` AS `Teacher_FName`,`teachers`.`LastName` AS `Teacher_LName` from (((`students` join `teams_4_projects` on((`students`.`Group_name` = `teams_4_projects`.`group_name`))) join `assignments` on((`teams_4_projects`.`Assignment_id` = `assignments`.`id`))) join `teachers` on((`teachers`.`id` = `assignments`.`Teacher_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-21 18:35:08
