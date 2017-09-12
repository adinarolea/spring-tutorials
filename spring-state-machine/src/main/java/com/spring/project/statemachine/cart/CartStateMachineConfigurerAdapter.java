package com.spring.project.statemachine.cart;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.math.BigDecimal;
import java.util.EnumSet;


@Configuration
@EnableStateMachine(name = "cartStateMachine")
public class CartStateMachineConfigurerAdapter extends EnumStateMachineConfigurerAdapter<CartStates, CartEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<CartStates, CartEvents> states) throws Exception {
        states
                .withStates()
                .initial(CartStates.EMPTY)
                .end(CartStates.CHECKOUT)
                .states(EnumSet.allOf(CartStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<CartStates, CartEvents> transitions) throws Exception {
        transitions
                .withExternal()
                .source(CartStates.EMPTY)
                .target(CartStates.READY)
                .guard(context -> isValidProduct(context))
                .event(CartEvents.ADD_PRODUCT)
                .and()
                .withExternal()
                .source(CartStates.READY)
                .target(CartStates.EMPTY)
                .guard(context -> isValidProduct(context))
                .event(CartEvents.DELETE_PRODUCT)
                .and()
                .withExternal()
                .source(CartStates.READY)
                .target(CartStates.EMPTY)
                .guard(context -> isValidProduct(context))
                .event(CartEvents.UPDATE_PRODUCT)
                .and()
                .withExternal()
                .source(CartStates.READY)
                .target(CartStates.CHECKOUT)
                .event(CartEvents.GO_TO_CHECKOUT);

    }

    private boolean isValidProduct(StateContext<CartStates, CartEvents> context) {
        Product product = context.getMessageHeaders().get("product", Product.class);
        if (product.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        return true;
    }


    @Override
    public void configure(StateMachineConfigurationConfigurer<CartStates, CartEvents> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .machineId("cart-state-machine");

    }
}
