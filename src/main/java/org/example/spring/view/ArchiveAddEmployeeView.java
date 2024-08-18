package org.example.spring.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.DefaultStringConverter;
import org.example.spring.controller.AddEmployeeController;
import org.example.spring.model.EmployeeData;
import org.example.spring.repository.RepositoryManager;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class ArchiveAddEmployeeView {
	RepositoryManager repositoryManager;
	AddEmployeeController AddEmployeeController;
	TableView<Person> tableView = new TableView<>();
	TextField txtname = new TextField();
	DatePicker datePicker;
	int cc;
	TextField txtid = new TextField();
	TextField txtdesination = new TextField();
	TableColumn<Person, String> column1 = new TableColumn<>("ID");

	TableColumn<Person, String> column2 = new TableColumn<>("Name");
	TableColumn<Person, String> column4 = new TableColumn<>("Designation");
	TableColumn<Person, String> column3 = new TableColumn<>("Join Date");
	TableColumn<Person, String> column5 = new TableColumn<>("Edit");

	public ArchiveAddEmployeeView()
	{

		AddEmployeeController =new AddEmployeeController();
	}
	List<EmployeeData> employeeList;

	BorderPane createTab(RepositoryManager repositoryManager1 , List<EmployeeData> employeeList1) {
		employeeList=employeeList1;
		repositoryManager=repositoryManager1;
		Dimension screenSize = new Dimension(1300,650);
		// Create labels for different panes
		Label label1 = new Label("");
		Label label2 = new Label("");

		// Create nested StackPane for the left section
		StackPane leftStackPane = new StackPane();
		leftStackPane.setPrefWidth(0.1 * screenSize.getWidth()-70); // 30% of the total width
		// leftStackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		leftStackPane.getChildren().add(label2);

		// Create nested StackPane for the center section
		StackPane centerStackPane = new StackPane();
		centerStackPane.setPrefWidth(0.9 *screenSize.getWidth()-70); // 40% of the total width
		// centerStackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		// centerStackPane.setStyle("-fx-border-color: black; -fx-border-width: 2;");


		VBox verticaldata=new VBox();

		txtid.setMinWidth(180);
		txtid.setStyle("-fx-background-color: white; -fx-text-fill: black;");

		txtname.setMinWidth(180);
		txtname.setStyle("-fx-background-color: white; -fx-text-fill: black;");




		txtdesination.setMinWidth(180);
		txtdesination.setStyle("-fx-background-color: white; -fx-text-fill: black;");

		// Create a DatePicker
		 datePicker = new DatePicker();
		// Set the minimum width of the DatePicker
		datePicker.setMaxWidth(125);
		// Optional: Set an initial date
		datePicker.setValue(LocalDate.now());



		Button submit=new Button("Add");
		submit.setMinWidth(100);
		submit.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
		HBox adddata = new HBox(3,new Label("ID"),txtid,new Label("Name"),txtname,new Label("Designation"),txtdesination,new Label("Join Date"),datePicker,submit);
		VBox.setMargin(adddata, new Insets(5, 0, 20, 0)); // Top and bottom margin of 5 pixel
		submit.setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Add Confirmation");
			alert.setHeaderText("Do you want to add?");
			alert.setContentText("Choose your option.");

			ButtonType addButton = new ButtonType("Add");
			ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(addButton, cancelButton);

			alert.showAndWait().ifPresent(buttonType -> {
				if (buttonType == addButton) {
					String newPersonName = txtname.getText().trim();
					String des = txtdesination.getText().trim();
					String id = txtid.getText().trim();
					LocalDate selectedDate = datePicker.getValue();
					boolean nameExists = tableView.getItems().stream()
							.anyMatch(person -> person.getFirstId().equalsIgnoreCase(id));

					if (!nameExists && newPersonName.length() > 0&&des.length()>0&&id.length()>0) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						String formattedDate = selectedDate.format(formatter);
						tableView.getItems().add(new Person(id,newPersonName, formattedDate, des,"Edit"));


						AddEmployeeController.insert(repositoryManager,id,newPersonName, formattedDate, des,tableView.getItems().size());

						txtdesination.setText("");
						txtid.setText("");
						txtname.setText("");
						datePicker.setValue(LocalDate.now());

					} else {
						System.out.println("Name already exists in the table.");
					}

				} else {
					// Cancel logic goes here
					System.out.println("Cancelled.");
				}
			});


		});

		//verticaldata.getChildren().add(adddata);


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

		});



		// Create the "date" column
		column3.setCellFactory(TextFieldTableCell.forTableColumn());
		column3.setCellValueFactory(new PropertyValueFactory<>("date"));
		column3.setOnEditCommit(event -> {
			Person person = event.getRowValue();
			person.setDesignation(event.getNewValue());
			// Update the data in your database or controller if needed
			// AddEmployeeController.updateDesignation(person.getFirstId(), event.getNewValue());
		});



		// Create the "Designation" column
		column4.setCellFactory(TextFieldTableCell.forTableColumn());
		column4.setCellValueFactory(new PropertyValueFactory<>("designation"));
		column4.setOnEditCommit(event -> {
			Person person = event.getRowValue();
			person.setDesignation(event.getNewValue());

		});

		column5.setCellFactory(TextFieldTableCell.forTableColumn());
		column5.setCellValueFactory(new PropertyValueFactory<>("edit"));

		column5.setEditable(false); // Make column 5 non-editable

