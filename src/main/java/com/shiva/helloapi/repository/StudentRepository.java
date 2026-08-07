package com.shiva.helloapi.repository;

import com.shiva.helloapi.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<StudentEntity, Integer> {
}