package com.bridgelabz.fundoonotesapi.fundoonotesapi.repository;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer>  {
//    UserDetails findById(String id);
    Optional<UserDetails> findByEmail(String email);

    UserDetails findById(int userId);
}
