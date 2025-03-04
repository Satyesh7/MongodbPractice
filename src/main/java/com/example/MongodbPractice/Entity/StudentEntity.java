package com.example.MongodbPractice.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = StudentEntity.COLLECTION_NAME)
public class StudentEntity implements Serializable {
    public static final String COLLECTION_NAME = "MongoStudent";
    @Id
    private String id;
    private String name;
    private Date dateOfBirth;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public List<CourseEnrollment> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<CourseEnrollment> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    private String organizationId;
    private List<String> courseIds;
    @DBRef
    @JsonIgnore
    private List<StudentCourse> studentCourses;
    @DBRef
    @JsonIgnore
    private OrganizationEntity organizationEntity;
    private List<CourseEnrollment> enrolledCourses = new ArrayList<>();

    // Nested class for enrollment details
    public static class CourseEnrollment {
        private String courseId;

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

        public EnrollmentStatus getStatus() {
            return status;
        }

        public void setStatus(EnrollmentStatus status) {
            this.status = status;
        }

        public LocalDateTime getEnrollmentDate() {
            return enrollmentDate;
        }

        public void setEnrollmentDate(LocalDateTime enrollmentDate) {
            this.enrollmentDate = enrollmentDate;
        }

        private String courseName;
        private EnrollmentStatus status = EnrollmentStatus.TO_DO;
        private LocalDateTime enrollmentDate;

        // Getters and setters
    }

    public enum EnrollmentStatus {
        TO_DO, IN_PROGRESS, COMPLETED
    }

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }


    public List<String> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<String> courseIds) {
        this.courseIds = courseIds;
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

    public OrganizationEntity getOrganizationEntity() {
        return organizationEntity;
    }

    public void setOrganizationEntity(OrganizationEntity organizationEntity) {
        this.organizationEntity = organizationEntity;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
