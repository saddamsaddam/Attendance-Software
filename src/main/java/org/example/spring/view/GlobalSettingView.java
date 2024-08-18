package org.example.spring.view;

import java.awt.Dimension;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
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

import org.example.spring.controller.GlobalSettingController;
import org.example.spring.model.UserGlobalSetting;
import org.example.spring.repository.RepositoryManager;

import static java.lang.System.out;

public class GlobalSettingView {
	int cc=0;
	Button submit;  Button update;
	int rr;
	List<UserGlobalSetting> globalSettingData;
	RepositoryManager repositoryManager;

    TextField txtname = new TextField();
    TableView<Person> tableView = new TableView<>();
    TextField txtid = new TextField();
    TextField txtdesination = new TextField();
    int k;

    BorderPane createTab(RepositoryManager repositoryManager1,List<UserGlobalSetting> globalSettingData1) throws SQLException {
		globalSettingData=globalSettingData1;
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
	        centerStackPane.setPrefWidth(0.7 *screenSize.getWidth()-70); // 40% of the total width
	       // centerStackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
	       // centerStackPane.setStyle("-fx-border-color: black; -fx-border-width: 2;");
	       
	       

		VBox verticaldata = new VBox();
		Button addButton = new Button("New");
		Button history = new Button("History");

		addButton.setOnAction(event -> {
			submit.setVisible(true);
			update.setVisible(false);
			// Place your action code here
			System.out.println("New button clicked!");
            globalSettingData= GlobalSettingController.retrieveData(repositoryManager);
            tableView.getItems().clear();

			//String birthDate, String deathDate, String startHours, String startMinute,
			//                      String lateHours, String lateMinute, String endHours, String endMinute,
			//                      String earlyHours, String earlyMinute
			if(!globalSettingData.isEmpty())
			{
				for(int i=0;i<globalSettingData.size();i++)
				{
					tableView.getItems().addAll(
							new Person(generateDate( globalSettingData.get(i).getFormattedDeathDate(),1),generateDate( globalSettingData.get(i).getFormattedDeathDate(),30) ,"9","00","9",globalSettingData.get(i).getLateMinute(),"Edit","Delete","17",globalSettingData.get(i).getEarlyMinute())
					);
					break;
				}
			}
			else {
				// Get current date
				LocalDate currentDate = LocalDate.now();

				// Define the desired date format
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Change the pattern as needed

				// Format the date as a string
				String dateString = currentDate.format(formatter);
				tableView.getItems().addAll(


				new Person( dateString, generateDate(dateString,30),"09","00","9","15","Edit","Delete","17","15")
				);
			}

		});

		HBox subcombobox = new HBox(10, addButton, history);
		VBox.setMargin(subcombobox, new Insets(0, 0, 20, 0)); // Top and bottom margin of 5 pixels
		subcombobox.setAlignment(Pos.TOP_LEFT);
		verticaldata.getChildren().add(subcombobox);




		tableView.setEditable(true);

	        // Create columns for the TableView
	
	        TableColumn<Person, String> birthDateColumn = new TableColumn<>("Start Date");
	        TableColumn<Person, String> deathDateColumn = new TableColumn<>("End Date");
	          TableColumn<Person, String> startHoursColumn = new TableColumn<>("Start Hours");
	          TableColumn<Person, String> startMinuteColumn = new TableColumn<>("Start Minute");
	          TableColumn<Person, String> lateHoursColumn = new TableColumn<>("Late Hours");
	          TableColumn<Person, String> lateMinuteColumn = new TableColumn<>("Late Minute");
	          TableColumn<Person, String> endHoursColumn = new TableColumn<>("Edit");
	          TableColumn<Person, String> endMinuteColumn = new TableColumn<>("Delete");
	          TableColumn<Person, String> earlyHoursColumn = new TableColumn<>("Early Hours");
	          TableColumn<Person, String> earlyMinuteColumn = new TableColumn<>("Early Minute");
	          
	          
	          birthDateColumn.setMinWidth(170);
	          deathDateColumn.setMinWidth(170);
	          lateMinuteColumn.setMinWidth(150);
	          earlyMinuteColumn.setMinWidth(150);
		        endHoursColumn.setMinWidth(100);
				endMinuteColumn.setMinWidth(100);
	         

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
	        earlyHoursColumn.setCellValueFactory(new PropertyValueFactory<>("earlyHours"));
	        earlyMinuteColumn.setCellValueFactory(new PropertyValueFactory<>("earlyMinute"));
	        
	        

	       
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
	               lateMinuteColumn, earlyMinuteColumn,endHoursColumn,endMinuteColumn
	               
	        );
	        
	        // Add sample data to the TableView

