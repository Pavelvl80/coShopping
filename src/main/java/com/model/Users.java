package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Edvard Piri on 25.12.2016.
 */
@Entity
@Table(name = "USERS")
public class Users extends BaseEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Long isEmailVerified;
    private Long isPhoneVerified;
    private Date dateOfBirth;
    private String city;
    private Set<Ad> adsPublished;
    private Set<Ad> adsJoined;
    private String rating;
    private String attributes;

    private Set<FriendRelation> friends;

    private Date lastLogin;
    private Date dateRegistered;

    private Long isActive;
    //this field is not stored in db
//    private boolean isLogged;

    public Users() {
        this.lastLogin = new Date();
        this.dateRegistered = new Date();
        this.isActive = 0L;
        this.isEmailVerified = 0L;
        this.isPhoneVerified = 0L;
    }

    public Users(String firstName, String lastName, String email, String password, String phone, Date dateOfBirth, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.city = city;

        this.lastLogin = new Date();
        this.dateRegistered = new Date();
        this.isActive = 0L;
        this.isEmailVerified = 0L;
        this.isPhoneVerified = 0L;
    }
    //    public Users(String userName, String firstName, String lastName, String email, String password, String phone, Date dateOfBirth, String city) {
//        this.userName = userName;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.phone = phone;
//        this.dateOfBirth = dateOfBirth;
//        this.city = city;
//
//        this.lastLogin = new Date();
//        this.dateRegistered = new Date();
//        this.isActive = 0L;
//        this.isEmailVerified = 0L;
//        this.isPhoneVerified = 0L;
//    }

    public static void setCurrent(HttpSession session, Users curUser) {
        session.setAttribute("logged", curUser);
    }

    public static Users current(HttpSession session) {
        return (Users) session.getAttribute("logged");
    }

    @JsonIgnore
    @Id
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    public Long getId() {
        return id;
    }

    @Column(name = "FIRST_NAME")
    @NotEmpty(message = "Please enter first name")
    @NotNull()
    @Size(min = 3, max = 15, message = "Your first name must be between 3 and 15 characters")
    @Pattern(regexp = "\\D*") //no numbers
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "LAST_NAME")
    @NotEmpty(message = "Please enter last name")
    @NotNull()
    @Size(min = 3, max = 15, message = "Your last name must be between 3 and 15 characters")
    @Pattern(regexp = "\\D*") //no numbers
    public String getLastName() {
        return lastName;
    }

    @Column(name = "EMAIL")
    @NotEmpty(message = "Please enter last name")
    @NotNull()
    @Size(min = 3, max = 20, message = "Your email must be between 3 and 20 characters")
//    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Column(name = "PASSWORD")
    @NotEmpty(message = "Please enter password name")
    @NotNull()
    @Size(min = 6, max = 15, message = "Your password must be between 6 and 15 characters")
    public String getPassword() {
        return password;
    }

    @Column(name = "PHONE")
    @NotEmpty(message = "Please enter your phone number")
    @Size(min = 8, max = 13, message = "Wrong phone number format")
    public String getPhone() {
        return phone;
    }


    @Column(name = "IS_EMAIL_VERIFIED")
    public Long getIsEmailVerified() {
        return isEmailVerified;
    }

    @Column(name = "IS_PHONE_VERIFIED")
    public Long getIsPhoneVerified() {
        return isPhoneVerified;
    }

    @Column(name = "DATE_OF_BIRTH")
    @NotNull
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Column(name = "CITY")
    @NotEmpty(message = "Please enter your city")
    @NotNull
    public String getCity() {
        return city;
    }

    @OneToMany(targetEntity = Ad.class, mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Ad> getAdsPublished() {
        return adsPublished;
    }

    @ManyToMany(cascade=CascadeType.ALL, mappedBy="participants", fetch = FetchType.EAGER)
    public Set<Ad> getAdsJoined() {
        return adsJoined;
    }

    @Column(name = "RATING")
    public String getRating() {
        return rating;
    }

    @Column(name = "ATTRIBUTES")
    public String getAttributes() {
        return attributes;
    }

    @OneToMany(targetEntity = FriendRelation.class, mappedBy = "toUserId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<FriendRelation> getFriends() {
        return friends;
    }

    @Column(name = "LAST_LOGIN")
    public Date getLastLogin() {
        return lastLogin;
    }

    @Column(name = "DATE_REGISTERED")
    @NotNull
    public Date getDateRegistered() {
        return dateRegistered;
    }

    @Column(name = "IS_ACTIVE")
    public Long getIsActive() {
        return isActive;
    }

//    public boolean isLogged() {
//        return isLogged;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIsEmailVerified(Long isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public void setIsPhoneVerified(Long isPhoneVerified) {
        this.isPhoneVerified = isPhoneVerified;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAdsPublished(Set<Ad> adsPublished) {
        this.adsPublished = adsPublished;
    }

    public void setAdsJoined(Set<Ad> adsJoined) {
        this.adsJoined = adsJoined;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }


    public void setFriends(Set<FriendRelation> friends) {
        this.friends = friends;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    }

    //    public void setLogged(boolean logged) {
//        isLogged = logged;
//    }

