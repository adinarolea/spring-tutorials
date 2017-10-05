package com.tutorial.elasticsearch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderTest {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    OrderRepository orderRepository;

    @Before
    public void before() {
        elasticsearchTemplate.deleteIndex(Order.class);
        elasticsearchTemplate.createIndex(Order.class);
        elasticsearchTemplate.putMapping(Order.class);
        elasticsearchTemplate.refresh(Order.class);
    }

    @Test
    public void testSave() {
        Order order = new Order();
        String id = randomAlphanumeric(5);
        order.setId(id);
        orderRepository.save(order);
        assertThat(orderRepository.findOne(id), is(notNullValue()));
    }

    @Test
    public void testPage(){
        Order order = new Order();
        String id = "123";
        order.setId(id);
        orderRepository.save(order);
        Order order2 = new Order();
        String id2 = "234";
        order2.setId(id2);
        orderRepository.save(order2);
        System.out.println(orderRepository.findAll());
        assertThat(orderRepository.findAll().size(),is(2));
        List<Order> orders = orderRepository.findByCreatedDateLessThanEqual(LocalDateTime.now(),new PageRequest(0, 1));
        assertThat(orders.size(), is(1));
    }
}
