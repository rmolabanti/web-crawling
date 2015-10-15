package com.raghu.crawling.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.raghu.crawling.models.Doctor;

public interface DoctorRepository extends MongoRepository<Doctor, String>{

}
