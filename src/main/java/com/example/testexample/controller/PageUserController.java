package com.example.testexample.controller;

import com.example.testexample.model.User;
import com.example.testexample.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class PageUserController {

  private final UserService userService;

  @GetMapping("/web/users")
  public String allUsers(Model model) {
    List<User> users = userService.findAll();
    model.addAttribute("list", users);
    return "/pages/user/index";
  }

  @GetMapping("/web/common")
  public String common() {
    return "/pages/common/index";
  }
}
