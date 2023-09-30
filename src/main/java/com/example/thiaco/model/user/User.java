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

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();

    public UserDTO toUserDTO(){
        return new UserDTO()
                .setId(id)
                .setUsername(username)
                .setPassword(password);
    }

    public UserResDTO toUserResDTO() {
        return new UserResDTO()
                .setId(id)
                .setUsername(username)
                .setRoleDTOs(roles.stream().map(role -> role.toRoleDTO()).collect(Collectors.toList()));
    }
}
