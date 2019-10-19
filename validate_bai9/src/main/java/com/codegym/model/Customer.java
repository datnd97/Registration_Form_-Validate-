package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Customer implements Validator {
//    @NotEmpty
//    private int id;
    @NotEmpty
    @Size(min = 2,max = 45)
    private String firstName;
    @NotEmpty
    @Size(min = 2,max =3)

    private String lastName;
    @Email
    private String email;

    private String number;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Customer() {
    }

    public Customer(@NotEmpty @Size(min = 2, max = 45) String firstName, @NotEmpty @Size(min = 2, max = 3) String lastName, @Email String email, String number, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
        this.age = age;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;

        String number = customer.getNumber();
        ValidationUtils.rejectIfEmpty(errors,"number","number.empty");
        if (number.length()>11 || number.length()<10){
            errors.rejectValue("number", "number.length");
        }
        if (!number.startsWith("0")){
            errors.rejectValue("number", "number.startsWith");
        }
        if (!number.matches("(^$|[0-9]*$)")){
            errors.rejectValue("number", "number.matches");
        }
        int age = customer.getAge();
        ValidationUtils.rejectIfEmpty(errors,"age","age.empty");
        if(age<18){
            errors.rejectValue("age","age.notOld");
        }

    }
}
