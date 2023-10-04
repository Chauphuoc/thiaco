package com.example.thiaco.service;

import com.example.thiaco.dto.UserDTO;
import com.example.thiaco.exception.ResourceNotFoundException;
import com.example.thiaco.model.user.User;
import com.example.thiaco.repository.RoleRepository;
import com.example.thiaco.repository.UserRepository;
import com.example.thiaco.security.UserPrinciple;
import com.example.thiaco.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserPrincipleService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    //Kiem tra co phai user hay ko
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent())
            throw new UsernameNotFoundException("Username not found!");
//        User currentUser = userOptional.get().toUser();

        return UserPrinciple.build(userOptional.get());
    }
}
