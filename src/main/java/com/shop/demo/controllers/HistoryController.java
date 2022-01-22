package com.shop.demo.controllers;

import com.shop.demo.database.DB;
import com.shop.demo.database.UserData;
import com.shop.demo.database.entity.CommonCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HistoryController {

    @Autowired
    DB database;

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("cartHistory", CommonCart.getHistoryOrderCart(UserData.getUser().getId()));
        return "/history";
    }
}
