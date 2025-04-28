CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         brand VARCHAR(255) NOT NULL,
                         model VARCHAR(255) NOT NULL,
                         price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE t_order (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         product_id BIGINT,
                         price DECIMAL(10, 2) NOT NULL,
                         quantity INT NOT NULL,
                         order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                         customer_name VARCHAR(255) NOT NULL,
                         status VARCHAR(255) NOT NULL,
                         FOREIGN KEY (product_id) REFERENCES product(id)
);
