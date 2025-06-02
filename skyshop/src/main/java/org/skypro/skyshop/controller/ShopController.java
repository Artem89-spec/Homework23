package org.skypro.skyshop.controller;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.UserBasket;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.skypro.skyshop.service.BasketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;
    private final ProductBasket productBasket;
    private final BasketService basketService;

    public ShopController(StorageService storageService, SearchService searchService, ProductBasket productBasket,
                          BasketService basketService) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
        this.productBasket = productBasket;
    }

    @GetMapping("/products")
    public Collection<Product> getProducts() {
        return storageService.getAllProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getArticles() {
        return storageService.getAllArticles();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam("pattern") String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        Optional<Product> product = storageService.getProductById(id);
        if (product.isPresent()) {
            productBasket.addProducts(id);
            return "Продукт успешно добавлен";
        } else {
            throw new NoSuchProductException(id.toString());
        }
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }
}
