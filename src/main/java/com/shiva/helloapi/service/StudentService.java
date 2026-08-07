package com.shiva.helloapi.service;

import com.shiva.helloapi.dto.PagedResponse;
import com.shiva.helloapi.dto.StudentRequestDTO;
import com.shiva.helloapi.dto.StudentResponseDTO;
import com.shiva.helloapi.entity.StudentEntity;
import com.shiva.helloapi.exception.InvalidStudentIdException;
import com.shiva.helloapi.exception.StudentNotFoundException;
import com.shiva.helloapi.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public StudentResponseDTO addStudent(StudentRequestDTO request) {

        StudentEntity student =
                new StudentEntity(
                        request.getName(),
                        request.getCourse()
                );

        student = repository.save(student);

        StudentResponseDTO response =
                new StudentResponseDTO(
                        student.getId(),
                        student.getName(),
                        student.getCourse()
                );

        return response;
    }

    public List<StudentResponseDTO> getAllStudents() {

        List<StudentEntity> students =
                repository.findAll();

        List<StudentResponseDTO> responses =
                new ArrayList<>();

        for (StudentEntity student : students) {

            responses.add(
                    new StudentResponseDTO(
                            student.getId(),
                            student.getName(),
                            student.getCourse()
                    )
            );
        }

        return responses;
    }

    public StudentResponseDTO findByStudentId(int id) {

        if (id <= 0) {
            throw new InvalidStudentIdException(id);
        }

        StudentEntity student =
                repository.findById(id)
                        .orElseThrow(
                                () -> new StudentNotFoundException(id)
                        );

        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getCourse()
        );
    }

    public StudentResponseDTO updateStudentWithId(
            int id,
            StudentRequestDTO request) {

        if (id <= 0) {
            throw new InvalidStudentIdException(id);
        }

        StudentEntity student =
                repository.findById(id)
                        .orElseThrow(
                                () -> new StudentNotFoundException(id)
                        );

        student.setName(request.getName());
        student.setCourse(request.getCourse());

        student = repository.save(student);

        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getCourse()
        );
    }

    public void studentDeleteById(int id) {

        if (id <= 0) {
            throw new InvalidStudentIdException(id);
        }

        StudentEntity student =
                repository.findById(id)
                        .orElseThrow(
                                () -> new StudentNotFoundException(id)
                        );

        repository.delete(student);
    }

    public PagedResponse<StudentResponseDTO> getStudentsPaged(
            int page,
            int size,
            String sortBy,
            String direction) {

        if (page < 0) {
            page = 0;
        }

        if (size <= 0) {
            size = 3;
        }

        if (!(sortBy.equals("id")
                || sortBy.equals("name")
                || sortBy.equals("course"))) {

            sortBy = "id";
        }

        Sort sort =
                direction.equalsIgnoreCase("desc")
                        ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending();

        PageRequest pageRequest =
                PageRequest.of(
                        page,
                        size,
                        sort
                );

        Page<StudentEntity> studentPage =
                repository.findAll(pageRequest);

        List<StudentResponseDTO> students =
                new ArrayList<>();

        for (StudentEntity student : studentPage.getContent()) {

            students.add(
                    new StudentResponseDTO(
                            student.getId(),
                            student.getName(),
                            student.getCourse()
                    )
            );
        }

        return new PagedResponse<>(
                students,
                studentPage.getNumber(),
                studentPage.getSize(),
                studentPage.getTotalPages(),
                studentPage.getTotalElements(),
                studentPage.isLast()
        );
    }
}