package ua.telecom.phonebook.users.model.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import ua.telecom.phonebook.base.BaseTo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;



public class UserTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "*Please provide your name")
    private String name;

    @NotBlank(message = "*Please provide your second name")
    private String surName;

    @Pattern(regexp = "^((\\+38)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", message = "*Not valid phone number")
    @NotBlank(message = "*Please provide your phone number")
    private String phone;

    @NotBlank(message = "*Please provide your password")
    @Size(min = 5, max = 64, message = "*Password must be between 5 and 64 characters")
    private String password;

    private String homeNumber;

    private String address;

    @Email(message = "*Please provide valid e-mail")
    private String email;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String surName, String phone, String password) {
        super(id);
        this.name = name;
        this.surName = surName;
        this.phone = phone;
        this.password = password;
    }

    public UserTo(Integer id, String name, String surName, String phone, String password, String homeNumber, String address, String email) {
        this(id, name, surName, phone, password);
        this.homeNumber = homeNumber;
        this.address = address;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
