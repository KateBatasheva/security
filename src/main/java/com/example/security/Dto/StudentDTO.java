package com.example.security.Dto;

import com.example.security.entity.Student;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDTO {

    String studentName;
    Integer score;

    public StudentDTO(Student student) {
        this.studentName = student.getStudentname();
        this.score = student.getScore();
    }

    @Override
    public String toString() {
        return "Student: " +
                "name='" + studentName + '\'' +
                ", score=" + score;
    }
}
