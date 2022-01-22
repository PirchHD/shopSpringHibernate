package com.shop.demo.model;

import com.shop.demo.database.entity.CommonProducts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderCartPosition {
    private CommonProducts product;
    private int quantity;
    private double sumProducts;

    public BigDecimal getSumProducts() {
        sumProducts = quantity * product.getPrice();
        return BigDecimal.valueOf(sumProducts).setScale(2, RoundingMode.DOWN);
    }

    public OrderCartPosition(CommonProducts product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OrderCartPosition() {
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void minusQuantity() {
        this.quantity--;
    }

    public CommonProducts getProduct() {
        return product;
    }

    public void setBook(CommonProducts product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

}
