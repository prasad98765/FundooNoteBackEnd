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
    public long id;

    public String labelName;

    public Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userDetails_id",nullable = false)
    private UserDetails userDetails;

    public LabelDetails() {
    }

    public LabelDetails(LabelDTO labelDTO,UserDetails userDetails){
        this.labelName = labelDTO.labelName;
        this.createdDate = new Date();
        this.userDetails = userDetails;
    }



}
