package com.gmail.apachdima.desirejob.bookservice.service.impl;

import com.gmail.apachdima.desirejob.bookservice.model.Book;
import com.gmail.apachdima.desirejob.bookservice.service.BookService;
import com.gmail.apachdima.desirejob.bookservice.repository.BookRepository;
import com.gmail.apachdima.desirejob.bookservice.util.mapper.BookMapper;
import com.gmail.apachdima.desirejob.commonservice.constant.Model;
import com.gmail.apachdima.desirejob.commonservice.constant.message.CommonError;
import com.gmail.apachdima.desirejob.commonservice.dto.book.BookRequestDTO;
import com.gmail.apachdima.desirejob.commonservice.dto.book.BookResponseDTO;
import com.gmail.apachdima.desirejob.commonservice.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final MessageSource messageSource;
    private final BookMapper bookMapper;

    @Override
    public Page<BookResponseDTO> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::toResponseDTO);
    }

    @Override
    public BookResponseDTO findById(long bookId, Locale locale) {
        return bookMapper.toResponseDTO(getBookById(bookId, locale));
    }

    @Override
    public BookResponseDTO create(BookRequestDTO bookRequestDTO) {
        Book book = Book.builder()
            .title(bookRequestDTO.getTitle())
            .author(bookRequestDTO.getAuthor())
            .addedAt(LocalDateTime.now())
            .build();
        return bookMapper.toResponseDTO(bookRepository.save(book));
    }

    @Override
    public BookResponseDTO update(long bookId, BookRequestDTO bookRequestDTO, Locale locale) {
        Book book = getBookById(bookId, locale);
        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(bookRequestDTO.getAuthor());
        return bookMapper.toResponseDTO(bookRepository.save(book));
    }

    @Override
    public void deleteById(long bookId) {
        bookRepository.deleteById(bookId);
    }

    private Book getBookById(long bookId, Locale locale) {
        Object[] params = new Object[]{Model.BOOK.getName(), Model.Field.ID.getFieldName(), bookId};
        return bookRepository.findById(bookId)
            .orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage(CommonError.ENTITY_NOT_FOUND.getKey(), params, locale)));
    }
}
