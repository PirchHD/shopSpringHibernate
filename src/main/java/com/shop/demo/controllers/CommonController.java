package com.shop.demo.controllers;

import com.shop.demo.database.DB;
import com.shop.demo.database.CreditCart;
import com.shop.demo.database.UserData;
import com.shop.demo.database.entity.CommonCart;
import com.shop.demo.database.entity.CommonLogin;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommonController{

    @Autowired
    DB database;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("User", UserData.getUser());
        model.addAttribute("products", this.database.getProducts());
        return "main";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }


    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buyForm() {
        return "/buy";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String transaction(@RequestParam String login,
                              @RequestParam String password, Model model) {

        if(UserData.getUser().getLogin().equals(login)
                && UserData.getUser().getPassword().equals(password)){
            model.addAttribute("result", "Zgadza się !");
            model.addAttribute("user", UserData.getUser());
            CommonCart commonCart = new CommonCart();
            commonCart.insert(UserData.getUser().getId(), this.database.getSum(), this.database.getListOrderCart());


            this.database.getOrderCart().clear();
            return "/buy";
        }else{
            model.addAttribute("result", "cos poszło nie tak - Nie prawidłowe dane");
            this.database.getOrderCart().clear();

            return "/buy";
        }
    }

}
