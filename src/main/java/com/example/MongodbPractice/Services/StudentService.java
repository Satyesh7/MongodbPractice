package com.example.MongodbPractice.Services;

import com.example.MongodbPractice.Dto.CourseDto;
import com.example.MongodbPractice.Dto.StudentDto;

import java.util.List;

public interface StudentService {
    public List<StudentDto> getAllStudents();

    public String addStudent(StudentDto studentDto);

    public String getStudent(String Id);

    public boolean deleteStudent(String Id);


}
