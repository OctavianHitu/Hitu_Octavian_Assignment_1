package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.builders.UserBuilder;
import ro.tuc.ds2020.dtos.UserDto;
import ro.tuc.ds2020.entities.UserOfApp;
import ro.tuc.ds2020.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){this.userRepository=userRepository;}

    public List<UserDto> findAll(){
        List<UserOfApp> userOfApps = userRepository.findAll();
        return userOfApps.stream().map(UserBuilder::toUserDto).collect(Collectors.toList());

    }

    public UUID insert(UserDto userDto){
        UserOfApp user= userRepository.save(UserBuilder.toUserEntity(userDto));
        LOGGER.debug("Person with id {} was inserted in db", user.getId());
        return user.getId();
    }

    public void delete(UUID id){
        Optional<UserOfApp> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new ResourceNotFoundException(UserOfApp.class.getSimpleName());

        }
        userRepository.delete(optionalUser.get());
    }


    public UserDto findById(UUID id){
        Optional<UserOfApp> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new ResourceNotFoundException(UserOfApp.class.getSimpleName());

        }
        UserOfApp user= optionalUser.get();
        return UserBuilder.toUserDto(user);
    }

    public void update(UserDto userDto){
        Optional<UserOfApp> optionalUser= userRepository.findById(userDto.getId());
                if(!optionalUser.isPresent()){
                    throw new ResourceNotFoundException(UserOfApp.class.getSimpleName());
                }
                UserOfApp user= optionalUser.get();
                if(userDto.getName()!=null){
                    user.setName(userDto.getName());
                }
                if(userDto.getEmail()!=null){
                    user.setEmail(userDto.getEmail());

                }
                if(userDto.getPassword()!=null){
                    user.setPassword(userDto.getPassword());

                }
                if(userDto.isRole()){
                    user.setRole(userDto.isRole());
                }
                userRepository.save(user);

    }
}
