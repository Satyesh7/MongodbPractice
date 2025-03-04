package com.example.MongodbPractice.Services.Impl;

import com.example.MongodbPractice.Dto.StudentCourseDto;
import com.example.MongodbPractice.Entity.CourseEntity;
import com.example.MongodbPractice.Entity.StudentCourse;
import com.example.MongodbPractice.Entity.StudentEntity;
import com.example.MongodbPractice.Repository.CourseRepository;
import com.example.MongodbPractice.Repository.StudentCourseRepository;
import com.example.MongodbPractice.Repository.StudentRepository;
import com.example.MongodbPractice.Services.StudentCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {
    @Autowired
    StudentCourseRepository studentCourseRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;


    @Override
    public StudentCourseDto enrollStudentInCourse(String studentId, String courseId) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        // Check if student is already enrolled in this course
        StudentCourse existingEnrollment = studentCourseRepository
                .findByStudentEntityIdAndCourseEntityId(studentId, courseId);

        if (existingEnrollment != null) {
            throw new IllegalStateException("Student is already enrolled in this course");
        }

        StudentCourse enrollment = new StudentCourse();
        enrollment.setStudentEntity(student);
        enrollment.setCourseEntity(course);
        enrollment.setStatus("TO_DO"); // Initial status

        StudentCourse savedEnrollment = studentCourseRepository.save(enrollment);

        // Convert to DTO and return
        StudentCourseDto dto = new StudentCourseDto();
        BeanUtils.copyProperties(savedEnrollment, dto);
        return dto;
    }

    @Override
    public void withdrawStudentFromCourse(String studentId, String courseId) {
        StudentCourse enrollment = studentCourseRepository
                .findByStudentEntityIdAndCourseEntityId(studentId, courseId);

        if (enrollment == null) {
            throw new RuntimeException("Enrollment not found");
        }

        studentCourseRepository.delete(enrollment);
    }

    @Override
    public StudentCourseDto updateCourseStatus(String enrollmentId, String status) {
        if (!Arrays.asList("TO_DO", "IN_PROGRESS", "COMPLETED").contains(status)) {
            throw new IllegalArgumentException("Invalid status. Must be TO_DO, IN_PROGRESS, or COMPLETED");
        }

        StudentCourse enrollment = studentCourseRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setStatus(status);
        StudentCourse updated = studentCourseRepository.save(enrollment);

        // Convert to DTO and return
        StudentCourseDto dto = new StudentCourseDto();
        BeanUtils.copyProperties(updated, dto);
        return dto;
    }

    @Override
    public List<StudentCourseDto> getCoursesForStudent(String studentId) {
        List<StudentCourse> enrollments = studentCourseRepository.findByStudentEntityId(studentId);

        List<StudentCourseDto> dtos = new ArrayList<>();
        for (StudentCourse enrollment : enrollments) {
            StudentCourseDto dto = new StudentCourseDto();
            BeanUtils.copyProperties(enrollment, dto);
            dtos.add(dto);
        }

        return dtos;
    }
}
