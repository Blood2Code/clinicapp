package com.example.clinickapp.repository;

import com.example.clinickapp.entity.Utilization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilizationRepository extends JpaRepository<Utilization, Long> {
    List<Utilization> findAllByPatient_Id(Long id);
}
