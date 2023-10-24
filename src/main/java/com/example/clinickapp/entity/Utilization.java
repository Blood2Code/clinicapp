package com.example.clinickapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date usedDate;
    @ManyToOne
    private UserLogin byWhom;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private Integer quantity;
}
