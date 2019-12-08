package org.vss.springmvc.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vss.springmvc.repositories.UserRepository;
import org.vss.springmvc.services.UserService;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "redirect:/users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String init(@ModelAttribute("model") ModelMap model) {
//        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("usersList", userRepository.findAll());
        return "index";
    }
}
