package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> products;

    public ProductBasket() {
        this.products = new HashMap<>();
    }

    public void addProducts(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("UUID не может быть пустым");
        }
        products.put(id, products.getOrDefault(id, 0) + 1);
    }

    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }
}
