USE DNCONTROL;

START TRANSACTION;

-- Insercion usuarios
INSERT INTO USUARIO (EMAIL, NOMBRE_USUARIO, PASSWD) VALUES
('admin@gmail.com', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
-- Fin usuarios

COMMIT;