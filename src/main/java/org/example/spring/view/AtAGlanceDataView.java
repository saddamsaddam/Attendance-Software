package org.example.spring.view;
import javafx.application.Platform;
import javafx.scene.control.Button;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.example.spring.controller.AddDataController;
import org.example.spring.model.EmployeeData;
import org.example.spring.model.EmployeeInsertedData;
import org.example.spring.model.UserGlobalSetting;
import org.example.spring.model.UserLocalSetting;
import org.example.spring.repository.RepositoryManager;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.engine.jdbc.Size;

import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.*;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AtAGlanceDataView {
	long timeInSecond=0,totalExtraTime=0, timeInSecondOfOutTime=0;
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
	LocalDate databaseDate=null;
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
    int top=4,bottom=4;
	LocalDate startDate,endDate;
    BorderPane createTab(RepositoryManager repositoryManager1 , List<EmployeeData> employeeList1, List<EmployeeInsertedData> dataList1, List<UserGlobalSetting> globalSettingdata1, List<UserLocalSetting> localSettingdata1) {
		localSettingdata=localSettingdata1;
		globalSettingdata=globalSettingdata1;
		repositoryManager= repositoryManager1;
		 dataList=dataList1;employeeList=employeeList1;
		 length=employeeList.size();
		 datePicker=new  DatePicker[length];
		labelh2date=new Label();
		labelh3date=new Label();
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
	        centerStackPane.setPrefWidth(0.9 *screenSize.getWidth()); // 40% of the total width
	        //centerStackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
	       // centerStackPane.setStyle("-fx-border-color: black; -fx-border-width: 2;");
	       
	       
	        VBox verticaldata=new VBox();

		HBox root11 = new HBox(50);
		DatePicker datePicker11 = new DatePicker();
		datePicker11.setMaxWidth(150);
		LocalDate currentDate11 = LocalDate.now();

		LocalDate initialDate11 = LocalDate.of(currentDate11.getYear(), currentDate11.getMonth(), 1);
		startDate=initialDate11;
		selectedmonthglance = Integer.toString(currentDate11.getMonthValue());
		selectedyearglance = Integer.toString(currentDate11.getYear());
		datePicker11.setValue(initialDate11);

		StringConverter<LocalDate> converter11 = new StringConverter<LocalDate>() {
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
						int day = temporalAccessor.get(ChronoField.DAY_OF_MONTH);
						return LocalDate.of(year, month, day);
					} catch (DateTimeParseException e) {
						e.printStackTrace(); // You may want to log the exception
						return null;
					}
				} else {
					return null;
				}
			}
		};
		datePicker11.setConverter(converter11);
		// Create the second DatePicker, which is one month later than the initial DatePicker
		DatePicker datePickerOneMonthLater = new DatePicker();
		datePickerOneMonthLater.setMaxWidth(150);
		LocalDate oneMonthLater = initialDate11.plusMonths(1);
		endDate=oneMonthLater;
		datePickerOneMonthLater.setValue(oneMonthLater);
		datePickerOneMonthLater.setConverter(converter11);

