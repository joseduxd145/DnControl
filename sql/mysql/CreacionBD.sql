CREATE DATABASE IF NOT EXISTS DNCONTROL;
USE DNCONTROL;

-- Create tables section -------------------------------------------------

-- Table PERSONAJE

CREATE TABLE IF NOT EXISTS PERSONAJE(
  PERSONAJE_ID INT(4) NOT NULL AUTO_INCREMENT,
  USUARIO_ID INT(3),
  NOMBRE_PERSONAJE VARCHAR(50) NOT NULL UNIQUE,
  APELLIDO VARCHAR(100),
  TRANSFONDO TEXT NOT NULL,
  FUERZA INT(2) NOT NULL CHECK (FUERZA between 1 and 50),
  DESTREZA INT(2) NOT NULL CHECK (DESTREZA between 1 and 50),
  CONSTITUCION INT(2) NOT NULL CHECK (CONSTITUCION between 1 and 50),
  INTELIGENCIA INT(2) NOT NULL CHECK (INTELIGENCIA between 1 and 50),
  SABIDURIA INT(2) NOT NULL CHECK (SABIDURIA between 1 and 50),
  CARISMA INT(2) NOT NULL CHECK (CARISMA between 1 and 50),
  JUGADOR Char(1 ) NOT NULL CHECK (JUGADOR in('j','e')),
  PRIMARY KEY (PERSONAJE_ID)
);

-- Create indexes for table PERSONAJE

CREATE INDEX IX_Relationship5 ON PERSONAJE (USUARIO_ID);

-- Table HABILIDAD

CREATE TABLE IF NOT EXISTS HABILIDAD(
  HABILIDAD_ID INT(4) NOT NULL AUTO_INCREMENT,
  NUM_DADO_ID INT(1),
  NOMBRE_HABILIDAD VARCHAR(50) NOT NULL UNIQUE,
  DESCRIPCION VARCHAR(1000) NOT NULL,
  CANTIDAD_DADO INT(3) NOT NULL,
  PRIMARY KEY (HABILIDAD_ID)
);

-- Create indexes for table HABILIDAD

CREATE INDEX IX_Relationship3 ON HABILIDAD (NUM_DADO_ID);


-- Table PERSONAJE_HABILIDAD

CREATE TABLE IF NOT EXISTS PERSONAJE_HABILIDAD(
  PERSONAJE_ID INT(4) NOT NULL,
  HABILIDAD_ID INT(4) NOT NULL,
  PRIMARY KEY (PERSONAJE_ID,HABILIDAD_ID)
);


-- Table USUARIO

CREATE TABLE IF NOT EXISTS USUARIO(
  USUARIO_ID INT(3) NOT NULL AUTO_INCREMENT,
  EMAIL VARCHAR(100) NOT NULL UNIQUE CHECK (EMAIL LIKE '%@%.%'),
  NOMBRE_USUARIO VARCHAR(50) NOT NULL UNIQUE,
  PASSWD Char(64) NOT NULL,
  DM BOOLEAN NOT NULL DEFAULT false,
  PRIMARY KEY (USUARIO_ID)
);

-- Table OBJETO

CREATE TABLE IF NOT EXISTS OBJETO(
  OBJETO_ID INT(4) NOT NULL AUTO_INCREMENT,
  PERSONAJE_ID INT(4),
  NOMBRE_OBJETO VARCHAR(100) NOT NULL,
  DESCRIPCION VARCHAR(1000) NOT NULL,
  VALOR INT(5) NOT NULL,
  PRIMARY KEY (OBJETO_ID)
);

-- Create indexes for table OBJETO

CREATE INDEX IX_Relationship4 ON OBJETO (PERSONAJE_ID);

-- Table SEL_NUM_DADO

CREATE TABLE IF NOT EXISTS SEL_NUM_DADO(
  NUM_DADO_ID INT(2) NOT NULL AUTO_INCREMENT,
  NUM_DADO INT(3) NOT NULL,
  PRIMARY KEY (NUM_DADO_ID)
);

-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE PERSONAJE_HABILIDAD ADD CONSTRAINT Relationship1 FOREIGN KEY (PERSONAJE_ID) REFERENCES PERSONAJE (PERSONAJE_ID);


