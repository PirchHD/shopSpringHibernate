package com.shop.demo.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ProductRest {
    private Integer id;
    private String name;
    private Double price;
    private int quantity;
}
