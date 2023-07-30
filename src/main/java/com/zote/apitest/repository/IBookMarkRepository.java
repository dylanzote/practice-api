package com.zote.apitest.repository;

import com.zote.apitest.entities.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookMarkRepository extends JpaRepository<BookMark, Long> {
}
