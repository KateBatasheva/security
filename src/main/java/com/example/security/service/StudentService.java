package com.example.security.service;

import com.example.security.entity.Role;
import com.example.security.entity.Student;
import com.example.security.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService implements UserDetailsService {

    @Autowired
    StudentRepo studentRepo;

    public Optional<Student> findByUsername(String username) {
        return studentRepo.findByStudentname(username);
    }

    public Optional<Student> findById(Long id) {
        return studentRepo.findById(id);
    }


    public void save(Student student){
        studentRepo.save(student);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String studentName) throws UsernameNotFoundException {
        Student student = findByUsername(studentName).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", studentName)));
        return new User(student.getStudentname(), student.getPassword(), mapRolesToAuthorities(student.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
