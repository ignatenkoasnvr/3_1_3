package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entityes.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImp;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import java.security.Principal;

@Controller
@RequestMapping()
public class UserController {

    private UserServiceImp userServiceImp;
    private RoleServiceImp roleServiceImp;

    @Autowired
    public UserController(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp) {
        this.roleServiceImp = roleServiceImp;
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleServiceImp.getRoles());
        userServiceImp.getAllUsers();

        roleServiceImp.getRoles();
        return "/registration";
    }

    @PostMapping("/registration")
    public String perfomRegistration(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userServiceImp.saveUser(user);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping(value = "/user")
    public String showUserUnfo(Principal principal, ModelMap model) {
        model.addAttribute("user", userServiceImp.findByUsername(principal.getName()));
        return "user";
    }
}
