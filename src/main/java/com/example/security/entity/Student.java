package com.example.security.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "studentname")
    private String studentname;

    @Column(name = "password")
    private String password;

    @Column(name = "score")
    private Integer score;

    @ManyToMany
    @JoinTable(name = "students_roles",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentname='" + studentname + '\'' +
                ", score=" + score +
                ", roles=" + roles +
                '}';
    }
}
