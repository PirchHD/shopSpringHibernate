package com.shop.demo.controllers;

import com.shop.demo.database.UserData;
import com.shop.demo.database.entity.CommonLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String registerToTheStore(@RequestParam String login, @RequestParam String password, Model model) {

        CommonLogin commonLogin = new CommonLogin();
        commonLogin.setLogin(login);
        commonLogin.setPassword(password);
        CommonLogin.tryToLogIn(login, password);

        if(!CommonLogin.tryToLogIn(login, password)){
            model.addAttribute("info", "Nie istnieje taki uzytkownik :c");
            return "/info";
        }

        commonLogin = CommonLogin.getUserData(login, password);
        UserData userData = new UserData();
        userData.setInstanceUser(commonLogin);

        model.addAttribute("User", UserData.getUser());
        return "/main";
    }


}
