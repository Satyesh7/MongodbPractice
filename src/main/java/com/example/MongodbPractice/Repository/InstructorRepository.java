package com.example.MongodbPractice.Repository;

import com.example.MongodbPractice.Entity.InstructorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends MongoRepository<InstructorEntity, String>{
    long countByOrganizationId(String organizationId);
    InstructorEntity findByCourseEntityId(String courseId);
}
