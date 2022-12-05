package com.example.bd_project.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import java.io.Serializable;

@Table(name = "payment_type")
@Entity
@Getter
@Setter
public class PaymentType implements Serializable {

    @Id
    private int payment_id;

    private String payment_name;
}
