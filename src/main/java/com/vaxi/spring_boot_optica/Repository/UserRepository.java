package com.vaxi.spring_boot_optica.Repository;

import com.vaxi.spring_boot_optica.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  @Query("{ 'cedula': ?0 }")
  User findPacienteByCedula(String cedula);

  @Query("{'username': ?0}")
  Optional<User> findUserName(String usuario);
}
