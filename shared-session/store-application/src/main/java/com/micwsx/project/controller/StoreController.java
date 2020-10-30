package com.micwsx.project.controller;

import model.MyHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Michael
 * @create 10/30/2020 1:39 PM
 * Store application
 */
@RestController
@RequestMapping("/store")
public class StoreController {
    @RequestMapping("/home")
    public String home(MyHttpServletRequest request, HttpServletResponse response){
        return "Welcome to Store!";
    }
}
