
INSERT INTO MARCAS(ID, NOMBRE_MARCA)
VALUES (1, 'mazda'),
       (2, 'seat'),
       (3, 'peugeot'),
       (4, 'audi'),
       (5, 'mercedes');
INSERT INTO COCHES (ID, ID_MARCA, NOMBRE_MODELO, COLOR)
VALUES (1, 1, 'cx-3', 'rojo'),
       (2, 2, 'león', 'gris'),
       (3, 3, '2008', 'verde'),
       (4, 4, 'a4', 'negro'),
       (5, 5, 'evoque', 'Blanco');
INSERT INTO PRECIOS (ID, ID_COCHE, FECHA_INICIO, FECHA_FIN, PRECIO)
VALUES (1,1,'2021-05-07','2022-06-07',25000),
       (2,2,'2021-05-07','2022-06-07',20000),
       (3,3,'2021-05-07','2022-06-07',15000),
       (4,4,'2021-05-07','2022-06-07',35000),
       (5,5,'2021-05-07','2022-06-07',45000);