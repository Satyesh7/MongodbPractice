package com.example.MongodbPractice.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = CourseEntity.COLLECTION_NAME)
public class CourseEntity implements Serializable {
    public static final String COLLECTION_NAME = "MongoCourse";

    @Id
    private String id;

    private String name;

    private Double fee;

    private String instructorId;

    private String organizationId;
    @DBRef
    @JsonIgnore
    private List<StudentCourse> studentCourses;

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }
    @DBRef
    @JsonIgnore
    private InstructorEntity instructorEntity;
    @DBRef
    @JsonIgnore
    private OrganizationEntity organizationEntity;

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

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public InstructorEntity getInstructorEntity() {
        return instructorEntity;
    }

    public void setInstructorEntity(InstructorEntity instructorEntity) {
        this.instructorEntity = instructorEntity;
    }

    public OrganizationEntity getOrganizationEntity() {
        return organizationEntity;
    }

    public void setOrganizationEntity(OrganizationEntity organizationEntity) {
        this.organizationEntity = organizationEntity;
    }
}
