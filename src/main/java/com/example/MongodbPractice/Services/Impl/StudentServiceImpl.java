package com.example.MongodbPractice.Services.Impl;

import com.example.MongodbPractice.Dto.CourseDto;
import com.example.MongodbPractice.Dto.StudentDto;
import com.example.MongodbPractice.Entity.CourseEntity;
import com.example.MongodbPractice.Entity.StudentEntity;
import com.example.MongodbPractice.Repository.StudentRepository;
import com.example.MongodbPractice.Services.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;


    @Override
    public List<StudentDto> getAllStudents() {
            List<StudentDto> studentDtos = new ArrayList<>();
            List<StudentEntity> studentEntityList = studentRepository.findAll();
            for (StudentEntity student : studentEntityList) {
                StudentDto studentDto = new StudentDto();
                BeanUtils.copyProperties(student, studentDto);
                studentDtos.add(studentDto);
            }
            return studentDtos;
    }

    @Override
    public String addStudent(StudentDto studentDto) {
        // Convert DTO to Entity
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(studentDto, studentEntity);

        // Save the entity
        StudentEntity savedEntity = studentRepository.save(studentEntity);

        // Convert back to DTO
        StudentDto savedDto = new StudentDto();
        BeanUtils.copyProperties(savedEntity, savedDto);

        return savedDto.toString();
    }

    @Override
    public String getStudent(String Id) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity = studentRepository.findById(Id).orElse(null);
        if (studentEntity == null) {
            throw new RuntimeException("Student not found with id:" + Id);
        }
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(studentEntity, studentDto);
        return studentDto.toString();
    }

    @Override
    public boolean deleteStudent(String Id) {
        String studentDto = getStudent(Id);
        if (studentDto != null) {
            studentRepository.deleteById(Id);
            return true;
        }
        return false;
    }

    }