ALTER TABLE PERSONAJE_HABILIDAD ADD CONSTRAINT Relationship2 FOREIGN KEY (HABILIDAD_ID) REFERENCES HABILIDAD (HABILIDAD_ID);


ALTER TABLE HABILIDAD ADD CONSTRAINT Relationship3 FOREIGN KEY (NUM_DADO_ID) REFERENCES SEL_NUM_DADO (NUM_DADO_ID);


ALTER TABLE OBJETO ADD CONSTRAINT Relationship4 FOREIGN KEY (PERSONAJE_ID) REFERENCES PERSONAJE (PERSONAJE_ID);


ALTER TABLE PERSONAJE ADD CONSTRAINT Relationship5 FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO (USUARIO_ID);

USE DNCONTROL;

START TRANSACTION;

-- Inserciones en tabla SEL_NUM_DADO
INSERT INTO SEL_NUM_DADO (NUM_DADO) VALUES (0);
INSERT INTO SEL_NUM_DADO (NUM_DADO) VALUES (4);
INSERT INTO SEL_NUM_DADO (NUM_DADO) VALUES (6);
INSERT INTO SEL_NUM_DADO (NUM_DADO) VALUES (8);
INSERT INTO SEL_NUM_DADO (NUM_DADO) VALUES (10);
INSERT INTO SEL_NUM_DADO (NUM_DADO) VALUES (12);
INSERT INTO SEL_NUM_DADO (NUM_DADO) VALUES (20);
INSERT INTO SEL_NUM_DADO (NUM_DADO) VALUES (30);
-- Fin inserciones en tabla SEL_NIM_DADO

