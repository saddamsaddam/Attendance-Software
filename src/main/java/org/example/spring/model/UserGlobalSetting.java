package org.example.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserGlobalSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currentTimee;
    private String formattedBirthDate;
    private String formattedDeathDate;
    private String lateMinute;
    private String earlyMinute;

    private String status; // New variable

    // Constructors

    // Default constructor
    public UserGlobalSetting() {
    }

    // Parameterized constructor
    public UserGlobalSetting(String currentTime, String formattedBirthDate,
                             String formattedDeathDate, String lateMinute, String earlyMinute, String status) {
        this.currentTimee = currentTime;
        this.formattedBirthDate = formattedBirthDate;
        this.formattedDeathDate = formattedDeathDate;
        this.lateMinute = lateMinute;
        this.earlyMinute = earlyMinute;
        this.status = status;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrentTime() {
        return currentTimee;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTimee = currentTime;
    }

    public String getFormattedBirthDate() {
        return formattedBirthDate;
    }

    public void setFormattedBirthDate(String formattedBirthDate) {
        this.formattedBirthDate = formattedBirthDate;
    }

    public String getFormattedDeathDate() {
        return formattedDeathDate;
    }

    public void setFormattedDeathDate(String formattedDeathDate) {
        this.formattedDeathDate = formattedDeathDate;
    }

    public String getLateMinute() {
        return lateMinute;
    }

    public void setLateMinute(String lateMinute) {
        this.lateMinute = lateMinute;
    }

    public String getEarlyMinute() {
        return earlyMinute;
    }

    public void setEarlyMinute(String earlyMinute) {
        this.earlyMinute = earlyMinute;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Other methods

    @Override
    public String toString() {
        return "UserGlobalSetting{" +
                "id=" + id +
                ", currentTime='" + currentTimee + '\'' +
                ", formattedBirthDate='" + formattedBirthDate + '\'' +
                ", formattedDeathDate='" + formattedDeathDate + '\'' +
                ", lateMinute='" + lateMinute + '\'' +
                ", earlyHours='" + earlyMinute + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    // Add other methods as needed
}
