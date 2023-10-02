package com.example.thiaco.controller;

import com.example.thiaco.model.user.User;
import com.example.thiaco.service.email.EmailSender;
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

    @Autowired
    private EmailSender emailSender;

    @GetMapping("/index")
    public String home() {
//        String link = "http://localhost:8080/index";
//        emailSender.sendEmail("chauphuoc1996@gmail.com",
//                buildEmail("Mr.Phuoc", link)
//        );
        return "index";
    }


    @GetMapping("/login")
    public String showLoginPage(HttpServletRequest request, HttpServletResponse response) {

        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "register";
    }

//    @PostMapping("/register/save")
//    public String registration(@Valid @ModelAttribute ("user") UserDTO userDTO, BindingResult bindingResult,Model model) {
//        User curUser = userService.findByUsername(userDTO.getUsername());
//        if (curUser != null && curUser.getUsername() != null && !curUser.getUsername().isEmpty()) {
//            bindingResult.rejectValue("username", null, "There is already an account registered with the same email");
//        }
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("user", userDTO);
//            return "register";
//        }
//        userService.saveUser(userDTO);
//        return "redirect:/register?success";
//    }

    @GetMapping("/admin")
    public String users(Model model){
        List<UserDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    private String buildEmail(String name, String link) {
        return

                        "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                        "\n" +
                        "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                        "\n" +

                        "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                        "    <tbody><tr>\n" +
                        "      <td height=\"30\"><br></td>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                        "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                        "        \n" +
                        "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Xin chúc mừng! Bạn đã đăng nhập thành công. Bạn có thể sử dụng tài khoản ngay bây giờ: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p> <p>Best Regard,</p> <p style=\"Margin-top:-10px ;font-size:14px;font-family: serif \">IT Department Admin</p>" +
                        "        \n" +
                        "      </td>\n" +
                        "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "      <td height=\"30\"><br></td>\n" +
                        "    </tr>\n" +
                        "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                        "\n" +
                        "</div></div>"
                        ;

    }
}
