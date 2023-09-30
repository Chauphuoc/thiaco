package com.example.thiaco.dto;

import com.example.thiaco.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class RoleDTO {
    private Long id;
    private String name;

    public Role toRole() {
        return new Role()
                .setId(id)
                .setName(name)
                ;
    }
}
