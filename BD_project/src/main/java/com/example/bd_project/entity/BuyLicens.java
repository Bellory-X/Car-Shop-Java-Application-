package com.example.bd_project.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Table(name = "buy_licens")
@Entity
@Getter
@Setter
public class BuyLicens implements Serializable {

    @Id
    long id_buy_license;

    String name;

    Date issue_date;

    String employee_full_name;
}
