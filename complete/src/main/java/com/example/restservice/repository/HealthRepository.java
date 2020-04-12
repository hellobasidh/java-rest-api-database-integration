package com.example.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restservice.entity.HealthData;

public interface HealthRepository extends JpaRepository<HealthData, Long> {


}
