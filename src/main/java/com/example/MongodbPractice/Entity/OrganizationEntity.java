package com.example.MongodbPractice.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = OrganizationEntity.COLLECTION_NAME)
public class OrganizationEntity implements Serializable {
    public static final String COLLECTION_NAME = "MongoOrganization";

    @Id
    private String id;
    private String name;
    @DBRef
    private List<StudentEntity> students;
    @DBRef
    private List<InstructorEntity> instructors;
    @DBRef
    private List<CourseEntity> courses;

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

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }

    public List<InstructorEntity> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<InstructorEntity> instructors) {
        this.instructors = instructors;
    }

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }
}
