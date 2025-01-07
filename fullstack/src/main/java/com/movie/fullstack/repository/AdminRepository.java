package com.movie.fullstack.repository;



import com.movie.fullstack.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {
}

