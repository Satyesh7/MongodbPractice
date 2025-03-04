package com.example.MongodbPractice.Repository;

import com.example.MongodbPractice.Entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<StudentEntity, String> {
    long countByOrganizationId(String organizationId);
    List<StudentEntity> findByOrganizationId(String organizationId);
}
