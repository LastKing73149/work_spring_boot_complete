package com.project.work.controller;
import com.project.work.model.User;
import com.project.work.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController (UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }


    @GetMapping("/new")
    public String newUserForm(@ModelAttribute("user") User user) {
        return "new_user";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") @Valid User user,
        BindingResult bindingResult) {
            if(bindingResult.hasErrors()) {
                return "new_user";
            }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             @PathVariable("id") Long id) {
        if(bindingResult.hasErrors()) {
            return "edit_user";
        }
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
