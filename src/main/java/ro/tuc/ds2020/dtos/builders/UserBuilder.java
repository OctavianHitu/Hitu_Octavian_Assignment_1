package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.UserDto;
import ro.tuc.ds2020.entities.UserOfApp;

public class UserBuilder {

    private UserBuilder(){
    }

    public static UserDto toUserDto(UserOfApp user){
        return new UserDto(user.getId(),user.getName(),user.getEmail(),user.getPassword(),user.getRole());
    }

    public static UserOfApp toUserEntity (UserDto userDto){
        return new UserOfApp(userDto.getName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole());
    }


}
