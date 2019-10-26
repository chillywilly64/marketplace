package com.epam.mentoring.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    @RequestMapping("/error")
    public String handleError() {

        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
