package org.example.spring.view;

import java.awt.Dimension;

import java.util.List;

import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.util.converter.DefaultStringConverter;
import   org.example.spring.controller.AddEmployeeController;
import org.example.spring.model.EmployeeData;
import org.example.spring.model.Position;
import org.example.spring.repository.RepositoryManager;


import static java.lang.System.out;

public class PositionView {
	String pos;
	List<Position> positionData;
	RepositoryManager repositoryManager;
	AddEmployeeController AddEmployeeController;
	TableView<Person> tableView = new TableView<>();
    TextField txtname = new TextField();
    int k;
    TextField txtid = new TextField();
    TextField txtdesination = new TextField();
    TableColumn<Person, String> column1 = new TableColumn<>("ID");
    
    TableColumn<Person, String> column2 = new TableColumn<>("Name");
    TableColumn<Person, String> column4 = new TableColumn<>("Designation");
    TableColumn<Person, String> column3 = new TableColumn<>("Position");
    
    public PositionView()
    {
    	 AddEmployeeController =new AddEmployeeController();
    }
	List<EmployeeData> employeeList;
    BorderPane createTab(RepositoryManager repositoryManager1, List<Position> positionData1, List<EmployeeData> employeeList1) {
		employeeList=employeeList1;
		positionData=positionData1;
		repositoryManager=repositoryManager1;
		Dimension screenSize = new Dimension(1300,650);
	        // Create labels for different panes
	        Label label1 = new Label("");
	        Label label2 = new Label("");

	        // Create nested StackPane for the left section
	        StackPane leftStackPane = new StackPane();
	        leftStackPane.setPrefWidth(0.2 * screenSize.getWidth()-70); // 30% of the total width
	       // leftStackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
	        leftStackPane.getChildren().add(label2);

	        // Create nested StackPane for the center section
	        StackPane centerStackPane = new StackPane();
	        centerStackPane.setPrefWidth(0.6 *screenSize.getWidth()-70); // 40% of the total width
	       // centerStackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
	       // centerStackPane.setStyle("-fx-border-color: black; -fx-border-width: 2;");
	       
	       
	        VBox verticaldata=new VBox();
	        
	        
	        
	         
	         // Create the "date" column
	         column1.setCellFactory(TextFieldTableCell.forTableColumn());
	         column1.setCellValueFactory(new PropertyValueFactory<>("firstId"));
	         column1.setOnEditCommit(event -> {
	             Person person = event.getRowValue();
	             person.setFirstId(event.getNewValue());
	             // Update the data in your database or controller if needed
	             // AddEmployeeController.updateDesignation(person.getFirstId(), event.getNewValue());
	         });
	         
	         
	         

	      // Create the "Name" column
	         column2.setCellFactory(TextFieldTableCell.forTableColumn());
	         column2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	         column2.setOnEditCommit(event -> {
	             Person person = event.getRowValue();
	             person.setFirstName(event.getNewValue());
	             // Update the data in your database or controller if needed
	             // AddEmployeeController.updateName(person.getFirstId(), event.getNewValue());
	         });
	         
	         
	         
	         // Create the "date" column
	         column3.setCellFactory(TextFieldTableCell.forTableColumn());
	         column3.setCellValueFactory(new PropertyValueFactory<>("position"));
	         column3.setOnEditCommit(event -> {

				 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				 alert.setTitle("Add Confirmation");
				 alert.setHeaderText("Do you want to change position?");
				 alert.setContentText("Choose your option.");

				 ButtonType addButton = new ButtonType("Change");
				 ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

				 alert.getButtonTypes().setAll(addButton, cancelButton);

				 alert.showAndWait().ifPresent(buttonType -> {
					 if (buttonType == addButton) {
						 // Add logic goes here
						 Person person = event.getRowValue();
						 person.setPosition(event.getNewValue());
						 out.println(person.firstId+" "+person.getPosition());
						 employeeList= new AddEmployeeController().updatesorting(repositoryManager,employeeList,person );
						 tableView.getItems().clear();
						 employeeList.forEach(e->{

							 tableView.getItems().add(new Person(e.getIdNumber(),e.getName(),e.getPosition(),e.getDesignation()));
							 k++;
						 });
					 } else {
						 // Cancel logic goes here
						 System.out.println("Cancelled.");
					 }
				 });

	             // Update the data in your database or controller if needed
	             // AddEmployeeController.updateDesignation(person.getFirstId(), event.getNewValue());
	         });
	         
	         
	         
	      // Create the "Designation" column
	         column4.setCellFactory(TextFieldTableCell.forTableColumn());
	         column4.setCellValueFactory(new PropertyValueFactory<>("designation"));
	         column4.setOnEditCommit(event -> {
	             Person person = event.getRowValue();
	             person.setDesignation(event.getNewValue());
	             // Update the data in your database or controller if needed
	             // AddEmployeeController.updateDesignation(person.getFirstId(), event.getNewValue());
	         });
	        
	      // Set minimum widths for each column
	         column1.setMinWidth(200);
	         column2.setMinWidth(280);
	         column3.setMinWidth(250);
	         column4.setMinWidth(280);
	         
	         setCenterAlignment(column1);
	         setCenterAlignment(column2);
	         setCenterAlignment(column3);
	         setCenterAlignment(column4);
	         tableView.getColumns().addAll(column1, column2, column3);
	         
	         // showing employee information
	        // List<EmployeeData> employeeList = AddEmployeeController.retrieveData();
	             k=1;
	         employeeList.forEach(e->{
	        	 
	        	 tableView.getItems().add(new Person(e.getIdNumber(),e.getName(),e.getPosition(),e.getDesignation()));
	        	 k++;
	         });
	        // tableView.getItems().add(new Person("1","John sadd rdyte ry6 mty", "2023-07-21", "sfv "));
	         //tableView.getItems().add(new Person("2","Jane", "2023-07-28", "db d"));
	         tableView.setEditable(true);  // Make the table editable
	         verticaldata.getChildren().add(tableView);
	        
	      
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
	       // Tab tab = new Tab(tabTitle);


	       // tab.setContent(borderPane);
	       // tab.setClosable(false); // Optional: Disable tab closing

	        return borderPane;
	    }
    private void setCenterAlignment(TableColumn<Person, String> column) {
        column.setCellFactory(tc -> new TextFieldTableCell<>(new DefaultStringConverter()) {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER); // Center-align the text
                }
            }
        });
    }
   public String position(String id,String name){
		pos="";
	   positionData.forEach(e->{
		   if(e.getEmployeeId().equals(id)&& e.getName().equals(name)){
			   pos=e.getPosition();
		   }
	   });

	   return pos;
   }

	 public static class Person {
	        private String firstName = null;
	        private String firstId = null;
	        private String position = null;
	        private String designation = null;

	        public Person() {
	        }

	        public Person(String id,String firstName, String position, String designation) {
	           this.firstId=id;
	        	this.firstName = firstName;
	            this.position = position;
	            this.designation = designation;
	        }

	        public String getFirstName() {
	            return firstName;
	        }

	        public void setFirstId(String firstId) {
	            this.firstId = firstId;
	        }
	        
	        public String getFirstId() {
	            return firstId;
	        }

	        public void setFirstName(String firstName) {
	            this.firstName = firstName;
	        }

	        public String getPosition() {
	            return position;
	        }

	        public void setPosition(String position) {
	            this.position= position;
	        }

	        public String getDesignation() {
	            return designation;
	        }

	        public void setDesignation(String designation) {
	            this.designation = designation;
	        }
	    }
}
