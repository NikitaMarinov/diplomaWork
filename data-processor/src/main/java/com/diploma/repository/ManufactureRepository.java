package com.diploma.repository;

import com.diploma.model.Manufacture;
import com.diploma.model.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufactureRepository extends ElasticsearchRepository<Manufacture, Long> {
}
