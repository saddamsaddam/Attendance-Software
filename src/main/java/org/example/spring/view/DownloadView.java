package org.example.spring.view;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.*;

import java.awt.Dimension;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.example.spring.controller.*;
import org.example.spring.model.EmployeeData;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import javafx.beans.value.ChangeListener;
import org.example.spring.model.EmployeeInsertedData;
import org.example.spring.model.UserGlobalSetting;
import org.example.spring.model.UserLocalSetting;

public class DownloadView {

	LocalDate databaseDate;
	long timeInSecond=0, totalExtraTime=0,timeInSecondOfOutTime=0;
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
    List<EmployeeInsertedData> dataList;
	List<UserGlobalSetting> globalSettingdata; List<UserLocalSetting> localSettingdata;
	LocalDate startDate,endDate;
    BorderPane createTab(List<UserGlobalSetting> globalSettingdata1, List<UserLocalSetting> localSettingdata1, List<EmployeeData> employeeList1, List<EmployeeInsertedData> dataList1) {
		localSettingdata=localSettingdata1;
		globalSettingdata=globalSettingdata1;
		 dataList=dataList1;employeeList=employeeList1;
		 length=employeeList.size();
		 datePicker=new  DatePicker[length];
		 startreason=new TextField[length];
		 endreason=new TextField[length];
		 statusbox = new ComboBox[length];
		 
		 starthour = new Spinner[length];
		 startminite = new Spinner[length];
		 comboBoxstart = new ComboBox[length];
		 endhour = new Spinner[length];
		 endminite = new Spinner[length];
		 comboBoxend = new ComboBox[length];
		Dimension screenSize = new Dimension(1300,650);
	        // Create labels for different panes
	        Label label1 = new Label("");
	        Label label2 = new Label("");

	        // Create nested StackPane for the left section
	        StackPane leftStackPane = new StackPane();
	        leftStackPane.setPrefWidth(0.1 * screenSize.getWidth()-70); // 30% of the total width
	        leftStackPane.getChildren().add(label2);

	        // Create nested StackPane for the center section
	        StackPane centerStackPane = new StackPane();
	        centerStackPane.setPrefWidth(0.9 *screenSize.getWidth()-70); // 40% of the total width
	        //centerStackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
	       // centerStackPane.setStyle("-fx-border-color: black; -fx-border-width: 2;");
	       
	       
	        VBox verticaldata=new VBox();
	       

	        // Layout
	        HBox root = new HBox(30);
	        DatePicker datePicker = new DatePicker();
	        datePicker.setMaxWidth(150);
	        LocalDate currentDate = LocalDate.now();
	        LocalDate initialDate = LocalDate.of(currentDate.getYear(),currentDate.getMonth(), 1);
		      startDate=initialDate;
	         selectedmonth=Integer.toString(currentDate.getMonthValue());
	         selectedmonthglance=selectedmonth;
	         selectedyear=Integer.toString(currentDate.getYear());
	         selectedyearglance=selectedyear;
	        datePicker.setValue(initialDate);
	        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
	            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

	            @Override
	            public String toString(LocalDate date) {
	                if (date != null) {
	                    return dateFormatter.format(date);
	                } else {
	                    return "";
	                }
	            }

	            @Override
	            public LocalDate fromString(String string) {
	                if (string != null && !string.isEmpty()) {
	                    try {
	                        TemporalAccessor temporalAccessor = dateFormatter.parse(string);
	                        int year = temporalAccessor.get(ChronoField.YEAR);
	                        int month = temporalAccessor.get(ChronoField.MONTH_OF_YEAR);
	                        return LocalDate.of(year, month, 1); // Assuming day is always the 1st
	                    } catch (DateTimeParseException e) {
	                        // Handle parsing exception, e.g., return null or throw an error
	                        e.printStackTrace(); // You may want to log the exception
	                        return null;
	                    }
	                } else {
	                    return null;
	                }
	            }
	        };

	        datePicker.setConverter(converter);
			DatePicker datePickerOneMonthLater = new DatePicker();
			datePickerOneMonthLater.setMaxWidth(150);
			LocalDate oneMonthLater = initialDate.plusMonths(1);
			endDate=oneMonthLater;
			datePickerOneMonthLater.setValue(oneMonthLater);
			datePickerOneMonthLater.setConverter(converter);


	        
	     // Add a listener to capture the selected month and year
	        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
	            @Override
	            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
	                if (newValue != null) {
	                    int selectedYear = newValue.getYear();
	                    int selectedMonthValue = newValue.getMonthValue();
	                    String selectedMonth = newValue.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());

	                    selectedmonth=Integer.toString(selectedMonthValue);
	                    selectedyear=Integer.toString(selectedYear);

