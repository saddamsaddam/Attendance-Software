package org.example.spring.controller;


import org.example.spring.model.Position;

import org.example.spring.repository.RepositoryManager;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PositionController {


    public boolean insertData(RepositoryManager repositoryManager, String employeeId, String name,  String position) {
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();

            // Format the current date and time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String currentTime = currentDateTime.format(formatter);
            Position newPosition = new Position(employeeId, name, currentTime, position, "1");
            repositoryManager.getPositionRepository().save(newPosition);
            return true;
        } catch (Exception e) {
            // Handle the exception or log the error
            e.printStackTrace();
            return false;
        }
    }

    public List<Position> retrieveData(RepositoryManager repositoryManager) throws SQLException{

        return repositoryManager.getPositionRepository().findAll();
    }


}
