-- =====================================================
-- CREAR BASE DE DATOS
-- =====================================================
DROP DATABASE IF EXISTS tienda_demo;
CREATE DATABASE tienda_demo;
USE tienda_demo;

-- =====================================================
-- TABLA CLIENTES
-- =====================================================
CREATE TABLE clientes (
    cliente_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(200)
);

-- =====================================================
-- TABLA PRODUCTOS
-- =====================================================
CREATE TABLE productos (
    producto_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0
);

-- =====================================================
-- TABLA ORDENES
-- =====================================================
CREATE TABLE ordenes (
    orden_id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    fecha DATE NOT NULL,
    total DECIMAL(10,2) DEFAULT 0,
    FOREIGN KEY (cliente_id) 
        REFERENCES clientes(cliente_id) 
        ON DELETE CASCADE
);

-- =====================================================
-- TABLA ORDEN_ITEMS (detalle de cada orden)
-- =====================================================
CREATE TABLE orden_items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    orden_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (orden_id) 
        REFERENCES ordenes(orden_id) 
        ON DELETE CASCADE,
    FOREIGN KEY (producto_id) 
        REFERENCES productos(producto_id) 
        ON DELETE CASCADE
);

-- =====================================================
-- DATOS DE PRUEBA
-- =====================================================

-- Insertar clientes
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES
('Juan Pérez', 'juan@example.com', '555-1234', 'Calle Falsa 123'),
('María Gómez', 'maria@example.com', '555-5678', 'Avenida Central 456');

-- Insertar productos
INSERT INTO productos (nombre, precio, stock) VALUES
('Laptop Lenovo', 750.00, 10),
('Mouse Logitech', 25.00, 50),
('Teclado Mecánico', 60.00, 30);

-- Crear una orden para Juan Pérez
INSERT INTO ordenes (cliente_id, fecha, total) VALUES (1, '2025-09-19', 0);

-- Insertar items en la orden
INSERT INTO orden_items (orden_id, producto_id, cantidad, precio_unitario) VALUES
(1, 1, 1, 750.00),  -- 1 Laptop
(1, 2, 2, 25.00);   -- 2 Mouse

-- Actualizar el total de la orden
UPDATE ordenes 
SET total = (
    SELECT SUM(cantidad * precio_unitario) 
    FROM orden_items 
    WHERE orden_id = 1
)
WHERE orden_id = 1;

SELECT 
    o.orden_id,
    c.nombre AS cliente,
    p.nombre AS producto,
    oi.cantidad,
    oi.precio_unitario,
    (oi.cantidad * oi.precio_unitario) AS subtotal,
    o.total AS total_orden
FROM ordenes o
JOIN clientes c ON o.cliente_id = c.cliente_id
JOIN orden_items oi ON o.orden_id = oi.orden_id
JOIN productos p ON oi.producto_id = p.producto_id
WHERE o.orden_id = 1;


