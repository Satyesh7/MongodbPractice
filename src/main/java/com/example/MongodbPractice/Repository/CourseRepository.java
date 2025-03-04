package com.example.MongodbPractice.Repository;

import com.example.MongodbPractice.Entity.CourseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<CourseEntity, String> {
    List<CourseEntity> findByOrganizationId(String organizationId);
}