// Add this line after initializing the TableView
		tableView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 1) { // Check if the click count is 1 (single click)
				int rowIndex = tableView.getSelectionModel().getSelectedIndex();
				TableColumn<?, ?> selectedColumn = tableView.getColumns().get(tableView.getSelectionModel().getSelectedCells().get(0).getColumn());
				int columnIndex = tableView.getColumns().indexOf(selectedColumn);
                if(columnIndex==4)
				{
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setTitle("Add Confirmation");
					alert.setHeaderText("Do you want to Edit?");
					alert.setContentText("Choose your option.");

					ButtonType addButton = new ButtonType("Edit");
					ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

					alert.getButtonTypes().setAll(addButton, cancelButton);

					alert.showAndWait().ifPresent(buttonType -> {
								if (buttonType == addButton) {
									cc=0;
									Person selectedPerson = tableView.getSelectionModel().getSelectedItem(); // Get the selected person
									if (selectedPerson != null) {
										employeeList.forEach(e->{
											if(cc==rowIndex)
											{
												updateRow(e,selectedPerson);
											}
											cc++;
										});


									}

								} else {
									// Cancel logic goes here
									System.out.println("Cancelled.");
								}
							});

				}

			}
		});

		// Set minimum widths for each column
		column1.setMinWidth(200);
		column2.setMinWidth(250);
		column3.setMinWidth(220);
		column4.setMinWidth(280);
		column5.setMinWidth(150);

		setCenterAlignment(column1);
		setCenterAlignment(column2);
		setCenterAlignment(column3);
		setCenterAlignment(column4);
		setCenterAlignment(column5);
		//tableView.getColumns().addAll(column1, column2, column3,column4,column5);
		tableView.getColumns().addAll(column1, column2, column3,column4);

		// showing employee information
		// List<EmployeeData> employeeList = AddEmployeeController.retrieveData();
		employeeList.forEach(e->{

			tableView.getItems().add(new Person(e.getIdNumber(),e.getName(),e.getJoinDate(),e.getDesignation(),"Edit"));
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
	public void updateRow(EmployeeData employeeData, Person person)
	{
System.out.println("id2: "+person.getFirstId());
		 new AddEmployeeController().updateData(repositoryManager,employeeData,person.getFirstId(),person.getFirstName(),person.getDate(),person.getDesignation());
         //public void updateData(RepositoryManager repositoryManager, EmployeeData exData, String id,String name, String date, String deg  )
		employeeList= org.example.spring.controller.AddEmployeeController.retrieveData(repositoryManager);


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

	public static class Person {
		private String firstName = null;
		private String firstId = null;
		private String date = null;
		private String designation = null;
		private String edit = null;

		public Person() {
		}

		public Person(String id,String firstName, String date, String designation,String edit) {
			this.firstId=id;
			this.firstName = firstName;
			this.date = date;
			this.designation = designation;
			this.edit=edit;
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

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getDesignation() {
			return designation;
		}
		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public String getEdit() {
			return edit;
		}
		public void setEdit(String edit) {
			this.edit = edit;
		}
	}
}
