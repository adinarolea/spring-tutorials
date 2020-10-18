package com.tutorial.spring.transactional.service;

import com.tutorial.spring.transactional.handler.TransactionalHandler;
import com.tutorial.spring.transactional.model.User;
import com.tutorial.spring.transactional.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Autowired
    TransactionalHandler transactionalHandler;

    @Autowired
    UserRepository userRepository;

    public List<User> createUsers(List<String> users) {
        List<User> savedUsers = users.stream()
                .map(userName -> {
                    try {
                        return transactionalHandler.runInNewTransaction(() -> saveUser(userName));
                    } catch (Exception ex) {
                        log.error("Unable to save new user {} ", ex.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return savedUsers;
    }

    private User saveUser(String name) {
        User user = User.builder()
                .name(name)
                .build();
        return userRepository.save(user);
    }
}
