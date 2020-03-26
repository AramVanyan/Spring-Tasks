package com.epam.jdbc.service;

import com.epam.jdbc.dao.StudentDao;
import com.epam.jdbc.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentService {
    private StudentDao studentDao;

    @Autowired
    public StudentService(@Qualifier("MySqlImpl") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void addNewStudent(Student s) {
        studentDao.addNewStudent(s);
    }

    public void updateStudent(int id, Student s) {
        studentDao.updateStudent(id,s);
    }

    public Optional<Student> getStudentById(int id) {
        return studentDao.getStudentById(id);
    }

    public void deleteStudent(int id) {
        studentDao.deleteStudent(id);
    }

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

}
