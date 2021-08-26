CREATE DATABASE  IF NOT EXISTS `items_directory`;
USE `items_directory`;

--
-- Table structure for table `money`
--

DROP TABLE IF EXISTS `money`;

CREATE TABLE `money` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `value` double NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `money`
--

INSERT INTO `money` VALUES 
	(1,'BILL500',500,0),
	(2,'BILL100',100,0),
	(3,'BILL50',50,0),
	(4,'BILL10',10.5,1),
	(5,'BILL5',5,5),
	(6,'BILL1',1,5),
	(7,'COIN50',0.5,11),
	(8,'COIN10',0.1,10),
	(9,'COIN5',0.05,50),
	(10,'COIN1',0.01,100)