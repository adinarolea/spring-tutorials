package com.tutorial.spring.transactional;

import com.tutorial.spring.transactional.model.User;
import com.tutorial.spring.transactional.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void whenNamesAreUnique_thenUsersAreSaved() {
        List<String> newNames = Arrays.asList("Jim", "Peter");
        List<User> savedUsers = userService.createUsers(newNames);
        assertThat(savedUsers).hasSize(newNames.size());
    }

    @Test
    public void whenNamesAreNotUnique_thenUsersAreNotSaved() {
        List<String> newNames = Arrays.asList("Jim", "Jim", "Peter", "Jim");
        List<User> savedUsers = userService.createUsers(newNames);
        assertThat(savedUsers).hasSize(2);
    }

}
