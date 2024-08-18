open module AttendenceSheet {
    requires spring.core;
    requires javafx.controls;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires javafx.graphics;
    requires java.base;
    requires jakarta.persistence;
    requires spring.data.jpa;
    requires spring.beans;

    requires org.hibernate.orm.core;
    requires spring.data.commons;
    requires java.desktop;
    requires jfoenix;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires flying.saucer.pdf;
    requires spring.tx;
    requires spring.orm; // Add this line for Hibernate dependency

    exports org.example.spring;
    exports org.example.spring.repository; // Assuming RepositoryManager is in this package
}
