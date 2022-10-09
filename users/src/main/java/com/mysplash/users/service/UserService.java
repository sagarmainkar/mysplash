package com.mysplash.users.service;


import com.mysplash.users.dto.SplashUser;
import com.mysplash.users.dto.UserRegistrationRequest;
import com.mysplash.users.exceptions.UserNotFoundException;
import com.mysplash.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SplashUser registerUser(UserRegistrationRequest userRegistrationRequest){

        log.info("Inside registerUser to register user with email: {}",userRegistrationRequest.email());

        SplashUser user = SplashUser.builder()
                .firstName(userRegistrationRequest.firstName())
                .lastName(userRegistrationRequest.lastName())
                .email(userRegistrationRequest.email())
                .build();

        SplashUser userEntity = userRepository.saveAndFlush(user);

        log.info("User registration success for user with email: {}",user.getEmail());
        return userEntity;

    }


    public SplashUser getUser(Integer userId){
        log.info("Getting the user for usrId: {}",userId);
        Optional<SplashUser> user = userRepository.findById(userId);

        if(user.isEmpty()) {
            log.info("User cannot be found : {}",userId);
            throw new UserNotFoundException("Please check the id of the user");
        }

        log.info("User found returning details for user with email: {}",user.get().getEmail());

        return user.get();

    }
}
