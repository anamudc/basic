package com.angie.basic.repositories;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    private Long id;
    private String name;
    private String lastName;
    private LocalDate admissionDate;
    private Long baseSalary;
    private LocalDate finishDate;

    public EmployeeEntity(Long id, String name, String lastName, LocalDate admissionDate, Long baseSalary, LocalDate finishDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.admissionDate = admissionDate;
        this.baseSalary = baseSalary;
        this.finishDate = finishDate;
    }

    public EmployeeEntity() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Long getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Long baseSalary) {
        this.baseSalary = baseSalary;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

}
