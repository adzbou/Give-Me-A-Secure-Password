package com.example.Password;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class APIController {

    Password mypassword = new Password();

    @RequestMapping(value = "/get-apple-password", method = RequestMethod.GET)
    public synchronized String GetApplePassword(){

        return String.valueOf(mypassword.BuildApplePassword());
    }



}
