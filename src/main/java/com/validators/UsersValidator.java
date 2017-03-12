package com.validators;

import com.model.Cities;
import org.springframework.stereotype.Component;

/**
 * Created by Edvard Piri on 07.03.2017.
 */

import com.model.Users;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;


@Component
public class UsersValidator implements Validator {

    public boolean supports(Class clazz) {
        return Users.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {

        Users users = (Users) target;

        String email = users.getEmail();


        //EmailClient emailClient = new EmailClient();
        /*try {
            emailClient.sendEmail(email);
        } catch (EmailException e) {
            ObjectError error = new ObjectError("", "error is here");
            errors.reject("error message");
        }*/
        //send email
        //
        //(.*[#@$].*
        //
        //

//        if ((birthDate != null) && (currentDate != null))
//            return Period.between(birthDate, currentDate).getYears();
//        else errors.reject("age < 18");

        //password
        if (users.getPassword().matches("(?=.*[@#$%])(?=.*[A-Z])")) {
        }

        //phone
        char[] firstTwoPhoneChar = users.getPhone().toCharArray();
        //43 = +
        //51 = 3
        if (firstTwoPhoneChar[0] != 43 && firstTwoPhoneChar[1] != 51)
            errors.reject("not ukrainian format");

        //age validation
        LocalDate birthDate = users.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        if (age < 18)
            errors.reject("age < 18");


        //City validation
        int index = 0;
        for (Cities city : Cities.values())
            if (users.getCity().equals(city.toString())) {
                index++;
            }
        if (index == 0)
            errors.reject("wrong city");


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.firstName", "First name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.lastName", "Last name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.email", "Email is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "error.phone", "Phone is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "error.dateOfBirth", "Date of birth is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "error.city", "city is required.");
    }


}
