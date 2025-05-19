package org.skypro.skyshop.model.exception;

public class NoSuchProductException extends RuntimeException {
    private final String searchQuery;

    public NoSuchProductException(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public String getMessage() {
        return "Для указанного поискового запроса { " + searchQuery + " } не найдено подходящей категории товара.";
    }
}
