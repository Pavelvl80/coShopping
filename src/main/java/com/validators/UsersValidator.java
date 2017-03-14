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
        phoneValidator(users.getPhone(), errors);
        dateOfBirthValidator(users.getDateOfBirth(), errors);
        cityValidator(users.getCity(), errors);

    }

    void phoneValidator(String number, Errors errors) {
        if (number == null)
            return;
        char[] firstTwoPhoneChar = number.toCharArray();
        if (firstTwoPhoneChar[0] != 43 && firstTwoPhoneChar[1] != 51)
            errors.reject("not ukrainian format");
    }

    void dateOfBirthValidator(Date dateOfBirth, Errors errors) {
        if (dateOfBirth == null)
            return;
        LocalDate birthDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        if (age < 18)
            errors.reject("age < 18");
    }

    void cityValidator(String userCity, Errors errors) {
        if (userCity == null)
            return;
        int index = 0;
        for (Cities city : Cities.values())
            if (userCity.equals(city.toString())) {
                index++;
            }
        if (index == 0)
            errors.reject("wrong city");
    }
}
