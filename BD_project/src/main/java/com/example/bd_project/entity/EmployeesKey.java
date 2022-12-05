package com.example.bd_project.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Getter
@Setter
public class EmployeesKey implements Serializable {

//    private static final long serialVersionUID = -1746403047391226079L;
    String second_name;
    String first_name;
    String patronymic;
    Date birthday;
}
