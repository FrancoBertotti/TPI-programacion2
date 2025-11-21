-- ==========================================================
-- 02_catalogos.sql - Datos de prueba
-- ==========================================================

USE segurovehiculos_db;

SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM vehiculo;
DELETE FROM segurovehicular;

SET FOREIGN_KEY_CHECKS = 1;

-- ================================
--   SEGUROS DE PRUEBA
-- ================================

INSERT INTO segurovehicular (eliminado, aseguradora, nroPoliza, cobertura, vencimiento) VALUES
(0,'Allianz',   'POL000001','RC',          DATE_ADD(CURDATE(), INTERVAL 60  DAY)),
(0,'Sancor',    'POL000002','TERCEROS',     DATE_ADD(CURDATE(), INTERVAL 90  DAY)),
(0,'La Caja',   'POL000003','TODO_RIESGO',  DATE_ADD(CURDATE(), INTERVAL 180 DAY)),
(0,'Mapfre',    'POL000004','RC',           DATE_ADD(CURDATE(), INTERVAL 75  DAY)),
(0,'Federación','POL000005','TERCEROS',     DATE_ADD(CURDATE(), INTERVAL 120 DAY)),
(0,'Zurich',    'POL000006','TODO_RIESGO',  DATE_ADD(CURDATE(), INTERVAL 210 DAY));

-- ================================
--   VEHÍCULOS DE PRUEBA
-- ================================

INSERT INTO vehiculo (eliminado, dominio, marca, modelo, anio, nroChasis, idSeguro) VALUES
-- Vehículos CON seguro (1,2,3)
(0,'AA123BB','Toyota',   'Corolla', 2019,'CH00000001',1),
(0,'AB456CD','Ford',     'Focus',   2018,'CH00000002',2),
(0,'AC789EF','Renault',  'Clio',    2020,'CH00000003',3),

-- Vehículos CON seguro (4,5,6)
(0,'AD111AA','Chevrolet','Cruze',   2021,'CH00000004',4),
(0,'AE222BB','Volkswagen','Golf',   2017,'CH00000005',5),
(0,'AF333CC','Peugeot',  '208',     2022,'CH00000006',6),

-- Vehículos SIN seguro (NULL)
(0,'AG444DD','Honda',    'Civic',   2016,'CH00000007',NULL),
(0,'AH555EE','Fiat',     'Argo',    2019,'CH00000008',NULL),
(0,'AI666FF','toyota',   'etios',  2015,'CH00000009',NULL);

-- ================================
--  Comprobacion
-- ================================

SELECT COUNT(*) AS cantidad_seguros   FROM segurovehicular;
SELECT COUNT(*) AS cantidad_vehiculos FROM vehiculo;