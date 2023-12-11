package com.miniproject.onlinequizapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   private String content;

   @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
   private List<Options> options;

}
