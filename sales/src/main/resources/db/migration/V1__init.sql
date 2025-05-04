CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         brand VARCHAR(255) NOT NULL,
                         model VARCHAR(255) NOT NULL,
                         price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE location (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          city VARCHAR(255) NOT NULL,
                          country VARCHAR(255) NOT NULL,
                          distance_to_warehouse INT
);

CREATE TABLE t_order (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         product_id BIGINT,
                         quantity INT NOT NULL,
                         status VARCHAR(255) NOT NULL,
                         order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                         price DECIMAL(20, 2) NOT NULL,
                         location_id BIGINT,
                         migration_id BIGINT NOT NULL,
                         FOREIGN KEY (location_id) REFERENCES location(id),
                         FOREIGN KEY (product_id) REFERENCES product(id)
);
