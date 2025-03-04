package com.example.MongodbPractice.Services;

import com.example.MongodbPractice.Dto.InstructorDto;
import com.example.MongodbPractice.Dto.StudentCourseDto;
import com.example.MongodbPractice.Dto.StudentDto;

import java.util.List;

public interface InstructorService {
    public List<InstructorDto> getAllInstructors();

    public String addInstructor(InstructorDto instructorDto);

    public String getInstructor(String Id);

    public boolean deleteInstructor(String Id);

    public InstructorDto assignCourse(String instructorId, String courseId);

    public void removeFromCourse(String instructorId);

    public StudentCourseDto updateStudentCourseStatus(String instructorId, String studentId,
                                                      String courseId, String status);

}
