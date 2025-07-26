CREATE TABLE topicos(
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    mensaje VARCHAR(255) NOT NULL,
    fecha_creacion DATETIME NOT NULL,  -- Nombre consistente
    status BOOLEAN NOT NULL DEFAULT TRUE,
    autor VARCHAR(50) NOT NULL,
    curso VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);