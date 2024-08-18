package org.example.spring.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class NameAndCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String status;
    private LocalDateTime presentTime;

    public NameAndCategory() {
        // Default constructor
    }

    public NameAndCategory(String name, String type, String status, LocalDateTime presentTime) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.presentTime = presentTime;
    }

    // Getters and Setters for all fields

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPresentTime() {
        return presentTime;
    }

    public void setPresentTime(LocalDateTime presentTime) {
        this.presentTime = presentTime;
    }
}
