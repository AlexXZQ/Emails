package com.example.xzq.emails.bean;


import java.net.PasswordAuthentication;

import javax.mail.Authenticator;

/**
 * Created by Xzq on 2017/3/28.
 */

public class MyAuthenticator extends Authenticator {

    String userName = null;
    char[] passWord = null;

    public MyAuthenticator(){

    }

    public MyAuthenticator(String userName, char[] passWord){
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        return super.getPasswordAuthentication();
    }
}
