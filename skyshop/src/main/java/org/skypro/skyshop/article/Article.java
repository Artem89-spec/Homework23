package org.skypro.skyshop.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String articleTitle;
    private final String contentOfArticle;
    private final UUID id;

    public Article(UUID id, String articleTitle, String contentOfArticle) {
        this.articleTitle = articleTitle;
        this.contentOfArticle = contentOfArticle;
        this.id = id;
    }

    public Article(String articleTitle, String contentOfArticle) {
        this(UUID.randomUUID(), articleTitle, contentOfArticle);
    }

    @Override
    public String toString() {
        return articleTitle + " " + contentOfArticle;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        Article object = (Article) other;
        return Objects.equals(contentOfArticle, object.contentOfArticle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentOfArticle);
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getTypeOfContent() {
        return "ARTICLE";
    }

    @Override
    public UUID getId() {
        return id;
    }
}
