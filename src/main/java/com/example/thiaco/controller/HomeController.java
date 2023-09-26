package com.example.thiaco.controller;

import com.example.thiaco.model.user.User;
import com.example.thiaco.service.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import com.example.thiaco.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private IUserService userService;

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(HttpServletRequest request, HttpServletResponse response) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
//                return "redirect:/users";
//            } else {
//                return "redirect:/index";
//            }
//        }
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute ("user") UserDTO userDTO, BindingResult bindingResult,Model model) {
        User curUser = userService.findByUsername(userDTO.getUsername());
        if (curUser != null && curUser.getUsername() != null && !curUser.getUsername().isEmpty()) {
            bindingResult.rejectValue("username", null, "There is already an account registered with the same email");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "register";
        }
        userService.saveUser(userDTO);
        return "redirect:/register?success";
    }

    @GetMapping("/admin")
    public String users(Model model){
        List<UserDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }
}
