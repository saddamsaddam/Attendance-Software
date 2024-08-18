package org.example.spring.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import org.example.spring.controller.AddDataController;
import org.example.spring.model.EmployeeData;
import org.example.spring.model.EmployeeInsertedData;
import org.example.spring.model.UserGlobalSetting;
import org.example.spring.model.UserLocalSetting;
import org.example.spring.repository.RepositoryManager;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.List;

public class SummaryDataView {

	LocalDate databaseDate;
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
    TableView<AttendanceData> tableView;List<EmployeeData> employeeList;
    int length=0;  Label labelh3date,starttime; Label labelh2date; Label labelh1date,labelh1dateday,labelh2dateday,labelh3dateday;
    String selectedparson,selectedparsonglance;
    String selectedmonth,selectedyear,selectedmonthglance,selectedyearglance,selectedmonthday,selectedyearday;
    List<EmployeeInsertedData> dataList;
	List<UserGlobalSetting> globalSettingdata;List<UserLocalSetting> localSettingdata;

    BorderPane createTab(RepositoryManager repositoryManager1 , List<EmployeeData> employeeList1, List<EmployeeInsertedData> dataList1, List<UserGlobalSetting> globalSettingdata1, List<UserLocalSetting> localSettingdata1) {
		localSettingdata=localSettingdata1;
		globalSettingdata=globalSettingdata1;
		repositoryManager= repositoryManager1;
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
	        centerStackPane.setPrefWidth(0.9 *screenSize.getWidth()-100); // 40% of the total width
	        //centerStackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
	       // centerStackPane.setStyle("-fx-border-color: black; -fx-border-width: 2;");
	       
	       
	        VBox verticaldata=new VBox();
	       

	        // Layout
	        HBox root = new HBox();
	        DatePicker datePicker = new DatePicker();

	        datePicker.setMaxWidth(150);
	         LocalDate currentDate = LocalDate.now();
	         LocalDate initialDate = LocalDate.of(currentDate.getYear(),currentDate.getMonth(), 1);
	         selectedmonth=Integer.toString(currentDate.getMonthValue());
	         selectedmonthglance=selectedmonth;
	         selectedyear=Integer.toString(currentDate.getYear());
	         selectedyearglance=selectedyear;
	         datePicker.setValue(initialDate);
	        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
	            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM,yyyy");

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
	                    tableView.getItems().clear();
	    	            settabledata();
	                    
	                    labelh2date.setText("Month: "+selectedMonth);
	                    labelh3date.setText("Month: "+selectedYear);
	                }
	            }
	        });
	        root.setPadding(new Insets(5,0,10,0));
	        root.getChildren().addAll( datePicker);
	        verticaldata.getChildren().add(root);
	        
	      
	    

	        HBox header = new HBox(); // HBox is a horizontal box
	        header.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");
	     // Center the label within the HBox
	        header.setAlignment(Pos.CENTER);
	        labelh1date = new Label("Monthly Attendance Sheet");
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
	        labelh2date = new Label("Month: "+currentDate1.getMonth());
	        GridPane.setConstraints(labelh2date, 0, 0); // (column, row)

	       labelh3date = new Label("    Year: "+currentDate1.getYear());
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
	        HBox emplyeebox = new HBox(); // HBox is a horizontal box
	        ComboBox<String> comboBox = new ComboBox<>();
	        comboBox.setMinWidth(150);
	        emplyeebox.setPadding(new Insets(0,0,10,0));

	        // Add items to the ComboBox
	        ObservableList<String> items = FXCollections.observableArrayList(
	        );
	        
	        employeeList.forEach(e->{
	        	
	        	items.add(e.getName());
	        });
	        
	        comboBox.setItems(items);

	        // Set a default value (optional)
	        if(employeeList.size()>0)
	        {
	        	comboBox.setValue(employeeList.get(0).getName());
		        selectedparson=employeeList.get(0).getName();
		        selectedparsonglance=employeeList.get(0).getName();
	        }
	        

	        // Handle selection change
	        comboBox.setOnAction(event -> {
	            selectedparson = comboBox.getValue();
	            
	            tableView.getItems().clear();
	            settabledata();
	        });
	        emplyeebox.getChildren().add(comboBox);
	        verticaldata.getChildren().add(emplyeebox);
	        
	         tableView = new TableView<>();

	        // Create columns
	        TableColumn<AttendanceData, String> dateColumn = createColumn("Date", "date", 60);
	        TableColumn<AttendanceData, String> entryTimeColumn = createColumn("Entry Time", "entryTime", 100);
	        TableColumn<AttendanceData, String> lateDurationColumn = createColumn("Late Duration", "lateDuration", 100);
	        TableColumn<AttendanceData, String> entryCommentColumn = createColumn("Entry Comment", "entryComment", 140);
	        TableColumn<AttendanceData, String> exitTimeColumn = createColumn("Exit Time", "exitTime", 100);
	        TableColumn<AttendanceData, String> timeAfterExitColumn = createColumn("Time After Exit", "timeAfterExit", 140);
	        TableColumn<AttendanceData, String> exitCommentColumn = createColumn("Exit Comment", "exitComment", 140);
	       
	        TableColumn<AttendanceData, String> totalTimeInDayColumn = createColumn("Total Time \n in a Day", "totalTimeInDay", 100);
	        TableColumn<AttendanceData, String> dayCommentColumn = createColumn("Day Comment", "dayComment", 100);
	        TableColumn<AttendanceData, String> commentColumn = createColumn("Comment", "comment", 90);

	     // Add columns to TableView
	        tableView.getColumns().addAll(dateColumn, entryTimeColumn, lateDurationColumn, entryCommentColumn,
	                exitTimeColumn, timeAfterExitColumn, exitCommentColumn, totalTimeInDayColumn, dayCommentColumn,
	                commentColumn);

	        settabledata();
		ScrollPane scrollTable = new ScrollPane(tableView);
		scrollTable.setFitToWidth(true); // Ensure TableView fits width of ScrollPane
		scrollTable.setFitToHeight(true); // Ensure TableView fits height of ScrollPane

        // Add the ScrollPane to your vertical layout
		verticaldata.getChildren().add(scrollTable);

		   
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
    
   public String[] getFirstTwoCharacters(String name,String month,String year) {
       String[] result = new String[monthlength];
          int k=0;
       for (int i = 0; i < dataList.size(); i++) {
    	   if(name.equals(dataList.get(i).getName())&& month.equals(dataList.get(i).getMonth())&&dataList.get(i).getYear().equals(year))
    	   {
    		   // Get the day of the week for each LocalDateTime
               DayOfWeek dayOfWeek = dataList.get(i).getEntryTime().getDayOfWeek();

               // Get the first two characters of the day name
               String firstTwoCharacters = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH).substring(0, 2);

               // Store the result in the array
               result[k] = firstTwoCharacters;
               k++;
    	   }
          
       }

       return result;
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

    public void setglanceclear() {
    	// regulartime,extratime,intime,latetime,totallatetime,ok,earlytime,totalextratime;
    	 officeday.setText("");
    	 presentday.setText("");
    	 avgtime.setText("");
    	 leaveday.setText("");
    	 absentday.setText("");
    	 holyday.setText("");
    	 shorttime.setText("");
    	 regulartime.setText("");
    	 extratime.setText("");
    	 intime.setText("");
    	 latetime.setText("");
    	 totallatetime.setText("");
    	 ok.setText("");
    	 earlytime.setText("");
    	 totalextratime.setText("");
    
    }

	public void settabledata(){


		 dataList.forEach(e->{
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			 databaseDate=null;
			 // Convert the string to LocalDate
			 try {
				 databaseDate = LocalDate.parse(e.getEntryDate(), dateFormatter);
				 //System.out.println("Converted LocalDate: " + databaseDate);
			 } catch (DateTimeParseException f) {
				 f.printStackTrace(); // Handle parsing exception
			 }
			    employeeList.forEach(g-> {
					LocalDate databaseDate1 = LocalDate.parse(g.getJoinDate(), dateFormatter);
					if ((e.getName().equals(g.getName()) && databaseDate1.isBefore(databaseDate)) || (e.getName().equals(g.getName()) && databaseDate1.isEqual(databaseDate))) {

						if(selectedparson.equals(e.getName())&&selectedmonth.equals(e.getMonth())&&selectedyear.equals(e.getYear()))
						{

							if(e.getStatus().equals("Absent")||e.getStatus().equals("Leave")||e.getStatus().equals("Holyday"))
							{
								String date=Integer.toString(e.getEntryTime().getDayOfMonth());
								tableView.getItems().add(new AttendanceData(date," "," ","","","","","","",e.getStatus()));//
							}
							else {


								String date=Integer.toString(e.getEntryTime().getDayOfMonth());
								// Define the desired format
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

								// Format the LocalDateTime using the formatter
								String entrytime= e.getEntryTime().format(formatter);




								LocalTime currentTime = e.getEntryTime().toLocalTime();

								// Define the threshold time (09:15 AM)
								LocalTime lateThreshold =LocalTime.of(returnSettingStartHour(e.getEmployeeId(),e.getName(),e.getEntryDate()), returnGlobalSettingLateMinute(e.getEntryDate()));

								// Compare with the threshold and print the result
								String lateduration;
								String entrycomment;

								if (currentTime.isAfter(lateThreshold)) {
									LocalTime lateThreshold1 =LocalTime.of(returnSettingStartHour(e.getEmployeeId(),e.getName(),e.getEntryDate()),returnSettingStartMinute(e.getEmployeeId(),e.getName(),e.getEntryDate()));
									Duration duration = Duration.between(lateThreshold1,currentTime);
									long hours = duration.toHoursPart();
									long minutes = duration.toMinutesPart();
									lateduration=hours+":"+ minutes;
									entrycomment="Late";
								} else {
									// if 9:00 >9:00 then count it.
									LocalTime lateThreshold1 =LocalTime.of(returnSettingStartHour(e.getEmployeeId(),e.getName(),e.getEntryDate()),returnSettingStartMinute(e.getEmployeeId(),e.getName(),e.getEntryDate()));
									if (currentTime.isAfter(lateThreshold1)) {
										Duration duration = Duration.between(lateThreshold1,currentTime);

										long hours = duration.toHoursPart();
										long minutes = duration.toMinutesPart();


										lateduration=hours+":"+ minutes;
									}
									else {
										lateduration="00:00";
									}

									entrycomment="In Time";
								}


								String exittime= e.getExitTime().format(formatter);


								currentTime = e.getExitTime().toLocalTime();

								String[] data=subtractHourMinute(returnSettingEndHour(e.getEmployeeId(),e.getName(),e.getEntryDate()),returnGlobalSettingEarlyMinute(e.getEntryDate()));
								LocalTime  overtimeThreshold = LocalTime.of(Integer.parseInt(data[0]),Integer.parseInt(data[1]) );

								// Compare with the threshold and print the result
								String overtimeduration;
								String exitcomment;
								System.out.println(overtimeThreshold+" "+currentTime);
								if (currentTime.isBefore(overtimeThreshold)) {
									LocalTime  overtimeThreshold1 = LocalTime.of(returnSettingEndHour(e.getEmployeeId(),e.getName(),e.getEntryDate()), returnSettingEndMinute(e.getEmployeeId(),e.getName(),e.getEntryDate()));
									Duration duration = Duration.between(currentTime,overtimeThreshold1);

									long hours = duration.toHours();
									long minutes = duration.minusHours(hours).toMinutes();

									overtimeduration="-"+hours+":"+ minutes;
									exitcomment="Early";

								} else {
									LocalTime  overtimeThreshold1 = LocalTime.of(returnSettingEndHour(e.getEmployeeId(),e.getName(),e.getEntryDate()), returnSettingEndMinute(e.getEmployeeId(),e.getName(),e.getEntryDate()));
									Duration duration = Duration.between(overtimeThreshold1,currentTime);

									long hours = duration.toHours();
									long minutes = duration.minusHours(hours).toMinutes();

									overtimeduration=hours+":"+ minutes;

									exitcomment="Ok";
								}



								Duration duration = Duration.between(e.getEntryTime(),e.getExitTime());

								long hours = duration.toHoursPart();
								long minutes = duration.toMinutesPart();

								String dayduration=hours+":"+minutes;
								int totalHours=returnSettingTotalHour(e.getEmployeeId(),e.getName(),e.getEntryDate());
								String daycomment= checkTimeDifference(duration,totalHours);
								String outtim=e.getOuttime();

								String status=e.getStatus();

								tableView.getItems().add(new AttendanceData(date,entrytime,lateduration,entrycomment,exittime,overtimeduration,exitcomment,dayduration,daycomment,status));//

							}
						}

					}
				});


	        	});
		 // sorting table data
		tableView.getItems().sort(Comparator.comparing(AttendanceData::getDate));
	 }

	public static String[] subtractHourMinute(int hour, int minutesToSubtract) {
		// Convert everything to minutes for easier calculation
		int totalMinutes = hour * 60 ;
		totalMinutes -= minutesToSubtract;

		// Handle negative result
		if (totalMinutes < 0) {
			totalMinutes += 24 * 60; // Assuming a 24-hour clock
		}

		// Calculate resulting hour and minute
		int resultingHour = totalMinutes / 60;
		int resultingMinute = totalMinutes % 60;

		// Format the result
		String[] result = new String[2];
		result[0] = String.valueOf(resultingHour);
		result[1] = String.valueOf(resultingMinute);
		return result;
	}
	 public static String checkTimeDifference(Duration duration , int totalHours) {
	       
	        long hours = duration.toHoursPart();
	        long minutes = duration.toMinutesPart();

	        String result;
	        if (hours < totalHours ) {
	            result = "Short time";
	        } else if (hours > totalHours|| (hours == totalHours && minutes > 0)) {
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
	   

	     public class AttendanceData {

	         private final SimpleStringProperty date;
	         private final SimpleStringProperty entryTime;
	         private final SimpleStringProperty lateDuration;
	         private final SimpleStringProperty entryComment;
	         private final SimpleStringProperty exitTime;
	         private final SimpleStringProperty timeAfterExit;
	         private final SimpleStringProperty exitComment;
	        
	         private final SimpleStringProperty totalTimeInDay;
	         private final SimpleStringProperty dayComment;
	         private final SimpleStringProperty comment;

	         public AttendanceData(String date, String entryTime, String lateDuration, String entryComment,
	                               String exitTime, String timeAfterExit, String exitComment,
	                               String totalTimeInDay, String dayComment, String comment) {
	             this.date = new SimpleStringProperty(date);
	             this.entryTime = new SimpleStringProperty(entryTime);
	             this.lateDuration = new SimpleStringProperty(lateDuration);
	             this.entryComment = new SimpleStringProperty(entryComment);
	             this.exitTime = new SimpleStringProperty(exitTime);
	             this.timeAfterExit = new SimpleStringProperty(timeAfterExit);
	             this.exitComment = new SimpleStringProperty(exitComment);
	             
	             this.totalTimeInDay = new SimpleStringProperty(totalTimeInDay);
	             this.dayComment = new SimpleStringProperty(dayComment);
	             this.comment = new SimpleStringProperty(comment);
	         }

	         // Generate getters for all properties
	         public String getDate() {
	             return date.get();
	         }

	         public String getEntryTime() {
	             return entryTime.get();
	         }

	         public String getLateDuration() {
	             return lateDuration.get();
	         }

	         public String getEntryComment() {
	             return entryComment.get();
	         }

	         public String getExitTime() {
	             return exitTime.get();
	         }

	         public String getTimeAfterExit() {
	             return timeAfterExit.get();
	         }

	         public String getExitComment() {
	             return exitComment.get();
	         }

	         

	         public String getTotalTimeInDay() {
	             return totalTimeInDay.get();
	         }

	         public String getDayComment() {
	             return dayComment.get();
	         }

	         public String getComment() {
	             return comment.get();
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
