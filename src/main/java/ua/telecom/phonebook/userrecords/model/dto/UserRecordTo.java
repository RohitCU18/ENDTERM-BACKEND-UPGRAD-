package ua.telecom.phonebook.userrecords.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import ua.telecom.phonebook.base.BaseTo;

import javax.validation.constraints.Pattern;



public class UserRecordTo extends BaseTo {

    @NotBlank(message = "*Name should not be empty")
    private String name;

    @NotBlank(message = "*Second name should not be empty")
    private String surName;

    @NotBlank(message = "*Phone number should not be empty")
    @Pattern(regexp = "^(\\+38)?(\\(?\\d{3}\\)?)?[\\d\\- ]{7,10}$", message = "Please provide valid phone number with pattern: +38(099)999-99-99")
    private String phone;

    @Email(message = "*Provide please valid e-mail")
    private String email;

    public UserRecordTo() {
    }

    public UserRecordTo(Integer id,
                        String name,
                        String surName,
                        String phone,
                        String email) {
        super(id);
        this.name = name;
        this.surName = surName;
        this.phone = phone;
        this.email = email;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("surName")
    public String getSurName() {
        return surName;
    }

    @JsonProperty("surName")
    public void setSurName(String surName) {
        this.surName = surName;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }
}
