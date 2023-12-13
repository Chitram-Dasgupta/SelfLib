package com.example.SelfLib.domain.entities;

import jakarta.persistence.*;
//import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @GeneratedValue
    private Long id;

//    @NotBlank(message = "Name cannot be blank")
    private String name;

//    @NotBlank(message = "Age cannot be blank")
//    @Min(0)
//    @Max(125)
    private Integer age;
}
