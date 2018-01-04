package com.norrissim.phonebook.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.norrissim.phonebook.utils.State.populateModel;

/**
 * Created by User on 01.01.2018.
 */

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showSignIn(Model model, HttpServletRequest request) {
        populateModel(model, request);
        return "index";
    }
}
