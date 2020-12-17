package com.bridgelabz.fundoonotesapi.fundoonotesapi.repository;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer>  {
    UserDetails findById(String id);
    UserDetails findByEmail(String email);

}
