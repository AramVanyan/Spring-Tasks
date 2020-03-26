package com.epam.jdbc.dao;

import com.epam.jdbc.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Qualifier("MySqlImpl")
public class StudentDaoImplMySql implements StudentDao {
    private Connection connection;
    private PreparedStatement statement;

    @Autowired
    public StudentDaoImplMySql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addNewStudent(Student s) {
        try {
            statement = connection.prepareStatement("INSERT INTO students(id,firstName,lastName,dateOfBirth,facultyName)" +
                    "VALUES(?,?,?,?,?)");
            statement.setInt(1,s.getId());
            statement.setString(2,s.getFirstName());
            statement.setString(3,s.getLastName());
            statement.setDate(4, s.getDateOfBirth());
            statement.setString(5,s.getFacultyName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(int id, Student s) {
        try {
            statement = connection.prepareStatement("UPDATE students" +
                    "SET id = ?" +
                    "firstName = ?" +
                    "lastName = ?" +
                    "dateOfBirth = ?" +
                    "facultyName = ?");
            statement.setInt(1,s.getId());
            statement.setString(2,s.getFirstName());
            statement.setString(3,s.getLastName());
            statement.setDate(4,s.getDateOfBirth());
            statement.setString(5,s.getFacultyName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Student> getStudentById(int id) {
        Student student = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM students " +
                    "WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                student = new Student(resultSet.getInt("id"),
                                   resultSet.getString("firstName"),
                                   resultSet.getString("lastName"),
                                   resultSet.getDate("dateOfBirth"),
                                   resultSet.getString("facultyName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(student);
    }

    @Override
    public void deleteStudent(int id) {
        try {
            statement = connection.prepareStatement("DELETE FROM students WHERE id = ?");
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> listOfStudents = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM students");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                listOfStudents.add(new Student(resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("dateOfBirth"),
                        resultSet.getString("facultyName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfStudents;
    }
}
