package org.example.spring.view;

import java.awt.Dimension;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Locale;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.example.spring.controller.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.spring.model.EmployeeData;
import org.example.spring.repository.EmployeeInsertedDataRepository;
import org.example.spring.repository.RepositoryManager;

public class AddDataView {
	EmployeeInsertedDataRepository employeeInsertedDataRepository;
	AddDataController AddDataController;
	private ObservableList<String> names;
	private ListView<String> listView;
	private TextField searchField;
	private TextField dateTextField;
	int[] id=new int[500];
	Button submit;
	TextField[] startreason;
	TextField[] endreason;
	TextField[] outtimehour;TextField[] outtimeminute;
	ComboBox<String>[] statusbox;

	TextField[] starthour;
	TextField[] startminite;
	DatePicker datePicker11;
	ComboBox<String>[] comboBoxstart;
	DatePicker[] datePicker;
	TextField[] endhour;
	TextField[] endminite;
	ComboBox<String>[] comboBoxend;
	RepositoryManager repositoryManager;
	String globalDayStatus;
	int length=0;  Label labelh3date; Label labelh2date; Label labelh1date;
	BorderPane createTab(RepositoryManager repositoryManager1 , List<EmployeeData> employeeList) {
		repositoryManager=repositoryManager1;

		length=employeeList.size();
		datePicker=new  DatePicker[length];
		startreason=new TextField[length];
		endreason=new TextField[length];
		outtimehour=new TextField[length];
		outtimeminute=new TextField[length];
		statusbox = new ComboBox[length];

		starthour = new  TextField[length];
		startminite = new TextField[length];
		comboBoxstart = new ComboBox[length];
		endhour = new TextField[length];
		endminite = new TextField[length];
		comboBoxend = new ComboBox[length];
		Dimension screenSize = new Dimension(1300,650);
		// Create labels for different panes
		Label label1 = new Label("");
		Label label2 = new Label("");

		// Create nested StackPane for the left section
		StackPane leftStackPane = new StackPane();
		leftStackPane.setPrefWidth(0.025 * screenSize.getWidth()-70); // 30% of the total width
		leftStackPane.getChildren().add(label2);

		// Create nested StackPane for the center section
		StackPane centerStackPane = new StackPane();
		centerStackPane.setPrefWidth(0.975 *screenSize.getWidth()-70); // 40% of the total width
		//centerStackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		// centerStackPane.setStyle("-fx-border-color: black; -fx-border-width: 2;");


		VBox verticaldata=new VBox();

		dateTextField = new TextField();
		dateTextField.setEditable(false);
		dateTextField.setMaxWidth(80);

		Button minusButton = new Button("<");
		Button plusButton = new Button(">");

		// Set initial date
		updateDateTextField(LocalDate.now());

		// Set button actions
		minusButton.setOnAction(e -> decrementDate());
		plusButton.setOnAction(e -> incrementDate());

		// Layout
		HBox root = new HBox(10);
		root.setPadding(new Insets(10));
		root.getChildren().addAll(minusButton, dateTextField, plusButton);


		datePicker11 = new DatePicker();
		datePicker11.setMaxWidth(150);
		LocalDate currentDate11 = LocalDate.now();
		datePicker11.setValue(currentDate11);

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
		datePicker11.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				if (newValue != null) {
					updateDateTextField(newValue);

					int selectedYear = newValue.getYear();
					String selectedMonth = newValue.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());


					labelh1date.setText("Daily Attendance Sheet-"+selectedYear);
					labelh2date.setText("Month : "+selectedMonth);
					labelh3date.setText("   Date: " + newValue.toString());

					// Compare the two dates

				}
			}
		});
		HBox topH=new HBox(50);
		HBox jj=new HBox();
		jj.setPadding(new Insets(10));
		jj.getChildren().add(datePicker11);
		topH.getChildren().addAll(root,jj);
		verticaldata.getChildren().add(topH);


		HBox header = new HBox(); // HBox is a horizontal box
		header.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");
		// Center the label within the HBox
		header.setAlignment(Pos.CENTER);
		labelh1date = new Label("Daily Attendance Sheet-"+dateTextField.getText().substring(0, 4));
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
		labelh2date = new Label("Month: "+LocalDate.now().getMonth());
		GridPane.setConstraints(labelh2date, 0, 0); // (column, row)

		labelh3date = new Label("    Date: "+dateTextField.getText());
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


		items.addAll("Present", "Absent","Leave","Holyday");

		comboBox.setItems(items);

		// Set a default value (optional)
		comboBox.setValue("Present");

		// Handle selection change
		comboBox.setOnAction(event -> {
			for(int i=0;i<length;i++)
			{
				statusbox[i].setValue(comboBox.getValue());
			}

		});
		ComboBox<String> golobalcomboBox = new ComboBox<>();

		// Add items to the ComboBox
		golobalcomboBox.getItems().addAll("Office", "Holiday");

		// Set default value
		globalDayStatus="Office";
		golobalcomboBox.setValue("Office");
		// Handle selection change for golobalcomboBox
		golobalcomboBox.setOnAction(event -> {
			globalDayStatus = golobalcomboBox.getValue();

		});

		emplyeebox.getChildren().addAll(comboBox,golobalcomboBox);
		emplyeebox.setSpacing(10); // Set spacing between ComboBoxes
		verticaldata.getChildren().add(emplyeebox);

		VBox body=new VBox();

		for(int i=0;i<=employeeList.size();i++)
		{
			if(i==0)
			{
				gridPane = new GridPane();
				gridPane.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px; ");

				//gridPane.setHgap(0); // Horizontal gap between columns
				//gridPane.setVgap(10); // Vertical gap between rows
				gridPane.setPadding(new Insets(0)); // Padding around the whole grid

				// Adding nodes to the GridPane
				Label    lab1 = new Label("ID");

				GridPane.setConstraints(lab1, 0, 0); // (column, row)

				Rectangle separator1 = new Rectangle(1, 35, Color.BLACK);
				GridPane.setConstraints(separator1, 1, 0);


				Label lab2 = new Label("Name");
				GridPane.setConstraints(lab2, 2, 0);


				Rectangle separator2 = new Rectangle(1, 35, Color.BLACK);
				GridPane.setConstraints(separator2, 3, 0);

				Label lab3 = new Label("Start Time");

				GridPane.setConstraints(lab3, 4, 0);

				Rectangle separator3 = new Rectangle(1, 35, Color.BLACK);
				GridPane.setConstraints(separator3, 5, 0);


				Label lab4 = new Label("Reason For\nBeing Late");
				GridPane.setConstraints(lab4, 6, 0);

				Rectangle separator4 = new Rectangle(1, 35, Color.BLACK);
				GridPane.setConstraints(separator4, 7, 0);

				Label lab5 = new Label("Exit Time");
				GridPane.setConstraints(lab5, 8, 0);

				Rectangle separator5 = new Rectangle(1, 35, Color.BLACK);
				GridPane.setConstraints(separator5, 9, 0);

				Label lab6 = new Label("Reason For\nEarly Exit");
				GridPane.setConstraints(lab6, 10, 0);


				Rectangle separator6 = new Rectangle(1, 35, Color.BLACK);
				GridPane.setConstraints(separator6, 11, 0);

				Label lab7 = new Label("Out Time");
				GridPane.setConstraints(lab7, 12, 0);


				Rectangle separator7 = new Rectangle(1, 35, Color.BLACK);
				GridPane.setConstraints(separator7, 13, 0);

				Label lab8 = new Label("Status");
				GridPane.setConstraints(lab8, 14, 0);


				// Set ColumnConstraints to make the first and third columns take up equal width
				ColumnConstraints col1 = new ColumnConstraints();
				col1.setPercentWidth(8);

				ColumnConstraints col2 = new ColumnConstraints();
				col2.setPercentWidth(5);

				ColumnConstraints col3 = new ColumnConstraints();
				col3.setPercentWidth(18);

				ColumnConstraints col4 = new ColumnConstraints();
				col4.setPercentWidth(5);

				ColumnConstraints col5 = new ColumnConstraints();
				col5.setPercentWidth(30);

				ColumnConstraints col6 = new ColumnConstraints();
				col6.setPercentWidth(5);

				ColumnConstraints col7 = new ColumnConstraints();
				col7.setPercentWidth(10);

				ColumnConstraints col8 = new ColumnConstraints();
				col8.setPercentWidth(5);

				ColumnConstraints col9 = new ColumnConstraints();
				col9.setPercentWidth(28);

				ColumnConstraints col10 = new ColumnConstraints();
				col10.setPercentWidth(5);


				ColumnConstraints col11 = new ColumnConstraints();
				col11.setPercentWidth(10);

				ColumnConstraints col12 = new ColumnConstraints();
				col12.setPercentWidth(5);

				ColumnConstraints col13 = new ColumnConstraints();
				col13.setPercentWidth(15);

				ColumnConstraints col14 = new ColumnConstraints();
				col14.setPercentWidth(5);

				ColumnConstraints col15 = new ColumnConstraints();
				col15.setPercentWidth(13);



				// Set alignment for labels
				GridPane.setHalignment(lab1, HPos.CENTER);
				GridPane.setHalignment(lab2, HPos.CENTER);
				GridPane.setHalignment(lab3, HPos.CENTER);
				GridPane.setHalignment(lab4, HPos.CENTER);
				GridPane.setHalignment(lab5, HPos.CENTER);
				GridPane.setHalignment(lab6, HPos.CENTER);
				GridPane.setHalignment(lab7, HPos.CENTER);
				GridPane.setHalignment(lab8, HPos.CENTER);

				// Set alignment for separators
				GridPane.setHalignment(separator1, HPos.CENTER);
				GridPane.setHalignment(separator2, HPos.CENTER);
				GridPane.setHalignment(separator3, HPos.CENTER);
				GridPane.setHalignment(separator4, HPos.CENTER);
				GridPane.setHalignment(separator5, HPos.CENTER);
				GridPane.setHalignment(separator6, HPos.CENTER);
				GridPane.setHalignment(separator7, HPos.CENTER);

				gridPane.getColumnConstraints().addAll(col1, col2,col3,col4, col5,col6,col7,col8,col9,col10,col11,col12,col13,col14,col15);

				// Create a rectangle with a background color to act as a separator


				gridPane.getChildren().addAll(lab1, separator1,lab2,separator2, lab3,separator3, lab4,separator4, lab5,separator5, lab6,separator6, lab7,separator7, lab8);
				// verticaldata.getChildren().add(gridPane);
				body.getChildren().add(gridPane);
			}
			else
			{
				gridPane = new GridPane();
				gridPane.setStyle("-fx-border-color: black; -fx-border-width: 0 1px 1px 1px; ");

				gridPane.setHgap(0); // Horizontal gap between columns
				gridPane.setVgap(0); // Vertical gap between rows
				gridPane.setPadding(new Insets(0)); // Padding around the whole grid

				// Adding nodes to the GridPane
				Label    lab1 = new Label(""+i);
				GridPane.setConstraints(lab1, 0, 0); // (column, row)

				Rectangle separator1 = new Rectangle(1, 30, Color.BLACK);
				GridPane.setConstraints(separator1, 1, 0);



				// VBox.setMargin(startdateTimeBox, new Insets(5, 0, 20, 0)); // Top and bottom margin of 5 pixel


				Label lab2 =new Label(employeeList.get(i-1).getName());
				GridPane.setConstraints(lab2, 2, 0);


				Rectangle separator2 = new Rectangle(1, 30, Color.BLACK);
				GridPane.setConstraints(separator2, 3, 0);

				starthour[i-1] =new  TextField("9");
				starthour[i-1] .setMaxWidth(55);
				starthour[i-1] .setEditable(true);


				// Create a Spinner for minutes
				startminite[i-1] = new TextField("0");
				startminite[i-1] .setMaxWidth(55);
				startminite[i-1] .setEditable(true);


				comboBoxstart[i-1] = new ComboBox<>();
				comboBoxstart[i-1].setMaxWidth(62);
				comboBoxstart[i-1].getItems().addAll("AM", "PM");
				comboBoxstart[i-1].setValue("AM"); // Set a default value if needed

				// Create an HBox to hold the Date and Time components
				HBox start = new HBox(15, starthour[i-1] , startminite[i-1] ,comboBoxstart[i-1]);



				// TextField lab3 = new TextField(" reson");
				GridPane.setConstraints(start, 4, 0);

				Rectangle separator3 = new Rectangle(1, 30, Color.BLACK);
				GridPane.setConstraints(separator3, 5, 0);






				startreason[i-1] = new TextField("");
				GridPane.setConstraints(startreason[i-1], 6, 0);

				Rectangle separator4 = new Rectangle(1, 30, Color.BLACK);
				GridPane.setConstraints(separator4, 7, 0);


				// Create a Spinner for hours
				datePicker[i-1] = new DatePicker();
				datePicker[i-1].setValue(LocalDate.now()); // Set initial date
				final int k=i;
				datePicker[k-1].setOnAction(e -> {

					LocalDate selectedDate =  datePicker[k-1].getValue();
					System.out.println("Selected date: " + selectedDate);
				});

				datePicker[i-1].setEditable(true);
				datePicker[i-1].setMaxWidth(100);

				// Create a Spinner for hours
				endhour[i-1] = new  TextField("5");
				endhour[i-1].setEditable(true);
				endhour[i-1].setMaxWidth(55);

				// Create a Spinner for minutes
				endminite[i-1] = new TextField("0");
				endminite[i-1] .setEditable(true);
				endminite[i-1] .setMaxWidth(55);

				comboBoxend[i-1] = new ComboBox<>();
				comboBoxend[i-1].getItems().addAll("AM", "PM");
				comboBoxend[i-1].setValue("PM"); // Set a default value if needed
				comboBoxend[i-1].setMaxWidth(62);


				// Create an HBox to hold the Date and Time components
				HBox enddateTimeBox = new HBox(5, endhour[i-1],  endminite[i-1] ,comboBoxend[i-1]);

				//TextField lab5 = new TextField(" reson");
				GridPane.setConstraints(enddateTimeBox, 8, 0);

				Rectangle separator5 = new Rectangle(1, 30, Color.BLACK);
				GridPane.setConstraints(separator5, 9, 0);

				endreason[i-1] = new TextField(" ");
				GridPane.setConstraints(endreason[i-1], 10, 0);



				Rectangle separator6 = new Rectangle(1, 30, Color.BLACK);
				GridPane.setConstraints(separator6, 11, 0);

				outtimehour[i-1] = new TextField("0");
				outtimehour[i-1].setAlignment(Pos.CENTER);

				outtimeminute[i-1] = new TextField("0");
				outtimeminute[i-1].setAlignment(Pos.CENTER);
				HBox outTimeBox = new HBox(5,outtimehour[i-1],outtimeminute[i-1]);
				GridPane.setConstraints(outTimeBox, 12, 0);


				Rectangle separator7 = new Rectangle(1, 30, Color.BLACK);
				GridPane.setConstraints(separator7, 13, 0);

				Label lab7 = new Label(" Date");
				statusbox[i-1] = new ComboBox<>();
				statusbox[i-1].getItems().addAll("Present", "Absent","Leave","Holyday");
				statusbox[i-1].setValue("Present"); // Set a default value if needed
				statusbox[i-1].setMaxWidth(100);
				GridPane.setConstraints(statusbox[i-1], 14, 0);





				// Set ColumnConstraints to make the first and third columns take up equal width
				ColumnConstraints col1 = new ColumnConstraints();
				col1.setPercentWidth(8);

				ColumnConstraints col2 = new ColumnConstraints();
				col2.setPercentWidth(5);

				ColumnConstraints col3 = new ColumnConstraints();
				col3.setPercentWidth(18);

				ColumnConstraints col4 = new ColumnConstraints();
				col4.setPercentWidth(5);

				ColumnConstraints col5 = new ColumnConstraints();
				col5.setPercentWidth(30);

				ColumnConstraints col6 = new ColumnConstraints();
				col6.setPercentWidth(5);

				ColumnConstraints col7 = new ColumnConstraints();
				col7.setPercentWidth(10);

				ColumnConstraints col8 = new ColumnConstraints();
				col8.setPercentWidth(5);

				ColumnConstraints col9 = new ColumnConstraints();
				col9.setPercentWidth(28);

				ColumnConstraints col10 = new ColumnConstraints();
				col10.setPercentWidth(5);


				ColumnConstraints col11 = new ColumnConstraints();
				col11.setPercentWidth(10);

				ColumnConstraints col12 = new ColumnConstraints();
				col12.setPercentWidth(5);

				ColumnConstraints col13 = new ColumnConstraints();
				col13.setPercentWidth(15);

				ColumnConstraints col14 = new ColumnConstraints();
				col14.setPercentWidth(5);

				ColumnConstraints col15 = new ColumnConstraints();
				col15.setPercentWidth(13);


				// Set alignment for labels
				GridPane.setHalignment(lab1, HPos.CENTER);
				GridPane.setHalignment(lab2, HPos.CENTER);
				GridPane.setHalignment(start, HPos.CENTER);
				GridPane.setHalignment(startreason[i-1], HPos.CENTER);
				GridPane.setHalignment(enddateTimeBox, HPos.CENTER);
				GridPane.setHalignment(endreason[i-1], HPos.CENTER);
				GridPane.setHalignment(statusbox[i-1], HPos.CENTER);
				GridPane.setHalignment(outTimeBox, HPos.CENTER);

				// Set alignment for separators
				GridPane.setHalignment(separator1, HPos.CENTER);
				GridPane.setHalignment(separator2, HPos.CENTER);
				GridPane.setHalignment(separator3, HPos.CENTER);
				GridPane.setHalignment(separator4, HPos.CENTER);
				GridPane.setHalignment(separator5, HPos.CENTER);
				GridPane.setHalignment(separator6, HPos.CENTER);
				GridPane.setHalignment(separator7, HPos.CENTER);



				gridPane.getColumnConstraints().addAll(col1, col2,col3,col4, col5,col6,col7,col8,col9,col10,col11,col12,col13,col14,col15);

				// Create a rectangle with a background color to act as a separator


				gridPane.getChildren().addAll(lab1, separator1,lab2,separator2,start,separator3,startreason[i-1],separator4,  enddateTimeBox,separator5, endreason[i-1],separator6,outTimeBox,separator7,statusbox[i-1]);
				// verticaldata.getChildren().add(gridPane);
				body.getChildren().add(gridPane);

			}



		}
