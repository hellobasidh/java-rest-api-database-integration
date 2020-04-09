package com.example.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.restservice.entity.PersonData;

@RepositoryRestResource(path = "persondata")
public interface UserRepository extends JpaRepository<PersonData, String> {

}
