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

CREATE TABLE transport (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           type VARCHAR(50) NOT NULL,
                           speed INT NOT NULL,
                           load_volume INT NOT NULL
);

CREATE TABLE t_order (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         product_id BIGINT,
                         quantity INT NOT NULL,
                         customer_name VARCHAR(255) NOT NULL,
                         status VARCHAR(255) NOT NULL,
                         delivery_time  TIMESTAMP,
                         delivery_duration VARCHAR(255),
                         location_id BIGINT,
                         transport_id BIGINT,
                         migration_id BIGINT NOT NULL,
                         FOREIGN KEY (location_id) REFERENCES location(id),
                         FOREIGN KEY (transport_id) REFERENCES transport(id),
                         FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE INDEX idx_status ON t_order (status);
CREATE INDEX idx_migration_id ON t_order (migration_id);