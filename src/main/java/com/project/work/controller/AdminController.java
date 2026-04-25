package com.project.work.controller;

import com.project.work.model.User;
import com.project.work.repository.UserRepository;
import com.project.work.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AdminController(UserService userService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("/new")
    public String newUserForm(@ModelAttribute("user") User user) {
        return "new_user";
    }

    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) throws Exception{
        if(userRepository.findByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username","error.user","Пользователь с таким никнеймом уже существует!");
        }
        if (bindingResult.hasErrors()) return "new_user";
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin";
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
            bindingResult.getAllErrors().forEach(error -> System.out.println("LOG ERROR: " + error.toString()));
            return "edit_user";
        }
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
