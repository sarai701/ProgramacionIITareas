IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'umg')
BEGIN
    CREATE DATABASE umg;
END
GO

USE umg;
GO


CREATE TABLE HOTEL (
    IdHotel CHAR(5) NOT NULL PRIMARY KEY,
    Nombre VARCHAR(50),
    Direccion VARCHAR(100),
    Telefono VARCHAR(15),
    Num_Habitaciones INT
);
GO


CREATE TABLE CLIENTE (
    IdCliente INT IDENTITY(1,1) PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE
);
GO


CREATE TABLE RESERVA (
    IdReserva INT IDENTITY(1,1) PRIMARY KEY,
    IdHotel CHAR(5) NOT NULL,
    IdCliente INT NOT NULL,
    Fecha_Entrada DATE NOT NULL,
    Fecha_Salida DATE NOT NULL,
    Costo_Total MONEY,
    FOREIGN KEY (IdHotel) REFERENCES HOTEL(IdHotel),
    FOREIGN KEY (IdCliente) REFERENCES CLIENTE(IdCliente)
);
GO

-- 3 registros para HOTEL
INSERT INTO HOTEL (IdHotel, Nombre, Direccion, Telefono, Num_Habitaciones)
VALUES 
    ('H0001', 'Holiday Inn', 'Zona 10, Ciudad', '55511122', 150),
    ('H0002', 'Grand Tikal', 'Zona 1, Centro', '55533344', 80),
    ('H0003', 'Vista Bella', 'Zona 14, La Villa', '55555566', 100);
GO

-- 3 registros para CLIENTE
INSERT INTO CLIENTE (Nombre, Apellido, Email)
VALUES 
    ('Luis', 'Pérez', 'luis.perez@email.com'),
    ('Ana', 'García', 'ana.garcia@email.com'),
    ('Carlos', 'Díaz', 'carlos.diaz@email.com');
GO

-- 3 registros para RESERVA
INSERT INTO RESERVA (IdHotel, IdCliente, Fecha_Entrada, Fecha_Salida, Costo_Total)
VALUES 
    ('H0001', 1, '2025-05-10', '2025-05-15', 750.00), -- Luis Pérez reservó en H0001
    ('H0002', 2, '2025-06-01', '2025-06-05', 320.50), -- Ana García reservó en H0002
    ('H0001', 3, '2025-07-20', '2025-07-22', 250.00); -- Carlos Díaz reservó en H0001
GO