package com.epam.jdbc.dao;

import com.epam.jdbc.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    void addNewStudent(Student s);
    void updateStudent(int id,Student s);
    Optional<Student> getStudentById(int id);
    void deleteStudent(int id);
    List<Student> getAllStudents();
}
