package org.skypro.skyshop.search;

import java.util.UUID;

public interface Searchable {
    String getSearchTerm();

    String getTypeOfContent();

    UUID getId();

    default String getStringRepresentation() {
        return "Наименование: " + getSearchTerm() + " - " + "тип: " + getTypeOfContent();

    }
}
