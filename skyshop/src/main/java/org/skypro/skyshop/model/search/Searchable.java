package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String getSearchTerm();

    String getTypeOfContent();

    UUID getId();

    default String getStringRepresentation() {
        return "Наименование: " + getSearchTerm() + " - " + "тип: " + getTypeOfContent();

    }
}
