CREATE DATABASE  IF NOT EXISTS `items_directory`;
USE `items_directory`;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `price` double DEFAULT 0,
  `quantity` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `items` VALUES 
	(1,'Snickers',2.5,1),
	(2,'Ben & Jerrys',10,2),
	(3,'Mars',3,5),
	(4,'Chio',4.5,1),
	(5,'Coke',2,4);