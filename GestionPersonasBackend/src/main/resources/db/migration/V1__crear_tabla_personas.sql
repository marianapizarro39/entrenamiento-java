CREATE TABLE personas (
    id BIGSERIAL PRIMARY KEY, 
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    fecha_nacimiento DATE,
    CONSTRAINT uk_personas_email UNIQUE (email)
);