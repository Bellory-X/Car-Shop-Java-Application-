package com.example.bd_project.entity;


import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "spare")
@Entity
@Getter
@Setter
public class Spare implements Serializable {

    @EmbeddedId
    SpareKey key;

    int stash_count;
}
