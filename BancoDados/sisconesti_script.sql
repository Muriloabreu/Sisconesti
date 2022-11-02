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
-- Table `mydb`.`Estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Estado` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `sigla` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cidade` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `id_estado` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Cidade_Estado_idx` (`id_estado` ASC) VISIBLE,
  CONSTRAINT `fk_Cidade_Estado`
    FOREIGN KEY (`id_estado`)
    REFERENCES `mydb`.`Estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Empresa` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `id_cidade` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Empresa_Cidade1_idx` (`id_cidade` ASC) VISIBLE,
  CONSTRAINT `fk_Empresa_Cidade1`
    FOREIGN KEY (`id_cidade`)
    REFERENCES `mydb`.`Cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Pessoa` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `email` VARCHAR(100) NULL,
  `cpf` VARCHAR(15) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Funcionario` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `matricula` VARCHAR(45) NULL,
  `id_pessoa` BIGINT NOT NULL,
  `id_empresa` BIGINT NOT NULL,
  `ativo` CHAR NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Funcionario_Pessoa1_idx` (`id_pessoa` ASC) VISIBLE,
  INDEX `fk_Funcionario_Empresa1_idx` (`id_empresa` ASC) VISIBLE,
  CONSTRAINT `fk_Funcionario_Pessoa1`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES `mydb`.`Pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Funcionario_Empresa1`
    FOREIGN KEY (`id_empresa`)
    REFERENCES `mydb`.`Empresa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `id` BIGINT NOT NULL,
  `Pessoa_id` BIGINT NOT NULL,
  `ativo` BIT NULL,
  `tipo` CHAR NULL,
  `senha` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Usuario_Pessoa1_idx` (`Pessoa_id` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_Pessoa1`
    FOREIGN KEY (`Pessoa_id`)
    REFERENCES `mydb`.`Pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Fabricante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Fabricante` (
  `id` BIGINT NOT NULL,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Equipamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Equipamento` (
  `id` BIGINT NOT NULL,
  `tipo` VARCHAR(45) NULL,
  `modelo` VARCHAR(45) NULL,
  `numero_serie` VARCHAR(45) NULL,
  `numero_tag` VARCHAR(45) NULL,
  `id_fabricante` BIGINT NOT NULL,
  `id_funcionario` BIGINT NOT NULL,
  `observacao` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Equipamento_Fabricante1_idx` (`id_fabricante` ASC) VISIBLE,
  INDEX `fk_Equipamento_Funcionario1_idx` (`id_funcionario` ASC) VISIBLE,
  CONSTRAINT `fk_Equipamento_Fabricante1`
    FOREIGN KEY (`id_fabricante`)
    REFERENCES `mydb`.`Fabricante` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipamento_Funcionario1`
    FOREIGN KEY (`id_funcionario`)
    REFERENCES `mydb`.`Funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
