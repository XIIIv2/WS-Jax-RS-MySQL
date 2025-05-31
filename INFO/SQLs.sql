CREATE SCHEMA IF NOT EXISTS `ws_rs_contacts`
DEFAULT CHARACTER SET utf8;

USE `ws_rs_contacts`;

CREATE TABLE IF NOT EXISTS `contacts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `phone` varchar(32) NOT NULL,
  `created_on` timestamp NOT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `extra_fields` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contact_id` bigint NOT NULL,
  `field_name` varchar(64) NOT NULL,
  `field_value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `contact_fk_idx` (`contact_id`),
  CONSTRAINT `contact_fk` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;