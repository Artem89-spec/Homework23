package org.skypro.skyshop.model.search;

import java.util.Objects;

public final class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + contentType;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        SearchResult object = (SearchResult) other;
        return Objects.equals(id, object.id) &&
                Objects.equals(name, object.name) &&
                Objects.equals(contentType, object.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contentType);
    }

    public static SearchResult fromSearchable(Searchable searchable) {

        return new SearchResult(
                searchable.getId().toString(),
                searchable.getSearchTerm(),
                searchable.getTypeOfContent());
    }
}
