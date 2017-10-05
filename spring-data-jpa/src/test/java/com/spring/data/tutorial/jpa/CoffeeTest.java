package com.spring.data.tutorial.jpa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/initCoffeeTable.sql")
})
public class CoffeeTest {

    @Autowired
    CoffeeRepository coffeeRepository;

    @Before
    public void initDatabase() throws IOException {
        coffeeRepository.save(getCoffees());

    }

    private List<Coffee> getCoffees() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = this.getClass().getClassLoader();
        return objectMapper.readValue(classLoader.getResourceAsStream("Coffee.json"), new TypeReference<List<Coffee>>() {
        });
    }

    @Test
    public void testCoffeeInserted() {
        Coffee coffee = coffeeRepository.findById("1");
        assertNotNull(coffee);
        assertEquals("Brazil", coffee.getOrigin());
        assertTrue(BigDecimal.valueOf(22.3).compareTo(coffee.getPrice()) == 0);
        assertEquals("1", coffee.getId());

    }

    @Test
    public void testAllCoffeesInserted() throws IOException {
        List<Coffee> coffeeList = coffeeRepository.findAll();
        assertNotNull(coffeeList);
        assertEquals(getCoffees(),coffeeList);

    }
}
