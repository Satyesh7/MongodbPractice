package com.example.MongodbPractice.Dto;

import java.util.Date;

public class InstructorDto {
    private String id;
    private String name;
    private Date dateOfBirth;
    private String organizationId;
    private CourseDto courseDto;

    public CourseDto getCourseDto() {
        return courseDto;
    }

    public void setCourseDto(CourseDto courseDto) {
        this.courseDto = courseDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
    @Override
    public String toString() {
        return "InstructorDTO{" +
             //   "id=" + id +
                " name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", organizationId=" + organizationId +
                '}';
    }
}

