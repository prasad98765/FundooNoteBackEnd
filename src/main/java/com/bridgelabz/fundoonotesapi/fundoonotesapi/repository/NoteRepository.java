package com.bridgelabz.fundoonotesapi.fundoonotesapi.repository;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.NoteDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteDetails, Integer>  {
    List<NoteDetails> findByUserDetailsId(int id);
    @Query(value = "SELECT * FROM note_details u WHERE u.note_Id=?1", nativeQuery=true)
    NoteDetails findByNote_Id(Long id);
    List<NoteDetails> findByUserDetailsIdAndIsPinedTrue(int id);
    List<NoteDetails> findByUserDetailsIdAndIsArchivedTrue(int id);
    List<NoteDetails> findByUserDetailsIdAndIsDeletedTrue(int id);

    @Transactional
    @Modifying
    void deleteByTitleAndIsDeletedTrue(String title);

    @Query(value = "SELECT * FROM note_details u INNER JOIN note_details_label_detail ue on ue.note_details_note_id = u.note_Id where ue.label_detail_id=?1 and u.user_details_id=?2 ", nativeQuery=true)
    List<NoteDetails> findByLabelNote_Id(Long id, int i);
}