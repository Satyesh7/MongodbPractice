package com.example.MongodbPractice.Controller;

import com.example.MongodbPractice.Dto.CourseDto;
import com.example.MongodbPractice.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    //Add a course
    @PostMapping("/addCourse")
    public CourseDto addCourse(@RequestBody CourseDto courseDto){
        return courseService.addCourse(courseDto);
    }

    //Get all courses
    @GetMapping("/getCourse")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courseList = courseService.getAllCourses();
        return new ResponseEntity<List<CourseDto>>(courseList, HttpStatus.OK);
    }

    //Get a course by Id
    @GetMapping("/getCourseById/{Id}")
    public String getCourse(@PathVariable String Id){
        return courseService.getCourse(Id);
    }

    //Delete a course by Id
    @DeleteMapping("/deleteCourseById/{Id}")
    public boolean deleteCourse(@PathVariable String Id){
       return courseService.deleteCourse(Id);
    }
}
