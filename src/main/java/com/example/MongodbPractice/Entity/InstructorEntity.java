package com.example.MongodbPractice.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = InstructorEntity.COLLECTION_NAME)
public class InstructorEntity implements Serializable {
    public static final String COLLECTION_NAME = "MongoInstructor";
    @Id
    private String id;
    private String name;
    private Date dateOfBirth;
    private String organizationId;
    @DBRef
    private CourseEntity courseEntity;
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

    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }

    public OrganizationEntity getOrganizationEntity() {
        return organizationEntity;
    }

    public void setOrganizationEntity(OrganizationEntity organizationEntity) {
        this.organizationEntity = organizationEntity;
    }

    @Override
    public String toString() {
        return "Instructor{" +
               // "id='" + id + '\'' +
                " name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", course=" + courseEntity.getName() +
                '}';
    }
}
