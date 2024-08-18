package org.example.spring.view;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import org.example.spring.model.UserGlobalSetting;
import org.example.spring.model.UserLocalSetting;
import org.example.spring.repository.RepositoryManager;

import org.example.spring.controller.*;
import org.example.spring.model.EmployeeInsertedData;
import org.example.spring.model.EmployeeData;
import org.example.spring.view.ShowDataView.AttendanceData;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class UserView {
    RepositoryManager repositoryManager;
	int en=0; VBox dailyinfo;
	int datalength=0;
	int monthlength=0;
	String[] datek= {"","","","Date:"};
	String[] bark= {"SI.","Name","","Day:"};
	String[] truefalsek= {"","","","Is office day"};
	 int officedayc=0,presentdayc=0,avgtimec=0,totaltimec=0,leavedayc=0,absentdayc=0,holydayc=0,shorttimec=0,regulartimec=0,extratimec=0,intimec=0,latetimec=0,totallatetimec=0,okc=0,earlytimec=0,totalextratimec=0;
	AddDataController AddDataController;double outtimec=0;

	private ObservableList<String> names;
    private ListView<String> listView;
    private TextField searchField;
    private TextField dateTextField;
    int[] id=new int[500];
    Label totaltime,intotaltime, officeday,presentday,avgtime,leaveday,absentday,holyday,shorttime,regulartime,extratime,intime,latetime,totallatetime,ok,outtime,earlytime,totalextratime;
    Duration durationc=Duration.ZERO,totallatedurationc=Duration.ZERO,totalextradurationc=Duration.ZERO;Duration totaltimecc=Duration.ZERO;
    Duration intotaltimec=Duration.ZERO;
    
    TextField[] startreason;
    TextField[] endreason;
    ComboBox<String>[] statusbox;
    
    Spinner<Integer>[] starthour;
    Spinner<Integer>[] startminite;
    ComboBox<String>[] comboBoxstart;
    DatePicker[] datePicker;
    Spinner<Integer>[] endhour;
    Spinner<Integer>[] endminite;
    ComboBox<String>[] comboBoxend;
    boolean result;
    TableView<AttendanceData> tableView;List<EmployeeData> employeeList;
    int length=0;  Label labelh3date,starttime; Label labelh2date; Label labelh1date,labelh1dateday,labelh2dateday,labelh3dateday;
    String selectedparson,selectedparsonglance;
    String selectedmonth,selectedyear,selectedmonthglance,selectedyearglance,selectedmonthday,selectedyearday;
    List<EmployeeInsertedData> dataList;String[] tabs= {"Summury", "At A Glance","Setting","Asset","Profile"};
    List<UserLocalSetting> usersettingdata; List<UserGlobalSetting> globalSettingdata;   List<UserLocalSetting> localSettingdata;
    BorderPane createTab(RepositoryManager repositoryManager1, List<EmployeeData> employeeList1, List<EmployeeInsertedData> dataList1) {
		 dataList=dataList1;employeeList=employeeList1;
		 length=employeeList.size();
        repositoryManager=repositoryManager1;

		 BorderPane borderPane = new BorderPane();

        employeeList = AddEmployeeController.retrieveData(repositoryManager);
         dataList=null;
       usersettingdata=null;
        try {
            usersettingdata=new userLocalSettingController().retrieveData(repositoryManager);
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
        globalSettingdata=null;
         localSettingdata=null;

        try {
            globalSettingdata= GlobalSettingController.retrieveData(repositoryManager);
            localSettingdata=new userLocalSettingController().retrieveData(repositoryManager);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

	        TabPane tabPane = new TabPane();
	        
	        Tab tabsummry = new Tab();
            tabsummry.setGraphic(createTabHeader("Summary"));
            tabsummry.setClosable(false); // Set the tab to be closable
            StackPane tabContentsummry =new StackPane();
            tabContentsummry.getChildren().add(new SummaryDataView().createTab(repositoryManager,employeeList,dataList,globalSettingdata,localSettingdata));
            tabsummry.setContent(tabContentsummry);
            tabPane.getTabs().add(tabsummry);
            
            Tab tabataglance = new Tab();
            tabataglance.setGraphic(createTabHeader("At A Glance"));
            tabataglance.setClosable(false); // Set the tab to be closable
            StackPane tabContentataglance = new StackPane();
            tabataglance.setContent(tabContentataglance);
            tabPane.getTabs().add(tabataglance);
            
            
            Tab tabSetting = new Tab();
            tabSetting.setGraphic(createTabHeader("Setting"));
            tabSetting.setClosable(false); // Set the tab to be closable
            StackPane tabContentSetting = new StackPane();
            tabSetting.setContent(tabContentSetting);
            tabPane.getTabs().add(tabSetting);
            
            Tab tabAsset = new Tab();
            tabAsset.setGraphic(createTabHeader("Asset"));
            tabAsset.setClosable(false); // Set the tab to be closable
            StackPane tabContentAsset = new StackPane();
            tabAsset.setContent(tabContentAsset);
            tabPane.getTabs().add(tabAsset);
            
            Tab tabProfile = new Tab();
            tabProfile.setGraphic(createTabHeader("Profile"));
            tabProfile.setClosable(false); // Set the tab to be closable
            StackPane tabContentProfile = new StackPane();
            tabProfile.setContent(tabContentProfile);
            tabPane.getTabs().add(tabProfile);
            
            tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
                // Handle tab change event
                int selectedIndex = tabPane.getSelectionModel().getSelectedIndex();
                employeeList = AddEmployeeController.retrieveData(repositoryManager);
            	 dataList=null;
                usersettingdata=null;
                try {
                usersettingdata=new userLocalSettingController().retrieveData(repositoryManager);
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
              globalSettingdata=null;
              localSettingdata=null;

                try {
                    globalSettingdata= GlobalSettingController.retrieveData(repositoryManager);
                    localSettingdata=new userLocalSettingController().retrieveData(repositoryManager);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                // Update the content of the selected tab based on the index
                switch (selectedIndex) {
                    case 0:
                        // Handle the content update for the first tab
                        updatePanel(tabContentsummry,new SummaryDataView().createTab(repositoryManager,employeeList,dataList,globalSettingdata,localSettingdata));
                        break;
                     case 1:
                    	
                     	//updatePanel(panel2,new AddEmployeeView().createTab(employeeList));
                        // Handle the content update for the second tab
                         updatePanel(tabContentataglance,new AtAGlanceDataView().createTab(repositoryManager,employeeList,dataList,globalSettingdata,localSettingdata));
                        break;
                        
                      case 2:
                    	
						try {

							updatePanel(tabContentSetting,new SettingView().createTab(repositoryManager,usersettingdata,employeeList));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                        // Handle the content update for the second tab
                        break;

                    case 3:
                        try {
                            updatePanel(tabContentAsset,new AssetView().createTab(repositoryManager,employeeList));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 4:

                        break;
                        
                        
                   
                }
            });

	        // Set the TabPane side to LEFT for vertical tabs
	        tabPane.setSide(javafx.geometry.Side.LEFT);

	        // Add the TabPane to the center of the BorderPane
	        borderPane.setCenter(tabPane);
           borderPane.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());


	        return borderPane;
	    }
    static private void updatePanel(StackPane panel, BorderPane nodeToAdd) {
    	if (!panel.getChildren().isEmpty()) {
            // Check if any nodes exist, then clear
            panel.getChildren().clear();
        }

        panel.getChildren().add(nodeToAdd);
    }
    private HBox createTabHeader(String leftText) {
        Label leftLabel = new Label(leftText);
       

        HBox hbox = new HBox(leftLabel);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
   
	 
}
