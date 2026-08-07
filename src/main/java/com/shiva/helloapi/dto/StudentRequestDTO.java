/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.shiva.helloapi.dto.StudentRequestDTO
 *  jakarta.validation.constraints.NotBlank
 *  jakarta.validation.constraints.Size
 */
package com.shiva.helloapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentRequestDTO {
    @NotBlank(message="name cannot be blank")
    @Size(min=2, max=40, message="name must be in between 2 and 40")
    private @NotBlank(message="name cannot be blank") @Size(min=2, max=40, message="name must be in between 2 and 40") String name;
    @NotBlank(message="course cannot be blank")
    @Size(min=2, max=40, message="course must be in between 2 and 40")
    private @NotBlank(message="course cannot be blank") @Size(min=2, max=40, message="course must be in between 2 and 40") String course;

    public StudentRequestDTO() {
    }

    public StudentRequestDTO(String name, String course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return this.name;
    }

    public String getCourse() {
        return this.course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}

