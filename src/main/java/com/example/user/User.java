package com.example.user;

import com.example.order.Order;
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
    @Column(nullable = false, name = "eMail")
    private String eMail;
    @Column(nullable = false, name = "phone")
    private String phone;
    @Column(nullable = false, name = "date")
    private Date date;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

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

    public String geteMail() {
        return eMail;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDate() {
        return date;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDate(Date date) {
        this.date = date;
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

}
