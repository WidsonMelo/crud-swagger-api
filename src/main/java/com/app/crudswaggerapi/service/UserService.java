package com.app.crudswaggerapi.service;

import com.app.crudswaggerapi.converter.UserConverter;
import com.app.crudswaggerapi.exception.ResourceNotFoundException;
import com.app.crudswaggerapi.model.User;
import com.app.crudswaggerapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public User createUser(User user){
        user.setUserId(UUID.randomUUID());
        var entity = userConverter.toUserEntity(user);
        return userConverter.toUser(userRepository.save(entity));
    }

    public List<User> findUsers(){
        return userRepository.findAll()
                .stream()
                .map(userConverter::toUser)
                .collect(Collectors.toList());
    }

    public User findUserById(UUID userId){
        return userConverter.toUser(userRepository.findById(userId.toString())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Usuário com ID %s não encontrado", userId))));
    }

    public void deleteUser(UUID userId){
        userRepository.deleteById(userId.toString());
    }

    public User updateUser(UUID userId, User user){
        userRepository.findById(userId.toString()).orElseThrow(() -> new ResourceNotFoundException("Usuário com ID não encontrado"));
        user.setUserId(userId);
        var userEntity = userRepository.save(userConverter.toUserEntity(user));
        return userConverter.toUser(userEntity);
    }
}
