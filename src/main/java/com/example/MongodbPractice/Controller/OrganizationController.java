package com.example.MongodbPractice.Controller;

import com.example.MongodbPractice.Dto.CourseDetailsDto;
import com.example.MongodbPractice.Dto.CourseInstructorDto;
import com.example.MongodbPractice.Dto.OrganizationDto;
import com.example.MongodbPractice.Dto.StudentDto;
import com.example.MongodbPractice.Services.InstructorService;
import com.example.MongodbPractice.Services.OrganizationService;
import com.example.MongodbPractice.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;


        // Get count of students enrolled in the organization
        @GetMapping("/{organizationId}/students/count")
        public ResponseEntity<Map<String, Long>> getStudentCount(@PathVariable String organizationId) {
            long count = organizationService.getStudentCount(organizationId);
            Map<String, Long> response = Collections.singletonMap("count", count);
            return ResponseEntity.ok(response);
        }

        // Get count of students enrolled in each course
        @GetMapping("/{organizationId}/courses/students/count")
        public ResponseEntity<Map<String, Long>> getStudentsPerCourse(@PathVariable String organizationId) {
            Map<String, Long> counts = organizationService.getStudentsPerCourse(organizationId);
            return ResponseEntity.ok(counts);
        }

        // Get instructor details for each course
        @GetMapping("/{organizationId}/courses/instructors")
        public ResponseEntity<List<CourseInstructorDto>> getInstructorsPerCourse(@PathVariable String organizationId) {
            List<CourseInstructorDto> instructors = organizationService.getInstructorsPerCourse(organizationId);
            return ResponseEntity.ok(instructors);
        }

        // Get count of instructors in organization
        @GetMapping("/{organizationId}/instructors/count")
        public ResponseEntity<Map<String, Long>> getInstructorCount(@PathVariable String organizationId) {
            long count = organizationService.getInstructorCount(organizationId);
            Map<String, Long> response = Collections.singletonMap("count", count);
            return ResponseEntity.ok(response);
        }

        // Get all details by course ID
        @GetMapping("/courses/{courseId}/details")
        public ResponseEntity<CourseDetailsDto> getCourseDetails(@PathVariable String courseId) {
            CourseDetailsDto details = organizationService.getCourseDetails(courseId);
            return ResponseEntity.ok(details);
        }

        // Get students by course status
        @GetMapping("/{organizationId}/students/by-status")
        public ResponseEntity<Map<String, List<StudentDto>>> getStudentsByStatus(@PathVariable String organizationId) {
            Map<String, List<StudentDto>> studentsByStatus = organizationService.getStudentsByStatus(organizationId);
            return ResponseEntity.ok(studentsByStatus);
        }

        //Add organization
    @PostMapping("/addOrganization")
    public String addOrganization(@RequestBody OrganizationDto organizationDto){
        return organizationService.addOrganization(organizationDto);
    }
    //Get ALL Organizations
    @GetMapping("/getOrganization")
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        List<OrganizationDto> organizationDtoList = organizationService.getAllOrganizations();
        return new ResponseEntity<List<OrganizationDto>>(organizationDtoList, HttpStatus.OK);
    }
    //Get Organization by Id
    @GetMapping("/getOrganizationById/{Id}")
    public String getOrganization(@PathVariable String Id){
        return organizationService.getOrganization(Id);
    }
    //Delete Organization by Id
    @DeleteMapping("/deleteOrganizationById/{Id}")
    public boolean deleteOrganization(@PathVariable String Id){
        return organizationService.deleteOrganization(Id);
    }


}
