CREATE SCHEMA IF NOT EXISTS `ws_rs_contacts`
DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `ws_rs_contacts`.`contacts` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `ws_rs_contacts`.`contact_fields` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `contact_id` BIGINT NOT NULL,
  `field_name` VARCHAR(64) NOT NULL,
  `field_value` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `contact_idx` (`contact_id` ASC) VISIBLE,
  CONSTRAINT `contact`
    FOREIGN KEY (`contact_id`)
    REFERENCES `ws_rs_contacts`.`contacts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