			    rr=0;
				out.println(globalSettingData.size());
				globalSettingData.forEach(e->{
					//tableView.getItems().clear();
					out.println(e.toString());
					rr=1;
					tableView.getItems().addAll(
							new Person( e.getFormattedBirthDate(), e.getFormattedDeathDate(),"09","00","9",e.getLateMinute(),"Edit","Delete","17",e.getEarlyMinute())
					);

					tableView.refresh();
				});



	 
	        startHoursColumn.setCellValueFactory(cellData -> cellData.getValue().startHoursProperty());
	        startHoursColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        startHoursColumn.setOnEditCommit(event -> {
	             Person person = event.getRowValue();
				  person.setStartHours(event.getNewValue());
				// Commit the edit
				tableView.refresh();
	        });
	        
	        startMinuteColumn.setCellValueFactory(cellData -> cellData.getValue().startMinuteProperty());
	        startMinuteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        startMinuteColumn.setOnEditCommit(event -> {
	            event.getRowValue().getStartMinute();
	        });
	        
	        lateHoursColumn.setCellValueFactory(cellData -> cellData.getValue().lateHoursProperty());
	        lateHoursColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        lateHoursColumn.setOnEditCommit(event -> {
	            event.getRowValue().getLateHours();
	        });
	        
	        lateMinuteColumn.setCellValueFactory(cellData -> cellData.getValue().lateMinuteProperty());
	        lateMinuteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        lateMinuteColumn.setOnEditCommit(event -> {
	           // event.getRowValue().getLateMinute();
				Person person = event.getRowValue();
				person.setLateMinute(event.getNewValue());
				// Commit the edit
				tableView.refresh();
	        });
	        

			endHoursColumn.setEditable(false);
		    endMinuteColumn.setEditable(false);

