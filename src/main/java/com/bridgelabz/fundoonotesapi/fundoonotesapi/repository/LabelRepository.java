package com.bridgelabz.fundoonotesapi.fundoonotesapi.repository;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.LabelDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.NoteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelRepository extends JpaRepository<LabelDetails, Integer> {
    List<LabelDetails> findByUserDetailsId(int id);
}
