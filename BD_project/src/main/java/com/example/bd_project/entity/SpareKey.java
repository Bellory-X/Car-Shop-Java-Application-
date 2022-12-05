package com.example.bd_project.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class SpareKey implements Serializable {

    String name;

    String model;

    String type;
}
