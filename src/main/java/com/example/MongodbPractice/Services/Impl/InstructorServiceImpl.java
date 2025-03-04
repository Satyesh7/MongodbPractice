package com.example.MongodbPractice.Services.Impl;

import com.example.MongodbPractice.Dto.CourseDto;
import com.example.MongodbPractice.Dto.InstructorDto;
import com.example.MongodbPractice.Dto.StudentCourseDto;
import com.example.MongodbPractice.Dto.StudentDto;
import com.example.MongodbPractice.Entity.CourseEntity;
import com.example.MongodbPractice.Entity.InstructorEntity;
import com.example.MongodbPractice.Entity.StudentCourse;
import com.example.MongodbPractice.Entity.StudentEntity;
import com.example.MongodbPractice.Repository.CourseRepository;
import com.example.MongodbPractice.Repository.InstructorRepository;
import com.example.MongodbPractice.Repository.StudentCourseRepository;
import com.example.MongodbPractice.Services.InstructorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;

        // Update course status for a student
        @Override
        public StudentCourseDto updateStudentCourseStatus(String instructorId, String studentId,
                                                          String courseId, String status) {
            // Validate status
            if (!Arrays.asList("TO_DO", "IN_PROGRESS", "COMPLETED").contains(status)) {
                throw new IllegalArgumentException("Invalid status. Must be TO_DO, IN_PROGRESS, or COMPLETED");
            }

            // Verify instructor teaches this course
            InstructorEntity instructor = instructorRepository.findById(instructorId)
                    .orElseThrow(() -> new RuntimeException("Instructor not found"));

            if (instructor.getCourseEntity() == null ||
                    !instructor.getCourseEntity().getId().equals(courseId)) {
                throw new RuntimeException("Instructor does not teach this course");
            }

            // Find the enrollment
            StudentCourse enrollment = studentCourseRepository
                    .findByStudentEntityIdAndCourseEntityId(studentId, courseId);

            if (enrollment == null) {
                throw new RuntimeException("Student is not enrolled in this course");
            }

            // Update status
            enrollment.setStatus(status);
            StudentCourse updated = studentCourseRepository.save(enrollment);

            // Convert to DTO
            StudentCourseDto dto = new StudentCourseDto();
            BeanUtils.copyProperties(updated, dto);

            // Set additional properties if needed
            dto.setStudentId(updated.getStudentEntity().getId());
            dto.setStudentName(updated.getStudentEntity().getName());
            dto.setCourseId(updated.getCourseEntity().getId());
            dto.setCourseName(updated.getCourseEntity().getName());

            return dto;
        }


    @Override
    public List<InstructorDto> getAllInstructors() {
        List<InstructorDto> instructorDtos = new ArrayList<>();
        List<InstructorEntity> instructorEntityList = instructorRepository.findAll();
        for (InstructorEntity instructor : instructorEntityList) {
            InstructorDto instructorDto = new InstructorDto();
            BeanUtils.copyProperties(instructor, instructorDto);
            instructorDtos.add(instructorDto);
        }
        return instructorDtos;
    }

    @Override
    public String addInstructor(InstructorDto instructorDto) {
        // Convert DTO to Entity
        InstructorEntity instructorEntity = new InstructorEntity();
        BeanUtils.copyProperties(instructorDto, instructorEntity);

        // Save the entity
        InstructorEntity savedEntity = instructorRepository.save(instructorEntity);

        // Convert back to DTO
        InstructorDto savedDto = new InstructorDto();
        BeanUtils.copyProperties(savedEntity, savedDto);

        return savedDto.toString();
    }

    @Override
    public String getInstructor(String Id) {
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity = instructorRepository.findById(Id).orElse(null);
        if (instructorEntity == null) {
            throw new RuntimeException("Instructor not found with id:" + Id);
        }
        InstructorDto instructorDto = new InstructorDto();
        BeanUtils.copyProperties(instructorEntity, instructorDto);
        return instructorDto.toString();
    }

    @Override
    public boolean deleteInstructor(String Id) {
        String instructor = getInstructor(Id);
        if (instructor != null) {
            instructorRepository.deleteById(Id);
            return true;
        }
        return false;
    }

    // Assign instructor to a course
    @Override
    public InstructorDto assignCourse(String instructorId, String courseId) {
        InstructorEntity instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + instructorId));

        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        // Check if instructor already has a course
        if (instructor.getCourseEntity() != null) {
            throw new IllegalStateException("Instructor is already assigned to a course");
        }

        // Update instructor with course reference
        instructor.setCourseEntity(course);
        InstructorEntity updatedInstructor = instructorRepository.save(instructor);

        // Convert to DTO and return
        InstructorDto dto = new InstructorDto();
        BeanUtils.copyProperties(updatedInstructor, dto);
        // Also set course details in DTO if needed
        if (dto.getCourseDto() == null) {
            CourseDto courseDto = new CourseDto();
            BeanUtils.copyProperties(course, courseDto);
            dto.setCourseDto(courseDto);
        }

        return dto;
    }

    @Override
    public void removeFromCourse(String instructorId) {
        InstructorEntity instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + instructorId));

        // Clear course reference
        instructor.setCourseEntity(null);
        instructorRepository.save(instructor);
    }

}
