package com.diploma.repository;

import com.diploma.model.Logistics;
import com.diploma.model.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsRepository extends ElasticsearchRepository<Logistics, Long> {
}
