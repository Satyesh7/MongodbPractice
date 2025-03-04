package com.example.MongodbPractice.Repository;

import com.example.MongodbPractice.Entity.StudentCourse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends MongoRepository<StudentCourse, String > {
    StudentCourse findByStudentEntityIdAndCourseEntityId(String studentId, String courseId);
    List<StudentCourse> findByStudentEntityId(String studentId);
    List<StudentCourse> findByCourseEntityId(String courseId);
    long countByCourseEntityId(String courseId);
}
