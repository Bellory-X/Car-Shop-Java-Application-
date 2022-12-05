package com.example.bd_project.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

import java.io.Serializable;

@Table(name = "sellers")
@Entity
@Getter
@Setter
public class Sellers implements Serializable {


    @Id
    private int passport_number;

    private String license_plate;
    private Date buy_date;
    private long id_buy_license;
}
