package org.example.spring.view;

import java.awt.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HomeView {
	private ObservableList<String> names;
    private ListView<String> listView;
    private TextField searchField;
    private TextField dateTextField;
    int[] id=new int[500];
    Spinner<Integer>[] starthour = new Spinner[500];
    Spinner<Integer>[] startminite = new Spinner[500];
    Spinner<String>[] startampm = new Spinner[500];
     
	 Tab createTab(String tabTitle) {
		
		 
		 Dimension screenSize = new Dimension(1400,700);
	        // Create labels for different panes
	        Label label1 = new Label("");
	        Label label2 = new Label("");

	        // Create nested StackPane for the left section
	        StackPane leftStackPane = new StackPane();
	        leftStackPane.setPrefWidth(0.1 * screenSize.getWidth()-70); // 30% of the total width
	        leftStackPane.getChildren().add(label2);

	        // Create nested StackPane for the center section
	        StackPane centerStackPane = new StackPane();
	        centerStackPane.setPrefWidth(0.8 *screenSize.getWidth()-70); // 40% of the total width
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
	        verticaldata.getChildren().add(root);
	        
	      
	    

	        HBox header = new HBox(); // HBox is a horizontal box
	        header.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");
	     // Center the label within the HBox
	        header.setAlignment(Pos.CENTER);
	        Label labelh1 = new Label("Daily Attendance Sheet-2024");
	       // header.setMinWidth(400);
	        header.getChildren().addAll(
	        		labelh1  
		        );
		        
		   verticaldata.getChildren().add(header);
		   
	        GridPane gridPane = new GridPane();
	        VBox.setMargin(gridPane, new Insets(0, 0, 20, 0)); // Top and bottom margin of 5 pixels
	        gridPane.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px; -fx-padding: 10px;");

	        gridPane.setHgap(0); // Horizontal gap between columns
	        gridPane.setVgap(10); // Vertical gap between rows
	        gridPane.setPadding(new Insets(10)); // Padding around the whole grid

	        // Adding nodes to the GridPane
	        Label labelh2 = new Label("Month: January");
	        GridPane.setConstraints(labelh2, 0, 0); // (column, row)

	        Label labelh3 = new Label("    Date:09.01.2024");
	        GridPane.setConstraints(labelh3, 1, 0);

	     // Set ColumnConstraints to make the first and third columns take up equal width
	        ColumnConstraints column1 = new ColumnConstraints();
	        column1.setPercentWidth(50);
	        
	        ColumnConstraints column3 = new ColumnConstraints();
	        column3.setPercentWidth(50);

	        gridPane.getColumnConstraints().addAll(column1, column3);

	        // Create a rectangle with a background color to act as a separator
	        Rectangle separator = new Rectangle(1, 20, Color.BLACK);
	        GridPane.setConstraints(separator, 1, 0);
	        
	        gridPane.getChildren().addAll(labelh2, separator, labelh3);
	        
	        verticaldata.getChildren().add(gridPane);
	       
	       for(int i=0;i<5;i++)
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
			        
			        Label lab7 = new Label("Status");
			        GridPane.setConstraints(lab7, 12, 0);
			       

			     // Set ColumnConstraints to make the first and third columns take up equal width
			        ColumnConstraints col1 = new ColumnConstraints();
			        col1.setPercentWidth(10);
			        
			        ColumnConstraints col2 = new ColumnConstraints();
			        col2.setPercentWidth(5);
			        
			        ColumnConstraints col3 = new ColumnConstraints();
			        col3.setPercentWidth(10);
			        
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
			        col9.setPercentWidth(30);
			        
			        ColumnConstraints col10 = new ColumnConstraints();
			        col10.setPercentWidth(5);
			        
			        
			        ColumnConstraints col11 = new ColumnConstraints();
			        col11.setPercentWidth(10);
			        
			        ColumnConstraints col12 = new ColumnConstraints();
			        col12.setPercentWidth(5);
			        
			        ColumnConstraints col13 = new ColumnConstraints();
			        col13.setPercentWidth(15);
			        
			        
			        
			     // Set alignment for labels
			        GridPane.setHalignment(lab1, HPos.CENTER);
			        GridPane.setHalignment(lab2, HPos.CENTER);
			        GridPane.setHalignment(lab3, HPos.CENTER);
			        GridPane.setHalignment(lab4, HPos.CENTER);
			        GridPane.setHalignment(lab5, HPos.CENTER);
			        GridPane.setHalignment(lab6, HPos.CENTER);
			        GridPane.setHalignment(lab7, HPos.CENTER);
			        
			     // Set alignment for separators
			        GridPane.setHalignment(separator1, HPos.CENTER);
			        GridPane.setHalignment(separator2, HPos.CENTER);
			        GridPane.setHalignment(separator3, HPos.CENTER);
			        GridPane.setHalignment(separator4, HPos.CENTER);
			        GridPane.setHalignment(separator5, HPos.CENTER);
			        GridPane.setHalignment(separator6, HPos.CENTER);

			        gridPane.getColumnConstraints().addAll(col1, col2,col3,col4, col5,col6,col7,col8,col9,col10,col11,col12,col13);

			        // Create a rectangle with a background color to act as a separator
			     
			        
			        gridPane.getChildren().addAll(lab1, separator1,lab2,separator2, lab3,separator3, lab4,separator4, lab5,separator5, lab6,separator6, lab7);
			        verticaldata.getChildren().add(gridPane);
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
			         
			        
			       Label lab2 =new Label("name");
			        GridPane.setConstraints(lab2, 2, 0);
			        
			        
			        Rectangle separator2 = new Rectangle(1, 30, Color.BLACK);
			        GridPane.setConstraints(separator2, 3, 0);
			        
			        Spinner<Integer> starthourSpinner = new Spinner<>(0, 23, 0);
			        starthourSpinner.setMaxWidth(55);
			         starthourSpinner.setEditable(true);

			         // Create a Spinner for minutes
			         Spinner<Integer> startminuteSpinner = new Spinner<>(0, 59, 0);
			         startminuteSpinner.setMaxWidth(55);
			         startminuteSpinner.setEditable(true);
			         
			         ComboBox<String> comboBoxst1 = new ComboBox<>();
			         comboBoxst1.setMaxWidth(62);
			         comboBoxst1.getItems().addAll("AM", "PM");
			         comboBoxst1.setValue("AM"); // Set a default value if needed

			         // Create an HBox to hold the Date and Time components
			         HBox start = new HBox(2, starthourSpinner, startminuteSpinner,comboBoxst1);
			         
			         
			        
			       // TextField lab3 = new TextField(" reson");
			        GridPane.setConstraints(start, 4, 0);
			        
			        Rectangle separator3 = new Rectangle(1, 30, Color.BLACK);
			        GridPane.setConstraints(separator3, 5, 0);
			        
			        
			        
			     

			        
			        TextField lab4 = new TextField(" reson");
			        GridPane.setConstraints(lab4, 6, 0);
			        
			        Rectangle separator4 = new Rectangle(1, 30, Color.BLACK);
			        GridPane.setConstraints(separator4, 7, 0);
			        
			        
			     // Create a Spinner for hours
			         Spinner<Integer> endhourSpinner = new Spinner<>(0, 23, 0);
			         endhourSpinner.setEditable(true);
			         endhourSpinner.setMaxWidth(55);

			         // Create a Spinner for minutes
			         Spinner<Integer> endminuteSpinner = new Spinner<>(0, 59, 0);
			         endminuteSpinner.setEditable(true);
			         endminuteSpinner.setMaxWidth(55);
			         
			         ComboBox<String> comboBoxst2 = new ComboBox<>();
			         comboBoxst2.getItems().addAll("AM", "PM");
			         comboBoxst2.setValue("PM"); // Set a default value if needed
			         comboBoxst2.setMaxWidth(62);
			         
			         
			         // Create an HBox to hold the Date and Time components
			         HBox enddateTimeBox = new HBox(0, endhourSpinner, endminuteSpinner,comboBoxst2);
			        
			        //TextField lab5 = new TextField(" reson");
			        GridPane.setConstraints(enddateTimeBox, 8, 0);
			        
			        Rectangle separator5 = new Rectangle(1, 30, Color.BLACK);
			        GridPane.setConstraints(separator5, 9, 0);
			        
			        TextField lab6 = new TextField(" Reson");
			        GridPane.setConstraints(lab6, 10, 0);
			        
			        
			        Rectangle separator6 = new Rectangle(1, 30, Color.BLACK);
			        GridPane.setConstraints(separator6, 11, 0);
			        
			        Label lab7 = new Label(" Date");
			          ComboBox<String> comboBoxst3 = new ComboBox<>();
			         comboBoxst3.getItems().addAll("Present", "Absent","Leave","Holyday");
			         comboBoxst3.setValue("Present"); // Set a default value if needed
			         comboBoxst3.setMaxWidth(100);
			        GridPane.setConstraints(comboBoxst3, 12, 0);
			       

			     // Set ColumnConstraints to make the first and third columns take up equal width
			        ColumnConstraints col1 = new ColumnConstraints();
			        col1.setPercentWidth(10);
			        
			        ColumnConstraints col2 = new ColumnConstraints();
			        col2.setPercentWidth(5);
			        
			        ColumnConstraints col3 = new ColumnConstraints();
			        col3.setPercentWidth(10);
			        
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
			        col9.setPercentWidth(30);
			        
			        ColumnConstraints col10 = new ColumnConstraints();
			        col10.setPercentWidth(5);
			        
			        
			        ColumnConstraints col11 = new ColumnConstraints();
			        col11.setPercentWidth(10);
			        
			        ColumnConstraints col12 = new ColumnConstraints();
			        col12.setPercentWidth(5);
			        
			        ColumnConstraints col13 = new ColumnConstraints();
			        col13.setPercentWidth(15);
			        
			        
			     // Set alignment for labels
			        GridPane.setHalignment(lab1, HPos.CENTER);
			        GridPane.setHalignment(lab2, HPos.CENTER);
			        GridPane.setHalignment(start, HPos.CENTER);
			        GridPane.setHalignment(lab4, HPos.CENTER);
			        GridPane.setHalignment(enddateTimeBox, HPos.CENTER);
			        GridPane.setHalignment(lab6, HPos.CENTER);
			        GridPane.setHalignment(comboBoxst3, HPos.CENTER);
			        
			     // Set alignment for separators
			        GridPane.setHalignment(separator1, HPos.CENTER);
			        GridPane.setHalignment(separator2, HPos.CENTER);
			        GridPane.setHalignment(separator3, HPos.CENTER);
			        GridPane.setHalignment(separator4, HPos.CENTER);
			        GridPane.setHalignment(separator5, HPos.CENTER);
			        GridPane.setHalignment(separator6, HPos.CENTER);
			        
			        

			        gridPane.getColumnConstraints().addAll(col1, col2,col3,col4, col5,col6,col7,col8,col9,col10,col11,col12,col13);

			        // Create a rectangle with a background color to act as a separator
			     
			       
			        gridPane.getChildren().addAll(lab1, separator1,lab2,separator2,start,separator3,lab4,separator4,  enddateTimeBox,separator5, lab6,separator6, comboBoxst3);
			        verticaldata.getChildren().add(gridPane); 
			       
	    	   }
	    	   
	    	  
		       
	       }
	      
	        
	        names = FXCollections.observableArrayList(
	                "Name 1", "Name 2", "Name 3", "Name 4", "Name 5",
	                "Name 6", "Name 7", "Name 8", "Name 9", "Name 10");

	        listView = new ListView<>(names);
	        listView.setPrefHeight(20); // Set the preferred height here
	        listView.setVisible(false); // Hide the ListView
	        searchField = new TextField();
	       
	     // Add a listener to the selection model of the ListView
	        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue != null) {
	                searchField.setText(newValue);
	                listView.setVisible(false); // Hide the ListView
	                listView.setPrefHeight(20);
	                 
	            }
	        });

	        searchField.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
	            // Code to execute when the TextField is clicked
	        	 listView.setVisible(true); // Hide the ListView
	        	 listView.setPrefHeight(100); // Set the preferred height here
	        });
	        // Add a listener to the text property of the search field
	        searchField.textProperty().addListener((observable, oldValue, newValue) -> search(newValue));

	        VBox vbox = new VBox(searchField, listView);
	        
	        verticaldata.getChildren().add(vbox);
	        
	     // Create a DatePicker
	        DatePicker datePicker = new DatePicker();
	     // Set the minimum width of the DatePicker
	        datePicker.setMinWidth(320);
	        // Optional: Set an initial date
	         datePicker.setValue(LocalDate.now());
	         
	         HBox date= new HBox(10,new Button("Date        :  "), datePicker);
	         VBox.setMargin(date, new Insets(5, 0, 20, 0)); // Top and bottom margin of 5 pixels
	        // date.setAlignment(Pos.CENTER);
	         verticaldata.getChildren().add(date);
	         
	         
	         
	         
	         // Create a Spinner for hours
	         Spinner<Integer> starthourSpinner = new Spinner<>(0, 23, 0);
	         starthourSpinner.setMaxWidth(120);
	         starthourSpinner.setEditable(true);

	         // Create a Spinner for minutes
	         Spinner<Integer> startminuteSpinner = new Spinner<>(0, 59, 0);
	         startminuteSpinner.setMaxWidth(120);
	         startminuteSpinner.setEditable(true);
	         
	         ComboBox<String> comboBoxst1 = new ComboBox<>();
	         comboBoxst1.getItems().addAll("AM", "PM");
	         comboBoxst1.setValue("AM"); // Set a default value if needed

	         // Create an HBox to hold the Date and Time components
	         HBox startdateTimeBox = new HBox(10,new Button("Start(H:M):"), starthourSpinner, startminuteSpinner,comboBoxst1);
	         VBox.setMargin(startdateTimeBox, new Insets(5, 0, 20, 0)); // Top and bottom margin of 5 pixel
	        // dateTimeBox.setAlignment(Pos.CENTER);
	         
	         verticaldata.getChildren().add(startdateTimeBox);
	         
	         // Create a Spinner for hours
	         Spinner<Integer> endhourSpinner = new Spinner<>(0, 23, 0);
	         endhourSpinner.setEditable(true);
	         endhourSpinner.setMaxWidth(120);

	         // Create a Spinner for minutes
	         Spinner<Integer> endminuteSpinner = new Spinner<>(0, 59, 0);
	         endminuteSpinner.setEditable(true);
	         endminuteSpinner.setMaxWidth(120);
	         
	         ComboBox<String> comboBoxst2 = new ComboBox<>();
	         comboBoxst2.getItems().addAll("AM", "PM");
	         comboBoxst2.setValue("PM"); // Set a default value if needed

	         // Create an HBox to hold the Date and Time components
	         HBox enddateTimeBox = new HBox(10,new Button("End(H:M):  "), endhourSpinner, endminuteSpinner,comboBoxst2);
	         VBox.setMargin(enddateTimeBox, new Insets(5, 0, 20, 0)); // Top and bottom margin of 5 pixel

	        // dateTimeBox.setAlignment(Pos.CENTER);
	         
	         verticaldata.getChildren().add(enddateTimeBox);
	         
	         
	         
	         Button submit=new Button("Submit");
	          submit.setMinWidth(160);
	          submit.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	         HBox sub = new HBox(10,submit);
	         sub.setAlignment(Pos.CENTER);
	         verticaldata.getChildren().add(sub);
	         
	         
            centerStackPane.getChildren().add(verticaldata);
            
            
	        // Create nested StackPane for the right section
	        StackPane rightStackPane = new StackPane();
	        rightStackPane.setPrefWidth(0.1 * screenSize.getWidth()-70); // 30% of the total width
	        rightStackPane.getChildren().add(new Label(""));
	       

	        // Create an HBox to hold the left, center, and right sections
	        HBox hbox = new HBox();
	        hbox.getChildren().addAll(leftStackPane, centerStackPane, rightStackPane);

	        // Create a BorderPane and set the HBox in the center
	        BorderPane borderPane = new BorderPane();
	     // Set padding using CSS
	        borderPane.setStyle("-fx-padding: 100 0 0 0;");
	        
	        
	        borderPane.setTop(new Label(""));
	        borderPane.setBottom(new Label(""));
	        borderPane.setCenter(hbox);


	        // Create a new tab with the specified title and content
	        Tab tab = new Tab(tabTitle);


	        tab.setContent(borderPane);
	        tab.setClosable(false); // Optional: Disable tab closing

	        return tab;
	    }
	 private void updateDateTextField(LocalDate date) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        dateTextField.setText(date.format(formatter));
	    }

	    private void incrementDate() {
	        LocalDate currentDate = LocalDate.parse(dateTextField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        LocalDate nextDate = currentDate.plusDays(1);
	        updateDateTextField(nextDate);
	    }

	    private void decrementDate() {
	        LocalDate currentDate = LocalDate.parse(dateTextField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        LocalDate previousDate = currentDate.minusDays(1);
	        updateDateTextField(previousDate);
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
