package com.example.clinickapp.repository;

import com.example.clinickapp.entity.Treatments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentsRepository extends JpaRepository<Treatments, Long> {
    List<Treatments> findAllByPatient_Id(Long id);
}
