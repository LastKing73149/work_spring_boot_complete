package com.project.work.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Имя не должно быть пустым")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ]+$", message = "Имя должно содержать только буквы")
    @Size(min = 2, max = 20, message = "Имя должно быть от 2 до 20 символов")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "Фамилия не должна быть пустой")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ]+$", message = "Фамилия должна содержать только буквы")
    @Size(min = 2, max = 20, message = "Фамилия должна быть от 2 до 20 символов")
    private String lastName;

    @Column(name = "age")
    @NotNull(message = "Укажите свой возраст")
    @Min(value = 1, message = "Возраст должен быть больше 0")
    @Max(value = 100, message = "Возраст не может быть больше 100")
    private Integer age;

    @Column(name = "email")
    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Некорректный email")
    private String email;

    public User(){

    }

    public User(String name, String lastName, int age, String email){
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
