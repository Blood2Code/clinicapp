package com.example.clinickapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String role;
}
