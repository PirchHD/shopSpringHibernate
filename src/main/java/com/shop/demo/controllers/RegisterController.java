package com.shop.demo.controllers;

import com.shop.demo.database.CreditCart;
import com.shop.demo.database.DB;
import com.shop.demo.database.UserData;
import com.shop.demo.database.entity.CommonCart;
import com.shop.demo.database.entity.CommonLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    DB database;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {

        return "/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerToTheStore(@RequestParam String login, @RequestParam String password, Model model) {

        if(CommonLogin.isThereThisLogin(login)) {
            model.addAttribute("info", "Istnieje juz taki Login");
            return "/info";
        }

        CommonLogin commonLogin = new CommonLogin();
        commonLogin.setLogin(login);
        commonLogin.setPassword(password);

        if(!commonLogin.insertCommonLogin(login, password)){
           model.addAttribute("info", "Blad bazy danych");
           return "/info";
       }

        UserData userData = new UserData();
        userData.setInstanceUser(commonLogin);


       model.addAttribute("IsLoginIn", true);
       model.addAttribute("User", UserData.getUser());
       model.addAttribute("products", this.database.getProducts());
       return "/main";
    }


}
