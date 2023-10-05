package com.example.thiaco.api;

import com.example.thiaco.dto.UserDTO;
import com.example.thiaco.exception.DataInputException;
import com.example.thiaco.model.user.User;
import com.example.thiaco.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
    @Autowired
    private IUserService userService;


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> userList = userService.findUsersByDeletedIsFalse();
        List<UserDTO> userDTOS = userList.stream().map(user -> user.toUserDTO()).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (!optionalUser.isPresent()) {
            throw new DataInputException("User không tồn tại.");
        }
        User user = optionalUser.get();
        UserDTO userDTO = user.toUserDTO();
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (!optionalUser.isPresent()) {
            throw new DataInputException("User không tồn tại.");
        }
        User user = optionalUser.get();
        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
