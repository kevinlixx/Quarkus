
-- Crear la base de datos product_db
CREATE DATABASE product_db;
GO

-- Usar la base de datos product_db
USE product_db;
GO

-- Crear el usuario product_user
CREATE LOGIN product_user WITH PASSWORD = 'Pr0duct!pass';
GO
CREATE USER product_user FOR LOGIN product_user;
GO

-- Otorgar permisos al usuario product_user
ALTER ROLE db_owner ADD MEMBER product_user;
GO

-- Crear la tabla Product en product_db
CREATE TABLE Product (
    id INT IDENTITY(1,1) PRIMARY KEY,
    code NVARCHAR(50) NOT NULL,
    name NVARCHAR(100) NOT NULL,
    description NVARCHAR(255)
);
