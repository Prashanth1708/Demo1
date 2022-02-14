package com.example.demo2.service;

import java.util.List;

import com.example.demo2.entity.Student;

public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudentById(Long id);

    Student updateStudent(Student student);
    void deleteStudentById(Long id);

}