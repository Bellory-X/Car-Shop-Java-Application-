package com.example.bd_project.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Table(name = "status_change")
@Entity
@Getter
@Setter
public class StatusChange implements Serializable {

    @Id
    int number;

    String cause;

    String position;

    Date order_date;
}
