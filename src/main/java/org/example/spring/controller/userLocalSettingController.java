package org.example.spring.controller;

import java.util.Comparator;
import org.example.spring.model.*;

import org.example.spring.repository.RepositoryManager;


import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class userLocalSettingController {


    public boolean insertData(RepositoryManager repositoryManager,String employeeId, String name, String currentTime, String formattedBirthDate,
                              String formattedDeathDate, String startHours, String startMinute,
                              String endHours, String endMinute, String totalHours, String designation) {
        try {


            // Create a new UserLocalSetting object with the provided data
            UserLocalSetting newUserLocalSetting = new UserLocalSetting(employeeId, name, currentTime,
                    formattedBirthDate, formattedDeathDate, startHours, startMinute,
                    endHours, endMinute, totalHours, designation,"1");
            //update
           // repositoryManager.getUserLocalSettingRepository().updateStatusByEmployeeIdAndStatus(employeeId,"1","0");
            // Save the new UserLocalSetting object to the database
            repositoryManager.getUserLocalSettingRepository().save(newUserLocalSetting);

            return true;
        } catch (Exception e) {
            // Handle the exception or log the error
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateData(RepositoryManager repositoryManager,UserLocalSetting exData,String employeeId, String name, String currentTime, String formattedBirthDate,
                              String formattedDeathDate, String startHours, String startMinute,
                              String endHours, String endMinute, String totalHours, String designation) {
        try {


            // Create a new UserLocalSetting object with the provided data
            UserLocalSetting newUserLocalSetting = new UserLocalSetting(employeeId, name, currentTime,
                    formattedBirthDate, formattedDeathDate, startHours, startMinute,
                    endHours, endMinute, totalHours, designation,"1");
            //update
            // repositoryManager.getUserLocalSettingRepository().updateStatusByEmployeeIdAndStatus(employeeId,"1","0");
            repositoryManager.getUserLocalSettingRepository().updateStatusByEmployeeIdAndNameAndCurrentTime(exData.getEmployeeId(),exData.getName(),exData.getCurrentTime(),"0");
            // Save the new UserLocalSetting object to the database
            repositoryManager.getUserLocalSettingRepository().save(newUserLocalSetting);

            return true;
        } catch (Exception e) {
            // Handle the exception or log the error
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteData(RepositoryManager repositoryManager,UserLocalSetting exData,String employeeId, String name, String currentTime, String formattedBirthDate,
                              String formattedDeathDate, String startHours, String startMinute,
                              String endHours, String endMinute, String totalHours, String designation) {
        try {


            // Create a new UserLocalSetting object with the provided data
            UserLocalSetting newUserLocalSetting = new UserLocalSetting(employeeId, name, currentTime,
                    formattedBirthDate, formattedDeathDate, startHours, startMinute,
                    endHours, endMinute, totalHours, designation,"1");
            //update
            // repositoryManager.getUserLocalSettingRepository().updateStatusByEmployeeIdAndStatus(employeeId,"1","0");
            repositoryManager.getUserLocalSettingRepository().updateStatusByEmployeeIdAndNameAndCurrentTime(exData.getEmployeeId(),exData.getName(),exData.getCurrentTime(),"0");
            // Save the new UserLocalSetting object to the database
          //  repositoryManager.getUserLocalSettingRepository().save(newUserLocalSetting);

            return true;
        } catch (Exception e) {
            // Handle the exception or log the error
            e.printStackTrace();
            return false;
        }
    }
    public List<UserLocalSetting> retrieveData(RepositoryManager repositoryManager) throws SQLException{

      //  return repositoryManager.getUserLocalSettingRepository().findByStatus("1");
        return  listData(repositoryManager.getUserLocalSettingRepository().findByStatus("1"),Comparator.comparing(UserLocalSetting::getFormattedBirthDate));
       // return  listData(repositoryManager.getUserLocalSettingRepository().findAll(),Comparator.comparing(UserLocalSetting::getFormattedBirthDate));

    }

    public static List<UserLocalSetting> listData(Iterable<UserLocalSetting> data,Comparator<UserLocalSetting> comparator)
    {
        List<UserLocalSetting> tr= new ArrayList<>();
        data.forEach(tr::add);
        Collections.sort(tr, comparator.reversed()); // Sorting in descending order
        return tr;
    }

}
