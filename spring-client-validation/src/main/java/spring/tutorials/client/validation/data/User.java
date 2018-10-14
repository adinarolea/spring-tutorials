package spring.tutorials.client.validation.data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class User {

    @Size(min = 2, max = 30, message = "Invalid name size, the name should contain between 2 and 30 characters")
    private String name;

    @Min(value = 18, message = "You must be over 18")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
