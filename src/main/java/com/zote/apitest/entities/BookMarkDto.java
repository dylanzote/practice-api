package com.zote.apitest.entities;


import java.time.Instant;

public record BookMarkDto(Long id, String title, String url, Instant createdAt) {
}
