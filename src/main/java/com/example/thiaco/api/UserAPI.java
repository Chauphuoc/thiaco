package com.example.thiaco.api;

import com.example.thiaco.dto.SalaryEffDTO;
import com.example.thiaco.dto.UserDTO;
import com.example.thiaco.dto.UserResDTO;
import com.example.thiaco.exception.DataInputException;
import com.example.thiaco.model.salary.SalaryCoEfficient;
import com.example.thiaco.model.user.User;
import com.example.thiaco.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
    @Autowired
    private IUserService userService;


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> userList = userService.findUsersByDeletedIsFalse();
        List<UserResDTO> userDTOS = userList.stream().map(user -> user.userResDTO()).collect(Collectors.toList());
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
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUsers(@RequestParam String ids) {
        String[] arrStringIds = ids.split(",");
        Set<String> uniqueIds = new HashSet<>();
        for (String id : arrStringIds) {
            uniqueIds.add(id);
        }
        List<Long> arrIds = new ArrayList<>();
        for (String item : uniqueIds) {
            Long userId = Long.parseLong(item);
            arrIds.add(userId);
        }
        for (Long id: arrIds) {
            Optional<User> userOptional = userService.findById(id);
            if (!userOptional.isPresent()) {
                throw new DataInputException("Người dùng không tồn tại");
            }
            User user = userOptional.get();
            user.setDeleted(true);
            userService.save(user);
        }
        List<User> list = userService.findUsersByDeletedIsFalse();
        List<UserResDTO> dtoList = list.stream().map(user -> user.userResDTO()).collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
