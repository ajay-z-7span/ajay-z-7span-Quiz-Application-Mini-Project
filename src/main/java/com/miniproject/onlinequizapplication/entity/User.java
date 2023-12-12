package com.miniproject.onlinequizapplication.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "FirstName should not be blank")
    private String firstName;

    @NotBlank(message = "UserName should not be blank")
    private String userName;

    @NotBlank(message = "Password should not be blank")
    private String password;

    @Column(length = 32, columnDefinition = "varchar(32) default 'USER'")
    @Enumerated(value = EnumType.STRING)
    private Role role;
}