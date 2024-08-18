package org.example.spring.model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.*;

@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeId;
    private String name;
    private String currentTimee;
    private String position;
    private String status;

    // Constructor (optional, depending on your needs)
    public Position(){

    }
    public Position(String employeeId, String name, String currentTime, String position, String status) {
        this.employeeId = employeeId;
        this.name = name;
        this.currentTimee = currentTime;
        this.position = position;
        this.status = status;
    }

    // Getter methods
    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getCurrentTime() {
        return currentTimee;
    }

    public String getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }

    // Setter methods
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTimee = currentTime;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // You can add additional methods or behavior as needed
}
