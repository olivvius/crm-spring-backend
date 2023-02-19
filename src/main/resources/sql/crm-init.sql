DROP DATABASE IF EXISTS `crm_api`;
DROP USER IF EXISTS 'crm'@'localhost';
CREATE DATABASE `crm_api` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE USER 'crm'@'localhost' IDENTIFIED BY 'crm';
GRANT ALL ON `crm_api` . * TO 'crm'@'localhost' IDENTIFIED BY 'crm'