package org.skypro.skyshop.service;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productsMap;
    private final Map<UUID, Article> articlesMap;

    public StorageService() {
        this.productsMap = new HashMap<>();
        this.articlesMap = new HashMap<>();
        createData();
    }

    public Collection<Product> getAllProducts() {
        return productsMap.values();
    }

    public Collection<Article> getAllArticles() {
        return articlesMap.values();
    }
    public Collection<Searchable> getAllSearchableResults() {
        List<Searchable> results = new ArrayList<>();
        results.addAll(productsMap.values());
        results.addAll(articlesMap.values());
        return results;
    }

    public Product getProductByIdOrThrow(UUID id) {
        return Optional.ofNullable(productsMap.get(id))
                .orElseThrow(() -> new NoSuchProductException(id.toString()));
    }

    private void createData() {
        List<Product> testProducts = Arrays.asList(
                new DiscountedProduct("Мышь проводная", 1000, 5),
                new DiscountedProduct("Клавиатура беспроводная", 15_000, 13),
                new DiscountedProduct("Мышь беспроводная", 1_500, 10),
                new DiscountedProduct("Наушники", 8_000, 5),
                new FixPriceProduct("Клавиатура"),
                new FixPriceProduct("Веб-камера"),
                new FixPriceProduct("Камера"),
                new SimpleProduct("Фронтальные колонки", 15_000),
                new SimpleProduct("Монитор", 40_000),
                new SimpleProduct("Фронтальные колонки 5.0", 33_000),
                new SimpleProduct("Наушники беспроводные", 9_000),
                new SimpleProduct("Процессор Intel Core i7 13700 KF", 26_000),
                new SimpleProduct("Процессор Intel Core i7 13700 K", 35_000)
                );
        for (Product product : testProducts) {
            productsMap.put(product.getId(), product);
        }
        List<Article> testArticles = Arrays.asList(
                new Article("Периферийные устройства", "Мышь беспроводная"),
                new Article("Наушники и аудиотехника", "Фронтальные колонки"),
                new Article("Периферийные устройства", "Клавиатура"),
                new Article("Периферийные устройства", "Веб-камера"),
                new Article("Наушники и аудиотехника", "Наушники"),
                new Article("Периферийные устройства", "Мышь проводная"),
                new Article("Наушники и аудиотехника", "Фронтальные колонки 5.0"),
                new Article("Периферийные устройства", "Клавиатура беспроводная"),
                new Article("Периферийные устройства", "Камера"),
                new Article("Наушники и аудиотехника", "Наушники беспроводные"),
                new Article("Процессоры", "Процессор Intel Core i7 13700 KF"),
                new Article("Процессоры", "Процессор Intel Core i7 13700 K"),
                new Article("Процессоры", "Процессор Intel Core i9 13900 K")
        );
        for (Article article : testArticles) {
            articlesMap.put(article.getId(), article);
        }
    }
}
