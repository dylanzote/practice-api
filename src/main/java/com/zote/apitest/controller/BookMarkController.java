package com.zote.apitest.controller;

import com.zote.apitest.entities.BookMarkDto;
import com.zote.apitest.services.BookMarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
@Slf4j
public class BookMarkController {

    private final BookMarkService bookMarkService;

    @GetMapping("/getAll")
    public List<BookMarkDto> getBookMarks(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        log.info("incoming request to get all bookMark with page {}", page);
        return bookMarkService.getBookMarks(page);
    }

    @PostMapping("/create")
    public void createBookMark(@RequestBody BookMarkDto bookMarkDto) {
        log.info("incoming request to create bookMark with data {}", bookMarkDto);
        bookMarkService.saveBookMarks(bookMarkDto);
    }

    @PutMapping("/update")
    public void updateBookMark(@RequestBody BookMarkDto bookMarkDto) {
        var foundBookMark = bookMarkService.findBookMarkById(bookMarkDto.id());
        bookMarkService.saveBookMarks(foundBookMark);
    }
}
