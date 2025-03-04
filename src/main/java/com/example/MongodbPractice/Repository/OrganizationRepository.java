package com.example.MongodbPractice.Repository;

import com.example.MongodbPractice.Entity.OrganizationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends MongoRepository<OrganizationEntity, String> {

}
