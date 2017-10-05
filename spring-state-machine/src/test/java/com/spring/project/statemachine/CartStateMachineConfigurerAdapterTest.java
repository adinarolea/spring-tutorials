package com.spring.project.statemachine;

import com.spring.project.statemachine.cart.CartEvents;
import com.spring.project.statemachine.cart.CartStates;
import com.spring.project.statemachine.cart.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartStateMachineConfigurerAdapterTest {

    @Autowired
    StateMachineProvider stateMachineProvider;

    @Test
    public void testCartStateMachineExists() {
        StateMachine stateMachine = stateMachineProvider.getStateMachine("cart-state-machine");
        assertNotNull(stateMachine);
    }

    @Test
    public void testAddItem() {
        StateMachine<CartStates, CartEvents> stateMachine = stateMachineProvider.getStateMachine("cart-state-machine");
        Product product = new Product("122");
        product.setQuantity(BigDecimal.TEN);
        Message<CartEvents> message = MessageBuilder
                .withPayload(CartEvents.ADD_PRODUCT)
                .setHeader("product", product)
                .build();
        stateMachine.sendEvent(message);
        assertEquals(CartStates.READY, stateMachine.getState().getId());
    }
}
