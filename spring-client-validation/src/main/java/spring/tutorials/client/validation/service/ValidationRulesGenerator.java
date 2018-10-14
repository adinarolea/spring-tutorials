package spring.tutorials.client.validation.service;

import java.util.Map;
import java.util.Set;

public interface ValidationRulesGenerator {

    Map<String, Set<ValidationAttribute>> generateValidationRules(Class<?> modelAttributeClass);
}
