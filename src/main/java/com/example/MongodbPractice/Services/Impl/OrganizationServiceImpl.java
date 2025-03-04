package com.example.MongodbPractice.Services.Impl;

import com.example.MongodbPractice.Dto.*;
import com.example.MongodbPractice.Entity.*;
import com.example.MongodbPractice.Repository.*;
import com.example.MongodbPractice.Services.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

        // Get count of students enrolled in the organization
        @Override
        public long getStudentCount(String organizationId) {
            return studentRepository.countByOrganizationId(organizationId);
        }

        // Get count of students enrolled in each course
        @Override
        public Map<String, Long> getStudentsPerCourse(String organizationId) {
            List<CourseEntity> courses = courseRepository.findByOrganizationId(organizationId);
            Map<String, Long> studentCounts = new HashMap<>();

            for (CourseEntity course : courses) {
                long count = studentCourseRepository.countByCourseEntityId(course.getId());
                studentCounts.put(course.getName(), count);
            }

            return studentCounts;
        }

        // Get instructor details for each course
        @Override
        public List<CourseInstructorDto> getInstructorsPerCourse(String organizationId) {
            List<CourseEntity> courses = courseRepository.findByOrganizationId(organizationId);
            List<CourseInstructorDto> courseInstructors = new ArrayList<>();

            for (CourseEntity course : courses) {
                CourseInstructorDto dto = new CourseInstructorDto();
                dto.setCourseId(course.getId());
                dto.setCourseName(course.getName());

                // Find instructor for this course
                InstructorEntity instructor = instructorRepository.findByCourseEntityId(course.getId());
                if (instructor != null) {
                    InstructorDto instructorDto = new InstructorDto();
                    BeanUtils.copyProperties(instructor, instructorDto);
                    dto.setInstructor(instructorDto);
                }

                courseInstructors.add(dto);
            }

            return courseInstructors;
        }

        // Get count of instructors in organization
        @Override
        public long getInstructorCount(String organizationId) {
            return instructorRepository.countByOrganizationId(organizationId);
        }

        // Get all details by course ID
        @Override
        public CourseDetailsDto getCourseDetails(String courseId) {
            CourseEntity course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

            // Get instructor
            InstructorEntity instructor = instructorRepository.findByCourseEntityId(courseId);

            // Get students
            List<StudentCourse> enrollments = studentCourseRepository.findByCourseEntityId(courseId);
            List<StudentDto> students = new ArrayList<>();

            for (StudentCourse enrollment : enrollments) {
                StudentEntity student = enrollment.getStudentEntity();
                StudentDto studentDto = new StudentDto();
                BeanUtils.copyProperties(student, studentDto);
                studentDto.setCourseStatus(enrollment.getStatus());
                students.add(studentDto);
            }

            // Build response
            CourseDetailsDto details = new CourseDetailsDto();
            details.setCourseId(course.getId());
            details.setCourseName(course.getName());

            if (instructor != null) {
                InstructorDto instructorDto = new InstructorDto();
                BeanUtils.copyProperties(instructor, instructorDto);
                details.setInstructor(instructorDto);
            }

            details.setStudents(students);
            details.setTotalStudents(students.size());

            return details;
        }

        // Get students by course status
        @Override
        public Map<String, List<StudentDto>> getStudentsByStatus(String organizationId) {
            Map<String, List<StudentDto>> studentsByStatus = new HashMap<>();
            studentsByStatus.put("TO_DO", new ArrayList<>());
            studentsByStatus.put("IN_PROGRESS", new ArrayList<>());
            studentsByStatus.put("COMPLETED", new ArrayList<>());

            // Get all students in organization
            List<StudentEntity> orgStudents = studentRepository.findByOrganizationId(organizationId);
            Set<String> orgStudentIds = orgStudents.stream()
                    .map(StudentEntity::getId)
                    .collect(Collectors.toSet());

            // Get all enrollments
            List<StudentCourse> allEnrollments = studentCourseRepository.findAll();

            // Filter enrollments for students in this organization
            for (StudentCourse enrollment : allEnrollments) {
                if (orgStudentIds.contains(enrollment.getStudentEntity().getId())) {
                    String status = enrollment.getStatus();
                    StudentDto studentDto = new StudentDto();
                    BeanUtils.copyProperties(enrollment.getStudentEntity(), studentDto);
                    studentDto.setCourseId(enrollment.getCourseEntity().getId());
                    studentDto.setCourseName(enrollment.getCourseEntity().getName());

                    studentsByStatus.get(status).add(studentDto);
                }
            }

            return studentsByStatus;
        }



    @Override
    public List<OrganizationDto> getAllOrganizations() {
        List<OrganizationDto> organizationDtos = new ArrayList<>();
        List<OrganizationEntity> organizationEntityList = organizationRepository.findAll();
        for (OrganizationEntity organization : organizationEntityList) {
            OrganizationDto organizationDto = new OrganizationDto();
            BeanUtils.copyProperties(organization, organizationDto);
            organizationDtos.add(organizationDto);
        }
        return organizationDtos;
    }

    @Override
    public String addOrganization(OrganizationDto organizationDto) {
        // Convert DTO to Entity
        OrganizationEntity organizationEntity = new OrganizationEntity();
        BeanUtils.copyProperties(organizationDto, organizationEntity);

        // Save the entity
        OrganizationEntity savedEntity = organizationRepository.save(organizationEntity);

        // Convert back to DTO
        OrganizationDto savedDto = new OrganizationDto();
        BeanUtils.copyProperties(savedEntity, savedDto);

        return savedDto.toString();
    }

    @Override
    public String getOrganization(String Id) {
        OrganizationEntity organizationEntity = organizationRepository.findById(Id).orElse(null);
        if (organizationEntity == null) {
            throw new RuntimeException("Organization not found with id:" + Id);
        }
        OrganizationDto organizationDto = new OrganizationDto();
        BeanUtils.copyProperties(organizationEntity, organizationDto);
        return organizationDto.toString();
    }

    @Override
    public boolean deleteOrganization(String Id) {
        String studentDto = getOrganization(Id);
        if (studentDto != null) {
            organizationRepository.deleteById(Id);
            return true;
        }
        return false;
    }

}
