-- Crear la base de datos
-- Crear la base de datos
CREATE DATABASE customer_db;
GO

-- Usar la base de datos
USE customer_db;
GO

-- Crear el usuario
CREATE LOGIN customer_user WITH PASSWORD = 'Cust0mer!Pass';
GO
CREATE USER customer_user FOR LOGIN customer_user;
GO

-- Otorgar permisos al usuario
ALTER ROLE db_owner ADD MEMBER customer_user;
GO

-- Crear la secuencia Customer_SEQ
CREATE SEQUENCE Customer_SEQ
    START WITH 1
    INCREMENT BY 1;
GO

-- Crear la tabla Customer
CREATE TABLE Customer (
    id INT IDENTITY(1,1) PRIMARY KEY,
    code NVARCHAR(50) NOT NULL,
    accountNumber NVARCHAR(50) NOT NULL,
    names NVARCHAR(100) NOT NULL,
    surname NVARCHAR(100) NOT NULL,
    phone NVARCHAR(20),
    address NVARCHAR(255)
);
GO

-- Crear la tabla Product
CREATE TABLE Product (
    id INT IDENTITY(1,1) PRIMARY KEY,
    customer INT NOT NULL,
    product BIGINT NOT NULL,
    name NVARCHAR(100),
    code NVARCHAR(50),
    description NVARCHAR(255),
    CONSTRAINT FK_Customer_Product FOREIGN KEY (customer) REFERENCES Customer(id),
    CONSTRAINT UQ_Customer_Product UNIQUE (customer, product)
);
GO