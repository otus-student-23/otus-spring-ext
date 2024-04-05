package ru.otus.mar.booklibrary.changelog;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.mar.booklibrary.dto.AuthorDto;
import ru.otus.mar.booklibrary.dto.BookCommentDto;
import ru.otus.mar.booklibrary.dto.BookDto;
import ru.otus.mar.booklibrary.dto.GenreDto;
import ru.otus.mar.booklibrary.service.BookCommentService;
import ru.otus.mar.booklibrary.service.BookService;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ChangeLog {

    private final BookService bookService;

    private final BookCommentService commentService;

    @PostConstruct
    public void init() {
        Faker faker = new Faker(new Locale("ru"));
        for (int i = 0; i < 15; i++) {
            var book = faker.book();
            var bookDto = bookService.create(new BookDto(book.title(), new AuthorDto(book.author()), new GenreDto(book.genre())));

            int commentCount = (int) (Math.random() * 10);
            for (int j = 0; j < commentCount; j++) {
                commentService.create(new BookCommentDto(bookDto, faker.matz().quote()));
            }
        }
    }
}
