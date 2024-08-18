package org.example.spring.model;

import jakarta.persistence.*;

@Entity
public class EmployeeData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Idnumber", nullable = false)
    private String idNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "joindate")
    private String joinDate;

    @Column(name = "designation")
    private String designation;

    @Column(name = "status")
    private String status;

    @Column(name = "enddate")
    private String endDate;

    private String position;
    private String currentTimee;


    // Constructors, getters, and setters

    // Constructors

    public EmployeeData() {
        // Default constructor
    }

    public EmployeeData(String idNumber, String name, String joinDate, String designation, String status, String endDate,String position,String currentTime) {
        this.idNumber = idNumber;
        this.name = name;
        this.joinDate = joinDate;
        this.designation = designation;
        this.status = status;
        this.endDate = endDate;
        this.position=position;
        this.currentTimee=currentTime;
    }

    // Getters and Setters

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCurrentTimee() {
        return currentTimee;
    }

    public void setCurrentTimee(String currentTimee) {
        this.currentTimee = currentTimee;
    }
}
