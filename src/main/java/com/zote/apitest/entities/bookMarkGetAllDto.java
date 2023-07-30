package com.zote.apitest.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

import java.util.List;

public record bookMarkGetAllDto(List<BookMarkDto> data, long totalElements, int totalPages, int currentPage, @JsonProperty("isFirst") boolean isFirst, @JsonProperty("isLast") boolean isLast, boolean hasNext, boolean hasPrevious) {
    public bookMarkGetAllDto(Page<BookMarkDto> bookMarkPage) {
        this(bookMarkPage.getContent(), bookMarkPage.getTotalElements(), bookMarkPage.getTotalPages(), bookMarkPage.getNumber() + 1, bookMarkPage.isFirst(), bookMarkPage.isLast(), bookMarkPage.hasNext(), bookMarkPage.hasPrevious());
    }
}
