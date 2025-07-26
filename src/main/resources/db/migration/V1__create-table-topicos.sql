CREATE TABLE topicos(
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    mensaje VARCHAR(255) NOT NULL, -- Increased size
    fechaCreacion DATETIME NOT NULL, -- Changed to DATETIME
    status BOOLEAN NOT NULL,
    autor VARCHAR(50) NOT NULL,
    curso VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);