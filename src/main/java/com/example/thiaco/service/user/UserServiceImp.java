package com.example.thiaco.service.user;

import com.example.thiaco.dto.RegisterDTO;
import com.example.thiaco.dto.UserDTO;
import com.example.thiaco.exception.DataInputException;
import com.example.thiaco.model.user.Role;
import com.example.thiaco.model.user.User;
import com.example.thiaco.repository.RoleRepository;
import com.example.thiaco.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImp implements IUserService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        user.setDeleted(true);
        save(user);
    }

    @Override
    public void deleteById(Long id) {

    }

//    @Override
//    public void saveUser(UserDTO userDTO) {
//        User user = new User();
//        user.setUsername(userDTO.getUsername());
//        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        Role role = roleRepository.findByName("ROLE_USER");
//        if (role == null) {
//            role = checkRoleExist();
//        }
//        user.setRoles(Arrays.asList(role));
//        userRepository.save(user);
//    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOS = userList.stream().map(user -> user.toUserDTO()).collect(Collectors.toList());
            return userDTOS;
    }

//    private Role checkRoleExist() {
//        Role role = new Role();
//        role.setName("ROLE_USER");
//        return roleRepository.save(role);
//    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User curUser = userRepository.findByUsername(username);
//        if (curUser != null) {
//            return new org.springframework.security.core.userdetails.User(curUser.getUsername(),
//                    curUser.getPassword(),
//                    mapRolesToAuthorities(curUser.getRoles()));
//        } else {
//            throw new UsernameNotFoundException("Invalid username or password");
//        }
//
//    }

//
//    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
//        Collection< ? extends GrantedAuthority> mapRoles = roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//        return mapRoles;
//    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUsersByDeletedIsFalse() {
        return userRepository.findUsersByDeletedIsFalse();
    }

    @Override
    public void register(RegisterDTO register){
        Optional<Role> roleOptional = roleRepository.findByCode(register.getCodeRole().toUpperCase());
        if (!roleOptional.isPresent())
            throw new DataInputException("Invalid role!");
//        check username đa có hay chưa
        Optional<User> userOptional =  userRepository.findUserByUsername(register.getUsername());
        if (userOptional.isPresent())
            throw new DataInputException("Username is unique!");

        User user = register.toUser();
        user.setRole(roleOptional.get());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }
}
