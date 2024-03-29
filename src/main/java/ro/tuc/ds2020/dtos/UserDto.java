package ro.tuc.ds2020.dtos;

import lombok.*;
import ro.tuc.ds2020.entities.RoleEnum;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private RoleEnum role;


}
