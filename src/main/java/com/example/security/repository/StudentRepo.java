package com.example.security.repository;

import com.example.security.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepo extends CrudRepository <Student, Long> {
    Optional<Student> findByStudentname (String studentname);
}
