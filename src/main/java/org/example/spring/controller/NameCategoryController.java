package org.example.spring.controller;


import org.example.spring.model.NameAndCategory;
import org.example.spring.model.Position;
import org.example.spring.model.UserLocalSetting;
import org.example.spring.repository.RepositoryManager;
import org.example.spring.view.AssetView;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NameCategoryController {


    public boolean insertData(RepositoryManager repositoryManager, String name, String type,  String status,LocalDateTime currentTime) {
        try {

            repositoryManager.getNameAndCategoryRepository().save(new NameAndCategory(name,type,status,currentTime));
            return true;
        } catch (Exception e) {
            // Handle the exception or log the error
            e.printStackTrace();
            return false;
        }
    }

    public List<NameAndCategory > retrieveData(RepositoryManager repositoryManager,String type) throws SQLException{

        return repositoryManager.getNameAndCategoryRepository().findAllByTypeAndStatus(type,"1");
    }

    public boolean updateData(RepositoryManager repositoryManager, NameAndCategory exData, AssetView.AssetData presentData, String type) {
        try {

            repositoryManager.getNameAndCategoryRepository().updateStatusByPresentTime(exData.getPresentTime(),"0");
            // Create a new UserLocalSetting object with the provided data

            repositoryManager.getNameAndCategoryRepository().save(new NameAndCategory(presentData.getAssetName(),type,"1", LocalDateTime.now()));

            return true;
        } catch (Exception e) {
            // Handle the exception or log the error
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteData(RepositoryManager repositoryManager,NameAndCategory exData, AssetView.AssetData presentData, String type) {
        try {


            repositoryManager.getNameAndCategoryRepository().updateStatusByPresentTime(exData.getPresentTime(),"2");

            return true;
        } catch (Exception e) {
            // Handle the exception or log the error
            e.printStackTrace();
            return false;
        }
    }
}
