package com.shop.demo.controllers;

import com.shop.demo.database.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartController {

    @Autowired
    DB database;

    @RequestMapping(value = "cart/add/{productId}")
    public String addBookToCart(@PathVariable Integer productId) {
        this.database.addProductToCart(productId);
        return "redirect:/main";
    }

    @RequestMapping(value = "cart/delete/{productId}")
    public String deleteBookToCart(@PathVariable Integer productId) {
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("cart", this.database.getOrderCart());
        model.addAttribute("sum", this.database.getSum());
        return "cart";
    }


}
