package com.diploma.repository;

import com.diploma.model.Order;
import com.diploma.model.Sales;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends ElasticsearchRepository<Sales, Long> {
}
