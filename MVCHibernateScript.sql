CREATE DATABASE  IF NOT EXISTS `web_customer_tracker`;
USE `web_customer_tracker`;


DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


insert into customer values(1,'fsk1','fsk1','fsk1@gmail.com');
insert into customer values(2,'fsk2','fsk2','fsk2@gmail.com');
insert into customer values(3,'fsk3','fsk3','fsk3@gmail.com');
insert into customer values(4,'fsk4','fsk4','fsk4@gmail.com');
insert into customer values(5,'fsk5','fsk5','fsk5@gmail.com');

CREATE TABLE `customer_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(128) DEFAULT NULL,
  `DOB` DATETIME DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

insert into customer_detail value(101,'abc def efg', '2020-01-01 10:10:10+05:30',1);
insert into customer_detail value(102,'abc def efg', '2020-01-01 10:10:10+05:30',2);
insert into customer_detail value(103,'abc def efg', '2020-01-01 10:10:10+05:30',3);
insert into customer_detail value(104,'abc def efg', '2020-01-01 10:10:10+05:30',4);
insert into customer_detail value(105,'abc def efg', '2020-01-01 10:10:10+05:30',5);