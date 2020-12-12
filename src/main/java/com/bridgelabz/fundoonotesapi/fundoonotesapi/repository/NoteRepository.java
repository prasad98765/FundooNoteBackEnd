package com.bridgelabz.fundoonotesapi.fundoonotesapi.repository;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.NoteDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteDetails, Integer>  {
    List<NoteDetails> findByUserDetailsId(int id);
    @Query(value = "SELECT * FROM note_details u WHERE u.note_Id=?1", nativeQuery=true)
    NoteDetails findByNote_Id(String id);
    List<NoteDetails> findByUserDetailsIdAndIsPinedTrue(int id);
    List<NoteDetails> findByUserDetailsIdAndIsArchivedTrue(int id);
    List<NoteDetails> findByUserDetailsIdAndIsDeletedTrue(int id);

}