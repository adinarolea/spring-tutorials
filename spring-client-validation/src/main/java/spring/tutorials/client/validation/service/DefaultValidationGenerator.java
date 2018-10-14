package spring.tutorials.client.validation.service;

import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class DefaultValidationGenerator implements ValidationRulesGenerator {

    private static final String RULE_PREFIX = "data-rule-";
    private static final String MSG_PREFIX = "data-msg-";

    @Override
    public Map<String, Set<ValidationAttribute>> generateValidationRules(Class<?> modelAttributeClass) {
        Field[] fields = modelAttributeClass.getDeclaredFields();
        if (fields == null || fields.length == 0) {
            return Collections.emptyMap();
        }
        return Arrays.stream(fields)
                .collect(Collectors.toMap(Field::getName, field -> getDataAttributes(field)));
    }

    private Set<ValidationAttribute> getDataAttributes(Field field) {
        Set<ValidationAttribute> dataAttributes = new HashSet<>();

        Size size = field.getAnnotation(Size.class);
        if (size != null) {
            dataAttributes.addAll(getFieldDataAttributes("minlength", size.min(), size.message()));
            dataAttributes.addAll(getFieldDataAttributes("maxlength", size.max(), size.message()));
        }
        Min min = field.getAnnotation(Min.class);
        if (min != null) {
            dataAttributes.addAll(getFieldDataAttributes("min", min.value(), min.message()));

        }
        return dataAttributes;
    }

    private Set<ValidationAttribute> getFieldDataAttributes(String identifier, Object value, String message) {
        Set<ValidationAttribute> validationRules = new HashSet<>();
        validationRules.add(new ValidationAttribute(RULE_PREFIX + identifier, value.toString()));
        validationRules.add(new ValidationAttribute(MSG_PREFIX + identifier, message));
        return validationRules;
    }
}
