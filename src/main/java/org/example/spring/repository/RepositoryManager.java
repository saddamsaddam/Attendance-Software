package org.example.spring.repository;

import org.example.spring.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryManager {

    private final RegistrationRepository registrationRepository;
    private final LoginRepository loginRepository;
    private final EmployeeInsertedDataRepository employeeInsertedDataRepository;
    private final UserLocalSettingRepository userLocalSettingRepository; // Add the new repository
    private final UserGlobalSettingRepository userGlobalSettingRepository; // Corrected repository name
     private final PositionRepository positionRepository;
    private final NameAndCategoryRepository nameAndCategoryRepository;
    @Autowired
    public RepositoryManager(RegistrationRepository registrationRepository,
                             LoginRepository loginRepository,
                             EmployeeInsertedDataRepository employeeInsertedDataRepository,
                             UserLocalSettingRepository userLocalSettingRepository,UserGlobalSettingRepository userGlobalSettingRepository,
                             PositionRepository positionRepository,NameAndCategoryRepository nameAndCategoryRepository) { // Add the new repository to the constructor
        this.registrationRepository = registrationRepository;
        this.loginRepository = loginRepository;
        this.employeeInsertedDataRepository = employeeInsertedDataRepository;
        this.userLocalSettingRepository = userLocalSettingRepository;
        this.userGlobalSettingRepository = userGlobalSettingRepository;
        this.positionRepository=positionRepository;
        this.nameAndCategoryRepository=nameAndCategoryRepository;
    }

    public RegistrationRepository getRegistrationRepository() {
        return registrationRepository;
    }

    public LoginRepository getLoginRepository() {
        return loginRepository;
    }

    public EmployeeInsertedDataRepository getEmployeeInsertedDataRepository() {
        return employeeInsertedDataRepository;
    }

    public UserLocalSettingRepository getUserLocalSettingRepository() {
        return userLocalSettingRepository;
    }
    public UserGlobalSettingRepository getUserGlobalSettingRepository() { // Corrected method name
        return userGlobalSettingRepository;
    }
    public PositionRepository getPositionRepository() { return positionRepository;}
    public NameAndCategoryRepository getNameAndCategoryRepository() { return nameAndCategoryRepository;}

}
