/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.shiva.helloapi.entity.StudentEntity
 *  com.shiva.helloapi.repository.StudentRepository
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.shiva.helloapi.repository;

import com.shiva.helloapi.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
extends JpaRepository<StudentEntity, Integer> {
}

