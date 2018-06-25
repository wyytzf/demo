package com.example.user;

import com.example.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "account")
    private String account;
    @Column(nullable = false, name = "password")
    private String password;
    @Column(nullable = false, name = "realname")
    private String realname;
    @Column(nullable = false, name = "email")
    private String email;
    @Column(nullable = false, name = "phone")
    private String phone;
    @Column(nullable = false, name = "registertime")
    private Date registertime;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userinrole", joinColumns = {@JoinColumn(name = "uid", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "rid", referencedColumnName = "id")})
    @JsonIgnore
    private List<Role> roles;

    public User() {

    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getRealname() {
        return realname;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Date getRegistertime() {
        return registertime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
