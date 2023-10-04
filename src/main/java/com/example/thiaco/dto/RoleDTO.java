package com.example.thiaco.dto;

import com.example.thiaco.model.user.Role;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "The role is required")
    private Long id;
    private String code;
    public Role toRole(){
        return new Role()
                .setId(id)
                .setCode(code);
    }
}
