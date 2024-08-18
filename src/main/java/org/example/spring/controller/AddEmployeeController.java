package org.example.spring.controller;

import org.example.spring.model.*;

import org.example.spring.repository.RepositoryManager;
import org.example.spring.view.PositionView;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.System.out;

@Component
public class AddEmployeeController {

	public boolean insert(RepositoryManager repositoryManager, String id, String name, String date, String deg,int count)
	{
		LocalDateTime currentDateTime = LocalDateTime.now();

		// Format the current date and time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		String currentTime = currentDateTime.format(formatter);

		repositoryManager.getRegistrationRepository().save(new EmployeeData(id,name,date,deg,"1",null,Integer.toString(count),currentTime));
		out.println(date);


		// Parse the string into a LocalDate object
		LocalDate originalDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);

		// Add 1 year to the original date
		LocalDate newDate = originalDate.plusYears(1);

		// Format and print the result
		String nextdate = newDate.format(DateTimeFormatter.ISO_LOCAL_DATE);




		repositoryManager.getUserLocalSettingRepository().save(new UserLocalSetting(id,name, currentTime,date,nextdate,"09","00","17","00","8","Soft","1"));
		//AddEmployeeModel.insert(id,name, date, deg);
		//repositoryManager.getPositionRepository().save(new Position(id,name,currentTime,Integer.toString(count),"1"));
		return true;
	}
	public void updateData(RepositoryManager repositoryManager, EmployeeData exData, String id,String name, String date, String deg  )
	{
		System.out.println("ID :"+id);
		LocalDateTime currentTime = LocalDateTime.now();

		// Define the desired date and time format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// Format the current time to a string
		String formattedTime = currentTime.format(formatter);
		repositoryManager.getRegistrationRepository().updateStatusWithCurrentTime("0",exData.getCurrentTimee());
		repositoryManager.getRegistrationRepository().save(new EmployeeData(id,name,date,deg,"1",null,exData.getPosition(),formattedTime));
// public EmployeeData(String idNumber, String name, String joinDate, String designation, String status, String endDate,String position,String currentTime) {
        // also update atendence data
		repositoryManager.getEmployeeInsertedDataRepository().updateEmployeeIdAndName(exData.getIdNumber(),exData.getName(),id,name);

	}
	public static List<EmployeeData> retrieveData(RepositoryManager repositoryManager){

		return sorting(repositoryManager.getRegistrationRepository().findByStatus("1"));
	}
	public static List<EmployeeData> ArchiveRetrieveData(RepositoryManager repositoryManager){

		return sorting(repositoryManager.getRegistrationRepository().findByStatus("2"));
	}
    public static List<EmployeeData> sorting(List<EmployeeData> data)
	{
		List<Integer> Prepositions=new ArrayList();
		data.forEach(e->{
			Prepositions.add(Integer.parseInt(e.getPosition()));
		});

		// sorting position
		Collections.sort(Prepositions);
		List<String> positions=new ArrayList();
		Prepositions.forEach(e->{
			positions.add(Integer.toString(e));
			out.println(e);
		});

		List<EmployeeData> returndata= new ArrayList<>();

		positions.forEach(e->{
			data.forEach(f->{
				if(e.equals(f.getPosition()))
				{
					returndata.add(f);
				}
			});
		});
		returndata.forEach(e->{
			out.println(e.getName()+"  "+e.getPosition());
		});
		return returndata;
	}
public 	List<EmployeeData> updatesorting(RepositoryManager repositoryManager,List<EmployeeData> employeeList,PositionView.Person singleRowData)
{
	List<EmployeeData> outresult=new ArrayList<>();
	for(int i=0;i<employeeList.size();i++)
	{
		if(i==(Integer.parseInt(singleRowData.getPosition())-1))
		{
			outresult.add(new EmployeeData(singleRowData.getFirstId(),singleRowData.getFirstName(),null,null,null,null,singleRowData.getPosition(),null));
			repositoryManager.getRegistrationRepository().updatePosition(singleRowData.getFirstId(),singleRowData.getFirstName(),"1",singleRowData.getPosition());

			break;
		}
		else {
			outresult.add(employeeList.get(i));
			repositoryManager.getRegistrationRepository().updatePosition(employeeList.get(i).getIdNumber(),employeeList.get(i).getName(),"1",employeeList.get(i).getPosition());
		}
	}
 int cc=Integer.parseInt(singleRowData.getPosition())+1;
	for(int i=Integer.parseInt(singleRowData.getPosition())-1;i<employeeList.size();i++)
	{
		if(singleRowData.getFirstId().equals(employeeList.get(i).getIdNumber())&& singleRowData.getFirstName().equals(employeeList.get(i).getName()) ){

		}
		else {

		outresult.add(new EmployeeData(employeeList.get(i).getIdNumber(),employeeList.get(i).getName(),null,null,null,null,Integer.toString(cc),null));
			repositoryManager.getRegistrationRepository().updatePosition(employeeList.get(i).getIdNumber(),employeeList.get(i).getName(),"1",Integer.toString(cc));
		cc++;
		}
	}

	return outresult;
}

}
