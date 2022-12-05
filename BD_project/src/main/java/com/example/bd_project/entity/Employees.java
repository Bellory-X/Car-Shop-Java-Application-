package com.example.bd_project.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import java.io.Serializable;

@Table(name = "employees")
@Entity
@Getter
@Setter
public class Employees implements Serializable {

    @EmbeddedId
    EmployeesKey key;

    String position;

    long salary;

    int change_status;
}
