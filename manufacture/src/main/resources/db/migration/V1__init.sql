CREATE TABLE product
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255)   NOT NULL,
    brand VARCHAR(255)   NOT NULL,
    model VARCHAR(255)   NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE t_order
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id      BIGINT,
    quantity        INT          NOT NULL,
    status          VARCHAR(255) NOT NULL,
    production_end_time TIMESTAMP NOT NULL,
    production_time VARCHAR(255) NOT NULL,
    migration_id BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE manufacture
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    manufacturing_time VARCHAR(255) NOT NULL,
    product_id         BIGINT       NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (id)
);
