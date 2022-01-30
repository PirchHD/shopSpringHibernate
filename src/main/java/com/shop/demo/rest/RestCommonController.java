package com.shop.demo.rest;

import com.shop.demo.database.DB;
import com.shop.demo.database.entity.CommonProducts;
import com.shop.demo.model.OrderCartPosition;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class RestCommonController {

    private final DB database;

    @GetMapping("/products")
    List<CommonProducts> findAll() {
        List<CommonProducts> products = this.database.getProducts();
        return products;
    }

    @GetMapping("/products/{id}")
    CommonProducts getProduct(@PathVariable int id) {
        CommonProducts products = this.database.getProductById(id);
        return products;
    }

    @PostMapping("/products")
    public ProductRest createProduct(@RequestBody CommonProducts product){

        CommonProducts.insert(product);

        ProductRest productRest = new ProductRest();
        productRest.setId(product.getId());
        productRest.setName(product.getName());
        productRest.setQuantity(product.getQuantity());
        productRest.setPrice(product.getPrice());

        return productRest;
    }

    @DeleteMapping("/products/{id}")
    public boolean remove(@PathVariable int id){

        return this.database.deleteProductById(id);
    }

    @PostMapping("/orderProd/{id}")
    public void addToOrder(@PathVariable String id){
        int idInt = 0;

        try {
            idInt = Integer.valueOf(id);
            this.database.addProductToCart(idInt);
        }catch (Exception e){
            return;

        }
    }

    @GetMapping("/orderProd")
    public List<OrderCartPosition> getAllOrderProd(){
        return this.database.getOrderCart();
    }


}
