package com.tutorial.elasticsearch.order.repository;

import com.tutorial.elasticsearch.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ElasticsearchRepository<Order,String> {

    Page<Order> findAll();

    List<Order> findByCreatedDateLessThanEqual(LocalDateTime localDateTime, Pageable pageable);
}
