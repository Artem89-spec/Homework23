package org.skypro.skyshop.service;


import org.skypro.skyshop.basket.BasketItem;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.basket.UserBasket;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("UUID не может быть пустым");
        }
        Product product = storageService.getProductByIdOrThrow(id);
        productBasket.addProducts(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> itemList = productBasket.getProducts().entrySet().stream()
                .map(entry -> {
                    UUID id = entry.getKey();
                    int numberOfProducts = entry.getValue();
                    Product product = storageService.getProductByIdOrThrow(id);
                    return new BasketItem(product, numberOfProducts);
                })
                .toList();
        return new UserBasket(itemList);
    }
}
