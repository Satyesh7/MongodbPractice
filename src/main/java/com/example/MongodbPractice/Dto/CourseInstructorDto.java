package com.example.MongodbPractice.Dto;

public class CourseInstructorDto {
    private String courseId;
    private String courseName;
    private InstructorDto instructor;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public InstructorDto getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorDto instructor) {
        this.instructor = instructor;
    }
}

