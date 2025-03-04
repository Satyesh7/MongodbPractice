package com.example.MongodbPractice.Services;

import com.example.MongodbPractice.Dto.CourseDto;
import com.example.MongodbPractice.Entity.CourseEntity;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    public List<CourseDto> getAllCourses();

    public CourseDto addCourse(CourseDto courseDto);

    public String  getCourse(String Id);

    public boolean deleteCourse(String Id);

}
