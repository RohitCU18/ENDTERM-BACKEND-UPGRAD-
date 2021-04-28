package ua.telecom.phonebook.users.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import ua.telecom.phonebook.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;



@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "phoneNumber", name = "users_unique_phonenumber_idx")})
public class User extends BaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "surName", nullable = false)
    @NotBlank
    private String surName;

    @Column(name = "phoneNumber", nullable = false, unique = true)
    @Pattern(regexp = "^((\\+38)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    @NotBlank
    private String phoneNumber;

    @Column(name = "homeNumber")
    private String homeNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    @Email
    private String email;

    @NotBlank
    @Size(min = 5, max = 64, message = " must between 5 and 64 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(Integer id, String name, String surName, String phoneNumber, String password, Role role, Role... roles) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roles = EnumSet.of(role, roles);
    }

    public User(Integer id, String name, String surName, String phoneNumber, String homeNumber, String address, String email, String password, Role role, Role... roles) {
        this(id, name, surName, phoneNumber, password, role, roles);
        this.homeNumber = homeNumber;
        this.address = address;
        this.email = email;
    }

    public User(String name, String surName, String phoneNumber, String password, Role role) {
        this(null, name, surName, phoneNumber, password, role);
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surName, user.surName) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surName, phoneNumber, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", id=" + id +
                '}';
    }
}
