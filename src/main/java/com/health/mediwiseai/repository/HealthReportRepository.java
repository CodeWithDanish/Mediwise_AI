package com.health.mediwiseai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.mediwiseai.model.HealthReport;
import com.health.mediwiseai.model.User;

@Repository
public interface HealthReportRepository extends JpaRepository<HealthReport, Long> {
    List<HealthReport> findByUser(User user);
}

