package com.example.thiaco.model.user;

import com.example.thiaco.dto.UserDTO;
import com.example.thiaco.dto.UserResDTO;
import com.example.thiaco.model.BaseEntity;
import com.example.thiaco.model.user.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Accessors(chain = true)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = true)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    public UserDTO toUserDTO(){
        return new UserDTO()
                .setId(id)
                .setUsername(username)
                .setPassword(password);
    }


    public UserResDTO userResDTO() {
        return new UserResDTO()
                .setId(id)
                .setUsername(username)
                .setRole(role.getCode());
    }

}