// Set the preferred height of the VBox
		double vboxHeight = 310; // Set the desired height
		body.setPrefHeight(vboxHeight);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(body);
		 scrollPane.setFitToWidth(true); // Ensure the scroll pane adjusts to the width of its content
		scrollPane.setFitToHeight(true); // Ensure the scroll pane adjusts to the height of its content

		verticaldata.getChildren().add(scrollPane);

		submit=new Button("Submit");
		submit.setOnAction(e->{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Add Confirmation");
			alert.setHeaderText("Do you want to add?");
			alert.setContentText("Choose your option.");

			ButtonType addButton = new ButtonType("Add");
			ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(addButton, cancelButton);

			alert.showAndWait().ifPresent(buttonType -> {
				if (buttonType == addButton) {
					LocalDate entryDate = LocalDate.parse(dateTextField.getText().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						for(int i=0;i<length;i++)
						{
							boolean chk=new AddDataController().dataCheck(repositoryManager,entryDate.toString(),employeeList.get(i).getIdNumber());
							if(chk)
							{
								Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
								alert1.setHeaderText("Already "+employeeList.get(i).getName()+" data inputted");
								ButtonType cancelButton1 = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
								alert1.getButtonTypes().setAll(cancelButton1);
								// Add the following line to display the alert
								alert1.showAndWait();
							}
							else {

								LocalDate exitDate =  datePicker[i].getValue();
								// System.out.println(createLocalDateTime(exitDate.getYear(),exitDate.getMonthValue(),exitDate.getDayOfMonth(),endhour[i].getValue(),endminite[i].getValue(),comboBoxend[i].getValue()));
								Integer.parseInt(starthour[i].getText().trim());
								Integer.parseInt(startminite[i].getText().trim());

								AddDataController=new AddDataController();
								try {
									new AddDataController().insertData(repositoryManager,employeeList.get(i).getIdNumber(), employeeList.get(i).getName(),
											createLocalDateTime(entryDate.getYear(),entryDate.getMonthValue(),entryDate.getDayOfMonth(),Integer.parseInt(starthour[i].getText().trim()),Integer.parseInt(startminite[i].getText().trim()),comboBoxstart[i].getValue()),
											startreason[i].getText(),
											createLocalDateTime(exitDate.getYear(),exitDate.getMonthValue(),exitDate.getDayOfMonth(),Integer.parseInt(endhour[i].getText().trim()),Integer.parseInt(endminite[i].getText().trim()),comboBoxend[i].getValue())
											, endreason[i].getText()
											, statusbox[i].getValue(),LocalDateTime.now()
											, Integer.toString(entryDate.getMonthValue()),Integer.toString(entryDate.getYear()),outtimehour[i].getText().trim()+"."+outtimeminute[i].getText().trim(),entryDate.toString(),globalDayStatus);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}


						}

				} else {
					// Cancel logic goes here
					System.out.println("Cancelled.");
				}
			});




		});
		submit.setMinWidth(160);
		submit.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
		HBox sub = new HBox(10,submit);
		VBox.setMargin(sub, new Insets(20, 0, 20, 0)); // Top and bottom margin of 5 pixels
		sub.setAlignment(Pos.CENTER);
		verticaldata.getChildren().add(sub);
		// Ensure the scroll pane adjusts to the height of its content

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
		borderPane.setStyle("-fx-padding: 10 0 200 0;");


		borderPane.setTop(new Label(""));
		borderPane.setBottom(new Label(""));
		borderPane.setCenter(hbox);


		return borderPane;

	}
	private void updateDateTextField(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		dateTextField.setText(date.format(formatter));
		//datePicker11.setValue(date);
	}

	private void incrementDate() {
		LocalDate currentDate = LocalDate.parse(dateTextField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate nextDate = currentDate.plusDays(1);
		updateDateTextField(nextDate);
		datePicker11.setValue(nextDate);
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


	private void decrementDate() {
		LocalDate currentDate = LocalDate.parse(dateTextField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate previousDate = currentDate.minusDays(1);
		datePicker11.setValue(previousDate);
		// Extract year and month separately
		int year = previousDate.getYear();
		Month month = previousDate.getMonth();

		// Print year and month separately
		System.out.println("Year: " + year);
		System.out.println("Month: " + month);

		// Update your UI components accordingly
		updateDateTextField(previousDate);
		labelh1date.setText("Daily Attendance Sheet-"+year);
		labelh2date.setText("Month : "+month);
		labelh3date.setText("   Date: " + previousDate.toString());
	}


	private void search(String keyword) {
		ObservableList<String> searchResults = FXCollections.observableArrayList();

		for (String name : names) {
			if (name.toLowerCase().contains(keyword.toLowerCase())) {
				searchResults.add(name);
			}
		}

		listView.setItems(searchResults);
	}

	private Label createBorderedLabel(String text) {
		Label label = new Label(text);
		label.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 5px;");
		return label;
	}

	public static class DataRow {
		private String headerData;
		private String data1;
		private String data2;

		public DataRow(String headerData, String data1, String data2) {
			this.headerData = headerData;
			this.data1 = data1;
			this.data2 = data2;
		}

		public String getHeaderData() {
			return headerData;
		}

		public String getData1() {
			return data1;
		}

		public String getData2() {
			return data2;
		}
	}
}
