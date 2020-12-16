package com.bridgelabz.fundoonotesapi.fundoonotesapi.repository;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.LabelDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<LabelDetails, Integer> {

}
