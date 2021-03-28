package com.example.security.controller;

import com.example.security.Dto.StudentDTO;
import com.example.security.entity.Student;
import com.example.security.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/university")
public class MainController {
//    GET .../app/score/inc - увеличивает балл текущего пользователя
    // GET .../app/score/dec - уменьшает балл текущего пользователя
    // GET .../app/score/get/current - показывает балл вошедшего пользователя
    // GET .../app/score/get/{id} - показывает балл пользователя с указанным id (доступно
    // только для залогиненных пользователей)
    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public String showHome(Principal principal) {
        Student student = studentService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to fing user by username: " + principal.getName()));
        return "Welcome, " + student.getStudentname();
    }

    @GetMapping("/score/inc")
    public StudentDTO doInc(@RequestParam String studentname){
        Student student = studentService.findByUsername(studentname).orElseThrow(() -> new RuntimeException("unable to fing user by username: " + studentname));
        student.setScore(student.getScore()+1);
        studentService.save(student);
        return new StudentDTO(student);
    }

    @GetMapping("/score/dec")
    public StudentDTO doDec (@RequestParam String studentname){
        Student student = studentService.findByUsername(studentname).orElseThrow(() -> new RuntimeException("unable to fing user by username: " + studentname));
        student.setScore(student.getScore()-1);
        studentService.save(student);
        return new StudentDTO(student);
    }


    @GetMapping("/score/get/current")
    public StudentDTO getCurrent (Principal principal){
        Student student = studentService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to fing user by username: " + principal.getName()));
        return new StudentDTO(student);
    }


    @GetMapping("/score/get/{id}")
    public StudentDTO getById(@PathVariable Long id){
        Student student = studentService.findById(id).orElseThrow(() -> new RuntimeException("unable to fing user by Id: " + id));
        return new StudentDTO(student);
    }



}
