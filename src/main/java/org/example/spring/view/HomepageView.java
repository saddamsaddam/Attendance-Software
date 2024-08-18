package org.example.spring.view;

import org.example.spring.controller.*;
import org.example.spring.controller.AddEmployeeController;
import org.example.spring.controller.DeleteEmployeeController;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.spring.model.EmployeeData;
import org.example.spring.model.EmployeeInsertedData;
import org.example.spring.model.UserGlobalSetting;
import org.example.spring.model.UserLocalSetting;
import org.example.spring.repository.RepositoryManager;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

@Component
public class HomepageView {

    RepositoryManager repositoryManager;
	AddEmployeeController AddEmployeeController;
	DeleteEmployeeController DeleteEmployeeController;
	AddDataController AddDataController;
    public HomepageView(RepositoryManager repositoryManager)
    {
       this.repositoryManager=repositoryManager;
    }
    public Scene createHomepageScene(Stage primaryStage) {
    	 AddEmployeeController =new AddEmployeeController();

    	 DeleteEmployeeController =new DeleteEmployeeController();
        // Create a TabPane
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); // Optional: Disable tab closing
        HomeView HomeScene= new HomeView();
        AddEmployeeView AddEmployeeScene= new AddEmployeeView();
        AddDataView AddDataScene=new AddDataView();
        UpdateDataView UpdateDataScene=new UpdateDataView();
        ShowDataView ShowDataScene=new ShowDataView();
        DownloadView DownloadScene=new DownloadView();
        DeleteEmployeeView DeleteEmployeeView=new DeleteEmployeeView();
        // Create tabs with content

        StackPane Attendencep = new StackPane();
        Tab Attendencet =new Tab("Attendence");
        Attendencet.setContent(Attendencep);

        StackPane Downloadp = new StackPane();
        Tab Downloadt =new Tab("Download");
        Downloadt.setContent(Downloadp);
        
        StackPane Userp = new StackPane();
        Tab Usert =new Tab("User");
        Usert.setContent(Userp);
        
        StackPane Settingp = new StackPane();
        Tab Settingt =new Tab("Setting");
        Settingt.setContent(Settingp);
        
        StackPane Employeep = new StackPane();
        Tab Employeet =new Tab("Employee");
        Employeet.setContent(Employeep);

        StackPane Archivep = new StackPane();
        Tab Archive =new Tab("Archive");
        Archive.setContent(Archivep);

        
        
       // Tab tab7 =DownloadScene.createTab("Download");
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            // Handle tab change event
            int selectedIndex = tabPane.getSelectionModel().getSelectedIndex();
            List<EmployeeData> employeeList = AddEmployeeController.retrieveData(repositoryManager);
        	List<EmployeeInsertedData> dataList=null;

            List<UserGlobalSetting> globalSettingdata=null;
            List<UserLocalSetting> localSettingdata=null;

            try {
                globalSettingdata= GlobalSettingController.retrieveData(repositoryManager);
                localSettingdata=new userLocalSettingController().retrieveData(repositoryManager);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
			try {
				 AddDataController= new  AddDataController();
				dataList = AddDataController.retrieveData(repositoryManager);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
            // Update the content of the selected tab based on the index
            switch (selectedIndex) {

                   case 0:
                       updatePanel(Attendencep,new AttendenceView().createTab(repositoryManager,employeeList,dataList));

                       // Handle the content update for the second tab
                       break;
                // ... Add more cases as needed
                       
                   case 1:
                       
                   	updatePanel(Userp,new UserView().createTab(repositoryManager,employeeList,dataList));
                      // Handle the content update for the second tab
                      break;
               // ... Add more cases as needed
                      
                   case 2:
                       
						try {
                            List<UserGlobalSetting> globalSettingData= GlobalSettingController.retrieveData(repositoryManager);

							updatePanel(Settingp,new GlobalSettingView().createTab(repositoryManager,globalSettingData));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                         // Handle the content update for the second tab
                         break;
                  // ... Add more cases as needed
                   case 3:
                       
                      	updatePanel(Employeep,new EmployeeView().   createTab(repositoryManager,employeeList,dataList));
                         // Handle the content update for the second tab
                         break;
                  // ... Add more cases as needed
                         
                      // ... Add more cases as needed
                   case 4:

                       updatePanel(Downloadp,new DownloadView().createTab(globalSettingdata,localSettingdata,employeeList,dataList));
                         // Handle the content update for the second tab
                         break;
                case 5:

                    updatePanel(Archivep,new ArchiveView().createTab(repositoryManager,employeeList,dataList));
                    // Handle the content update for the second tab
                    break;
                    // Handle the content update for the second tab
                  // ... Add more cases as needed
            }
        });

        // Add tabs to the TabPane
        tabPane.getTabs().addAll(Attendencet,Usert,Settingt,Employeet,Downloadt,Archive);

        // Create a layout for the homepage
        BorderPane homepageLayout = new BorderPane();
        homepageLayout.setTop(tabPane);


        Scene homepageScene;
        if (GraphicsEnvironment.isHeadless()) {
            // Set default scene size (modify these values as needed)
            homepageScene= new Scene(homepageLayout, 1300, 650);
        } else {
            // Get the screen size if the environment is not headless
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            homepageScene=new Scene(homepageLayout, screenSize.getWidth() - 70, screenSize.getHeight() - 70);
        }



        // Set the stage title
        primaryStage.setTitle("Homepage");

        // Set the stage scene
        primaryStage.setScene(homepageScene);

        return homepageScene;
    }

   
    
   

    static private void updatePanel(StackPane panel, BorderPane nodeToAdd) {
    	if (!panel.getChildren().isEmpty()) {
            // Check if any nodes exist, then clear
            panel.getChildren().clear();
        }

        panel.getChildren().add(nodeToAdd);
    }
    
}
