package com.bridgelabz.fundoonotesapi.fundoonotesapi.module;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.LabelDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
@Table(name = "LabelDetails")
public class LabelDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long id;

    public String labelName;

    public Date createdDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userDetails_id")
    private UserDetails userDetails;

    @ManyToMany(mappedBy = "labelDetail")
    private List<NoteDetails> details;


    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public LabelDetails() {
    }

    public LabelDetails(LabelDTO labelDTO,UserDetails userDetails){
        this.labelName = labelDTO.labelName;
        this.createdDate = new Date();
        this.userDetails = userDetails;
    }



}
