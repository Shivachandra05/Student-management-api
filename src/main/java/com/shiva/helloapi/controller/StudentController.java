package com.shiva.helloapi.controller;

import com.shiva.helloapi.dto.PagedResponse;
import com.shiva.helloapi.dto.StudentRequestDTO;
import com.shiva.helloapi.dto.StudentResponseDTO;
import com.shiva.helloapi.service.StudentService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/all")
    public ResponseEntity<StudentResponseDTO> addStudent(
            @RequestBody @Valid StudentRequestDTO request) {

        StudentResponseDTO response =
                studentService.addStudent(request);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {

        List<StudentResponseDTO> students =
                studentService.getAllStudents();

        return new ResponseEntity<>(
                students,
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentWithId(
            @PathVariable int id) {

        StudentResponseDTO student =
                studentService.findByStudentId(id);

        return new ResponseEntity<>(
                student,
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudentWithId(
            @PathVariable int id,
            @RequestBody @Valid StudentRequestDTO request) {

        StudentResponseDTO response =
                studentService.updateStudentWithId(id, request);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentWithId(
            @PathVariable int id) {

        studentService.studentDeleteById(id);

        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping
    public ResponseEntity<PagedResponse<StudentResponseDTO>> getStudentsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        PagedResponse<StudentResponseDTO> response =
                studentService.getStudentsPaged(
                        page,
                        size,
                        sortBy,
                        direction
                );

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    @GetMapping("/me")
    public Object me(Authentication authentication) {
        return authentication.getAuthorities();
    }

    @PreAuthorize("denyAll()")
    @GetMapping("/deny")
    public String deny() {
        return "should never print";
    }
}