-- Insercion usuarios
INSERT INTO USUARIO (EMAIL, NOMBRE_USUARIO, PASSWD, DM) VALUES
('admin@gmail.com', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', true);
INSERT INTO USUARIO (EMAIL, NOMBRE_USUARIO, PASSWD) VALUES
('usr1@gmail.com', 'usr1', '491a2800b80719ea9e3c89ca5472a8bda1bdd1533d4574ea5bd85b70a8e93be0');
INSERT INTO USUARIO (EMAIL, NOMBRE_USUARIO, PASSWD) VALUES
('usr2@gmail.com', 'usr2', 'c577e95bf729b94c30a878d01155693a9cdddafbb2fe0d52143027474ecb91bc');
INSERT INTO USUARIO (EMAIL, NOMBRE_USUARIO, PASSWD) VALUES
('usr3@gmail.com', 'usr3', '5a91a66f0c86b5435fe748706b99c17e6e54a17e03c2a3ef8d0dfa918db41cf6');
INSERT INTO USUARIO (EMAIL, NOMBRE_USUARIO, PASSWD) VALUES
('usr4@gmail.com', 'usr4', '6046bc3337728a60967a151ee584e4fd7c53740a49485ebdc38cac42a255f266');
-- Fin usuarios

-- Insertar Personajes
INSERT INTO PERSONAJE (USUARIO_ID, NOMBRE_PERSONAJE, APELLIDO, TRANSFONDO, FUERZA, DESTREZA, CONSTITUCION, INTELIGENCIA, SABIDURIA, CARISMA, JUGADOR) VALUES
(1, 'Nombre generico 1', 'Apellido generico', 'Una epica historia generica...', 16, 13, 12, 6, 6, 9, 'j');
INSERT INTO PERSONAJE (USUARIO_ID, NOMBRE_PERSONAJE, APELLIDO, TRANSFONDO, FUERZA, DESTREZA, CONSTITUCION, INTELIGENCIA, SABIDURIA, CARISMA, JUGADOR) VALUES
(2, 'Segundo nombre mas generico', NULL, 'Algun tipo de historia', 6, 8, 7, 17, 15, 12, 'j');
INSERT INTO PERSONAJE (USUARIO_ID, NOMBRE_PERSONAJE, APELLIDO, TRANSFONDO, FUERZA, DESTREZA, CONSTITUCION, INTELIGENCIA, SABIDURIA, CARISMA, JUGADOR) VALUES
(NULL, 'enemigo', NULL, 'Es tan basico que no tine nada especial', 10, 10, 10, 10, 10, 10, 'e');
INSERT INTO PERSONAJE (USUARIO_ID, NOMBRE_PERSONAJE, APELLIDO, TRANSFONDO, FUERZA, DESTREZA, CONSTITUCION, INTELIGENCIA, SABIDURIA, CARISMA, JUGADOR) VALUES
(3, 'Primo paquito', 'Paquito paquito', 'Nadie sabe si es paquito o su primo', 20, 20, 20, 20, 20, 20, 'j');
INSERT INTO PERSONAJE (USUARIO_ID, NOMBRE_PERSONAJE, APELLIDO, TRANSFONDO, FUERZA, DESTREZA, CONSTITUCION, INTELIGENCIA, SABIDURIA, CARISMA, JUGADOR) VALUES
(NULL, 'Velkhana', 'hoho', 'Dragon legendario de hielo', 30, 15, 50, 25, 40, 10, 'e');
-- Fin personajes

-- Insertar Objetos
INSERT INTO OBJETO (PERSONAJE_ID, NOMBRE_OBJETO, DESCRIPCION, VALOR) VALUES
(1, 'Pesos balanza', 'Un set de pesos para valanza sin apenas valor', 4);
INSERT INTO OBJETO (PERSONAJE_ID, NOMBRE_OBJETO, DESCRIPCION, VALOR) VALUES
(1, 'Cristales de ragn', 'Piedras de alto valor entre colecionistas', 627);
INSERT INTO OBJETO (PERSONAJE_ID, NOMBRE_OBJETO, DESCRIPCION, VALOR) VALUES
(3, 'Naranjo de paquito', 'Legendario naranjo de paquito', 5000);
INSERT INTO OBJETO (PERSONAJE_ID, NOMBRE_OBJETO, DESCRIPCION, VALOR) VALUES
(2, 'Objeto generico', 'Cosa generica para un personaje generico', 1);
INSERT INTO OBJETO (PERSONAJE_ID, NOMBRE_OBJETO, DESCRIPCION, VALOR) VALUES
(3, 'Dinero', 'Dinero?', 43);
INSERT INTO OBJETO (PERSONAJE_ID, NOMBRE_OBJETO, DESCRIPCION, VALOR) VALUES
(3, 'No se me ocurre nada', 'Descripcion de algo', 41);
INSERT INTO OBJETO (PERSONAJE_ID, NOMBRE_OBJETO, DESCRIPCION, VALOR) VALUES
(3, 'Berenjena', 'La Berenjena de paquito', 41);
-- Fin Objetos

-- Inserccion habilidades
INSERT INTO HABILIDAD (NUM_DADO_ID, NOMBRE_HABILIDAD, DESCRIPCION, CANTIDAD_DADO) VALUES
(1, 'Respiracion acuatica', 'Puede respirar bajo el agua', 0);
INSERT INTO HABILIDAD (NUM_DADO_ID, NOMBRE_HABILIDAD, DESCRIPCION, CANTIDAD_DADO) VALUES
(1, 'Cosa generica', 'Habilidad generica que lanza 4d4', 4);
INSERT INTO HABILIDAD (NUM_DADO_ID, NOMBRE_HABILIDAD, DESCRIPCION, CANTIDAD_DADO) VALUES
(7, 'Legendario golpe de Paquito', 'Golpe extremadamente peligroso de paquito', 4);
INSERT INTO HABILIDAD (NUM_DADO_ID, NOMBRE_HABILIDAD, DESCRIPCION, CANTIDAD_DADO) VALUES
(5 , 'Ataque generico', 'Lanzar 2d10', 2);
-- Fin habilidades

-- Insertar PERSONAJE_HABILIDAD
INSERT INTO PERSONAJE_HABILIDAD (PERSONAJE_ID, HABILIDAD_ID) VALUES (1, 3);
INSERT INTO PERSONAJE_HABILIDAD (PERSONAJE_ID, HABILIDAD_ID) VALUES (2, 1);
INSERT INTO PERSONAJE_HABILIDAD (PERSONAJE_ID, HABILIDAD_ID) VALUES (3, 2);
INSERT INTO PERSONAJE_HABILIDAD (PERSONAJE_ID, HABILIDAD_ID) VALUES (3, 3);
INSERT INTO PERSONAJE_HABILIDAD (PERSONAJE_ID, HABILIDAD_ID) VALUES (1, 1);
INSERT INTO PERSONAJE_HABILIDAD (PERSONAJE_ID, HABILIDAD_ID) VALUES (4, 1);
-- Fin PERSONAJE_HABILIDAD

COMMIT;
