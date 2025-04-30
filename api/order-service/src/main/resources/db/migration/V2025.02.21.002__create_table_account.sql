CREATE TABLE orders (
    id_order VARCHAR(36) NOT NULL,
    dt_date DATE NOT NULL,
    nr_total FLOAT,
    id_account VARCHAR(64) NOT NULL,
    CONSTRAINT pk_order PRIMARY KEY (id_order)
);
