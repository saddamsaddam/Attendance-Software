package org.example.spring.controller;


import org.example.spring.model.UserGlobalSetting;

import org.example.spring.repository.RepositoryManager;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class GlobalSettingController {

	public boolean insert(RepositoryManager repositoryManager,  String formattedBirthDate,
						  String formattedDeathDate, String lateMinute, String earlyMinute)
	{
       // update
	//	repositoryManager.getUserGlobalSettingRepository().updateStatusByOldStatus("1","0");


		LocalDateTime currentDateTime = LocalDateTime.now();

		// Format the current date and time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		String currentTime = currentDateTime.format(formatter);
		repositoryManager.getUserGlobalSettingRepository().save(new UserGlobalSetting(currentTime, formattedBirthDate, formattedDeathDate, lateMinute, earlyMinute,"1"));


		return true;
	}
	public boolean updateData(RepositoryManager repositoryManager,UserGlobalSetting exData ,String formattedBirthDate,
							  String formattedDeathDate, String lateMinute, String earlyMinute ) {
		try {
			LocalDateTime currentDateTime = LocalDateTime.now();

			// Format the current date and time
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			String currentTime = currentDateTime.format(formatter);



			// Save the new UserLocalSetting object to the database
			repositoryManager.getUserGlobalSettingRepository().updateStatusByCurrentTime(exData.getCurrentTime(),"0");
			repositoryManager.getUserGlobalSettingRepository().save(new UserGlobalSetting(currentTime,formattedBirthDate,formattedDeathDate,lateMinute,earlyMinute,"1"));

			return true;
		} catch (Exception e) {
			// Handle the exception or log the error
			e.printStackTrace();
			return false;
		}
	}
	public boolean deleteData(RepositoryManager repositoryManager,UserGlobalSetting exData ,String formattedBirthDate,
							  String formattedDeathDate, String lateMinute, String earlyMinute ) {
		try {
			LocalDateTime currentDateTime = LocalDateTime.now();

			// Format the current date and time
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			String currentTime = currentDateTime.format(formatter);



			// Save the new UserLocalSetting object to the database
			repositoryManager.getUserGlobalSettingRepository().updateStatusByCurrentTime(exData.getCurrentTime(),"0");
			//repositoryManager.getUserGlobalSettingRepository().save(new UserGlobalSetting(currentTime,formattedBirthDate,formattedDeathDate,lateMinute,earlyMinute,"1"));

			return true;
		} catch (Exception e) {
			// Handle the exception or log the error
			e.printStackTrace();
			return false;
		}
	}
	public static List<UserGlobalSetting> retrieveData(RepositoryManager repositoryManager){

		//return repositoryManager.getUserGlobalSettingRepository().findAllByStatus("1");
		   return  listData(repositoryManager.getUserGlobalSettingRepository().findAllByStatus("1"),Comparator.comparing(UserGlobalSetting::getFormattedBirthDate));
	}

	public static List<UserGlobalSetting> listData(Iterable<UserGlobalSetting> data, Comparator<UserGlobalSetting> comparator)
	{
		List<UserGlobalSetting> tr= new ArrayList<>();
		data.forEach(tr::add);
		Collections.sort(tr, comparator.reversed()); // Sorting in descending order
		return tr;
	}
}
