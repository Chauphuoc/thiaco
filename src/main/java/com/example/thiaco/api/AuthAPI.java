package com.example.thiaco.api;

import com.example.thiaco.dto.RegisterDTO;
import com.example.thiaco.dto.UserDTO;
import com.example.thiaco.exception.ResourceNotFoundException;
import com.example.thiaco.exception.UnauthorizedException;
import com.example.thiaco.model.user.User;
import com.example.thiaco.service.JwtService;
import com.example.thiaco.service.user.IUserService;
import com.example.thiaco.utils.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthAPI {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDTO.getUsername(),userDTO.getPassword()));
        if (authentication.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtService.generateToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userService.getByUsername(userDetails.getUsername());
            if (currentUser == null)
                throw new ResourceNotFoundException("Username not found!");
            JwtResponse jwtResponse = new JwtResponse(
                    jwt,
//                    currentUser.getId(),
                    currentUser.getUsername(),
                    currentUser.getRole().toString()
            );
            ResponseCookie springCookie = ResponseCookie.from("JWT", jwt)
                    .httpOnly(false)
                    .secure(false)
                    .path("/")
                    .maxAge(60 * 1000) //time sống 1h
                    .domain("localhost")
                    .build();
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                    .body(jwtResponse);
        }
        throw new UnauthorizedException("Unauthorized!");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO register){
        userService.register(register);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}