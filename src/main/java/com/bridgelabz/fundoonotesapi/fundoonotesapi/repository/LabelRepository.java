package com.bridgelabz.fundoonotesapi.fundoonotesapi.repository;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.LabelDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.NoteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LabelRepository extends JpaRepository<LabelDetails, Integer> {
    List<LabelDetails> findByUserDetailsId(int id);
    LabelDetails findById(Long id);
    @Transactional
    @Modifying
    void deleteById(Long id);

}
