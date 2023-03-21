package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entityes.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPage(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "admin";
    }

    @GetMapping(value = "/addUser")
    public String getUserCreateForm(@ModelAttribute("user") User user, ModelMap model) {
        model.addAttribute("roles", roleService.getRoles());
        userService.getAllUsers();
        return "addUser";
    }

    @PostMapping("/addUser")
    public String createUser(@ModelAttribute("eser") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String getUserEditForm(@PathVariable("id") Long id, Model model) {
        User userById = userService.findById(id);
        model.addAttribute("user", userById);
        model.addAttribute("roles", roleService.getRoles());
        return "edit";
    }

    @PutMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user, id);
        return "redirect:/admin";
    }


    @GetMapping("/showUser/{id}")
    public String user(ModelMap model, @PathVariable Long id) {
        model.addAttribute("user", userService.findById(id));
        return "/showUser";
    }

    @GetMapping("/admin")
//@GetMapping("/admin")
    public String listUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        //   return "/users";
        return "/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
