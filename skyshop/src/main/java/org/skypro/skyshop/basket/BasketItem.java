package org.skypro.skyshop.basket;


import org.skypro.skyshop.product.Product;

public final class BasketItem {
    private final Product product;
    private final int numberOfProducts;

    public BasketItem(Product product, int numberOfProducts) {
        this.product = product;
        this.numberOfProducts = numberOfProducts;
    }

    public Product getProduct() {
        return product;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }
}
