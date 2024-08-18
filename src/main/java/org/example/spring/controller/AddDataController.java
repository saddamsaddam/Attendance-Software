package org.example.spring.controller;


import org.example.spring.model.EmployeeInsertedData;

import org.example.spring.repository.RepositoryManager;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddDataController {


	public  boolean insertData(RepositoryManager repositoryManager,String employeeId, String name, LocalDateTime entryTime,
							   String lateEntryReason, LocalDateTime exitTime, String earlyExitReason,
							   String status, LocalDateTime presentTime, String month, String year, String outtime, String entrydate,String globalDayStatus) throws SQLException {

		repositoryManager.getEmployeeInsertedDataRepository().save(new EmployeeInsertedData(employeeId, name, month, year, entryTime, lateEntryReason, exitTime, earlyExitReason, status, outtime, entrydate, presentTime,"1",globalDayStatus));

		//AddDataModel.insertData(employeeId, name, entryTime, lateEntryReason, exitTime, earlyExitReason, status, presentTime, month, year, outtime, entrydate);
		
		return true;
	}
	
	public List<EmployeeInsertedData> retrieveData(RepositoryManager repositoryManager) throws SQLException{

		return repositoryManager.getEmployeeInsertedDataRepository().findByUpdateStatus("1");
	}
	public static List<EmployeeInsertedData> listData(Iterable<EmployeeInsertedData> data)
	{
		List<EmployeeInsertedData> tr= new ArrayList<>();
		data.forEach(tr::add);
		return tr;
	}

	public  boolean dataCheck(RepositoryManager repositoryManager,String entryDate,String id)
	{

		return repositoryManager.getEmployeeInsertedDataRepository().existsByEntryDateAndEmployeeId(entryDate,id);
	}

}
