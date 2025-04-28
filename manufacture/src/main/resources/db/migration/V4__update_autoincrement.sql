SET @max_id = (SELECT MAX(id) FROM product);
SET @next_auto_increment = @max_id + 1;
SET @sql = CONCAT('ALTER TABLE product AUTO_INCREMENT = ', @next_auto_increment);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @max_id = (SELECT MAX(id) FROM manufacture);
SET @next_auto_increment = @max_id + 1;
SET @sql = CONCAT('ALTER TABLE manufacture AUTO_INCREMENT = ', @next_auto_increment);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;