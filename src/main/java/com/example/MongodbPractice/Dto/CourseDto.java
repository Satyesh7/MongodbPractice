package com.example.MongodbPractice.Dto;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseDto implements Serializable {

    private String id;
    private String name;
    private Double fee;
    private String instructorId;
    private String organizationId;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Override
    public String toString() {
        return "CourseDTO{" +
                //"id=" + id +
                ", name='" + name + '\'' +
                ", fee=" + fee +
              //  ", instructorId=" + instructorId +
              //  ", organizationId=" + organizationId +
                '}';
    }
}
