package org.example.spring.view;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

import java.io.File;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import org.example.spring.controller.GlobalSettingController;
import org.example.spring.controller.NameCategoryController;
import org.example.spring.controller.userLocalSettingController;
import org.example.spring.model.EmployeeData;
import org.example.spring.model.NameAndCategory;
import org.example.spring.model.UserGlobalSetting;
import org.example.spring.model.UserLocalSetting;
import org.example.spring.repository.RepositoryManager;
import org.example.spring.repository.UserLocalSettingRepository;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class AssetView {
	TableColumn<Asset, String> categoryColumn;
	TableColumn<Asset, String> assetNameColumn;
	int cc=0;
	Button newadd,assetButton,categoryButton; Button submit;Button update;
	RepositoryManager repositoryManager;
	String selectedEmployeeName;
	String selectedEmployeeIdNumber;
	TableView<Asset> tableView;
	TextField txtname = new TextField();

	TextField txtid = new TextField();
	Button AssetSubmit,CategorySubmit;

	List<NameAndCategory> assetData,categoryData;
	TextField txtdesination = new TextField();
	int k;
	List<EmployeeData> employeeList;
	TableView<AssetData> AssettableView,CategorytableView;

	public AssetView() throws SQLException
	{

	}
	UserLocalSettingRepository userLocalSettingRepository;
	List<UserLocalSetting> usersettingdata;
	List<NameAndCategory> AssetDataList;
	List<NameAndCategory> CategoryDataList;
	String name;
	BorderPane createTab(RepositoryManager repositoryManager1,List<EmployeeData> employeeList1) throws SQLException {
		tableView = new TableView<>();

		employeeList=employeeList1;
		repositoryManager=repositoryManager1;
		AssetDataList=new NameCategoryController().retrieveData(repositoryManager,"asset");
		CategoryDataList=new NameCategoryController().retrieveData(repositoryManager,"category");
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
		if(!employeeList.isEmpty()) {
			comboBox.setValue(employeeList.get(0).getName());
			name=employeeList.get(0).getName();
		}
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
							//	new Person(generateDate( usersettingdata.get(i).getFormattedDeathDate(),1),generateDate( usersettingdata.get(i).getFormattedDeathDate(),30) ,usersettingdata.get(i).getStartHours(),usersettingdata.get(i).getStartMinute(),"Edit","Delete",usersettingdata.get(i).getEndHours(),usersettingdata.get(i).getEndMinute(),usersettingdata.get(i).getTotalHours(),usersettingdata.get(i).getDesignation())
					);

					break;
				}
			}
		});
		assetButton=new Button("Add Asset");
		// Inside your AssetView class

// Inside your AssetView class
		assetButton.setOnAction(event -> {
			// Create a new Stage for the popup panel
			Stage popupStage = new Stage();
			popupStage.initModality(Modality.APPLICATION_MODAL); // Block events to other windows
			popupStage.setTitle("Asset ");

			// Create a VBox to hold the content of the popup panel
			VBox popupContent = new VBox();
			popupContent.setAlignment(Pos.CENTER);
			popupContent.setSpacing(20);
			popupContent.setPadding(new Insets(20)); // Set padding

			// Create an HBox for buttons
			HBox buttonBox = new HBox();
			buttonBox.setAlignment(Pos.CENTER);
			buttonBox.setSpacing(10);

			// Create buttons
			Button addButton = new Button("Add");
			addButton.setOnAction(event1->{
				AssetSubmit.setVisible(true);
				AssettableView.getItems().clear();
				AssettableView.getItems().add( new AssetData(" Name", "Edit", "Delete"));

			});
			Button historyButton = new Button("History");

			// Add buttons to the HBox
			buttonBox.getChildren().addAll(addButton, historyButton);

			// Create TableView
			AssettableView = new TableView<>();

			TableColumn<AssetData, String> nameColumn = new TableColumn<>("Name");
			TableColumn<AssetData, String> editColumn = new TableColumn<>("Edit");
			TableColumn<AssetData, String> deleteColumn = new TableColumn<>("Delete");


			// Set TableView to be editable


// Set cell value factories and cell factories for each column
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("assetName"));

			editColumn.setCellValueFactory(new PropertyValueFactory<>("edit"));
			editColumn.setCellFactory(TextFieldTableCell.forTableColumn()); // Allow editing with text fields

			deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));
			deleteColumn.setCellFactory(TextFieldTableCell.forTableColumn()); // Allow editing with text fields
			// Set minimum widths for columns
			nameColumn.setMinWidth(120); // Set minimum width for the "Name" column
			editColumn.setMinWidth(120); // Set minimum width for the "Edit" column
			deleteColumn.setMinWidth(120); // Set minimum width for the "Delete" column
