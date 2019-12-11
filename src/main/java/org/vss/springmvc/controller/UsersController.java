package org.vss.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vss.springmvc.model.User;
import org.vss.springmvc.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "redirect:/users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(@ModelAttribute("model") ModelMap model) {
        Iterable<User> retrievedUsers = userRepository.findAll();
        List<User> users = new ArrayList<>();
        retrievedUsers.forEach(users::add);

        logger.info("users: {}", users);

        model.addAttribute("usersList", users);
        return "index";
    }
}
