package spring.tutorials.client.validation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spring.tutorials.client.validation.data.User;
import spring.tutorials.client.validation.service.ValidationRulesGenerator;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    ValidationRulesGenerator validationGenerator;

    @GetMapping("/user")
    public String home(Map<String, Object> model) {
        model.put("validationRules", validationGenerator.generateValidationRules(User.class));
        model.put("user", new User());
        return "userForm";
    }

    @PostMapping("/user")
    public String createUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "userForm";
        }
        logger.info("Received new user with name {} and age {} ", user.getName(), user.getAge());
        return "redirect:/";
    }
}
