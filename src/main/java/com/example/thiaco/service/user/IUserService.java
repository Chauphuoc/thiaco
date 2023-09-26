package com.example.thiaco.service.user;

import com.example.thiaco.dto.UserDTO;
import com.example.thiaco.model.user.User;
import com.example.thiaco.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User,Long>, UserDetailsService {
    void saveUser(UserDTO userDTO);

    User findByUsername(String username);

    List<UserDTO> findAllUsers();

    List<User> findAll();

    List<User> findUsersByDeletedIsFalse();

}