		     tableView.setOnMouseClicked(event -> {
			if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
				// Get the selected row index
				int rowIndex = tableView.getSelectionModel().getSelectedIndex();
				TableColumn<?, ?> selectedColumn = tableView.getColumns().get(tableView.getSelectionModel().getSelectedCells().get(0).getColumn());
				int columnIndex = tableView.getColumns().indexOf(selectedColumn);

				// Get the data of the clicked row
				Person rowData = tableView.getSelectionModel().getSelectedItem();
				if(columnIndex==4)
				{
					// Get the selected column index
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setTitle("Add Confirmation");
					alert.setHeaderText("Do you want to Edit?");
					alert.setContentText("Choose your option.");

					ButtonType addButton1 = new ButtonType("Edit");
					ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

					alert.getButtonTypes().setAll(addButton1, cancelButton);

					alert.showAndWait().ifPresent(buttonType -> {
						if (buttonType == addButton1) {
							cc=0;
							for(int i=0;i<globalSettingData.size();i++)
							{
								if(cc==rowIndex)
								{

									updateRow(globalSettingData.get(i),rowData);

									break;
								}

								cc++;
							}
						} else {
							// Cancel logic goes here
							System.out.println("Cancelled.");
						}
					});


				}
				else if(columnIndex==5){  // delete
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setTitle("Add Confirmation");
					alert.setHeaderText("Do you want Delete ?");
					alert.setContentText("Choose your option.");

					ButtonType addButton1 = new ButtonType("Delete");
					ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

					alert.getButtonTypes().setAll(addButton1, cancelButton);

					alert.showAndWait().ifPresent(buttonType -> {
						if (buttonType == addButton1) {
							cc=0;
							for(int i=0;i<globalSettingData.size();i++)
							{
								if(cc==rowIndex)
								{

									deleteRow(globalSettingData.get(i),rowData);

									break;
								}

								cc++;
							}
						} else {
							// Cancel logic goes here
							System.out.println("Cancelled.");
						}
					});



				}



			}
		});





		earlyHoursColumn.setCellValueFactory(cellData -> cellData.getValue().earlyHoursProperty());
	        earlyHoursColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        earlyHoursColumn.setOnEditCommit(event -> {
	            event.getRowValue().getEarlyHours();

	        });

	        earlyMinuteColumn.setCellValueFactory(cellData -> cellData.getValue().earlyMinuteProperty());
	        earlyMinuteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        earlyMinuteColumn.setOnEditCommit(event -> {
	           // event.getRowValue().getEarlyMinute();
				Person person = event.getRowValue();
				person.setEarlyMinute(event.getNewValue());
				// Commit the edit
				tableView.refresh();
			});

	        setCenterAlignment(startHoursColumn);
	        setCenterAlignment(startMinuteColumn);
	        setCenterAlignment( earlyMinuteColumn);
	        setCenterAlignment( lateMinuteColumn);
			setCenterAlignment( endHoursColumn);
			setCenterAlignment( endMinuteColumn);
	        
	        tableView.setEditable(true);  // Make the table editable
	        verticaldata.getChildren().add(tableView);

		submit=new Button("Add");
		submit.setVisible(false);
		    submit.setOnAction(e -> {
			tableView.refresh();
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Add Confirmation");
				alert.setHeaderText("Do you want to add?");
				alert.setContentText("Choose your option.");

				ButtonType addButton1 = new ButtonType("Add");
				ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

				alert.getButtonTypes().setAll(addButton1, cancelButton);

				alert.showAndWait().ifPresent(buttonType -> {
					if (buttonType == addButton1) {
						ObservableList<Person> tableData = tableView.getItems();

						// Print the table data
						for (Person person : tableData) {

							new GlobalSettingController().insert(repositoryManager,person.getFormattedBirthDate(),person.getFormattedDeathDate(),person.getLateMinute(),person.getEarlyMinute());
						}

						// Perform additional actions if needed
						globalSettingData= GlobalSettingController.retrieveData(repositoryManager);
						tableView.getItems().clear();

						//String birthDate, String deathDate, String startHours, String startMinute,
						//                      String lateHours, String lateMinute, String endHours, String endMinute,
						//                      String earlyHours, String earlyMinute
						for(int i=0;i<globalSettingData.size();i++)
						{
							tableView.getItems().addAll(
									new Person(generateDate( globalSettingData.get(i).getFormattedDeathDate(),1),generateDate( globalSettingData.get(i).getFormattedDeathDate(),30) ,"9","00","9",globalSettingData.get(i).getLateMinute(),"Edit","Delete","17",globalSettingData.get(i).getEarlyMinute())
							);
						}
						submit.setVisible(false);
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
	public void updateRow(UserGlobalSetting exData, Person person)
	{
		if(!submit.isVisible()) {

			LocalDateTime currentTime = LocalDateTime.now();

			// Define the desired date and time format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			// Format the current time to a string
			String formattedTime = currentTime.format(formatter);


			new GlobalSettingController().updateData(repositoryManager, exData,  person.getFormattedBirthDate(), person.getFormattedDeathDate(),person.getLateMinute(),person.getEarlyMinute()
					);

			// update table
            globalSettingData = GlobalSettingController.retrieveData(repositoryManager);

			tableView.getItems().clear();

			//String birthDate, String deathDate, String startHours, String startMinute,
			//                      String lateHours, String lateMinute, String endHours, String endMinute,
			//                      String earlyHours, String earlyMinute
			for(int i=0;i<globalSettingData.size();i++)
			{
				tableView.getItems().addAll(
						new Person(generateDate( globalSettingData.get(i).getFormattedDeathDate(),1),generateDate( globalSettingData.get(i).getFormattedDeathDate(),30) ,"9","00","9",globalSettingData.get(i).getLateMinute(),"Edit","Delete","17",globalSettingData.get(i).getEarlyMinute())
				);
			}
		}
	}
	public void deleteRow(UserGlobalSetting exData, Person person)
	{
		if(!submit.isVisible()) {

			LocalDateTime currentTime = LocalDateTime.now();

			// Define the desired date and time format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			// Format the current time to a string
			String formattedTime = currentTime.format(formatter);


			new GlobalSettingController().deleteData(repositoryManager, exData,  person.getFormattedBirthDate(), person.getFormattedDeathDate(),person.getLateMinute(),person.getEarlyMinute()
			);

			// update table
			globalSettingData = GlobalSettingController.retrieveData(repositoryManager);

			tableView.getItems().clear();

			//String birthDate, String deathDate, String startHours, String startMinute,
			//                      String lateHours, String lateMinute, String endHours, String endMinute,
			//                      String earlyHours, String earlyMinute
			for(int i=0;i<globalSettingData.size();i++)
			{
				tableView.getItems().addAll(
						new Person(generateDate( globalSettingData.get(i).getFormattedDeathDate(),1),generateDate( globalSettingData.get(i).getFormattedDeathDate(),30) ,"9","00","9",globalSettingData.get(i).getLateMinute(),"Edit","Delete","17",globalSettingData.get(i).getEarlyMinute())
				);
			}
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
        private final SimpleStringProperty earlyHours;
        private final SimpleStringProperty earlyMinute;

        public Person( String birthDate, String deathDate, String startHours, String startMinute,
                      String lateHours, String lateMinute, String endHours, String endMinute,
                      String earlyHours, String earlyMinute) {

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
            this.earlyHours = new SimpleStringProperty(earlyHours);
            this.earlyMinute = new SimpleStringProperty(earlyMinute);
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

        public String getEarlyHours() {
            return earlyHours.get();
        }

        public SimpleStringProperty earlyHoursProperty() {
            return earlyHours;
        }

        public String getEarlyMinute() {
            return earlyMinute.get();
        }

        public SimpleStringProperty earlyMinuteProperty() {
            return earlyMinute;
        }

		// Setter methods
		public void setBirthDate(LocalDate date) {
			birthDate.setValue(date);
		}

		public void setDeathDate(LocalDate date) {
			deathDate.setValue(date);
		}

		public void setStartHours(String hours) {
			startHours.set(hours);
		}

		public void setStartMinute(String minute) {
			startMinute.set(minute);
		}

		public void setLateHours(String hours) {
			lateHours.set(hours);
		}

		public void setLateMinute(String minute) {
			lateMinute.set(minute);
		}

		public void setEndHours(String hours) {
			endHours.set(hours);
		}

		public void setEndMinute(String minute) {
			endMinute.set(minute);
		}

		public void setEarlyHours(String hours) {
			earlyHours.set(hours);
		}

		public void setEarlyMinute(String minute) {
			earlyMinute.set(minute);
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
