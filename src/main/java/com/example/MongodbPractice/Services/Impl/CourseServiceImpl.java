package com.example.MongodbPractice.Services.Impl;

import com.example.MongodbPractice.Dto.CourseDto;
import com.example.MongodbPractice.Entity.CourseEntity;
import com.example.MongodbPractice.Repository.CourseRepository;
import com.example.MongodbPractice.Services.CourseService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<CourseDto> getAllCourses() {
        List<CourseDto> courseDtos = new ArrayList<>();
        List<CourseEntity> courseList = courseRepository.findAll();
        for (CourseEntity course : courseList) {
            CourseDto courseDto = new CourseDto();
            BeanUtils.copyProperties(course, courseDto);
            courseDtos.add(courseDto);
        }
        return courseDtos;
    }

    @Override
    public CourseDto addCourse(CourseDto courseDto) {
        // Convert DTO to Entity
        CourseEntity courseEntity = new CourseEntity();
        BeanUtils.copyProperties(courseDto, courseEntity);

        // Save the entity
        CourseEntity savedEntity = courseRepository.save(courseEntity);

        // Convert back to DTO
        CourseDto savedDto = new CourseDto();
        BeanUtils.copyProperties(savedEntity, savedDto);

        return savedDto;
    }

    @Override
    public String getCourse(String Id) {
        CourseEntity courseEntity =  courseRepository.findById(Id).get();
        if (courseEntity == null) {
            throw new RuntimeException("Course not found with id:" + Id);
        }
        CourseDto courseDto = new CourseDto();
        BeanUtils.copyProperties(courseEntity, courseDto);
        return courseDto.toString();
    }

    @Override
    public boolean deleteCourse(String Id) {
        String courseDto = getCourse(Id);
        if (courseDto != null) {
            courseRepository.deleteById(Id);
            return true;
        }
        return false;
    }

}

