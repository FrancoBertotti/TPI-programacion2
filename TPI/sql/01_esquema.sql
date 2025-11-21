DROP DATABASE IF EXISTS segurovehiculos_db;
CREATE DATABASE segurovehiculos_db;
USE segurovehiculos_db;

-- ================================
--      TABLA: segurovehicular
-- ================================
CREATE TABLE segurovehicular (
    id INT AUTO_INCREMENT PRIMARY KEY,
    eliminado TINYINT NOT NULL DEFAULT 0,
    aseguradora VARCHAR(100) NOT NULL,
    nroPoliza VARCHAR(50) NOT NULL UNIQUE,
    cobertura ENUM('RC','TERCEROS','TODO_RIESGO') NOT NULL,
    vencimiento DATE NOT NULL
);

-- ================================
--      TABLA: vehiculo
-- ================================
CREATE TABLE vehiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    eliminado TINYINT NOT NULL DEFAULT 0,
    dominio VARCHAR(15) NOT NULL UNIQUE,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    anio INT NOT NULL,
    nroChasis VARCHAR(100) NOT NULL UNIQUE,
    seguro INT NULL,

    CONSTRAINT fk_vehiculo_seguro
        FOREIGN KEY (seguro)
        REFERENCES segurovehicular(id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);