						// Compare the two dates
						if (newValue.isBefore(datePickerOneMonthLater.getValue())) {
							startDate=newValue;
							labelh2date.setText("Start Date: "+datePicker.getValue().toString());
							tableView.getItems().clear();
							settabledata();
						} else if (newValue.isAfter(datePickerOneMonthLater.getValue())) {
							alert("Sorry, First Date is after than the second Date.");
							datePicker.setValue(startDate);
						} else {
							alert("Sorry,The dates are the same.");
							datePicker.setValue(startDate);
						}
	                }
	            }
	        });


		datePickerOneMonthLater.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				if (newValue != null) {

					int selectedYear = newValue.getYear();
					int selectedMonthValue = newValue.getMonthValue();
					int selectedDay = newValue.getDayOfMonth();
					String selectedMonth = newValue.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());

					// You can add any additional logic here if needed for the second date picker

					String selectedDayString = Integer.toString(selectedDay);
					System.out.println("Selected Date: " + selectedMonth + " " + selectedDayString + ", " + selectedYear);

					// Compare the two dates
					if (newValue.isBefore(datePicker.getValue())) {
						alert("Sorry,  The second Date is before than the first Date.");
						datePickerOneMonthLater.setValue(endDate);

					} else if (newValue.isAfter(datePicker.getValue())) {
						endDate=newValue;
						labelh3date.setText("   End Date: "+datePickerOneMonthLater.getValue().toString());
						System.out.println("The date from the second DatePicker is after the date from the first DatePicker.");
						tableView.getItems().clear();
						settabledata();
					} else {
						alert("Sorry,The dates are the same.");
						datePickerOneMonthLater.setValue(endDate);
					}

				}
			}
		});

		root.setPadding(new Insets(5, 0, 10, 0));
		root.getChildren().addAll(datePicker, datePickerOneMonthLater);
	        verticaldata.getChildren().add(root);
	        
	      
	    

	        HBox header = new HBox(); // HBox is a horizontal box
	        header.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");
	     // Center the label within the HBox
	        header.setAlignment(Pos.CENTER);
	        labelh1date = new Label("Monthly AtA Glance");
	       // header.setMinWidth(400);
	        header.getChildren().addAll(
	        		labelh1date  
		        );
		        
		   verticaldata.getChildren().add(header);
		   
	        GridPane gridPane = new GridPane();
	        VBox.setMargin(gridPane, new Insets(0, 0, 20, 0)); // Top and bottom margin of 5 pixels
	        gridPane.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px; -fx-padding: 10px;");

	        gridPane.setHgap(0); // Horizontal gap between columns
	        gridPane.setVgap(10); // Vertical gap between rows
	        gridPane.setPadding(new Insets(10)); // Padding around the whole grid
	        

	        // Adding nodes to the GridPane
	        LocalDate currentDate1 = LocalDate.now();
	        labelh2date = new Label("Start Date: "+datePicker.getValue());
	        GridPane.setConstraints(labelh2date, 0, 0); // (column, row)

	       labelh3date = new Label("    End Date: "+datePickerOneMonthLater.getValue());
	        GridPane.setConstraints(labelh3date, 1, 0);

	     // Set ColumnConstraints to make the first and third columns take up equal width
	        ColumnConstraints column1 = new ColumnConstraints();
	        column1.setPercentWidth(50);
	        
	        ColumnConstraints column3 = new ColumnConstraints();
	        column3.setPercentWidth(50);

	        gridPane.getColumnConstraints().addAll(column1, column3);

	        // Create a rectangle with a background color to act as a separator
	        Rectangle separator = new Rectangle(1, 20, Color.BLACK);
	        GridPane.setConstraints(separator, 1, 0);
	        
	        gridPane.getChildren().addAll(labelh2date, separator, labelh3date);
	        
	        verticaldata.getChildren().add(gridPane);
	       // add export button here
	       // verticaldata.getChildren().add(emplyeebox);
	        
	        Button exportButton = new Button("Export Data");
	        exportButton.setOnAction(event -> exportDataToFile());

	        // Add the export button to your layout (you can place it where it makes sense in your UI)
	        verticaldata.getChildren().add(exportButton);
	        
	        
	        tableView = new TableView<>();
	     
	         // Create table columns
	         TableColumn<AttendanceData, String> serialColumn = createColumn("Serial", "serial",100);
	         TableColumn<AttendanceData, String> nameColumn = createColumn("Name", "name",100);
	         TableColumn<AttendanceData, String> officeDayColumn = createColumn("Office Day", "officeDay",100);
	         TableColumn<AttendanceData, String> totalPresentColumn = createColumn("Total Present", "totalPresent",100);
	         TableColumn<AttendanceData, String> avgTimeColumn = createColumn("Avg Time", "avgTime",100);
	         TableColumn<AttendanceData, String> leaveColumn = createColumn("Leave", "leave",100);
	         TableColumn<AttendanceData, String> absentColumn = createColumn("Absent", "absent",100);
	         TableColumn<AttendanceData, String> holydayColumn = createColumn("Holyday", "holyday",100);
	         TableColumn<AttendanceData, String> shortTimeColumn = createColumn("Short Time", "shortTime",100);
	         TableColumn<AttendanceData, String> requiredTimeColumn = createColumn("Maintain Office Duration", "requiredTime",100);
	         TableColumn<AttendanceData, String> extraTimeColumn = createColumn("Extra Time", "extraTime",100);
	         TableColumn<AttendanceData, String> entryInTimeColumn = createColumn("Entry In Time", "entryInTime",100);
	         TableColumn<AttendanceData, String> entryLateColumn = createColumn("Entry Late", "entryLate",100);
	         TableColumn<AttendanceData, String> entryTotalLateColumn = createColumn("Entry Total Late", "entryTotalLate",100);
	         TableColumn<AttendanceData, String> exitOkColumn = createColumn("Exit Ok", "exitOk",100);
	         TableColumn<AttendanceData, String> exitEarlyColumn = createColumn("Exit Early", "exitEarly",100);
	         TableColumn<AttendanceData, String> totalExtraTimeColumn = createColumn("Total Extra Time", "totalExtraTime",100);
	         TableColumn<AttendanceData, String> officeOutTimeColumn = createColumn("Office Out Time", "officeOutTime",100);
	         TableColumn<AttendanceData, String> officeInTimeColumn = createColumn("Office In Time", "officeInTime",100);
	         TableColumn<AttendanceData, String> totalTimeColumn = createColumn("Total Time", "totalTime",100);

	       
	       
	         tableView.getColumns().addAll(serialColumn, nameColumn, officeDayColumn, totalPresentColumn, avgTimeColumn,
	                 leaveColumn, absentColumn, holydayColumn, shortTimeColumn, requiredTimeColumn, extraTimeColumn,
	                 entryInTimeColumn, entryLateColumn, entryTotalLateColumn, exitOkColumn, exitEarlyColumn,
	                 totalExtraTimeColumn, officeOutTimeColumn, officeInTimeColumn, totalTimeColumn);

	        
            
	        settabledata();
	       
	        
	        
		   
		   
		   verticaldata.setPadding(new Insets(5,10,100,10));
		   ScrollPane scrollPane = new ScrollPane(tableView);
		   
		   verticaldata.getChildren().add(scrollPane);
		   
            centerStackPane.getChildren().add(verticaldata);
            
            
	        // Create nested StackPane for the right section
	        StackPane rightStackPane = new StackPane();
	        rightStackPane.setPrefWidth(0.0 * screenSize.getWidth()-70); // 30% of the total width
	        rightStackPane.getChildren().add(new Label(""));
	       

	        // Create an HBox to hold the left, center, and right sections
	        HBox hbox = new HBox();
	        hbox.getChildren().addAll(leftStackPane, centerStackPane, rightStackPane);
	        

	        // Create a BorderPane and set the HBox in the center
	        BorderPane borderPane = new BorderPane();
	     // Set padding using CSS
	        borderPane.setStyle("-fx-padding: 10 0 0 0;");
	        
	        
	        borderPane.setTop(new Label(""));
	        borderPane.setBottom(new Label(""));
	        borderPane.setCenter(hbox);


	        // Create a new tab with the specified title and content
	        //Tab tab = new Tab(tabTitle);


	        return borderPane;
	    }

	public void alert(String title){
		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);

			// Create a custom label with centered alignment
			Label contentLabel = new Label(title );
			contentLabel.setStyle("-fx-font-size:24px");
			contentLabel.setAlignment(Pos.CENTER);

			// Create a VBox to hold the label and center it
			VBox content = new VBox(contentLabel);
			content.setAlignment(Pos.CENTER);
			content.setPrefSize(900, 600); // Set preferred size of the VBox

			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.setContent(content); // Set the VBox as the content of the dialog pane
			dialogPane.setPrefWidth(800);  // Set preferred width of the dialog pane
			dialogPane.setPrefHeight(600); // Set preferred height of the dialog pane

			alert.showAndWait();

		});
	}
    private void exportDataToFile() {
        // You can customize this method to export data to a file format of your choice (Excel, CSV, etc.)
        // For simplicity, let's use a CSV format in this example

        StringBuilder csvContent = new StringBuilder();

     // Add header row
        csvContent.append("Employee ID,Name,Office Day,Total Present,Avg Time,Leave,Absent,Holiday,Short Time,Maintain Office Duration,Extra Time,Entry In Time,Entry Late,Entry Total Late,Exit Ok,Exit Early,Total Extra Time,Office Out Time,Office In Time,Total Time\n");

        // Add data rows
        for (AttendanceData rowData : tableView.getItems()) {
            csvContent.append(String.join(",",
                    rowData.getSerial(),
                    rowData.getName(),
                    rowData.getOfficeDay(),
                    rowData.getTotalPresent(),
                    rowData.getAvgTime(),
                    rowData.getLeave(),
                    rowData.getAbsent(),
                    rowData.getHolyday(),
                    rowData.getShortTime(),
                    rowData.getRequiredTime(),
                    rowData.getExtraTime(),
                    rowData.getEntryInTime(),
                    rowData.getEntryLate(),
                    rowData.getEntryTotalLate(),
                    rowData.getExitOk(),
                    rowData.getExitEarly(),
                    rowData.getTotalExtraTime(),
                    rowData.getOfficeOutTime(),
                    rowData.getOfficeInTime(),
                    rowData.getTotalTime()))
                    .append("\n");
        }

        // Show file chooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Data");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv"));
        java.io.File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(csvContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String officeday(String name, String month,String year)
    {
    	officedayc=0;
   	    dataList.forEach(e->{
   		 //office day
   		
	        	if(name.equals(e.getName())&&month.equals(e.getMonth())&&year.equals(e.getYear()) )
	        	{
	        		if(!"Holyday".equals(e.getStatus()))
	        		{
	        			officedayc++;
	        		}
	        	}
   	     });
   	    return (Integer.toString(officedayc));
    }
   public int datalength(String name,String month,String year)
   {
	   datalength=0;
	   for (int i = 0; i < dataList.size(); i++) {
       	if(name.equals(dataList.get(i).getName())&& month.equals(dataList.get(i).getMonth())&&dataList.get(i).getYear().equals(year))
       	{
       		
       		datalength++;
       		
       	}
           
       }
	   
	   return datalength;
   }
    
  
   
   public String[] getstatus(String name,String month,String year) {
       String[] result = new String[monthlength];
          int k=0;
       for (int i = 0; i < dataList.size(); i++) {
    	   if(name.equals(dataList.get(i).getName())&& month.equals(dataList.get(i).getMonth())&&dataList.get(i).getYear().equals(year))
    	   {
               // Store the result in the array
               result[k] = dataList.get(i).getStatus();
               k++;
    	   }
          
       }

       return result;
   }
   public String[] getday(String name,String month,String year) {
       String[] result = new String[monthlength];
          int k=0;
       for (int i = 0; i < dataList.size(); i++) {
    	   if(name.equals(dataList.get(i).getName())&& month.equals(dataList.get(i).getMonth())&&dataList.get(i).getYear().equals(year))
    	   {
               // Store the result in the array
               result[k] = Integer.toString(dataList.get(i).getEntryTime().getDayOfMonth());
               k++;
    	   }
          
       }

       return result;
   }

  
	public void settabledata(){
		
		 
    	
    	 if(dataList.size()>0)
    	 { 
    		 
    		 employeeList.forEach(f->{
				 totalExtraTime=0;
    			 officedayc=0;presentdayc=0;avgtimec=0;leavedayc=0;absentdayc=0;holydayc=0;shorttimec=0;regulartimec=0;extratimec=0;intimec=0;latetimec=0;totallatetimec=0;okc=0;earlytimec=0;totalextratimec=0;
    	    	 durationc=Duration.ZERO;totallatedurationc=Duration.ZERO;totalextradurationc=Duration.ZERO;outtimec=0;totaltimecc=Duration.ZERO;
    	    	 intotaltimec=Duration.ZERO;outtimec=0;totaltimecc=Duration.ZERO;
				 timeInSecond=0;
                  result=false;
				 timeInSecondOfOutTime=0;
    		     dataList.forEach(e->{
					 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					 databaseDate=null;
					 // Convert the string to LocalDate
					 try {
						 databaseDate = LocalDate.parse(e.getEntryDate(), dateFormatter);
						 //System.out.println("Converted LocalDate: " + databaseDate);
					 } catch (DateTimeParseException g) {
						 g.printStackTrace(); // Handle parsing exception
					 }
					 if (databaseDate != null) {
						 System.out.println(databaseDate+" "+startDate+" "+endDate);
						 if (((databaseDate.equals(startDate) || databaseDate.equals(endDate))&& f.getName().equals(e.getName()) )||
								 (databaseDate.isAfter(startDate) && databaseDate.isBefore(endDate)&& f.getName().equals(e.getName()))) {

							 LocalDate databaseDate1 = LocalDate.parse(f.getJoinDate(), dateFormatter);
							 if ((e.getName().equals(f.getName()) && databaseDate1.isBefore(databaseDate)) || (e.getName().equals(f.getName()) && databaseDate1.isEqual(databaseDate))) {
								 result=true;
								 if(!"Holyday".equals(e.getStatus()))
								 {
									 officedayc++;
								 }

								 if("Present".equals(e.getStatus()))
								 {
									 Duration durationBetweenEntryExit = Duration.between(e.getEntryTime(), e.getExitTime());
									 timeInSecond=timeInSecond+durationBetweenEntryExit.toHoursPart()*60L*60L+durationBetweenEntryExit.toMinutesPart()*60L;
									 durationc = durationc.plus(durationBetweenEntryExit);
									 System.out.println("Current total duration (seconds): " + durationc.getSeconds());
									 presentdayc++;
									 long hours = durationBetweenEntryExit.toHoursPart();
									 long minutes = durationBetweenEntryExit.toMinutesPart();

									 // total hours calculate
									 // need info:
									 int settingHours= returnSettingTotalHour(e.getEmployeeId(),e.getName(),e.getEntryDate());
									 System.out.println(settingHours);

									 if (hours < settingHours ) {

										 shorttimec++;
									 } else if (hours > settingHours || (hours == settingHours && minutes > 0)) {

										 extratimec++;
									 } else {
										 regulartimec++;

									 }


									 LocalTime lateThreshold = LocalTime.of(returnSettingStartHour(e.getEmployeeId(),e.getName(),e.getEntryDate()), (returnSettingStartMinute(e.getEmployeeId(),e.getName(),e.getEntryDate())+16));

									 if ( e.getEntryTime().toLocalTime().isBefore(lateThreshold)) {
										 intimec++;
									 }

									 lateThreshold = LocalTime.of(returnSettingStartHour(e.getEmployeeId(),e.getName(),e.getEntryDate()), returnGlobalSettingLateMinute(e.getEntryDate()));
									 // late count
									 if ( e.getEntryTime().toLocalTime().isAfter(lateThreshold)) {
										 latetimec++;

										 //late duration count
										 Duration duration = Duration.between(lateThreshold, e.getEntryTime().toLocalTime());
										 duration= addMinutesToDuration(duration , 15);
										 totallatedurationc=totallatedurationc.plus(duration);


									 }


									 LocalTime exitThreshold = LocalTime.of(returnSettingEndHour(e.getEmployeeId(),e.getName(),e.getEntryDate()), returnSettingEndMinute(e.getEmployeeId(),e.getName(),e.getEntryDate()));
									 if ( e.getExitTime().toLocalTime().isBefore(exitThreshold)) {
										 earlytimec++;
									 }



									 if ( e.getExitTime().toLocalTime().isAfter(exitThreshold)) {
										 // 15 minute extra time count
										 LocalTime exitThreshold1 = LocalTime.of(returnSettingEndHour(e.getEmployeeId(),e.getName(),e.getEntryDate()), 15+returnSettingEndMinute(e.getEmployeeId(),e.getName(),e.getEntryDate()));

										 if(e.getExitTime().toLocalTime().isAfter(exitThreshold1)){
											 Duration duration = Duration.between(exitThreshold, e.getExitTime().toLocalTime());
											 totalExtraTime=totalExtraTime+duration.toHoursPart()*60L*60L+duration.toMinutesPart()*60L;
											// totalextradurationc=totalextradurationc.plus(duration);
										 }


									 }

									 String regex = "(\\d+)\\.(\\d+)";

									 Pattern pattern = Pattern.compile(regex);
									 Matcher matcher = pattern.matcher(e.getOuttime());
									 if (matcher.matches()) {
										 String integerPart = matcher.group(1);
										 String fractionalPart = matcher.group(2);
										 timeInSecondOfOutTime=timeInSecondOfOutTime+Long.parseLong(integerPart)*60L*60L+Long.parseLong(fractionalPart)*60L;

										 System.out.println("Integer Part: " + integerPart);
										 System.out.println("Fractional Part: " + fractionalPart);
									 } else {
										 System.out.println("The input is not a valid decimal number.");
									 }


								 }

								 if("Leave".equals(e.getStatus()))
								 {


									 leavedayc++;
								 }

								 if("Absent".equals(e.getStatus()))
								 {


									 absentdayc++;
								 }

								 if("Holyday".equals(e.getStatus()))
								 {


									 holydayc++;
								 }


							 }
						   }
						 }



						 //office day
        		
    	        	if(f.getName().equals(e.getName())&&selectedmonth.equals(e.getMonth())&&selectedyear.equals(e.getYear()) )
    	        	{


    	        		
    	        		
    	        	}
        	 });
        	 
    		     if(result)
    		     {
    		    	// officeday.setText(Integer.toString(officedayc));
    		        	// presentday.setText(Integer.toString(presentdayc));
    		        	 intotaltimec=Duration.ofSeconds(timeInSecond);
    		        	 
    		        	// outtime.setText(Double.toString(outtimec));

    		             // Convert decimal hours to seconds

					    // Duration outT=Duration.ofSeconds(timeInSecondOfOutTime);

    		             // Create a Duration object
    		             Duration outtimeduration = Duration.ofSeconds(timeInSecondOfOutTime);
    		             
    		            
    		             totaltimecc= intotaltimec.plus(outtimeduration);
    		           
    		            // intotaltime.setText(intotaltimec.toHours()+":"+ intotaltimec.toMinutesPart());
    		        	// totaltime.setText(totaltimecc.toHours()+":"+ totaltimecc.toMinutesPart());

					 if(presentdayc!=0)
					 {
						 long totalSeconds =  timeInSecond;
						 long averageSeconds = totalSeconds / presentdayc;
						 System.out.println("Avg second " + totalSeconds);
						 durationc = Duration.ofSeconds(averageSeconds);
						 /// total extra time
						 totalextradurationc=Duration.ofSeconds(totalExtraTime);
					 }


					 // avgtime.setText( durationc.toHoursPart()+":"+ durationc.toMinutesPart());
    		        	 
    		        	// leaveday.setText(Integer.toString(leavedayc));
    		        	// absentday.setText(Integer.toString(absentdayc));
    		        	// holyday.setText(Integer.toString(holydayc));
    		        	// shorttime.setText(Integer.toString(shorttimec));
    		        	 //regulartime.setText(Integer.toString(regulartimec));
    		        	// extratime.setText(Integer.toString(extratimec));
    		        	// intime.setText(Integer.toString(intimec));
    		        	// latetime.setText(Integer.toString(latetimec));
    		        	// totallatetime.setText( totallatedurationc.toHoursPart()+":"+ totallatedurationc.toMinutesPart());
    		        	// ok.setText(Integer.toString(presentdayc));
    		        	// earlytime.setText(Integer.toString(earlytimec));
    		        	// totalextratime.setText( totalextradurationc.toHoursPart()+":"+ totalextradurationc.toMinutesPart()); 
    		        	 
    		        	 tableView.getItems().add(new AttendanceData(f.getIdNumber(),f.getName(),Integer.toString(officedayc),Integer.toString(presentdayc),
    		        			 durationc.toHoursPart()+":"+ durationc.toMinutesPart(),
    		        			 Integer.toString(leavedayc),
    		        			 Integer.toString(absentdayc),
    		        			 Integer.toString(holydayc),
    		        			 Integer.toString(shorttimec),
    		        			 Integer.toString(regulartimec),
    		        			 Integer.toString(extratimec),
    		        			 Integer.toString(intimec),
    		        			 Integer.toString(latetimec),
    		        			 totallatedurationc.toHoursPart()+":"+ totallatedurationc.toMinutesPart(),
    		        			 Integer.toString(presentdayc),
    		        			 Integer.toString(earlytimec),
    		        			 totalextradurationc.toHoursPart()+":"+ totalextradurationc.toMinutesPart(),
								 outtimeduration.toHours() + ":" + outtimeduration.toMinutesPart(),
    		        			 intotaltimec.toHours()+":"+ intotaltimec.toMinutesPart(),
    		        			 totaltimecc.toHours()+":"+ totaltimecc.toMinutesPart()));
    		     }
        	
        	 
			 
			 
		 });
    	 }
    	 
    	 
	 }
	 public static String checkTimeDifference(Duration duration) {
	       
	        long hours = duration.toHoursPart();
	        long minutes = duration.toMinutesPart();

	        String result;
	        if (hours < 8 ) {
	            result = "Short time";
	        } else if (hours > 8 || (hours == 8 && minutes > 0)) {
	            result = "Extra time";
	        } else {
	            result = "Required time";
	        }

	        return result;
	    }
	public int returnGlobalSettingLateMinute(String insertDataDate1)
	{
		int hour = 9; // Default hour value

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = globalSettingdata.size() - 1; i >= 0; i--) {

				try {
					Date insertDataDate = dateFormat.parse(insertDataDate1);
					Date startDate = dateFormat.parse(globalSettingdata.get(i).getFormattedBirthDate());
					Date endDate = dateFormat.parse(globalSettingdata.get(i).getFormattedDeathDate());

					// Check if insertDataDate is between startDate and endDate (inclusive)
					if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
							(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
						hour = Integer.parseInt(globalSettingdata.get(i).getLateMinute());
						break;
					}
				} catch (java.text.ParseException e) {
					// Handle parsing exception appropriately, e.g., logging
					e.printStackTrace();
				}
			}



		return hour;
	}
	public int returnGlobalSettingEarlyMinute(String insertDataDate1)
	{
		int hour = 9; // Default hour value

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = globalSettingdata.size() - 1; i >= 0; i--) {

			try {
				Date insertDataDate = dateFormat.parse(insertDataDate1);
				Date startDate = dateFormat.parse(globalSettingdata.get(i).getFormattedBirthDate());
				Date endDate = dateFormat.parse(globalSettingdata.get(i).getFormattedDeathDate());

				// Check if insertDataDate is between startDate and endDate (inclusive)
				if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
						(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
					hour = Integer.parseInt(globalSettingdata.get(i).getEarlyMinute());
					break;
				}
			} catch (java.text.ParseException e) {
				// Handle parsing exception appropriately, e.g., logging
				e.printStackTrace();
			}
		}



		return hour;
	}
	public int returnSettingStartMinute(String id, String name,String insertDataDate1)
	{
		int hour = 9; // Default hour value

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = localSettingdata.size() - 1; i >= 0; i--) {
			if (id.equals(localSettingdata.get(i).getEmployeeId()) && name.equals(localSettingdata.get(i).getName())) {
				try {
					Date insertDataDate = dateFormat.parse(insertDataDate1);
					Date startDate = dateFormat.parse(localSettingdata.get(i).getFormattedBirthDate());
					Date endDate = dateFormat.parse(localSettingdata.get(i).getFormattedDeathDate());

					// Check if insertDataDate is between startDate and endDate (inclusive)
					if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
							(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
						hour = Integer.parseInt(localSettingdata.get(i).getStartMinute());
						break;
					}
				} catch (java.text.ParseException e) {
					// Handle parsing exception appropriately, e.g., logging
					e.printStackTrace();
				}
			}
		}


		return hour;
	}
	public int returnSettingEndHour(String id, String name,String insertDataDate1)
	{
		int hour = 17; // Default hour value

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = localSettingdata.size() - 1; i >= 0; i--) {
			if (id.equals(localSettingdata.get(i).getEmployeeId()) && name.equals(localSettingdata.get(i).getName())) {
				try {
					Date insertDataDate = dateFormat.parse(insertDataDate1);
					Date startDate = dateFormat.parse(localSettingdata.get(i).getFormattedBirthDate());
					Date endDate = dateFormat.parse(localSettingdata.get(i).getFormattedDeathDate());

					// Check if insertDataDate is between startDate and endDate (inclusive)
					if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
							(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
						hour = Integer.parseInt(localSettingdata.get(i).getEndHours());
						break;
					}
				} catch (java.text.ParseException e) {
					// Handle parsing exception appropriately, e.g., logging
					e.printStackTrace();
				}
			}
		}


		return hour;
	}
	public int returnSettingEndMinute(String id, String name,String insertDataDate1)
	{
		int hour = 0; // Default hour value

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = localSettingdata.size() - 1; i >= 0; i--) {
			if (id.equals(localSettingdata.get(i).getEmployeeId()) && name.equals(localSettingdata.get(i).getName())) {
				try {
					Date insertDataDate = dateFormat.parse(insertDataDate1);
					Date startDate = dateFormat.parse(localSettingdata.get(i).getFormattedBirthDate());
					Date endDate = dateFormat.parse(localSettingdata.get(i).getFormattedDeathDate());

					// Check if insertDataDate is between startDate and endDate (inclusive)
					if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
							(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
						hour = Integer.parseInt(localSettingdata.get(i).getEndMinute());
						break;
					}
				} catch (java.text.ParseException e) {
					// Handle parsing exception appropriately, e.g., logging
					e.printStackTrace();
				}
			}
		}


		return hour;
	}
	public int returnSettingStartHour(String id, String name,String insertDataDate1)
	{
		int hour = 9; // Default hour value

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = localSettingdata.size() - 1; i >= 0; i--) {
			if (id.equals(localSettingdata.get(i).getEmployeeId()) && name.equals(localSettingdata.get(i).getName())) {
				try {
					Date insertDataDate = dateFormat.parse(insertDataDate1);
					Date startDate = dateFormat.parse(localSettingdata.get(i).getFormattedBirthDate());
					Date endDate = dateFormat.parse(localSettingdata.get(i).getFormattedDeathDate());

					// Check if insertDataDate is between startDate and endDate (inclusive)
					if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
							(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
						hour = Integer.parseInt(localSettingdata.get(i).getStartHours());
						break;
					}
				} catch (java.text.ParseException e) {
					// Handle parsing exception appropriately, e.g., logging
					e.printStackTrace();
				}
			}
		}


		return hour;
	}
		public int returnSettingTotalHour(String id, String name,String insertDataDate1)
		{
			int hour = 8; // Default hour value

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			for (int i = localSettingdata.size() - 1; i >= 0; i--) {
				if (id.equals(localSettingdata.get(i).getEmployeeId()) && name.equals(localSettingdata.get(i).getName())) {
					try {
						Date insertDataDate = dateFormat.parse(insertDataDate1);
						Date startDate = dateFormat.parse(localSettingdata.get(i).getFormattedBirthDate());
						Date endDate = dateFormat.parse(localSettingdata.get(i).getFormattedDeathDate());

						// Check if insertDataDate is between startDate and endDate (inclusive)
						if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
								(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
							hour = Integer.parseInt(localSettingdata.get(i).getTotalHours());
							break;
						}
					} catch (java.text.ParseException e) {
						// Handle parsing exception appropriately, e.g., logging
						e.printStackTrace();
					}
				}
			}


			return hour;
		}
	 public static Duration addMinutesToDuration(Duration originalDuration, long minutesToAdd) {
	        return originalDuration.plusMinutes(minutesToAdd);
	    }
	 private void updateDateTextField(LocalDate date) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        dateTextField.setText(date.format(formatter));
	    }

	    private void incrementDate() {
	        LocalDate currentDate = LocalDate.parse(dateTextField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        LocalDate nextDate = currentDate.plusDays(1);
	        updateDateTextField(nextDate);
	        
	        // Extract year and month separately
	        int year = nextDate.getYear();
	        Month month = nextDate.getMonth();

	        // Print year and month separately
	        System.out.println("Year: " + year);
	        System.out.println("Month: " + month);
	        
	        labelh1date.setText("Daily Attendance Sheet-"+year);
	        labelh2date.setText("Month : "+month);
	        labelh3date.setText("   Date: "+nextDate.toString());
	        for(int i=0;i<length;i++)
	        {
	        	 datePicker[i].setValue(nextDate); // Set initial date
	        }
	        
	    }
	    
	    public LocalDateTime createLocalDateTime(int year, int month, int dayOfMonth, int hour, int minute, String amPm) {
	        int adjustedHour = hour % 12;
	        if (amPm.equalsIgnoreCase("PM")) {
	            adjustedHour += 12;
	        }
	        return LocalDateTime.of(year, month, dayOfMonth, adjustedHour, minute);
	    }

	     private TableColumn<AttendanceData, String> createColumn(String columnName, String propertyName, double minWidth) {
	         TableColumn<AttendanceData, String> column = new TableColumn<>(columnName);
	         column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
	         column.setMinWidth(minWidth);
	         
	      // Set the cell factory to center-align the content
	         column.setCellFactory(col -> new TableCell<AttendanceData, String>() {
	             @Override
	             protected void updateItem(String item, boolean empty) {
	                 super.updateItem(item, empty);
	                 if (item == null || empty) {
	                     setText(null);
	                 } else {
	                     setText(item);
	                     setAlignment(Pos.CENTER);
	                 }
	             }
	         });
	        // column.setStyle("-fx-background-color: #a0a0a0; -fx-text-fill: white;");
	         return column;
	     }
	   

	  // Custom class for data representation
	     public  class AttendanceData {
	         private final SimpleStringProperty serial;
	         private final SimpleStringProperty name;
	         private final SimpleStringProperty officeDay;
	         private final SimpleStringProperty totalPresent;
	         private final SimpleStringProperty avgTime;
	         private final SimpleStringProperty leave;
	         private final SimpleStringProperty absent;
	         private final SimpleStringProperty holyday;
	         private final SimpleStringProperty shortTime;
	         private final SimpleStringProperty requiredTime;
	         private final SimpleStringProperty extraTime;
	         private final SimpleStringProperty entryInTime;
	         private final SimpleStringProperty entryLate;
	         private final SimpleStringProperty entryTotalLate;
	         private final SimpleStringProperty exitOk;
	         private final SimpleStringProperty exitEarly;
	         private final SimpleStringProperty totalExtraTime;
	         private final SimpleStringProperty officeOutTime;
	         private final SimpleStringProperty officeInTime;
	         private final SimpleStringProperty totalTime;

	         public AttendanceData(String serial, String name, String officeDay, String totalPresent, String avgTime,
	                               String leave, String absent, String holyday, String shortTime, String requiredTime,
	                               String extraTime, String entryInTime, String entryLate, String entryTotalLate,
	                               String exitOk, String exitEarly, String totalExtraTime, String officeOutTime,
	                               String officeInTime, String totalTime) {
	             this.serial = new SimpleStringProperty(serial);
	             this.name = new SimpleStringProperty(name);
	             this.officeDay = new SimpleStringProperty(officeDay);
	             this.totalPresent = new SimpleStringProperty(totalPresent);
	             this.avgTime = new SimpleStringProperty(avgTime);
	             this.leave = new SimpleStringProperty(leave);
	             this.absent = new SimpleStringProperty(absent);
	             this.holyday = new SimpleStringProperty(holyday);
	             this.shortTime = new SimpleStringProperty(shortTime);
	             this.requiredTime = new SimpleStringProperty(requiredTime);
	             this.extraTime = new SimpleStringProperty(extraTime);
	             this.entryInTime = new SimpleStringProperty(entryInTime);
	             this.entryLate = new SimpleStringProperty(entryLate);
	             this.entryTotalLate = new SimpleStringProperty(entryTotalLate);
	             this.exitOk = new SimpleStringProperty(exitOk);
	             this.exitEarly = new SimpleStringProperty(exitEarly);
	             this.totalExtraTime = new SimpleStringProperty(totalExtraTime);
	             this.officeOutTime = new SimpleStringProperty(officeOutTime);
	             this.officeInTime = new SimpleStringProperty(officeInTime);
	             this.totalTime = new SimpleStringProperty(totalTime);
	         }

	         public String getSerial() {
	             return serial.get();
	         }

	         public String getName() {
	             return name.get();
	         }

	         public String getOfficeDay() {
	             return officeDay.get();
	         }

	         public String getTotalPresent() {
	             return totalPresent.get();
	         }

	         public String getAvgTime() {
	             return avgTime.get();
	         }

	         public String getLeave() {
	             return leave.get();
	         }

	         public String getAbsent() {
	             return absent.get();
	         }

	         public String getHolyday() {
	             return holyday.get();
	         }

	         public String getShortTime() {
	             return shortTime.get();
	         }

	         public String getRequiredTime() {
	             return requiredTime.get();
	         }

	         public String getExtraTime() {
	             return extraTime.get();
	         }

	         public String getEntryInTime() {
	             return entryInTime.get();
	         }

	         public String getEntryLate() {
	             return entryLate.get();
	         }

	         public String getEntryTotalLate() {
	             return entryTotalLate.get();
	         }

	         public String getExitOk() {
	             return exitOk.get();
	         }

	         public String getExitEarly() {
	             return exitEarly.get();
	         }

	         public String getTotalExtraTime() {
	             return totalExtraTime.get();
	         }

	         public String getOfficeOutTime() {
	             return officeOutTime.get();
	         }

	         public String getOfficeInTime() {
	             return officeInTime.get();
	         }

	         public String getTotalTime() {
	             return totalTime.get();
	         }
	     }


	  public  class DataModel {
		   
		    // ... Repeat for columns 4 to 30
		    private String[] columns = new String[ monthlength+5]; // Assuming 31 columns, adjust as needed

		    public DataModel(ArrayList<String> rowdata ) {
		    	System.out.println( rowdata.size()+"  jjl  "+columns.length);
		    	
		        // Initialize columns with default values
		        for (int i = 0; i < columns.length; i++) {
		        	
		            columns[i] = rowdata.get(i);
		        }
		    }

		   

		    // ... Repeat for other columns

		    // Method to retrieve column property based on index
		    public StringProperty getColumnProperty(int index) {
		        if (index >= 0 && index < columns.length) {
		            return new SimpleStringProperty(columns[index]);
		        }
		        return null;
		    }
		}
}
