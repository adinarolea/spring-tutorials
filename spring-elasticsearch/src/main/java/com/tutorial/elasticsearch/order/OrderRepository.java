package com.tutorial.elasticsearch.order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ElasticsearchRepository<Order,String> {

    List<Order> findAll();

    List<Order> findByCreatedDateLessThanEqual(LocalDateTime localDateTime, Pageable pageable);
}
