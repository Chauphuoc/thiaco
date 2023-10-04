package com.example.thiaco.service.user;

import com.example.thiaco.dto.RegisterDTO;
import com.example.thiaco.dto.UserDTO;
import com.example.thiaco.model.user.User;
import com.example.thiaco.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User,Long> {
//    void saveUser(UserDTO userDTO);


    List<UserDTO> findAllUsers();

    List<User> findAll();

    List<User> findUsersByDeletedIsFalse();

    User getByUsername(String username);

    Optional<User> findUserByUsername(String username);


    void register(RegisterDTO registerDTO);

}
