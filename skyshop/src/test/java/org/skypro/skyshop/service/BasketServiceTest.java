package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.basket.BasketItem;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.basket.UserBasket;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.product.Product;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasketServiceTest {
    private ProductBasket productBasketMock;
    private StorageService storageServiceMock;
    private BasketService basketService;

    @BeforeEach
    public void setUp() {
        productBasketMock = mock(ProductBasket.class);
        storageServiceMock = mock(StorageService.class);
        basketService = new BasketService(productBasketMock, storageServiceMock);
    }

    /**
     * Добавление несуществующего товара в корзину приводит к выбросу исключения.
     */
    @Test
    public void testAdd_NonExistentProductThrowsAnException() {
        UUID falseId = UUID.randomUUID();

        when(storageServiceMock.getProductById(falseId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(falseId));

        verify(storageServiceMock).getProductById(falseId);
        verifyNoInteractions(productBasketMock);
    }

    /**
     * Добавление существующего товара вызывает метод addProduct у мока ProductBasket.
     */
    @Test
    public void testAdd_ExistingProductCallsAddProduct() {
        UUID productId = UUID.randomUUID();

        Product mockProduct = mock(Product.class);

        when(storageServiceMock.getProductById(productId)).thenReturn(Optional.of(mockProduct));

        basketService.addProduct(productId);

        verify(productBasketMock).addProducts(productId);
        verify(storageServiceMock).getProductById(productId);
    }

    /**
     *Метод getUserBasket возвращает пустую корзину, если ProductBasket пуст.
     */
    @Test
    public void testGetUserBasketEmpty() {
        when(productBasketMock.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        assertTrue(userBasket.getBasketItems().isEmpty());
    }

    /**
     * Метод getUserBasket возвращает подходящую корзину, если в ProductBasket есть товары.
     */
    @Test
    public void testGetUserBasketWithItems() {
        UUID productId = UUID.randomUUID();

        Map<UUID, Integer> productsMap = Map.of(productId, 3);
        when(productBasketMock.getProducts()).thenReturn(productsMap);

        Product mockProduct = mock(Product.class);
        when(mockProduct.getPrice()).thenReturn(100.00);

        when(storageServiceMock.getProductById(productId)).thenReturn(Optional.of(mockProduct));

        UserBasket basket = basketService.getUserBasket();

        assertEquals(1, basket.getBasketItems().size());

        BasketItem item = basket.getBasketItems().get(0);
        assertEquals(mockProduct, item.getProduct());
        assertEquals(3, item.getNumberOfProducts());

    }
    /**
     * Добавление продукта с null значением поля Id.
     */
    @Test
    public void testAdd_WithNullIdProduct() {
        assertThrows(IllegalArgumentException.class, () ->basketService.addProduct(null));
        verifyNoInteractions(storageServiceMock);
    }

}
