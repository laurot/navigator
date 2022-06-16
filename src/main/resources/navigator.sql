-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`coordinates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`coordinates` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `x` INT NOT NULL,
  `y` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`accounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `pass` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `position_id` INT NOT NULL,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_users_coordinates_idx` (`position_id` ASC) VISIBLE,
  INDEX `fk_users_users_copy11_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_coordinates`
    FOREIGN KEY (`position_id`)
    REFERENCES `mydb`.`coordinates` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_users_copy11`
    FOREIGN KEY (`account_id`)
    REFERENCES `mydb`.`accounts` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`places`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`places` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `location_id` INT NOT NULL,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_places_coordinates1_idx` (`location_id` ASC) VISIBLE,
  INDEX `fk_places_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_places_coordinates1`
    FOREIGN KEY (`location_id`)
    REFERENCES `mydb`.`coordinates` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_places_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `mydb`.`accounts` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`fuels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`fuels` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`transports`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`transports` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `speed` INT NOT NULL,
  `consumption` DECIMAL(4,2) NOT NULL,
  `fuel_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_transport_fuel1_idx` (`fuel_id` ASC) VISIBLE,
  CONSTRAINT `fk_transport_fuel1`
    FOREIGN KEY (`fuel_id`)
    REFERENCES `mydb`.`fuels` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`trips`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`trips` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `transport_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trip_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_trip_transport1_idx` (`transport_id` ASC) VISIBLE,
  CONSTRAINT `fk_trip_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trip_transport1`
    FOREIGN KEY (`transport_id`)
    REFERENCES `mydb`.`transports` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`paths`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`paths` (
  `trip_id` INT NOT NULL,
  `coordinate_id` INT NOT NULL,
  PRIMARY KEY (`trip_id`, `coordinate_id`),
  INDEX `fk_trip_has_coordinate_coordinate1_idx` (`coordinate_id` ASC) VISIBLE,
  INDEX `fk_trip_has_coordinate_trip1_idx` (`trip_id` ASC) VISIBLE,
  CONSTRAINT `fk_trip_has_coordinate_trip1`
    FOREIGN KEY (`trip_id`)
    REFERENCES `mydb`.`trips` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trip_has_coordinates_coordinate1`
    FOREIGN KEY (`coordinate_id`)
    REFERENCES `mydb`.`coordinates` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO Accounts (username,pass) VALUES ('coco','difficultPassword1');
INSERT INTO Accounts (username,pass) VALUES ('lauti','difficultPassword2');
INSERT INTO Coordinates (x,y) VALUES (1,1);
INSERT INTO Users (account_id,position_id) VALUES (1,1);
INSERT INTO Fuels (type,price) VALUES ('Gas',100);
insert into fuels (type, price) values ("diesel", 3.5);
INSERT INTO Transports (type,speed,consumption,fuel_id) VALUES ('Car',180,10,1);
INSERT INTO Trips (user_id,transport_id) VALUES (1,1);
INSERT INTO Paths (trip_id,coordinate_id) VALUES (1,1);
INSERT INTO Places (name,location_id,account_id) VALUES ('Starbucks',1,2);
insert into transports (type, speed, consumption, fuel_id) values ("Bus", 30, 3, 3);
insert into transports (type, speed, consumption, fuel_id) values ("Car", 60, 1, 2);