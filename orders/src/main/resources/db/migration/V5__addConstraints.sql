ALTER TABLE t_order ADD CONSTRAINT t_order_ibf2k_2 FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE;
ALTER TABLE t_order ADD CONSTRAINT t_location_ib1fk_2 FOREIGN KEY (location_id) REFERENCES location(id) ON DELETE CASCADE;
