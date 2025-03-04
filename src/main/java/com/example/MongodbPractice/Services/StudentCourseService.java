package com.example.MongodbPractice.Services;

import com.example.MongodbPractice.Dto.StudentCourseDto;

import java.util.List;

public interface StudentCourseService {
    public StudentCourseDto enrollStudentInCourse(String studentId, String courseId);

    public void withdrawStudentFromCourse(String studentId, String courseId);

    public StudentCourseDto updateCourseStatus(String enrollmentId, String status);

    public List<StudentCourseDto> getCoursesForStudent(String studentId);

}
