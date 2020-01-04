package com.example.spring.aop;

import com.example.spring.aop.data.BusinessObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static junit.framework.TestCase.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class AspectTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testAspectCalled() {
        BusinessObject response = restTemplate.getForObject("/test/1", BusinessObject.class);
        assertEquals(Integer.valueOf(1), response.getInputNumber());
        assertEquals(Integer.valueOf(2), response.getOutputNumber());
    }
}