// Add a listener to capture the selected year, month, and day
		datePicker11.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				if (newValue != null) {

					int selectedYear = newValue.getYear();
					int selectedMonthValue = newValue.getMonthValue();
					int selectedDay = newValue.getDayOfMonth();
					String selectedMonth = newValue.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());

					selectedmonthglance = Integer.toString(selectedMonthValue);
					selectedyearglance = Integer.toString(selectedYear);
					// Add a variable to capture the day if needed
					String selectedDayString = Integer.toString(selectedDay);



					labelh2date.setText("Month: " + selectedMonth + ", Day: " + selectedDayString);
					labelh3date.setText("Year: " + selectedYear);

					// Compare the two dates
					if (newValue.isBefore(datePickerOneMonthLater.getValue())) {
						startDate=newValue;

						setglanceclear();
						setglance();
					} else if (newValue.isAfter(datePickerOneMonthLater.getValue())) {
						alert("Sorry, First Date is after than the second Date.");
						datePicker11.setValue(startDate);
					} else {
						alert("Sorry,The dates are the same.");
						datePicker11.setValue(startDate);
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
					if (newValue.isBefore(datePicker11.getValue())) {
						alert("Sorry,  The second Date is before than the first Date.");
						datePickerOneMonthLater.setValue(endDate);

					} else if (newValue.isAfter(datePicker11.getValue())) {
						endDate=newValue;
						System.out.println("The date from the second DatePicker is after the date from the first DatePicker.");
						setglanceclear();
						setglance();
					} else {
						alert("Sorry,The dates are the same.");
						datePickerOneMonthLater.setValue(endDate);
					}

				}
			}
		});

		root11.setPadding(new Insets(5, 0, 10, 0));
		root11.getChildren().addAll(datePicker11, datePickerOneMonthLater);

		verticaldata.getChildren().add(root11);

		HBox emplyeebox11 = new HBox(20); // HBox is a horizontal box

		ComboBox<String> comboBox11 = new ComboBox<>();
		comboBox11.setMinWidth(150);
		emplyeebox11.setPadding(new Insets(10, 0, 10, 0));

		// Add items to the ComboBox
		ObservableList<String> items11 = FXCollections.observableArrayList();

		employeeList.forEach(e -> items11.add(e.getName()));

		comboBox11.setItems(items11);

		if (employeeList.size() > 0) {
			// Set a default value (optional)
			comboBox11.setValue(employeeList.get(0).getName());
			selectedparsonglance = employeeList.get(0).getName();
		}

		// Handle selection change
		comboBox11.setOnAction(event -> {
		selectedparsonglance = comboBox11.getValue();
			setglanceclear();
			setglance();
			// settabledata(); // have to work
		});

		HBox tt=new HBox();

		Button exportBTN = new Button("Export  Data");
		exportBTN.setOnAction(e->{
			exportGlanceData();
		});

		emplyeebox11.getChildren().addAll(comboBox11,exportBTN);
		verticaldata.getChildren().add(emplyeebox11);
		   
		   HBox glrow1 = new HBox(); // HBox is a horizontal box
		   glrow1.setMaxWidth(1130);
		   glrow1.setStyle("-fx-padding:5px");
		  // VBox glrow1colum1 = new VBox(); // HBox is a horizontal box
		   GridPane glrow1colum1 = new GridPane();
		  // glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width:0px 0px 0px 0px;");
		   HBox col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle(" -fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color:#CCCCCC; -fx-text-fill: white;");

		   Label ccc = new Label("Office Day");
		   ccc.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col1.getChildren().add(ccc);

		   glrow1colum1.add(col1,0,0);
		   
		   HBox col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(400);
		   col2.setStyle("-fx-border-color: black; -fx-border-width:0px 1px 1px 1px;");
		   officeday=new Label();
		 // ccc = new Label("Office Day");
		    officeday.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col2.getChildren().add(officeday);
		   
		   glrow1colum1.add(col2,0,1);
		   
		   GridPane glrow1colum2 = new GridPane();
		   //glrow1colum2.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #B6D7A8; -fx-text-fill: white;");
		   Label ccc1 = new Label("Total Present");
		   ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col1.getChildren().add(ccc1);

		   glrow1colum2.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(300);
		   col2.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px;");
		   presentday=new Label();
		   presentday.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col2.getChildren().add(presentday);
		   glrow1colum2.add(col2,0,1);
		   
		   GridPane glrow1colum3 = new GridPane();
		   //glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   HBox   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #4472C4; -fx-text-fill: white;");
		   Label totalTimeLabel = new Label("Avg Time");
		   totalTimeLabel.setStyle("-fx-font-size:14px; -fx-font-weight: bold;-fx-text-fill: white;");
		   col31.getChildren().add(totalTimeLabel);
		   glrow1colum3.add(col31,0,0);
		   
		   HBox  col32=new HBox();
		   col32.setPadding(new Insets(top,9,bottom,0));;
		   col32.setAlignment(Pos.CENTER);
		   col32.setMinWidth(302);
		   col32.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px;");
		   avgtime=new Label();
		   avgtime.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col32.getChildren().add(avgtime);
		  
		   glrow1colum3.add(col32,0,1);
		  
		  
		   glrow1colum1.setPrefWidth(400);
	        glrow1colum2.setPrefWidth(400);
	        glrow1colum3.setPrefWidth(400);
		   glrow1.getChildren().addAll(glrow1colum1, glrow1colum2, glrow1colum3);
		   
		   verticaldata.getChildren().add(glrow1);
		   
		   
		   HBox glrow2 = new HBox(); // HBox is a horizontal box
		   glrow2.setMaxWidth(1130);
		   glrow2.setStyle("-fx-padding:5px");
		  // VBox glrow1colum1 = new VBox(); // HBox is a horizontal box
		   glrow1colum1 = new GridPane();
		  // glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width:0px 0px 0px 0px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #FFE5A0; -fx-text-fill: white;");
			Label ccc2 = new Label("Leave");
			ccc2.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
			col1.getChildren().add(ccc2);

		   glrow1colum1.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(400);
		   col2.setStyle("-fx-border-color: black; -fx-border-width:0px 1px 1px 1px;");
		   leaveday=new Label();
		   leaveday.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col2.getChildren().add(leaveday);
		   glrow1colum1.add(col2,0,1);
		   
		   glrow1colum2 = new GridPane();
		   //glrow1colum2.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #B10202; -fx-text-fill: white;");
		   totalTimeLabel = new Label("Absent");
		   totalTimeLabel.setStyle("-fx-font-size:14px; -fx-font-weight: bold;-fx-text-fill: white;");
		   col1.getChildren().add(totalTimeLabel);
		   glrow1colum2.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(300);
		   col2.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px;");
		   absentday=new Label();
		   absentday.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col2.getChildren().add(absentday);
		   glrow1colum2.add(col2,0,1);
		   
		   glrow1colum3 = new GridPane();
		   //glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #3D3D3D; -fx-text-fill: white;");
		   totalTimeLabel = new Label("Holiday");
		   totalTimeLabel.setStyle("-fx-font-size:14px; -fx-font-weight: bold;-fx-text-fill: white;");
		   col31.getChildren().add(totalTimeLabel);
		   glrow1colum3.add(col31,0,0);
		   
		   col32=new HBox();
		   col32.setPadding(new Insets(top,9,bottom,0));;
		   col32.setAlignment(Pos.CENTER);
		   col32.setMinWidth(302);
		   col32.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px;");
		   holyday=new Label();
		   holyday.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col32.getChildren().add(holyday);
		   glrow1colum3.add(col32,0,1);
		  
		  
		   glrow1colum1.setPrefWidth(400);
	        glrow1colum2.setPrefWidth(400);
	        glrow1colum3.setPrefWidth(400);
		   glrow2.getChildren().addAll(glrow1colum1, glrow1colum2, glrow1colum3);
		   
		   verticaldata.getChildren().add(glrow2);
		   
		   HBox glrow3 = new HBox(); // HBox is a horizontal box
		   glrow3.setMaxWidth(1130);
		   glrow3.setStyle("-fx-padding:5px");
		  // VBox glrow1colum1 = new VBox(); // HBox is a horizontal box
		   glrow1colum1 = new GridPane();
		  // glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width:0px 0px 0px 0px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #F4CCCC; -fx-text-fill: white;");
			ccc1 = new Label("Short Time");
			ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
			col1.getChildren().add(ccc1);
		   glrow1colum1.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(400);
		   col2.setStyle("-fx-border-color: black; -fx-border-width:0px 1px 1px 1px;");
		   shorttime=new Label();
		   shorttime.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col2.getChildren().add(shorttime);
		   glrow1colum1.add(col2,0,1);
		   
		   glrow1colum2 = new GridPane();
		   //glrow1colum2.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #B7E1CD; -fx-text-fill: white;");
			ccc1 = new Label("Regular Time");
			ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
			col1.getChildren().add(ccc1);
		   glrow1colum2.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(300);
		   col2.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px;");
		   regulartime=new Label();
		   regulartime.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col2.getChildren().add( regulartime);
		   glrow1colum2.add(col2,0,1);
		   
		   glrow1colum3 = new GridPane();
		   //glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #38761D; -fx-text-fill: white;");
		   totalTimeLabel = new Label("Extra Time");
		   totalTimeLabel.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col31.getChildren().add(totalTimeLabel);
		   glrow1colum3.add(col31,0,0);
		   
		   col32=new HBox();
		   col32.setPadding(new Insets(top,9,bottom,0));;
		   col32.setAlignment(Pos.CENTER);
		   col32.setMinWidth(302);
		   col32.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px;");
		   extratime=new Label();
		   extratime.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col32.getChildren().add(extratime);
		   glrow1colum3.add(col32,0,1);
		  
		  
		   glrow1colum1.setPrefWidth(400);
	        glrow1colum2.setPrefWidth(400);
	        glrow1colum3.setPrefWidth(400);
		   glrow3.getChildren().addAll(glrow1colum1, glrow1colum2, glrow1colum3);
		   
		   verticaldata.getChildren().add(glrow3);
		   
		   HBox glrow4 = new HBox(); // HBox is a horizontal box
		   glrow4.setMaxWidth(1130);
		   glrow4.setStyle("-fx-padding:5px");
		  // VBox glrow1colum1 = new VBox(); // HBox is a horizontal box
		   glrow1colum1 = new GridPane();
		  // glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width:0px 0px 0px 0px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 1px ;-fx-background-color: #9FC5E8; -fx-text-fill: white;");
		ccc1 = new Label(" ");
		ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		col1.getChildren().add(ccc1);
		   glrow1colum1.add(col1,0,0);
		   
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #70AD47; -fx-text-fill: white;");
			ccc1 = new Label(" In Time");
			ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
			col1.getChildren().add(ccc1);
		   glrow1colum1.add(col1,0,1);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(400);
		   col2.setStyle("-fx-border-color: black; -fx-border-width:0px 1px 1px 1px;");
		   intime=new Label();
		   intime.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col2.getChildren().add(intime);
		   glrow1colum1.add(col2,0,2);
		   
		   glrow1colum2 = new GridPane();
		   //glrow1colum2.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #9FC5E8; -fx-text-fill: white;");
		ccc1 = new Label("Entry");
		ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		col1.getChildren().add(ccc1);
		   glrow1colum2.add(col1,0,0);
		   
		   
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #F4CCCC; -fx-text-fill: white;");
		ccc1 = new Label("Late");
		ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		col1.getChildren().add(ccc1);
		   glrow1colum2.add(col1,0,1);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(300);
		   col2.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px;");
		   latetime=new Label();
		   latetime.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col2.getChildren().add(latetime);
		   glrow1colum2.add(col2,0,2);
		   
		   glrow1colum3 = new GridPane();
		   //glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 0px ;-fx-background-color: #9FC5E8; -fx-text-fill: white;");
		ccc1 = new Label(" ");
		ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		col31.getChildren().add(ccc1);
		   glrow1colum3.add(col31,0,0);
		   
		   
		   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #EA9999; -fx-text-fill: white;");
		ccc1 = new Label("Total Late");
		ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		col31.getChildren().add(ccc1);
		   glrow1colum3.add(col31,0,1);
		   
		   col32=new HBox();
		   col32.setPadding(new Insets(top,9,bottom,0));;
		   col32.setAlignment(Pos.CENTER);
		   col32.setMinWidth(302);
		   col32.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px;");
		   totallatetime=new Label();
		totallatetime.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col32.getChildren().add(totallatetime);
		   glrow1colum3.add(col32,0,2);
		  
		  
		   glrow1colum1.setPrefWidth(400);
	        glrow1colum2.setPrefWidth(400);
	        glrow1colum3.setPrefWidth(400);
		   glrow4.getChildren().addAll(glrow1colum1, glrow1colum2, glrow1colum3);
		   
		   verticaldata.getChildren().add(glrow4);
		   
		   
		   HBox glrow5 = new HBox(); // HBox is a horizontal box
		   glrow5.setMaxWidth(1130);
		   glrow5.setStyle("-fx-padding:5px");
		  // VBox glrow1colum1 = new VBox(); // HBox is a horizontal box
		   glrow1colum1 = new GridPane();
		  // glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width:0px 0px 0px 0px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER_RIGHT);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 1px ;-fx-background-color: #D9D2E9; -fx-text-fill: white;");
		ccc1 = new Label("Exit");
		ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		col1.getChildren().add(ccc1);

		   glrow1colum1.add(col1,0,0);
		   
		   
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #70AD47; -fx-text-fill: white;");
		ccc1 = new Label("Ok");
		ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		col1.getChildren().add(ccc1);

		   glrow1colum1.add(col1,0,1);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(400);
		   col2.setStyle("-fx-border-color: black; -fx-border-width:0px 1px 1px 1px;");
		   ok=new Label();
		   ok.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col2.getChildren().add(ok);
		   glrow1colum1.add(col2,0,2);
		   
		   glrow1colum2 = new GridPane();
		   //glrow1colum2.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #D9D2E9; -fx-text-fill: white;");
		ccc1 = new Label(" ");
		ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		col1.getChildren().add(ccc1);

		glrow1colum2.add(col1,0,0);
		   
		   
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #F4CCCC; -fx-text-fill: white;");
		ccc1 = new Label("Early");
		ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		col1.getChildren().add(ccc1);

		   glrow1colum2.add(col1,0,1);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(300);
		   col2.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px;");
		   earlytime=new Label();
		   earlytime.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col2.getChildren().add(earlytime);
		   glrow1colum2.add(col2,0,2);
		   
		   glrow1colum3 = new GridPane();

				//glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #D9EAD3; -fx-text-fill: white;");
		ccc1 = new Label("Total Extra Time");
		ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		col31.getChildren().add(ccc1);
		   glrow1colum3.add(col31,0,0);
		   
		   col32=new HBox();
		   col32.setPadding(new Insets(top*4,9,bottom*4+5,0));;
		   col32.setAlignment(Pos.CENTER);
		   col32.setMinWidth(302);
		   col32.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px;");
		   totalextratime=new Label();
		   totalextratime.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col32.getChildren().add( totalextratime);
		   glrow1colum3.add(col32,0,1);
		  
		  
		   glrow1colum1.setPrefWidth(400);
	        glrow1colum2.setPrefWidth(400);
	        glrow1colum3.setPrefWidth(400);
		   glrow5.getChildren().addAll(glrow1colum1, glrow1colum2, glrow1colum3);
		   
		   verticaldata.getChildren().add(glrow5);
		   
		   
		   HBox glrow6 = new HBox(); // HBox is a horizontal box
		   glrow6.setMaxWidth(1130);
		   glrow6.setStyle("-fx-padding:5px");
		  // VBox glrow1colum1 = new VBox(); // HBox is a horizontal box
		   glrow1colum1 = new GridPane();
		  // glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width:0px 0px 0px 0px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #F4CCCC; -fx-text-fill: white;");
		ccc1 = new Label("Office Out Time");
		ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		col1.getChildren().add(ccc1);
		   glrow1colum1.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(400);
		   col2.setStyle("-fx-border-color: black; -fx-border-width:0px 1px 1px 1px;");
		   outtime=new Label();
		   outtime.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col2.getChildren().add(outtime);
		   glrow1colum1.add(col2,0,1);
		   
		   glrow1colum2 = new GridPane();
		   //glrow1colum2.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #B7E1CD; -fx-text-fill: white;");
		ccc1 = new Label("Office In Time");
		ccc1.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		col1.getChildren().add(ccc1);

		   glrow1colum2.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(300);
		   col2.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px;");
		   intotaltime=new Label();
		   intotaltime.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col2.getChildren().add(intotaltime);
		   
		   glrow1colum2.add(col2,0,1);
		   
		   glrow1colum3 = new GridPane();
		   //glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #38761D; -fx-text-fill: white;");
		   totalTimeLabel = new Label("Total Time");
		   totalTimeLabel.setStyle("-fx-text-fill: white;-fx-font-size:14px; -fx-font-weight: bold;");
		   col31.getChildren().add(totalTimeLabel);
		   glrow1colum3.add(col31,0,0);
		   
		   col32=new HBox();
		   col32.setPadding(new Insets(top,9,bottom,0));;
		   col32.setAlignment(Pos.CENTER);
		   col32.setMinWidth(302);
		   col32.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px;");
		   totaltime=new Label();
		   totaltime.setStyle("-fx-font-size:14px; -fx-font-weight: bold;");
		   col32.getChildren().add(totaltime);
		  
		   glrow1colum3.add(col32,0,1);
		  
		  
		   glrow1colum1.setPrefWidth(400);
	        glrow1colum2.setPrefWidth(400);
	        glrow1colum3.setPrefWidth(400);
		   glrow6.getChildren().addAll(glrow1colum1, glrow1colum2, glrow1colum3);
		   
		     verticaldata.getChildren().add(glrow6);


		   setglance();
		   


		   // give margin padding
		    ScrollPane scrollPane = new ScrollPane();
		    scrollPane.setContent(verticaldata);
		     // scrollPane.setFitToWidth(true); // Adjusts the width of the scroll pane to its content
		   // scrollPane.setFitToHeight(true); // Adjusts the height of the scroll pane to its content


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
	        borderPane.setStyle("-fx-padding: 10 10 50 10;");
		  //  VBox.setMargin(borderPane , new Insets(0, 0, 100, 0)); // 10 pixels of bottom margin
	        
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
		outtime.setText("");
		intotaltime.setText("");
		totaltime.setText("");
    
    }
	public void setglance() {
            System.out.println(startDate+" "+endDate);

		// Initialize counters
		officedayc = 0;
		presentdayc = 0;
		avgtimec = 0;
		leavedayc = 0;
		absentdayc = 0;
		holydayc = 0;
		shorttimec = 0;
		regulartimec = 0;
		extratimec = 0;
		intimec = 0;
		latetimec = 0;
		totallatetimec = 0;
		okc = 0;
		earlytimec = 0;
		totalextratimec = 0;
		durationc = Duration.ZERO;
		totallatedurationc = Duration.ZERO;
		totalextradurationc = Duration.ZERO;
		outtimec = 0;totalExtraTime=0;
		totaltimecc = Duration.ZERO;
		intotaltimec = Duration.ZERO;
		outtimec = 0;
		totaltimecc = Duration.ZERO;
		timeInSecond=0;
		timeInSecondOfOutTime=0;
		if (dataList.size() > 0) {
			dataList.forEach(e -> {
				// Define the date format
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        databaseDate=null;
				// Convert the string to LocalDate
				try {
					databaseDate = LocalDate.parse(e.getEntryDate(), dateFormatter);
					//System.out.println("Converted LocalDate: " + databaseDate);
				} catch (DateTimeParseException f) {
					f.printStackTrace(); // Handle parsing exception
				}
				if (databaseDate != null) {
					System.out.println(databaseDate+" "+startDate+" "+endDate);
					if (((databaseDate.equals(startDate) || databaseDate.equals(endDate))&& selectedparsonglance.equals(e.getName()) )||
							(databaseDate.isAfter(startDate) && databaseDate.isBefore(endDate)&& selectedparsonglance.equals(e.getName()))) {

						employeeList.forEach(g->{
							LocalDate databaseDate1 = LocalDate.parse(g.getJoinDate(), dateFormatter);
							if((e.getName().equals(g.getName())&& databaseDate1.isBefore(databaseDate)) || (e.getName().equals(g.getName())&& databaseDate1.isEqual(databaseDate) )){


								if (!"Holyday".equals(e.getStatus())) {
									officedayc++;
								}

								if ("Present".equals(e.getStatus())) {
									Duration durationBetweenEntryExit = Duration.between(e.getEntryTime(), e.getExitTime());
									timeInSecond=timeInSecond+durationBetweenEntryExit.toHoursPart()*60L*60L+durationBetweenEntryExit.toMinutesPart()*60L;
									durationc = durationc.plus(durationBetweenEntryExit);
									System.out.println("Current total duration (seconds): " + durationc.getSeconds());
									presentdayc++;

									long hours = durationBetweenEntryExit.toHoursPart();
									long minutes = durationBetweenEntryExit.toMinutesPart();
									int settingHours = returnSettingTotalHour(e.getEmployeeId(), e.getName(), e.getEntryDate());
									System.out.println(settingHours);

									if (hours < settingHours) {
										shorttimec++;
									} else if (hours > settingHours || (hours == settingHours && minutes > 0)) {
										extratimec++;
									} else {
										regulartimec++;
									}

									LocalTime lateThreshold = LocalTime.of(returnSettingStartHour(e.getEmployeeId(), e.getName(), e.getEntryDate()), returnGlobalSettingLateMinute(e.getEntryDate()) + 1);

									if (e.getEntryTime().toLocalTime().isBefore(lateThreshold)) {
										intimec++;
									}

									lateThreshold = LocalTime.of(returnSettingStartHour(e.getEmployeeId(), e.getName(), e.getEntryDate()), returnGlobalSettingLateMinute(e.getEntryDate()));
									if (e.getEntryTime().toLocalTime().isAfter(lateThreshold)) {
										latetimec++;
										LocalTime lateThreshold1 = LocalTime.of(returnSettingStartHour(e.getEmployeeId(), e.getName(), e.getEntryDate()), returnSettingStartMinute(e.getEmployeeId(), e.getName(), e.getEntryDate()));
										Duration lateDuration = Duration.between(lateThreshold1, e.getEntryTime().toLocalTime());
										totallatedurationc = totallatedurationc.plus(lateDuration);
									}

									String[] data = subtractHourMinute(returnSettingEndHour(e.getEmployeeId(), e.getName(), e.getEntryDate()), returnGlobalSettingEarlyMinute(e.getEntryDate()));
									LocalTime exitThreshold = LocalTime.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
									if (e.getExitTime().toLocalTime().isBefore(exitThreshold)) {
										earlytimec++;
									}

									exitThreshold = LocalTime.of(returnSettingEndHour(e.getEmployeeId(), e.getName(), e.getEntryDate()), returnSettingEndMinute(e.getEmployeeId(), e.getName(), e.getEntryDate()));
									if (e.getExitTime().toLocalTime().isAfter(exitThreshold)) {
										// 15 minute calculation after end time
										LocalTime	exitThreshold1 = LocalTime.of(returnSettingEndHour(e.getEmployeeId(), e.getName(), e.getEntryDate()), 15+returnSettingEndMinute(e.getEmployeeId(), e.getName(), e.getEntryDate()));
										if (e.getExitTime().toLocalTime().isAfter(exitThreshold1)){
											Duration extraDuration = Duration.between(exitThreshold, e.getExitTime().toLocalTime());
											totalExtraTime=totalExtraTime+extraDuration.toHoursPart()*60L*60L+extraDuration.toMinutesPart()*60L;
											//totalextradurationc = totalextradurationc.plus(extraDuration);
											Duration ff=	Duration.ofSeconds(totalExtraTime);

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

								if ("Leave".equals(e.getStatus())) {
									leavedayc++;
								}

								if ("Absent".equals(e.getStatus())) {
									absentdayc++;
								}

								if ("Holyday".equals(e.getStatus())) {
									holydayc++;
								}

							}
						});

						// Your code for handling the case when the databaseDate is within the range

					} else {
						// Your code for handling the case when the databaseDate is outside the range
						System.out.println("The date is outside the range.");
					}
				}
				if (selectedparsonglance.equals(e.getName()) && selectedmonthglance.equals(e.getMonth()) && selectedyearglance.equals(e.getYear())) {

				}
			});

			officeday.setText(Integer.toString(officedayc));
			presentday.setText(Integer.toString(presentdayc));
			intotaltimec = Duration.ofSeconds(timeInSecond);
            //
			Duration outT=Duration.ofSeconds(timeInSecondOfOutTime);
			outtime.setText(outT.toHours() + ":" + outT.toMinutesPart());

			long seconds = (long) (outtimec * 60 * 60);
			//Duration outtimeduration = Duration.ofSeconds(seconds+timeInSecond);
			long gg=timeInSecondOfOutTime+timeInSecond;
			totaltimecc = Duration.ofSeconds(gg);

			intotaltime.setText(intotaltimec.toHours() + ":" + intotaltimec.toMinutesPart());
			totaltime.setText(totaltimecc.toHours() + ":" + totaltimecc.toMinutesPart());

			System.out.println("TOTAL HOURS " + durationc.toHoursPart() + " TOTAL MINUTES " + durationc.toMinutesPart() + " total days " + presentdayc);

			if (presentdayc != 0) {
				long totalSeconds =  timeInSecond;
				long averageSeconds = totalSeconds / presentdayc;
				System.out.println("Avg second " + totalSeconds);
				durationc = Duration.ofSeconds(averageSeconds);
				totalextradurationc=Duration.ofSeconds(totalExtraTime);
			}

			System.out.println("Avg hours " + durationc.toHoursPart());

			avgtime.setText(durationc.toHoursPart() + ":" + durationc.toMinutesPart());

			leaveday.setText(Integer.toString(leavedayc));
			absentday.setText(Integer.toString(absentdayc));
			holyday.setText(Integer.toString(holydayc));
			shorttime.setText(Integer.toString(shorttimec));
			regulartime.setText(Integer.toString(regulartimec));
			extratime.setText(Integer.toString(extratimec));
			intime.setText(Integer.toString(intimec));
			latetime.setText(Integer.toString(latetimec));
			totallatetime.setText(totallatedurationc.toHoursPart() + ":" + totallatedurationc.toMinutesPart());
			ok.setText(Integer.toString(presentdayc - earlytimec));
			earlytime.setText(Integer.toString(earlytimec));
			totalextratime.setText(totalextradurationc.toHoursPart() + ":" + totalextradurationc.toMinutesPart());
		}
	}

	public void exportGlanceData() {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Employee Data");
        int x=14;
        int width=6000;
		int minusWidth=5000;

		Font font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());

		Font fontSize = workbook.createFont();
		fontSize.setBold(true);
		fontSize.setFontHeightInPoints((short) 14); // Font size

		// Define the number of empty rows and columns for padding
		int paddingRows = 1;
		int paddingColumns = 1;
		// Add empty rows for top padding
		for (int i = 0; i < paddingRows; i++) {
			sheet.createRow(i);
		}

		// Add header row with padding
		String[] headers = {
				"At A GLANCE"

		};

		Row headerRow = sheet.createRow(paddingRows);
		headerRow.setHeightInPoints(40); // Set header row height

		for (int i = 0; i < 5; i++) {
            if(i==2)
			{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically
				headerCellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 255, 204), null));

				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) 18); // Font size
				headerFont.setFontName("Arial"); // Font name
				headerCellStyle.setFont(headerFont);





				Cell cell = headerRow.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("At A GLANCE");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else {
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 255, 204), null));
				if(i==0){
					headerCellStyle.setBorderLeft(BorderStyle.THIN);
				}
				if(i==4){
					headerCellStyle.setBorderRight(BorderStyle.THIN);
				}
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically

				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) 18); // Font size
				headerFont.setFontName("Arial"); // Font name
				headerCellStyle.setFont(headerFont);

				Cell cell = headerRow.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue(" ");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, 0); // Set column width
			}

		}

		//  month
		paddingRows=paddingRows+1;
		Row monthRow=sheet.createRow(paddingRows);
		monthRow.setHeightInPoints(20); // Set header row height
		for(int i=0;i<5;i++){
			if(i==2)
			{
				// Create a CellStyle with a background color for the header row
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically
				headerCellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 255, 204), null));

				Cell cell = monthRow.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue(startDate+" to "+endDate);

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				// Create a CellStyle with a background color for the header row
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 255, 204), null));
				if(i==4){
					headerCellStyle.setBorderRight(BorderStyle.THIN);
				}
				if(i==0)
				{
					headerCellStyle.setBorderLeft(BorderStyle.THIN);
				}

				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = monthRow.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue(" ");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, 0); // Set column width
			}

		}

		paddingRows=paddingRows+1;
		Row nameRow=sheet.createRow(paddingRows);
		nameRow.setHeightInPoints(20); // Set header row height
		for(int i=0;i<5;i++){
			if(i==2){
				// Create a CellStyle with a background color for the header row
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = nameRow.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue(selectedparsonglance);

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				// Create a CellStyle with a background color for the header row
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = nameRow.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue(" ");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, 0); // Set column width
			}

		}

		sheet.createRow(paddingRows+1);

		paddingRows=paddingRows+2;
		Row line1Row=sheet.createRow(paddingRows);
		line1Row.setHeightInPoints(20); // Set header row height
		for(int i=0;i<5;i++){
			if(i%2==0){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically

				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) x); // Font size
				headerFont.setFontName("Arial"); // Font name
				headerCellStyle.setFont(headerFont);
				headerCellStyle.setFont(fontSize);

				Cell cell = line1Row.createCell(i + paddingColumns); // Add padding columns
				if(i==0){
					cell.setCellValue("Office Day");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#CCCCCC"), null));
					//headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				}else if(i==2){
					cell.setCellValue("Total Present");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#B6D7A8"), null));
				}
				else{
					cell.setCellValue("Avg Time");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#4472C4"), null));
				}


				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line1Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}

		}

		paddingRows=paddingRows+1;
		Row line12Row=sheet.createRow(paddingRows);
		line12Row.setHeightInPoints(40); // Set header row height
		for(int i=0;i<5;i++){
			if(i%2==0){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically



				Cell cell = line12Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue(i);
				if(i==0){
					cell.setCellValue(officeday.getText());
				}
				else if (i==2) {
					cell.setCellValue(presentday.getText());
				}else{
					cell.setCellValue(avgtime.getText());
				}
				headerCellStyle.setFont(fontSize);
				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line12Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}

		}

		sheet.createRow(paddingRows+1);

		paddingRows=paddingRows+2;
		Row line2Row=sheet.createRow(paddingRows);
		line2Row.setHeightInPoints(20); // Set header row height
		for(int i=0;i<5;i++){
			if(i%2==0){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically

				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) x); // Font size
				headerFont.setFontName("Arial"); // Font name
				headerCellStyle.setFont(headerFont);

				Cell cell = line2Row.createCell(i + paddingColumns); // Add padding columns
				if(i==0){
					cell.setCellValue("Leave");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#FFE5A0"), null));
					//headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				}else if(i==2){
					cell.setCellValue("Absent");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#B10202"), null));
					headerCellStyle.setFont(font);
				}
				else{
					cell.setCellValue("Holiday");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#3D3D3D"), null));
					headerCellStyle.setFont(font);
				}

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line2Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}

		}

		paddingRows=paddingRows+1;
		Row line21Row=sheet.createRow(paddingRows);
		line21Row.setHeightInPoints(40); // Set header row height
		for(int i=0;i<5;i++){
			if(i%2==0){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line21Row.createCell(i + paddingColumns); // Add padding columns
				if(i==0){
					cell.setCellValue(leaveday.getText());
				}
				else if (i==2) {
					cell.setCellValue(absentday.getText());
				}else{
					cell.setCellValue(holyday.getText());
				}
				headerCellStyle.setFont(fontSize);
				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line21Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}

		}

		sheet.createRow(paddingRows+1);

		paddingRows=paddingRows+2;
		Row line3Row=sheet.createRow(paddingRows);
		line3Row.setHeightInPoints(20); // Set header row height
		for(int i=0;i<5;i++){
			if(i%2==0){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically

				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) x); // Font size
				headerFont.setFontName("Arial"); // Font name
				headerCellStyle.setFont(headerFont);

				Cell cell = line3Row.createCell(i + paddingColumns); // Add padding columns
				if(i==0){
					cell.setCellValue("Short Time");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#F4CCCC"), null));
					//headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				}else if(i==2){
					cell.setCellValue("Regular Time");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#B7E1CD"), null));
				}
				else{
					cell.setCellValue("Extra Time");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#38761D"), null));
				}

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line3Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}

		}

		paddingRows=paddingRows+1;
		Row line31Row=sheet.createRow(paddingRows);
		line31Row.setHeightInPoints(40); // Set header row height
		for(int i=0;i<5;i++){
			if(i%2==0){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line31Row.createCell(i + paddingColumns); // Add padding columns
				if(i==0){
					cell.setCellValue(shorttime.getText());
				}
				else if (i==2) {
					cell.setCellValue(regulartime.getText());
				}else{
					cell.setCellValue(extratime.getText());
				}
				headerCellStyle.setFont(fontSize);
				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line31Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}

		}

		sheet.createRow(paddingRows+1);

		paddingRows=paddingRows+2;
		Row line4Row=sheet.createRow(paddingRows);
		line4Row.setHeightInPoints(20); // Set header row height
		for(int i=0;i<5;i++){
			if(i==2){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically

				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) x); // Font size
				headerFont.setFontName("Arial"); // Font name
				headerCellStyle.setFont(headerFont);

				Cell cell = line4Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("Entry");
				headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#9FC5E8"), null));

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();

				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				if(i==1){
					headerCellStyle.setBorderBottom(BorderStyle.THIN);
				}
				if(i==3){
					headerCellStyle.setBorderBottom(BorderStyle.THIN);
				}
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
					if(i==0){
						headerCellStyle.setBorderLeft(BorderStyle.THIN);
					}
				if(i==4){
					headerCellStyle.setBorderRight(BorderStyle.THIN);
				}
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line4Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");
				headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#9FC5E8"), null));
				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}

		}

		paddingRows=paddingRows+1;
		Row line41Row=sheet.createRow(paddingRows);
		line41Row.setHeightInPoints(20); // Set header row height
		for(int i=0;i<5;i++){
			if(i%2==0){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically

				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) x); // Font size
				headerFont.setFontName("Arial"); // Font name
				headerCellStyle.setFont(headerFont);

				Cell cell = line41Row.createCell(i + paddingColumns); // Add padding columns
				if(i==0){
					cell.setCellValue("In Time");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#38761D"), null));
					//headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				}else if(i==2){
					cell.setCellValue("Late");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#F4CCCC"), null));
				}
				else{
					cell.setCellValue("Total Late");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#EA9999"), null));
				}

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line41Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}

		}
		paddingRows=paddingRows+1;
		Row line42Row=sheet.createRow(paddingRows);
		line42Row.setHeightInPoints(40); // Set header row height
		for(int i=0;i<5;i++){
			if(i%2==0){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line42Row.createCell(i + paddingColumns); // Add padding columns
				if(i==0){
					cell.setCellValue(intime.getText());
				}
				else if (i==2) {
					cell.setCellValue(latetime.getText());
				}else{
					cell.setCellValue(totallatetime.getText());
				}
				headerCellStyle.setFont(fontSize);
				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line42Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}

		}


		sheet.createRow(paddingRows+1);

		paddingRows=paddingRows+2;
		Row line5Row=sheet.createRow(paddingRows);
		line5Row.setHeightInPoints(20); // Set header row height
		for(int i=0;i<5;i++){
			if(i==1|| i==3){
				CellStyle headerCellStyle = workbook.createCellStyle();

				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				if(i==1){
					headerCellStyle.setBorderBottom(BorderStyle.THIN);
					headerCellStyle.setBorderTop(BorderStyle.THIN);
				}
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically
				int mm=x-2;
				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) mm); // Font size
				headerFont.setFontName("Arial"); // Font name
				headerCellStyle.setFont(headerFont);

				Cell cell = line5Row.createCell(i + paddingColumns); // Add padding columns

				if(i==1){
					cell.setCellValue("Exit");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#CCCCCC"), null));
				}
				else{
					cell.setCellValue(" ");
					headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				}


				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}
			else if(i==4){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically

				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) x); // Font size
				headerFont.setFontName("Arial"); // Font name
				headerCellStyle.setFont(headerFont);

				Cell cell = line5Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("Total Extra");
				headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#B6D7A8"), null));

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				if(i==0){
					headerCellStyle.setBorderLeft(BorderStyle.THIN);
				}
				if(i==2){
					headerCellStyle.setBorderRight(BorderStyle.THIN);
				}
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line5Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");
				headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#CCCCCC"), null));
				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}

		}

		paddingRows=paddingRows+1;
		Row line51Row=sheet.createRow(paddingRows);
		line51Row.setHeightInPoints(20); // Set header row height
		for(int i=0;i<5;i++){
			if(i%2==0){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				if(i!=4){
					headerCellStyle.setBorderTop(BorderStyle.THIN);
				}
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically

				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) x); // Font size
				headerFont.setFontName("Arial"); // Font name
				headerCellStyle.setFont(headerFont);

				Cell cell = line51Row.createCell(i + paddingColumns); // Add padding columns
				if(i==0){
					cell.setCellValue("OK");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#38761D"), null));
					//headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				}else if(i==2){
					cell.setCellValue("Early");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#F4CCCC"), null));
				}
				else{
					cell.setCellValue("Time");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#B6D7A8"), null));
				}

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line51Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}

		}
		paddingRows=paddingRows+1;
		Row line52Row=sheet.createRow(paddingRows);
		line52Row.setHeightInPoints(40); // Set header row height
		for(int i=0;i<5;i++){
			if(i%2==0){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line52Row.createCell(i + paddingColumns); // Add padding columns
				if(i==0){
					cell.setCellValue(ok.getText());
				}
				else if (i==2) {
					cell.setCellValue(earlytime.getText());
				}else{
					cell.setCellValue(totalextratime.getText());
				}
				headerCellStyle.setFont(fontSize);
				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line52Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}

		}

		sheet.createRow(paddingRows+1);

		paddingRows=paddingRows+2;
		Row line6Row=sheet.createRow(paddingRows);
		line6Row.setHeightInPoints(20); // Set header row height
		for(int i=0;i<5;i++){
			if(i%2==0){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically

				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) x); // Font size
				headerFont.setFontName("Arial"); // Font name
				headerCellStyle.setFont(headerFont);

				Cell cell = line6Row.createCell(i + paddingColumns); // Add padding columns
				if(i==0){
					cell.setCellValue("Office Out Time");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#F4CCCC"), null));
					//headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				}else if(i==2){
					cell.setCellValue("Office in Time");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#B7E1CD"), null));
				}
				else{
					cell.setCellValue("Total Time");
					headerCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#38761D"), null));
				}

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line6Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}

		}

		paddingRows=paddingRows+1;
		Row line61Row=sheet.createRow(paddingRows);
		line61Row.setHeightInPoints(40); // Set header row height
		for(int i=0;i<5;i++){
			if(i%2==0){
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line61Row.createCell(i + paddingColumns); // Add padding columns
				if(i==0){
					cell.setCellValue(outtime.getText());
				}
				else if (i==2) {
					cell.setCellValue(intotaltime.getText());
				}else{
					cell.setCellValue(totaltime.getText());
				}
				headerCellStyle.setFont(fontSize);
				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width); // Set column width
			}
			else{
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//headerCellStyle.setBorderBottom(BorderStyle.THIN);
				//headerCellStyle.setBorderTop(BorderStyle.THIN);
				//headerCellStyle.setBorderRight(BorderStyle.THIN);
				//headerCellStyle.setBorderLeft(BorderStyle.THIN);
				////headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
				//headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically


				Cell cell = line61Row.createCell(i + paddingColumns); // Add padding columns
				cell.setCellValue("");

				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(i + paddingColumns, width-minusWidth); // Set column width
			}

		}

		// Add data rows
		// Replace this with your actual data
		/*Object[][] data = {
				{"101", "John Doe", "5", "4", "9:00", "0", "1", "0", "0", "8:00", "1:00", "8:30", "0", "0:30", "8:30", "0", "0:30", "18:30", "8:30", "10:00"}
				// Add more rows as needed
		};

		int rowNum = paddingRows + 1;
		for (Object[] rowData : data) {
			Row row = sheet.createRow(rowNum++);
			row.setHeightInPoints(20); // Set data row height
			for (int i = 0; i < rowData.length; i++) {
				Cell cell = row.createCell(i + paddingColumns); // Add padding columns
				if (rowData[i] instanceof String) {
					cell.setCellValue((String) rowData[i]);
				} else if (rowData[i] instanceof Integer) {
					cell.setCellValue((Integer) rowData[i]);
				} else if (rowData[i] instanceof Double) {
					cell.setCellValue((Double) rowData[i]);
				}
			}
		}*/

		// Show file chooser dialog
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Data");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files (*.xlsx)", "*.xlsx"));
		java.io.File file = fileChooser.showSaveDialog(new Stage());

		if (file != null) {
			try (FileOutputStream fos = new FileOutputStream(file)) {
				workbook.write(fos);
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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
