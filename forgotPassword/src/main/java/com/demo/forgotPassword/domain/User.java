package com.demo.forgotPassword.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userprofile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private static final long serialVersionUID = 1L;
    private String userName;
    @Id
    private String email;
    private String password;
}
