/* Populate tabla users */

INSERT INTO usuarios ( dni, nombre, apellido, direccion  ) VALUES('12345','Daniel','Acosta', 'cr 1# 13-25');
INSERT INTO usuarios ( dni, nombre, apellido, direccion  ) VALUES('54321','Carlos','Ramirez' ,'cr 20# 12-02');


INSERT INTO productos (nombre, precio) VALUES('Panasonic Pantalla LCD', 25999);
INSERT INTO productos (nombre, precio) VALUES('Sony Camara digital DSC-W320B', 12349);
INSERT INTO productos (nombre, precio) VALUES('Apple iPod shuffle', 159999);
INSERT INTO productos (nombre, precio) VALUES('Sony Notebook Z110', 37990);
INSERT INTO productos (nombre, precio) VALUES('Hewlett Packard Multifuncional F2280', 60990);
INSERT INTO productos (nombre, precio) VALUES('Bianchi Bicicleta Aro 26', 67990);
INSERT INTO productos (nombre, precio) VALUES('Mica Comoda 5 Cajones', 30990);

INSERT INTO pedidos (estado, fecha_pedido, observacion, usuario_id) VALUES('Realizado','2021-02-21 09:17:35', 'Factura equipos de oficina',  1);


INSERT INTO pedido_lineas (cantidad, producto_id, pedido_id) VALUES(1, 1, 1);
INSERT INTO pedido_lineas (cantidad, producto_id, pedido_id) VALUES(2, 4, 1);
INSERT INTO pedido_lineas (cantidad, producto_id, pedido_id) VALUES(3, 5, 1);
INSERT INTO pedido_lineas (cantidad, producto_id, pedido_id) VALUES(4, 7, 1);