package com.spring.project.statemachine.cart;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private Map<String, Product> items = new LinkedHashMap<>();

    public void addToCart(Product product) {
        Product item = items.get(product.getCode());
        if (item != null) {
            item.setQuantity(item.getQuantity().add(product.getQuantity()));
        } else {
            items.put(product.getCode(), product);
        }
    }

    public void removeFromCart(int productCode) {
        items.remove(productCode);
    }

    public void removeFromCart(Product product) {
        items.remove(product.getCode());
    }

    public void updateQuantity(String productCode, BigDecimal quantity) {
        Product product = items.get(productCode);
        if (product == null) {
            throw new CartException("Product with code : " + productCode + " does not exist ! UPDATE failed");
        }
        product.setQuantity(quantity);
    }
}
