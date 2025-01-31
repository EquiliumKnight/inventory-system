package com.inventarysystem.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableUtil {
    public static Pageable getPageable(int page, int size) {
        return PageRequest.of(page, size);
    }
}
