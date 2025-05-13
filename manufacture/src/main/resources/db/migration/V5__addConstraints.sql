ALTER TABLE t_order ADD CONSTRAINT t_order_ibf2k_2 FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE;
ALTER TABLE manufacture ADD CONSTRAINT fk_manufacture_product FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE;
