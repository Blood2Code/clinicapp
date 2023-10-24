package com.example.clinickapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String gender;
    private Date birthDate;
    private String passportSerial;
    private Date receivedDate;
    private Integer wardNumber;
    private Integer wardBedNumber;
    private Date wardedDate;
    private String illness;
    private String diagnosis;
    @OneToMany(mappedBy = "patient")
    private List<Treatments> treatments;
    @OneToMany(mappedBy = "patient")
    private List<Utilization> utilizationList;
}
