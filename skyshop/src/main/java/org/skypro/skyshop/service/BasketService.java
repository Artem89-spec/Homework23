package org.skypro.skyshop.service;


import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
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
        if (storageService.getProductById(id).isEmpty())
            throw new NoSuchProductException(id.toString());
        productBasket.addProducts(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> itemList = productBasket.getProducts().entrySet().stream()
                .map(entry -> {
                    UUID id = entry.getKey();
                    int numberOfProducts = entry.getValue();
                    Product product = storageService.getProductById(id)
                            .orElseThrow(() -> new NoSuchProductException(id.toString()));
                    return new BasketItem(product, numberOfProducts);
                })
                .toList();
        return new UserBasket(itemList);
    }
}
