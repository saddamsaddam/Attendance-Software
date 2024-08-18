package org.example.spring.controller;

import org.example.spring.model.EmployeeData;
import org.example.spring.repository.RegistrationRepository;
import org.example.spring.repository.RepositoryManager;

import java.util.List;


public class DeleteEmployeeController {


	public boolean delete(RepositoryManager repositoryManager, String id, String name, String date,String position)
	{
		repositoryManager.getRegistrationRepository().updateStatusAndEndDate(id,name,"2",date);

		// update position
		List<EmployeeData> data= AddEmployeeController.retrieveData(repositoryManager);

data.forEach(e->{
	if(Integer.parseInt(e.getPosition())>Integer.parseInt(position))
	{

		int pp=Integer.parseInt(e.getPosition())-1;
		//System.out.println(id+" "+ name+" "+" ddd "+position+" "+pp);
		repositoryManager.getRegistrationRepository().updatePositionByIdNumberAndNameAndStatus(e.getIdNumber(),e.getName(),"1",Integer.toString(pp));
	}
	else
	{
		System.out.println(" "+position);
	}
});
		//DeleteEmployeeModel.delete(id, name,date);
		
		return true;
	}
	
}
