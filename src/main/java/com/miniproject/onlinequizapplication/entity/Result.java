package com.miniproject.onlinequizapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Quiz quiz;

    @ManyToOne
    private User user;

    private Integer scored;

    private Boolean isPassed;

    private String questionAttempts;

}
