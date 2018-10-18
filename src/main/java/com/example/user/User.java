package com.example.user;


import com.example.security.user.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@Data
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


//    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
////    @JoinTable(name = "user_in_role", joinColumns = {@JoinColumn(name = "uid", referencedColumnName = "id")}
////            , inverseJoinColumns = {@JoinColumn(name = "rid", referencedColumnName = "id")})
    @Transient
    private List<Role> roles;

}
