package com.sparta.hanghaebnb.config;

import com.sparta.hanghaebnb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String handler(Model model) {
        model.addAttribute("Hello World");
        return "helloworld";
    }
}
