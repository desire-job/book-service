package com.gmail.apachdima.desirejob.bookservice.util.mapper;

import com.gmail.apachdima.desirejob.bookservice.model.Book;
import com.gmail.apachdima.desirejob.commonservice.dto.book.BookResponseDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {

    BookResponseDTO toResponseDTO(Book book);
}
