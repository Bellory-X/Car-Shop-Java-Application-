package com.example.bd_project.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Table(name = "car")
@Entity
@Getter
@Setter
public class Car implements Serializable {

    @Id
    String license_plate;

    String model;

    String type;

    String color;

    long engine_number;

    long car_body_number;

    long chassis_number;

    Date release_date;

    int car_mileage;

    int release_price;

    int sale_price;

    int buy_price;

    long number_tech_cond;

    Date date_tech_cond;

    String expert_full_name;
}