//        Create cell factories to center the text


			editColumn.setCellFactory(tc -> new TableCell<AssetData, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText(null);
						setAlignment(Pos.CENTER);
					} else {
						setText(item);
						setAlignment(Pos.CENTER);
					}
				}
			});

			deleteColumn.setCellFactory(tc -> new TableCell<AssetData, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText(null);
						setAlignment(Pos.CENTER);
					} else {
						setText(item);
						setAlignment(Pos.CENTER);
					}
				}
			});
			AssettableView.getColumns().addAll(nameColumn, editColumn, deleteColumn);

			// Set width and height of the TableView
			AssettableView.setPrefWidth(200); // Set preferred width
			AssettableView.setPrefHeight(200); // Set preferred height

			// Create an ObservableList to hold the data
			try {
				assetData=new NameCategoryController().retrieveData(repositoryManager,"asset");
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			ObservableList<AssetData> data = FXCollections.observableArrayList();
			assetData.forEach(e->{
				data.add(new AssetData(e.getName(), "Edit", "Delete"));
			});

			// Set items of the TableView to the data list
			AssettableView.setItems(data);
			nameColumn.setCellValueFactory(cellData -> cellData.getValue().assetNameProperty());
			nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			nameColumn.setOnEditCommit(event1 -> {
				AssetData data1 = event1.getRowValue();
				data1.setAssetName(event1.getNewValue());
				AssettableView.refresh(); // Refresh TableView after editing
			});
			AssettableView.setStyle("-fx-alignment: CENTER;");

// Alternatively, you can set alignment for individual columns
			nameColumn.setStyle("-fx-alignment: CENTER;");

			AssettableView.setEditable(true);
			AssetSubmit=new Button("Add");
			AssetSubmit.setOnAction(event1 -> {
				// Iterate over the items in the table view and print their values
				List<AssetData> dataView = new ArrayList<>(AssettableView.getItems());
				dataView.forEach(e->{
					if (!e.getAssetName().isEmpty())
					{
						Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
						alert.setTitle("Add Confirmation");
						alert.setHeaderText("Do you want to add?");
						alert.setContentText("Choose your option.");

						ButtonType addButton1 = new ButtonType("Add");
						ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

						alert.getButtonTypes().setAll(addButton1, cancelButton);

						alert.showAndWait().ifPresent(buttonType -> {
							if (buttonType == addButton1) {

								LocalDateTime currentTime = LocalDateTime.now();

								// Define the desired date and time format
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

								// Format the current time to a string
								String formattedTime = currentTime.format(formatter);
								new NameCategoryController().insertData(repositoryManager,e.getAssetName(),"asset","1",currentTime);


								try {
									AssetDataList=new NameCategoryController().retrieveData(repositoryManager,"asset");
								} catch (SQLException f) {
									throw new RuntimeException(f);
								}

								AssettableView.getItems().clear();

								//String birthDate, String deathDate, String startHours, String startMinute,
								//                      String lateHours, String lateMinute, String endHours, String endMinute,
								//                      String earlyHours, String earlyMinute
								for(int i=0;i<AssetDataList.size();i++)
								{
									AssettableView.getItems().addAll(
											new AssetData(AssetDataList.get(i).getName(),"Edit","Delete")

									);
								}
								try {
									assetNameColumn.setCellFactory(createComboBoxTableCell());
								} catch (SQLException f) {
									throw new RuntimeException(f);
								}


								AssetSubmit.setVisible(false);

							} else {
								// Cancel logic goes here
								System.out.println("Cancelled.");
							}
						});
					}

					System.out.println("-----------------------");

				});

			});
			AssettableView.setOnMouseClicked(event1 -> {
				try {
					assetData=new NameCategoryController().retrieveData(repositoryManager,"asset");
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				if (event1.getButton().equals(MouseButton.PRIMARY) && event1.getClickCount() == 1) {
					// Get the selected row index
					int rowIndex = AssettableView.getSelectionModel().getSelectedIndex();
					TableColumn<?, ?> selectedColumn = AssettableView.getColumns().get(AssettableView.getSelectionModel().getSelectedCells().get(0).getColumn());
					int columnIndex = AssettableView.getColumns().indexOf(selectedColumn);

					// Get the data of the clicked row
					AssetData rowData = AssettableView.getSelectionModel().getSelectedItem();
					if(columnIndex==1)
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
								for(int i=0;i<assetData.size();i++)
								{
									if(cc==rowIndex)
									{

										try {
											updateRow(assetData.get(i),rowData,"asset",AssettableView);
										} catch (SQLException e) {
											throw new RuntimeException(e);
										}
										try {
											assetNameColumn.setCellFactory(createComboBoxTableCell());
										} catch (SQLException e) {
											throw new RuntimeException(e);
										}
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
					else if(columnIndex==2){  // delete
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
								for(int i=0;i<assetData.size();i++)
								{
									if(cc==rowIndex)
									{

										try {
											deleteRow(assetData.get(i),rowData,"asset",AssettableView);
										} catch (SQLException e) {
											throw new RuntimeException(e);
										}
										try {
											assetNameColumn.setCellFactory(createComboBoxTableCell());
										} catch (SQLException e) {
											throw new RuntimeException(e);
										}
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
			AssetSubmit.setVisible(false);
			popupContent.getChildren().addAll(buttonBox, AssettableView,AssetSubmit);

			// Set the scene with the popup content
			Scene popupScene = new Scene(popupContent, 400, 400);
			popupStage.setScene(popupScene);

			// Center the popup panel on the screen
			popupStage.centerOnScreen();

			// Show the popup panel
			popupStage.show();
		});


		categoryButton=new Button("Add Category");
		categoryButton.setOnAction(event -> {
			// Create a new Stage for the popup panel
			Stage popupStage = new Stage();
			popupStage.initModality(Modality.APPLICATION_MODAL); // Block events to other windows
			popupStage.setTitle("Asset ");

			// Create a VBox to hold the content of the popup panel
			VBox popupContent = new VBox();
			popupContent.setAlignment(Pos.CENTER);
			popupContent.setSpacing(20);
			popupContent.setPadding(new Insets(20)); // Set padding

			// Create an HBox for buttons
			HBox buttonBox = new HBox();
			buttonBox.setAlignment(Pos.CENTER);
			buttonBox.setSpacing(10);

			// Create buttons
			Button addButton = new Button("Add");
			addButton.setOnAction(event1->{
				CategorySubmit.setVisible(true);
				CategorytableView.getItems().clear();
				CategorytableView.getItems().add( new AssetData(" Name", "Edit", "Delete"));

			});
			Button historyButton = new Button("History");

			// Add buttons to the HBox
			buttonBox.getChildren().addAll(addButton, historyButton);

			// Create TableView
			CategorytableView = new TableView<>();

			TableColumn<AssetData, String> nameColumn = new TableColumn<>("Name");
			TableColumn<AssetData, String> editColumn = new TableColumn<>("Edit");
			TableColumn<AssetData, String> deleteColumn = new TableColumn<>("Delete");


			// Set TableView to be editable


// Set cell value factories and cell factories for each column
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("assetName"));

			editColumn.setCellValueFactory(new PropertyValueFactory<>("edit"));
			editColumn.setCellFactory(TextFieldTableCell.forTableColumn()); // Allow editing with text fields

			deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));
			deleteColumn.setCellFactory(TextFieldTableCell.forTableColumn()); // Allow editing with text fields
			// Set minimum widths for columns
			nameColumn.setMinWidth(120); // Set minimum width for the "Name" column
			editColumn.setMinWidth(120); // Set minimum width for the "Edit" column
			deleteColumn.setMinWidth(120); // Set minimum width for the "Delete" column
//        Create cell factories to center the text


			editColumn.setCellFactory(tc -> new TableCell<AssetData, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText(null);
						setAlignment(Pos.CENTER);
					} else {
						setText(item);
						setAlignment(Pos.CENTER);
					}
				}
			});

			deleteColumn.setCellFactory(tc -> new TableCell<AssetData, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText(null);
						setAlignment(Pos.CENTER);
					} else {
						setText(item);
						setAlignment(Pos.CENTER);
					}
				}
			});
			CategorytableView.getColumns().addAll(nameColumn, editColumn, deleteColumn);

			// Set width and height of the TableView
			CategorytableView.setPrefWidth(200); // Set preferred width
			CategorytableView.setPrefHeight(200); // Set preferred height

			// Create an ObservableList to hold the data
			try {
				categoryData=new NameCategoryController().retrieveData(repositoryManager,"category");
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			ObservableList<AssetData> data = FXCollections.observableArrayList();
			categoryData.forEach(e->{
				data.add(new AssetData(e.getName(), "Edit", "Delete"));
			});

			// Set items of the TableView to the data list
			CategorytableView.setItems(data);
			nameColumn.setCellValueFactory(cellData -> cellData.getValue().assetNameProperty());
			nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			nameColumn.setOnEditCommit(event1 -> {
				AssetData data1 = event1.getRowValue();
				data1.setAssetName(event1.getNewValue());
				CategorytableView.refresh(); // Refresh TableView after editing
			});
			CategorytableView.setStyle("-fx-alignment: CENTER;");

// Alternatively, you can set alignment for individual columns
			nameColumn.setStyle("-fx-alignment: CENTER;");

			CategorytableView.setEditable(true);
			CategorySubmit=new Button("Add");
			CategorySubmit.setOnAction(event1 -> {
				// Iterate over the items in the table view and print their values
				List<AssetData> dataView=new ArrayList<>(CategorytableView.getItems());
				dataView.forEach(f->{


					if (!f.getAssetName().isEmpty())
					{
						Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
						alert.setTitle("Add Confirmation");
						alert.setHeaderText("Do you want to add?");
						alert.setContentText("Choose your option.");

						ButtonType addButton1 = new ButtonType("Add");
						ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

						alert.getButtonTypes().setAll(addButton1, cancelButton);

						alert.showAndWait().ifPresent(buttonType -> {
							if (buttonType == addButton1) {
								System.out.println("Asset Name: " + f.getAssetName());
								LocalDateTime currentTime = LocalDateTime.now();

								// Define the desired date and time format
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

								// Format the current time to a string
								String formattedTime = currentTime.format(formatter);
								new NameCategoryController().insertData(repositoryManager,f.getAssetName(),"category","1",currentTime);


								try {
									categoryData=new NameCategoryController().retrieveData(repositoryManager,"category");
								} catch (SQLException e) {
									throw new RuntimeException(e);
								}

								CategorytableView.getItems().clear();

								//String birthDate, String deathDate, String startHours, String startMinute,
								//                      String lateHours, String lateMinute, String endHours, String endMinute,
								//                      String earlyHours, String earlyMinute
								for(int i=0;i<categoryData.size();i++)
								{
									CategorytableView.getItems().addAll(
											new AssetData(categoryData.get(i).getName(),"Edit","Delete")

									);
								}
								try {
									categoryColumn.setCellFactory(createComboBoxTableCellCategory());
								} catch (SQLException e) {
									throw new RuntimeException(e);
								}
								CategorySubmit.setVisible(false);

							} else {
								// Cancel logic goes here
								System.out.println("Cancelled.");
							}
						});
					}

					System.out.println("-----------------------");
				});

			});
			CategorytableView.setOnMouseClicked(event1 -> {
				try {
					categoryData=new NameCategoryController().retrieveData(repositoryManager,"category");
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				if (event1.getButton().equals(MouseButton.PRIMARY) && event1.getClickCount() == 1) {
					// Get the selected row index
					int rowIndex = CategorytableView.getSelectionModel().getSelectedIndex();
					TableColumn<?, ?> selectedColumn = CategorytableView.getColumns().get(CategorytableView.getSelectionModel().getSelectedCells().get(0).getColumn());
					int columnIndex = CategorytableView.getColumns().indexOf(selectedColumn);

					// Get the data of the clicked row
					AssetData rowData = CategorytableView.getSelectionModel().getSelectedItem();
					if(columnIndex==1)
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
								for(int i=0;i<categoryData.size();i++)
								{
									if(cc==rowIndex)
									{

										try {
											updateRow(categoryData.get(i),rowData,"category",CategorytableView);
										} catch (SQLException e) {
											throw new RuntimeException(e);
										}
										try {
											categoryColumn.setCellFactory(createComboBoxTableCellCategory());
										} catch (SQLException e) {
											throw new RuntimeException(e);
										}
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
					else if(columnIndex==2){  // delete
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
								for(int i=0;i<categoryData.size();i++)
								{
									if(cc==rowIndex)
									{

										try {
											deleteRow(categoryData.get(i),rowData,"category",CategorytableView);
										} catch (SQLException e) {
											throw new RuntimeException(e);
										}
										try {
											categoryColumn.setCellFactory(createComboBoxTableCellCategory());
										} catch (SQLException e) {
											throw new RuntimeException(e);
										}
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
			CategorySubmit.setVisible(false);
			popupContent.getChildren().addAll(buttonBox, CategorytableView,CategorySubmit);

			// Set the scene with the popup content
			Scene popupScene = new Scene(popupContent, 400, 400);
			popupStage.setScene(popupScene);

			// Center the popup panel on the screen
			popupStage.centerOnScreen();

			// Show the popup panel
			popupStage.show();
		});

		HBox subcombobox = new HBox(10,comboBox,newadd,assetButton,categoryButton,history);
		VBox.setMargin(subcombobox, new Insets(0, 0, 20, 0)); // Top and bottom margin of 5 pixels
		subcombobox.setAlignment(Pos.TOP_LEFT);
		verticaldata.getChildren().add(subcombobox);






		// Define columns
		assetNameColumn = new TableColumn<>("Asset Name");
		assetNameColumn.setCellValueFactory(cellData -> cellData.getValue().assetNameProperty());
		assetNameColumn.setCellFactory(col -> {
			ComboBoxTableCell<Asset, String> cell = new ComboBoxTableCell<>();

			updateComboBoxItems(cell);
			//cell.getItems().addAll("Asset 1", "Asset 2", "Asset 3"); // Populate with your asset names
			return cell;
		});

		categoryColumn = new TableColumn<>("Category");
		categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
		categoryColumn.setCellFactory(col -> {
			ComboBoxTableCell<Asset, String> cell = new ComboBoxTableCell<>();
			updateComboBoxItemsCategory(cell);
			return cell;
		});

		TableColumn<Asset, LocalDate> usingStartTimeColumn = new TableColumn<>("Using Start Time");
		usingStartTimeColumn.setCellValueFactory(cellData -> cellData.getValue().usingStartTimeProperty());
		usingStartTimeColumn.setCellFactory(col -> new DatePickerTableCell<>());

		TableColumn<Asset, LocalDate> buyingTimeColumn = new TableColumn<>("Buying Time");
		buyingTimeColumn.setCellValueFactory(cellData -> cellData.getValue().buyingTimeProperty());
		buyingTimeColumn.setCellFactory(col -> new DatePickerTableCell<>());


		TableColumn<Asset, String> buyingPriceColumn = new TableColumn<>("Buying Price");
		buyingPriceColumn.setCellValueFactory(cellData -> cellData.getValue().buyingPriceProperty());
		buyingPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());


		TableColumn<Asset, String> possibleSellingPriceColumn = new TableColumn<>("Possible Selling Price");
		possibleSellingPriceColumn.setCellValueFactory(cellData -> cellData.getValue().possibleSellingPriceProperty());
		possibleSellingPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());


		TableColumn<Asset, String> assetPictureColumn = new TableColumn<>("Asset Picture");
		assetPictureColumn.setCellValueFactory(cellData -> cellData.getValue().assetPictureProperty());
		assetPictureColumn.setCellFactory(col -> new PictureTableCell());

		TableColumn<Asset, ImageView> AssetImageColumn = new TableColumn<>("Asset Image");
		AssetImageColumn.setCellValueFactory(new PropertyValueFactory<>("AssetImageView"));
		AssetImageColumn.setCellFactory(column -> new TableCell<>() {
			private final Label imageLabel = new Label();

			{
				imageLabel.setGraphicTextGap(5); // Adjust the gap between image and text if needed
				imageLabel.setOnMouseClicked(e -> {
					if (e.getClickCount() == 2) {
						System.out.println("Double clicked");
						FileChooser fileChooser = new FileChooser();
						fileChooser.setTitle("Select Image");
						fileChooser.getExtensionFilters().addAll(
								new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
						);
						File selectedFile = fileChooser.showOpenDialog(null);
						if (selectedFile != null) {
							Image image = new Image(selectedFile.toURI().toString());
							ImageView imageView = new ImageView(image);
							imageView.setFitWidth(100);
							imageView.setFitHeight(100);
							imageLabel.setGraphic(imageView);
							Asset item = getTableView().getItems().get(getIndex());
							item.setAssetImageView(imageView);
						}
					}
				});
			}

			@Override
			protected void updateItem(ImageView item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setGraphic(null);
				} else {
					imageLabel.setGraphic(item);
					setGraphic(imageLabel);
				}
			}
		});
		TableColumn<Asset, ImageView> MemoImageColumn = new TableColumn<>("Memo");
		MemoImageColumn.setCellValueFactory(new PropertyValueFactory<>("MemoImageView"));
		MemoImageColumn.setCellFactory(column -> new TableCell<>() {
			private final Label imageLabel = new Label();

			{
				imageLabel.setGraphicTextGap(5); // Adjust the gap between image and text if needed
				imageLabel.setOnMouseClicked(e -> {
					if (e.getClickCount() == 2) {
						System.out.println("Double clicked");
						FileChooser fileChooser = new FileChooser();
						fileChooser.setTitle("Select Image");
						fileChooser.getExtensionFilters().addAll(
								new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
						);
						File selectedFile = fileChooser.showOpenDialog(null);
						if (selectedFile != null) {
							Image image = new Image(selectedFile.toURI().toString());
							ImageView imageView = new ImageView(image);
							imageView.setFitWidth(100);
							imageView.setFitHeight(100);
							imageLabel.setGraphic(imageView);
							Asset item = getTableView().getItems().get(getIndex());
							item.setMemoImageView(imageView);
						}
					}
				});
			}

			@Override
			protected void updateItem(ImageView item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setGraphic(null);
				} else {
					imageLabel.setGraphic(item);
					setGraphic(imageLabel);
				}
			}
		});


		TableColumn<Asset, String> vaulterPictureColumn = new TableColumn<>("Vaulter Picture");
		vaulterPictureColumn.setCellValueFactory(cellData -> cellData.getValue().vaulterPictureProperty());
		vaulterPictureColumn.setCellFactory(col -> new PictureTableCell());

		TableColumn<Asset, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Asset, String> montobboColumn = new TableColumn<>("Comment");
		montobboColumn.setCellValueFactory(cellData -> cellData.getValue().montobboProperty());
		montobboColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Asset, String> editColumn = new TableColumn<>("Edit");
		editColumn.setCellValueFactory(cellData -> cellData.getValue().editProperty());
		editColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Asset, String> deleteColumn = new TableColumn<>("Edit");
		deleteColumn.setCellValueFactory(cellData -> cellData.getValue().deleteProperty());
		deleteColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Asset, String> serialIdColumn = new TableColumn<>("Serial Number");
		serialIdColumn.setCellValueFactory(cellData -> cellData.getValue().getSerialIdProperty());
		serialIdColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Asset, String> modelNumberColumn = new TableColumn<>("Model Number");
		modelNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getModelNumberProperty());
		modelNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		// Inside your createTab method
// Set alignment of the table cells to center
		tableView.setStyle("-fx-alignment: CENTER;");

// Alternatively, you can set alignment for individual columns
		assetNameColumn.setStyle("-fx-alignment: CENTER;");
		AssetImageColumn.setStyle("-fx-alignment: CENTER;");
		MemoImageColumn.setStyle("-fx-alignment: CENTER;");
		serialIdColumn.setStyle("-fx-alignment: CENTER;");
		modelNumberColumn.setStyle("-fx-alignment: CENTER;");
		categoryColumn.setStyle("-fx-alignment: CENTER;");
		usingStartTimeColumn.setStyle("-fx-alignment: CENTER;");
		buyingTimeColumn.setStyle("-fx-alignment: CENTER;");
		buyingPriceColumn.setStyle("-fx-alignment: CENTER;");
		possibleSellingPriceColumn.setStyle("-fx-alignment: CENTER;");
		assetPictureColumn.setStyle("-fx-alignment: CENTER;");
		vaulterPictureColumn.setStyle("-fx-alignment: CENTER;");
		descriptionColumn.setStyle("-fx-alignment: CENTER;");
		montobboColumn.setStyle("-fx-alignment: CENTER;");
		editColumn.setStyle("-fx-alignment: CENTER;");
		deleteColumn.setStyle("-fx-alignment: CENTER;");
		// Add columns to the table
		tableView.getColumns().addAll(assetNameColumn,serialIdColumn,modelNumberColumn ,categoryColumn, usingStartTimeColumn,
				buyingTimeColumn, buyingPriceColumn, possibleSellingPriceColumn,
				AssetImageColumn ,MemoImageColumn, descriptionColumn, montobboColumn,editColumn,deleteColumn);

		// Sample data
		tableView.getItems().addAll(
				new Asset("Asset 1","12","345" ,"Category 1", LocalDate.now(), LocalDate.now(),
						"100", "150", "img/xyz.jpg", "img/xyz.jpg","img/xyz.jpg","img/xyz.jpg", "Description for asset 1", "Montobbo for asset 1","Edit","Delete"),
				new Asset("Asset 2","12","345" , "Category 2", LocalDate.now(), LocalDate.now(),
						"200", "250", "img/xyz.jpg","img/xyz.jpg" ,"img/xyz.jpg","img/xyz.jpg", "Description for asset 2", "Montobbo for asset 2","Edit","Delete")
		);
		tableView.setEditable(true);
		verticaldata.getChildren().add(tableView);




		submit=new Button("Add");
		submit.setOnAction(e->{
			tableView.getItems().forEach(f->{
				out.println(f.toString());
			});


		});
		// submit.setVisible(false);


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
	private <T> Callback<TableColumn<Asset, String>, TableCell<Asset, String>> createComboBoxTableCell() throws SQLException {
		ObservableList<String> items=FXCollections.observableArrayList();
		AssetDataList=new NameCategoryController().retrieveData(repositoryManager,"asset");
		AssetDataList.forEach(e->{
			items.add(e.getName());
		});
		return col -> {
			ComboBoxTableCell<Asset, String> cell = new ComboBoxTableCell<>(FXCollections.observableArrayList(items));
			return cell;
		};
	}
	private <T> Callback<TableColumn<Asset, String>, TableCell<Asset, String>> createComboBoxTableCellCategory() throws SQLException {
		ObservableList<String> items=FXCollections.observableArrayList();
		CategoryDataList=new NameCategoryController().retrieveData(repositoryManager,"category");
		CategoryDataList.forEach(e->{
			items.add(e.getName());
		});
		return col -> {
			ComboBoxTableCell<Asset, String> cell = new ComboBoxTableCell<>(FXCollections.observableArrayList(items));
			return cell;
		};
	}
	public void updateRow(NameAndCategory exData, AssetData person,String type,TableView<AssetData> tableView) throws SQLException {


		new NameCategoryController().updateData(repositoryManager,exData,person,type);


		// update table
		assetData=new NameCategoryController().retrieveData(repositoryManager,type);

		tableView.getItems().clear();

		//String birthDate, String deathDate, String startHours, String startMinute,
		//                      String lateHours, String lateMinute, String endHours, String endMinute,
		//                      String earlyHours, String earlyMinute
		for(int i=0;i<assetData.size();i++)
		{
			tableView.getItems().addAll(
					new AssetData(assetData.get(i).getName(),"Edit","Delete")

			);
		}

	}
	private void updateComboBoxItems(ComboBoxTableCell<Asset, String> cell) {
		cell.getItems().clear();
		for (NameAndCategory asset : AssetDataList) {
			cell.getItems().add(asset.getName());
		}
	}
	private void updateComboBoxItemsCategory(ComboBoxTableCell<Asset, String> cell) {
		cell.getItems().clear();
		for (NameAndCategory asset : CategoryDataList) {
			cell.getItems().add(asset.getName());
		}
	}
	public void deleteRow(NameAndCategory exData, AssetData person,String type,TableView<AssetData> tableView) throws SQLException {


		new NameCategoryController().deleteData(repositoryManager,exData,person,type);


		// update table
		assetData=new NameCategoryController().retrieveData(repositoryManager,type);

		tableView.getItems().clear();

		//String birthDate, String deathDate, String startHours, String startMinute,
		//                      String lateHours, String lateMinute, String endHours, String endMinute,
		//                      String earlyHours, String earlyMinute
		for(int i=0;i<assetData.size();i++)
		{
			tableView.getItems().addAll(
					new AssetData(assetData.get(i).getName(),"Edit","Delete")

			);
		}

	}

	// Define a class to represent the data for each row
	public static class AssetData {
		private final StringProperty assetName;
		private final StringProperty edit;
		private final StringProperty delete;

		public AssetData(String assetName,String edit,String delete)
		{
			this.assetName = new SimpleStringProperty(assetName);
			this.edit = new SimpleStringProperty(edit);
			this.delete = new SimpleStringProperty(delete);
		}

		public StringProperty assetNameProperty() {
			return assetName;
		}
		public StringProperty editProperty() {
			return edit;
		}
		public StringProperty deleteProperty() {
			return delete;
		}
		public String getAssetName() {
			return assetName.get();
		}

		public void setAssetName(String assetName) {
			this.assetName.set(assetName);
		}
	}

	// Define Asset class
	public static class Asset {
		private final StringProperty assetName;
		private final StringProperty category;
		private final StringProperty serialId;
		private final StringProperty modelNumber;
		private final ObjectProperty<LocalDate> usingStartTime;
		private final ObjectProperty<LocalDate> buyingTime;
		private final StringProperty buyingPrice;
		private final StringProperty possibleSellingPrice;
		private final StringProperty assetPicture;
		private final StringProperty vaulterPicture;
		private final StringProperty description;
		private final StringProperty montobbo;
		private final StringProperty edit;
		private final StringProperty delete;
		private ImageView AssetImageView;
		private ImageView MemoImageView;


		public Asset(String assetName,String serialId,String modelNumber ,String category, LocalDate usingStartTime,
					 LocalDate buyingTime, String buyingPrice, String possibleSellingPrice,
					 String assetPicture,String AssetImageUrl,String MemoImageUrl ,String vaulterPicture, String description, String montobbo,String edit,String delete) {
			this.assetName = new SimpleStringProperty(assetName);
			this.serialId = new SimpleStringProperty(serialId);
			this.modelNumber = new SimpleStringProperty(modelNumber);
			this.category = new SimpleStringProperty(category);
			this.usingStartTime = new SimpleObjectProperty<>(usingStartTime);
			this.buyingTime = new SimpleObjectProperty<>(buyingTime);
			this.buyingPrice = new SimpleStringProperty(buyingPrice);
			this.possibleSellingPrice = new SimpleStringProperty(possibleSellingPrice);
			this.assetPicture = new SimpleStringProperty(assetPicture);
			this.vaulterPicture = new SimpleStringProperty(vaulterPicture);
			this.description = new SimpleStringProperty(description);
			this.montobbo = new SimpleStringProperty(montobbo);
			this.edit = new SimpleStringProperty(edit);
			this.delete = new SimpleStringProperty(delete);
			this.AssetImageView = new ImageView(new Image(AssetImageUrl));
			this.AssetImageView.setFitWidth(100);
			this.AssetImageView.setFitHeight(100);
			this.MemoImageView = new ImageView(new Image(MemoImageUrl));
			this.MemoImageView.setFitWidth(100);
			this.MemoImageView.setFitHeight(100);
		}

		public StringProperty assetNameProperty() {
			return assetName;
		}
		public StringProperty getSerialIdProperty() {
			return serialId;
		}

		public StringProperty getModelNumberProperty() {
			return modelNumber;
		}

		public StringProperty categoryProperty() {
			return category;
		}

		public ObjectProperty<LocalDate> usingStartTimeProperty() {
			return usingStartTime;
		}

		public ObjectProperty<LocalDate> buyingTimeProperty() {
			return buyingTime;
		}

		public StringProperty buyingPriceProperty() {
			return buyingPrice;
		}

		public StringProperty possibleSellingPriceProperty() {
			return possibleSellingPrice;
		}

		public StringProperty assetPictureProperty() {
			return assetPicture;
		}

		public StringProperty vaulterPictureProperty() {
			return vaulterPicture;
		}

		public StringProperty descriptionProperty() {
			return description;
		}

		public StringProperty montobboProperty() {
			return montobbo;
		}
		public ImageView getAssetImageView() {
			return AssetImageView;
		}

		public void setAssetImageView(ImageView imageView) {
			this.AssetImageView = imageView;
		}
		public ImageView getMemoImageView() {
			return MemoImageView;
		}

		public void setMemoImageView(ImageView imageView) {
			this.MemoImageView = imageView;
		}
		public StringProperty editProperty() {
			return edit;
		}
		public StringProperty deleteProperty() {
			return delete;
		}
		public String getAssetName() {
			return assetName.get();
		}

		public void setAssetName(String assetName) {
			this.assetName.set(assetName);
		}

		public String getCategory() {
			return category.get();
		}

		public void setCategory(String category) {
			this.category.set(category);
		}

		public LocalDate getUsingStartTime() {
			return usingStartTime.get();
		}

		public void setUsingStartTime(LocalDate usingStartTime) {
			this.usingStartTime.set(usingStartTime);
		}

		public LocalDate getBuyingTime() {
			return buyingTime.get();
		}

		public void setBuyingTime(LocalDate buyingTime) {
			this.buyingTime.set(buyingTime);
		}

		public String getBuyingPrice() {
			return buyingPrice.get();
		}

		public void setBuyingPrice(String buyingPrice) {
			this.buyingPrice.set(buyingPrice);
		}

		public String getPossibleSellingPrice() {
			return possibleSellingPrice.get();
		}

		public void setPossibleSellingPrice(String possibleSellingPrice) {
			this.possibleSellingPrice.set(possibleSellingPrice);
		}

		public String getAssetPicture() {
			return assetPicture.get();
		}

		public void setAssetPicture(String assetPicture) {
			this.assetPicture.set(assetPicture);
		}

		public String getVaulterPicture() {
			return vaulterPicture.get();
		}

		public void setVaulterPicture(String vaulterPicture) {
			this.vaulterPicture.set(vaulterPicture);
		}

		public String getDescription() {
			return description.get();
		}

		public void setDescription(String description) {
			this.description.set(description);
		}

		public String getMontobbo() {
			return montobbo.get();
		}

		public void setMontobbo(String montobbo) {
			this.montobbo.set(montobbo);
		}

		public String getSerialId() {
			return serialId.get();
		}

		public void setSerialId(String serialId) {
			this.serialId.set(serialId);
		}
		public String getModelNumber() {
			return modelNumber.get();
		}

		public void setModelNumber(String modelNumber) {
			this.modelNumber.set(modelNumber);
		}
		@Override
		public String toString() {
			return "Asset{" + "assetName=" + assetName.get() + ", category=" + category.get() + ", serialId="
					+ serialId.get() + ", modelNumber=" + modelNumber.get() + ", usingStartTime=" + usingStartTime.get()
					+ ", buyingTime=" + buyingTime.get() + ", buyingPrice=" + buyingPrice.get() + ", possibleSellingPrice="
					+ possibleSellingPrice.get() + ", assetPicture=" + assetPicture.get() + ", vaulterPicture="
					+ vaulterPicture.get() + ", description=" + description.get() + ", montobbo=" + montobbo.get()
					+ ", edit=" + edit.get() + ", delete=" + delete.get() + '}';
		}
	}

	// Custom cell editor for LocalDate (DatePicker)
	public static class DatePickerTableCell<S, T> extends TableCell<S, T> {
		private final DatePicker datePicker;

		public DatePickerTableCell() {
			this.datePicker = new DatePicker();
			this.datePicker.setConverter(new StringConverter<LocalDate>() {
				@Override
				public String toString(LocalDate date) {
					return date == null ? "" : date.toString();
				}

				@Override
				public LocalDate fromString(String string) {
					return string == null || string.isEmpty() ? null : LocalDate.parse(string);
				}
			});
			this.datePicker.setOnAction(event -> commitEdit((T) datePicker.getValue()));
		}

		@Override
		protected void updateItem(T item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setGraphic(null);
			} else {
				datePicker.setValue((LocalDate) item);
				setGraphic(datePicker);
			}
		}

		@Override
		public void commitEdit(T newValue) {
			super.commitEdit(newValue);
			setText(newValue.toString());
			setGraphic(null);
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();
			setText(getItem().toString());
			setGraphic(null);
		}
	}
	public static class DatePickerTableCellBirth extends TableCell<Asset, String> {
		private final DatePicker datePicker;

		public DatePickerTableCellBirth() {
			this.datePicker = new DatePicker();
			this.datePicker.setConverter(new SettingView.DatePickerFormatter());
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
			Asset person = (Asset) getTableRow().getItem();

			// Update the birthDate property in the Person object
			person.setUsingStartTime(datePicker.getValue());

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
	// Custom cell for displaying pictures
	public static class PictureTableCell extends TableCell<Asset, String> {
		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (empty || item == null || item.isEmpty()) {
				setText(null);
				setGraphic(null);
			} else {
				Button viewButton = new Button("View");
				viewButton.setOnAction(event -> {
					// Logic to view the picture
					System.out.println("Viewing picture: " + item);
				});
				setGraphic(viewButton);
			}
		}
	}

}
