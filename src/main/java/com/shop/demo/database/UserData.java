package com.shop.demo.database;

import com.shop.demo.database.entity.CommonLogin;

public class UserData {

    private static CommonLogin commonLogin;
    public void setInstanceUser(CommonLogin commonLogin){
        this.commonLogin = commonLogin;
    }
    public static CommonLogin getUser(){
        return commonLogin;
    }

}
