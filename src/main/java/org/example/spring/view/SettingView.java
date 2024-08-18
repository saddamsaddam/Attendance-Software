package org.example.spring.view;

import java.awt.Dimension;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import org.example.spring.controller.userLocalSettingController;
import org.example.spring.model.EmployeeData;
import org.example.spring.model.UserLocalSetting;
import org.example.spring.repository.RepositoryManager;
import org.example.spring.repository.UserLocalSettingRepository;

import static java.lang.System.out;

public class SettingView {
	int cc=0;
	Button newadd; Button submit;Button update;
	RepositoryManager repositoryManager;
	String selectedEmployeeName;
	String selectedEmployeeIdNumber;

    TextField txtname = new TextField();
    TableView<Person> tableView = new TableView<>();
    TextField txtid = new TextField();
    TextField txtdesination = new TextField();
    int k;
    List<EmployeeData> employeeList;
    public SettingView() throws SQLException
    {

    }
	UserLocalSettingRepository userLocalSettingRepository;
	List<UserLocalSetting> usersettingdata;
    BorderPane createTab(RepositoryManager repositoryManager1, List<UserLocalSetting> usersettingdata1, List<EmployeeData> employeeList1) throws SQLException {
     employeeList=employeeList1;usersettingdata=usersettingdata1;
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
	        centerStackPane.setPrefWidth(0.8 *screenSize.getWidth()-70); // 40% of the total width
	       // centerStackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
	       // centerStackPane.setStyle("-fx-border-color: black; -fx-border-width: 2;");
	       
	       
	        VBox verticaldata=new VBox();
	        
	        // Create a ComboBox
	        ComboBox<String> comboBox = new ComboBox<>();

	        // Add items to the ComboBox
	        ObservableList<String> items = FXCollections.observableArrayList(
	        );
	        employeeList.forEach(e->{
	        	
	        	items.add(e.getName());
	        });
	        comboBox.setItems(items);

	        // Set default selection
	        comboBox.getSelectionModel().selectFirst();
		    selectedEmployeeName= comboBox.getSelectionModel().getSelectedItem();
		    selectedEmployeeIdNumber=selectedid();

		     comboBox.setOnAction(event -> {
				 selectedEmployeeName = comboBox.getSelectionModel().getSelectedItem();
			if (selectedEmployeeName != null) {
				// Print or use the selected employee name as needed
				out.println("Selected Employee: " + selectedEmployeeName);
				selectedEmployeeIdNumber=selectedid();
				setTableData();

			}
		});
	        Button history=new Button("   History    ");
			 newadd=new Button("    New    ");
		    // Adding action listener
			  newadd.setOnAction(event -> {
				  submit.setVisible(true);
				  update.setVisible(false);
				// Place your action code here
				System.out.println("New button clicked!");
                try {
					usersettingdata= new userLocalSettingController().retrieveData(repositoryManager);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
				tableView.getItems().clear();
				for(int i=0;i<usersettingdata.size();i++)
				{
					if(usersettingdata.get(i).getEmployeeId().equals(selectedEmployeeIdNumber))
					{

							tableView.getItems().addAll(
									new Person(generateDate( usersettingdata.get(i).getFormattedDeathDate(),1),generateDate( usersettingdata.get(i).getFormattedDeathDate(),30) ,usersettingdata.get(i).getStartHours(),usersettingdata.get(i).getStartMinute(),"Edit","Delete",usersettingdata.get(i).getEndHours(),usersettingdata.get(i).getEndMinute(),usersettingdata.get(i).getTotalHours(),usersettingdata.get(i).getDesignation())
							);

						break;
					}
				}
            });

	        HBox subcombobox = new HBox(10,comboBox,newadd,history);
	        VBox.setMargin(subcombobox, new Insets(0, 0, 20, 0)); // Top and bottom margin of 5 pixels
	        subcombobox.setAlignment(Pos.TOP_LEFT);
            verticaldata.getChildren().add(subcombobox);
	        
	        tableView.setEditable(true);

	        // Create columns for the TableView
	
	        TableColumn<Person, String> birthDateColumn = new TableColumn<>("Start Date");
	        TableColumn<Person, String> deathDateColumn = new TableColumn<>("End Date");
	          TableColumn<Person, String> startHoursColumn = new TableColumn<>("Start Hours");
	          TableColumn<Person, String> startMinuteColumn = new TableColumn<>("Start Minute");

	          TableColumn<Person, String> endHoursColumn = new TableColumn<>("End Hours");
	          TableColumn<Person, String> endMinuteColumn = new TableColumn<>("End Minute");
	          TableColumn<Person, String> totalHoursColumn = new TableColumn<>("Total Hours");
	          TableColumn<Person, String> deginationColumn = new TableColumn<>("Designation");
		      TableColumn<Person, String> lateHoursColumn = new TableColumn<>("Edit");
		      TableColumn<Person, String> lateMinuteColumn = new TableColumn<>("Delete");
	          
	          
	          birthDateColumn.setMinWidth(150);
	          deathDateColumn.setMinWidth(150);
	          startHoursColumn.setMinWidth(80);
	          startMinuteColumn.setMinWidth(80);
	         
	          endHoursColumn.setMinWidth(80);
	          endMinuteColumn.setMinWidth(80);
	          totalHoursColumn.setMinWidth(80);
		     deginationColumn.setMinWidth(80);

			 lateHoursColumn.setMinWidth(80);
			 lateMinuteColumn.setMinWidth(80);

	         

	        // Set cell value factories for columns

	        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedBirthDate"));
	        deathDateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedDeathDate"));
	        // complete rest column
	        
	     // Set cell value factories for columns
	        startHoursColumn.setCellValueFactory(new PropertyValueFactory<>("startHours"));
	        startMinuteColumn.setCellValueFactory(new PropertyValueFactory<>("startMinute"));
	        lateHoursColumn.setCellValueFactory(new PropertyValueFactory<>("lateHours"));
	        lateMinuteColumn.setCellValueFactory(new PropertyValueFactory<>("lateMinute"));
	        endHoursColumn.setCellValueFactory(new PropertyValueFactory<>("endHours"));
	        endMinuteColumn.setCellValueFactory(new PropertyValueFactory<>("endMinute"));
	        totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalHours"));
		    deginationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));

	        

	       
	        // Create custom cell factories for the birthDateColumn and deathDateColumn
	        birthDateColumn.setCellFactory(new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
	            @Override
	            public TableCell<Person, String> call(TableColumn<Person, String> param) {
	                return new DatePickerTableCellBirth();
	            }
	        });


	        deathDateColumn.setCellFactory(new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
	            @Override
	            public TableCell<Person, String> call(TableColumn<Person, String> param) {
	                return new DatePickerTableCellDeath();
	            }
	        });
	       
	        // Add columns to the TableView
	     // Add columns to the TableView
	        tableView.getColumns().addAll(
	               birthDateColumn, deathDateColumn,
	                startHoursColumn, startMinuteColumn,
	                endHoursColumn, endMinuteColumn,totalHoursColumn,deginationColumn,lateHoursColumn,lateMinuteColumn
	        );
		setTableData();


	 
	        startHoursColumn.setCellValueFactory(cellData -> cellData.getValue().startHoursProperty());
	        startHoursColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        startHoursColumn.setOnEditCommit(event -> {

				Person person = event.getRowValue();
				person.setStartHours(event.getNewValue());
				tableView.refresh(); // This line ensures that the updated value is reflected in the table



	        });
	        
	        startMinuteColumn.setCellValueFactory(cellData -> cellData.getValue().startMinuteProperty());
	        startMinuteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        startMinuteColumn.setOnEditCommit(event -> {
	           // event.getRowValue().getStartMinute();

				Person person = event.getRowValue();
				person.setStartMinute(event.getNewValue());
				tableView.refresh(); // This line ensures that the updated value is reflected in the table

			});


        lateHoursColumn.setEditable(false);
        lateMinuteColumn.setEditable(false);


        tableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
                // Get the selected row index
                int rowIndex = tableView.getSelectionModel().getSelectedIndex();
				TableColumn<?, ?> selectedColumn = tableView.getColumns().get(tableView.getSelectionModel().getSelectedCells().get(0).getColumn());
				int columnIndex = tableView.getColumns().indexOf(selectedColumn);

				// Get the data of the clicked row
				Person rowData = tableView.getSelectionModel().getSelectedItem();
                if(columnIndex==8)
                {
                    // Get the selected column index
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
							for(int i=0;i<usersettingdata.size();i++)
							{
								if(usersettingdata.get(i).getEmployeeId().equals(selectedEmployeeIdNumber)){

									if(cc==rowIndex)
									{

										updateRow(usersettingdata.get(i),rowData);

										break;
									}

									cc++;
								}
							}
						} else {
							// Cancel logic goes here
							System.out.println("Cancelled.");
						}
					});


                }
                else if(columnIndex==9){  // delete

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
							cc=0;
							for(int i=0;i<usersettingdata.size();i++)
							{
								if(usersettingdata.get(i).getEmployeeId().equals(selectedEmployeeIdNumber)){

									if(cc==rowIndex)
									{

										deleteRow(usersettingdata.get(i),rowData);

										break;
									}

									cc++;
								}
							}
						} else {
							// Cancel logic goes here
							System.out.println("Cancelled.");
						}
					});


                }



            }
        });




        endHoursColumn.setCellValueFactory(cellData -> cellData.getValue().endHoursProperty());
	        endHoursColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        endHoursColumn.setOnEditCommit(event -> {
	           // event.getRowValue().getEndHours();

				Person person = event.getRowValue();
				person.setEndHours(event.getNewValue());
				tableView.refresh(); // This line ensures that the updated value is reflected in the table

			});

	        endMinuteColumn.setCellValueFactory(cellData -> cellData.getValue().endMinuteProperty());
	        endMinuteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        endMinuteColumn.setOnEditCommit(event -> {
	           // event.getRowValue().getEndMinute();
				Person person = event.getRowValue();
				person.setEndMinute(event.getNewValue());
				tableView.refresh(); // This line ensures that the updated value is reflected in the table

			});

	        totalHoursColumn.setCellValueFactory(cellData -> cellData.getValue().totalHoursProperty());
	        totalHoursColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        totalHoursColumn.setOnEditCommit(event -> {
	           // event.getRowValue().getTotalHours();
				Person person = event.getRowValue();
				person.setTotalHours(event.getNewValue());
				tableView.refresh(); // This line ensures that the updated value is reflected in the table

			});

		deginationColumn.setCellValueFactory(cellData -> cellData.getValue().designationProperty());
		deginationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		deginationColumn.setOnEditCommit(event -> {
			// Update the Person object's designation property
			Person person = event.getRowValue();
			person.setDesignation(event.getNewValue());
			// Commit the edit
			tableView.refresh(); // This line ensures that the updated value is reflected in the table

		});


	        setCenterAlignment(startHoursColumn);
	        setCenterAlignment(startMinuteColumn);
	        setCenterAlignment(endHoursColumn);
	        setCenterAlignment(endMinuteColumn);
	        setCenterAlignment(totalHoursColumn);
		    setCenterAlignment(deginationColumn);
		    setCenterAlignment(lateHoursColumn);
		    setCenterAlignment(lateMinuteColumn);
	        
	        tableView.setEditable(true);  // Make the table editable
	        verticaldata.getChildren().add(tableView);
	        
	        submit=new Button("Add");
		    submit.setVisible(false);

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
						ObservableList<Person> tableData = tableView.getItems();


						for (Person person : tableData) {
							LocalDateTime currentTime = LocalDateTime.now();

							// Define the desired date and time format
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

							// Format the current time to a string
							String formattedTime = currentTime.format(formatter);



							new userLocalSettingController().insertData(repositoryManager,selectedEmployeeIdNumber,selectedEmployeeName,formattedTime ,person.getFormattedBirthDate(),person.getFormattedDeathDate(),
									person.getStartHours(),
									person.getStartMinute(),
									person.getEndHours(),
									person.getEndMinute(),
									person.getTotalHours(),
									person.getDesignation() );

						}

						// update table
						try {
							usersettingdata=new userLocalSettingController().retrieveData(repositoryManager);
						} catch (SQLException ex) {
							throw new RuntimeException(ex);
						}
						setTableData();
						submit.setVisible(false);
						update.setVisible(true);
						// Add logic goes here
						System.out.println("Adding...");
					} else {
						// Cancel logic goes here
						System.out.println("Cancelled.");
					}
				});


	        	 
	         });
	          submit.setMinWidth(130);
	          submit.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	          
	           update=new Button("Update");
	            update.setOnAction(e->{
		        	 
		        	
		        	 
		         });
	          update.setMinWidth(130);
	          update.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"); 
	          
	         HBox sub = new HBox(10,submit);
		        VBox.setMargin(sub, new Insets(20, 0, 20, 0)); // Top and bottom margin of 5 pixels
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
	       // Tab tab = new Tab(tabTitle);


	       // tab.setContent(borderPane);
	       // tab.setClosable(false); // Optional: Disable tab closing

	        return borderPane;
	    }

		public void updateRow( UserLocalSetting exData,Person person)
		{
			if(!submit.isVisible()) {

				LocalDateTime currentTime = LocalDateTime.now();

				// Define the desired date and time format
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

				// Format the current time to a string
				String formattedTime = currentTime.format(formatter);


				new userLocalSettingController().updateData(repositoryManager, exData, selectedEmployeeIdNumber, selectedEmployeeName, formattedTime, person.getFormattedBirthDate(), person.getFormattedDeathDate(),
						person.getStartHours(),
						person.getStartMinute(),
						person.getEndHours(),
						person.getEndMinute(),
						person.getTotalHours(),
						person.getDesignation());


				// update table
				try {
					usersettingdata = new userLocalSettingController().retrieveData(repositoryManager);
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				}
				setTableData();
			}
		}
    public void deleteRow( UserLocalSetting exData,Person person)
    {
        if(!submit.isVisible()) {

            LocalDateTime currentTime = LocalDateTime.now();

            // Define the desired date and time format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Format the current time to a string
            String formattedTime = currentTime.format(formatter);


            new userLocalSettingController().deleteData(repositoryManager, exData, selectedEmployeeIdNumber, selectedEmployeeName, formattedTime, person.getFormattedBirthDate(), person.getFormattedDeathDate(),
                    person.getStartHours(),
                    person.getStartMinute(),
                    person.getEndHours(),
                    person.getEndMinute(),
                    person.getTotalHours(),
                    person.getDesignation());


            // update table
            try {
                usersettingdata = new userLocalSettingController().retrieveData(repositoryManager);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            setTableData();
        }
    }
	public static String generateDate(String startDate, int daysToAdd) {
		// Parse the input string date to LocalDate
		LocalDate parsedDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);

		// Add the specified number of days to the parsed date
		LocalDate newDate = parsedDate.plusDays(daysToAdd);

		// Format the new date to string
		String formattedDate = newDate.format(DateTimeFormatter.ISO_DATE);

		return formattedDate;
	}

	public void setTableData(){
			tableView.getItems().clear(); // Clear existing data
			usersettingdata.forEach(e->{
				if(e.getEmployeeId().equals(selectedEmployeeIdNumber)){
					tableView.getItems().addAll(
							new Person( e.getFormattedBirthDate(), e.getFormattedDeathDate(),e.getStartHours(),e.getStartMinute(),"Edit","Delete",e.getEndHours(),e.getEndMinute(),e.getTotalHours(),e.getDesignation())

					);
				}

			});
		}
    public String selectedid()
	{
		EmployeeData selectedEmployee = employeeList.stream()
				.filter(e -> e.getName().equals(selectedEmployeeName))
				.findFirst()
				.orElse(null);

		if (selectedEmployee != null) {
			// Do something with the selected EmployeeData
			//System.out.println("Selected Employee ID: " + selectedEmployee.getIdNumber());
			// Add more properties as needed
			selectedEmployeeIdNumber=selectedEmployee.getIdNumber();
		}
		return selectedEmployeeIdNumber;
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
   
    public class Person {


        private final DatePicker birthDate;
        private final DatePicker deathDate;
        private final SimpleStringProperty startHours;
        private final SimpleStringProperty startMinute;
        private final SimpleStringProperty lateHours;
        private final SimpleStringProperty lateMinute;
        private final SimpleStringProperty endHours;
        private final SimpleStringProperty endMinute;
        private final SimpleStringProperty totalHours;
        private final SimpleStringProperty designation;

        public Person( String birthDate, String deathDate, String startHours, String startMinute,
                      String lateHours, String lateMinute, String endHours, String endMinute,
                      String totalHours, String designation) {
          
            this.birthDate = new DatePicker();
            this.birthDate.setValue(java.sql.Date.valueOf(birthDate).toLocalDate());
            this.deathDate = new DatePicker();
            this.deathDate.setValue(java.sql.Date.valueOf(deathDate).toLocalDate());
            this.startHours = new SimpleStringProperty(startHours);
            this.startMinute = new SimpleStringProperty(startMinute);
            this.lateHours = new SimpleStringProperty(lateHours);
            this.lateMinute = new SimpleStringProperty(lateMinute);
            this.endHours = new SimpleStringProperty(endHours);
            this.endMinute = new SimpleStringProperty(endMinute);
            this.totalHours = new SimpleStringProperty(totalHours);
            this.designation = new SimpleStringProperty(designation);
        }



        public DatePicker getBirthDate() {
            return birthDate;
        }

        public String getFormattedBirthDate() {
            return birthDate.getValue().toString();
        }

        public DatePicker getDeathDate() {
            return deathDate;
        }

        public String getFormattedDeathDate() {
            return deathDate.getValue().toString();
        }

        public String getStartHours() {
            return startHours.get();
        }

        public SimpleStringProperty startHoursProperty() {
            return startHours;
        }

        public String getStartMinute() {
            return startMinute.get();
        }

        public SimpleStringProperty startMinuteProperty() {
            return startMinute;
        }

        public String getLateHours() {
            return lateHours.get();
        }

        public SimpleStringProperty lateHoursProperty() {
            return lateHours;
        }

        public String getLateMinute() {
            return lateMinute.get();
        }

        public SimpleStringProperty lateMinuteProperty() {
            return lateMinute;
        }

        public String getEndHours() {
            return endHours.get();
        }

        public SimpleStringProperty endHoursProperty() {
            return endHours;
        }

        public String getEndMinute() {
            return endMinute.get();
        }

        public SimpleStringProperty endMinuteProperty() {
            return endMinute;
        }

        public String getTotalHours() {
            return totalHours.get();
        }

        public SimpleStringProperty totalHoursProperty() {
            return totalHours;
        }

        public String getDesignation() {
            return designation.get();
        }

        public SimpleStringProperty designationProperty() {
            return designation;
        }

		public void setStartHours(String startHours) {
			this.startHours.set(startHours);
		}

		public void setStartMinute(String startMinute) {
			this.startMinute.set(startMinute);
		}

		public void setLateHours(String lateHours) {
			this.lateHours.set(lateHours);
		}

		public void setLateMinute(String lateMinute) {
			this.lateMinute.set(lateMinute);
		}

		public void setEndHours(String endHours) {
			this.endHours.set(endHours);
		}

		public void setEndMinute(String endMinute) {
			this.endMinute.set(endMinute);
		}

		public void setTotalHours(String totalHours) {
			this.totalHours.set(totalHours);
		}

		public void setDesignation(String designation) {
			this.designation.set(designation);
		}

		// Setter for birthDate
		public void setBirthDate(LocalDate date) {
			birthDate.setValue(date);
		}

		// Setter for deathDate
		public void setDeathDate(LocalDate date) {
			deathDate.setValue(date);
		}
    }

	 public static class DatePickerTableCellBirth extends TableCell<Person, String> {
		private final DatePicker datePicker;

		public DatePickerTableCellBirth() {
			this.datePicker = new DatePicker();
			this.datePicker.setConverter(new DatePickerFormatter());
			this.datePicker.setOnAction(event -> commitEdit(datePicker.getValue().toString()));
		}

		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setGraphic(null);
			} else {
				datePicker.setValue(java.sql.Date.valueOf(item).toLocalDate());
				setGraphic(datePicker);
			}
		}

		@Override
		public void commitEdit(String newValue) {
			System.out.println("Committing edit: " + newValue);
			Person person = (Person) getTableRow().getItem();

			// Update the birthDate property in the Person object
			person.setBirthDate(datePicker.getValue());

		}


		@Override
		public void startEdit() {
			super.startEdit();
			datePicker.setDisable(false);
			setText(null);
			setGraphic(datePicker);
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();
			setGraphic(null);
			datePicker.setDisable(true);
			setText(getItem());
		}
	}
	public static class DatePickerTableCellDeath extends TableCell<Person, String> {
		private final DatePicker datePicker;

		public DatePickerTableCellDeath() {
			this.datePicker = new DatePicker();
			this.datePicker.setConverter(new DatePickerFormatter());
			this.datePicker.setOnAction(event -> commitEdit(datePicker.getValue().toString()));
		}

		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setGraphic(null);
			} else {
				datePicker.setValue(java.sql.Date.valueOf(item).toLocalDate());
				setGraphic(datePicker);
			}
		}

		@Override
		public void commitEdit(String newValue) {
			System.out.println("Committing edit: " + newValue);
			Person person = (Person) getTableRow().getItem();

			// Update the birthDate property in the Person object
			person.setDeathDate(datePicker.getValue());

		}


		@Override
		public void startEdit() {
			super.startEdit();
			datePicker.setDisable(false);
			setText(null);
			setGraphic(datePicker);
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();
			setGraphic(null);
			datePicker.setDisable(true);
			setText(getItem());
		}
	}

	public static class DatePickerFormatter extends StringConverter<LocalDate> {
         @Override
         public String toString(LocalDate date) {
             return date == null ? "" : date.toString();
         }

         @Override
         public LocalDate fromString(String string) {
             return string == null || string.isEmpty() ? null : LocalDate.parse(string);
         }
     } 
    
}
