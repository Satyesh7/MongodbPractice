package com.example.MongodbPractice.Services;

import com.example.MongodbPractice.Dto.CourseDetailsDto;
import com.example.MongodbPractice.Dto.CourseInstructorDto;
import com.example.MongodbPractice.Dto.OrganizationDto;
import com.example.MongodbPractice.Dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface OrganizationService {
    public List<OrganizationDto> getAllOrganizations();

    public String addOrganization(OrganizationDto organizationDto);

    public String getOrganization(String Id);

    public boolean deleteOrganization(String Id);

    public long getStudentCount(String organizationId);

    public Map<String, Long> getStudentsPerCourse(String organizationId);

    public List<CourseInstructorDto> getInstructorsPerCourse(String organizationId);

    public long getInstructorCount(String organizationId);

    public CourseDetailsDto getCourseDetails(String courseId);

    public Map<String, List<StudentDto>> getStudentsByStatus(String organizationId);


}
