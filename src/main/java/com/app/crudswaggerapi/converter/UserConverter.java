package com.app.crudswaggerapi.converter;

import com.app.crudswaggerapi.entity.UserEntity;
import com.app.crudswaggerapi.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserConverter {
    public UserEntity toUserEntity(User user){
        return UserEntity.builder()
                .userId(user.getUserId().toString())
                .firstName(user.getFirstName())
                .lastNamee(user.getLastNamee())
                .build();
    }

    public User toUser(UserEntity userEntity){
        return User.builder()
                .userId(UUID.fromString(userEntity.getUserId()))
                .firstName(userEntity.getFirstName())
                .lastNamee(userEntity.getLastNamee())
                .build();
    }
}
