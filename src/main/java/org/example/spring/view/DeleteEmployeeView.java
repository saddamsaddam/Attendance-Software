package org.example.spring.view;

import java.awt.Dimension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.scene.control.*;
import org.example.spring.controller.*;

import org.example.spring.model.EmployeeData;
import org.example.spring.repository.RegistrationRepository;
import org.example.spring.repository.RepositoryManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class DeleteEmployeeView {
	String position;
	RegistrationRepository registrationRepository;
    String idd="";
	  DatePicker datePicker;
	private ObservableList<String> names;
    private ListView<String> listView;
    private TextField searchField;
    private  DeleteEmployeeController DeleteEmployeeController;
	List<EmployeeData> employeeList;
	RepositoryManager repositoryManager;
	 BorderPane createTab(RepositoryManager repositoryManager1, List<EmployeeData> employeeList1) {
		 employeeList=employeeList1;
		 repositoryManager=repositoryManager1;
		 Dimension screenSize = new Dimension(1300,650);
	        // Create labels for different panes
	        Label label1 = new Label("");
	        Label label2 = new Label("");

	        // Create nested StackPane for the left section
	        StackPane leftStackPane = new StackPane();
	        leftStackPane.setPrefWidth(0.3 * screenSize.getWidth()-70); // 30% of the total width
	        leftStackPane.getChildren().add(label2);

	        // Create nested StackPane for the center section
	        StackPane centerStackPane = new StackPane();
	        centerStackPane.setPrefWidth(0.4 *screenSize.getWidth()-70); // 40% of the total width
	        //centerStackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
	       // centerStackPane.setStyle("-fx-border-color: black; -fx-border-width: 2;");
	       
	       
	        VBox verticaldata=new VBox();
	        names = nameare(employeeList);

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
	        datePicker = new DatePicker();
	     // Set the minimum width of the DatePicker
	        datePicker.setMinWidth(355);
	        // Optional: Set an initial date
	         datePicker.setValue(LocalDate.now());
	         
	         HBox adddata = new HBox(3,new Label("End Date"),datePicker);
	         VBox.setMargin(adddata, new Insets(5, 0, 20, 0)); // Top and bottom margin of 5 pixel
	         verticaldata.getChildren().add(adddata);
	         
	         Button submit=new Button("Delete");
	          submit.setMinWidth(160);
	          submit.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	          submit.setOnAction(event ->{
				  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				  alert.setTitle("Add Confirmation");
				  alert.setHeaderText("Do you want to Delete?");
				  alert.setContentText("Choose your option.");

				  ButtonType addButton = new ButtonType("Delete");
				  ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

				  alert.getButtonTypes().setAll(addButton, cancelButton);

				  alert.showAndWait().ifPresent(buttonType -> {
					  if (buttonType == addButton) {
						  // Add logic goes here
						  deleteSelectedItem();
					  } else {
						  // Cancel logic goes here
						  System.out.println("Cancelled.");
					  }
				  });

			  } );
	          
	         HBox sub = new HBox(10,submit);
	         sub.setAlignment(Pos.CENTER);
	         verticaldata.getChildren().add(sub);
	         
	         
            centerStackPane.getChildren().add(verticaldata);
            
            
	        // Create nested StackPane for the right section
	        StackPane rightStackPane = new StackPane();
	        rightStackPane.setPrefWidth(0.325 * screenSize.getWidth()-70); // 30% of the total width
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
	       // Tab tab = new Tab(tabTitle);


	        //tab.setContent(borderPane);
	       // tab.setClosable(false); // Optional: Disable tab closing

	        return borderPane;
	    }
	 private void deleteSelectedItem() {
	        String selectedItem = searchField.getText();
	        
	        LocalDate selectedDate = datePicker.getValue();
	        
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = selectedDate.format(formatter);
    
            List<EmployeeData> employeeList = AddEmployeeController.retrieveData(repositoryManager);
	   position="0";
	        employeeList.forEach(e -> {
	           if(e.getName().equals(selectedItem))
	           {
	        	  idd=e.getIdNumber() ;
				   position=e.getPosition();
	           }
	           
	        });
	        
	        DeleteEmployeeController=new  DeleteEmployeeController();
	        DeleteEmployeeController.delete(repositoryManager,idd, selectedItem, formattedDate,position);
	        
	        names.remove(selectedItem);
	        searchField.clear(); // Clear the search field after deletion
	        listView.setItems(names);
	    }
	 public ObservableList<String> nameare(List<EmployeeData> employeeList) {
	        ObservableList<String> name = FXCollections.observableArrayList();

	        // Showing employee information
	       
	        employeeList.forEach(e -> {
	            name.add(e.getName());
	        });

	        return name;
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
}
