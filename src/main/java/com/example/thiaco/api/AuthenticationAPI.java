package com.example.thiaco.api;

import com.example.thiaco.dto.UserDTO;
import com.example.thiaco.model.user.User;
import com.example.thiaco.service.user.IUserService;
import com.example.thiaco.utils.AppUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class AuthenticationAPI {
    @Autowired
    private IUserService userService;

    @Autowired
    private AppUtils appUtils;

    @PostMapping("/save")
    public ResponseEntity<?> registration(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        User curUser = userService.findByUsername(userDTO.getUsername());
        if (curUser != null && curUser.getUsername() != null && !curUser.getUsername().isEmpty()) {
            bindingResult.rejectValue("username", null, "There is already an account registered with the same email");
        }
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        userService.saveUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
