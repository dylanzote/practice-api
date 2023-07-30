package com.zote.apitest.services;

import com.zote.apitest.entities.BookMark;
import com.zote.apitest.entities.BookMarkDto;
import com.zote.apitest.repository.IBookMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookMarkService {

    private final IBookMarkRepository bookMarkRepository;

    @Transactional(readOnly = true)
    public List<BookMarkDto> getBookMarks(Integer page) {
        var pageNo = page < 0 ? 0 : page - 1;
        var pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
        var bookMarkList = bookMarkRepository.findAll(pageable).getContent();
        return bookMarkList.stream()
                .map(bookMark -> new BookMarkDto(bookMark.getId(), bookMark.getTitle(), bookMark.getUrl(), bookMark.getCreatedAt()))
                .toList();
    }

    public void saveBookMarks(BookMarkDto bookMarkDto) {
        var bookMark = BookMark.builder()
                .createdAt(Instant.now())
                .id(bookMarkDto.id())
                .title(bookMarkDto.title())
                .url(bookMarkDto.url())
                .build();
        bookMarkRepository.save(bookMark);
    }

    public BookMarkDto findBookMarkById(long id) {
        var optionalBookMark = bookMarkRepository.findById(id);
        if (optionalBookMark.isPresent()) {
            var bookMark = optionalBookMark.get();
            return new BookMarkDto(bookMark.getId(), bookMark.getTitle(), bookMark.getUrl(), bookMark.getCreatedAt());
        } else {
            throw new RuntimeException("no bookMark found");
        }
    }
}
