// esse tipo de arquivo é executado automaticamente pelo Spring Boot ao iniciar a aplicação
// ele é usado para popular o banco de dados com dados iniciais


INSERT INTO CATEGORY (id, description) VALUES (1, 'Eletrônicos');
INSERT INTO CATEGORY (id, description) VALUES (2, 'Celulares');
INSERT INTO CATEGORY (id, description) VALUES (3, 'Informática');


INSERT INTO SUPPLIER (id, name) VALUES (1, 'Amazon');

INSERT INTO PRODUCT (id, name, quantity_available, fk_category, fk_supplier) VALUES (1, 'Smartphone com tela de 6.5 polegadas', 15, 2, 1);
INSERT INTO PRODUCT (id, name, quantity_available, fk_category, fk_supplier) VALUES (2, 'Notebook com processador i7 e 16GB de RAM', 5, 3, 1);
INSERT INTO PRODUCT (id, name, quantity_available, fk_category, fk_supplier) VALUES (3, 'Smart TV 4K de 55 polegadas', 2, 1, 1);
