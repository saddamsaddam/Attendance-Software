package org.example.spring.controller;

import org.example.spring.model.EmployeeInsertedData;
import org.example.spring.repository.RepositoryManager;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class UpdateDataController {


	public  boolean updateData(RepositoryManager repositoryManager,String employeeId, String name, LocalDateTime entryTime,
							   String lateEntryReason, LocalDateTime exitTime, String earlyExitReason,
							   String status, LocalDateTime presentTime, String month, String year, String outtime, String entrydate,String globalDayStatus) throws SQLException {
		out.println( name+" "+ entryTime);
		repositoryManager.getEmployeeInsertedDataRepository().updateUpdateStatusByEmployeeIdAndEntryDate("0",employeeId,entrydate);
		repositoryManager.getEmployeeInsertedDataRepository().save(new EmployeeInsertedData(employeeId, name, month, year, entryTime, lateEntryReason, exitTime, earlyExitReason, status, outtime, entrydate, presentTime,"1",globalDayStatus));

		//AddDataModel.insertData(employeeId, name, entryTime, lateEntryReason, exitTime, earlyExitReason, status, presentTime, month, year, outtime, entrydate);
		
		return true;
	}
	
	public List<EmployeeInsertedData> retrieveData(RepositoryManager repositoryManager) throws SQLException{

		return repositoryManager.getEmployeeInsertedDataRepository().findByUpdateStatus("1");
	}

	public String globalDayStatus(RepositoryManager repositoryManager,String entryDate) throws SQLException {
		List<EmployeeInsertedData> data=retrieveData(repositoryManager);
		for (EmployeeInsertedData e:data)
		{
			if(e.getEntryDate().equals(entryDate))
				return e.getGlobalDayStatus();
		}
		return null;
	}
	public static List<EmployeeInsertedData> listData(Iterable<EmployeeInsertedData> data)
	{
		List<EmployeeInsertedData> tr= new ArrayList<>();
		data.forEach(tr::add);
		return tr;
	}

}
