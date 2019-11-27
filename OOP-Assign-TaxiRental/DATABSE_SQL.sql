-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema taxi_rental
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema taxi_rental
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `taxi_rental` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
-- -----------------------------------------------------
-- Schema users
-- -----------------------------------------------------
USE `taxi_rental` ;

-- -----------------------------------------------------
-- Table `taxi_rental`.`taxis`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi_rental`.`taxis` ;

CREATE TABLE IF NOT EXISTS `taxi_rental`.`taxis` (
  `taxiID` INT(11) NOT NULL AUTO_INCREMENT,
  `driver` VARCHAR(100) NOT NULL,
  `make` VARCHAR(45) NOT NULL,
  `taxiLocation` VARCHAR(55) NOT NULL,
  `mobile` VARCHAR(15) NOT NULL,
  `rate` FLOAT(5,2) NOT NULL,
  `daysAvailable` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`taxiID`))
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `taxi_rental`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi_rental`.`users` ;

CREATE TABLE IF NOT EXISTS `taxi_rental`.`users` (
  `userID` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `location` VARCHAR(100) NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`userID`))
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
