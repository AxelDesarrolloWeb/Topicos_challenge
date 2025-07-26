CREATE TABLE usuarios(  -- Cambiado nombre de tabla
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(300) NOT NULL,  -- Corregido nombre
    PRIMARY KEY(id)
);