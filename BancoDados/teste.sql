-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_vendas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_vendas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_vendas` ;
USE `db_vendas` ;

-- -----------------------------------------------------
-- Table `db_vendas`.`tb_fornecedores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_vendas`.`tb_fornecedores` ;

CREATE TABLE IF NOT EXISTS `db_vendas`.`tb_fornecedores` (
  `for_codigo` BIGINT NOT NULL AUTO_INCREMENT,
  `for_descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`for_codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_vendas`.`tb_produtos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_vendas`.`tb_produtos` ;

CREATE TABLE IF NOT EXISTS `db_vendas`.`tb_produtos` (
  `pro_codigo` BIGINT NOT NULL AUTO_INCREMENT,
  `pro_descricao` VARCHAR(45) NOT NULL,
  `pro_valor` DECIMAL(7,2) NOT NULL,
  `pro_quantidade` INT NOT NULL,
  `tb_fornecedores_for_codigo` BIGINT NOT NULL,
  PRIMARY KEY (`pro_codigo`),
  INDEX `fk_tb_produtos_tb_fornecedores_idx` (`tb_fornecedores_for_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_tb_produtos_tb_fornecedores`
    FOREIGN KEY (`tb_fornecedores_for_codigo`)
    REFERENCES `db_vendas`.`tb_fornecedores` (`for_codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_vendas`.`tb_funcionarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_vendas`.`tb_funcionarios` ;

CREATE TABLE IF NOT EXISTS `db_vendas`.`tb_funcionarios` (
  `fun_codigo` BIGINT NOT NULL AUTO_INCREMENT,
  `fun_nome` VARCHAR(45) NOT NULL,
  `fun_cpf` VARCHAR(45) NOT NULL,
  `fun_senha` VARCHAR(50) NOT NULL,
  `fun_funcao` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`fun_codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_vendas`.`tb_vendas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_vendas`.`tb_vendas` ;

CREATE TABLE IF NOT EXISTS `db_vendas`.`tb_vendas` (
  `ven_codigo` BIGINT NOT NULL AUTO_INCREMENT,
  `ven_horario` TIMESTAMP NULL,
  `ven_valor_total` DECIMAL(7,2) NULL,
  `tb_funcionarios_fun_codigo` BIGINT NOT NULL,
  PRIMARY KEY (`ven_codigo`),
  INDEX `fk_tb_vendas_tb_funcionarios1_idx` (`tb_funcionarios_fun_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_tb_vendas_tb_funcionarios1`
    FOREIGN KEY (`tb_funcionarios_fun_codigo`)
    REFERENCES `db_vendas`.`tb_funcionarios` (`fun_codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_vendas`.`tb_itens`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_vendas`.`tb_itens` ;

CREATE TABLE IF NOT EXISTS `db_vendas`.`tb_itens` (
  `ite_codigo` BIGINT NOT NULL AUTO_INCREMENT,
  `ite_quantidade` INT NOT NULL,
  `ite_valor_parcial` DECIMAL(7,2) NULL,
  `tb_produtos_pro_codigo` BIGINT NOT NULL,
  `tb_vendas_ven_codigo` BIGINT NOT NULL,
  PRIMARY KEY (`ite_codigo`),
  INDEX `fk_tb_itens_tb_produtos1_idx` (`tb_produtos_pro_codigo` ASC) VISIBLE,
  INDEX `fk_tb_itens_tb_vendas1_idx` (`tb_vendas_ven_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_tb_itens_tb_produtos1`
    FOREIGN KEY (`tb_produtos_pro_codigo`)
    REFERENCES `db_vendas`.`tb_produtos` (`pro_codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_itens_tb_vendas1`
    FOREIGN KEY (`tb_vendas_ven_codigo`)
    REFERENCES `db_vendas`.`tb_vendas` (`ven_codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
