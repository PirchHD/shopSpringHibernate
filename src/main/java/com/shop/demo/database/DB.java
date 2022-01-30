package com.shop.demo.database;

import com.shop.demo.database.entity.CommonProducts;
import com.shop.demo.model.FoodProduct;
import com.shop.demo.model.OrderCartPosition;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import java.util.List;

@Component
public class DB {

    private List<CommonProducts> products = new ArrayList<>();
    private List<OrderCartPosition> orderCart = new ArrayList<>();

    public List<OrderCartPosition> getOrderCart() {
        return orderCart;
    }

    public void addProductToCart(OrderCartPosition orderCartPosition){
        orderCart.add(orderCartPosition);
    }

    public void addProductToCart(int bookId) {

        CommonProducts product = getProductById(bookId);
        if(product == null)
            return;

        if(!(product.getQuantity() > 0)) {
            return;
        }else{
            product.setQuantity(product.getQuantity() - 1);
        }

        for(OrderCartPosition orderCartPosition : this.orderCart) {
            if(orderCartPosition.getProduct().getId() == bookId) {
                orderCartPosition.incrementQuantity();
                return;
            }
        }

        OrderCartPosition orderCartPosition = new OrderCartPosition(product, 1);

        addProductToCart(orderCartPosition);
    }

    public CommonProducts getProductById(int bookId) {
        CommonProducts commonProducts = new CommonProducts();

        return commonProducts.loadById(bookId);
    }

    public void removeProductInCart(OrderCartPosition orderCartPosition){
        orderCart.remove(orderCartPosition);
    }

    public DB() {
        products = CommonProducts.loadAll();
    }

    public Boolean deleteProductById(int id) {
        CommonProducts commonProducts = getProductById(id);

        return commonProducts.delete(id);
    }

    public OrderCartPosition getProductInCartById(int bookId) {
        for(OrderCartPosition product : this.orderCart) {
            if(product.getProduct().getId() == bookId) {
                return product;
            }
        }
        return null;
    }

    public BigDecimal getSum() {
        double sum = 0.0;
        for(OrderCartPosition orderPosition : this.getOrderCart()) {
            sum += orderPosition.getQuantity() * orderPosition.getProduct().getPrice();
        }

        return BigDecimal.valueOf(sum).setScale(2, RoundingMode.DOWN);
    }

    public List<CommonProducts> getProducts() {
        return CommonProducts.loadAll();
    }

    public String getListOrderCart() {
        String result = "";

        for (int i = 0; i < orderCart.size(); i++){
            int number = i + 1;

            OrderCartPosition orderCartPosition = orderCart.get(i);

            if(orderCartPosition == null)
                continue;

            result += number + ". " + orderCartPosition.getProduct().getName() + " " +
                    orderCartPosition.getQuantity() + " szt. za " +
                    orderCartPosition.getProduct().getPrice() + " zł" + "\n" ;
        }

        return result;
    }

}
