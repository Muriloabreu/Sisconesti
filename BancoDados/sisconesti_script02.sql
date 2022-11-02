-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_sisconesti_v02
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_sisconesti_v02
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_sisconesti_v02` DEFAULT CHARACTER SET utf8 ;
USE `db_sisconesti_v02` ;

-- -----------------------------------------------------
-- Table `db_sisconesti_v02`.`Estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sisconesti_v02`.`Estado` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `sigla` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_sisconesti_v02`.`Cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sisconesti_v02`.`Cidade` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `id_estado` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Cidade_Estado_idx` (`id_estado` ASC) VISIBLE,
  CONSTRAINT `fk_Cidade_Estado`
    FOREIGN KEY (`id_estado`)
    REFERENCES `db_sisconesti_v02`.`Estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_sisconesti_v02`.`Empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sisconesti_v02`.`Empresa` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `id_cidade` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Empresa_Cidade1_idx` (`id_cidade` ASC) VISIBLE,
  CONSTRAINT `fk_Empresa_Cidade1`
    FOREIGN KEY (`id_cidade`)
    REFERENCES `db_sisconesti_v02`.`Cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_sisconesti_v02`.`Pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sisconesti_v02`.`Pessoa` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `email` VARCHAR(100) NULL,
  `cpf` VARCHAR(15) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_sisconesti_v02`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sisconesti_v02`.`Funcionario` (
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
    REFERENCES `db_sisconesti_v02`.`Pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Funcionario_Empresa1`
    FOREIGN KEY (`id_empresa`)
    REFERENCES `db_sisconesti_v02`.`Empresa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_sisconesti_v02`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sisconesti_v02`.`Usuario` (
  `id` BIGINT NOT NULL,
  `Pessoa_id` BIGINT NOT NULL,
  `ativo` BIT NULL,
  `tipo` CHAR NULL,
  `senha` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Usuario_Pessoa1_idx` (`Pessoa_id` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_Pessoa1`
    FOREIGN KEY (`Pessoa_id`)
    REFERENCES `db_sisconesti_v02`.`Pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_sisconesti_v02`.`Fabricante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sisconesti_v02`.`Fabricante` (
  `id` BIGINT NOT NULL,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_sisconesti_v02`.`Equipamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sisconesti_v02`.`Equipamento` (
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
    REFERENCES `db_sisconesti_v02`.`Fabricante` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipamento_Funcionario1`
    FOREIGN KEY (`id_funcionario`)
    REFERENCES `db_sisconesti_v02`.`Funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
