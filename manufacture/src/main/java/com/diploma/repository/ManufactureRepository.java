package com.diploma.repository;

import com.diploma.model.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufactureRepository  extends JpaRepository<Manufacture, Long> {
}
