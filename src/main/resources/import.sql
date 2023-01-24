INSERT INTO regions (id, name) VALUES (1, 'Sudamérica');
INSERT INTO regions (id, name) VALUES (2, 'Centroamérica');
INSERT INTO regions (id, name) VALUES (3, 'Norteamérica');
INSERT INTO regions (id, name) VALUES (4, 'Europa');
INSERT INTO regions (id, name) VALUES (5, 'Asia');
INSERT INTO regions (id, name) VALUES (6, 'Africa');
INSERT INTO regions (id, name) VALUES (7, 'Oceanía');
INSERT INTO regions (id, name) VALUES (8, 'Antártida');

INSERT INTO clients (region_id, name, last_name, email, create_at) VALUES(4, 'Adrián', 'Navarro', 'adrian@adrian.com', '2020-04-06');
INSERT INTO clients (region_id, name, last_name, email, create_at) VALUES(2, 'Mr. John', 'Doe', 'john.doe@gmail.com', '2018-01-02');
INSERT INTO clients (region_id, name, last_name, email, create_at) VALUES(4, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2018-01-03');
INSERT INTO clients (region_id, name, last_name, email, create_at) VALUES(4, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2018-01-04');
INSERT INTO clients (region_id, name, last_name, email, create_at) VALUES(4, 'Erich', 'Gamma', 'erich.gamma@gmail.com', '2018-02-01');
INSERT INTO clients (region_id, name, last_name, email, create_at) VALUES(3, 'Richard', 'Helm', 'richard.helm@gmail.com', '2018-02-10');
INSERT INTO clients (region_id, name, last_name, email, create_at) VALUES(3, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2018-02-18');
INSERT INTO clients (region_id, name, last_name, email, create_at) VALUES(3, 'John', 'Vlissides', 'john.vlissides@gmail.com', '2018-02-28');
INSERT INTO clients (region_id, name, last_name, email, create_at) VALUES(3, 'Dr. James', 'Gosling', 'james.gosling@gmail.com', '2018-03-03');
INSERT INTO clients (region_id, name, last_name, email, create_at) VALUES(5, 'Magma', 'Lee', 'magma.lee@gmail.com', '2018-03-04');
INSERT INTO clients (region_id, name, last_name, email, create_at) VALUES(6, 'Tornado', 'Roe', 'tornado.roe@gmail.com', '2018-03-05');
INSERT INTO clients (region_id, name, last_name, email, create_at) VALUES(7, 'Jade', 'Doe', 'jane.doe@gmail.com', '2018-03-06');

INSERT INTO products (name, price, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO products (name, price, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

INSERT INTO invoices (description, observation, client_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());

INSERT INTO items_invoice (quantity, invoice_id, product_id) VALUES(1, 1, 1);
INSERT INTO items_invoice (quantity, invoice_id, product_id) VALUES(2, 1, 4);
INSERT INTO items_invoice (quantity, invoice_id, product_id) VALUES(1, 1, 5);
INSERT INTO items_invoice (quantity, invoice_id, product_id) VALUES(1, 1, 7);

INSERT INTO invoices (description, observation, client_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO items_invoice (quantity, invoice_id, product_id) VALUES(3, 2, 6);