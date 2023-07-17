package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.entityes.User;

@RestController
@RequestMapping
public class UserRestController {

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("loginPage");
    }

    @GetMapping("/user")
    public ModelAndView showAdminPage() {
        return new ModelAndView("user");
    }

    @GetMapping("/user/authUser")
    public ResponseEntity<User> getAuthUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }
}
