package com.epam.jdbc;

import com.epam.jdbc.configuration.AppConfig;
import com.epam.jdbc.entity.Student;
import com.epam.jdbc.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentService studentService = applicationContext.getBean("studentService",StudentService.class);
        Student student = studentService.getStudentById(3).orElseThrow();
        System.out.println(student);
        System.out.println(studentService.getAllStudents());
        studentService.deleteStudent(7);

    }
}
