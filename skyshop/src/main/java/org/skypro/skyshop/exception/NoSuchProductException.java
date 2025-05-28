package org.skypro.skyshop.exception;

public class NoSuchProductException extends RuntimeException {
    private final String productId;

    public NoSuchProductException(String productId) {
        this.productId = productId;
    }

    @Override
    public String getMessage() {
        return "Для указанного поискового запроса { " + productId + " } не найдено подходящей категории товара.";
    }
}
