package com.example.clinickapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private LocalDateTime usedDate;
    @ManyToOne
    private UserLogin byWhom;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private Integer quantity;
}
