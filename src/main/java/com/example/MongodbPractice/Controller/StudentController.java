package com.example.MongodbPractice.Controller;

import com.example.MongodbPractice.Dto.CourseDto;
import com.example.MongodbPractice.Dto.StudentCourseDto;
import com.example.MongodbPractice.Dto.StudentDto;
import com.example.MongodbPractice.Services.StudentCourseService;
import com.example.MongodbPractice.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    StudentCourseService studentCourseService;

    //Add Student
    @PostMapping("/addStudent")
    public String addStudent(@RequestBody StudentDto studentDto){
        return studentService.addStudent(studentDto);
    }
    //Get All Students
    @GetMapping("/getStudent")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> studentList = studentService.getAllStudents();
        return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.OK);
    }
    //Get student by Id
    @GetMapping("/getStudentById/{Id}")
    public String getCourse(@PathVariable String Id){
        return studentService.getStudent(Id);
    }
    //Delete Student by Id
    @DeleteMapping("/deleteStudentById/{Id}")
    public boolean deleteStudent(@PathVariable String Id){
        return studentService.deleteStudent(Id);
    }

    // Enroll in a course
    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<StudentCourseDto> enrollInCourse(
            @PathVariable String studentId,
            @PathVariable String courseId) {
        StudentCourseDto enrollment = studentCourseService.enrollStudentInCourse(studentId, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
    }

    // Withdraw from a course
    @DeleteMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Void> withdrawFromCourse(
            @PathVariable String studentId,
            @PathVariable String courseId) {
        studentCourseService.withdrawStudentFromCourse(studentId, courseId);
        return ResponseEntity.noContent().build();
    }

    // Get all courses for a student
    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<StudentCourseDto>> getStudentCourses(@PathVariable String studentId) {
        List<StudentCourseDto> courses = studentCourseService.getCoursesForStudent(studentId);
        return ResponseEntity.ok(courses);
    }

    // Update course progress
    @PatchMapping("/courses/{enrollmentId}/status")
    public ResponseEntity<StudentCourseDto> updateCourseStatus(
            @PathVariable String enrollmentId,
            @RequestBody Map<String, String> statusUpdate) {
        String status = statusUpdate.get("status");
        StudentCourseDto updated = studentCourseService.updateCourseStatus(enrollmentId, status);
        return ResponseEntity.ok(updated);
    }
}
