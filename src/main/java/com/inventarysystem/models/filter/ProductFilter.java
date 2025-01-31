package com.inventarysystem.models.filter;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductFilter {
    private String category;
    private BigDecimal min;
    private BigDecimal max;
    private Integer minStock;
}
