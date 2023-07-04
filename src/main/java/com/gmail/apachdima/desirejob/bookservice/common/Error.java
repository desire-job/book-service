package com.gmail.apachdima.desirejob.bookservice.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Error {

    ERROR_TEST("error.book-service.test");

    private final String key;
}
