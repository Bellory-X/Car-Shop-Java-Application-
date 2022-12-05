package com.example.bd_project.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Table(name = "buyers")
@Entity
@Getter
@Setter
public class Buyers implements Serializable {

    @Id
    long passport_number;

    String license_plate;

    Date sale_date;

    long order_number;

    int payment_type;
}
