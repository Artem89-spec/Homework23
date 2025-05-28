package org.skypro.skyshop.basket;

import java.util.List;

public final class UserBasket {
    private final List<BasketItem> basketItems;
    private final int total;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        this.total = getTotal();
    }

    private int getTotal() {
        return basketItems.stream()
                .mapToInt(basketItems -> (int) basketItems.getProduct().getPrice() * basketItems.getNumberOfProducts())
                .sum();
    }

    public List<BasketItem> getBasketItems() {
        return List.copyOf(basketItems);
    }
}
