package ua.telecom.phonebook.users.model.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;


public class UserPasswordDto implements Serializable{
    private static final long serialVersionUID = 2L;

    @NotBlank(message = "*Please provide current password")
    private String currentPassword;

    @NotBlank(message = "*Please provide new password")
    @Size(min = 5, max = 64, message = "*Password must be between 5 and 64 characters")
    private String newPassword;

    private String confirmPass;

    public UserPasswordDto() {
    }

    public UserPasswordDto(String currentPassword, String newPassword, String confirmPass) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPass = confirmPass;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }
}



